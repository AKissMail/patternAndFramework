package de.gruppeo.wise2122_java_client.models;

import lombok.Getter;
import java.util.List;

/**
 *
 */

@Getter
public class MGame {

    private int id;
    private String gamestatus;

    private MPlayer playerone;
    private int playeronescore;
    private int playeroneround;

    private MPlayer playertwo;
    private int playertwoscore;
    private int playertworound;

    private MCategory category;
    private MRounds rounds;
    private String[] questions;

    public MGame() {}

    public MGame(int id, String gamestatus, MPlayer playerone, int playeronescore, int playeroneround, MPlayer playertwo, int playertwoscore, int playertworound, MCategory category, MRounds rounds, String[] questions) {
        this.id = id;
        this.gamestatus = gamestatus;
        this.playerone = playerone;
        this.playeronescore = playeronescore;
        this.playeroneround = playeroneround;
        this.playertwo = playertwo;
        this.playertwoscore = playertwoscore;
        this.playertworound = playertworound;
        this.category = category;
        this.rounds = rounds;
        this.questions = questions;
    }
}