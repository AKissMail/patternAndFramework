package de.gruppeo.wise2122_java_client.models;

import lombok.Getter;

@Getter
public class MRounds {

    private int quizroundsid;
    private int rounds;

    public MRounds() {}

    public MRounds(int quizroundsid, int rounds) {
        this.quizroundsid = quizroundsid;
        this.rounds = rounds;
    }
}