/**
 * Das ist die Notlösung da ich grade nicht mit den Pakten klarkomme.
 * Damit ich alle function fertigstellen kann, hab ich nun alles in eine JS Datei gepackt.
 * Kommentare die mit /** beginnen sind entweder die function an sich oder das Paket
 * kommentare die mit // beginnen sind die einzeln Dateien.
 * Wer ist schuld? @andreas Kissmehl
 */
/**
 * Das ist die View
 */
// logIn.js
/**
 * Das ist die LogIn view. Sie stellt die login Maske dar.
 */
function logIn_show() {
    base_clearStage();

    let backHome = document.createElement("div");
    backHome.setAttribute("id", "back home");
    backHome.setAttribute("class", "btn");
    let backHometext = document.createElement("p");
    backHometext.append("Zürck");
    backHome.appendChild(backHometext);
    document.getElementsByTagName("article")[0].appendChild(backHome);


    let side = document.createElement("img");
    side.setAttribute("src", "img/bulb.png");
    side.setAttribute("id", "logInLamp");
    let sideP = document.createElement("p");
    sideP.append("Quizzz");

    let aside = document.createElement("div");
    aside.setAttribute("id", "aside");

    document.getElementsByTagName("main")[0].appendChild(aside);
    document.getElementById("aside").appendChild(side);
    document.getElementById("aside").appendChild(sideP);


    let form = document.createElement("form");

    let inputServer = document.createElement("input");
    inputServer.setAttribute("type", "text");
    inputServer.setAttribute("name", "inputServer");
    inputServer.setAttribute("placeholder", "Server URL");
    inputServer.setAttribute("id", "inputServer")

    let inputUser = document.createElement("input");
    inputUser.setAttribute("type", "text");
    inputUser.setAttribute("name", "userName");
    inputUser.setAttribute("placeholder", "Nutzername");
    inputUser.setAttribute("id", "userName")

    let inputPassword = document.createElement("input");
    inputPassword.setAttribute("type", "password");
    inputPassword.setAttribute("name", "password");
    inputPassword.setAttribute("id", "password");
    inputPassword.setAttribute("placeholder", "Password");

    let radioA = document.createElement("input");
    radioA.setAttribute("type", "radio");
    radioA.setAttribute("id", "newUser");
    radioA.setAttribute("value", "newUser");
    radioA.setAttribute("name", "modus");
    let radioALabel = document.createElement("label");
    radioALabel.setAttribute("for", "newUser");
    radioALabel.innerHTML = "Neuen Account anlegen";

    let radioB = document.createElement("input");
    radioB.setAttribute("type", "radio");
    radioB.setAttribute("id", "logIn");
    radioB.setAttribute("value", "logIn");
    radioB.setAttribute("name", "modus");
    let radioBLabel = document.createElement("label");
    radioBLabel.setAttribute("for", "logIn");
    radioBLabel.innerHTML = "Nutzer einloggen"


    let button = document.createElement("input");
    button.setAttribute("type", "submit");
    button.setAttribute("value", "Senden");
    button.setAttribute("id", "button");
    button.setAttribute("onClick", "runLogIn");

    let brake = document.createElement("br");

    form.appendChild(inputUser);
    form.appendChild(inputPassword);
    form.appendChild(brake);
    form.appendChild(button);
    document.getElementsByTagName("article")[0].appendChild(form);
    document.getElementById("back home").addEventListener("click", choice_show);
    document.getElementById("button").addEventListener("click", logIn_runLogIn);
    console.log('addEventListener')
}
/**
 *  Das ist das bissen magic in der Login maske.
 */
