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
    document.getElementsByTagName("nav")[0].appendChild(view.createButton("backHome", "Hauptmenü", "btn"));
    document.getElementsByTagName("article")[0].appendChild(view.createButton("updatePicture","Profilbild ändern", "btn"));
    document.getElementsByTagName("article")[0].appendChild(view.createButton("updatePassword", "Passwort ändern", "btn"));
    document.getElementsByTagName("article")[0].appendChild(view.createButton("deleteStatistics","Statistik löschen", "btn"));
    document.getElementById("updatePicture").addEventListener("click", updatePicture.show);
    document.getElementById("updatePassword").addEventListener("click", controllerSettings.updatePassword);
    document.getElementById("deleteStatistics").addEventListener("click", controllerSettings.deleteStatistics);
    document.getElementById("backHome").addEventListener("click", mainMenu.show);
}