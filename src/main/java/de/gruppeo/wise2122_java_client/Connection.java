package de.gruppeo.wise2122_java_client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Connection {
    private URL serverURL;
    private String privateToken;
    private String requestMethod;
    private String serverResponse;

    /**
     * Initialisiert globale Variablen und
     * führt Methode zur Anfrage einer JSON-
     * Zeichenkette aus.
     *
     * @param directory
     * @param requestMethod
     * @param privateToken
     * @throws MalformedURLException
     */
    public Connection(String directory, String requestMethod, String privateToken) throws Exception {
        this.serverURL = new URL("http://localhost:8080" + directory);
        this.requestMethod = requestMethod;
        this.privateToken = privateToken;

        //requestServer();
        readFile(); // TEST
    }

    /**
     * Fragt den Server nach einer JSON-Datei
     * und speichert das Ergebnis in einer
     * globalen Variable, die von außen über
     * eine GETTER-Methode gelesen werden kann.
     *
     * @throws Exception
     */
    private void requestServer() throws Exception {
        HttpURLConnection conn = (HttpURLConnection) serverURL.openConnection();

        conn.setRequestProperty("Authorization", "Bearer " + privateToken);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestMethod(requestMethod);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String output;

        StringBuffer response = new StringBuffer();
        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        System.out.println("Server: " + response.toString());
        serverResponse = response.toString();
    }

    public String getServerResponse() {
        return serverResponse;
    }

    /**
     * TESTMETHODE
     * Statt das JSON über die Server-API zu laden,
     * wird auf eine lokale JSON-Datei zurückgegriffen.
     *
     * @return Zeichenkette
     * @throws Exception
     */
    public void readFile() throws Exception {
        serverResponse = new String(Files.readAllBytes(Paths.get("src/main/resources/de/gruppeo/wise2122_java_client/test.json")));
    }
}