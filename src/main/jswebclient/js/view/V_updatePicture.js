import * as controller from "../controller/C_updatePicture.js";
import * as base from "../controller/C_controller.js";
import * as view from "./V_view.js";
import * as settings from "./V_settings.js";

/**
 * Diese function hängt in den Dom die View zum Hochladen ein Profilbild.
 */
export function show() {
    base.clearStage();
    document.getElementsByTagName("header")[0].appendChild(view.createNavBar("updatePictureBackHome", "Zurück", "btn btn-light navbar-brand", "navbar navbar-light bg-primary"));

    document.getElementsByTagName("article")[0].appendChild(view.createGenericElementWithClassAndID("from", "btnGrid","uploadFrom"));
    document.getElementById("uploadFrom").appendChild(view.createInput("input", "file", "","","","inputFile"));
    document.getElementById("uploadFrom").appendChild(view.createButton("button", "Hochladen", "btn btn-primary"));

    document.getElementById("updatePictureBackHome").addEventListener("click", settings.show);
    controller.base64Picture();
}