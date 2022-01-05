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