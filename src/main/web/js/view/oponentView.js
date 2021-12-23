export {displayOponentView};

function displayOponentView(nameA, nameB ){

    let backHome = document.createElement("p");
    backHome.append("Kategorie");
    document.getElementsByTagName("nav")[0].appendChild(backHome);

    let heading = document.createElement("h1");
    heading.append("Gegner gefunden!");
    let iconPlayer = document.createElement("img");
    iconPlayer.setAttribute("src", "img/iconPlayer.png");
    let iconOpponent = document.createElement("img");
    iconOpponent.setAttribute("src", "img/iconOpponent.png");
    let name =document.createElement("p");
    name.append(nameA+ " vs. "+nameB);

    let button = document.createElement("div");
    button.setAttribute("id", "centerBtn");
    button.setAttribute("class", "activeBtn");
    button.append("Quiz starten");

    document.getElementsByTagName("article")[0].appendChild(heading);
    document.getElementsByTagName("article")[0].appendChild(iconPlayer);
    document.getElementsByTagName("article")[0].appendChild(iconOpponent);
    document.getElementsByTagName("article")[0].appendChild(name);
    document.getElementsByTagName("article")[0].appendChild(button);
}