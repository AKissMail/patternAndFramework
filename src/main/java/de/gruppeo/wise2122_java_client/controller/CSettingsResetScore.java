package de.gruppeo.wise2122_java_client.controller;

import de.gruppeo.wise2122_java_client.helper.Connection;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class CSettingsResetScore {

    public void onMouseClicked_resetScore() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Möchtest du deine Spieldaten wirklich zurücksetzen?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            // Setzt den Highscore des angemeldeten Spielers zurück
            Connection highscore = new Connection("/player/setplayerhighscore");
            highscore.resetHighscore();

            // Setzt die Spielhistorie des angemeldeten Spielers zurück
            Connection history = new Connection("/games/historydeletebytoken");
            history.resetHistory();
        }
    }
}