function logIn_runLogIn() {
    let password = document.getElementById("password").value;
    let userName = document.getElementById("userName").value;
    let inputServer = "http://localhost/"

    if (password === "" || userName === "") {
        alert('Bitte geben Sie ALLE daten ein!');
    } else {
        let token = apiCalls_getToken(userName, password, inputServer);
        if (apiCalls_checkToken(token)) {
            console.log("Token: " + token + " ist valide");
            document.cookie = "token =" + token;
            base_clearStage();
            mainMenu_show();
        } else {
            console.log("Token: " + token + " ist nicht valide");
            alert('Die Eingeben daten sind nicht gültig!');
        }
    }
}
function register_show(){

    base_clearStage();

    let backHome = document.createElement("div");
    backHome.setAttribute("id", "back home");
    backHome.setAttribute("class", "btn");
    let backHometext = document.createElement("p");
    backHometext.append("Zürck");
    backHome.appendChild(backHometext);
    document.getElementsByTagName("article")[0].appendChild(backHome);


    let side = document.createElement("img");
    side.setAttribute("src", "img/bulb.png");
    side.setAttribute("id", "logInLamp");
    let sideP = document.createElement("p");
    sideP.append("Quizzz");

    let aside = document.createElement("div");
    aside.setAttribute("id", "aside");

    document.getElementsByTagName("main")[0].appendChild(aside);
    document.getElementById("aside").appendChild(side);
    document.getElementById("aside").appendChild(sideP);


    let form = document.createElement("form");

    let inputUser = document.createElement("input");
    inputUser.setAttribute("type", "text");
    inputUser.setAttribute("name", "userName");
    inputUser.setAttribute("placeholder", "Nutzername");
    inputUser.setAttribute("id", "userName")

    let inputPassword = document.createElement("input");
    inputPassword.setAttribute("type", "password");
    inputPassword.setAttribute("name", "password");
    inputPassword.setAttribute("id", "password");
    inputPassword.setAttribute("placeholder", "Password");

    let inputPasswordVerify = document.createElement("input");
    inputPasswordVerify.setAttribute("type", "password");
    inputPasswordVerify.setAttribute("name", "passwordVerify");
    inputPasswordVerify.setAttribute("id", "passwordVerify");
    inputPasswordVerify.setAttribute("placeholder", "Password wiederholen");

    let button = document.createElement("input");
    button.setAttribute("type", "submit");
    button.setAttribute("value", "Senden");
    button.setAttribute("id", "button");
    button.setAttribute("onClick", "runLogIn");

    let brake = document.createElement("br");

    form.appendChild(inputUser);
    form.appendChild(inputPassword);
    form.appendChild(inputPasswordVerify);
    form.appendChild(brake);
    form.appendChild(button);
    document.getElementsByTagName("article")[0].appendChild(form);
    document.getElementById("back home").addEventListener("click", choice_show);
    document.getElementById("button").addEventListener("click", base_createUser);
    console.log('addEventListener')
}

// choice.js
/**
 * @todo
 */
function choice_show() {
    base_clearStage();

    let logIn = document.createElement("div");
    logIn.setAttribute("id", "logIn");
    logIn.setAttribute("class", "btn");
    let logInText = document.createElement("p");
    logInText.append("Log In");
    logIn.appendChild(logInText);
    document.getElementsByTagName("article")[0].appendChild(logIn);

    let register = document.createElement("div");
    register.setAttribute("id", "register");
    register.setAttribute("class", "btn");
    let registertext = document.createElement("p");
    registertext.append("Registrieren");
    register.appendChild(registertext);
    document.getElementsByTagName("article")[0].appendChild(register);


    let side = document.createElement("img");
    side.setAttribute("src", "img/bulb.png");
    side.setAttribute("id", "logInLamp");
    let sideP = document.createElement("p");
    sideP.append("Quizzz");

    let aside = document.createElement("div");
    aside.setAttribute("id", "aside");

    document.getElementsByTagName("main")[0].appendChild(aside);
    document.getElementById("aside").appendChild(side);
    document.getElementById("aside").appendChild(sideP);

    document.getElementById("logIn").addEventListener("click", logIn_show);
    document.getElementById("register").addEventListener("click", register_show);
    console.log('addEventListener');
}

