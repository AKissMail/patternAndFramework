/**
 * Hier wird das ergebnis aus einer Runde angegeigt.
 * @param nameA string User Name
 * @param picA string Pfad zum Bild
 * @param nameB string Gegner Name
 * @param picB string Pfad zum Bild
 * @param result bool true = user hat gewonnen
 */
function result_show(nameA, picA, nameB, picB, result) {
    base_clearStage();
    let backHome = document.createElement("div");
    let backHometext = document.createElement("p");
    backHometext.append("Hauptmenü");
    backHome.appendChild(backHometext);
    backHome.setAttribute("class", "btn");
    document.getElementsByTagName("nav")[0].appendChild(backHome);

    let heading = document.createElement("h1");
    if (result === true) {
        heading.append("Du hast gewonnen!");
        heading.classList.add("green");
    } else {
        heading.append("Du hast verloren!");
        heading.classList.add("red");
    }

    let iconPlayer = document.createElement("img");
    iconPlayer.setAttribute("src", picA);
    iconPlayer.setAttribute("class", "playerPic")
    let iconOpponent = document.createElement("img");
    iconOpponent.setAttribute("src", picB);
    iconOpponent.setAttribute("class", "playerPic");
    let name = document.createElement("p");
    name.append(nameA + " vs. " + nameB);

    let button = document.createElement("div");
    button.setAttribute("id", "centerBtn");
    button.setAttribute("class", "btn");
    let buttonText = document.createElement("p");
    buttonText.append("Highscore anzeigen");
    button.appendChild(buttonText);

    document.getElementsByTagName("article")[0].appendChild(heading);
    document.getElementsByTagName("article")[0].appendChild(iconPlayer);
    document.getElementsByTagName("article")[0].appendChild(iconOpponent);
    document.getElementsByTagName("article")[0].appendChild(name);
    document.getElementsByTagName("article")[0].appendChild(button);
    document.getElementsByClassName("btn")[0].addEventListener("click", mainMenu_show);
    document.getElementsByClassName("btn")[1].addEventListener("click", score_show);
}