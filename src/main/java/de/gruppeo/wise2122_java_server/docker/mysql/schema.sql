# noinspection SpellCheckingInspectionForFile @ schema/"mibquizzz"

# Erstelle die Datenbank, falls nicht existent
CREATE DATABASE IF NOT EXISTS mibquizzz CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# Erstelle die Tabellenstruktur, falls noch nicht existent
# 1. Spielerprofile
create table quizPlayer
(
    playerID         int auto_increment
        primary key,
    username         varchar(20)                                                           not null,
    password         varchar(100)                                                          not null,
    currentScore     int                                                 default 0         null,
    fk_quizHighscore int                                                 default 0         null,
    thumbnail        binary(1)                                                             null,
    currentStatus    enum ('offline', 'online', 'quizzing', 'searching') default 'offline' not null,
    constraint player_playerID_uindex
        unique (playerID),
    constraint player_username_uindex
        unique (username)
)
    comment 'Enthält die Spielerprofile';

# 2. Kategorien der Fragen
CREATE TABLE quizCategory
(
    quizCategoryID int auto_increment
        primary key,
    categoryName   varchar(255) not null,
    constraint quizCategory_quizCategoryID_uindex
        unique (quizCategoryID)
)
    comment 'Enthält verschiedene Fachgebiete';

# 3. Tabellen mit den Fragen erstellen
create table quizQuestions
(
    quizQuestionID  int auto_increment
        primary key,
    question        text                                                        not null,
    correctAnswer   varchar(255)                                                not null,
    falseAnswer1    varchar(255)                                                not null,
    falseAnswer2    varchar(255)                                                not null,
    falseAnswer3    varchar(255)                                                not null,
    fk_quizCategory int                                                         null,
    difficulty      enum ('Fachkraft', 'Ingenieur', 'Prof') default 'Fachkraft' not null,
    constraint quizQuestions_quizQuestionID_uindex
        unique (quizQuestionID),
    constraint quizQuestions_quizCategory_quizCategoryID_fk
        foreign key (fk_quizCategory) references quizCategory (quizCategoryID)
)
    comment 'Enthält die Quizfragen';

# 4. Highscore Table
create table quizHighscore
(
    quizHighscoreID int auto_increment
        primary key,
    fk_playerID     int                                   not null,
    highscore       int       default 0                   null,
    lastUpdate      timestamp default current_timestamp() not null on update current_timestamp(),
    constraint quizHighscore_quizHighscoreID_uindex
        unique (quizHighscoreID),
    constraint quizHighscore_player_playerID_fk
        foreign key (fk_playerID) references quizPlayer (playerID)
            on delete cascade
);
