package de.gruppeo.wise2122_java_client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import java.net.URL;

public class ViewLoader {

    private Pane pane;
    private Scene scene;

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
}