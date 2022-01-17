import * as base from '../controller/base.js';
import * as choice from './choice.js';
import * as apiCalls from '../controller/apiCalls.js';
import * as mainMenu from './mainMenu.js';

/**
 * Das ist die LogIn view. Sie stellt die login Maske dar.
 */
export function show() {
    base.clearStage();

    let backHome = document.createElement("div");
    backHome.setAttribute("id", "back home");
    backHome.setAttribute("class", "btn");
    let backHomeText = document.createElement("p");
    backHomeText.append("Zurück");
    backHome.appendChild(backHomeText);
    document.getElementsByTagName("article")[0].appendChild(backHome);

    let side = document.createElement("img");
    side.setAttribute("src", "img/bulb.png");
    side.setAttribute("id", "logInLamp");
    let sideP = document.createElement("p");
    sideP.append("Quizzz");

    let aside = document.createElement("div");
    aside.setAttribute("id", "aside");

    document.getElementsByTagName("main")[0].appendChild(aside);
    document.getElementById("aside").appendChild(side);
    document.getElementById("aside").appendChild(sideP);

    let form = document.createElement("form");

    let inputServer = document.createElement("input");
    inputServer.setAttribute("type", "text");
    inputServer.setAttribute("name", "inputServer");
    inputServer.setAttribute("placeholder", "Server URL");
    inputServer.setAttribute("id", "inputServer")

    let inputUser = document.createElement("input");
    inputUser.setAttribute("type", "text");
    inputUser.setAttribute("name", "userName");
    inputUser.setAttribute("placeholder", "Nutzername");
    inputUser.setAttribute("id", "userName")

    let inputPassword = document.createElement("input");
    inputPassword.setAttribute("type", "password");
    inputPassword.setAttribute("name", "password");
    inputPassword.setAttribute("id", "password");
    inputPassword.setAttribute("placeholder", "Password");

    let radioA = document.createElement("input");
    radioA.setAttribute("type", "radio");
    radioA.setAttribute("id", "newUser");
    radioA.setAttribute("value", "newUser");
    radioA.setAttribute("name", "modus");
    let radioALabel = document.createElement("label");
    radioALabel.setAttribute("for", "newUser");
    radioALabel.innerHTML = "Neuen Account anlegen";

    let radioB = document.createElement("input");
    radioB.setAttribute("type", "radio");
    radioB.setAttribute("id", "logIn");
    radioB.setAttribute("value", "logIn");
    radioB.setAttribute("name", "modus");
    let radioBLabel = document.createElement("label");
    radioBLabel.setAttribute("for", "logIn");
    radioBLabel.innerHTML = "Nutzer einloggen"

    let button = document.createElement("input");
    button.setAttribute("type", "submit");
    button.setAttribute("value", "Senden");
    button.setAttribute("id", "button");
    //button.setAttribute("onClick", "runLogIn");

    let brake = document.createElement("br");

    form.appendChild(inputUser);
    form.appendChild(inputPassword);
    form.appendChild(brake);
    form.appendChild(button);
    document.getElementsByTagName("article")[0].appendChild(form);
    addEventListener();
}

/**
 * Kleine Helferfunktion um die EventListener zu setzten.
 */
function addEventListener(){
    document.getElementById("back home").addEventListener("click", choice.show);
    document.getElementById("button").addEventListener("click", runLogIn);
    console.log('addEventListener')

}
/**
 *  Das ist das bissen magic in der Login maske. @todo hier muss der Login umgesetzt werden und in Base verschoben werden.
 */
function runLogIn() {
    let userName = document.getElementById("userName").value;
    let password = document.getElementById("password").value;
    let bool = apiCalls.logInUser(userName, password);
    if(bool){
        mainMenu.show();
    }else{
        alert("Log In ist fehlgeschlagen!");
    }
}