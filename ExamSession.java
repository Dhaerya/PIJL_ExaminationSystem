

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamSession {
    private String username;
    private List<Question> questions;
    private int[] userAnswers;
    private Date startTime;
    private Date endTime;
    private boolean completed;
    private int score;
    
    public ExamSession(String username, List<Question> questions) {
        this.username = username;
        this.questions = questions;
        this.userAnswers = new int[questions.size()];
        // Initialize with -1 indicating not answered
        for (int i = 0; i < userAnswers.length; i++) {
            userAnswers[i] = -1;
        }
        this.startTime = new Date();
        this.completed = false;
    }
    
    public void submitAnswer(int questionIndex, int answerIndex) {
        if (questionIndex >= 0 && questionIndex < userAnswers.length) {
            userAnswers[questionIndex] = answerIndex;
        }
    }
    
    public void finishExam() {
        endTime = new Date();
        completed = true;
        calculateScore();
    }
    
    private void calculateScore() {
        score = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (userAnswers[i] == questions.get(i).getCorrectOption()) {
                score++;
            }
        }
    }
    
    // Getters
    public String getUsername() {
        return username;
    }
    
    public List<Question> getQuestions() {
        return questions;
    }
    
    public int[] getUserAnswers() {
        return userAnswers;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    
    public Date getEndTime() {
        return endTime;
    }
    
    public boolean isCompleted() {
        return completed;
    }
    
    public int getScore() {
        return score;
    }
    
    public int getTotalQuestions() {
        return questions.size();
    }
    
    public long getDurationInSeconds() {
        if (endTime == null) {
            return (new Date().getTime() - startTime.getTime()) / 1000;
        }
        return (endTime.getTime() - startTime.getTime()) / 1000;
    }
}