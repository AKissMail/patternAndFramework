import * as base from '../controller/base.js';
import * as apiCalls from '../controller/apiCalls.js';
import * as mainMenu from './mainMenu.js';
import * as resultView from './result.js';

/**
 * Hier wird die Lobby angezeigt, welche während der Suche nach einem Gegner angezeigt wird.
 */
export function show(gameID) {
    base.clearStage();
    let backHome = document.createElement("div");
    let backHometext = document.createElement("p");
    backHometext.append("Hauptmenü");
    backHome.appendChild(backHometext);
    backHome.setAttribute("class", "btn");
    document.getElementsByTagName("nav")[0].appendChild(backHome);

    let heading = document.createElement("h1");
    let subHeadding = document.createElement("p");
    heading.append("Warten auf den Gegner");
    subHeadding.append("Netzwerk wird durchsucht ...");
    let icon = document.createElement("img");
    icon.setAttribute("src", "img/settingsGear.png");
    icon.setAttribute("id", "rotation");

    let button = document.createElement("div");
    let p = document.createElement("p");
    p.append("neu laden");
    button.setAttribute("id", "centerBtn");
    button.setAttribute("class", "btn");
    button.appendChild(p);

    document.getElementsByTagName("article")[0].appendChild(heading);
    document.getElementsByTagName("article")[0].appendChild(subHeadding);
    document.getElementsByTagName("article")[0].appendChild(icon);
    document.getElementsByTagName("article")[0].appendChild(button);
    document.getElementsByClassName("btn")[0].addEventListener("click", mainMenu.show);
    document.getElementById("centerBtn").addEventListener("click", ()=>{
        let result =apiCalls.getResult(gameID);
        if(result.done){
            resultView.show(result);
        }else{
            alert('Es liegen noch keine Ergebnisse vor.')
        }
    })
}