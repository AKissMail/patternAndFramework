package de.gruppeo.wise2122_java_client.models;

import lombok.Getter;

@Getter
public class MHistory {

    int id;
    MPlayer playername;
    int playerscore;
    int opponentscore;
    MCategory category;
    MRounds rounds;
    double average;

    MHistory() {}

    public MHistory(int id, MPlayer playername, int playerscore, int opponentscore, MCategory category, MRounds rounds, double average) {
        this.id = id;
        this.playername = playername;
        this.playerscore = playerscore;
        this.opponentscore = opponentscore;
        this.category = category;
        this.rounds = rounds;
        this.average = average;
    }

    public double getAverage() {
        return 42.0;
    }
}