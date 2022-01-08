package de.gruppeo.wise2122_java_client.models;

import lombok.Getter;
import lombok.Setter;

/**
 *
 */

@Getter
@Setter
public class MQuestion {

    private int quizquestionid;
    private String question;
    private String correctAnswer;
    private String falseAnswer1;
    private String falseAnswer2;
    private String falseAnswer3;
    private String difficulty;
    private MCategory category;

    public MQuestion() {}

    public MQuestion(int quizquestionid, String question, String correctAnswer, String falseAnswer1, String falseAnswer2, String falseAnswer3, String difficulty, MCategory category) {
        this.quizquestionid = quizquestionid;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.falseAnswer1 = falseAnswer1;
        this.falseAnswer2 = falseAnswer2;
        this.falseAnswer3 = falseAnswer3;
        this.difficulty = difficulty;
        this.category = category;
    }
}