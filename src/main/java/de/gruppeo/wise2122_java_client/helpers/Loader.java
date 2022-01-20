package de.gruppeo.wise2122_java_client.helpers;

import de.gruppeo.wise2122_java_client.Start;
import de.gruppeo.wise2122_java_client.models.MConfig;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Loader {

    private Pane pane;
    private Scene scene;
    private File file;
    private String location;

    public Loader() {
        location = "fxml/";
    }

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
            URL fileURL = Start.class.getResource(location + filename + ".fxml");

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
            FXMLLoader loader = new FXMLLoader(Start.class.getResource(location + filename + ".fxml"));

            Parent root = loader.load();
            scene = new Scene(root);

        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return scene;
    }

    /**
     * Lädt das in der Datenbank gespeicherte
     * Profilbild und zeigt es auf der GUI an.
     * Wenn kein Profilbild gefunden wurde,
     * wird ein Standardbild geladen.
     *
     * @param thumbnail
     */
    public void loadThumbnail(Circle thumbnail, String playername) {
        try {
            // Erhält enkodiertes Image aus Datenbank
            Connection download = new Connection("/player/getthumbnailbyname?playername=" + playername);
            Converter.decodeImage(download.getServerResponse());
            thumbnail.setFill(new ImagePattern(SwingFXUtils.toFXImage(Converter.decodeImage(download.getServerResponse()), null)));
        } catch (Exception e) {
            try {
                thumbnail.setFill(new ImagePattern(loadDefaultImage()));
                System.out.println("Standardbild gesetzt");
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Gibt ein lokal gespeichertes
     * Standardbild zurück.
     *
     * @return lokal gespeichertes Image
     * @throws MalformedURLException
     */
    public Image loadDefaultImage() throws MalformedURLException {
        try {
            this.file = new File("src/main/resources/de/gruppeo/wise2122_java_client/images" + MConfig.getInstance().getDefaultPic());
        } catch (Exception e) {
            System.out.println("Bild konnte nicht geladen werden");
        }
        return new Image(file.toURI().toURL().toExternalForm(), false);
    }
}