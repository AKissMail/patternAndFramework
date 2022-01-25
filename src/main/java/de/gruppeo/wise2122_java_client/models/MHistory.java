package de.gruppeo.wise2122_java_client.models;

import lombok.Getter;

@Getter
public class MHistory {

    int id;
    String playername;
    int playerscore;
    int opponentscore;
    String categoryname;
    int rounds;
    double average;

    MHistory() {}

    public MHistory(int id, String playername, int playerscore, int opponentscore, String categoryname, int rounds, double average) {
        this.id = id;
        this.playername = playername;
        this.playerscore = playerscore;
        this.opponentscore = opponentscore;
        this.categoryname = categoryname;
        this.rounds = rounds;
        this.average = average;
    }

    public double getAverage() {
        return 42.0; // @TODO Muss noch implementiert werden

    }
}