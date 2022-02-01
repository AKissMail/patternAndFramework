import {decodeCookie } from "../model/M_model.js";
import {clearStage} from "../controller/C_controller.js";
import * as view from "./V_view.js";
import * as mainMenu from "./V_mainMenu.js";
import * as apiCalls from "../model/M_model.js";
import * as Vresult from "./V_result.js";
import * as model from "../model/M_model.js";
import * as controllerLobby from "../controller/C_lobby.js";

/**
 * Diese Function zeigt eine Lobby an.
 */
export function show() {
    clearStage();
    document.getElementsByTagName("header")[0].appendChild(view.createNavBar("lobbyResultBackHome", "Hauptmenü", "btn btn-light navbar-brand","navbar navbar-light bg-primary"));
    document.getElementsByTagName("article")[0].appendChild(view.createGenericText("h1", "Warten auf den Gegner"));
    document.getElementsByTagName("article")[0].appendChild(view.createGenericText("p", "Dein Gegner hat noch nicht alle Fragen Beantwortet. Du Kannst hier warten oder später in der Statistik nachschauen."));
    document.getElementsByTagName("article")[0].appendChild(view.createGenericElementWithTwoAttribute("img", "src", "img/settingsGear.png", "id", "rotation"));
    document.getElementsByClassName("btn")[0].addEventListener("click", function (){apiCalls.updateGame(decodeCookie("gameID"),"null","null","OPEN",mainMenu.show);});
    loop(150,);
}

/**
 * Diese function schaut nach, ob ein Ergebnis zu dem Spiel vorliegt und zeigt diese an. Falls es ein Problem gibt,
 * wird der User in das Hauptmenü geschickt.
 * @param counter
 */
function loop(counter){
    if(counter >= 1){
        console.log(counter);
        setTimeout(function(){
                model.getGameByID(model.decodeCookie("gameID"), function (result) {
                    console.log(result);
                    if (result.valueOf().gamestatus == "CLOSE") {
                        if (model.decodeCookie("")){
                            Vresult.show(result.valueOf().playeronescore, result.valueOf().playertwoscore,result.valueOf().playertwo.valueOf().username);
                        }else {
                            Vresult.show(result.valueOf().playertwoscore, result.valueOf().playeronescore,result.valueOf().playertwo.valueOf().username);
                        }

                    } else {
                        loop(counter - 1)
                    }
                })}
            , 1000);
    }else {
        controllerLobby.leavingGame("Es gab ein Problem und dein Gegner antwort nicht. Schau später in der Statistik!");
    }

}