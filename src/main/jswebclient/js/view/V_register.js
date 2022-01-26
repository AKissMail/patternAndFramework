import * as base from "../controller/C_controller.js";
import * as choice from "./V_start.js";
import * as apiCalls from "../model/M_model.js";
import * as view from "./V_view.js";

/**
 * Diese function setzt das Formular für die Registrierung in den DOM
 */
export function show(){
    base.clearStage();

    document.getElementsByTagName("article")[0].appendChild(view.createButton("back home", "Zurück", "btn"));
    document.getElementsByTagName("article")[0].appendChild(view.createInput("input", "text", "userName", "", "Nutzername", "userName"));
    document.getElementsByTagName("article")[0].appendChild(view.createInput("input", "password", "password", "","Password","password"));
    document.getElementsByTagName("article")[0].appendChild(view.createInput("input", "password","passwordVerify","","Password wiederholen","passwordVerify"));
    document.getElementsByTagName("article")[0].appendChild(view.createInput("input", "button", "", "Senden", "", "button"));
    document.getElementsByTagName("main")[0].appendChild(view.createAside("div", "aside","","img", "logInLamp", "img/bulb.png","p", "", "MiB-Quiz"));
    addEventListener();
}
/**
 * Kleine helfer function die EventListener setzt.
 */
function addEventListener(){
    document.getElementById("back home").addEventListener("click", choice.show);
    document.getElementById("button").addEventListener("click", function (){
        if(document.getElementById("password").value === document.getElementById("passwordVerify").value){
            apiCalls.createPlayer(document.getElementById("userName").value, document.getElementById("password").value, choice.show);
        }else{
            alert("Die Passwörter stimmen nicht über ein!");
        }
    });
}