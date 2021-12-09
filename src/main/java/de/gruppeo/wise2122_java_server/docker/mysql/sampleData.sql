# 1. Sample Spieler anlegen
INSERT INTO mibquizzz.player (playerid, username, password, currentscore, fk_quizHighscore, thumbnail, currentstatus)
VALUES (1, 'todeskralle', 'fingerweg!', 100, 0, null, 'offline');

# 2. Beispielkategorien anlegen
INSERT INTO mibquizzz.quizCategory (quizCategoryID, categoryName)
VALUES (1, 'Entwicklung');
INSERT INTO mibquizzz.quizCategory (quizCategoryID, categoryName)
VALUES (2, 'Mediendesign');
INSERT INTO mibquizzz.quizCategory (quizCategoryID, categoryName)
VALUES (3, 'IT-Sicherheit');

#3. Beispielfragen anlegen
INSERT INTO mibquizzz.quizQuestions (quizQuestionID, question, correctAnswer, falseAnswer1, falseAnswer2, falseAnswer3,
                                     fk_quizCategory, difficulty)
VALUES (1, 'Wo werden Laptops am häufigsten gestohlen?', 'An Bushaltestellen und Flughäfen', 'in Hotels',
        'in Konferenzräumen', 'in der Mensa', 3, 'Fachkraft');
INSERT INTO mibquizzz.quizQuestions (quizQuestionID, question, correctAnswer, falseAnswer1, falseAnswer2, falseAnswer3,
                                     fk_quizCategory, difficulty)
VALUES (2, 'Woran erkennt man eine sichere Webseite?', 'Am kleinen Schloss neben der URL',
        'Am optischen Gesamteindruck des Seite', 'Am Impressum: Der Firmensitz sollte in Deutschland sein',
        'Wie aktuell die Seite ist', 3, 'Fachkraft');

