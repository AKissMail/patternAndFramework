import * as apiCalls from './apiCalls.js';

import * as quiz from '../view/quiz.js';


export let killTimeOut = null;

/** --- Done ---
 * Diese function macht den DOM Leer und setzt ein minimal gerüst in den DOM ein. Dieses gerüst wird von
 * den _show function erwartet. Das letztendliche resultat schaut wie die ursprüngliche index.html aus.
 * Lediglich der onload event lissner ist nicht enthalten.
 */
export function clearStage() {
    //alle element im body werden entfernt
    document.querySelector("header").remove();
    document.querySelector("main").remove();
    document.querySelector("footer").remove();
    // die struktur wir neu aufgebaut
    let header = document.createElement("header");
    let nav = document.createElement("nav");
    let main = document.createElement("main");
    let article = document.createElement("article");
    let footer = document.createElement("footer");
    header.appendChild(nav);
    main.appendChild(article);
    document.getElementsByTagName("body")[0].appendChild(header);
    document.getElementsByTagName("body")[0].appendChild(main);
    document.getElementsByTagName("body")[0].appendChild(footer);
}

export function joinGame(game){
    let gameID    = game[3];
    let size      = game[2];
    let category  = game[1];
    let oponet    = game[0];
    let question = apiCalls.getMyQuestions(category, size);
    quiz.show(gameID, question, size, category, oponet);
}
export function startGame(category, size){
    console.log('Test');
    let gameID = apiCalls.createGame(category, size);
    let question = apiCalls.getMyQuestions(category, size);
    killTimeOut = null;
    quiz.show(gameID, question, size,category);
}