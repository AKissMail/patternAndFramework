export {displayLobby};
function displayLobby(){

    let backHome = document.createElement("p");
    backHome.append("Kategorie");
    document.getElementsByTagName("nav")[0].appendChild(backHome);

    let heading = document.createElement("h1");
    let subHeadding = document.createElement("p");
    heading.append("Warten auf einen Gegner");
    subHeadding.append("Netzwerk wird durchsucht ...");
    let icon = document.createElement("img");
    icon.setAttribute("src", "img/searchOpponent.png");

    let button = document.createElement("div");
    button.setAttribute("id", "centerBtn");
    button.setAttribute("class", "inactiveBtn");

    document.getElementsByTagName("article")[0].appendChild(heading);
    document.getElementsByTagName("article")[0].appendChild(subHeadding);
    document.getElementsByTagName("article")[0].appendChild(icon);
    document.getElementsByTagName("article")[0].appendChild(button);
}