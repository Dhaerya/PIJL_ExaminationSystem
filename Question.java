

public class Question {
    private int id;
    private String questionText;
    private String[] options;
    private int correctOption; // 0-based index
    
    public Question(int id, String questionText, String[] options, int correctOption) {
        this.id = id;
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getQuestionText() {
        return questionText;
    }
    
    public String[] getOptions() {
        return options;
    }
    
    public int getCorrectOption() {
        return correctOption;
    }
    
    public boolean isCorrect(int selectedOption) {
        return selectedOption == correctOption;
    }
}