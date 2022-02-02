package de.gruppeo.wise2122_java_client.helper;

import de.gruppeo.wise2122_java_client.model.MConfig;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Configuration {
    JAXBContext jaxbContext;
    Marshaller marshaller;
    Unmarshaller unmarshaller;
    MConfig config;
    String filePath = "src/main/resources/de/gruppeo/wise2122_java_client/configs/config.xml";
    File file;

    /**
     * Initialisiert Objekte und legt
     * Pfad zur XML-Config fest.
     */
    public Configuration() {
        this.config = MConfig.getInstance();
        this.file = new File(filePath);

        try {
            jaxbContext = JAXBContext.newInstance(MConfig.class);
            marshaller = jaxbContext.createMarshaller();
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            System.out.println("Fehler bei Objekterzeugung: " + e);
        }
    }
}