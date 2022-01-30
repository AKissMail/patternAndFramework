package de.gruppeo.wise2122_java_client.helpers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    /**
     * Benutzername muss mindestens eine Länge von 5 Zeichen haben
     * Benutzername darf maximal eine Länge von 15 Zeichen haben
     */
    private static final String PATTERN_USERNAME = ".{5,15}$";
    private static final Pattern patternUsername = Pattern.compile(PATTERN_USERNAME);

    /**
     * Passwort muss mindestens eine Zahl enthalten: [0-9]
     * Passwort muss mindestens einen Kleinbuchstaben enthalten: [a-z]
     * Passwort muss mindestens einen Großbuchstaben enthalten: [A-Z]
     * Passwort muss mindestens ein Sonderzeichen enthalten: ! @ # & ()
     * Passwort muss mindestens eine Länge von 8 Zeichen haben
     * Passwort darf maximal eine Länge von 20 Zeichen haben
     */
    private static final String PATTERN_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    private static final Pattern patternPassword = Pattern.compile(PATTERN_PASSWORD);

    /**
     * Validiert den übergebenen Benutzernamen
     * und wendet dabei das definierte Muster
     * für gültige Benutzernamen an.
     *
     * @param username Benutername
     * @return boolean Ist der Benutzername valide?
     */
    public boolean isValidUsername(final String username) {
        Matcher matcher = patternUsername.matcher(username);
        return matcher.matches();
    }

    /**
     * Validiert das übergebene Passwort und wendet dabei
     * das definierte Muster für sichere Passwörter an.
     *
     * @param password Passwort
     * @return boolean Ist das Passwort valide?
     */
    public boolean isValidPassword(final String password) {
        Matcher matcher = patternPassword.matcher(password);
        return matcher.matches();
    }

    /**
     * Aktiviert einen Button, wenn alle
     * drei Felder vollständig und korrekt
     * ausgefüllt wurden.
     *
     * @param label Etikett eines Eingabefelds
     * @param labelColor Farbe des Etiketts
     * @param list Liste der zu prüfenden Eingabefelder
     * @param button Zu aktivierender/deaktivierender Button
     */
    public void checkInputValidation(Label label, Color labelColor, ArrayList<Boolean> list, Button button) {
        int checkSum = 0;

        // Färbt das Label in rot/schwarz
        label.setTextFill(labelColor);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)) {
                checkSum += 1;
            }
        }

        if (checkSum == list.size()) {
            button.setDisable(false);
        } else {
            button.setDisable(true);
        }
    }

    /**
     * Aktiviert oder deaktiviert
     * die übergebenen Buttons.
     *
     * @param buttons Buttons
     * @param disable Sollen Buttons deaktiviert werden?
     */
    public void disableButtons(Button[] buttons, boolean disable) {
        for (Button button : buttons) {
            button.setDisable(disable);
        }
    }
}