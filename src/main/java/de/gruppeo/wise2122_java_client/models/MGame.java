package de.gruppeo.wise2122_java_client.models;

import lombok.Getter;

/**
 * Modell eines Spiels mit allen
 * Attributen, die zum JSON-Parsing
 * benötigt werden. Getter-Methoden
 * werden mittels Lombok automatisch
 * generiert.
 */

@Getter
public class MGame {

    private int id;
    private String gamestatus;
    private MPlayer playerone;
    private int playeronescore;
    private int playeroneround;
    private MPlayer playertwo;
    private int playertwoscore;
    private int playertworound;
    private MCategory category;
    private MRounds rounds;
    private MQuestion[] questions;

    /**
     * Leerer Konstruktor wird
     * beim JSON-Parsing benötigt.
     */
    public MGame() {}

    /**
     * Standardkonstruktor zur Initialisierung
     * eines Spiel-Objektes.
     *
     * @param id Eindeutige ID eines Spiels
     * @param gamestatus Aktueller Status eines Spiels
     * @param playerone Initiator eines Spiels
     * @param playeronescore Punktestand des Initiators
     * @param playeroneround Aktuelle Rundenzahl des Initators
     * @param playertwo Beigetretener Spieler eines Spiels
     * @param playertwoscore Punktestand des beigetretenen Spielers
     * @param playertworound Aktuelle Rundenzahl des beigetretenen Spielers
     * @param category Ausgewählte Quizkategorie eines Spiels
     * @param rounds Ausgewählte Rundenzahl eines Spiels
     * @param questions Fragen eines Spiels
     */
    public MGame(int id, String gamestatus, MPlayer playerone, int playeronescore, int playeroneround, MPlayer playertwo, int playertwoscore, int playertworound, MCategory category, MRounds rounds, MQuestion[] questions) {
        this.id = id;
        this.gamestatus = gamestatus;
        this.playerone = playerone;
        this.playeronescore = playeronescore;
        this.playeroneround = playeroneround;
        this.playertwo = playertwo;
        this.playertwoscore = playertwoscore;
        this.playertworound = playertworound;
        this.category = category;
        this.rounds = rounds;
        this.questions = questions;
    }
}