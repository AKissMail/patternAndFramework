# 1. Sample Spieler anlegen
INSERT INTO mibquizzz.player (playerid, username, password, currentscore, currentstatus)
VALUES (99, 'todeskralle', 'fingerweg!', 100, 'OFFLINE');
INSERT INTO mibquizzz.player (playerid, username, password, currentscore, currentstatus)
VALUES (88, 'bree luce', 't0d35kr4113', 500, 'OFFLINE');

# 2. Beispielkategorien anlegen
INSERT INTO mibquizzz.category (quizcategoryid, categoryname)
VALUES (1, 'Medieninformatik');
INSERT INTO mibquizzz.category (quizcategoryid, categoryname)
VALUES (2, 'Entwicklung');
INSERT INTO mibquizzz.category (quizcategoryid, categoryname)
VALUES (3, 'Mediendesign');
INSERT INTO mibquizzz.category (quizcategoryid, categoryname)
VALUES (4, 'IT-Sicherheit');
INSERT INTO mibquizzz.category (quizcategoryid, categoryname)
VALUES (5, 'Erdkunde');
INSERT INTO mibquizzz.category (quizcategoryid, categoryname)
VALUES (6, 'Kommunikationsnetze');

#3. Beispielfragen anlegen
INSERT INTO mibquizzz.questions (quizquestionid, question, correctAnswer, falseAnswer1, falseAnswer2, falseAnswer3,
                                 difficulty, category_quizcategoryid)
VALUES (1, 'Wo werden Laptops am häufigsten gestohlen?', 'An Bushaltestellen und Flughäfen', 'in Hotels',
        'in Konferenzräumen', 'in der Mensa', 'Fachkraft', 1);
INSERT INTO mibquizzz.questions (quizquestionid, question, correctAnswer, falseAnswer1, falseAnswer2, falseAnswer3,
                                 difficulty, category_quizcategoryid)
VALUES (2, 'Woran erkennt man eine sichere Webseite?', 'Am kleinen Schloss neben der URL',
        'Am optischen Gesamteindruck des Seite', 'Am Impressum: Der Firmensitz sollte in Deutschland sein',
        'Wie aktuell die Seite ist', 'Fachkraft', 4);

#4. HighscoreEntity Beispieldaten
INSERT INTO mibquizzz.highscore (quizHighscoreID, highscorepoints, lastUpdate)
VALUES (1, 500, '2021-12-08 12:16:45');
INSERT INTO mibquizzz.highscore (quizHighscoreID, highscorepoints, lastUpdate)
VALUES (2, 350, '2021-12-10 22:25:08');

#5. Rundenanzahl anlegen
INSERT INTO mibquizzz.rounds (quizroundsid, rounds)
VALUES (1, 5);
INSERT INTO mibquizzz.rounds (quizroundsid, rounds)
VALUES (2, 10);
INSERT INTO mibquizzz.rounds (quizroundsid, rounds)
VALUES (3, 20);

