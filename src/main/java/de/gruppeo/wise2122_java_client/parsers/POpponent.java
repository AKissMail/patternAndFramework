package de.gruppeo.wise2122_java_client.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.gruppeo.wise2122_java_client.Connection;
import de.gruppeo.wise2122_java_client.models.MOpponent;
import java.util.Arrays;
import java.util.List;

public class POpponent {

    Connection connection;
    List<MOpponent> opponents;

    public POpponent(Connection connection) throws JsonProcessingException {
        this.connection = connection;
        parseJSON();
    }

    private void parseJSON() {
        try {
            // Erstellt eine Instanz von 'Object Mapper'
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // Konvertiert JSON-Zeichenkette in eine Liste mit 'Opponents'
            this.opponents = Arrays.asList(mapper.readValue(connection.getServerResponse(), MOpponent[].class));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<MOpponent> getList() {
        return opponents;
    }
}