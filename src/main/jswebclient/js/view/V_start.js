import * as base from "../controller/C_controller.js";
import * as view from "./V_view.js";
import * as logIn from "./V_logIn.js";
import * as register from "./V_register.js";

/**
 * Diese function ist die start View der App. Hier kann man aussuchen, ob man sich
 * registrieren möchte oder anmelden möchte.
 */
export function show() {
    base.clearStage();
    document.getElementsByTagName("main")[0].setAttribute("class", "mainGrid");
    let wrapper = view.createGenericElementWithOneAttribute("span", "class","btnGrid");
    wrapper.appendChild(view.createButton("startRegister", "Registrieren", "btn btn-primary"));
    wrapper.appendChild(view.createButton("startLogIn", "Log In", "btn btn-primary"));

    document.getElementsByTagName("header")[0].appendChild(view.createNavBar("", "MiB-Quiz", "navbar-brand", "navbar navbar-dark bg-primary"));
    document.getElementsByTagName("article")[0].appendChild(wrapper);
    document.getElementsByTagName("main")[0].appendChild(view.createAside("div", "aside","","img", "logInLamp", "img/bulb.png","p", "", "MiB-Quiz"));
    document.getElementById("startLogIn").addEventListener("click", logIn.show);
    document.getElementById("startRegister").addEventListener("click", register.show);
}