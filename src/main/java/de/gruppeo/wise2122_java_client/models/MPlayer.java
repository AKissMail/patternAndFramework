package de.gruppeo.wise2122_java_client.models;

public class MPlayer {

    private int playerID;
    private String username;
    private int currentScore;
    private int highscore;
    //private enum currentStatus;

    public MPlayer(String username) {
        this.username = username;
    }

    public int getPlayerID() {
        return playerID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
}
