import {clearStage} from '../controler/base.js';
/**
 * @todo in die Init verschiben
 * macht die Stage leer und ruft home auf
 */

function backMainMenu() {
    clearStage(displayHome);
    //@todo
}

/**
 *
 */
export function displaySore(){
    let games = [[1, "User A", 650],[2, "User A", 650]]//apiGetOwnHighscore();

    let backHome = document.createElement("a");
    backHome.append("< Hauptmenü");
    document.getElementsByTagName("nav")[0].appendChild(backHome);
    document.getElementsByTagName("a")[0].addEventListener(onclick, backMainMenu());

    let highscoreHading = document.createElement("h1");
    highscoreHading.append("Highscore");
    let highscoreHadingDescription = document.createElement("p");
    highscoreHadingDescription.append(games.length+" Spielrunden gefunden");
    document.getElementsByTagName("article")[0].appendChild(highscoreHading);
    document.getElementsByTagName("article")[0].appendChild(highscoreHadingDescription);


    let scoreTabel= document.createElement("table");
    scoreTabel.setAttribute("id", "scoreTabel");
    let scoreTabelHeading = document.createElement("tr");
    let scoreTabelHeadingPlatz = document.createElement("th");
    scoreTabelHeadingPlatz.append("Platz");
    let scoreTabelHeadingUser = document.createElement("th");
    scoreTabelHeadingUser.append("User");
    let scoreTabelHeadingPunkte = document.createElement("th");
    scoreTabelHeadingPunkte.append("Punkte");
    scoreTabelHeading.appendChild(scoreTabelHeadingPlatz);
    scoreTabelHeading.appendChild(scoreTabelHeadingUser);
    scoreTabelHeading.appendChild(scoreTabelHeadingPunkte);
    scoreTabel.appendChild(scoreTabelHeading);

    for (let i= 1; i <= games.length; i++){
       let row = document.createElement("tr");
       let ranking = document.createElement("td");
       let user = document.createElement("td");
       let score = document.createElement("td");
       ranking.append(i.toString());
       user.append(games[i-1][1].toString());
       score.append((games[i-1][2]).toString());

        row.appendChild(ranking);
        row.appendChild(user);
        row.appendChild(score);
        scoreTabel.appendChild(row);
    }
    document.getElementsByTagName("article")[0].appendChild(scoreTabel);

}