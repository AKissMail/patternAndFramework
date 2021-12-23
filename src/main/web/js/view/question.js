


/**
 * @ todo in API
 * @param a1
 * @returns {undefined}
 */
function sendAnser(a1) {
    return true;
}

/**
 * @todo In API!
 */
function goHome() {

}

/**
 *
 * @param question
 */
function displayQuestion(question) {

    let backHome = document.createElement("p");
    backHome.append("Beenden");
    backHome.setAttribute("id", "backHome")
    let timer = document.createElement("div");
    timer.setAttribute("id", "timer");
    document.getElementsByTagName("nav")[0].appendChild(backHome);
    document.getElementsByTagName("nav")[0].appendChild(timer);



    let wapper = document.createElement("div");
    wapper.setAttribute("id", "wapper");
    let a = document.createElement("p");
    a.append(question.a);
    a.setAttribute("id", "a");
    let b = document.createElement("p");
    b.append(question.b);
    b.setAttribute("id", "b");
    let c = document.createElement("p");
    c.append(question.c);
    let d = document.createElement("p");
    d.append(question.d);
    wapper.appendChild(a);
    wapper.appendChild(b);
    wapper.appendChild(c);
    wapper.appendChild(d);
    document.getElementsByTagName("article")[0].appendChild(wapper);

    let hr = document.createElement("hr");
    let footerText= document.createElement("p");
    footerText.append(question.footerInfo);
    document.

    document.getElementById("a").addEventListener("click", sendAnser("a"));
    document.getElementById("a").addEventListener("click", sendAnser("b"));
    document.getElementById("a").addEventListener("click", sendAnser("c"));
    document.getElementById("a").addEventListener("click", sendAnser("d"));
    document.getElementsByIs("backHome").addEventListener("click", goHome);
}