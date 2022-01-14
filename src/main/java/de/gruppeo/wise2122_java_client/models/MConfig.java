package de.gruppeo.wise2122_java_client.models;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modell wird zum Speichern und Lesen wichtiger
 * Konfigurationsdaten benötigt. Es ermöglicht den Zugriff
 * von außen auf alle im Objekt gespeicherten Daten. Es
 * kann nur eine Instanz vom Objekt erzeugt werden.
 */

@Getter
@Setter
@XmlRootElement
public class MConfig {
    private static final MConfig OBJ = new MConfig();

    private String jwtSecret;       // Tokenschlüssel
    private String privateToken;    // Authentifizierungsschlüssel
    private String username;        // Benutername
    private String baseURL;         // Standard Host-URL
    private Object category;        // Ausgewählte Kategorie
    private Object opponent;        // Ausgewählter Gegner
    private Object rounds;          // Objekt der ausgewählten Rundenzahl
    private int indexRounds;        // Index Standard-Rundenzahl
    private int joinedGameID;       // ID des beigetretenen Spiels
    private int registeredGameID;   // ID des registrierten Spiels
    private int refreshrate;        // Aktualisierungsrate in Millisekunden

    /**
     * Um sicherzustellen, dass nur eine Instanz des Config-Modells
     * erzeugt werden kann, wird das Singleton-Pattern angewendet.
     * Es soll verhindern, dass Werte in der XML-Config überschrieben
     * werden, wenn aus unterschiedlichen Klassen schreibend zugegriffen
     * wird. Der Konstruktor ist privat und kann von außen nicht angesprochen
     * werden.
     */
    private MConfig() {
        this.baseURL = "http://localhost:8080";
        this.jwtSecret = "IchbineinfurchtbargeheimesGeheimnisvonThomas";
        this.refreshrate = 2000; // 2 Sekunden
        this.indexRounds = 0;
    }

    /**
     * Bestandteil des Singleton-Patterns. Ermöglicht
     * Zugriff auf das eine statische MConfig-Objekt
     * aus anderen Klassen.
     *
     * @return MConfig-Objekt
     */
    public static MConfig getInstance() {
        return OBJ;
    }
}