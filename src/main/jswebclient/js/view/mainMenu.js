import * as base from '../controller/base.js';
import * as apiCalls from '../controller/apiCalls.js';

import * as gamemode from './gamemode.js';
import * as settings from './settings.js';
import * as highscore from './highscore.js';
import * as statistic from './statistic.js';

/**
 * Diese function zeigt das Haubtmenü an.
 *
 */
export function show() {
    console.log("show");
    base.clearStage();
    let user = apiCalls.getLocalUser();

    let profilePic = document.createElement("img");
    profilePic.setAttribute("id", "thumbnail");
    profilePic.setAttribute("src", user[4]);
    let name = document.createElement("h2");
    name.append("Hallo "+ apiCalls.decodeCookie("playername"));

    let logout = document.createElement("div");
    let logoutText = document.createElement("p");
    logoutText.append("Abmelden");
    logout.setAttribute("id", "logout");
    logout.setAttribute("class", "btn");
    logout.appendChild(logoutText);

    let startGame = document.createElement("div");
    startGame.setAttribute("id", "startGame");
    startGame.setAttribute("class", "btn");
    let startGameText = document.createElement("p");
    startGameText.append("Quiz starten")
    startGame.appendChild(startGameText);

    let settings = document.createElement("div");
    settings.setAttribute("id", "settings");
    settings.setAttribute("class", "btn");
    let settingsText = document.createElement("p")
    settingsText.append("Einstellungen")
    settings.appendChild(settingsText);

    let highscore = document.createElement("div");
    highscore.setAttribute("id", "highscore");
    highscore.setAttribute("class", "btn");
    let highscoreText = document.createElement("p")
    highscoreText.append("Highscore")
    highscore.appendChild(highscoreText);

    let statistic = document.createElement("div");
    statistic.setAttribute("id", "statistic");
    statistic.setAttribute("class", "btn");
    let statisticText = document.createElement("p")
    statisticText.append("Spielstatistik")
    statistic.appendChild(statisticText);

    document.getElementsByTagName("article")[0].appendChild(profilePic);
    document.getElementsByTagName("article")[0].appendChild(name);
    document.getElementsByTagName("article")[0].appendChild(logout);
    document.getElementsByTagName("article")[0].appendChild(startGame);
    document.getElementsByTagName("article")[0].appendChild(settings);
    document.getElementsByTagName("article")[0].appendChild(highscore);
    document.getElementsByTagName("article")[0].appendChild(statistic);
    addEventListener();
}

/**
 * kleine helfer function, welch die eventListener setzt.
 */
function addEventListener(){
    document.getElementById("logout").addEventListener("click", apiCalls.logout);
    document.getElementById("startGame").addEventListener("click", gamemode.show);
    document.getElementById("settings").addEventListener("click",settings.show);
    document.getElementById("highscore").addEventListener("click", highscore.show);
    document.getElementById("statistic").addEventListener("click", statistic.show);
    console.log("addEventListener");
}