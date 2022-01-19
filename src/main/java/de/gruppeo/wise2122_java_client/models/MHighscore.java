package de.gruppeo.wise2122_java_client.models;

import lombok.Getter;

@Getter
public class MHighscore {

    private int quizhighscoreid;
    public int highscorepoints;
    private String playername;
    private String lastupdate;
    private int rank;

    public MHighscore() {}

    public MHighscore(int quizhighscoreid, int highscorepoints, String playername, String lastupdate) {
        this.quizhighscoreid = quizhighscoreid;
        this.highscorepoints = highscorepoints;
        this.playername = playername;
        this.lastupdate = lastupdate;
    }

    public MHighscore(int rank, String playername, int highscorepoints, String lastupdate) {
        this.rank = rank;
        this.playername = playername;
        this.highscorepoints = highscorepoints;
        this.lastupdate = lastupdate;
    }
}