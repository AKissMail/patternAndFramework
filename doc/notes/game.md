##Vorschlag für eine Asynchronen Multiplayer.

Die Idee ist, dass Nutzer*innen nicht zur gleichen Zeit das Quiz durchgehen. Sondern am Ende sich mit einem andern Spieler vergleichen können, der auch ein ähnliches Quiz unter denselben Bedingungen absolviert hat. Hierzu wird eine neue Tabelle angelegt, die weiter unten ein aufgeführt ist. Eine Zeile in dieser Tabelle repräsentiert ein Spiel und beinhaltet die folgenden Einträge:

* Den erstellenden Player (1) (Eindeutige ID) 
* Den Punktestand den erstellenden Player 
* Die Kategorie des Quiz 
* Die Anzahl der Fragen 
* Den später betretenden Player (Eindeutige ID)
* Den Punktestand des später betretenden Player (2)
* Den Erstellungszeitpunkt des Spiels 
* Den Status des Spiels 
* Eindeutige ID des Spiels ← kann das der Server sich ausdenken? 

Nun sind in den Clients 2 Fälle umgesetzt. Wenn ein Spiel erstellt, wird ein Request an den Server geschickt, der die Punkte 1 bis 4 und 7 bis 8 enthält. Der  Client kann lädt sich jetzt die Fragen vom Server und wählt entsprechend der Angabe des Nutzers eine Menge an Zufälligen fragen aus. Die Antwort wird jetzt an den Server gesandt zusammen, mit der Zeit, die zum Antworten benötigt wurde in Millisekunden. (Alternativ könnten man auch nur ein Boolean und die Zeit schicken). Der Server rechnet die Punkte für jede richtige Antwort aus und aktualisiert entsprechend die Tabelle bis die Fragen durch sind.

In dieser Tabelle ist auch Status des Spiels mit erfasst. Sobald ein Spiel offen ist, können andre Spieler dem Spiel beitreten. Sobald dies passiert ist, ist der Status des Spiels auf geschlossen gesetzt. Der später betretenden Player beantwortet wie oben beschrieben seine Fragen und erhält, sobald dieser fertig ist ein Ergebnis verfügbar.

Der Player 1 kann entweder in einer Lobby warten oder zurück in das Hauptmenü. Falls er in der Lobby wartet, fragt er regelmäßig das Ergebnis ab und bekommt es angezeigt, sobald es vorliegt. Falls es in das Hauptmenü geht, kann er diese über Statistik abrufen. Dort werden alle Spielrunde  von dem Player angezeigt.

Am Ende werden die Punkte von einem Spiel in eine Highscore Tabelle übertragen. Der Spielscore könnte sich wie folgt errechnen:
* Punkte aus dem Spiel = p
* Anzahl der Fragen = a
> p : a × 10 = Score

Sollte der Score aus den Aktuell runde höher sein so wird dieser in die Tabelle geschrieben.

Daraus würden sich die folgenden Methoden ergeben:

POST
---------------------------
* POST: Registrierung von einem Spiel (Player 1 oder Token, Kategorie des Quiz,  Anzahl der Fragen) - es kommt eine Spiel-ID zurück
* POST: Antwort an den Server schicken (true/false Antwort richtig oder falsch, Zeit in Millisekunden)

GET
----------------------------
* GET: alle offenen Spiele - es kommt eine Liste mit allen Spiel zurück (Themas, Anzahl Fragen, Gegner, ID)
* GET: Ergebnis von Player - es kommen alle Ergebnisse von einem Spieler zurück
* GET: Ergebnis vom Spiel - es kommt das Ergebnis von einer Spielrunde zurück
* GET: Highscore - es kommt der Highscore zurück.