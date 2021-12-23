package de.gruppeo.wise2122_java_client.models;

public class MPoint {
    public int value;
    private double currentScore;

    public MPoint() {
        value = 10;
    }

    /**
     * Gibt den aktuellen Punktestand zurück.
     *
     * @return aktueller Punktestand
     */
    public double getCurrentScore() {
        return currentScore;
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
