package de.gruppeo.wise2122_java_client.models;

import lombok.Getter;
import lombok.Setter;

/**
 *
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

    private int rank;

    /**
     * Standardkonstruktor wird zwingend zur
     * Initialisierung der JSON-Mappers benötigt.
     */
    public MPlayer() {}

    /**
     * 1:1-Abbild der JSON-Struktur. Initialisiert
     * alle im JSON-String enthaltenen Datenfelder.
     *
     * @param playerid
     * @param username
     * @param currentscore
     * @param currentstatus
     */
    public MPlayer(int playerid, String username, String thumbnail, int currentscore, String currentstatus, MHighscore highscore) {
        this.playerid = playerid;
        this.username = username;
        this.thumbnail = thumbnail;
        this.currentscore = currentscore;
        this.currentstatus = currentstatus;
        this.highscore = highscore;
    }

    public MPlayer(int rank, String username, MHighscore highscore) {
        this.rank = rank;
        this.username = username;
        this.highscore = highscore;
    }
}