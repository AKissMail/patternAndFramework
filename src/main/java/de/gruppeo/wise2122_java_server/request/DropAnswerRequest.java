package de.gruppeo.wise2122_java_server.request;

import lombok.Data;

/**
 * Mapping für die das Senden der Antworten
 * games id = die ID des game
 * player one = Wenn der Player one antwortet → true | Player tow antwortet → false
 * time = die verbleibende Zeit bis die 10 Sekunden voll sind.
 */
@Data
public class DropAnswerRequest {

    private Long gamesid;
    private boolean isplayerone;
    private boolean answers;
    private int time;

}
