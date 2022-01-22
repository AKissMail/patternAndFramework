// API URI
const serverURL = 'http://localhost:8080';
const loginPath = '/auth/login';
const registrationPath = '/auth/register';
const updatePasswordPath = '/auth/updatepassword';
const highscorePath = '/highscore';
const historyPath = '/games/history';
const historydeletePath = '/games/historydelete';
const catigroyPath = '/category';
const roundsPath ='/rounds';
const openGamePath = '/games/open';
const updateGamePath = '/games/update';
const createGamePath = '/games/create';


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
function connection({path, method = "GET", body, datatype = "JSON", doneFunction, malFunction} = {}) {
    console.log(decodeCookie("token"));
    $.ajax(
        {
            method: method,
            crossDomain: true,
            url: serverURL + path,
            xhrFields: {withCredentials: true},
            headers: {
                "Access-Control-Allow-Origin": "http://localhost:8080",
                "Access-Control-Allow-Methods": "GET, POST, PUT, OPTIONS",
                "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token",
                "Access-Control-Max-Age": "30",
                "Content-Type": "application/json",
                "Platform": "web",
                "Accept": "*/*",
                "Build": 2,
                "Authorization": path === loginPath || path === registrationPath ? "" : "Bearer " + decodeCookie("token")
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
 * @param playername der name von dem User
 * @param password das Passwort von dem User
 */
export function createPlayer(playername, password,callback) {
    connection({
        path: registrationPath,
        method: "POST",
        body: {username: playername, password: password},
        datatype: "text",
        doneFunction: function done(response) {
            console.log(response);
            console.log('registration done!');
            callmeback();
        },
        malFunction: function fail(xhr, status) {
            console.log('registration failed!');
            console.log(xhr.response);
            console.log(status);
            alert("Registrierung fehlgeschlagen");
        }
    });
    function callmeback(){
        callback();
    }
}

/**
 * Diese Methode loggt den Spieler ein.
 * @param playername Spielername
 * @param password Passwort
 */
export function logInUser(playername, password, callback) {
    connection({
        path: loginPath,
        method: "POST",
        body: {username: playername, password: password},
        datatype: "text",
        doneFunction: function done(response) {
            console.log(response);
            console.log('Login done!');
            document.cookie = "token = " + response;
            document.cookie = "playername =" + playername;
            console.log(decodeCookie("token"));
            callback();
        },
        malFunction: function fail(xhr, status) {
            console.log('Login failed!');
            console.log(xhr.response);
            console.log(status);
            alert("Login ist fehlgeschlagen!");
        }
    });
}

/**
 * Diese Methode loggt den User aus.
 * @todo callback
 */
export function logout (callback){
    document.cookie = "token = ";
    console.log(document.cookie);
    callback();

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
    connection({
        path: updatePasswordPath,
        datatype: "POST",
        body: {"playername": decodeCookie("playername"), "oldpassword": oldPassword, "newpassword": newPassword},
        method: "text",
        doneFunction: function (response) {
            console.log(response);
            console.log('updatePassword done!');
            console.log(decodeCookie("token"));
            logout();
        },
        malFunction: function (xhr, status) {
            console.log('Login failed!');
            console.log(xhr);
            console.log(status);
            alert("Die Änderung ist fehlgeschlagen!");
        }
    })
}

/**
 * Diese Methode lädt die Kategorien vom Server und gib diese zurück.
 */
export function getCategory(callmeback) {

    {
        connection({
            path: catigroyPath,
            doneFunction: function (response) {
                console.log(response);
                //gamemode.displayCategory(response);
                callback(response);
            },
            malFunction: function (xhr, status) {
                console.log('highscorePath failed!');
                console.log(xhr);
                console.log(status);
            }
        })
    }
    function callback(response){
        callmeback(response);
    }
}

/**
 * Diese Methode lädt die auf dem Server hinterlegten Spielrunden (Anzahl der Fragen)
 * @param callmeback die Methoden die nach dem Laden
 */
export function getGameSize(callmeback) {
    {
        connection({
            path: roundsPath,
            doneFunction: function (response) {
                console.log(response);
                callback(response);
            },
            malFunction: function (xhr, status) {
                console.log('highscorePath failed!');
                console.log(xhr);
                console.log(status);
            }
        })
    }
    function callback(response){
        callmeback(response);
    }
}

/**
 * Diese Methode aktualisiert ein Spiel auf dem Server
 * @param id Die ID des Spiels
 * @param playerOne der Name des playerOne
 * @param playerTwo der Name des playerTwo
 * @param status der Status des Spiels (OPEN|CLOSE|RUNNING|JOINED)
 * @param callback die Function die gerufen werden soll, bei http 200
 */
export function updateGame(id,playerOne, playerTwo, status, callback){
    connection({
        path: updateGamePath,
        method: "PUT",
        body:{
            "gamesid": id,
            "playerone": playerOne,
            "playertwo": playerTwo,
            "status": status
        },
        doneFunction: function (response) {
            console.log()
            callback(response);
        },
        malFunction: function (xhr, status) {
            console.log('highscorePath failed!');
            console.log(xhr);
            console.log(status);
        }
    })
}

/**
 * Diese Methode holt die Statistik für ein Speiler vom Server
 * @param callmeback Die Function die ausgeführt werden soll wenn eine 200 kommt.
 * @todo CORS
 */
export function getStatistic(callback) {
    connection({
        method: "GET",
        path: historyPath + "?playername=" + decodeCookie("playername"),
        doneFunction: function (response) {
            callmeback(response);
        },
        malFunction: function (xhr, status) {
            callmeback(xhr);
            console.log('highscorePath failed!');
            console.log(xhr);
            console.log(status);
        }
    })
    function callmeback(response){
        console.log(response);
        let fakeResponse = [
            {
                "id": 15,
                "playername": {
                    "playerid": 111,
                    "username": "test",
                    "currentscore": 0,
                    "currentstatus": "ONLINE",
                    "highscore": {
                        "quizhighscoreid": 14,
                        "playername": "test",
                        "highscorepoints": 0,
                        "lastupdate": "2022-01-18T19:02:00.445679"
                    }
                },
                "playerscore": 2,
                "opponentscore": 1,
                "category": {
                    "quizcategoryid": 3,
                    "categoryname": "Mediendesign"
                },
                "rounds": {
                    "quizroundsid": 1,
                    "rounds": 5
                }
            },
            {
                "id": 16,
                "playername": {
                    "playerid": 111,
                    "username": "test",
                    "currentscore": 0,
                    "currentstatus": "ONLINE",
                    "highscore": {
                        "quizhighscoreid": 14,
                        "playername": "test",
                        "highscorepoints": 0,
                        "lastupdate": "2022-01-18T19:02:00.445679"
                    }
                },
                "playerscore": 3,
                "opponentscore": 2,
                "category": {
                    "quizcategoryid": 4,
                    "categoryname": "IT-Sicherheit"
                },
                "rounds": {
                    "quizroundsid": 2,
                    "rounds": 10
                }
            },
            {
                "id": 17,
                "playername": {
                    "playerid": 111,
                    "username": "test",
                    "currentscore": 0,
                    "currentstatus": "ONLINE",
                    "highscore": {
                        "quizhighscoreid": 14,
                        "playername": "test",
                        "highscorepoints": 0,
                        "lastupdate": "2022-01-18T19:02:00.445679"
                    }
                },
                "playerscore": 4,
                "opponentscore": 3,
                "category": {
                    "quizcategoryid": 5,
                    "categoryname": "Erdkunde"
                },
                "rounds": {
                    "quizroundsid": 3,
                    "rounds": 20
                }
            }
        ];
        let emptytext = [];
        callback(response);
    }
}

/**
 * Diese Function ruft die Spiele vom Server, ab die den Status offen haben.
 * @param callback die Function die gerufen werden soll, bei http 200
 */
export function getOpenGames(callback) {
    connection({
        path: openGamePath,
        doneFunction: function (response) {
            console.log(response);
            callmeback(response);
        },
        malFunction: function (xhr, status) {
            console.log('highscorePath failed!');
            console.log(xhr);
            console.log(status);
        }
    })
    function callmeback (response){
        callback(response);
    }
}

/**
 * Diese function erstellt ein Spiel auf dem Server
 * @param {*} category Die Kategorie als String
 * @param {*} size Die anzahl der Runden als Zahl ohne ''
 * @param callback Die Function die ausgeführt werden soll sobalt eine 200 ergebnis da ist.
 */
export function createGame(category, size, callback) {
    console.log(category, size);
    connection({
        path: createGamePath,
        method:"POST",
        body: {
            "username" : decodeCookie("playername"),
            "category":  category,
            "rounds" : size
        },
        doneFunction: function (response) {
            callmeback(response);
        },
        malFunction: function (xhr, status) {
            console.log('CreateGame failed!');
            console.log(xhr);
            console.log(status);
        }
    })
    function callmeback(response) {
        callback(response);
    }
}

/**
 * Diese function holt den Highscore vom Server
 * @returns
 */
export function getHighscore(callback) {
    connection({
        path: highscorePath,
        doneFunction: function (response) {
            callmeback(response);
        },
        malFunction: function (xhr, status) {
            console.log('highscorePath failed!');
            console.log(xhr);
            console.log(status);
        }
    })
    function callmeback(response){
        callback(response);
    }
}

/**
 * Diese Function löscht die Statistik eines Users.
 * @param callback Die Function die ausgeführt werden soll sobalt eine 200 ergebnis da ist.
 * @todo CORS
 */
export function deleteStatistics(callback) {

    connection({
        method: "PUT",
        path: historydeletePath + "?playername=" + decodeCookie("playername"),
        doneFunction: function (response) {
            callmeback(response);
        },
        malFunction: function (xhr, status) {
            callmeback(status);
            console.log('highscorePath failed!');
            console.log(xhr);
            console.log(status);
        }
    })
    function callmeback(response){
        callback(response);
    }
}

/**
 * Diese function lädt ein Base64 encodetet Bild
 * @param data
 */
export function updatePicture(data) {
    //@todo
}

/********************************************* @todo */ /******************************************************* */
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
export function getLocalUser() {
    //@todo
    return [1, "Andy", "42", "42", "https://hub.dummyapis.com/image?text=Test&height=120&width=120", "online"];

}

