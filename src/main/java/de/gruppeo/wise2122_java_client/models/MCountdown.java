package de.gruppeo.wise2122_java_client.models;

import lombok.Getter;

/**
 *
 */

@Getter
public class MCountdown {
    private double totalSeconds;
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
     * @param seconds
     */
    public void decreaseSeconds(double seconds) {
        this.currentSeconds -= seconds;
    }

    public void resetSeconds() {
        this.currentSeconds = totalSeconds;
    }
}