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
    public void getData() throws Exception {
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
    public void sendData(String requestMethod, String serverInput) throws Exception {
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


    public void updateGame(int gamesID, String username, String status) {
        try {
            sendData("PUT", "{ \"gamesid\": \"" + gamesID + "\", \"username\": \"" + username + "\", \"status\": \"" + status + "\" }");
            getServerResponse();
        } catch (Exception e) {
            System.out.println("Spiel konnte nicht aktualisiert werden: " + e);
        }
    }

    public void createGame(String reguest) {
        try {
            sendData("POST", reguest);
            System.out.println("Neues Spiel registriert");
        } catch (Exception e) {
            System.out.println("Spiel konnte nicht erstellt werden: " + e);
        }
    }
}