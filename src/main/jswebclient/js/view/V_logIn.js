import * as C_logIn from '../controller/C_logIn.js';
import * as controller from '../controller/C_controller.js';
import * as choice from './V_start.js';
import * as view from "./V_view.js";

/**
 * Das ist die LogIn view. Sie stellt die login Maske dar.
 */
export function show() {
    controller.clearStage();
    document.getElementsByTagName("main")[0].setAttribute("class", "mainGrid");
    let form = document.createElement("form");
    form.appendChild(view.createInput("input","text", "userName","" ,"Nutzername", "userName"));
    form.appendChild(view.createInput("input","password", "password", "", "Password", "password"));
    form.appendChild(view.createInput("input","submit", "", "Senden", "", "LoginButton", "btn btn-primary"));
    document.getElementsByTagName("header")[0].appendChild(view.createNavBar("loginBackHome", "Zurück", "btn btn-light navbar-brand", "navbar navbar-light bg-primary"));
    document.getElementsByTagName("article")[0].appendChild(form);
    document.getElementsByTagName("main")[0].appendChild(view.createAside("div", "aside","","img", "logInLamp", "img/bulb.png","p", "", "MiB-Quiz"));
    document.getElementById("loginBackHome").addEventListener("click", choice.show);
    document.getElementById("LoginButton").addEventListener("click", function (event){
        event.preventDefault();
        C_logIn.runLogIn("userName","password"); });
}