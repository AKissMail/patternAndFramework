package de.gruppeo.wise2122_java_server.request;

import lombok.Data;

/**
 * Mapping für die das Senden der Antworten
 * - gamesid = die ID des Spiels
 * - isplayerone = true,  wenn Spieler Eins antwortet und false, wenn Spieler Zwei
 * - time = die verbleibende Zeit bis die 10 Sekunden voll sind
 */
@Data
public class DropAnswerRequest {

    private Long gamesid;
    private boolean isplayerone;
    private boolean answers;
    private int time;

}
