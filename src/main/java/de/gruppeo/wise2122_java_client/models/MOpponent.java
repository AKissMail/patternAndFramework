package de.gruppeo.wise2122_java_client.models;

import lombok.Getter;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.ArrayList;

@Getter
public class MOpponent {

    private int playerid;
    private String username;
    private int currentscore;
    private String currentstatus;

    public MOpponent() {}

    public MOpponent(int playerid, String username, int currentscore, String currentstatus) {
        this.playerid = playerid;
        this.username = username;
        this.currentscore = currentscore;
        this.currentstatus = currentstatus;
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