package de.gruppeo.wise2122_java_client.controller;

import de.gruppeo.wise2122_java_client.helper.*;
import de.gruppeo.wise2122_java_client.model.MConfig;
import de.gruppeo.wise2122_java_server.security.JwtTokenProvider;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;

public class CLogIn implements Initializable {
    private final Loader loader;
    private final JwtTokenProvider tokenProvider;

    @FXML private BorderPane mainPane;
    @FXML private TextField textField_logIn_username;
    @FXML private TextField textField_logIn_password;
    @FXML private Button button_logIn_logIn;

    public CLogIn() {
        loader = new Loader();
        tokenProvider = new JwtTokenProvider();
    }

    /**
     * Ermöglicht, dass sich der User auch
     * mit einem ENTER-Schlag anmelden kann.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        textField_logIn_password.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                logIn();
            }
        });
    }

    /**
     * Klick auf "Anmelden"-Button
     * leitet den Anmeldevorgang ein.
     */
    public void onMouseClicked_logIn() {
        logIn();
    }

    /**
     * Klick auf "Registrieren"-Button
     * navigiert auf die signUp-Maske.
     */
    public void onMouseClicked_signUp() {
        Pane pane = loader.getPane("signUp");
        mainPane.setRight(pane);
    }

    /**
     * Führt Methode zum Aktivieren oder
     * Deaktivieren des LogIn-Buttons aus.
     * Wird bei jedem Tastendruck getriggert.
     */
    public void onKeyTyped_username() {
        enableLogInButton();
    }

    /**
     * Führt Methode zum Aktivieren oder
     * Deaktivieren des LogIn-Buttons aus.
     * Wird bei jedem Tastendruck getriggert.
     */
    public void onKeyTyped_password() {
        enableLogInButton();
    }

    /**
     * Führt Methode zum Aktivieren oder
     * Deaktivieren des LogIn-Buttons aus.
     * Wird bei jedem Tastendruck getriggert.
     */
    private void enableLogInButton() {
        int usernameLength = textField_logIn_username.getText().length();
        int passwordLength = textField_logIn_password.getText().length();

        if (usernameLength > 0 && passwordLength > 0) {
            button_logIn_logIn.setDisable(false);
        } else {
            button_logIn_logIn.setDisable(true);
        }
    }

    /**
     * Etabliert neue Verbindung und sendet
     * Anmeldedaten an Server. Bei Erfolg wird
     * ein privater Token empfangen und im Config-Objekt
     * zur Laufzeit des Quizes gespeichert und bei
     * nachfolgenden Serververbindungen, die eine
     * Authentifizierung voraussetzen, bereitgestellt,
     */
    private void logIn() {
        String username = textField_logIn_username.getText();
        String password = textField_logIn_password.getText();

        try {
            // Meldet einen Spieler an
            Connection logIn = new Connection("/auth/login");
            logIn.logInPlayer(username, password);

            // Speichert Token und Usernamen in Config-Objekt
            MConfig.getInstance().setPrivateToken(logIn.getServerResponse());
            MConfig.getInstance().setUsername(tokenProvider.getUsernameFromToken(logIn.getServerResponse(), MConfig.getInstance().getJwtSecret()));

            // Lädt Hauptmenü
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(loader.getScene("main"));
            stage.show();
        } catch (Exception e) {
            // Zeigt Warnmeldung an
            Alert alert = new Alert(Alert.AlertType.WARNING, "Die eingegebenen Anmeldedaten sind nicht korrekt. Bitte versuche es erneut.", ButtonType.OK);
            alert.showAndWait();

            // Setzt Textfelder zurück
            textField_logIn_username.clear();
            textField_logIn_password.clear();

            // Deaktiviert LogIn-Button
            button_logIn_logIn.setDisable(true);
        }
    }
}