package de.gruppeo.wise2122_java_client.models;

import javafx.scene.image.Image;

public class MPlayer {

    private int playerID;
    private String username;
    private int currentScore;
    private int highscore;

    public MPlayer() {

    }

    public MPlayer(String username) {
        this.username = username;
    }

    public MPlayer(Image picture) {
        this.picture = picture;
    }

    public Image getPicture() {
        return picture;
    }

    public void setPicture(Image picture) {
        this.picture = picture;
    }

    private Image picture;
    //private enum currentStatus;

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
