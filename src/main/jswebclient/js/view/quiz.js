import * as base from '../controller/base.js';
import * as apiCalls from '../controller/apiCalls.js';
import * as mainMenu from './mainMenu.js';
import * as lobby from './lobby.js';

/**
 * Hier werden die Fragen dargestellt.
 * @param gameID
 * @param question
 * @param size
 * @param category
 * @param oponet
 */
export function show(gameID, question, size, category, oponet) {
    let check = question[size-1]
    console.log(question[size-1]);
    if(check == null && check == undefined) {
        let result = apiCalls.getResult(gameID);
        if(result.done){
            lobby.show(result.gameID);
        }else{
            result.show(result);
        }

    }else {
        var start = new Date();
        base.clearStage();

        let backHome = document.createElement("div");
        let backHometext = document.createElement("p");
        backHometext.append("Beenden");
        backHome.appendChild(backHometext);
        backHome.setAttribute("class", "btn");
        document.getElementsByTagName("nav")[0].appendChild(backHome);

        let timer = document.createElement("div");

        timer.setAttribute("id", "timer");
        document.getElementsByTagName("nav")[0].appendChild(backHome);
        document.getElementsByTagName("nav")[0].appendChild(timer);

        let frage = document.createElement("h2");
        frage.append(question[size-1].question);
        document.getElementsByTagName("article")[0].appendChild(frage);


        let wapper = document.createElement("div");
        wapper.setAttribute("id", "wapper");
        let a = document.createElement("p");
        a.append(question[size-1].a);
        a.setAttribute("id", "a");
        a.setAttribute("class", "question");
        let b = document.createElement("p");
        b.append(question[size-1].b);
        b.setAttribute("id", "b");
        b.setAttribute("class", "question");
        let c = document.createElement("p");
        c.append(question[size-1].c);
        c.setAttribute("id", "c");
        c.setAttribute("class", "question");
        let d = document.createElement("p");
        d.append(question[size-1].d);
        d.setAttribute("id", "d");
        d.setAttribute("class", "question");
        wapper.appendChild(a);
        wapper.appendChild(b);
        wapper.appendChild(c);
        wapper.appendChild(d);
        document.getElementsByTagName("article")[0].appendChild(wapper);

        document.getElementById("a").addEventListener("click", ()=> {
            let now = new Date();
            let time = now - start;
            apiCalls.submitAnswer(true, time, gameID);
            show(gameID, question, size-1, category, oponet);
        });
        document.getElementById("b").addEventListener("click", ()=> {
            let now = new Date();
            let time = now - start;
            apiCalls.submitAnswer(true, time, gameID);
            show(gameID, question, size-1, category, oponet);
        });
        document.getElementById("c").addEventListener("click",()=> {
            let now = new Date();
            let time = now - start;
            apiCalls.submitAnswer(true, time, gameID);
            show(gameID, question, size-1, category, oponet);
        });
        document.getElementById("d").addEventListener("click", ()=> {
            let now = new Date();
            let time = now - start;
            apiCalls.submitAnswer(true, time, gameID);
            show(gameID, question, size-1, category, oponet);
        });

        document.getElementsByClassName("btn")[0].addEventListener("click", mainMenu.show);
    }
}