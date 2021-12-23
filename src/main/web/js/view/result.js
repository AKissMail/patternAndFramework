export {displayResultWin};

/**
 * Diese Funktion zeigt das Ergebnis eines Speils
 * @param nameA Name des localPlayer
 * @param nameB Name des RemodePalyer
 * @param result Boolean true -> localPlayer hat gewonnen. false -> localPlayer hat verloren
 */
function displayResultWin(nameA, nameB, result){

    let backHome = document.createElement("p");
    backHome.append("Kategorie");
    document.getElementsByTagName("nav")[0].appendChild(backHome);

    let heading = document.createElement("h1");
    if(result === true){
        heading.append("Du hast gewonnen!");
        heading.classList.add("green");
    }else{
        heading.append("Du hast verloren!");
        heading.classList.add("red");
    }

    let iconPlayer = document.createElement("img");
    iconPlayer.setAttribute("src", "img/iconPlayer.png");
    let iconOpponent = document.createElement("img");
    iconOpponent.setAttribute("src", "img/iconOpponent.png");
    let name =document.createElement("p");
    name.append(nameA+ " vs. "+nameB);

    let button = document.createElement("div");
    button.setAttribute("id", "centerBtn");
    button.setAttribute("class", "activeBtn");
    button.append("Highscore anzeigen");

    document.getElementsByTagName("article")[0].appendChild(heading);
    document.getElementsByTagName("article")[0].appendChild(iconPlayer);
    document.getElementsByTagName("article")[0].appendChild(iconOpponent);
    document.getElementsByTagName("article")[0].appendChild(name);
    document.getElementsByTagName("article")[0].appendChild(button);
}