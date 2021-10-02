#Vorbesprechung
Wir haben über die anstehenden Aufgaben gesprochen und was für ein  Spiel wir umsetzten, möchten. Bei der Diskussion sind wir auf 2 Vorschläge gekommen. Das Spiel muss die folgenden Anforderungen erfüllen:

#### Es ist ein Server in Java zu implementiert
* Die registrierten Anwender und Spielhistorie soll zentral in einer relationalen Datenbank gespeichert werden via Framework für das objekt-relationale Mapping
* Eine API für den Datenaustausch mit dem Client implementiert werden. Daten sollen im JSON- oder XML-Format ausgetauscht werden. Die API soll JSON Web Tokens (JWT) zur Authentifizierung von Anwendern unterstützen.

#### Es ist ein Client in Java FX zu implementiert
* Der Client soll ein grafisches UI umfassen
* Die Severseitengen funktionalitäten nutzen
 
#### Es ist ein Client in Android Java API/Web-Anwendung mit Angular zu implemtentiren
* Der Client soll ein grafisches UI umfassen
* Die Severseitengen funktionalitäten nutzen

###Die erste Idee:
Ein Musikquiz in dem man kurze Ausschnitte aus einem Song vorgespielt bekommt und den richtigen Titel benennen muss. Hier für könnte die API von Spotify infrage kommen. Hier ist die Doku: https://developer.spotify.com/documentation/web-api/

###Die zweite Idee:
Ein Wissensquiz welches nach dem Prinzip von "Wer wird Millionär" in welchen Fragen mit der Auswahl von einer von vier möglichen Antworten beantwortet werden.

In der Diskussion haben wir festgestellt das die zweite Idee wohl simpler umzusetzen ist und außerdem hat Thomas schon ein Logo gebastelt (Ja das Whiteboard ist der Shit!)

Zusammengefasst haben wir uns auf das folgende Projekt verständigt:
Ein Wissensquiz, welches nach dem Prinzip von "Wer wird Millionär" in welchen Fragen mit der Auswahl von einer von vier möglichen Antworten beantwortet werden und dabei die folgenden Anforderungen erfüllt:

* Man kann sich bei dem Server registrieren und erhält Logindaten
* Man kann sich bei dem Server mit Logindaten anmelde
* Man kann sich bei dem Server abmelden
* Man kann aus einem Set von Fragen auswählen
* Man kann Fragen beantworten
* Man muss die Menge der Fragen pro Set beschrenken
* Man kann Ergebnisse auf dem Server speichern
* Man kann seine Ergebnisse mit andren verglichen
* Man kann eine Historie an Spielen ansehen
* Man kann die Fragen über eine Konfigurationsdatei Serverseitig verändern und anpassen 