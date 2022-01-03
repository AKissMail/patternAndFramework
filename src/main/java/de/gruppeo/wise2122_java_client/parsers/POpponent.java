package de.gruppeo.wise2122_java_client.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.models.MPlayer;
import java.util.Arrays;
import java.util.List;

public class POpponent {
    List<MPlayer> opponents;

    public POpponent(Connection connection) {
        try {
            // Erstellt eine Instanz von 'Object Mapper'
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // Konvertiert JSON-Zeichenkette in eine Liste mit Spielern
            this.opponents = Arrays.asList(mapper.readValue(connection.getServerResponse(), MPlayer[].class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<MPlayer> getList() {
        return opponents;
    }
}