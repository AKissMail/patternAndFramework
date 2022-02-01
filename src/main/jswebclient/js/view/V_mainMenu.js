import * as base from '../controller/C_controller.js';
import * as apiCalls from '../model/M_model.js';
import * as view from './V_view.js';

import * as gameModus from './V_gameModus.js';
import * as settings from './V_settings.js';
import * as highscore from './V_highscore.js';
import * as statistic from './V_statistic.js';
import * as choice from "./V_start.js";
/**
 * Diese Funktion zeigt das Hauptmenü an.
 *
 */
export function show() {
    base.clearStage();
    document.getElementsByTagName("header")[0].appendChild(view.createNavBar("mainMenuLogout", "Abmelden", "btn btn-light navbar-brand", "navbar navbar-light bg-primary"));

    document.getElementsByTagName("article")[0].appendChild(view.createGenericElementWithOneAttribute("img","id", "thumbnail"));
    document.getElementsByTagName("article")[0].appendChild(view.createGenericText("h2","Hallo "+ apiCalls.decodeCookie("playername")));
    let wrapper = view.createGenericElementWithOneAttribute("span","class", "btnGrid");
    wrapper.appendChild(view.createButton("startGame", "Quiz starten", "btn btn-primary"));
    wrapper.appendChild(view.createButton("settings", "Einstellungen", "btn btn-primary"));
    wrapper.appendChild(view.createButton("highscore", "Highscore", "btn btn-primary"));
    wrapper.appendChild(view.createButton("statistic", "Spielstatistik", "btn btn-primary"));
    document.getElementsByTagName("article")[0].appendChild(wrapper);

    apiCalls.getPicture(apiCalls.decodeCookie("playername"), function (response){
        let pic= "data:image/gif;base64,"+ response;
        document.getElementById("thumbnail").setAttribute("src", pic);
    })
    addEventListener();
}

/**
 * kleine helfer function, welch die eventListener setzt.
 */
function addEventListener(){
    document.getElementById("mainMenuLogout").addEventListener("click", function(){apiCalls.logout(choice.show)});
    document.getElementById("startGame").addEventListener("click", gameModus.show);
    document.getElementById("settings").addEventListener("click",settings.show);
    document.getElementById("highscore").addEventListener("click", highscore.show);
    document.getElementById("statistic").addEventListener("click", statistic.show);
}