

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import your.package.name.ExamManager; // Replace 'your.package.name' with the actual package name of ExamManager

public class AfterExam extends javax.swing.JPanel {

    /**
     * Creates new form AfterExam
     */
    public AfterExam() {
        initComponents();
        displayResults();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 128, 128)); // Teal background
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        
        // Message panel
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setOpaque(false);
        
        // Response recorded message
        JLabel responseLabel = new JLabel("Your response has been recorded");
        responseLabel.setFont(new Font("Segoe UI", Font.ITALIC, 22));
        responseLabel.setForeground(Color.WHITE);
        responseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messagePanel.add(responseLabel);
        
        messagePanel.add(Box.createVerticalStrut(20));
        
        // Results panel (will be added dynamically in displayResults())
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setOpaque(false);
        resultsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messagePanel.add(resultsPanel);
        
        messagePanel.add(Box.createVerticalStrut(40));
        
        // Thank you message
        JLabel thankYouLabel = new JLabel("Thank You");
        thankYouLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        thankYouLabel.setForeground(Color.WHITE);
        thankYouLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messagePanel.add(thankYouLabel);
        
        messagePanel.add(Box.createVerticalStrut(40));
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        
        JButton homeButton = new JButton("Return to Home");
        homeButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppController.getInstance().showHomeScreen();
            }
        });
        buttonPanel.add(homeButton);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppController.getInstance().logout();
            }
        });
        buttonPanel.add(logoutButton);
        
        messagePanel.add(buttonPanel);
        
        mainPanel.add(messagePanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void displayResults() {
        // Get the current user and their exam results
        String username = AuthenticationManager.getInstance().getCurrentUser().getUsername();
        ExamSession session = ExamManager.getInstance().getCompletedSession(username);
        
        if (session != null) {
            // Format timestamps
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startTime = dateFormat.format(session.getStartTime());
            String endTime = dateFormat.format(session.getEndTime());
            
            // Create results components
            JLabel scoreLabel = new JLabel("Score: " + session.getScore() + " / " + session.getTotalQuestions());
            scoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            scoreLabel.setForeground(Color.WHITE);
            scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            JLabel timeLabel = new JLabel("Time taken: " + formatDuration(session.getDurationInSeconds()));
            timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            JLabel startTimeLabel = new JLabel("Started: " + startTime);
            startTimeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            startTimeLabel.setForeground(Color.WHITE);
            startTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            JLabel endTimeLabel = new JLabel("Completed: " + endTime);
            endTimeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            endTimeLabel.setForeground(Color.WHITE);
            endTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            // Add to results panel
            resultsPanel.add(scoreLabel);
            resultsPanel.add(Box.createVerticalStrut(10));
            resultsPanel.add(timeLabel);
            resultsPanel.add(Box.createVerticalStrut(10));
            resultsPanel.add(startTimeLabel);
            resultsPanel.add(Box.createVerticalStrut(5));
            resultsPanel.add(endTimeLabel);
        }
    }
    
    private String formatDuration(long seconds) {
        long minutes = seconds / 60;
        long remainingSeconds = seconds % 60;
        return String.format("%d minutes, %d seconds", minutes, remainingSeconds);
    }
    
    // Variables declaration
    private JPanel resultsPanel;
    // End of variables declaration
}