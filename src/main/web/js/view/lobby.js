/**
 * Das hier ist die lobby in der die User auf ein gegner warten.
 */
function lobby_show() {
    base_clearStage();
    let backHome = document.createElement("div");
    let backHometext = document.createElement("p");
    backHometext.append("Kategorie");
    backHome.appendChild(backHometext);
    backHome.setAttribute("class", "btn");
    document.getElementsByTagName("nav")[0].appendChild(backHome);

    let heading = document.createElement("h1");
    let subHeadding = document.createElement("p");
    heading.append("Warten auf einen Gegner");
    subHeadding.append("Netzwerk wird durchsucht ...");
    let icon = document.createElement("img");
    icon.setAttribute("src", "img/settingsGear.png");
    icon.setAttribute("id", "rotation");

    let button = document.createElement("div");
    button.setAttribute("id", "centerBtn");
    button.setAttribute("class", "inactiveBtn");

    document.getElementsByTagName("article")[0].appendChild(heading);
    document.getElementsByTagName("article")[0].appendChild(subHeadding);
    document.getElementsByTagName("article")[0].appendChild(icon);
    document.getElementsByTagName("article")[0].appendChild(button);
    document.getElementsByTagName("aside")[0].setAttribute("id", "none");

    document.getElementsByClassName("btn")[0].addEventListener("click", mainMenu_show); // todo austauschen gegen katigorie...

    setTimeout(oponentView_show("Hans","Dampf"),3000); // todo austauschen gegen logic

}