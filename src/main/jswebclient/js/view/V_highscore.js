import * as base from '../controller/C_controller.js';
import * as apiCalls from '../model/M_model.js';
import * as mainMenu from './V_mainMenu.js';
import * as view from './V_view.js';

/**
 * Zeigt den Highscore aller Spiele an.
 */
export function show() {
    base.clearStage();
    apiCalls.getHighscore(showData);
    document.getElementsByTagName("nav")[0].appendChild(view.createButton("back home", "Hauptmenü", "btn"));
}

export function showData (data){
    document.getElementsByTagName("article")[0].appendChild(view.createGenericText("h1", "Highscore"));
    document.getElementsByTagName("article")[0].appendChild(view.createGenericText("p", data.length + " Einträge gefunden"));

    let scoreTable = view.createGenericElementWithOneAttribute("table", "id", "scoreTable");
    let scoreTableHeading = document.createElement("tr");
    scoreTableHeading.appendChild(view.createGenericText("th", "Platz"));
    scoreTableHeading.appendChild(view.createGenericText("th","User"));
    scoreTableHeading.appendChild(view.createGenericText("th","Punkte"));
    scoreTableHeading.appendChild(view.createGenericText("th","Datum"));

    scoreTable.appendChild(scoreTableHeading);

    for (let i = 1; i <= data.length; i++) {
        let row = document.createElement("tr");
        row.appendChild(view.createGenericText("td", i.toString()));
        row.appendChild(view.createGenericText("td", data[i-1].valueOf().playername));
        row.appendChild(view.createGenericText("td", data[i-1].valueOf().highscorepoints));
        row.appendChild(view.createGenericText("td", data[i-1].valueOf().lastupdate.slice(0,10)));
        scoreTable.appendChild(row);
    }
    document.getElementsByTagName("article")[0].appendChild(scoreTable);
    document.getElementById("back home").addEventListener("click", mainMenu.show);
}