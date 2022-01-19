package de.gruppeo.wise2122_java_client.helpers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

public class Converter {

    /**
     * Konvertiert übergebenes Image in eine
     * Zeichenkette, sodass es in der Datenbank
     * gespeichert werden kann.
     *
     * @param imagePath
     * @return Zeichenkette
     */
    public static String encodeImage(String imagePath) {
        String imageString = null;
        try {
            FileInputStream imageStream = new FileInputStream(imagePath);
            byte[] data = imageStream.readAllBytes();
            imageString = Base64.getEncoder().encodeToString(data);
        } catch (FileNotFoundException file) {
            System.out.println("Datei nicht gefunden: " + file);
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen/Schreiben: " + e);
        }
        return imageString;
    }

    /**
     * Konvertriert übergebene Zeichenkette
     * in eine Bilddatei, sodass es auf der
     * GUI angezeigt werden kann.
     *
     * @param imageString
     */
    public static BufferedImage decodeImage(String imageString) {
        BufferedImage image = null;
        try {
            byte[] imageByte = Base64.getDecoder().decode(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {

        }
        return image;
    }
}