

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionBank {
    private List<Question> questions;
    private static QuestionBank instance;
    
    private QuestionBank() {
        questions = new ArrayList<>();
        initializeQuestions();
    }
    
    public static QuestionBank getInstance() {
        if (instance == null) {
            instance = new QuestionBank();
        }
        return instance;
    }
    
    private void initializeQuestions() {
        // Add Java Basic MCQs
        questions.add(new Question(1, 
            "What is the correct way to declare a variable in Java?", 
            new String[]{
                "var x = 10;", 
                "int x = 10;", 
                "x = 10;", 
                "variable x = 10;"
            }, 
            1)); // Correct answer is "int x = 10;"
            
        questions.add(new Question(2, 
            "Which of the following is a valid Java identifier?", 
            new String[]{
                "123abc", 
                "_abc", 
                "java.lang", 
                "#value"
            }, 
            1)); // Correct answer is "_abc"
            
        questions.add(new Question(3, 
            "What is the output of: System.out.println(5 + 7 + \"Java\")?", 
            new String[]{
                "12Java", 
                "5 + 7 + Java", 
                "57Java", 
                "Java12"
            }, 
            0)); // Correct answer is "12Java"
            
        questions.add(new Question(4, 
            "Which statement is true about Java?", 
            new String[]{
                "Java is a purely procedural programming language", 
                "Java programs can run on any machine without recompilation", 
                "Java does not support multiple inheritance through classes", 
                "Java does not support interfaces"
            }, 
            2)); // Correct answer is "Java does not support multiple inheritance through classes"
            
        questions.add(new Question(5, 
            "What is the default value of an int variable in a class?", 
            new String[]{
                "0", 
                "null", 
                "undefined", 
                "1"
            }, 
            0)); // Correct answer is "0"
            
        questions.add(new Question(6, 
            "Which access modifier makes a variable accessible only within its own class?", 
            new String[]{
                "public", 
                "protected", 
                "private", 
                "default"
            }, 
            2)); // Correct answer is "private"
            
        questions.add(new Question(7, 
            "What is the correct way to create an object of class Student?", 
            new String[]{
                "Student s = create Student();", 
                "Student s = new Student;", 
                "Student s = Student();", 
                "Student s = new Student();"
            }, 
            3)); // Correct answer is "Student s = new Student();"
            
        questions.add(new Question(8, 
            "Which of the following is NOT a primitive data type in Java?", 
            new String[]{
                "int", 
                "boolean", 
                "String", 
                "char"
            }, 
            2)); // Correct answer is "String"
            
        questions.add(new Question(9, 
            "What is the meaning of method overloading in Java?", 
            new String[]{
                "Having multiple methods with the same name but different parameters", 
                "Overriding a method in a subclass", 
                "Having multiple methods with different names but same parameters", 
                "Having a method that can accept unlimited number of parameters"
            }, 
            0)); // Correct answer is "Having multiple methods with the same name but different parameters"
            
        questions.add(new Question(10, 
            "What happens when you try to compile and run this code? public static void main(String args) { System.out.println(\"Hello\"); }", 
            new String[]{
                "It will compile and run successfully", 
                "It will compile but throw an exception at runtime", 
                "It will not compile because of incorrect main method signature", 
                "It will compile but will not print anything"
            }, 
            2)); // Correct answer is "It will not compile because of incorrect main method signature"
    }
    
    public List<Question> getRandomQuestions(int count) {
        if (count > questions.size()) {
            count = questions.size();
        }
        
        List<Question> allQuestions = new ArrayList<>(questions);
        Collections.shuffle(allQuestions);
        return allQuestions.subList(0, count);
    }
    
    public List<Question> getAllQuestions() {
        return new ArrayList<>(questions);
    }
}