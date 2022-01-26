import * as base from "../controller/C_controller.js";
import * as view from "./V_view.js";
import * as logIn from "./V_logIn.js";
import * as register from "./V_register.js";
import {createButton} from "./V_view.js";


/**
 * Diese function ist die start View der App. Hier kann man aussuchen, ob man sich
 * registrieren möchte oder anmelden möchte.
 */
export function show() {
    base.clearStage();
    document.getElementsByTagName("article")[0].appendChild(createButton("register", "Registrieren", "btn"));
    document.getElementsByTagName("article")[0].appendChild(createButton("logIn", "Log In", "btn"));
    document.getElementsByTagName("main")[0].appendChild(view.createAside("div", "aside","","img", "logInLamp", "img/bulb.png","p", "", "MiB-Quiz"));
    addEventListener();
}

/**
 * Kleine helfer function die EventListener setzt.
 */
function addEventListener() {
    document.getElementById("logIn").addEventListener("click", logIn.show);
    document.getElementById("register").addEventListener("click", register.show);
}