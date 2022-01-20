import * as base from '../controller/base.js';
import * as apiCalls from '../controller/apiCalls.js';

import * as mainMenu from './mainMenu.js';

/**
 * Diese function holt die Statistic von Server ab und hängt sie in den DOM.
 */
export function show() {
    base.clearStage();
    apiCalls.getStatistic();

    let backHome = document.createElement("div");
    backHome.setAttribute("id", "back home");
    backHome.setAttribute("class", "btn");
    let backHometext = document.createElement("p");
    backHometext.append("Hauptmenü");
    backHome.appendChild(backHometext);

    document.getElementsByTagName("nav")[0].appendChild(backHome);
    document.getElementById("back home").addEventListener("click", mainMenu.show);
}

    export function showData(data){
    console.log(data);
    let highscoreHading = document.createElement("h1");
    highscoreHading.append("Spielstatistik");
    let highscoreHadingDescription = document.createElement("p");
    //highscoreHadingDescription.append(data.length + " Spielrunden gefunden");
        highscoreHadingDescription.append('1'+ " Spielrunden gefunden");
    document.getElementsByTagName("article")[0].appendChild(highscoreHading);
    document.getElementsByTagName("article")[0].appendChild(highscoreHadingDescription);
    let scoreTabel = document.createElement("table");
    scoreTabel.setAttribute("id", "scoreTabel");

    let scoreTabelHeading = document.createElement("tr");
    let scoreTabelHeadingRundennummer = document.createElement("th");
    scoreTabelHeadingRundennummer.append("Spiel");
    let scoreTabelHeadingPlatz = document.createElement("th");
    scoreTabelHeadingPlatz.append("Rundennummer");
    let scoreTabelHeadingUser = document.createElement("th");
    scoreTabelHeadingUser.append("Kategorie");
    let scoreTabelHeadingPunkte = document.createElement("th");
    scoreTabelHeadingPunkte.append("Deine Punkte");
    let scoreTabelHeadingPunkteOpponent = document.createElement("th");
    scoreTabelHeadingPunkteOpponent.append("Die Punkte des Gegners");

    scoreTabelHeading.appendChild(scoreTabelHeadingRundennummer);
    scoreTabelHeading.appendChild(scoreTabelHeadingPlatz);
    scoreTabelHeading.appendChild(scoreTabelHeadingUser);
    scoreTabelHeading.appendChild(scoreTabelHeadingPunkte);
    scoreTabelHeading.appendChild(scoreTabelHeadingPunkteOpponent);
    scoreTabel.appendChild(scoreTabelHeading);

        let row = document.createElement("tr");
        let ranking = document.createElement("td");
        let rounds =document.createElement("td");
        let user = document.createElement("td");
        let score = document.createElement("td");
        let scoreOppnent = document.createElement("td");
        ranking.append('1');
        rounds.append(data.valueOf().rounds.rounds);
        user.append(data.valueOf().category.categoryname);
        score.append(data.valueOf().playerscore);
        scoreOppnent.append(data.valueOf().opponentscore);
        row.appendChild(ranking);
        row.appendChild(rounds);
        row.appendChild(user);
        row.appendChild(score);
        row.appendChild(scoreOppnent);
        scoreTabel.appendChild(row);


   /**
    * // hier muss die schleife aktualisiere werden
    for (let i = 1; i <= data.length; i++) {
        let row = document.createElement("tr");
        let ranking = document.createElement("td");
        let rounds =document.createElement("td");
        let user = document.createElement("td");
        let score = document.createElement("td");
        let scoreOppnent = document.createElement("td");
        ranking.append(i.toString());
        rounds.append(data[i - 1].valueOf().rounds.rounds);
        user.append(data[i - 1].valueOf().category.categoryname);
        score.append(data[i - 1].valueOf().playerscore);
        scoreOppnent.append(data[i-1].valueOf().opponentscore);
        row.appendChild(ranking);
        row.appendChild(rounds);
        row.appendChild(user);
        row.appendChild(score);
        row.appendChild(scoreOppnent);
        scoreTabel.appendChild(row);
    }
    */
    document.getElementsByTagName("article")[0].appendChild(scoreTabel);

}