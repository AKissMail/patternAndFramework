/**
 *
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
    document.getElementById(register).addEventListener("click", );
    console.log('addEventListener');
}
