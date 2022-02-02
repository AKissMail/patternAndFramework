package de.gruppeo.wise2122_java_client.model;

import lombok.Getter;

/**
 * Modell eines Countdowns mit allen
 * Attributen, die den Betrieb des
 * Runden-Timers während des Spiels
 * gewährleisten. Getter-Methoden
 * werden mittels Lombok automatisch
 * generiert.
 */

@Getter
public class MCountdown {
    
    private final double totalSeconds;
    private double currentSeconds;

    /**
     * Standardkonstruktor mit einem
     * Startwert von insgesamt 10 Sekunden.
     */
    public MCountdown() {
        this.totalSeconds = MConfig.getInstance().getDefaultCountdown();
        this.currentSeconds = MConfig.getInstance().getDefaultCountdown();
    }

    /**
     * Verringert die maximale Sekundenzahl
     * des Timers um die übergebenen Sekunden.
     *
     * @param seconds Zu verringernde Sekunden
     */
    public void decreaseSeconds(double seconds) {
        this.currentSeconds -= seconds;
    }

    /**
     * Setzt den aktuellen Timer-Stand
     * auf den Standardwert zurück.
     */
    public void resetSeconds() {
        this.currentSeconds = totalSeconds;
    }
}