import * as base from '../controller/base.js';
import * as apiCalls from '../controller/apiCalls.js';
import * as mainMenu from './mainMenu.js';
import {decodeCookie} from "../controller/apiCalls.js";
// import {showNewGameRounds} from "./scrap";

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
    document.getElementById("newGame").addEventListener("click", showNewGamePreload);
    document.getElementById("enterGame").addEventListener("click", function (){apiCalls.getOpenGames(showEnterGame)});
}

function showNewGamePreload(){
    base.clearStage();
    let backHome = document.createElement("div");
    backHome.setAttribute("id", "back home");
    backHome.setAttribute("class", "btn");
    let backHometext = document.createElement("p");
    backHometext.append("zurück");
    backHome.appendChild(backHometext);
    document.getElementsByTagName("nav")[0].appendChild(backHome);
    document.getElementById("back home").addEventListener("click", show);
    apiCalls.getCategory(displayCategory);
}

export function displayCategory(category){
    console.log('h')
    let brake1 = document.createElement("br");
    let brake2 = document.createElement("br");
    let brake3 = document.createElement("br");
    let from = document.createElement("form");
    let labe = document.createElement("label");
    labe.append("Wählen sie eine Kategorie");
    from.appendChild(labe);
    from.appendChild(brake1);

    let select = document.createElement("select");
    select.setAttribute("id", "category");


    for (let i = 0; i < category.length; i++) {
        console.log('jippi');
        let option = document.createElement("option");
        option.setAttribute("value", category[i].valueOf().categoryname);
        option.append(category[i].valueOf().categoryname);
        select.appendChild(option);
    }
    from.appendChild(select);
    from.appendChild(brake3);
    let label = document.createElement("label");
    label.append("Wählen sie die anzahl der Fragen");
    from.appendChild(label);
    from.appendChild(brake2);
    document.getElementsByTagName("article")[0].appendChild(from)
    apiCalls.getGameSize(displayRounds);
}
export function displayRounds (rounds){
    let brake = document.createElement("br");
    let select = document.createElement("select");
    select.setAttribute("id", "length");

    for (let i = 0; i < rounds.length; i++){
        let option = document.createElement("option");
        option.setAttribute("value", rounds[i].valueOf().rounds);
        option.append(rounds[i].valueOf().rounds);
        select.appendChild(option);
    }

    let input = document.createElement("input");
    input.setAttribute("type", "button");
    input.setAttribute("id", "submit");
    input.setAttribute("value", "Senden");

    document.getElementsByTagName("form")[0].appendChild(select);
    document.getElementsByTagName("form")[0].appendChild(brake);
    document.getElementsByTagName("form")[0].appendChild(input);


    document.getElementsByTagName("input")[0].addEventListener("click",()=>{
        console.log(
            document.getElementById("category").value + "  "+
            document.getElementById("length").value);
        apiCalls.createGame(
            document.getElementById("category").value,
            document.getElementById("length").value)
    });

}

export function showEnterGame(games){

    console.log(games);

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
    heading.append("Es wurden "+games.length+" gefunden.");

    document.getElementsByTagName("article")[0].appendChild(heading);

    let form = document.createElement("form");
    let label = document.createElement("label");
    label.setAttribute("for", "gameDropdown");
    label.append("Wählen Sie ein Speil aus");
    form.appendChild(label);
    let select = document.createElement("select");
    select.setAttribute("name", "gameDropdown");
    select.setAttribute("id", "selectGame")
    for(let i = 0; i< games.length; i++){

        let option = document.createElement("option");
        option.setAttribute("value", i);
        option.setAttribute("id", games[i].valueOf().id);
        option.append("Thema: "+games[i].valueOf().category.valueOf().categoryname+", Fragen: "+games[i].valueOf().rounds.rounds+", gegen: "+games[i].valueOf().playerone.valueOf().username);

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
        console.log(
        games[document.getElementById("selectGame").value].valueOf().id+" "+
        games[document.getElementById("selectGame").value].valueOf().playerone.valueOf().username +" "+
        decodeCookie("playername")+" "+
        "JOINED")

        let id = games[document.getElementById("selectGame").value].valueOf().id;
        let playerOne = games[document.getElementById("selectGame").value].valueOf().playerone.valueOf().username;
        let playerTwo = decodeCookie("playername");
        let status = "JOINED";
        let callback  = function (data){console.log(data)};
        console.log(id);
        apiCalls.updateGame(id,playerOne,playerTwo, status, callback);
    });
}