package de.gruppeo.wise2122_java_client.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.gruppeo.wise2122_java_client.helper.Connection;
import de.gruppeo.wise2122_java_client.model.MCategory;
import lombok.Getter;
import java.util.Arrays;
import java.util.List;

@Getter
public class PCategory {
    List<MCategory> categories;

    public PCategory(Connection connection) {
        try {
            // Erstellt eine Instanz von 'Object Mapper'
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            // Konvertiert JSON-Zeichenkette in eine Liste mit Kategorie-Objekten
            this.categories = Arrays.asList(mapper.readValue(connection.getServerResponse(), MCategory[].class));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}