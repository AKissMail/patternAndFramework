import * as controller from "../controller/C_controller.js";
import * as view from "./V_view.js";
import * as mainMenu from "./V_mainMenu.js";
import * as model from "../model/M_model.js";
import * as quiz from './V_quiz.js';
import * as controllerLobby from '../controller/C_lobby.js';

export function show(result) {
    document.cookie = "gameID=" + result.valueOf().id;
    controller.clearStage();
    document.getElementsByTagName("nav")[0].appendChild(view.createButton("", "Hauptmenü", "btn"));
    document.getElementsByTagName("article")[0].appendChild(view.createGenericText("h1", "Warten auf den Gegner"));
    document.getElementsByTagName("article")[0].appendChild(view.createGenericText("p", "Netzwerk wird durchsucht ..."));
    document.getElementsByTagName("article")[0].appendChild(view.createGenericElementWithTwoAttribute("img", "src", "img/settingsGear.png", "id", "rotation"));
    document.getElementsByClassName("btn")[0].addEventListener("click", function (){model.updateGame(model.decodeCookie("gameID"),"null","null","OPEN",mainMenu.show);});
    controllerLobby.loop(150,"JOINED", ".", showJoined, ()=>{});
}

function showJoined(){
        if(confirm("Es wurde ein Gegner gefunden! Möchtest Du Starten?")){
            model.updateGame(model.decodeCookie("gameID"),model.decodeCookie("playername"), "", "RUNNING");
            model.getGameByID(model.decodeCookie("gameID"), function (r){quiz.show(r);});
        }else {
            model.updateGame(model.decodeCookie("gameID"),"null", "null", "OPEN");
            mainMenu.show();
        }
}