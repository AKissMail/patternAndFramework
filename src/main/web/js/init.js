//export {clearStage,statUp, existCookie};
import {displayLobby} from './view/lobby.js';
import {displaySHighscore} from './view/highscore';
import {displayOponentView} from 'view/oponentView';
import {displaySHighscore} from 'view/highscore';







function existCookie(url) {
    return false;
}
function statUp(){
    console.log("startUp");
 if(existCookie("url")){
     if(existCookie("url")&&existCookie("jwToken")){
       alert("homeView();");
     }else{
        displayLogIn();
     }
 }
 else {
     console.log('displayLogIn();');
     displayLogIn();
}
}

/**
 * Setzt das Dokument zurück
 */
function clearStage(){

    document.querySelector("header").remove();
    document.querySelector("main").remove();
    document.querySelector("aside").remove();
    document.querySelector("footer").remove();

    let header = document.createElement("header");
    let nav = document.createElement("nav");
    let main = document.createElement("main");
    let article = document.createElement("article");
    let aside = document.createElement("aside");
    let footer = document.createElement("footer");
    header.appendChild(nav);
    main.appendChild(article);
    document.getElementsByName("body")[0].appendChild(header);
    document.getElementsByName("body")[0].appendChild(main);
    document.getElementsByName("body")[0].appendChild(aside);
    document.getElementsByName("body")[0].appendChild(footer);
}

