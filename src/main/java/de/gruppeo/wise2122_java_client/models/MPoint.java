package de.gruppeo.wise2122_java_client.models;

import lombok.Getter;

/**
 *
 */

@Getter
public class MPoint {
    public int value;
    private double currentScore;

    public MPoint() {
        value = 10;
    }

    public MPoint(int value, double currentScore) {
        this.value = value;
        this.currentScore = currentScore;
    }

    /**
     * Berechnet, auf Grundlage des übergebenen
     * Timer-Standes, die gut geschriebenen Punkte.
     *
     * @param timerValue
     */
    public void setCurrentScore(double timerValue) {
        currentScore = value * timerValue;
    }
}