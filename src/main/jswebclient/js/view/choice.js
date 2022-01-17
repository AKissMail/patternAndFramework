import * as base from "../controller/base.js";
import * as logIn from "./logIn.js";
import * as register from "./register.js";


/**
 * Diese function ist die start View der App. Hier kann man aussuchen, ob man sich
 * registrieren möchte oder anmelden möchte.
 */
export function show() {
    console.log("show");

    base.clearStage();

    let logIn = document.createElement("div");
    logIn.setAttribute("id", "logIn");
    logIn.setAttribute("class", "btn");
    let logInText = document.createElement("p");
    logInText.append("Log In");
    logIn.appendChild(logInText);
    document.getElementsByTagName("article")[0].appendChild(logIn);

    let register = document.createElement("div");
    register.setAttribute("id", "register");
    register.setAttribute("class", "btn");
    let registertext = document.createElement("p");
    registertext.append("Registrieren");
    register.appendChild(registertext);
    document.getElementsByTagName("article")[0].appendChild(register);

    let side = document.createElement("img");
    side.setAttribute("src", "img/bulb.png");
    side.setAttribute("id", "logInLamp");
    let sideP = document.createElement("p");
    sideP.append("Quizz");

    let aside = document.createElement("div");
    aside.setAttribute("id", "aside");

    document.getElementsByTagName("main")[0].appendChild(aside);
    document.getElementById("aside").appendChild(side);
    document.getElementById("aside").appendChild(sideP);
    addEvetLissner();
}
/**
 * Kleine helfer function die EventListener setzt.
 */
function addEvetLissner(){
    document.getElementById("logIn").addEventListener("click", logIn.show);
    document.getElementById("register").addEventListener("click", register.show);
    console.log('addEventListener, show done');
}