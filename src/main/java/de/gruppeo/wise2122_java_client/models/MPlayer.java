package de.gruppeo.wise2122_java_client.models;

public class MPlayer {

    private int playerid;
    private int rank;
    private String username;
    private int currentscore;
    private String currentstatus;

    public MPlayer() {}

    public MPlayer(int playerid, String username, int currentscore, String currentstatus) {
        this.playerid = playerid;
        this.username = username;
        this.currentscore = currentscore;
        this.currentstatus = currentstatus;
    }

    public MPlayer(int rank, String username, int currentscore) {
        this.rank = rank;
        this.username = username;
        this.currentscore = currentscore;
    }

    public int getRank() {
        return rank;
    }

    public String getUsername() {
        return username;
    }

    public int getCurrentscore() {
        return currentscore;
    }

    public String getCurrentstatus() {
        return currentstatus;
    }
}