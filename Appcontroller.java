

import javax.swing.*;
import java.awt.*;

public class AppController {
    private static AppController instance;
    private JFrame mainFrame;
    
    private AppController() {
        // Initialize the main application frame
        mainFrame = new JFrame("Online Examination System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
    }
    
    public static AppController getInstance() {
        if (instance == null) {
            instance = new AppController();
        }
        return instance;
    }
    
    public void start() {
        // Start with login screen
        showLoginScreen();
        mainFrame.setVisible(true);
    }
    
    public void showLoginScreen() {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(new LoginPage());
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    
    public void showHomeScreen() {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(new Home());
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    
    public void showExamScreen() {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(new ExamPanel());
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    
    public void showAfterExamScreen() {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(new AfterExam());
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    
    public void logout() {
        AuthenticationManager.getInstance().logout();
        showLoginScreen();
    }
}