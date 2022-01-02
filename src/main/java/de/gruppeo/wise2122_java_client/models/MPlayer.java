package de.gruppeo.wise2122_java_client.models;

public class MPlayer {

    private int playerid;
    private String username;
    private int currentscore;
    private String currentstatus;
    private int rank;

    /**
     * Standardkonstruktor wird zwingend zur
     * Initialisierung der JSON-Mappers benötigt.
     */
    public MPlayer() {}

    /**
     * 1:1-Abbild der JSON-Struktur. Initialisiert
     * alle im JSON-String enthaltenen Datenfelder.
     *
     * @param playerid
     * @param username
     * @param currentscore
     * @param currentstatus
     */
    public MPlayer(int playerid, String username, int currentscore, String currentstatus) {
        this.playerid = playerid;
        this.username = username;
        this.currentscore = currentscore;
        this.currentstatus = currentstatus;
    }

    /**
     * Wird zur Initialisierung eines neuen Player-Objekts
     * benötigt, das für die Befüllung des Highscores
     * Verwendung findet.
     *
     * @param rank
     * @param username
     * @param currentscore
     */
    public MPlayer(int rank, String username, int currentscore) {
        this.rank = rank;
        this.username = username;
        this.currentscore = currentscore;
    }

    /**
     * Gibt die Platzierung eines Spielers zurück.
     *
     * @return Platzierung
     */
    public int getRank() {
        return rank;
    }

    /**
     * Gibt den Benutzernamen eines Spielers zurück.
     *
     * @return Benutzername
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gibt den aktuellen Punktestand eines Spielers zurück.
     *
     * @return Punktestand
     */
    public int getCurrentscore() {
        return currentscore;
    }

    /**
     * Gibt den aktuellen Spielstatus eines Spielers zurück.
     *
     * @return Spielstatus
     */
    public String getCurrentstatus() {
        return currentstatus;
    }
}