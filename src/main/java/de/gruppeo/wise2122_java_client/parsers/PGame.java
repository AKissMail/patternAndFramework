package de.gruppeo.wise2122_java_client.parsers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.models.MGame;
import lombok.Getter;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Getter
public class PGame {
    List<MGame> games;

    public PGame(Connection connection) {
        try {
            // Erstellt eine Instanz von 'Object Mapper'
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            // Konvertiert JSON-Zeichenkette in eine Liste mit Spiel-Objekten
            this.games = Arrays.asList(mapper.readValue(connection.getServerResponse(), MGame[].class));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public PGame(String json) {
        try {
            // Erstellt eine Instanz von 'Object Mapper'
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            // Konvertiert JSON-Zeichenkette in eine Liste mit Spiel-Objekten
            this.games = Arrays.asList(mapper.readValue(json, MGame[].class));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}