//mainMenu.js
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

    // document.getElementsByTagName("aside")[0].setAttribute("id", "none");

    document.getElementById("logout").addEventListener("click", base_logout);
    document.getElementById("startGame").addEventListener("click", gamemode_showPicker);
    document.getElementById("settings").addEventListener("click",settings_show);
    document.getElementById("highscore").addEventListener("click", highscore_show);
}
// gamemode
function gamemode_showPicker(){
    base_clearStage();

    let backHome = document.createElement("div");
    backHome.setAttribute("id", "back home");
    backHome.setAttribute("class", "btn");
    let backHometext = document.createElement("p");
    backHometext.append("Hauptmenü");
    backHome.appendChild(backHometext);
    document.getElementsByTagName("nav")[0].appendChild(backHome);
    document.getElementById("back home").addEventListener("click", mainMenu_show);


    let btnA = document.createElement("div");
    btnA.setAttribute("class","btn");
    btnA.setAttribute("id", "newGame");
    let btnAText = document.createElement("p");
    btnAText.append("neues Spiel erstellen");
    btnA.appendChild(btnAText);

    let btnB = document.createElement("div");
    btnB.setAttribute("class","btn");
    btnB.setAttribute("id", "enterGame");
    let btnBText = document.createElement("p");
    btnBText.append("eine Spiel beitreten");
    btnB.appendChild(btnBText)

    document.getElementsByTagName("article")[0].appendChild(btnA);
    document.getElementsByTagName("article")[0].appendChild(btnB);

    document.getElementById("newGame").addEventListener("click", gamemode_showNewGame);
    document.getElementById("enterGame").addEventListener("click", gamemode_showEnterGame);
}

function gamemode_showNewGame(){
    base_clearStage();
    let catigroy = ["DemoA", "DemoB", "DemoC"];
    let backHome = document.createElement("div");
    backHome.setAttribute("id", "back home");
    backHome.setAttribute("class", "btn");
    let backHometext = document.createElement("p");
    backHometext.append("zurück");
    backHome.appendChild(backHometext);
    document.getElementsByTagName("nav")[0].appendChild(backHome);
    document.getElementById("back home").addEventListener("click", gamemode_showPicker);

    let brake1 = document.createElement("br");
    let brake2 = document.createElement("br");
    let brake3 = document.createElement("br");
    let brake4 = document.createElement("br");
    let from = document.createElement("form");
    let labe = document.createElement("label");
    labe.append("Wählen sie eine Kategorie");
    from.appendChild(labe);
    from.appendChild(brake1);

    let select = document.createElement("select");

    for(let i = 0; i < catigroy.length; i++){
        let option = document.createElement("option");
        option.setAttribute("value", catigroy[i]);
        option.append(catigroy[i])
        select.appendChild(option);
    }
    from.appendChild(select);
    from.appendChild(brake3);
    let label2 = document.createElement("label");
    label2.append("Wählen sie die anzahl der Fragen");
    from.appendChild(label2);
    from.appendChild(brake2);

    let select2 = document.createElement("select");

    let option2 = document.createElement("option");
    option2.setAttribute("value", "10");
    option2.append("10");

    let option3 = document.createElement("option");
    option3.setAttribute("value", "20");
    option3.append("20");

    select2.appendChild(option2);
    select2.appendChild(option3);

    let input = document.createElement("input");
    input.setAttribute("type", "button");
    input.setAttribute("id", "submit");
    input.setAttribute("value", "Senden");

    from.appendChild(select2);
    from.appendChild(brake4);
    from.appendChild(input);

    document.getElementsByTagName("article")[0].appendChild(from);
    document.getElementsByTagName("input")[0].addEventListener("click",lobby_show);
}

