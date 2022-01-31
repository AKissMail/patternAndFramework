package de.gruppeo.wise2122_java_client.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Modell eines Spielers mit allen
 * Attributen, die zum JSON-Parsing
 * benötigt werden. Getter-/ Setter-
 * Methoden werden mittels Lombok
 * automatisch generiert.
 */

@Getter
@Setter
public class MPlayer {

    private int playerid;
    private String username;
    private String thumbnail;
    private int currentscore;
    private String currentstatus;
    private MHighscore highscore;

    /**
     * Leerer Konstruktor wird
     * beim JSON-Parsing benötigt.
     */
    public MPlayer() {}

    /**
     * 1:1-Abbild der JSON-Struktur. Initialisiert
     * alle im JSON-String enthaltenen Datenfelder.
     *
     * @param playerid Eindeutige ID des Spielers
     * @param username Eindeutiger Name des Spielers
     * @param currentscore Aktueller Punktestand des Spielers
     * @param currentstatus Aktueller Status des Spielers
     */
    public MPlayer(int playerid, String username, String thumbnail, int currentscore, String currentstatus, MHighscore highscore) {
        this.playerid = playerid;
        this.username = username;
        this.thumbnail = thumbnail;
        this.currentscore = currentscore;
        this.currentstatus = currentstatus;
        this.highscore = highscore;
    }
}