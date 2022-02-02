package de.gruppeo.wise2122_java_client.controller;

import de.gruppeo.wise2122_java_client.helper.Loader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class CSettings {

    private final Loader loader;

    @FXML private BorderPane mainPane;

    public CSettings() {
        loader = new Loader();
    }

    /**
     * Zeigt die Maske zur Änderung des Profilbildes an.
     */
    public void onMouseClicked_showPicturePane() {
        Pane pane = loader.getPane("settings_changePicture");
        mainPane.setRight(pane);
    }

    /**
     * Zeigt die Maske zur Änderung des Passwortes an.
     */
    public void onMouseClicked_showPasswordPane() {
        Pane pane = loader.getPane("settings_changePassword");
        mainPane.setRight(pane);
    }

    /**
     * Zeigt die Maske zum Zurücksetzen des Scores an.
     */
    public void onMouseClicked_showScorePane() {
        Pane pane = loader.getPane("settings_resetScore");
        mainPane.setRight(pane);
    }

    /**
     * Zeigt das Hauptmenü an.
     */
    public void onMouseClicked_back() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("main"));
        stage.show();
    }
}