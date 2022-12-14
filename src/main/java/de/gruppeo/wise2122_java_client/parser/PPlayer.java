package de.gruppeo.wise2122_java_client.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.gruppeo.wise2122_java_client.helper.Connection;
import de.gruppeo.wise2122_java_client.model.MPlayer;
import lombok.Getter;
import java.util.Arrays;
import java.util.List;

@Getter
public class PPlayer {
    List<MPlayer> players;

    public PPlayer(Connection connection) {
        try {
            // Erstellt eine Instanz von 'Object Mapper'
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // Konvertiert JSON-Zeichenkette in eine Liste mit Spieler-Objekten
            this.players = Arrays.asList(mapper.readValue(connection.getServerResponse(), MPlayer[].class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}