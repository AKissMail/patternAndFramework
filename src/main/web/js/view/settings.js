/**
 * Diese View stellt das einstellungsmenü dar. Außerdem werden die Dialoge zum Verändern eines nutzers aufgerufen.
 */
function settings_show() {
    base_clearStage();
    let backHome = document.createElement("div");
    let backHomeText = document.createElement("p");
    backHomeText.append("Hauptmenü");
    backHome.appendChild(backHomeText);
    backHome.setAttribute("class", "btn");
    backHome.setAttribute("id", "backHome");
    document.getElementsByTagName("nav")[0].appendChild(backHome);

    let form = document.createElement("div");
    let buttonPicture = document.createElement("div");
    buttonPicture.setAttribute("class", "btn");
    buttonPicture.setAttribute("id", "updatePicture");
    let buttonPictureText = document.createElement("p");
    buttonPictureText.append("Profilbild ändern");
    buttonPicture.appendChild(buttonPictureText);

    let buttonPassword = document.createElement("div");
    buttonPassword.setAttribute("class", "btn");
    buttonPassword.setAttribute("id", "updatePassword");
    let buttonPasswordText = document.createElement("p");
    buttonPasswordText.append("Passwort ändern");
    buttonPassword.appendChild(buttonPasswordText);

    let buttonDeleteStatistics = document.createElement("div");
    buttonDeleteStatistics.setAttribute("class", "btn");
    buttonDeleteStatistics.setAttribute("id", "deleteStatistics");
    let buttonDeleteStatisticsText = document.createElement("p");
    buttonDeleteStatisticsText.append("Statistik löschen");
    buttonDeleteStatistics.appendChild(buttonDeleteStatisticsText);

    form.appendChild(buttonPicture);
    form.appendChild(buttonPassword);
    form.appendChild(buttonDeleteStatistics);
    document.getElementsByTagName("article")[0].appendChild(form);
    document.getElementById("updatePicture").addEventListener("click", settings_updatePicture);
    document.getElementById("updatePassword").addEventListener("click", settings_updatePassword);
    document.getElementById("deleteStatistics").addEventListener("click", settings_deleteStatistics);
    document.getElementById("backHome").addEventListener("click", mainMenu_show);
}

/**
 * @todo diese Funktion öffnet den generic Browser Dialog um ein Bild hochzuladen.
 */
function settings_updatePicture() {
    alert("settings_updatePicture");
}

/**
 * @todo diese Funktion öffnet den generic Browser Dialog um ein neues Passwort zu setzen.
 */
function settings_updatePassword() {
    alert("settings_updatePassword");
}

/**
 * @todo deise Funktion löscht die Statistik auf dem Server.
 */
function settings_deleteStatistics() {
    alert("settings_deleteStatistics");
}