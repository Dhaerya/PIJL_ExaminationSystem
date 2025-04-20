

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ExamPanel extends JPanel {
    private ExamSession session;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;
    private JButton nextButton;
    private JButton previousButton;
    private JButton submitButton;
    private JLabel timerLabel;
    private JLabel questionCountLabel;
    private Timer timer;
    private int timeLeft = 20 * 60; // 20 minutes in seconds
    
    public ExamPanel() {
        initializeUI();
        startExam();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(0, 128, 128)); // Teal background to match other screens
        
        // Top panel for timer and question counter
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        
        timerLabel = new JLabel("Time Left: 20:00");
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(timerLabel, BorderLayout.WEST);
        
        questionCountLabel = new JLabel("Question 1/10");
        questionCountLabel.setForeground(Color.WHITE);
        questionCountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(questionCountLabel, BorderLayout.EAST);
        
        add(topPanel, BorderLayout.NORTH);
        
        // Center panel for question and options
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        
        questionLabel = new JLabel();
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(questionLabel);
        centerPanel.add(Box.createVerticalStrut(20));
        
        optionButtons = new JRadioButton[4];
        optionGroup = new ButtonGroup();
        
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 16));
            optionButtons[i].setForeground(Color.WHITE);
            optionButtons[i].setBackground(new Color(0, 128, 128));
            optionGroup.add(optionButtons[i]);
            centerPanel.add(optionButtons[i]);
            centerPanel.add(Box.createVerticalStrut(10));
            
            final int optionIndex = i;
            optionButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (session != null) {
                        session.submitAnswer(currentQuestionIndex, optionIndex);
                    }
                }
            });
        }
        
        add(centerPanel, BorderLayout.CENTER);
        
        // Bottom panel for navigation buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setOpaque(false);
        
        previousButton = new JButton("Previous");
        previousButton.setFont(new Font("Arial", Font.BOLD, 14));
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToPreviousQuestion();
            }
        });
        bottomPanel.add(previousButton);
        
        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToNextQuestion();
            }
        });
        bottomPanel.add(nextButton);
        
        submitButton = new JButton("Submit Exam");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitExam();
            }
        });
        bottomPanel.add(submitButton);
        
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private void startExam() {
        String username = AuthenticationManager.getInstance().getCurrentUser().getUsername();
        session = ExamManager.getInstance().startExam(username);
        questions = session.getQuestions();
        
        updateUI();
        
        // Start the timer
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                updateTimerDisplay();
                
                if (timeLeft <= 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(ExamPanel.this, 
                        "Time's up! Your exam will be submitted.", 
                        "Time Expired", 
                        JOptionPane.INFORMATION_MESSAGE);
                    submitExam();
                }
            }
        });
        timer.start();
    }
    
    private void updateUI() {
        if (currentQuestionIndex >= 0 && currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex);
            questionLabel.setText("<html><body>" + (currentQuestionIndex + 1) + ". " + question.getQuestionText() + "</body></html>");
            
            String[] options = question.getOptions();
            for (int i = 0; i < 4; i++) {
                optionButtons[i].setText(options[i]);
            }
            
            // Set selected option if previously answered
            int selectedOption = session.getUserAnswers()[currentQuestionIndex];
            if (selectedOption >= 0 && selectedOption < 4) {
                optionButtons[selectedOption].setSelected(true);
            } else {
                optionGroup.clearSelection();
            }
            
            // Update navigation button states
            previousButton.setEnabled(currentQuestionIndex > 0);
            nextButton.setEnabled(currentQuestionIndex < questions.size() - 1);
            
            // Update question counter
            questionCountLabel.setText("Question " + (currentQuestionIndex + 1) + "/" + questions.size());
        }
    }
    
    private void navigateToPreviousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            updateUI();
        }
    }
    
    private void navigateToNextQuestion() {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            updateUI();
        }
    }
    
    private void updateTimerDisplay() {
        int minutes = timeLeft / 60;
        int seconds = timeLeft % 60;
        timerLabel.setText(String.format("Time Left: %02d:%02d", minutes, seconds));
    }
    
    private void submitExam() {
        int option = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to submit your exam? This action cannot be undone.",
            "Confirm Submission",
            JOptionPane.YES_NO_OPTION
        );
        
        if (option == JOptionPane.YES_OPTION) {
            timer.stop();
            String username = AuthenticationManager.getInstance().getCurrentUser().getUsername();
            ExamManager.getInstance().finishExam(username);
            
            // Switch to After Exam screen
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new AfterExam());
            frame.revalidate();
            frame.repaint();
        }
    }
}