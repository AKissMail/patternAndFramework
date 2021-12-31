package de.gruppeo.wise2122_java_client.helpers;

import java.io.*;
import java.util.Properties;

public class Configuration {
    File configFile;
    Properties props;

    /**
     * Initialisiert die globalen Klassen-Objekte.
     */
    public Configuration() {
        this.configFile = new File("src/main/resources/de/gruppeo/wise2122_java_client/configurations/config.properties");
        this.props = new Properties();
    }

    /**
     * Liest den mit dem übergebenen Schlüssel
     * verknüpften Wert aus der Konfigurationsdatei
     * und gibt ihn zurück.
     *
     * @param key
     * @return Schlüsselwert
     */
    public String readProperty(String key) {
        String property = null;
        try {
            FileReader reader = null;

            try {
                reader = new FileReader(configFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            props.load(reader);
            property = props.getProperty(key);

            reader.close();
        } catch (FileNotFoundException fnfe) {
            // file does not exist
        } catch (IOException ioe) {
            // I/O error
        }
        return property;
    }

    /**
     * Schreibt den übergebenen Wert in die
     * Konfigurationsdatei. Der Wert ist über
     * den ebenfalls übergebenen Schlüssel
     * ansprechbar.
     *
     * @param key
     * @param value
     */
    public void writeProperty(String key, String value) {
        try {
            props.setProperty(key, value);
            FileWriter writer = new FileWriter(configFile);
            props.store(writer, "Server Properties");
            writer.close();
        } catch (FileNotFoundException fnfe) {
            // file does not exist
            System.out.println("Datei existiert nicht!");
        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }
}
