package de.gruppeo.wise2122_java_client.parsers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.models.MQuestion;
import java.util.Arrays;
import java.util.List;

public class PQuestion {
    List<MQuestion> questions;

    public PQuestion(Connection connection) {
        try {
            // Erstellt eine Instanz von 'Object Mapper'
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // Konvertiert JSON-Zeichenkette in eine Liste mit Fragen
            this.questions = Arrays.asList(mapper.readValue(connection.getServerResponse(), MQuestion[].class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<MQuestion> getList() {
        return questions;
    }
}