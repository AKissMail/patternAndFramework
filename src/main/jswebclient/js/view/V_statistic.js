import * as base from '../controller/C_controller.js';
import * as apiCalls from '../model/M_model.js';
import * as mainMenu from './V_mainMenu.js';
import * as view from './V_view.js';

/**
 * Diese function holt die Statistic von Server ab und hängt ein Button in den DOM.
 */
export function show() {
    base.clearStage();
    apiCalls.getStatistic(showData);
    document.getElementsByTagName("nav")[0].appendChild(view.createButton("back home", "Hauptmenü", "btn"));
}

/**
 * Das ist die Callback function. Sie macht aus der antwort vom Server eine Tabelle und hängt diese in den DOM.
 * @param data die Antwort vom Server
 */
export function showData(data) {
    if (data.length > 0) {
        document.getElementsByTagName("article")[0].appendChild(view.createGenericText("h1", "Spielstatistik"));
        document.getElementsByTagName("article")[0].appendChild(view.createGenericText("p", data.length + " Spielrunden gefunden"));

        let scoreTable = view.createGenericElementWithOneAttribute("table", "id", "scoreTable");

        let scoreTableHeading = document.createElement("tr");
        scoreTableHeading.appendChild(view.createGenericText("th", "Spiel"));
        scoreTableHeading.appendChild(view.createGenericText("th", "Runden nummer"));
        scoreTableHeading.appendChild(view.createGenericText("th", "Kategorie"));
        scoreTableHeading.appendChild(view.createGenericText("th", "Deine Punkte"));
        scoreTableHeading.appendChild(view.createGenericText("th", "Die Punkte des Gegners"));
        scoreTable.appendChild(scoreTableHeading);

        let summPoints = 0;
        let winn = 0;
        let lose = 0;

        for (let i = 1; i <= data.length; i++) {
            let row = document.createElement("tr");
            let ranking = view.createGenericText("td", i.toString());
            let rounds = view.createGenericText("td", data[i - 1].valueOf().rounds.rounds);
            let user = view.createGenericText("td", data[i - 1].valueOf().categoryname);
            let score = view.createGenericText("td", data[i - 1].valueOf().playerscore);
            let scoreOpponent = view.createGenericText("td", data[i - 1].valueOf().opponentscore);
            row.appendChild(ranking);
            row.appendChild(rounds);
            row.appendChild(user);
            row.appendChild(score);
            row.appendChild(scoreOpponent);
            scoreTable.appendChild(row);
            if (data[i - 1].valueOf().opponentscore > data[i - 1].valueOf().playerscore) {
                lose++
            } else {
                winn++
            }
            summPoints += data[i - 1].valueOf().playerscore;
        }

        document.getElementsByTagName("article")[0].appendChild(scoreTable);
        document.getElementsByTagName("article")[0].appendChild(view.createGenericText("p", "Du hast insgesamt " + data.length + " Spiele gespielt."));
        document.getElementsByTagName("article")[0].appendChild(view.createGenericText("p", "Davon hast du " + winn + " gewonnen und " + lose + " verloren."));
        document.getElementsByTagName("article")[0].appendChild(view.createGenericText("p", "Im Durchschnitt hast du, " + summPoints / data.length + " Punkte erzeilt."));
    } else {
        document.getElementsByTagName("article")[0].appendChild(view.createGenericText("p", "Es wurden noch kein Spiele von dir gespielt"));
    }
    document.getElementById("back home").addEventListener("click", mainMenu.show);
}