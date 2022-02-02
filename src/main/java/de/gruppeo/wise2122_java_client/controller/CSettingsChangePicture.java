package de.gruppeo.wise2122_java_client.controller;

import de.gruppeo.wise2122_java_client.helper.Connection;
import de.gruppeo.wise2122_java_client.helper.Converter;
import de.gruppeo.wise2122_java_client.helper.Loader;
import de.gruppeo.wise2122_java_client.model.MConfig;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class CSettingsChangePicture implements Initializable {

    private final Loader loader;

    @FXML private Circle circle_settings_picture;

    public CSettingsChangePicture() {
        loader = new Loader();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Lädt Profilbild
        loader.loadThumbnail(circle_settings_picture, MConfig.getInstance().getUsername());
    }

    /**
     * Zeigt Maske zur Auswahl
     * eines neuen Profilbildes an.
     *
     * @param event Mouseclick
     * @throws MalformedURLException Fehlerhafter Pfad
     */
    public void onAction_uploadPicture(ActionEvent event) throws Exception {
        String playername = MConfig.getInstance().getUsername();

        // Stage wird für den FileChooser benötigt
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // Initialisierung des FileChoosers zur Auswahl eines Dateipfads
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wähle ein Profilbild aus");
        File file = fileChooser.showOpenDialog(stage);

        // Speichert enkodiertes Image in Datenbank
        Connection upload = new Connection("/player/uploadthumbnailstr");
        upload.uploadThumbnail(Converter.encodeImage(file.getPath()), playername);

        // Erhält enkodiertes Image aus Datenbank
        Connection download = new Connection("/player/getthumbnailbyname?playername=" + playername);
        Converter.decodeImage(download.getServerResponse());

        // Setzt Image in Kreis ein
        circle_settings_picture.setFill(new ImagePattern(SwingFXUtils.toFXImage(Converter.decodeImage(download.getServerResponse()), null)));
    }
}