function gamemode_showEnterGame(){
    let games =  [["Hans","DemoA",20,1],["Peter","DemoB",10,2],["Nina","DemoC",20,3],["Wurst","DemoD",10,4]]; //apiCalls_GetOpenGames;
    console.log(games.length);
    console.log(games[1][1]);

    base_clearStage();
    let backHome = document.createElement("div");
    backHome.setAttribute("id", "back home");
    backHome.setAttribute("class", "btn");
    let backHometext = document.createElement("p");
    backHometext.append("zurück");
    backHome.appendChild(backHometext);
    document.getElementsByTagName("nav")[0].appendChild(backHome);
    document.getElementById("back home").addEventListener("click", gamemode_showPicker);

    let heading  = document.createElement("h1");
    heading.append("Es wurden "+games.length+" gefunden.");
    let description = document.createElement("p");
    description.append("Bitte wählen Sie ein Spiel aus");

    document.getElementsByTagName("article")[0].appendChild(heading);
    document.getElementsByTagName("article")[0].appendChild(description);

    let form = document.createElement("form");
    let label = document.createElement("label");
    label.setAttribute("for", "gameDropdown");
    label.append("Wählen Sie ein Speil aus");
    form.appendChild(label);
    let select = document.createElement("select");
    select.setAttribute("name", "gameDropdown");
    for(let i = 0; i< games.length; i++){

        let option = document.createElement("option");
        option.setAttribute("value", games[i][3]);
        option.setAttribute("id", games[i][3]);
        option.append("Thema: "+games[i][1]+", Fragen: "+games[i][2]+", gegen: "+games[i][0]);

        select.appendChild(option);
    }
    form.appendChild(select);
    let brake = document.createElement("br");
    form.appendChild(brake);
    let input = document.createElement("input");
    input.setAttribute("type", "button");
    input.setAttribute("value", "Spiel starten");
    form.appendChild(input);
    document.getElementsByTagName("article")[0].appendChild(form);

    document.getElementsByTagName("input")[0].addEventListener("click",lobby_show);


}
// category.js
//todo
function category_show(){
    alert("category_show  Sie haben mit Magie eine Kategorie gewählt");
    lobby_show();
}
// highscore.js
function highscore_show() {
    base_clearStage();
    let games = apiCalls_GetOwnHighscore(base_getCookie("token"));

    let backHome = document.createElement("div");
    backHome.setAttribute("id", "back home");
    backHome.setAttribute("class", "btn");
    let backHometext = document.createElement("p");
    backHometext.append("Hauptmenü");
    backHome.appendChild(backHometext);

    document.getElementsByTagName("nav")[0].appendChild(backHome);
    let highscoreHading = document.createElement("h1");
    highscoreHading.append("Highscore");
    let highscoreHadingDescription = document.createElement("p");
    highscoreHadingDescription.append(games.length + " Spielrunden gefunden");
    document.getElementsByTagName("article")[0].appendChild(highscoreHading);
    document.getElementsByTagName("article")[0].appendChild(highscoreHadingDescription);
    let scoreTabel = document.createElement("table");
    scoreTabel.setAttribute("id", "scoreTabel");
    let scoreTabelHeading = document.createElement("tr");
    let scoreTabelHeadingPlatz = document.createElement("Platz");
    let scoreTabelHeadingUser = document.createElement("User");
    let scoreTabelHeadingPunkte = document.createElement("Punkte");
    scoreTabelHeading.appendChild(scoreTabelHeadingPlatz);
    scoreTabelHeading.appendChild(scoreTabelHeadingUser);
    scoreTabelHeading.appendChild(scoreTabelHeadingPunkte);
    scoreTabel.appendChild(scoreTabelHeading);

    for (let i = 1; i <= games.length; i++) {
        let row = document.createElement("tr");
        let ranking = document.createElement("td");
        let user = document.createElement("td");
        let score = document.createElement("td");
        ranking.append(i.toString());
        user.append(games[i - 1][1].toString());
        score.append((games[i - 1][2].toString()));
        row.appendChild(ranking);
        row.appendChild(user);
        row.appendChild(score);
        scoreTabel.appendChild(row);
    }
    document.getElementsByTagName("article")[0].appendChild(scoreTabel);
    //document.getElementsByTagName("aside")[0].setAttribute("id", "none");
    document.getElementById("back home").addEventListener("click", mainMenu_show);

}
// lobby.js
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
    // document.getElementsByTagName("aside")[0].setAttribute("id", "none");

    document.getElementsByClassName("btn")[0].addEventListener("click", mainMenu_show); // todo austauschen gegen katigorie...

    setTimeout(oponentView_show("Hans","Dampf"),3000); // todo austauschen gegen logic

}
// oponentView.js
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
    //   document.getElementsByTagName("aside")[0].setAttribute("id", "none");

    document.getElementsByClassName("btn")[0].addEventListener("click", mainMenu_show);
    document.getElementsByClassName("btn")[1].addEventListener("click", quiz_show);


}
// quiz.js
function quiz_show() {
    base_clearStage();
    let question = {
        question: "Was ist Nass?",
        a: "Annanass",
        b: "Bananen",
        c: "Kuchen",
        d: "Wasser"
    };


    let backHome = document.createElement("div");
    let backHometext = document.createElement("p");
    backHometext.append("Beenden");
    backHome.appendChild(backHometext);
    backHome.setAttribute("class", "btn");
    document.getElementsByTagName("nav")[0].appendChild(backHome);

    let timer = document.createElement("div");

    timer.setAttribute("id", "timer"); //todo fancy timer shit
    document.getElementsByTagName("nav")[0].appendChild(backHome);
    document.getElementsByTagName("nav")[0].appendChild(timer);

    let frage = document.createElement("h2");
    frage.append(question.question);
    document.getElementsByTagName("article")[0].appendChild(frage);


    let wapper = document.createElement("div");
    wapper.setAttribute("id", "wapper");
    let a = document.createElement("p");
    a.append(question.a);
    a.setAttribute("id", "a");
    a.setAttribute("class", "question");
    let b = document.createElement("p");
    b.append(question.b);
    b.setAttribute("id", "b");
    b.setAttribute("class", "question");
    let c = document.createElement("p");
    c.append(question.c);
    c.setAttribute("id", "c");
    c.setAttribute("class", "question");
    let d = document.createElement("p");
    d.append(question.d);
    d.setAttribute("id", "d");
    d.setAttribute("class", "question");
    wapper.appendChild(a);
    wapper.appendChild(b);
    wapper.appendChild(c);
    wapper.appendChild(d);
    document.getElementsByTagName("article")[0].appendChild(wapper);

    let hr = document.createElement("hr");
    let footerText = document.createElement("p");
    footerText.append(question.footerInfo);


    document.getElementById("a").addEventListener("click", quiz_sendAnserA);
    document.getElementById("b").addEventListener("click", quiz_sendAnserB);
    document.getElementById("c").addEventListener("click", quiz_sendAnserC);
    document.getElementById("d").addEventListener("click", quiz_sendAnserD);
    // document.getElementsByTagName("aside")[0].setAttribute("id", "none");
    document.getElementsByClassName("btn")[0].addEventListener("click", mainMenu_show);
}
function quiz_sendAnserA() { //todo mit dem server sprechen
    result_show("Hans","","Dampf","", true);
}
function quiz_sendAnserB() { //todo mit dem server sprechen
    result_show("Hans","","Dampf","", false);
}
function quiz_sendAnserC() { //todo mit dem server sprechen
    result_show("Hans","","Dampf","", false);
}
function quiz_sendAnserD() { //todo mit dem server sprechen
    result_show("Hans","","Dampf","", false);
}
// result.js
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
    //   document.getElementsByTagName("aside")[0].setAttribute("id", "none");

    document.getElementsByClassName("btn")[0].addEventListener("click", mainMenu_show);
    document.getElementsByClassName("btn")[1].addEventListener("click", score_show);
}
// score.js
function score_show() {
    base_clearStage();
    let games = [[1, "User A", 650], [2, "User A", 650]]//apiGetOwnHighscore();

    let backHome = document.createElement("div");
    backHome.setAttribute("class", "btn")
    let backHomeText = document.createElement("p");
    backHomeText.append("Hauptmenü");
    backHome.appendChild(backHomeText);
    document.getElementsByTagName("nav")[0].appendChild(backHome);
    //document.getElementsByTagName("a")[0].addEventListener(onclick,);

    let highscoreHading = document.createElement("h1");
    highscoreHading.append("Highscore");
    let highscoreHadingDescription = document.createElement("p");
    highscoreHadingDescription.append(games.length + " Spielrunden gefunden");
    document.getElementsByTagName("article")[0].appendChild(highscoreHading);
    document.getElementsByTagName("article")[0].appendChild(highscoreHadingDescription);


    let scoreTabel = document.createElement("table");
    scoreTabel.setAttribute("id", "scoreTabel");
    let scoreTabelHeading = document.createElement("tr");
    let scoreTabelHeadingPlatz = document.createElement("th");
    scoreTabelHeadingPlatz.append("Platz");
    let scoreTabelHeadingUser = document.createElement("th");
    scoreTabelHeadingUser.append("User");
    let scoreTabelHeadingPunkte = document.createElement("th");
    scoreTabelHeadingPunkte.append("Punkte");
    scoreTabelHeading.appendChild(scoreTabelHeadingPlatz);
    scoreTabelHeading.appendChild(scoreTabelHeadingUser);
    scoreTabelHeading.appendChild(scoreTabelHeadingPunkte);
    scoreTabel.appendChild(scoreTabelHeading);

    for (let i = 1; i <= games.length; i++) {
        let row = document.createElement("tr");
        let ranking = document.createElement("td");
        let user = document.createElement("td");
        let score = document.createElement("td");
        ranking.append(i.toString());
        user.append(games[i - 1][1].toString());
        score.append((games[i - 1][2]).toString());

        row.appendChild(ranking);
        row.appendChild(user);
        row.appendChild(score);
        scoreTabel.appendChild(row);
    }
    document.getElementsByTagName("article")[0].appendChild(scoreTabel);
    //  document.getElementsByTagName("aside")[0].setAttribute("id", "none");

    document.getElementsByClassName("btn")[0].addEventListener("click", mainMenu_show);
}
// settings.js
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
    // document.getElementsByTagName("aside")[0].setAttribute("id", "none");
}
function settings_updatePicture() {
    alert("settings_updatePicture");
}
function settings_updatePassword() {
    alert("settings_updatePassword");
}
function settings_deleteStatistics() {
    alert("settings_deleteStatistics");
}
/**
 *  Das sind die function der controller
 */
