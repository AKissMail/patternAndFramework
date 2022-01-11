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

