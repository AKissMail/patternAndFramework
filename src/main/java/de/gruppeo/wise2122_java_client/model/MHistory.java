package de.gruppeo.wise2122_java_client.model;

import lombok.Getter;

/**
 * Modell einer Spielhistorie mit allen
 * Attributen, die zum JSON-Parsing
 * benötigt werden. Getter-Methoden
 * werden mittels Lombok automatisch
 * generiert.
 */

@Getter
public class MHistory {

    int id;
    String playername;
    int playerscore;
    int opponentscore;
    String categoryname;
    int rounds;
    double average;

    /**
     * Leerer Konstruktor wird
     * beim JSON-Parsing benötigt.
     */
    MHistory() {}

    /**
     * Konstruktor, der alle für die
     * JavaFX-Historie-View benötigten
     * Attribute initialisiert.
     *
     * @param id Eindeutige ID der Spielhistorie
     * @param playername Name des Spielers
     * @param playerscore Punktestand des angemeldeten Spielers
     * @param opponentscore Punktestand des Gegners eines Spiels
     * @param categoryname Name der Kategorie des gespielten Spiels
     * @param rounds Rundenanzahl des gespielten Spiels
     * @param average Punktedurchschnitt pro Spielrunde
     */
    public MHistory(int id, String playername, int playerscore, int opponentscore, String categoryname, int rounds, double average) {
        this.id = id;
        this.playername = playername;
        this.playerscore = playerscore;
        this.opponentscore = opponentscore;
        this.categoryname = categoryname;
        this.rounds = rounds;
        this.average = average;
    }

    /**
     * Gibt die durchschnittlichen
     * Punkte pro Quizrunde zurück.
     *
     * @return Punktedurchschnitt pro Runde
     */
    public double getAverage() {
        return playerscore / rounds;
    }
}