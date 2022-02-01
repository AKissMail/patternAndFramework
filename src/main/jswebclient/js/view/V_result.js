import * as base from '../controller/C_controller.js';
import * as mainMenu from './V_mainMenu.js';
import * as statistic from './V_statistic.js';
import * as view from './V_view.js';

/**
 * Stellt das ergebnis eines Spiel dar.
 * @param localPoint die Punkte des lokalen Spielers.
 * @param remotePoint die Punkte des Gegners.
 * @param nameOpponent der Name des Gegners.
 */
export function show(localPoint, remotePoint, nameOpponent) {
    base.clearStage();
    document.getElementsByTagName("header")[0].appendChild(view.createNavBar("resultBackHome", "Hauptmenü", "btn btn-light navbar-brand","navbar navbar-light bg-primary"));
    document.getElementsByTagName("article")[0].appendChild(v_result(localPoint, remotePoint));
    document.getElementsByTagName("article")[0].appendChild(view.createGenericText("p", "Du("+localPoint+" Punkte) vs. " + nameOpponent+"("+ remotePoint+" Punkte)"));
    document.getElementsByTagName("article")[0].appendChild(view.createButton("resultCenterBtn", "Highscore anzeigen", "btn btn-primary"));
    document.getElementById("resultBackHome").addEventListener("click", mainMenu.show);
    document.getElementById("resultCenterBtn").addEventListener("click", statistic.show);
}

/**
 * Diese Function verglicht Zwei Punktestände und gibt ein passenden stückchen HTML zurück.
 * @param localPoint Punkte des Lokalen User
 * @param remotePoint Punkte des remote User
 * @returns {*} ein <p> mit den passenden String
 */
function v_result(localPoint, remotePoint){
    let heading;
    if (localPoint > remotePoint) {
        heading = view.createGenericText("h1","Du hast gewonnen!");
        heading.classList.add("green");
    } else if(localPoint < remotePoint){
        heading = view.createGenericText("h1","Du hast verloren!");
        heading.classList.add("red");
    }else{
        heading = view.createGenericText("h1","Es ist unentschieden!");
    }
    return heading;
}