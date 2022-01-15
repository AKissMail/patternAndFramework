package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Connection;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class CSettingsResetScore {

    public void onMouseClicked_resetScore() throws Exception {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Möchtest du deinen Highscore wirklich zurücksetzen?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            System.out.println("Es wurde auf JA geklickt");

            // Löscht den Highscore des Spielers
            Connection con = new Connection("/player/setplayerhighscore");
            con.resetHighscore();
        }
    }
}