package de.gruppeo.wise2122_java_client.models;

public class MQuestion {

    private int questionID;
    private String category;
    private String question;
    private String[] answers;

    public MQuestion() {}

    public MQuestion(int questionID, String category, String question, String[] answers) {
        this.questionID = questionID;
        this.category = category;
        this.question = question;
        this.answers = answers;
    }

    public String getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }
}
