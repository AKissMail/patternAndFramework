/**
 * Mit dieser funtion wird das Grundgerüst leer geräumt und wieder aufgebeut
 */
function base_clearStage() {

    document.querySelector("header").remove();
    document.querySelector("main").remove();
    document.querySelector("footer").remove();

    let header = document.createElement("header");
    let nav = document.createElement("nav");
    let main = document.createElement("main");
    let article = document.createElement("article");
    let aside = document.createElement("aside");
    let footer = document.createElement("footer");
    header.appendChild(nav);
    main.appendChild(article);
    document.getElementsByTagName("body")[0].appendChild(header);
    document.getElementsByTagName("body")[0].appendChild(main);
    document.getElementsByTagName("body")[0].appendChild(aside);
    document.getElementsByTagName("body")[0].appendChild(footer);
}
/**
 * Hier wird der login token ungültig gemacht
 */
function base_logout() {
    document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC;"
    base_clearStage();
    logIn_show();
}
/**
 * src = https://www.w3schools.com/js/tryit.asp?filename=tryjs_cookie_username
 * @param token Name des Cookies
 * @returns {string} inhalt des Cookies
 * @todo hier ist noch irgend wie ein bug dinn
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
        logIn_show();
    }
}