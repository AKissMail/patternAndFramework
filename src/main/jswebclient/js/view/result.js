import * as base from '../controller/base.js';
import * as mainMenu from './mainMenu.js';
import * as statistic from './statistic.js';


/**
 * Stellt das ergebnis eines Spiel da.
 * @param id datenobjekt von der Api
 */
export function show(id) {
    base.clearStage();
    console.log(id);
    let backHome = document.createElement("div");
    let backHometext = document.createElement("p");
    backHometext.append("Hauptmenü");
    backHome.appendChild(backHometext);
    backHome.setAttribute("class", "btn");
    document.getElementsByTagName("nav")[0].appendChild(backHome);

    let heading = document.createElement("h1");
    if (id.localPoint > id.remodePoint) {
        heading.append("Du hast gewonnen!");
        heading.classList.add("green");
    } else if(id.localPoint < id.remodePoint){
        heading.append("Du hast verloren!");
        heading.classList.add("red");
    }else{
        heading.append("Es ist unenschiden ausgegengen!");
    }

    let name = document.createElement("p");
    name.append("Du vs. " + id.nameOponent);

    let button = document.createElement("div");
    button.setAttribute("id", "centerBtn");
    button.setAttribute("class", "btn");
    let buttonText = document.createElement("p");
    buttonText.append("Highscore anzeigen");
    button.appendChild(buttonText);

    document.getElementsByTagName("article")[0].appendChild(heading);
    document.getElementsByTagName("article")[0].appendChild(name);
    document.getElementsByTagName("article")[0].appendChild(button);
    document.getElementsByClassName("btn")[0].addEventListener("click", mainMenu.show);
    document.getElementsByClassName("btn")[1].addEventListener("click", statistic.show);
}