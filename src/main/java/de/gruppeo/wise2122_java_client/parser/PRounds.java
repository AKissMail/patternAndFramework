package de.gruppeo.wise2122_java_client.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.gruppeo.wise2122_java_client.helper.Connection;
import de.gruppeo.wise2122_java_client.model.MRounds;
import lombok.Getter;
import java.util.Arrays;
import java.util.List;

@Getter
public class PRounds {
    List<MRounds> rounds;

    public PRounds(Connection connection) {
        try {
            // Erstellt eine Instanz von 'Object Mapper'
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // Konvertiert JSON-Zeichenkette in eine Liste mit Runden-Objekten
            this.rounds = Arrays.asList(mapper.readValue(connection.getServerResponse(), MRounds[].class));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}