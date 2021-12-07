package de.gruppeo.wise2122_java_client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.prefs.Preferences;

public class ViewLoader {

    // Objekt zum Speichern und Laden von lokalen Einstellungen
    Preferences pref = java.util.prefs.Preferences.userNodeForPackage(java.util.prefs.Preferences.class);

    private Pane pane;
    private Scene scene;
    private File file;

    /**
     * Gibt die Pane der übergebenen
     * FXML-Datei zurück. Kann zur
     * Ersetzung eines Maskenbereichs
     * verwendet werden.
     *
     * @param filename
     * @return pane
     */
    public Pane getPane(String filename) {
        try {
            URL fileURL = Start.class.getResource(filename + ".fxml");

            if (fileURL == null) {
                throw new java.io.FileNotFoundException("FXML File can't be found.");
            }
            pane = new FXMLLoader().load(fileURL);

        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return pane;
    }

    /**
     * Gibt die Scene der übergebenen
     * FXML-Datei zurück. Kann zur
     * Ersetzung einer ganzen Maske
     * verwendet werden.
     *
     * @param filename
     * @return scene
     */
    public Scene getScene(String filename) {
        try {
            FXMLLoader loader = new FXMLLoader(Start.class.getResource(filename + ".fxml"));

            Parent root = loader.load();
            scene = new Scene(root);

        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return scene;
    }

    /**
     * Gibt das dem übergebenen Schlüssel
     * zugeordneten Image zurück.
     *
     * @param key
     * @return lokal gespeichertes Image
     * @throws MalformedURLException
     */
    public Image loadImage(String key) throws MalformedURLException {
        Image image;

        this.file = new File(pref.get(key, "src/main/resources/de/gruppeo/wise2122_java_client/images/picture.png"));
        return image = new Image(file.toURI().toURL().toExternalForm(), false);
    }

    /**
     * Speichert Zeichenkette lokal in einer XML-Datei.
     * Zeichenkette wird mittels Schlüssel aufgerufen.
     *
     * @param key
     * @param data
     */
    public void saveData(String key, String data) {
        pref.put(key.toUpperCase(), data);
        System.out.println(data + " wurde erfolgreich gespeichert");
    }
}