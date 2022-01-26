import * as apiCalls from "../model/M_model.js";
import * as mainMenu from "../view/V_mainMenu.js";

/**
 *  Diese function holt aus 2 gegeben Elementen den Inhalt und Log damit den nutzer ein.
 */
export function runLogIn(idUserName, idPassword) {
    let userName = document.getElementById(idUserName).value;
    let password = document.getElementById(idPassword).value;
    apiCalls.logInUser(userName, password, mainMenu.show);
}