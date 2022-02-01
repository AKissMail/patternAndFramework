import * as base from "../controller/C_controller.js";
import * as choice from "./V_start.js";
import * as apiCalls from "../model/M_model.js";
import * as view from "./V_view.js";

/**
 * Diese function setzt das Formular für die Registrierung in den DOM
 */
export function show(){
    base.clearStage();

    document.getElementsByTagName("main")[0].setAttribute("class", "mainGrid");
    document.getElementsByTagName("header")[0].appendChild(view.createNavBar("registrationBackHome", "Zurück", "btn btn-light navbar-brand","navbar navbar-light bg-primary" ));

    let form = document.createElement("form");
    form.appendChild(view.createInput("input", "text", "userName", "", "Nutzername", "userName"));
    form.appendChild(view.createInput("input", "password", "password", "","Password","password"));
    form.appendChild(view.createInput("input", "password","passwordVerify","","Password wiederholen","passwordVerify"));
    form.appendChild(view.createInput("input", "button", "", "Senden", "", "button", "btn btn-primary"));

    document.getElementsByTagName("article")[0].appendChild(form);
    document.getElementsByTagName("main")[0].appendChild(view.createAside("div", "aside","","img", "logInLamp", "img/bulb.png","p", "", "MiB-Quiz"));
    addEventListener();
}
/**
 * Kleine helfer function die EventListener setzt.
 */
function addEventListener(){
    document.getElementById("registrationBackHome").addEventListener("click", choice.show);
    document.getElementById("button").addEventListener("click", function (){
        if(document.getElementById("password").value === document.getElementById("passwordVerify").value){
            apiCalls.createPlayer(document.getElementById("userName").value, document.getElementById("password").value, (r, err) => {
                if(err===null){
                    choice.show();
                }
                else {
                    alert("Registrierung fehlgeschlagen");
                }
            });
        }else{
            alert("Die Passwörter stimmen nicht über ein!");
        }
    });
}