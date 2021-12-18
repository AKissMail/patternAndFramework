/**
 * macht die Stage leer und ruft home auf
 */

function backHome() {
    clearStage();
    displayHome();
}

/**
 * @todo
 * @returns {undefined}
 */
function deleteStatistics() {
    alert("deleteStatistics()");
    return undefined;
}

/**
 * @todo
 * @returns {undefined}
 */
function updatePassword() {

    return undefined;
}

/**
 * @todo
 * @returns {undefined}
 */
function updatePicture() {
    return undefined;
}

/**
 * Baut das Optionsmenü auf
 */
function displaySettings() {

    let backHome = document.createElement("p");
    backHome.append("Hauptmenü");
    document.getElementsByTagName("nav")[0].appendChild(backHome);

    let form = document.createElement("form");

    let buttonPicture = document.createElement("button");
    buttonPicture.setAttribute("class", "btnCenter");
    buttonPicture.setAttribute("id", "updatePicture");
    let buttonPassword = document.createElement("button");
    buttonPassword.setAttribute("class", "btnCenter");
    buttonPassword.setAttribute("id", "updatePassword");
    let buttonDeleteStatistics = document.createElement("button");
    buttonDeleteStatistics.setAttribute("class", "btnCenter");
    buttonDeleteStatistics.setAttribute("id", "deleteStatistics");

    form.appendChild(buttonPicture);
    form.appendChild(buttonPicture);
    form.appendChild(buttonDeleteStatistics);
    document.getElementsByTagName("article")[0].appendChild(form);
    document.getElementById("updatePicture").addEventListener("click", updatePicture());
    document.getElementById("updatePassword").addEventListener("click", updatePassword());
    document.getElementById("deleteStatistics").addEventListener("click", deleteStatistics())
    document.getElementsByIs("backHome").addEventListener("click", backHome)
}
