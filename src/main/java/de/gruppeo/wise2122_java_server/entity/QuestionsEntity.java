package de.gruppeo.wise2122_java_server.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "questions")
@Table(name = "questions", schema = "mibquizzz")
public class QuestionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quizquestionid", nullable = false, updatable = false)
    private long quizquestionid;

    @Basic
    @Column(name = "question", nullable = false)
    private String question;

    @Basic
    @Column(name = "correctanswer", nullable = false)
    private String correctAnswer;

    @Basic
    @Column(name = "falseanswer1", nullable = false)
    private String falseAnswer1;
    @Basic
    @Column(name = "falseanswer2", nullable = false)
    private String falseAnswer2;
    @Basic
    @Column(name = "falseanswer3", nullable = false)
    private String falseAnswer3;

    @Basic
    @Column(name = "fk_quizcategory")
    private Long fkQuizcategory;

    @Basic
    @Column(name = "difficulty", nullable = false)
    private String difficulty;

    public long getQuizquestionid() {
        return quizquestionid;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getFalseAnswer1() {
        return falseAnswer1;
    }

    public String getFalseAnswer2() {
        return falseAnswer2;
    }

    public String getFalseAnswer3() {
        return falseAnswer3;
    }

    public Long getFkQuizcategory() {
        return fkQuizcategory;
    }

    public String getDifficulty() {return difficulty;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionsEntity that = (QuestionsEntity) o;
        return quizquestionid == that.quizquestionid && Objects.equals(question, that.question) && Objects.equals(correctAnswer, that.correctAnswer) && Objects.equals(falseAnswer1, that.falseAnswer1) && Objects.equals(falseAnswer2, that.falseAnswer2) && Objects.equals(falseAnswer3, that.falseAnswer3) && Objects.equals(fkQuizcategory, that.fkQuizcategory) && Objects.equals(difficulty, that.difficulty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quizquestionid, question, correctAnswer, falseAnswer1, falseAnswer2, falseAnswer3, fkQuizcategory, difficulty);
    }
}
