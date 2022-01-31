package de.gruppeo.wise2122_java_client.model;

import lombok.Getter;

/**
 * Modell einer Kategorie mit allen
 * Attributen, die zum JSON-Parsing
 * benötigt werden. Getter-Methoden
 * werden mittels Lombok automatisch
 * generiert.
 */

@Getter
public class MCategory {

    private int quizcategoryid;
    private String categoryname;

    /**
     * Leerer Konstruktor wird
     * beim JSON-Parsing benötigt.
     */
    public MCategory() {}

    /**
     * Standardkonstruktor einer Kategorie
     * zur Initialisierung eines Kategorie-Objeks.
     *
     * @param quizcategoryid Eindeutige ID einer Kategorie
     * @param categoryname Name einer Kategorie
     */
    public MCategory(int quizcategoryid, String categoryname) {
        this.quizcategoryid = quizcategoryid;
        this.categoryname = categoryname;
    }
}