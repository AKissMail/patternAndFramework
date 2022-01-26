import * as controller from "../controller/C_controller.js";
import * as controllerLobby from '../controller/C_lobby.js';
import * as view from "./V_view.js";
import * as mainMenu from "./V_mainMenu.js";
import * as quiz from './quiz.js';
import * as model from "../model/M_model.js";



/**
 * Das ist eine Lobby, in der man auf das Starten das Spiel wartet.
 * @param response das Spiel (sollte die Antwort vom Server sein)
 */
export function show(response) {
    controller.clearStage();
    document.cookie = "gameID=" + response.valueOf().id;
    document.getElementsByTagName("nav")[0].appendChild(view.createButton("","Hauptmenü", "btn"));
    document.getElementsByTagName("article")[0].appendChild(view.createGenericText("h1", "Gegner wird angefragt"));
    document.getElementsByTagName("article")[0].appendChild(view.createGenericText("p", "Der gegner muss das Spiel beginnen."));
    document.getElementsByTagName("article")[0].appendChild(view.createGenericElementWithTwoAttribute("img" , "src", "img/settingsGear.png", "id", "rotation"));
    document.getElementsByClassName("btn")[0].addEventListener("click", function (){model.updateGame(model.decodeCookie("gameID"),"","null","OPEN",mainMenu.show);});
    controllerLobby.loop(150,"RUNNING", "CLOSE",
        ()=>{model.getGameByID(model.decodeCookie("gameID"),  (r)=> {quiz.show(r);})},
        ()=>{controllerLobby.leavingGame("Leider antwort der Gegner nicht.");})
}

