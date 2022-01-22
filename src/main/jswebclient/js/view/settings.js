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
/**
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
    let button = document.createElement("button");
    button.setAttribute("class", "btn");
    button.setAttribute("id", "button");
    button.append("Hochladen");
    from.appendChild(input);
    from.appendChild(button);
    document.getElementsByTagName("article")[0].appendChild(from);

    const htmlForm = document.getElementById("uploadFrom");
    const inputFile = document.getElementById("inputFile");
    let myFile ={};
    let isFilePresent = true; // soll das so?
    let data;
    inputFile.addEventListener("change", async (event) => {
        myFile = {};
        isFilePresent = false;

        const filePromise = Object.entries(files).map((item) => {
            return new Promise((resolve, reject) => {
                const [index, file] = item;
                const reader = new FileReader();
                reader.readAsBinaryString(file);
                reader.onload = function (event) {
                    const fileKey = `${inputKey}${files.length > 1 ? `[${index}]` : ""}`;
                    myFile[fileKey] = `data:${file.type};base64,${btoa(event.target.result)}`;
                    resolve();
                };
                reader.onerror = function () {
                    console.log("can't read the file");
                    reject();
                };
            });
            Promise.all(filePromise)
                .then(()=>{
                isFilePresent = true;
                let newData =myFile;
            }
        })


        });


    htmlForm.addEventListener("submit", e => {
        alert("Test");
    })

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