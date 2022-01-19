package de.gruppeo.wise2122_java_client.helpers;

import de.gruppeo.wise2122_java_client.models.MConfig;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {
    HttpURLConnection connection;
    private String serverResponse;
    private String privateToken;

    /**
     * Initialisiert Objekt zum Aufbau einer
     * HTTP-Verbindung und stellt privaten Token
     * zur Verfügung.
     *
     * @param directory
     * @throws Exception
     */
    public Connection(String directory) throws Exception {
        connection = (HttpURLConnection) new URL(MConfig.getInstance().getBaseURL() + directory).openConnection();
        privateToken = MConfig.getInstance().getPrivateToken();
    }

    /**
     * Fragt den Server nach einer JSON-Datei
     * und speichert das Ergebnis in einer
     * globalen Variable, die von außen über
     * eine GETTER-Methode gelesen werden kann.
     *
     * @throws Exception
     */
    private void getData() throws Exception {
        // Konfiguriert HTTP-Verbindung
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + privateToken);
        connection.setRequestProperty("Content-Type", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String output;

        StringBuffer response = new StringBuffer();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        serverResponse = response.toString();
    }

    /**
     * Sendet dem Server Daten über
     * eine strukturierte JSON-Datei
     * und speichert Antwort in globaler
     * Variable 'serverResponse'.
     *
     * @param serverInput
     * @throws Exception
     */
    private void sendData(String requestMethod, String serverInput) throws Exception {
        // Konfiguriert HTTP-Verbindung
        connection.setRequestMethod(requestMethod);

        if (MConfig.getInstance().getPrivateToken() != null) {
            connection.setRequestProperty("Authorization", "Bearer " + privateToken);
        }

        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        // Schreibt Server-Input
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = serverInput.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Liest die Server-Antwort vom Input Stream
        try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;

            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            serverResponse = response.toString();
        }
    }

    /**
     * Gibt die Antwort des Servers zurück.
     *
     * @return Antwort des Servers
     */
    public String getServerResponse() throws Exception {
        if (serverResponse == null) {
            getData();
        }
        return serverResponse;
    }

    /**
     * Gibt das Verbindungs-Objekt mit
     * all seinen Methoden zurück.
     *
     * @return Verbindungs-Objekt
     */
    public HttpURLConnection getConnection() {
        return connection;
    }

    /**
     * Registriert einen neuen Spieler mit
     * einem Benuternamen und Passwort.
     *
     * @param username
     * @param password
     */
    public void signUpPlayer(String username, String password) {
        try {
            sendData("POST", "{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }");
            System.out.println("Spieler wurde registriert");
        } catch (Exception e) {
            System.out.println("Spieler konnte nicht registriert werden: " + e);
        }
    }

    /**
     * Meldet einen registrierten Spieler mit
     * einem Benutzernamen und Passwort an.
     *
     * @param username
     * @param password
     */
    public void logInPlayer(String username, String password) {
        try {
            sendData("POST", "{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }");
            System.out.println("Spieler wurde angemeldet");
        } catch (Exception e) {
            System.out.println("Spieler konnte nicht angemeldet werden: " + e);
        }
    }

    /**
     * Aktualisiert das Passwort
     * des angemeldeten Benutzers.
     *
     * @param username
     * @param currentPassword
     * @param newPassword
     */
    public void updatePassword(String username, String currentPassword, String newPassword) {
        try {
            sendData("POST", "{ \"playername\": \"" + username + "\", \"oldpassword\": \"" + currentPassword + "\", \"newpassword\": \"" + newPassword + "\" }");
            System.out.println("Passwort wurde aktualisiert");
        } catch (Exception e) {
            System.out.println("Passwort konnte nicht aktualisiert werden: " + e);
        }
    }

    /**
     * Kann den Benutzernamen des Spieler 1
     * und Spieler 2 in ein erstelltes Spiel
     * schreiben und den Spielstatus ändern.
     * Eine leere Zeichenkette überschreibt
     * den entsprechenden Benutzernamen nicht.
     *
     * @param gamesID
     * @param playerOne
     * @param playerTwo
     * @param status
     */
    public void updateGame(int gamesID, String playerOne, String playerTwo, String status) {
        try {
            sendData("PUT", "{ \"gamesid\": \"" + gamesID + "\", \"playerone\": \"" + playerOne + "\", \"playertwo\": \"" + playerTwo + "\", \"status\": \"" + status + "\" }");
            System.out.println("Spiel " + gamesID + " aktualisiert");
        } catch (Exception e) {
            System.out.println("Spiel konnte nicht aktualisiert werden: " + e);
        }
    }

    /**
     * Registriert ein neues Spiel mit Benuternamen
     * des Initiators, der ausgewählten Quiz-Kategorie
     * und der Rundenzahl. Der Spielstatus eines neuen
     * Spiels wird automatisch auf OPEN gestellt und
     * in der Liste der verfügbaren Spiele angezeigt.
     *
     * @param playerOne
     * @param category
     * @param rounds
     */
    public void createGame(String playerOne, String category, int rounds) {
        try {
            sendData("POST", "{ \"username\": \"" + playerOne + "\", \"category\": \"" + category + "\", \"rounds\": \"" + rounds + "\" }");
            System.out.println("Neues Spiel registriert");
        } catch (Exception e) {
            System.out.println("Spiel konnte nicht erstellt werden: " + e);
        }
    }

    /**
     * Löscht das übergebene Spiel.
     *
     * @param gamesID
     */
    public void deleteGame(int gamesID) {
        try {
            sendData("PUT", "{ \"gamesid\": \"" + gamesID + "\", \"playerone\": \"" + "null" + "\", \"playertwo\": \"" + "null" + "\", \"status\": \"" + "OPEN" + "\" }");
            System.out.println("Spiel " + gamesID + " wurde gelöscht");
        } catch (Exception e) {
            System.out.println("Spiel konnte nicht gelöscht werden: " + e);
        }
    }

    /**
     * Sendet Daten einer
     * Beantwortung an den Server.
     *
     * @param gameID
     * @param isPlayerOne
     * @param answer
     * @param timer
     */
    public void dropAnswer(int gameID, boolean isPlayerOne, boolean answer, int timer) {
        try {
            sendData("PUT", "{ \"gamesid\": \"" + gameID + "\", \"isplayerone\": \"" + isPlayerOne + "\", \"answers\": \"" + answer + "\", \"time\": \"" + timer + "\" }");
            System.out.println("Antwort zu Spiel " + gameID + " wurde gesendet");
        } catch (Exception e) {
            System.out.println("Antwort konnte nicht gesendet werden: " + e);
        }
    }

    /**
     * Setzt den Highscore des Spielers zurück.
     * Der Spieler wird von der Api anhand des Tokens ermittelt.
     */
    public void resetHighscore() {
        try {
            sendData("PUT", "{ \"playerHighscore\": \"0\", \"token\": \"" + privateToken + "\" }");
            System.out.println("Highscore des Spielers zurückgesetzt.");
        } catch (Exception e) {
            System.out.println("Highscore konnte nicht zurückgesetzt werden: " + e);
        }
    }
}