import * as controller from "../controller/C_controller.js";
import * as view from "./V_view.js";
import * as mainMenu from "./V_mainMenu.js";
import * as model from "../model/M_model.js";
import * as quiz from './V_quiz.js';
import * as controllerLobby from '../controller/C_lobby.js';

/**
 * Das
 * @param result
 */
export function show(result) {
    document.cookie = "isplayerone = true"
    document.cookie = "gameID=" + result.valueOf().id;
    controller.clearStage();
    document.getElementsByTagName("header")[0].appendChild(view.createNavBar("lobbyNewGameBackHome", "Hauptmenü", "btn btn-light navbar-brand","navbar navbar-light bg-primary"));
    document.getElementsByTagName("article")[0].appendChild(view.createGenericText("h1", "Warten auf den Gegner"));
    document.getElementsByTagName("article")[0].appendChild(view.createGenericText("p", "Netzwerk wird durchsucht ..."));
    document.getElementsByTagName("article")[0].appendChild(view.createGenericElementWithTwoAttribute("img", "src", "img/settingsGear.png", "id", "rotation"));
    document.getElementsByClassName("btn")[0].addEventListener("click", function (){model.updateGame(model.decodeCookie("gameID"),"null","null","OPEN",mainMenu.show);});
    loop(150);
}

/**
 *
 */
function showJoined(){
        if(confirm("Es wurde ein Gegner gefunden! Möchtest Du Starten?")){
            model.updateGame(model.decodeCookie("gameID"),model.decodeCookie("playername"), "", "RUNNING", (r)=>{console.log(r)});
            model.getGameByID(model.decodeCookie("gameID"), function (r){quiz.show(r);});
        }else {
            model.updateGame(model.decodeCookie("gameID"),"null", "null", "OPEN");
            mainMenu.show();
        }
}

/**
 *
 * @param counter
 */
function loop (counter){
    if(counter >= 1){
        console.log(counter);
        setTimeout(function(){
                model.getGameByID(model.decodeCookie("gameID"), function (result) {
                    console.log(result);
                    if (result.valueOf().gamestatus == "JOINED") {
                        showJoined();
                    } else if (result.valueOf().gamestatus == "CLOSE") {
                        controllerLobby.leavingGame("Es wurde kein Gegner gefunden.");
                    } else {
                        loop(counter - 1)
                    }
                })}
            , 1000);
    }else {
        controllerLobby.leavingGame("Es wurde kein Gegner gefunden.");
    }
}
