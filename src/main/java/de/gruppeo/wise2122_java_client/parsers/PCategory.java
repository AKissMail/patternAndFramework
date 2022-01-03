package de.gruppeo.wise2122_java_client.parsers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.models.MCategory;
import java.util.Arrays;
import java.util.List;

public class PCategory {
    List<MCategory> categories;

    public PCategory(Connection connection) {
        try {
            // Erstellt eine Instanz von 'Object Mapper'
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // Konvertiert JSON-Zeichenkette in eine Liste mit Kategorien
            this.categories = Arrays.asList(mapper.readValue(connection.getServerResponse(), MCategory[].class));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<MCategory> getList() {
        return categories;
    }
}