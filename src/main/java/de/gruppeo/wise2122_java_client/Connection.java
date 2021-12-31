package de.gruppeo.wise2122_java_client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connection {
    HttpURLConnection connection;
    private String privateToken;
    private String serverResponse;
    private String baseURL = "http://localhost:8080";

    /**
     * GET-Konstruktor
     * Initialisiert globale Variablen und
     * führt Methode zur Anfrage einer JSON-
     * Zeichenkette aus.
     *
     * @param directory
     * @param privateToken
     * @throws Exception
     */
    public Connection(String directory, String privateToken) throws Exception {
        this.privateToken = privateToken;
        connection = (HttpURLConnection) new URL(baseURL + directory).openConnection();
        getData();
    }

    /**
     * POST-Konstruktor
     * Initialisiert globale Variablen und
     * führt Methode zur Übermittlung von Daten
     * an den Server aus.
     *
     * @param directory
     * @throws Exception
     */
    public Connection(String directory) throws Exception {
        connection = (HttpURLConnection) new URL(baseURL + directory).openConnection();
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
     *
     * @param serverInput
     * @throws Exception
     */
    public void postData(String serverInput) throws Exception {
        connection.setRequestMethod("POST");
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
    public String getServerResponse() {
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
}