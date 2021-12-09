package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.Validation;
import de.gruppeo.wise2122_java_client.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MPlayer;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class CSignUp extends Validation {

    private ViewLoader loader = new ViewLoader();

    @FXML
    private BorderPane mainPane;

    @FXML
    private TextField textField_signUp_username;

    @FXML
    private TextField textField_signUp_password1;

    @FXML
    private TextField textField_signUp_password2;

    /**
     * Klick auf "Registrieren"-Button
     * speichert Daten in Datenbank.
     */
    public void onMouseClicked_signUp() {
        String username = textField_signUp_username.getText();
        String password1 = textField_signUp_password1.getText();
        String password2 = textField_signUp_password2.getText();

        /**
         * Prüft, ob Benutzername den Richtlinien entspricht
         * Prüft, ob Passwörter den Richtlinien entsprechen
         * Prüft, ob Passwörter übereinstimmen
         */

            if(!password1.equals(password2)){
                errorprint("Die Passwörter stimmmen nicht überein");
            }else if(!isValidUsername(username)){
                errorprint("Der Nutzername enthält unzulässige zeichen");
            }else if (!isValidPassword(password1)){
                errorprint("Das erste Passwort enthält unzulässige zeichen");
            }else if (!isValidPassword(password2)){
                errorprint("Das zweite Passwort enthält unzulässige zeichen");
            }else {
                //
            MPlayer player = new MPlayer(username);

            // Prüfen, ob Benutzername in DB enthalten ist
            if (isPlayerNotRegistered(player)) {
                // Benutzer in DB speichern
                // Anmeldung durchführen
                Stage stage = (Stage) mainPane.getScene().getWindow();
                stage.setScene(loader.getScene("main"));
                stage.show();
            } else {
                // Fehlermeldung, die auf redundanten Benutzernamen hinweist
                System.out.println("Player bereits vergeben");
            }
        }
    }

    /**
     * Klick auf "Zurück"-Button
     * navigiert auf die logIn-Maske.
     */
    public void onMouseClicked_back() {
        Pane pane = loader.getPane("logIn");
        mainPane.setRight(pane);
    }

    /**
     * Diese ist die Fehlernachricht, sie infomiert den Nutzer*innen über eine falsche eingaben
     * @param error Das ist die Fehlernachricht.
     */
    public void errorprint(String error){
        System.out.println(error);
        Alert alert = new Alert(Alert.AlertType.WARNING, error, ButtonType.OK);
        alert.showAndWait();
    }
}