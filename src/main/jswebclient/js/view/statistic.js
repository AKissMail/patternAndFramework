import * as base from '../controller/base.js';
import * as apiCalls from '../controller/apiCalls.js';

import * as mainMenu from './mainMenu.js';

/**
 * Diese function holt die Statistic von Server ab und hängt sie in den DOM.
 */
export function show() {
    base.clearStage();
    let games = apiCalls.getStatistic();

    let backHome = document.createElement("div");
    backHome.setAttribute("id", "back home");
    backHome.setAttribute("class", "btn");
    let backHometext = document.createElement("p");
    backHometext.append("Statistik");
    backHome.appendChild(backHometext);

    document.getElementsByTagName("nav")[0].appendChild(backHome);
    let highscoreHading = document.createElement("h1");
    highscoreHading.append("Highscore");
    let highscoreHadingDescription = document.createElement("p");
    highscoreHadingDescription.append(games.length + " Spielrunden gefunden");
    document.getElementsByTagName("article")[0].appendChild(highscoreHading);
    document.getElementsByTagName("article")[0].appendChild(highscoreHadingDescription);
    let scoreTabel = document.createElement("table");
    scoreTabel.setAttribute("id", "scoreTabel");
    let scoreTabelHeading = document.createElement("tr");
    let scoreTabelHeadingPlatz = document.createElement("th");
    scoreTabelHeadingPlatz.append("Rundennummer");
    let scoreTabelHeadingUser = document.createElement("th");
    scoreTabelHeadingUser.append("Gegner");
    let scoreTabelHeadingPunkte = document.createElement("th");
    scoreTabelHeadingPunkte.append("Deine Punkte");
    let scoreTabelHeadingPunkteOpponent = document.createElement("th");
    scoreTabelHeadingPunkteOpponent.append("Die Punkte des Gegners");
    scoreTabelHeading.appendChild(scoreTabelHeadingPlatz);
    scoreTabelHeading.appendChild(scoreTabelHeadingUser);
    scoreTabelHeading.appendChild(scoreTabelHeadingPunkte);
    scoreTabelHeading.appendChild(scoreTabelHeadingPunkteOpponent);
    scoreTabel.appendChild(scoreTabelHeading);

    for (let i = 1; i <= games.length; i++) {
        let row = document.createElement("tr");
        let ranking = document.createElement("td");
        let user = document.createElement("td");
        let score = document.createElement("td");
        let scoreOppnent = document.createElement("td");
        ranking.append(i.toString());
        user.append(games[i - 1][0].toString());
        score.append(games[i - 1][1].toString());
        scoreOppnent.append(games[i-1][2].toString());
        row.appendChild(ranking);
        row.appendChild(user);
        row.appendChild(score);
        row.appendChild(scoreOppnent);
        scoreTabel.appendChild(row);
    }
    document.getElementsByTagName("article")[0].appendChild(scoreTabel);
    document.getElementById("back home").addEventListener("click", mainMenu.show);
}