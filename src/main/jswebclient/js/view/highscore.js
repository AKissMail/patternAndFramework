import * as base from '../controller/base.js';
import * as apiCalls from '../controller/apiCalls.js';
import * as mainMenu from './mainMenu.js';

/**
 * Zeigt den Highscore aller Spiele an.
 */
export function show() {
    base.clearStage();
    apiCalls.getHighscore();

    let backHome = document.createElement("div");
    backHome.setAttribute("id", "back home");
    backHome.setAttribute("class", "btn");
    let backHometext = document.createElement("p");
    backHometext.append("Hauptmenü");
    backHome.appendChild(backHometext);

    document.getElementsByTagName("nav")[0].appendChild(backHome);
}

export function showData (data){
    let highscoreHading = document.createElement("h1");
    highscoreHading.append("Highscore");
    let highscoreHadingDescription = document.createElement("p");
    highscoreHadingDescription.append(data.length + " Einträge gefunden");
    document.getElementsByTagName("article")[0].appendChild(highscoreHading);
    document.getElementsByTagName("article")[0].appendChild(highscoreHadingDescription);
    let scoreTabel = document.createElement("table");
    scoreTabel.setAttribute("id", "scoreTabel");
    let scoreTabelHeading = document.createElement("tr");
    let scoreTabelHeadingPlatz = document.createElement("th");
    scoreTabelHeadingPlatz.append("Platz");
    let scoreTabelHeadingUser = document.createElement("th");
    scoreTabelHeadingUser.append("User");
    let scoreTabelHeadingPunkte = document.createElement("th");
    scoreTabelHeadingPunkte.append("Punkte");
    let scoreTabelHeadingDate = document.createElement("th");
    scoreTabelHeadingDate.append("Datum");
    scoreTabelHeading.appendChild(scoreTabelHeadingPlatz);
    scoreTabelHeading.appendChild(scoreTabelHeadingUser);
    scoreTabelHeading.appendChild(scoreTabelHeadingPunkte);
    scoreTabelHeading.appendChild(scoreTabelHeadingDate);
    scoreTabel.appendChild(scoreTabelHeading);
    for (let i = 1; i <= data.length; i++) {
        let row = document.createElement("tr");
        let ranking = document.createElement("td");
        let user = document.createElement("td");
        let score = document.createElement("td");
        let date = document.createElement("td");
        ranking.append(i.toString());
        user.append(data[i-1].valueOf().playername);
        score.append(data[i-1].valueOf().highscorepoints);
        date.append(data[i-1].valueOf().lastupdate);
        row.appendChild(ranking);
        row.appendChild(user);
        row.appendChild(score);
        row.appendChild(date);
        scoreTabel.appendChild(row);
    }
    document.getElementsByTagName("article")[0].appendChild(scoreTabel);
    document.getElementById("back home").addEventListener("click", mainMenu.show);
}