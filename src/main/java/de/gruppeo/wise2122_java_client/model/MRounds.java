package de.gruppeo.wise2122_java_client.model;

import lombok.Getter;

/**
 * Modell einer Quizrunde mit allen
 * Attributen, die zum JSON-Parsing
 * benötigt werden. Getter-Methoden
 * werden mittels Lombok automatisch
 * generiert.
 */

@Getter
public class MRounds {

    private int quizroundsid;
    private int rounds;

    /**
     * Leerer Konstruktor wird
     * beim JSON-Parsing benötigt.
     */
    public MRounds() {}

    /**
     * Konstruktor zur Initialisierung
     * eines Quizrunden-Objekts.
     *
     * @param quizroundsid Eindeutige ID einer Quizrunde
     * @param rounds Quizrunde
     */
    public MRounds(int quizroundsid, int rounds) {
        this.quizroundsid = quizroundsid;
        this.rounds = rounds;
    }
}