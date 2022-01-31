package de.gruppeo.wise2122_java_client.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.gruppeo.wise2122_java_client.helper.Connection;
import de.gruppeo.wise2122_java_client.model.MQuestion;
import lombok.Getter;
import java.util.Arrays;
import java.util.List;

@Getter
public class PQuestion {
    List<MQuestion> questions;

    public PQuestion(Connection connection) {
        try {
            // Erstellt eine Instanz von 'Object Mapper'
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // Konvertiert JSON-Zeichenkette in eine Liste mit Frage-Objekten
            this.questions = Arrays.asList(mapper.readValue(connection.getServerResponse(), MQuestion[].class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}