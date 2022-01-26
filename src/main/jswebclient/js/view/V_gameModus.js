import * as base from '../controller/C_controller.js';
import * as apiCalls from '../model/M_model.js';
import * as mainMenu from './V_mainMenu.js';
import * as newGame from './V_newgame.js';
import * as view from './V_view.js';
import {showEnterGame} from "./V_joineGame.js";

/**
 * Dies function stellt ein Menü dar, welches eine Auswahl ermöglicht ein Spiel beizutreten oder ein neues zu starten.
 */
export function show() {
    base.clearStage();
    document.getElementsByTagName("nav")[0].appendChild(view.createButton("back home","Hauptmenü", "btn"));
    document.getElementsByTagName("article")[0].appendChild(view.createButton("newGame","neues Spiel erstellen", "btn"));
    document.getElementsByTagName("article")[0].appendChild(view.createButton("enterGame","eine Spiel beitreten", "btn"));
    document.getElementById("back home").addEventListener("click", mainMenu.show);
    document.getElementById("newGame").addEventListener("click", newGame.showNewGamePreload);
    document.getElementById("enterGame").addEventListener("click", function (){apiCalls.getOpenGames(showEnterGame)});
}