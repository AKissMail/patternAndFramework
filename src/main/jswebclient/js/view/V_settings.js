import * as controller from '../controller/C_controller.js';
import * as controllerSettings from '../controller/C_settings.js';
import * as mainMenu from './V_mainMenu.js';
import * as view from './V_view.js';
import * as updatePicture from './V_updatePicture.js';

/**
 * Hier wird das Einstellungsmenü in den DOM gehängt
 */
export function show() {
    controller.clearStage();


    document.getElementsByTagName("header")[0].appendChild(view.createNavBar("settingsBackHome", "Hauptmenü", "btn btn-light navbar-brand", "navbar navbar-light bg-primary"));

    let wrapper = view.createGenericElementWithOneAttribute("span", "class", "btnGrid");
    wrapper.appendChild(view.createButton("updatePicture","Profilbild ändern", "btn btn-primary"));
    wrapper.appendChild(view.createButton("updatePassword", "Passwort ändern", "btn btn-primary"));
    wrapper.appendChild(view.createButton("deleteStatistics","Statistik löschen", "btn btn-danger"));

    document.getElementsByTagName("article")[0].appendChild(wrapper);
    document.getElementById("updatePicture").addEventListener("click", updatePicture.show);
    document.getElementById("updatePassword").addEventListener("click", controllerSettings.updatePassword);
    document.getElementById("deleteStatistics").addEventListener("click", controllerSettings.deleteStatistics);
    document.getElementById("settingsBackHome").addEventListener("click", mainMenu.show);
}