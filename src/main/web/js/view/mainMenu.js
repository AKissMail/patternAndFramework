/**
 * Diese Funktion zeigt das Hauptmenü an.
 */
function mainMenu_show() {
    base_clearStage();
    let user = api_getLocalUser(base_getCookie("token"));

    let profilePic = document.createElement("img");
    profilePic.setAttribute("id", "thumbnail");
    profilePic.setAttribute("src", user[4]);
    let name = document.createElement("h2");
    name.append(user[1]);

    let logout = document.createElement("div");
    let logoutText = document.createElement("p");
    logoutText.append("Abmelden");
    logout.setAttribute("id", "logout");
    logout.setAttribute("class", "btn");
    logout.appendChild(logoutText);

    let startGame = document.createElement("div");
    startGame.setAttribute("id", "startGame");
    startGame.setAttribute("class", "btn");
    let startGameText = document.createElement("p");
    startGameText.append("Quiz starten")
    startGame.appendChild(startGameText);

    let settings = document.createElement("div");
    settings.setAttribute("id", "settings");
    settings.setAttribute("class", "btn");
    let settingsText = document.createElement("p")
    settingsText.append("Einstellungen")
    settings.appendChild(settingsText);

    let highscore = document.createElement("div");
    highscore.setAttribute("id", "highscore");
    highscore.setAttribute("class", "btn");
    let highscoreText = document.createElement("p")
    highscoreText.append("Highscore")
    highscore.appendChild(highscoreText);

    document.getElementsByTagName("article")[0].appendChild(profilePic);
    document.getElementsByTagName("article")[0].appendChild(name);
    document.getElementsByTagName("article")[0].appendChild(logout);
    document.getElementsByTagName("article")[0].appendChild(startGame);
    document.getElementsByTagName("article")[0].appendChild(settings);
    document.getElementsByTagName("article")[0].appendChild(highscore);
    document.getElementsByTagName("aside")[0].setAttribute("id", "none");

    document.getElementById("logout").addEventListener("click", base_logout);
    document.getElementById("startGame").addEventListener("click", category_show);
    document.getElementById("settings").addEventListener("click",settings_show);
    document.getElementById("highscore").addEventListener("click", highscore_show);
}