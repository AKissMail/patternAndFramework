package de.gruppeo.wise2122_java_client.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Modell einer Frage mit allen
 * Attributen, die zum JSON-Parsing
 * benötigt werden. Getter-Methoden
 * werden mittels Lombok automatisch
 * generiert.
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

    /**
     * Leerer Konstruktor wird
     * beim JSON-Parsing benötigt.
     */
    public MQuestion() {}

    /**
     * Konstruktor zur Initialisierung
     * eines Fragen-Objekts.
     *
     * @param quizquestionid Eindeutige ID einer Frage
     * @param question Inhalt einer Frage
     * @param correctAnswer Korrekte Antwort einer Frage
     * @param falseAnswer1 Falsche Antwort einer Frage
     * @param falseAnswer2 Falsche Antwort einer Frage
     * @param falseAnswer3 Falsche Antwort einer Frage
     * @param difficulty Schwierigkeitsgrad einer Frage
     * @param category Kategorie einer Frage
     */
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