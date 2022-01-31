package de.gruppeo.wise2122_java_client.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.gruppeo.wise2122_java_client.helper.Connection;
import de.gruppeo.wise2122_java_client.model.MHighscore;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public class PHighscore {
    List<MHighscore> highscores;

    public PHighscore(Connection connection) {
        try {
            // Erstellt eine Instanz von 'Object Mapper'
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // Konvertiert JSON-Zeichenkette in eine Liste mit Highscore-Objekten
            this.highscores = Arrays.asList(mapper.readValue(connection.getServerResponse(), MHighscore[].class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}