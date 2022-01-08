package de.gruppeo.wise2122_java_client.models;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modell wird zum Speichern und Lesen der
 * XML-Config benötigt. Es ermöglicht den Zugriff
 * von außen auf alle im XML gespeicherten Daten.
 */
@XmlRootElement
public class MConfiguration {
    private static final MConfiguration OBJ = new MConfiguration();

    private String privateToken;
    private String baseURL;
    private Object category;
    private Object rounds;
    private Object player;

    /**
     * Um sicherzustellen, dass nur eine Instanz des Config-Modells
     * erzeugt werden kann, wird das Singleton-Pattern angewendet.
     * Es soll verhindern, dass Werte in der XML-Config überschrieben
     * werden, wenn aus unterschiedlichen Klassen schreibend zugegriffen
     * wird. Der Konstruktor ist privat und kann von außen nicht angesprochen
     * werden.
     */
    private MConfiguration() {
        this.baseURL = "http://localhost:8080";
    }

    /**
     * Bestandteil des Singleton-Patterns. Ermöglicht
     * Zugriff auf das eine statische MConfig-Objekt
     * aus anderen Klassen.
     *
     * @return MConfig-Objekt
     */
    public static MConfiguration getInstance() {
        return OBJ;
    }

    public String getPrivateToken() {
        return privateToken;
    }

    public void setPrivateToken(String privateToken) {
        this.privateToken = privateToken;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category;
    }

    public Object getRounds() {
        return rounds;
    }

    public void setRounds(Object numberQuestions) {
        this.rounds = numberQuestions;
    }

    public Object getPlayer() {
        return player;
    }

    public void setPlayer(Object player) {
        this.player = player;
    }
}