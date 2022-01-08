package de.gruppeo.wise2122_java_client.models;

import lombok.Getter;

/**
 *
 */

@Getter
public class MTimer {
    public double defaultSeconds = 10.0;
    private double seconds;

    /**
     * Standardkonstruktor mit einem
     * Startwert von insgesamt 10 Sekunden.
     */
    public MTimer() {
        this.seconds = this.defaultSeconds;
    }

    /**
     * Verringert die maximale Sekundenzahl
     * des Timers um die übergebenen Sekunden.
     *
     * @param seconds
     */
    public void decreaseSeconds(double seconds) {
        this.seconds -= seconds;
    }
}