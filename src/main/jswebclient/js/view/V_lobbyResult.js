import {decodeCookie } from "../model/M_model.js";
import {clearStage} from "../controller/C_controller.js";
import {loop} from "../controller/C_lobby.js";
import * as view from "./V_view.js";
import * as mainMenu from "./V_mainMenu.js";
import * as apiCalls from "../model/M_model.js";
import * as result from "./V_result.js";


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
    loop(150,"CLOSE", ".",()=>{result.show(result.valueOf().playeronescore, result.valueOf().playertwoscore,result.valueOf().playertwo.valueOf().username)}, ()=>{});
}
