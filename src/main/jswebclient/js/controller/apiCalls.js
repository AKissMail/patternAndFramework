import * as choice from '../view/choice.js';
import * as mainMenu from "../view/mainMenu.js";

// API URI
const serverURL = 'http://localhost:8080';
const loginPath = '/auth/login';
const regestationPath = '/auth/register';
const updatePasswordPath = '/auth/updatepassword';


/**
 * Dies ist die function dei ein ajax request an die Api mache. Sie bekommt die notwendigen parameter sowie zwei
 * callback übergeben. Die doneFunction wir im erfolgs fall ausgeführt und iei
 * @param path der Pfad zu der Api
 * @param method die Methode → GET, POST, PUT
 * @param body der zu übertragende Request-Body als JSON
 * @param datatype was wird als antwort erwartet? "text" oder "json"
 * @param token der JWT Token – falls kein Token mit geschickt werden soll → ""
 * @param doneFunction die Function welche bei einem .done ausgeführt werden soll
 * @param malFunction die Function welche bei einem .fail ausgeführt werden soll
 */
function connection(path, method, body, datatype, token, doneFunction, malFunction) {
    $.ajax(
        {
            method: method,
            crossDomain: true,
            url: serverURL + path,
            xhrFields: {withCredentials: true},
            headers: {
                "Access-Control-Allow-Origin": serverURL,
                "Access-Control-Allow-Methods": "GET, POST, PATCH, PUT, DELETE, OPTIONS",
                "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
                "Access-Control-Max-Age": "86400",
                "Content-Type": "application/json",
                "Platform": "web",
                "Accept": "*/*",
                "Build": 2,
                "Authorization": token
            },
            contentType: "application/json; charset=utf-8",
            dataType: datatype,
            data: JSON.stringify(body)
        }
    )
        .done(function (response) {
            doneFunction(response);
        })
        .fail(function (xhr, status) {
            malFunction(xhr, status);
        });
}

/**
 * Diese Methode erstellt einen neuen Spieler.
 *
 * @param playername der name von dem User
 * @param password das Passwort von dem User
 */
export function createPlayer(playername, password) {
    connection(
        registrationPath,
        "POST",
        {
            username: playername,
            password: password
        },
        "text",
        "",
        function done(response) {
            console.log(response);
            console.log('registration done!');
            choice.show();
            mainMenu.show();
        },
        function fail(xhr, status) {
            console.log('registration failed!');
            console.log(xhr.response);
            console.log(status);
            alert("Registrierung fehlgeschlagen");
        });
}

/**
 * Diese Methode loggt den Spieler ein.
 *
 * @param playername Spielername
 * @param password Passwort
 */
export function logInUser(playername, password) {
    connection(
        loginPath,
        "POST",
        {
            username: playername,
            password: password
        },
        "text",
        "",
        function done(response) {
            console.log(response);
            console.log('Login done!');
            document.cookie = "token = " + response;
            document.cookie = "playername =" + playername;
            console.log(decodeCookie("token"));
            mainMenu.show();
        },
        function fail(xhr, status) {
            console.log('Login failed!');
            console.log(xhr.response);
            console.log(status);
            alert("Login ist fehlgeschlagen!");
        });
}

/**
 * Diese Methode loggt den User aus.
 */
export function logout (){
    document.cookie = "token = ";
    console.log(document.cookie);
    choice.show();
}

/**
 * Diese Methode holt aus dem document.cookie den zugehörigen Wert raus und gibt diesen zurück.
 *
 * @param cookieName der Bezeichner des Wertes
 * @returns {string} den Text (String), falls der Bezeichner vorhanden ist und wenn nicht, dann "".
 */
export function decodeCookie(cookieName) {
    let name = cookieName + "=";
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
 * Diese function setzt ein neues Password
 * @param oldPassword das alte Password
 * @param newPassword das neue Password
 */
export function updatePassword(oldPassword, newPassword) {
    connection(
        updatePasswordPath,
        "POST",
        {
            "playername":decodeCookie("playername"),
            "oldpassword":oldPassword,
            "newpassword":newPassword
        },
        "text",
        decodeCookie("token"),
        function (response){
            console.log(response);
            console.log('updatePassword done!');
            console.log(decodeCookie("token"));
            logout();
        },
        function ( xhr , status){
            console.log('Login failed!');
            console.log(xhr.response);
            console.log(status);
            alert("Login ist fehlgeschlagen!");
        })
}

/** ******************************************* @todo ******************************************************** */
/**
 * Diese Methode gibt die Daten eines lokalen Spielers zurück.
 */
export function getLocalUser() {
    return [1, "Andy", "42", "42", "https://hub.dummyapis.com/image?text=Test&height=120&width=120", "online"];

}

/**
 * Diese Methode lädt die Kategorien vom Server und gib diese zurück.
 */
export function getCategory() {
    return ["DemoA", "DemoB", "DemoC"]; // todo hier muss die funkion alle Kategorie hin.
}

/**
 *
 */
export function getGameSize() {
    return [10, 20]; // todo hier muss die funkion alle längen abzufragen hin.
}

/**
 *
 */
export function deleteStatistics() {
    //todd!

}

/**
 *
 * @param {*} data
 */
export function updatePicture(data) {
    const endpoint = "./img";
    fetch(endpoint, {
        method: "post",
        body: data
    }).catch(console.error);
}

/**
 *
 * @returns
 */
export function getHighscore() {
    return [["User A", 650], ["User B", 550], ["User C", 450], ["User D", 350], ["User F", 250], ["User G", 150], ["User H", 50], ["User I", 0]];
}

/**
 *
 * @returns
 */
export function getStatistic() {
    return [["User A", 650, 300], ["User B", 550, 350], ["User C", 450, 400], ["User D", 350, 370], ["User F", 250, 220], ["User G", 150, 200], ["User H", 50, 500], ["User I", 0, 50]];
}

/**
 *
 * @returns
 */
export function getOpenGames() {
    return [["User A", "DemoA", 20, 1], ["User B", "DemoB", 10, 2], ["User C", "DemoC", 20, 3], ["User D", "DemoD", 10, 4]];
}

/**
 *
 * @param {*} category
 * @param {*} size
 * @returns
 */
export function createGame(category, size) {
    return 10;
}

/**
 *
 * @param {*} category
 * @param {*} size
 * @returns
 */
export function getMyQuestions(category, size) {
    let allQuestions = getAllQuestions(category);
    let question = [];
    for (let i = 0; i < size; i++) {
        question.push(allQuestions[Math.floor(Math.random() * size)]);
    }
    return question;
}

function getAllQuestions(c) {
    //@todo
    return [
        {"question": "FrageA", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageB", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageC", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageD", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageF", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageG", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageH", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageI", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageJ", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageK", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageL", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageM", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageN", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageO", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageP", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageQ", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageR", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageS", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageT", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageU", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageV", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageW", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageX", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageY", "a": "A", "b": "B", "c": "C", "d": "D"},
        {"question": "FrageZ", "a": "A", "b": "B", "c": "C", "d": "D"}
    ];
    //todo
}

export function submitAnswer(bool, time, gameID) {
    //@todo
    console.log(bool, time, gameID);
}

export function getResult(gameID) {
    //@todo
    return {"done": true, "localPoint": 750, "remotePoint": 650, "nameOpponent": "Hans"};
    //return {"done": false, "localPoint": 750, "remotePoint": null}
}