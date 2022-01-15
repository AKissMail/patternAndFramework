package de.gruppeo.wise2122_java_client.models;

import lombok.Getter;

@Getter
public class MHighscore {

    private int quizhighscoreid;
    public int highscorepoints;
    private String playername;
    private String lastupdate;

    public MHighscore() {}

    public MHighscore(int quizhighscoreid, int highscorepoints, String playername, String lastupdate) {
        this.quizhighscoreid = quizhighscoreid;
        this.highscorepoints = highscorepoints;
        this.playername = playername;
        this.lastupdate = lastupdate;
    }

    public MHighscore(int highscorepoints) {
        this.highscorepoints = highscorepoints;
    }
}