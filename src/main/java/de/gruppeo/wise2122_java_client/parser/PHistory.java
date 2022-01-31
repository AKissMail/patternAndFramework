package de.gruppeo.wise2122_java_client.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.gruppeo.wise2122_java_client.helper.Connection;
import java.util.Arrays;
import java.util.List;

import de.gruppeo.wise2122_java_client.model.MHistory;
import lombok.Getter;

@Getter
public class PHistory {
    List<MHistory> histories;

    public PHistory(Connection connection) {
        try {
            // Erstellt eine Instanz von 'Object Mapper'
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // Konvertiert JSON-Zeichenkette in eine Liste mit Highscore-Objekten
            this.histories = Arrays.asList(mapper.readValue(connection.getServerResponse(), MHistory[].class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}