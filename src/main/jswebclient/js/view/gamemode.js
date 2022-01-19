import * as base from '../controller/base.js';
import * as apiCalls from '../controller/apiCalls.js';

import * as mainMenu from './mainMenu.js';

let openGames = "";
let catigroy = "";
let lenght = 0;

export function show() {
    base.clearStage();

    let backHome = document.createElement("div");
    backHome.setAttribute("id", "back home");
    backHome.setAttribute("class", "btn");
    let backHometext = document.createElement("p");
    backHometext.append("Hauptmenü");
    backHome.appendChild(backHometext);
    document.getElementsByTagName("nav")[0].appendChild(backHome);
    document.getElementById("back home").addEventListener("click", mainMenu.show);

    let btnA = document.createElement("div");
    btnA.setAttribute("class","btn");
    btnA.setAttribute("id", "newGame");
    let btnAText = document.createElement("p");
    btnAText.append("neues Spiel erstellen");
    btnA.appendChild(btnAText);

    let btnB = document.createElement("div");
    btnB.setAttribute("class","btn");
    btnB.setAttribute("id", "enterGame");
    let btnBText = document.createElement("p");
    btnBText.append("eine Spiel beitreten");
    btnB.appendChild(btnBText)

    document.getElementsByTagName("article")[0].appendChild(btnA);
    document.getElementsByTagName("article")[0].appendChild(btnB);
    addShowEventListener();
}
function addShowEventListener() {
    document.getElementById("newGame").addEventListener("click", showNewGame);
    document.getElementById("enterGame").addEventListener("click", showEnterGame);
};

function showNewGame() {
    base.clearStage();
    catigroy = apiCalls.getCategory();
    lenght = apiCalls.getGameSize();

    let backHome = document.createElement("div");
    backHome.setAttribute("id", "back home");
    backHome.setAttribute("class", "btn");
    let backHometext = document.createElement("p");
    backHometext.append("zurück");
    backHome.appendChild(backHometext);
    document.getElementsByTagName("nav")[0].appendChild(backHome);
    document.getElementById("back home").addEventListener("click", show);

    let brake1 = document.createElement("br");
    let brake2 = document.createElement("br");
    let brake3 = document.createElement("br");
    let brake4 = document.createElement("br");
    let from = document.createElement("form");
    let labe = document.createElement("label");
    labe.append("Wählen sie eine Kategorie");
    from.appendChild(labe);
    from.appendChild(brake1);

    let select = document.createElement("select");
    select.setAttribute("id", "category");

    for (let i = 0; i < catigroy.length; i++) {
        let option = document.createElement("option");
        option.setAttribute("value", catigroy[i]);
        option.append(catigroy[i])
        select.appendChild(option);
    }
    from.appendChild(select);
    from.appendChild(brake3);
    let label2 = document.createElement("label");
    label2.append("Wählen sie die anzahl der Fragen");
    from.appendChild(label2);
    from.appendChild(brake2);

    let select2 = document.createElement("select");
    select2.setAttribute("id", "lenth");

    for (let i = 0; i < lenght.length; i++){
        let option2 = document.createElement("option");
        option2.setAttribute("value", lenght[i].toString());
        option2.append(lenght[i].toString());
        select2.appendChild(option2);
    }

    let input = document.createElement("input");
    input.setAttribute("type", "button");
    input.setAttribute("id", "submit");
    input.setAttribute("value", "Senden");

    from.appendChild(select2);
    from.appendChild(brake4);
    from.appendChild(input);

    document.getElementsByTagName("article")[0].appendChild(from);
    document.getElementsByTagName("input")[0].addEventListener("click",()=>{
        console.log(document.getElementById("category").value, document.getElementById("lenth").value);
        base.startGame(document.getElementById("category").value, document.getElementById("lenth").value);
    });
}

function showEnterGame(){
    this.games = apiCalls.getOpenGames();
    console.log(this.games.length);
    console.log(this.games[1][1]);

    base.clearStage();
    let backHome = document.createElement("div");
    backHome.setAttribute("id", "back home");
    backHome.setAttribute("class", "btn");
    let backHometext = document.createElement("p");
    backHometext.append("zurück");
    backHome.appendChild(backHometext);
    document.getElementsByTagName("nav")[0].appendChild(backHome);
    document.getElementById("back home").addEventListener("click", show);

    let heading  = document.createElement("h1");
    heading.append("Es wurden "+this.games.length+" gefunden.");

    document.getElementsByTagName("article")[0].appendChild(heading);

    let form = document.createElement("form");
    let label = document.createElement("label");
    label.setAttribute("for", "gameDropdown");
    label.append("Wählen Sie ein Speil aus");
    form.appendChild(label);
    let select = document.createElement("select");
    select.setAttribute("name", "gameDropdown");
    select.setAttribute("id", "selectGame")
    for(let i = 0; i< this.games.length; i++){

        let option = document.createElement("option");
        option.setAttribute("value", i);
        option.setAttribute("id", this.games[i][3]);
        option.append("Thema: "+this.games[i][1]+", Fragen: "+this.games[i][2]+", gegen: "+this.games[i][0]);

        select.appendChild(option);
    }
    form.appendChild(select);
    let brake = document.createElement("br");
    form.appendChild(brake);
    let input = document.createElement("input");
    input.setAttribute("type", "button");
    input.setAttribute("value", "Spiel starten");
    input.setAttribute("id", "submit");
    form.appendChild(input);
    document.getElementsByTagName("article")[0].appendChild(form);

    document.getElementsByTagName("input")[0].addEventListener("click",()=>{
        let data = this.games[document.getElementById("selectGame").value]
        console.log(data);
        base.joinGame(data);
    });
}