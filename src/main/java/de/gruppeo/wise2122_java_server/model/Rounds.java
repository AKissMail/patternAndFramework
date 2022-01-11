package de.gruppeo.wise2122_java_server.model;

import java.util.HashMap;
import java.util.Map;

public enum Rounds {
    EASY(10),
    MEDIUM(15),
    HARD(20);

    /**
     * Werte für die Runden
     */
    public final int Value;

    Rounds(int value) {
        Value = value;
    }

    // Mapping der Bezeichnung zur Rundenanzahl
    private static final Map<Integer, Rounds> _map = new HashMap<>();

    static {
        for (Rounds rounds : Rounds.values())
            _map.put(rounds.Value, rounds);
    }

    /**
     * Erhalte die Bezeichnung vom value
     *
     * @param value Rundenanzahl
     * @return Rounds
     */
    public static Rounds from(int value) {
        return _map.get(value);
    }
}