package de.gruppeo.wise2122_java_client.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.gruppeo.wise2122_java_client.helper.Connection;
import de.gruppeo.wise2122_java_client.model.MGame;
import lombok.Getter;

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