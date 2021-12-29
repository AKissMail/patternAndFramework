package de.gruppeo.wise2122_java_client;

import de.gruppeo.wise2122_java_client.models.MPlayer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Validation {

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
     * Passwort muss mindestens ein Sonderzeichen enthalten: ! @ # & ( )
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
     * @param username
     * @return boolean
     */
    protected boolean isValidUsername(final String username) {
        Matcher matcher = patternUsername.matcher(username);
        return matcher.matches();
    }

    /**
     * Validiert das übergebene Passwort und wendet dabei
     * das definierte Muster für sichere Passwörter an.
     *
     * @param password
     * @return boolean
     */
    protected boolean isValidPassword(final String password) {
        Matcher matcher = patternPassword.matcher(password);
        return matcher.matches();
    }

    /**
     * @TODO Datenbank-API
     * Prüft, ob die Eingabe mit dem in der DB
     * gespeicherten Passwort übereinstimmt.
     *
     * @param password
     * @return true oder false
     */
    protected boolean isValidCurrentPassword(final String password) {
        // Logik muss implementiert werden ...
        return true;
    }

    /**
     * @TODO Datenbank-API
     * Baut Datenbankverbindung auf, übergibt das Player-Objekt und
     * prüft, ob Benutzername des Players bereits vergeben ist. Wenn
     * nicht, wird true zurückgegeben.
     *
     * @param player
     * @return
     */
    protected boolean isUsernameAvailable(MPlayer player) {
        // Logik muss implementiert werden ...
        return true;
    }

    /**
     * @TODO Datenbank-API
     * Baut Datenbankverbindng auf, übergibt Username und Password
     * und prüft, ob die Benutzereingaben valide sind. Wenn gültige
     * Daten gefunden wurden, wird true zurückgegeben.
     *
     * @param username
     * @param password
     * @return
     */
    protected boolean isLoginDataValid(String username, String password, Connection connection) throws Exception {

        connection.postData("{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }");
        System.out.println("Server: " + connection.getServerResponse());

        return true;
    }

    /**
     * Aktiviert einen Button, wenn alle
     * drei Felder vollständig und korrekt ausgefüllt
     * wurden.
     */
    protected void checkInputValidation(Label label, Color labelColor, ArrayList<Boolean> list, Button button) {
        int checkSum = 0;

        // Färbt das Label in rot/schwarz
        label.setTextFill(labelColor);
        //System.out.println("Huhu");

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == true) {
                checkSum += 1;
            }
        }

        if (checkSum == list.size()) {
            button.setDisable(false);
        } else {
            button.setDisable(true);
        }
    }
}