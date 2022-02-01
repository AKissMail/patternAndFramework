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
    document.getElementsByTagName("header")[0].appendChild(view.createNavBar("gameModusBackHome","Hauptmenü", "btn btn-light navbar-brand","navbar navbar-light bg-primary"));

    let wrapper = view.createGenericElementWithOneAttribute("span", "class","btnGrid");
    wrapper.appendChild(view.createButton("gameModusNewGame","neues Spiel erstellen", "btn btn-primary"));
    wrapper.appendChild(view.createButton("gameModusEnterGame","eine Spiel beitreten", "btn btn-primary"));

    document.getElementsByTagName("article")[0].appendChild(wrapper);
    document.getElementById("gameModusBackHome").addEventListener("click", mainMenu.show);
    document.getElementById("gameModusNewGame").addEventListener("click", newGame.showNewGamePreload);
    document.getElementById("gameModusEnterGame").addEventListener("click", function (){apiCalls.getOpenGames(showEnterGame)});
}