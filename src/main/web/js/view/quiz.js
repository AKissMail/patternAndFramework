// quiz.js
/**
 * Hier wird eine Frage angezeit sowie die Antwort vom nutzer entgegengenommen @todo 1. der Counddown, 2 Api
 */
function quiz_show() {
    base_clearStage();
    let question = {
        question: "Was ist Nass?",
        a: "Annanass",
        b: "Bananen",
        c: "Kuchen",
        d: "Wasser"
    };

    let backHome = document.createElement("div");
    let backHometext = document.createElement("p");
    backHometext.append("Beenden");
    backHome.appendChild(backHometext);
    backHome.setAttribute("class", "btn");
    document.getElementsByTagName("nav")[0].appendChild(backHome);

    let timer = document.createElement("div");

    timer.setAttribute("id", "timer"); //todo fancy timer shit
    document.getElementsByTagName("nav")[0].appendChild(backHome);
    document.getElementsByTagName("nav")[0].appendChild(timer);

    let frage = document.createElement("h2");
    frage.append(question.question);
    document.getElementsByTagName("article")[0].appendChild(frage);

    let wapper = document.createElement("div");
    wapper.setAttribute("id", "wapper");
    let a = document.createElement("p");
    a.append(question.a);
    a.setAttribute("id", "a");
    a.setAttribute("class", "question");
    let b = document.createElement("p");
    b.append(question.b);
    b.setAttribute("id", "b");
    b.setAttribute("class", "question");
    let c = document.createElement("p");
    c.append(question.c);
    c.setAttribute("id", "c");
    c.setAttribute("class", "question");
    let d = document.createElement("p");
    d.append(question.d);
    d.setAttribute("id", "d");
    d.setAttribute("class", "question");
    wapper.appendChild(a);
    wapper.appendChild(b);
    wapper.appendChild(c);
    wapper.appendChild(d);
    document.getElementsByTagName("article")[0].appendChild(wapper);

    let hr = document.createElement("hr");
    let footerText = document.createElement("p");
    footerText.append(question.footerInfo);

    document.getElementById("a").addEventListener("click", quiz_sendAnserA);
    document.getElementById("b").addEventListener("click", quiz_sendAnserB);
    document.getElementById("c").addEventListener("click", quiz_sendAnserC);
    document.getElementById("d").addEventListener("click", quiz_sendAnserD);
    document.getElementsByClassName("btn")[0].addEventListener("click", mainMenu_show);
}
// Antworten Senden
function quiz_sendAnserA() { //todo mit dem server sprechen
    result_show("Hans","","Dampf","", true);
}
function quiz_sendAnserB() { //todo mit dem server sprechen
    result_show("Hans","","Dampf","", false);
}
function quiz_sendAnserC() { //todo mit dem server sprechen
    result_show("Hans","","Dampf","", false);
}
function quiz_sendAnserD() { //todo mit dem server sprechen
    result_show("Hans","","Dampf","", false);
}