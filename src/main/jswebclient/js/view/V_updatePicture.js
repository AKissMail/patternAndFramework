import * as controller from "../controller/C_updatePicture.js";
import * as base from "../controller/C_controller.js";
import * as view from "./V_view.js";
import * as settings from "./V_settings.js";

/**
 * Diese function hängt in den Dom die View zum Hochladen ein Profilbild.
 */
export function show() {
    base.clearStage();
    document.getElementsByTagName("nav")[0].appendChild(view.createButton("backHome", "Zurück", "btn"));
    document.getElementsByTagName("article")[0].appendChild(view.createGenericElementWithClassAndID("span", "","uploadFrom"));
    document.getElementsByTagName("span")[0].appendChild(view.createInput("input", "file", "","","","inputFile"));
    document.getElementsByTagName("span")[0].appendChild(view.createButton("button", "Hochladen", "btn"));
    document.getElementById("backHome").addEventListener("click", settings.show);
    controller.base64Picture();
}