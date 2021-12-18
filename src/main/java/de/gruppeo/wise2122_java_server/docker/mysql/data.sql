# 1. Sample Spieler anlegen
INSERT INTO mibquizzz.player (playerid, username, password, currentscore, fk_quizHighscore, thumbnail, currentstatus)
VALUES (1, 'todeskralle', 'fingerweg!', 100, 0, null, 'offline');
INSERT INTO mibquizzz.player (playerid, username, password, currentscore, fk_quizHighscore, thumbnail, currentstatus)
VALUES (1, 'bree luce', 't0d35kr4113', 500, 0, null, 'offline');

# 2. Beispielkategorien anlegen
INSERT INTO mibquizzz.category (quizcategoryid, categoryname)
VALUES (1, 'Entwicklung');
INSERT INTO mibquizzz.category (quizcategoryid, categoryname)
VALUES (2, 'Mediendesign');
INSERT INTO mibquizzz.category (quizcategoryid, categoryname)
VALUES (3, 'IT-Sicherheit');

#3. Beispielfragen anlegen
INSERT INTO mibquizzz.questions (quizquestionid, question, correctAnswer, falseAnswer1, falseAnswer2, falseAnswer3,
                                     fk_quizCategory, difficulty)
VALUES (1, 'Wo werden Laptops am häufigsten gestohlen?', 'An Bushaltestellen und Flughäfen', 'in Hotels',
        'in Konferenzräumen', 'in der Mensa', 3, 'Fachkraft');
INSERT INTO mibquizzz.questions (quizquestionid, question, correctAnswer, falseAnswer1, falseAnswer2, falseAnswer3,
                                     fk_quizCategory, difficulty)
VALUES (2, 'Woran erkennt man eine sichere Webseite?', 'Am kleinen Schloss neben der URL',
        'Am optischen Gesamteindruck des Seite', 'Am Impressum: Der Firmensitz sollte in Deutschland sein',
        'Wie aktuell die Seite ist', 3, 'Fachkraft');

#4. HighscoreEntity Beispieldaten
INSERT INTO mibquizzz.highscore (quizHighscoreID, fk_playerID, highscorepoints, lastUpdate) VALUES (1, 1, 500, '2021-12-08 12:16:45');
INSERT INTO mibquizzz.highscore (quizHighscoreID, fk_playerID, highscorepoints, lastUpdate) VALUES (2, 2, 350, '2021-12-10 22:25:08');

