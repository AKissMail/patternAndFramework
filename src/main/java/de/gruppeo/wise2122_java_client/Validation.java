package de.gruppeo.wise2122_java_client;

import de.gruppeo.wise2122_java_client.models.MPlayer;

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
     * Validiert das übergebene Passwort
     * und wendet dabei das definierte
     * Muster für sichere Passwörter an.
     *
     * @param password
     * @return boolean
     */
    protected boolean isValidPassword(final String password) {
        Matcher matcher = patternPassword.matcher(password);
        return matcher.matches();
    }

    /**
     * Baut Datenbankverbindung auf,
     * übergibt das Player-Objekt und
     * prüft, ob Benutzername des Players
     * bereits vergeben ist. Wenn nicht,
     * wird true zurückgegeben.
     *
     * @param player
     * @return
     */
    protected boolean isPlayerValid(MPlayer player) {

        // Logik muss implementiert werden ...

        return true;
    }
}