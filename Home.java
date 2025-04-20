

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends javax.swing.JPanel {

    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        setupWelcomeText();
    }

    private void setupWelcomeText() {
        // Get current user's name
        String username = AuthenticationManager.getInstance().getCurrentUser().getUsername();
        
        // Set welcome message
        welcomeLabel.setText("Welcome, " + username + "!");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 128, 128)); // Teal background

        // Panel for header (welcome message)
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        welcomeLabel = new JLabel();
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.WHITE);
        headerPanel.add(welcomeLabel, BorderLayout.WEST);
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppController.getInstance().logout();
            }
        });
        headerPanel.add(logoutButton, BorderLayout.EAST);
        
        add(headerPanel, BorderLayout.NORTH);
        
        // Main content panel
        JPanel contentPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        // Rules panel
        JPanel rulesPanel = new JPanel(new BorderLayout(10, 10));
        rulesPanel.setBackground(Color.WHITE);
        rulesPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 60, 60), 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel rulesTitle = new JLabel("Examination Rules:");
        rulesTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        rulesPanel.add(rulesTitle, BorderLayout.NORTH);
        
        JTextArea rulesText = new JTextArea();
        rulesText.setEditable(false);
        rulesText.setLineWrap(true);
        rulesText.setWrapStyleWord(true);
        rulesText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rulesText.setText(
            "1) No Cheating or Plagiarism: Any form of cheating, including copying from external sources or another person, is strictly prohibited and may result in disqualification.\n\n" +
            "2) Do Not Refresh or Close the Page: Refreshing, closing, or navigating away from the examination window may result in the termination of your test session.\n\n" +
            "3) One Attempt Only: Each student is allowed to attempt the exam only once. Multiple attempts are not permitted.\n\n" +
            "4) Use of Unauthorized Materials is Prohibited: Calculators, books, notes, mobile phones, and other electronic devices are not allowed unless specifically permitted.\n\n" +
            "5) Question Format: The examination will consist of 10 multiple-choice questions (MCQs), each with 4 options. Every question carries 1 mark.\n\n" +
            "NOTE: The examination is time-bound and will be automatically submitted once the time limit is reached, even if not all questions are answered."
        );
        
        JScrollPane rulesScrollPane = new JScrollPane(rulesText);
        rulesScrollPane.setBorder(null);
        rulesPanel.add(rulesScrollPane, BorderLayout.CENTER);
        
        // Right panel with good luck message and start button
        JPanel rightPanel = new JPanel(new BorderLayout(10, 30));
        rightPanel.setOpaque(false);
        
        JLabel goodLuckLabel = new JLabel("BEST OF LUCK !!!!");
        goodLuckLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        goodLuckLabel.setForeground(Color.WHITE);
        goodLuckLabel.setHorizontalAlignment(JLabel.CENTER);
        rightPanel.add(goodLuckLabel, BorderLayout.NORTH);
        
        JButton startExamButton = new JButton("Start Exam");
        startExamButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        startExamButton.setBackground(new Color(100, 220, 120)); // Green button
        startExamButton.setForeground(Color.WHITE);
        startExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startExam();
            }
        });
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(startExamButton);
        rightPanel.add(buttonPanel, BorderLayout.CENTER);
        
        // Add panels to content panel
        contentPanel.add(rulesPanel, BorderLayout.CENTER);
        contentPanel.add(rightPanel, BorderLayout.EAST);
        
        add(contentPanel, BorderLayout.CENTER);
    }
    
    private void startExam() {
        // Confirm before starting
        int option = JOptionPane.showConfirmDialog(
            this,
            "Are you ready to begin the exam? Once started, you will have 20 minutes to complete it.",
            "Start Exam",
            JOptionPane.YES_NO_OPTION
        );
        
        if (option == JOptionPane.YES_OPTION) {
            // Navigate to exam screen
            AppController.getInstance().showExamScreen();
        }
    }
    
    // Variables declaration
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration
}