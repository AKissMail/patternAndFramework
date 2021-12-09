# noinspection SpellCheckingInspectionForFile @ schema/"mibquizzz"

# Erstelle die Datenbank, falls nicht existent
CREATE DATABASE IF NOT EXISTS mibquizzz CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# Erstelle die Tabellenstruktur, falls noch nicht existent
# 1. Spielerprofile
create table player
(
    playerid         bigint(20) auto_increment
        primary key,
    username         varchar(20)                                                           not null,
    password         varchar(100)                                                          not null,
    currentscore     int                                                 default 0         null,
    fk_quizHighscore bigint                                                 default 0         null,
    thumbnail        binary(1)                                                             null,
    currentstatus    enum ('offline', 'online', 'quizzing', 'searching') default 'offline' not null,
    constraint player_playerid_uindex
        unique (playerid),
    constraint player_username_uindex
        unique (username)
)
    comment 'Enthält die Spielerprofile';

# 2. Kategorien der Fragen
CREATE TABLE quizCategory
(
    quizcategoryid bigint(20) auto_increment
        primary key,
    categoryName   varchar(255) not null,
    constraint quizCategory_quizCategoryid_uindex
        unique (quizcategoryid)
)
    comment 'Enthält verschiedene Fachgebiete';

# 3. Tabellen mit den Fragen erstellen
create table quizQuestions
(
    quizquestionid  bigint auto_increment
        primary key,
    question        text                                                        not null,
    correctAnswer   varchar(255)                                                not null,
    falseAnswer1    varchar(255)                                                not null,
    falseAnswer2    varchar(255)                                                not null,
    falseAnswer3    varchar(255)                                                not null,
    fk_quizcategory bigint                                                      null,
    difficulty      enum ('Fachkraft', 'Ingenieur', 'Prof') default 'Fachkraft' not null,
    constraint quizQuestions_quizQuestionID_uindex
        unique (quizquestionid),
    constraint quizQuestions_quizCategory_quizCategoryID_fk
        foreign key (fk_quizcategory) references quizCategory (quizcategoryid)
)
    comment 'Enthält die Quizfragen';

# 4. Highscore Table
create table quizHighscore
(
    quizHighscoreid bigint auto_increment
        primary key,
    fk_playerid     bigint                                   not null,
    highscore       int       default 0                   null,
    lastUpdate      timestamp default current_timestamp() not null on update current_timestamp(),
    constraint quizHighscore_quizHighscoreid_uindex
        unique (quizHighscoreid),
    constraint quizHighscore_player_playerid_fk
        foreign key (fk_playerid) references player (playerid)
            on delete cascade
);
