import {decodeCookie, getGameByID} from "../model/apiCalls.js";
import * as base from "../controller/base.js";
import * as view from "./view.js";
import * as mainMenu from "./mainMenu.js";
import * as apiCalls from "../model/apiCalls.js";
import * as result from "./result.js";

export function show() {
    base.clearStage();

    let backHome = view.createButton("", "Hauptmenü", "btn")
    let heading = view.createGenericText("h1", "Warten auf den Gegner");
    let subHeadding = view.createGenericText("p", "Dein Gegner hat noch nicht alle Fragen Beantwortet. Du Kannst hier warten oder später in der Statistik nachschauen.");
    let icon = view.createGenericElementWithTwoAttribute("img", "src", "img/settingsGear.png", "id", "rotation");

    document.getElementsByTagName("nav")[0].appendChild(backHome);
    document.getElementsByTagName("article")[0].appendChild(heading);
    document.getElementsByTagName("article")[0].appendChild(subHeadding);
    document.getElementsByTagName("article")[0].appendChild(icon);
    document.getElementsByClassName("btn")[0].addEventListener("click", function (){apiCalls.updateGame(decodeCookie("gameID"),"null","null","OPEN",mainMenu.show);});
    loop();
}
/**
 * Das ist eine Erneute abf age an die API mit einem Time-out. Diese Ruft sich Recursive selber auf so lange
 * bis ein Gegner gefunden ist.
 * @param apiCalls
 * */
function loop(){
    setTimeout(function(){
        getGameByID(apiCalls.decodeCookie("gameID"), function (newResponse){
            if(newResponse.valueOf().gamestatus == "CLOSE"){
                result.show(newResponse.valueOf().playeronescore, newResponse.valueOf().playertwoscore,newResponse.valueOf().playertwo.valueOf().username)
            }else {
                loop(newResponse);
            }
        });
    }, 1000);
}