import * as base from '../controller/base.js';
import * as apiCalls from '../controller/apiCalls.js';
import * as mainMenu from './mainMenu.js';
import * as choice from './choice.js';
import {logout} from "../controller/apiCalls.js";

/**
 * Hier wird das einstellungsmenü in den DOM gehängt
 */
export function show() {
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

function updatePicture() {
    base.clearStage();

    let backHome = document.createElement("div");
    let backHomeText = document.createElement("p");
    backHomeText.append("Abbrechen");
    backHome.appendChild(backHomeText);
    backHome.setAttribute("class", "btn");
    backHome.setAttribute("id", "backHome");
    document.getElementsByTagName("nav")[0].appendChild(backHome);

    let from = document.createElement("span");
    from.setAttribute("id", "uploadFrom");
    let input = document.createElement("input");
    input.setAttribute("id","inputFile");
    input.setAttribute("type", "file");
    input.setAttribute("accept", '"image/png, image/gif, image/jpeg"');
    let button = document.createElement("button");
    button.setAttribute("class", "btn");
    button.setAttribute("id", "button");
    button.append("Hochladen");
    from.appendChild(input);
    from.appendChild(button);
    document.getElementsByTagName("article")[0].appendChild(from);

    const toBase64 = file => new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
    });

    $('#button').click(function() {
        const uploadedFile = document.querySelector('#inputFile').files[0];
        toBase64(uploadedFile)
            .then(res => {
                let cutRes = res.split(',');
                let data = cutRes[1];
                console.log(data);
                apiCalls.updatePicture(data, function (){
                    console.log(res);
                    show();
                });

            })
            .catch(err => {
                alert("Das hat leider nicht geklappt" + err);
                console.log(err);
            })
    });

    document.getElementById("backHome").addEventListener("click", show);
}
/**
 * kleine Helfer function für das Ändern eines Passwortes.
 */
function updatePassword() {
    let oldPassword = prompt("Bitte geben Sie ihr altes password ein.")
    let newPassword = prompt("Bitte geben Sie ein neues Passwort ein.");
    let newPassword2 = prompt("Bitte geben Sie das Passwort erneut ein.");
    if(newPassword === newPassword2){
        apiCalls.updatePassword(oldPassword,newPassword, function (){logout(choice.show)});
    }else{
        alert("Die Passwörter stimmen nicht überein!");
    }
}
/**
 * kleine Helfer function für das Zurücksetzen der Spielstatistik.
 */
function deleteStatistics() {
    if(confirm("Möchten Sie wirklich alle daten Löschen?")){
        apiCalls.deleteStatistics(console.log);
    }
}