import * as base from '../controller/base.js';
import * as apiCalls from '../controller/apiCalls.js';

import * as mainMenu from './mainMenu.js';

/**
 * Diese function holt die Statistic von Server ab und hängt sie in den DOM.
 */
export function show() {
    base.clearStage();
    apiCalls.getStatistic(showData);

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

    if (!data.length === 0) {
        console.log(data);
        let highscoreHading = document.createElement("h1");
        highscoreHading.append("Spielstatistik");
        let highscoreHadingDescription = document.createElement("p");
        highscoreHadingDescription.append(data.length + " Spielrunden gefunden");
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


        let summPoints = 0;
        let winn = 0;
        let lose = 0;

        for (let i = 1; i <= data.length; i++) {
            let row = document.createElement("tr");
            let ranking = document.createElement("td");
            let rounds = document.createElement("td");
            let user = document.createElement("td");
            let score = document.createElement("td");
            let scoreOppnent = document.createElement("td");
            ranking.append(i.toString());
            rounds.append(data[i - 1].valueOf().rounds.rounds);
            user.append(data[i - 1].valueOf().category.categoryname);
            score.append(data[i - 1].valueOf().playerscore);
            scoreOppnent.append(data[i - 1].valueOf().opponentscore);
            row.appendChild(ranking);
            row.appendChild(rounds);
            row.appendChild(user);
            row.appendChild(score);
            row.appendChild(scoreOppnent);
            scoreTabel.appendChild(row);
            if (data[i - 1].valueOf().opponentscore > data[i - 1].valueOf().playerscore) {
                lose++
            } else {
                winn++
            }
            summPoints += data[i - 1].valueOf().playerscore;
        }
        let text1 = document.createElement("p");
        text1.append("Du hast insgesamt " + data.length + " Spiele gespielt.");
        let text2 = document.createElement("p");
        text2.append("Davon hast du " + winn + " gewonnen und " + lose + " verloren.");
        let text3 = document.createElement("p");
        text3.append("Im Durchschnitt hast du, " + summPoints / data.length + " Punkte erzeilt.")
        document.getElementsByTagName("article")[0].appendChild(scoreTabel);
        document.getElementsByTagName("article")[0].appendChild(text1);
        document.getElementsByTagName("article")[0].appendChild(text2);
        document.getElementsByTagName("article")[0].appendChild(text3);
    }else{
        let emptytext = document.createElement("p");
        emptytext.append("Es wurden noch kein Spiele von dir gespielt");
        document.getElementsByTagName("article")[0].appendChild(emptytext);
    }

}