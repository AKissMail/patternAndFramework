package de.gruppeo.wise2122_java_client.models;

import lombok.AccessLevel;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Getter
public class MHighscore {

    private int quizhighscoreid;
    public int highscorepoints;
    private String playername;
    @Getter(AccessLevel.NONE)
    private String lastupdate;

    private int rank;

    public MHighscore() {}

    public MHighscore(int quizhighscoreid, int highscorepoints, String playername, String lastupdate) {
        this.quizhighscoreid = quizhighscoreid;
        this.highscorepoints = highscorepoints;
        this.playername = playername;
        this.lastupdate = lastupdate;
    }

    public MHighscore(int rank, String playername, int highscorepoints, String lastupdate) {
        this.rank = rank;
        this.playername = playername;
        this.highscorepoints = highscorepoints;
        this.lastupdate = lastupdate;
    }

    public String getLastupdate() {
        // lastupdate = "2022-01-21T16:44:16.380622"
        // der Timestamp wird zerlegt
        String[] newDate = lastupdate.split("\\.");
        // nun wird ein Regex festgelegt und nach deutschen Datumsformat formatiert zurückgegeben
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime returnDate = LocalDateTime.parse(newDate[0], formatter);
        String dateStr = returnDate.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.GERMAN));
        System.out.println(dateStr);
        String[] newTempDate = dateStr.split(",");
        dateStr = newTempDate[0];
        return dateStr;
    }
}