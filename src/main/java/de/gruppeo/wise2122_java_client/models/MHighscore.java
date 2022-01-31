package de.gruppeo.wise2122_java_client.models;

import lombok.Getter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Modell eines Highscores mit allen
 * Attributen, die zum JSON-Parsing
 * benötigt werden. Getter-Methoden
 * werden mittels Lombok automatisch
 * generiert.
 */

@Getter
public class MHighscore {

    private int quizhighscoreid;
    public int highscorepoints;
    private String playername;
    private String lastupdate;
    private int rank;

    /**
     * Leerer Konstruktor wird
     * beim JSON-Parsing benötigt.
     */
    public MHighscore() {}

    /**
     * Konstruktor, der alle für die
     * JavaFX-Highscore-View benötigten
     * Attribute initialisiert.
     *
     * @param rank Platzierung
     * @param playername Name des Spielers
     * @param highscorepoints Höchste Punktzahl eines Spielers
     * @param lastupdate Datum der letzten Aktualisierung
     */
    public MHighscore(int rank, String playername, int highscorepoints, String lastupdate) {
        this.rank = rank;
        this.playername = playername;
        this.highscorepoints = highscorepoints;
        this.lastupdate = lastupdate;
    }

    /**
     *  Formatiert die vom Server stammende
     *  Zeichenkette in ein gut lesbares Datum.
     *
     * @return lastUpdate Formatiertes Datum
     */
    public String getLastupdate() {
        // lastupdate = "2022-01-21T16:44:16.380622"
        // der Timestamp wird zerlegt
        String[] newDate = lastupdate.split("\\.");
        // nun wird ein Regex festgelegt und nach deutschen Datumsformat formatiert zurückgegeben
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        try {
            LocalDateTime returnDate = LocalDateTime.parse(newDate[0], formatter);
            return returnDate.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.GERMAN));
        } catch (DateTimeParseException e) {
            return lastupdate;
        }
    }
}