//base
/**
 * Das ist ein @todo
 */
function base_createUser(){
    alert("Andy ist erfolgreich registiert");
    base_clearStage();
    logIn_show();
}

/**
 *
 */
function base_clearStage() {

    document.querySelector("header").remove();
    document.querySelector("main").remove();
    // document.querySelector("aside").remove();
    document.querySelector("footer").remove();

    let header = document.createElement("header");
    let nav = document.createElement("nav");
    let main = document.createElement("main");
    let article = document.createElement("article");
    //let aside = document.createElement("aside");
    let footer = document.createElement("footer");
    header.appendChild(nav);
    main.appendChild(article);
    document.getElementsByTagName("body")[0].appendChild(header);
    document.getElementsByTagName("body")[0].appendChild(main);
    //document.getElementsByTagName("body")[0].appendChild(aside);
    document.getElementsByTagName("body")[0].appendChild(footer);
}
/**
 *
 */
function base_logout() {
    document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC;"
    base_clearStage();
    choice_show();
}
/**
 * src = https://www.w3schools.com/js/tryit.asp?filename=tryjs_cookie_username
 * @param token Name des Cookies
 * @returns {string} inhalt des Cookies
 */
function base_getCookie(token) {
    let name = token + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
/**
 *  Das ist die Start function
 */
function statUp() {
    console.log("startUp");
    if (apiCalls_checkToken("token")) {
        console.log("mainMenu_show");
        mainMenu_show();
    } else {
        choice_show();
    }
}
// apiCalls.js
/**
 *
 * @returns Die offen Spiel auf dem Server
 */
function apiCalls_GetOpenGames(){
    return  [["Hans","DemoA",20],["Peter","DemoB",10],["Nina","DemoC",20],["Wurst","DemoD",10]];
}
/**
 * holt die ID des Local Players
 * @param token
 * @returns {number}
 */
function apiCalls_getLocalPlayerID(token) {
    return 1;
}
/**
 * Hier soll geprüft werden ob ein Token valid ist
 * @param token
 * @returns {boolean}
 */
function apiCalls_checkToken(token) {

    let t = "demo";//base_getCookie(token);
    console.log(t);
    if (t === "demo") {
        return true; //todo

    } else if(token.length !== 0){
        console.log("apiCalls_checkToken false");
        return false;

    }
    else {
        console.log("apiCalls_checkToken true");
        return false;
    }
}
/**
 * holt den Namen des LocalPlayers
 * @param token
 * @returns {string}
 */
function apiCalls_getLocalPlayerName(token) {
    return "Andy"; //todo
}
/**
 * holt den aktuellen Punktestand
 * @param token
 * @returns {number}
 */
function apiCalls_getLocalPlayerCurScore(token) {
    return 42; //todo
}
/**
 * holt den highScore
 * @param token
 * @returns {number}
 */
function apiCalls_getLocalPlayerHighScore(token) {
    return 4242;
}
/**
 * Hier wird das Profil bild
 * @returns {string}
 */
function apiCalls_getProfilePic(token) {
    return "/img/bulb.png"; //todo
}
/**
 * Das gibt den Status
 * @param token
 * @returns {string}
 */
function apiCalls_getCurStatus(token) {
    return "online"; //todo
}
/**
 * Diese Funktion
 * @param user
 * @param password
 * @param url
 * @returns {string}
 * @constructor
 */
function apiCalls_getToken(user, password, url) {
    if (url === "0") {
        return "demo"
    } else {
        //@todo hier muss der JWT geholt werden
        return "demo"
    }
}
/**
 * Hier wird der Highscore geholt
 * @param token
 * @returns {(number|string)[][]}
 * @constructor
 */
function apiCalls_GetOwnHighscore(token) {
    return [[1, "User A", 650], [2, "User A", 650]]; //todo
}
/**
 *
 */
function api_getLocalUser(s) {
    return [1, "Andy", "42", "42", "img/bulb.png", "online"];
}
/**
 * Das sind die function der model
 *
 // game.js
 /**
 * das ist die class die eine Spiel darstellt
 */
class game_GameClient {
    constructor(sessionID, playerLocal, playerRemote, curLevel, modus) {
        this.sessionID = sessionID;
        this.playerLocal = playerLocal;
        this.playerRemode = playerRemote;
        this.curLevel = curLevel;
        this.modus = modus;
    }
}
// player.js
/**
 * Das ist die class die eine Player darstellt
 */
class player_player {

    constructor(playerIDid, userName, curScore, highScore, thumbnail, curStatus) {
        this.playerIDid = playerIDid;
        this.userName = userName;
        this.curScore = curScore;
        this.highScore = highScore;
        this.thumbnail = thumbnail;
        this.curStatus = curStatus;
    }
}
// question.js
/**
 * Das ist die class die eine Frage darstellt
 */
class question_question {
    // constructors
    constructor(questionID, question, answerA, answerB, answerC, answerD, diffculty) {
        this.questionID = questionID;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.diffculty = diffculty;
    }
}