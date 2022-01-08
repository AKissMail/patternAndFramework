import * as base from '../controler/base.js';
import * as apiCalls from '../controler/apiCalls.js';
import * as mainMenu from './mainMenu.js';

/**
 * Hier wird das einstellungsmenü in den DOM gehängt
 */
export function show(){
    base.clearStage();
    let backHome = document.createElement("div");
    let backHomeText = document.createElement("p");
    backHomeText.append("Hauptmenü");
    backHome.appendChild(backHomeText);
    backHome.setAttribute("class", "btn");
    backHome.setAttribute("id", "backHome");
    document.getElementsByTagName("nav")[0].appendChild(backHome);

    let form = document.createElement("div");

    let buttonPicture = document.createElement("div");
    buttonPicture.setAttribute("class", "btn");
    buttonPicture.setAttribute("id", "updatePicture");
    let buttonPictureText = document.createElement("p");
    buttonPictureText.append("Profilbild ändern");
    buttonPicture.appendChild(buttonPictureText);

    let buttonPassword = document.createElement("div");
    buttonPassword.setAttribute("class", "btn");
    buttonPassword.setAttribute("id", "updatePassword");
    let buttonPasswordText = document.createElement("p");
    buttonPasswordText.append("Passwort ändern");
    buttonPassword.appendChild(buttonPasswordText);

    let buttonDeleteStatistics = document.createElement("div");
    buttonDeleteStatistics.setAttribute("class", "btn");
    buttonDeleteStatistics.setAttribute("id", "deleteStatistics");
    let buttonDeleteStatisticsText = document.createElement("p");
    buttonDeleteStatisticsText.append("Statistik löschen");
    buttonDeleteStatistics.appendChild(buttonDeleteStatisticsText);

    form.appendChild(buttonPicture);
    form.appendChild(buttonPassword);
    form.appendChild(buttonDeleteStatistics);
    document.getElementsByTagName("article")[0].appendChild(form);
    document.getElementById("updatePicture").addEventListener("click", updatePicture);
    document.getElementById("updatePassword").addEventListener("click", updatePassword);
    document.getElementById("deleteStatistics").addEventListener("click", deleteStatistics);
    document.getElementById("backHome").addEventListener("click", mainMenu.show);
}

/**
 * kleine Helfer function für den upload von Bildern
 */
function updatePicture() {
    base.clearStage();

    let backHome = document.createElement("div");
    let backHomeText = document.createElement("p");
    backHomeText.append("Abbrechen");
    backHome.appendChild(backHomeText);
    backHome.setAttribute("class", "btn");
    backHome.setAttribute("id", "backHome");
    document.getElementsByTagName("nav")[0].appendChild(backHome);

    let from = document.createElement("form");
    from.setAttribute("id", "uploadFrom");
    let input = document.createElement("input");
    input.setAttribute("id","inputFile");
    input.setAttribute("type", "file");
    let button = document.createElement("button");
    button.setAttribute("class", "btn");
    button.setAttribute("id", "button");
    button.append("Hochladen");
    from.appendChild(input);
    from.appendChild(button);
    document.getElementsByTagName("article")[0].appendChild(from);

    const htmlForm = document.getElementById("uploadFrom");
    const inputFile = document.getElementById("inputFile");

    htmlForm.addEventListener("submit", e => {
        e.preventDefault();
        const formData = new FormData();
        formData.append("newPicture", inputFile.files[0]);
        apiCalls.updatePicture(formData);
    })

    document.getElementById("backHome").addEventListener("click", show);
}
/**
 * kleine Helfer function für das Ändern eines Passwortes.
 */
function updatePassword() {
    let newPassword = prompt("Bitte geben Sie ein neues Passwort ein.");
    let newPassword2 = prompt("Bitte geben Sie das Passwort erneut ein.");
    if(newPassword === newPassword2){
        apiCalls.updatePassword(newPassword);
    }else{
        alert("Die Passwörter stimmen nicht überein!");
    }
}
/**
 * kleine Helfer function für das Zurücksetzen der Spielstatistik.
 */
function deleteStatistics() {
    if(confirm("Möchten Sie wirklich alle daten Löchen?")){
        apiCalls.deleteStatistics();
    }
}