package de.gruppeo.wise2122_java_client.models;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

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

    private String jwtSecret;
    private String privateToken;
    private String username;
    private String baseURL;
    private Object category;
    private Object rounds;
    private Object opponent;

    private int indexRounds;

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