package de.gruppeo.wise2122_java_client.helpers;

import de.gruppeo.wise2122_java_client.models.MConfig;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Connection {
    HttpURLConnection connection;
    private String serverResponse;
    private String privateToken;

    /**
     * Initialisiert Objekt zum Aufbau einer
     * HTTP-Verbindung und stellt privaten Token
     * zur Verfügung.
     *
     * @param directory URL-Pfad
     */
    public Connection(String directory) {
        try {
            connection = (HttpURLConnection) new URL(MConfig.getInstance().getBaseURL() + directory).openConnection();
            privateToken = MConfig.getInstance().getPrivateToken();
        } catch (Exception e) {
            System.out.println("Verbindung konnte nicht hergestellt werden");
        }
    }

    /**
     * Fragt den Server nach einer JSON-Datei
     * und speichert das Ergebnis in einer
     * globalen Variable, die von außen über
     * eine GETTER-Methode gelesen werden kann.
     *
     * @throws Exception Verbindungsproblem
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
     * @param serverInput JSON-Zeichenkette
     * @throws Exception Verbindungsproblem
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
            byte[] input = serverInput.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Liest die Server-Antwort vom Input Stream
        try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;

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
     * @param username Benutzername
     * @param password Passwort
     */
    public void signUpPlayer(String username, String password) {
        try {
            sendData("POST", "{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }");
            System.out.println("Spieler " + username.toUpperCase() + " wurde registriert");
        } catch (Exception e) {
            System.out.println("Spieler " + username.toUpperCase() + " konnte nicht registriert werden: " + e);
        }
    }

    /**
     * Meldet einen registrierten Spieler mit
     * einem Benutzernamen und Passwort an.
     *
     * @param username Benutzername
     * @param password Passwort
     */
    public void logInPlayer(String username, String password) {
        try {
            sendData("POST", "{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }");
            System.out.println("Spieler " + username.toUpperCase() + " wurde angemeldet");
        } catch (Exception e) {
            System.out.println("Spieler " + username.toUpperCase() + " konnte nicht angemeldet werden: " + e);
        }
    }

    /**
     * Aktualisiert das Passwort
     * des angemeldeten Benutzers.
     *
     * @param username Benutzername
     * @param currentPassword Aktuelles Passwort
     * @param newPassword Neues Passwort
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
     * Schreibt den Benutzernamen des Spieler 1
     * und Spieler 2 in ein erstelltes Spiel
     * und kann den Spielstatus ändern.
     * Eine leere Zeichenkette überschreibt
     * den entsprechenden Benutzernamen nicht.
     *
     * @param gamesID ID des Spiels
     * @param playerOne Spielername des Initiators
     * @param playerTwo Spielername des Beitretenden
     * @param status Spielstatus
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
     * @param playerOne Spielername des Initiators
     * @param category Quizkategorie
     * @param rounds Quizrunden
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
     * Löscht das übergebene Spiel, indem Zeichenketten
     * mit dem Wert 'null' jeweils in die Spielernamen
     * geschrieben werden. Der Server interpretiert dies
     * als Löschinstruktion.
     *
     * @param gamesID ID des zu löschenden Spiels
     */
    public void deleteGame(int gamesID) {
        try {
            sendData("PUT", "{ \"gamesid\": \"" + gamesID + "\", \"playerone\": \"" + "null" + "\", \"playertwo\": \"" + "null" + "\", \"status\": \"" + "CLOSE" + "\" }");
            System.out.println("Spiel " + gamesID + " wurde gelöscht");
        } catch (Exception e) {
            System.out.println("Spiel " + gamesID + " konnte nicht gelöscht werden: " + e);
        }
    }

    /**
     * Sendet Daten einer Beantwortung an den Server.
     * Ob eine Antwort vom Spieler korrekt beantwortet
     * hat, wird vom Client geprüft und mittels Boolean
     * an den Server übertragen. Die Berechnung der
     * Punkte wird auf Grundlage der übergebenen Zeit
     * vom Server übernommen.
     *
     * @param gameID ID des Spiels
     * @param isPlayerOne Gibt der Initiator des Spiels eine Antwort?
     * @param answer Wurde die Frage korrekt beantwortet?
     * @param timer Für die Beantwortung einer Frage benötigte Zeit
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
     * Lädt enkodiertes Image an den Server
     * und speichert es im Spielermodell.
     *
     * @param imageString Enkodierte Zeichenkette als Repräsentation eines Images
     * @param playername Spielername
     */
    public void uploadThumbnail(String imageString, String playername) {
        try {
            sendData("POST", "{ \"file\": \"" + imageString + "\", \"playername\": \"" + playername + "\" }");
            System.out.println("Image wurde hochgeladen");
        } catch (Exception e) {
            System.out.println("Image konnte nicht hochgeladen werden: " + e);
        }
    }

    /**
     * Setzt die Spielhistorie des
     * angemeldeten Spielers zurück.
     */
    public void resetHistory() {
        try {
            sendData("POST", "{ \"token\": \"" + privateToken + "\" }");
            System.out.println("History wurde erfolgreich gelöscht");
        } catch (Exception e) {
            System.out.println("Historie konnte nicht gelöscht werden: " + e);
        }
    }

    /**
     * Setzt den Highscore des Spielers zurück.
     * Der Spieler wird von der API anhand des
     * Tokens ermittelt.
     */
    public void resetHighscore() {
        try {
            sendData("PUT", "{ \"playerHighscore\": \"0\", \"token\": \"" + privateToken + "\" }");
            System.out.println("Highscore des Spielers zurückgesetzt");
        } catch (Exception e) {
            System.out.println("Highscore konnte nicht zurückgesetzt werden: " + e);
        }
    }
}