# noinspection SpellCheckingInspectionForFile @ schema/"mibquizzz"

# Das ist eine alte version. Das Schema wird automatisch von Server angelegt!
########## NICHT BENUTZEN! ########




# Erstelle die Datenbank, falls nicht existent
CREATE DATABASE IF NOT EXISTS mibquizzz CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# Erstelle die Tabellenstruktur, falls noch nicht existent
# 1. Spielerprofile
create table player
(
    playerid      bigint(20) auto_increment
        primary key,
    username      varchar(20)                                                                      not null,
    password      varchar(100)                                                                     not null,
    currentscore  int                                                            default 0         null,
    thumbnail     binary(1)                                                                        null,
    currentstatus enum ('OFFLINE', 'ONLINE', 'QUIZZING', 'SEARCHING', 'WAITING') default 'OFFLINE' not null,
    constraint player_playerid_uindex
        unique (playerid),
    constraint player_username_uindex
        unique (username)
)
    comment 'Enthält die Spielerprofile';

# 2. Kategorien der Fragen
CREATE TABLE category
(
    quizcategoryid bigint(20) auto_increment
        primary key,
    categoryname   varchar(255) not null,
    constraint quizCategory_quizCategoryid_uindex
        unique (quizcategoryid)
)
    comment 'Enthält verschiedene Fachgebiete';

# 3. Tabellen mit den Fragen erstellen
create table questions
(
    quizquestionid  bigint(20) auto_increment
        primary key,
    question        varchar(255)                                                not null,
    correctanswer   varchar(255)                                                not null,
    falseanswer1    varchar(255)                                                not null,
    falseanswer2    varchar(255)                                                not null,
    falseanswer3    varchar(255)                                                not null,
    difficulty      enum ('Fachkraft', 'Ingenieur', 'Prof') default 'Fachkraft' not null,
    constraint quizQuestions_quizQuestionID_uindex
        unique (quizquestionid)
)
    comment 'Enthält die Quizfragen';

# 4. Highscore Table
create table highscore
(
    quizhighscoreid bigint(20) auto_increment
        primary key,
    highscorepoints int       default 0                   null,
    lastUpdate      timestamp default current_timestamp() not null on update current_timestamp(),
    fk_playerid     bigint(20)                            not null,
    constraint quizHighscore_quizHighscoreid_uindex
        unique (quizhighscoreid),
    constraint quizHighscore_player_playerid_fk
        foreign key (fk_playerid) references player (playerid)
            on delete cascade
);
