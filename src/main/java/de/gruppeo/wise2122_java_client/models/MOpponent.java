package de.gruppeo.wise2122_java_client.models;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.ArrayList;

public class MOpponent {
    private ArrayList<String> opponents;

    /**
     * Initialisiert die Kategorien-Liste und
     * führt die Methode zur Konvertierung der
     * JSON-Zeichenkette aus.
     *
     * @param serverResponse
     * @throws JSONException
     */
    public MOpponent(String serverResponse) throws JSONException {
        this.opponents = new ArrayList<>();
        parseJSON(serverResponse);
    }

    /**
     * Liest die übergebene JSON-Zeichenkette ein,
     * iteriert über alle Kategorien und speichert
     * sie in eine Liste.
     *
     * @param json
     * @throws JSONException
     */
    private void parseJSON(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        JSONArray arr = obj.getJSONArray("categories");

        for (int i = 0; i < arr.length(); i++) {
            opponents.add(arr.getJSONObject(i).getString("category_name"));
        }
    }

    /**
     * Gibt eine Liste aller
     * Quiz-Gegner zurück.
     *
     * @return Liste aller Quiz-Gegner
     */
    public ArrayList<String> getOpponents() {
        return opponents;
    }
}
