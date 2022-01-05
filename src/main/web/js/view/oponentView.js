/**
 * Hier wird das Quiz getartet
 * @param nameA name des users
 * @param nameB name des gegners
 */
function oponentView_show(nameA, nameB) {
    base_clearStage();
    let backHome = document.createElement("div");
    let backHometext = document.createElement("p");
    backHometext.append("Kategorie");
    backHome.appendChild(backHometext);
    backHome.setAttribute("class", "btn");
    document.getElementsByTagName("nav")[0].appendChild(backHome);

    let heading = document.createElement("h1");
    heading.append("Gegner gefunden!");
    let iconPlayer = document.createElement("img");
    iconPlayer.setAttribute("src", "img/franz.gif");
    iconPlayer.setAttribute("class", "playerPic");
    let iconOpponent = document.createElement("img");
    iconOpponent.setAttribute("src", "img/hans.gif");
    iconOpponent.setAttribute("class", "playerPic")
    let name = document.createElement("p");
    name.append(nameA + " vs. " + nameB);

    let button = document.createElement("div");
    button.setAttribute("class", "btn");
    let buttotext = document.createElement("p");
    buttotext.append("Quiz starten");
    button.appendChild(buttotext);

    document.getElementsByTagName("article")[0].appendChild(heading);
    document.getElementsByTagName("article")[0].appendChild(iconPlayer);
    document.getElementsByTagName("article")[0].appendChild(iconOpponent);
    document.getElementsByTagName("article")[0].appendChild(name);
    document.getElementsByTagName("article")[0].appendChild(button);
    document.getElementsByClassName("btn")[0].addEventListener("click", mainMenu_show);
    document.getElementsByClassName("btn")[1].addEventListener("click", quiz_show);
}