#6. Fragen anlegen

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Portnummer', 'Fachkraft', 'MAC-Adresse', 'Hardwareadresse', 'IPv6-Adresse',
        'Wie heißt die Adressierung auf der Transportschicht?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Eine Vereinbarung, welche Portnummern auf der Serverseite standardmäßig für welche Anwendungen verwendet werden',
        'Fachkraft',
        'Einen Teil einer Implementierung, mit der Portnummern auf der Serverseite standardmäßig zu bestimmten Anwendungen zugewiesen sind',
        'Eine Vereinbarung, welche Portnummern auf der Clientseite standardmäßig für welche Anwendungen verwendet werden',
        'Einen Teil einer Implementierung, mit der Portnummern auf der Clientseite standardmäßig zu bestimmten Anwendungen zugewiesen sind',
        'Was versteht man unter dem Begriff „Well-known Port“?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Portnummern auf der Client-Seite sind unterschiedlich', 'Fachkraft',
        'IP-Adressen auf der Client-Seite sind unterschiedlich',
        'Portnummern auf der Server-Seite sind unterschiedlich',
        'IP-Adressen auf der Server-Seite sind unterschiedlich',
        'Ein Nutzer lädt beim Betrachten einer Webseite im Browser automatisch zwei Bilder über unterschiedliche TCP-Verbindungen herunter. Wie können die Verbindungen unterschieden werden?',
        6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Ein schneller Sender soll einen langsamen Empfänger nicht überfordern, so dass dieser eine Rückmeldung über seine aktuelle Aufnahmefähigkeit geben kann.',
        'Fachkraft',
        'Der Datenverkehr im Netz wird überprüft, ob er von unsicheren Quellen kommt. Nicht legitimer Verkehr wird dabei unterbunden.',
        'Eine Überlastung des Netzes soll verhindert werden, so dass die Datenflüsse ermöglicht werden.',
        'Die Daten sollen im Netz möglichst mit gleich hoher Bitrate übertragen werden. Hierzu werden Reservierungen durchgeführt.',
        'Was versteht man unter dem Begriff „Flusskontrolle“?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Der Empfänger steuert den Sender, so dass dieser ggf. seine Datenrate reduziert', 'Fachkraft',
        'Mit der Flusskontrolle wird eine Überlastung des Netzes verhindert',
        'Die Daten werden dadurch mit einer konstanten Bitrate versendet',
        'Mit der Flusskontrolle wird nicht verhindert, dass Daten an einen Empfänger gesandt werden, die dieser nicht mehr annehmen kann',
        'Welche Aussage passen zur Flusskontrolle bei TCP?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Mit der Staukontrolle wird eine Überlastung des Netzes verhindert', 'Fachkraft',
        'Eine Stausituation wird standardmäßig durch die Router im Netz erkannt und gemeldet',
        'Mit der Staukontrolle wird verhindert, dass Daten an einen Empfänger gesandt werden, die dieser nicht mehr annehmen kann',
        'Die Informationen im „Receive Window“-Feld gehören zur Staukontrolle',
        'Welche Aussagen passen zur Staukontrolle bei TCP?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Zeigt an, wieviel Platz derzeit im Empfangspuffer frei ist', 'Fachkraft',
        'Zeigt Zeigt an, wieviel Speicherplatz auf den Routern im Netz noch nur Verfügung steht',
        'Zeigt an, wieviel Speicherplatz auf dem nächsten Router im Netz noch zur Verfügung steht',
        'Zeigt die Größe des Empfangspuffers an', 'Welche Bedeutung hat das „Receive Window“-Feld im TCP Header?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Exponentielle Steigerung der Datenrate, ausgehend von einmal der MSS', 'Fachkraft',
        'Verzögerung der Datenübertragung durch den Three-Way-Handshake',
        'Langsame Datenübertragung zu Beginn durch die Initialisierung des Pufferspeichers',
        'Lineare Steigerung der Datenrate, ausgehend von einmal der MSS',
        'Was versteht man unter dem „Slow Start“ bei TCP?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Tahoe reagiert auf Timeouts mit einem Slow Start', 'Fachkraft',
        'Tahoe reagiert auf Duplicate Acknowledgements mit einer Halbierung der Datenrate',
        'Reno reagiert nicht auf Timeouts mit einem Slow Start',
        'Reno reagiert nicht auf Duplicate Acknowledgements mit einer Halbierung der Datenrate',
        'Welche Aussage zu den TCP-Varianten Tahoe und Reno stimmt?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('TCP verhält sich fair. ', 'Fachkraft', 'UDP verhält sich fair. ', 'TCP verhält sich nicht fair.',
        'UDP-Ströme können TCP-Ströme komplett verdrängen.', 'Welche Aussage zur Fairness von UDP und TCP stimmt?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Sicherungsschicht', 'Fachkraft', 'Bitübertragungsschicht', 'Transportschicht', 'Anwendungsschicht',
        'Zu welcher Schicht gehört eine Netzwerkkomponente, die Switch (ohne Zusätze) genannt wird?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Rahmen', 'Fachkraft', 'Datagramme', 'Segmente', 'Pakete',
        'Wie werden Dateneinheiten der Sicherungsschicht genannt?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Es ist der dominante Standard bei lokalen Festnetzen', 'Fachkraft',
        'Es ist der dominante Standard bei lokalen drahtlosen Netzen',
        'Es ist der dominante Standard bei Weitverkehrsnetzen', 'Es ist der dominante Standard bei Mobilfunknetzen',
        'Welche Bedeutung hat Ethernet heutzutage?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Bei Ethernet II kommt nach den Adressfeldern ein Typ-Feld, beim IEEE Ethernet ein Längenschlüssel.',
        'Fachkraft',
        'Die Synchronisationsbitmuster sind unterschiedlich. Beim IEEE Ethernet kommt man mit einem kürzeren Muster (nur 6 Bytes) aus.',
        'Beim älteren Ethernet II wurde keine Frame Check Sequence vorgesehen. Eine Überprüfung wird dann von den höheren Schichten vorgenommen.',
        'Die Adressfelder unterscheiden sich. Beim Ethernet II sind die Adressfelder nur 16 Bit lang, während sie beim IEEE Ethernet 48 Bit lang sind.',
        'Welche Unterschiede gibt es zwischen Ethernet II und IEEE Ethernet?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Das Verfahren spielt in bei heutigen Ethernet-Netzen eine marginale Rolle, da man die Netze so konstruiert, dass keine Kollisionen mehr vorkommen.',
        'Fachkraft',
        'CSMA/CD ist das Verfahren, welches bei Ethernet eingesetzt wird. Aufgrund der hohen Verbreitung von Ethernet ist es daher sehr wichtig, um den Medienzugriff zu regeln.',
        'Das Verfahren wird bei WLANs eingesetzt. Durch die hohe Verbreitung von WLAN spielt es daher eine wichtige Rolle.',
        'Das Verfahren ist wichtig, um unterschiedliche VLANs auf einem einzigen Switch voneinander abzugrenzen. VLANs werden aktuell sehr viel eingesetzt, so dass damit das Verfahren auch häufig vorkommt.',
        'Welche Bedeutung hat das CSMA/CD-Verfahren in heutigen Netzen?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Ist die Netzwerkkomponente ein Repeater, dann sind beide Endgeräte in derselben Broadcast Domain', 'Fachkraft',
        'Ist die Netzwerkkomponente ein Hub, dann sind beide Endgeräte in derselben Broadcast Domain',
        'Ist die Netzwerkkomponente ein Switch, dann sind beide Endgeräte in derselben Collision Domain',
        'Ist die Netzwerkkomponente ein Käsekuchen, dann sind beide Endgeräte in derselben Collision Domain',
        'Zwei Endgeräte seien über eine Netzwerkkomponente miteinander verbunden. Welche Aussage stimmt dann?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Switches werden verwendet.', 'Fachkraft', 'Hubs werden verwendet.', 'Sie haben eine Ringtopologie.',
        'Sie haben eine Bustopologie', 'Wie sind moderne Ethernet-Installationen aufgebaut?
', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Der Switch sendet den Rahmen auf allen Ports bis auf den Eingangsport', 'Fachkraft',
        'Der Switch speichert die Zuordnung von Zieladresse und Zielport ab',
        'Der Switch sendet den Rahmen zurück an die Quelladresse, weil er mit der Zieladresse nichts anfangen kann',
        'Der Switch speichert nicht die Zuordnung von Quelladresse und Quellport ab',
        'Ein Unicast-Rahmen kommt bei einem Switch an, so dass der Switch nun die Weiterleitung prüft. Der Switch kannte vorher weder die Quell- noch die Zieladresse. Welche Aussagen sind dann richtig?',
        6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Der Switch sendet den Rahmen gezielt nur auf einem Port (höchstens)', 'Fachkraft',
        'Der Switch erneuert die Gültigkeit der Zuordnung von Zieladresse und Zielport',
        'Der Switch verwirft den Rahmen auf jeden Fall, weil eine Weiterleitung nicht notwendig ist',
        'Der Switch erneuert nicht die Gültigkeit der internen Zuordnung von Quelladresse und Quellport',
        'Ein Unicast-Rahmen kommt bei einem Switch an, so dass der Switch nun die Weiterleitung prüft. Der Switch kannte vorher sowohl die Quell- als auch die Zieladresse. Welche Aussagen sind dann richtig?',
        6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Einzelne Ports an Switches werden inaktiv', 'Fachkraft', 'Das Protokoll transportiert nun die Nutzdaten',
        'Es können Schleifen entstehen', 'VLANs können nun konfiguriert werden',
        'Welche Folgen hat die Verwendung des Spanning Tree Protocols?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Ein halbduplex-Modus ist nicht mehr vorgesehen.', 'Fachkraft',
        'Es sind nur noch Glasfaservarianten spezifiziert.', 'CSMA/CD ist implementiert.',
        'Das Rahmenformat wurde geändert.', 'Das Rahmenformat wurde geändert.', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Das Protokoll ist ineffizient, da während des Wartens keine weiteren Dateneinheiten gesendet werden.',
        'Fachkraft', 'Es können keine alternativen Wege im Netz genutzt werden.',
        'Der Speicher auf der Empfängerseite wird überlastet, was zum Warten führt.',
        'Das Protokoll führt zu Datenverlusten, wenn das Anhalten während des Versands eines Rahmens eintritt.',
        'Welche Nachteile hat das Stop-and-Wait-Protokoll?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Er verwirft den Rahmen und unternimmt keine weiteren Schritte.', 'Fachkraft',
        'Er versucht mit Hilfe von redundanten Bits im Rahmen die fehlerhaften Bits zu rekonstruieren. Wenn das nicht gelingt, erfolgt eine Fehlermeldung an den Absender.',
        'Er fordert den Rahmen aktiv neu beim Sender an.',
        'Er meldet den Fehler an den Absender zurück (sog. Negative Acknowledgement).',
        'Wie reagiert ein Empfänger in realen LANs, wenn er Bitfehler in einem gerade erhaltenen Rahmen feststellt?',
        6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Verzögerungsschwankung', 'Fachkraft', 'Paketverluste', 'Verzögerung', 'Durchsatz',
        'Was versteht man unter dem Begriff „Jitter“?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Live Streaming', 'Fachkraft', 'E-Mail', 'World Wide Web', 'Darpa Net',
        'Bei welchen Anwendungen ist keine 100% Zuverlässigkeit bei der Datenübertragung notwendig?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Übertragung von E-Mails zwischen Mail Servern', 'Fachkraft',
        'Abholung von E-Mails vom Mail Server durch den Client des Empfängers',
        'Ende-zu-Ende-Übertragung von E-Mails zwischen den Clients von Sender und Empfänger', 'Versand von Post',
        'Wozu dient das Simple Mail Transfer Protocol (SMTP)?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Abholung von E-Mails vom Mail Server durch den Client des Empfängers', 'Fachkraft',
        'Versand von E-Mails vom Client des Senders zum Mail Server', 'Übertragung von E-Mails zwischen Mail Servern',
        'Ende-zu-Ende-Übertragung von E-Mails zwischen den Clients von Sender und Empfänger',
        'Wozu dient das Post Office Protocol (POP)?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Abholung von E-Mails vom Mail Server durch den Client des Empfängers', 'Fachkraft',
        'Versand von E-Mails vom Client des Senders zum Mail Server',
        'Ende-zu-Ende-Übertragung von E-Mails zwischen den Clients von Sender und Empfänger',
        'Übertragung von E-Mails zwischen Mail Servern', 'Wozu dient das Internet Mail Access Protocol (IMAP)?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Basiert auf TCP', 'Fachkraft', 'Unterscheidet Zustände', 'Ist verbindungsorientiert',
        'Bietet Vertraulichkeit durch Verschlüsselung', 'Welche Eigenschaften hat HTTP?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Es sind verschiedene Streams innerhalb von einer Verbindung möglich.', 'Fachkraft',
        'Laut Spezifikation muss es mit TLS kombiniert werden.', 'Der Server kann mit Anforderung Daten versenden.',
        'Der Header kann nicht werden.', 'Was ist neu bei HTTP/2 im Vergleich zu vorherigen Versionen?', 6);

INSERT INTO mibquizzz.questions (correctanswer, difficulty, falseanswer1, falseanswer2, falseanswer3, question,
                                 category_quizcategoryid)
VALUES ('Cookies heben das zustandslose Prinzip von HTTP auf', 'Fachkraft',
        'In den Cookies sind immer Daten über die Nutzer (IP-Adresse, vorher besuchte Webseiten, etc.) gespeichert',
        'Cookies enthalten Code, der auf der Seite des Nutzers ausgeführt wird',
        'Cookies sind Informationen über besuchte Webseiten, die den Nutzer identifizierbar machen und auf dem PC des Nutzers in einer Datei oder eine Datenbank gespeichert sind.',
        'Welche Eigenschaften haben Cookies?', 6);


