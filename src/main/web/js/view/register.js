/**
 * Diese Funktion zeigt
 */
function register_show() {

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