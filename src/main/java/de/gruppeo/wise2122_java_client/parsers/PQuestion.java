package de.gruppeo.wise2122_java_client.parsers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.models.MQuestion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class PQuestion {
    List<MQuestion> questions;

    /**
     * NUR ZU TESTZWECKEN!
     * Lokale JSON-Datei wird geparst und
     * mit dem MQuestion-Modell gemappt.
     *
     * @throws IOException
     */
    public PQuestion() throws IOException {
        String file = "src/main/resources/de/gruppeo/wise2122_java_client/questions.json";
        String json = new String(Files.readAllBytes(Paths.get(file)));

        try {
            // Erstellt eine Instanz von 'Object Mapper'
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // Konvertiert JSON-Zeichenkette in eine Liste mit Fragen
            this.questions = Arrays.asList(mapper.readValue(json, MQuestion[].class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

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