package de.gruppeo.wise2122_java_client.helpers;

import de.gruppeo.wise2122_java_client.models.MConfiguration;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

public class Configuration {
    JAXBContext jaxbContext;
    Marshaller marshaller;
    Unmarshaller unmarshaller;
    MConfiguration config;
    String filePath = "src/main/resources/de/gruppeo/wise2122_java_client/configurations/config.xml";
    File file;

    /**
     * Initialisiert Objekte und legt
     * Pfad zur XML-Config fest.
     */
    public Configuration() {
        this.config = MConfiguration.getInstance();
        this.file = new File(filePath);

        try {
            jaxbContext = JAXBContext.newInstance(MConfiguration.class);
            marshaller = jaxbContext.createMarshaller();
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            System.out.println("Fehler bei Objekterzeugung: " + e);
        }
    }

    /**
     * Schreibt Modelldaten in die XML-Config.
     */
    public void writeConfiguration() {
        try {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(config, file);
        } catch (JAXBException ew) {
            System.out.println("Fehler beim Schreiben: " + ew);
        }
    }

    /**
     * Liest XML-Config und gibt Objekt zurück.
     *
     * @return MConfig-Objekt
     */
    public MConfiguration readConfiguration() {
        try {
            config = (MConfiguration) unmarshaller.unmarshal(file);
        } catch (JAXBException er) {
            //System.out.println("Fehler beim Lesen: " + e);
        } catch (IllegalArgumentException e) {
            File file = new File(filePath);
            try {
                if (file.createNewFile()) {
                    System.out.println("XML-Datei erzeugt: " + file.getName());
                }
            } catch (IOException io) {
                System.out.println(io);
            }
        }
        return config;
    }

    /**
     * Löscht die XML-Config.
     */
    public void deleteFile() {
        this.file.delete();
        System.out.println("Datei wurde erfolgreich gelöscht");
    }
}