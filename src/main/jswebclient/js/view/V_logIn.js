import * as C_logIn from '../controller/C_logIn.js';
import * as controller from '../controller/C_controller.js';
import * as choice from './V_start.js';
import * as view from "./V_view.js";

/**
 * Das ist die LogIn view. Sie stellt die login Maske dar.
 */
export function show() {
    controller.clearStage();
    document.getElementsByTagName("article")[0].appendChild(view.createButton("back home", "Zurück", "btn"));
    document.getElementsByTagName("article")[0].appendChild(view.createInput("input","text", "userName","" ,"Nutzername", "userName"));
    document.getElementsByTagName("article")[0].appendChild(view.createInput("input","password", "password", "", "Password", "password"));
    document.getElementsByTagName("article")[0].appendChild(view.createInput("input","submit", "", "Senden", "", "button"));
    document.getElementsByTagName("main")[0].appendChild(view.createAside("div", "aside","","img", "logInLamp", "img/bulb.png","p", "", "MiB-Quiz"));
    document.getElementById("back home").addEventListener("click", choice.show);
    document.getElementById("button").addEventListener("click", function (){C_logIn.runLogIn("userName","password")});
}