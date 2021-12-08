package de.gruppeo.wise2122_java_client.models;

import javafx.scene.image.Image;

public class MPlayer {

    private Long playerid;
    private String username;
    private int currentscore;
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
    //private enum currentstatus;

    public Long getPlayerid() {
        return playerid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCurrentscore() {
        return currentscore;
    }

    public void setCurrentscore(int currentscore) {
        this.currentscore = currentscore;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
}
