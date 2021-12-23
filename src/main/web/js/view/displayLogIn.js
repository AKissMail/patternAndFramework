export {displayLogIn, runLogIn};


/**
 * Das ist der LogIn Screen. Diese function baut in das HTML gerüst ein Formular
 * welche die folgenden 3 werte abfragt.
 * 1. Server URI
 * 2. Username
 * 3. Password
 * Die verarbeitung übernimmt runLogIn in displayLogin.js.
 */
function displayLogIn(){
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
    radioALabel.innerHTML="Neuen Account anlegen";

    let radioB = document.createElement("input");
    radioB.setAttribute("type", "radio");
    radioB.setAttribute("id", "logIn");
    radioB.setAttribute("value", "logIn");
    radioB.setAttribute("name", "modus");
    let radioBLabel = document.createElement("label");
    radioBLabel.setAttribute("for", "logIn");
    radioBLabel.innerHTML="Nutzer einloggen"


    let button = document.createElement("input");
    button.setAttribute("type", "submit");
    button.setAttribute("value", "Senden");
    button.setAttribute("id", "button");
  //  button.setAttribute("onClick", "runLogIn()");

    form.appendChild(inputServer);
    form.appendChild(inputUser);
    form.appendChild(inputPassword);
    form.appendChild(radioA);
    form.appendChild(radioALabel);
    form.appendChild(radioB);
    form.appendChild(radioBLabel);
    form.appendChild(button);
    document.getElementsByTagName("article")[0].appendChild(form);

    let side = document.createElement("img");
    side.setAttribute("src", "img/bulb.png");
    side.setAttribute("id", "logInLamp");
    let sideP = document.createElement("p");
    sideP.append("Quizzz");

    document.getElementsByTagName("aside")[0].appendChild(side);
    document.getElementsByTagName("aside")[0].appendChild(sideP);

    document.getElementById("button").addEventListener("click", runLogIn);
}

/**
 *
 */

function runLogIn(){
    let password = document.getElementById("password").value;
    let userName = document.getElementById("userName").value;
    let inputServer = document.getElementById("inputServer").value;

   // document.cookie = "severname="+document.openSever.severName.value;
    alert("input ="+password + userName+ inputServer);
}