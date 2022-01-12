package de.gruppeo.wise2122_java_client.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class CSettingsResetScore {

    public void onMouseClicked_resetScore() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Möchtest du deinen Highscore wirklich zurücksetzen?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            System.out.println("Es wurde auf JA geklickt");

            // @TODO Code, der den Highscore zurücksetzt implementieren
        }
    }
}