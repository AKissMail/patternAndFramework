import {decodeCookie } from "../model/M_model.js";
import {clearStage} from "../controller/C_controller.js";
import * as view from "./V_view.js";
import * as mainMenu from "./V_mainMenu.js";
import * as apiCalls from "../model/M_model.js";
import * as Vresult from "./V_result.js";
import * as model from "../model/M_model.js";
import * as controllerLobby from "../controller/C_lobby.js";


export function show() {
    clearStage();
    let backHome = view.createButton("", "Hauptmenü", "btn")
    let heading = view.createGenericText("h1", "Warten auf den Gegner");
    let subHeadding = view.createGenericText("p", "Dein Gegner hat noch nicht alle Fragen Beantwortet. Du Kannst hier warten oder später in der Statistik nachschauen.");
    let icon = view.createGenericElementWithTwoAttribute("img", "src", "img/settingsGear.png", "id", "rotation");
    document.getElementsByTagName("nav")[0].appendChild(backHome);
    document.getElementsByTagName("article")[0].appendChild(heading);
    document.getElementsByTagName("article")[0].appendChild(subHeadding);
    document.getElementsByTagName("article")[0].appendChild(icon);
    document.getElementsByClassName("btn")[0].addEventListener("click", function (){apiCalls.updateGame(decodeCookie("gameID"),"null","null","OPEN",mainMenu.show);});
    loop(150,);
}
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
        controllerLobby.leavingGame("Es wurde kein Gegner gefunden.");
    }

}
// "CLOSE", ".",()=>{result.show(result.valueOf().playeronescore, result.valueOf().playertwoscore,result.valueOf().playertwo.valueOf().username)}, ()=>{}