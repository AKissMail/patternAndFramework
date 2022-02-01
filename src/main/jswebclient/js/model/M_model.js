/**
 * In dieser Datei werden die Abfragen af die REST Api gemacht. Daneben wird hier auch auf die Cookies zugegriffen.
 * Dort wird der Token, der Nutzername und die Rolle des Users gespeichert.
 */
//die URI
const serverURL =               'http://localhost:8080';
const uploadthumbnailstrPath =  '/player/uploadthumbnailstr';
const loginPath =               '/auth/login';
const registrationPath =        '/auth/register';
const updatePasswordPath =      '/auth/updatepassword';
const categoryPath =            '/category';
const getGameByIDPath =         '/games/'
const openGamePath =            '/games/open';
const updateGamePath =          '/games/update';
const createGamePath =          '/games/create';
const sendAnswerPath =          '/games/dropanswer';
const historyPath =             '/games/history';
const historydeletePath =       '/games/historydelete';
const highscorePath =           '/highscore';
const roundsPath =              '/rounds';
const getthumbnailPath =        '/player/getthumbnailbyname?playername=';

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
 * Diese Function ruft Bilder von der API ab. Es kommt Base 64 zurück.
 * @param name Der Name des Users
 * @param callback Die Callback function.
 */

export function getPicture (name,callback){
    connection({
        path: getthumbnailPath+name,
        datatype: "text",
        doneFunction: function done(response) {
            callback(response);
        },
        malFunction: function fail(xhr, status) {
            callback(xhr, status);
        }
    });
}

/**
 * Diese Methode erstellt einen neuen Spieler.
 * @param player der name von dem User
 * @param password das Passwort von dem User
 * @param callback
 */
export function createPlayer(player, password,callback) {
    connection({
        path: registrationPath,
        method: "POST",
        body: {username: player, password: password},
        datatype: "text",
        doneFunction: function done(response) {
            callback(response);
        },
        malFunction: function fail(xhr, status) {
            callback(xhr, status);
        }
    });
}

/**
 * Diese Methode loggt den Spieler ein.
 * @param playername Spielername
 * @param password Passwort
 * @param callback
 */
export function logInUser(playername, password, callback) {
    connection({
        path: loginPath,
        method: "POST",
        body: {username: playername, password: password},
        datatype: "text",
        doneFunction: function done(response) {
            callback(response);
        },
        malFunction: function fail(xhr, status) {
            callback(xhr, status)
        }
    });
}

/**
 * Diese Methode loggt den User aus.
 */
export function logout (callback){
    document.cookie = "token = ";
    callback();

}

/**
 * Diese Methode holt aus dem document.cookie den zugehörigen Wert raus und gibt diesen zurück.
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
 * Diese Methode lädt die Kategorien vom Server und gib diese zurück.
 * @param callback
 */
export function getCategory(callback) {
    {
        connection({
            path: categoryPath,
            doneFunction: function (response) {
                console.log(response);
                callback(response);
            },
            malFunction: function (xhr, status) {
                callback(xhr, status);
            }
        })
    }
}

/**
 * Diese Methode lädt die auf dem Server hinterlegten Spielrunden (Anzahl der Fragen)
 * @param callback die Methoden die nach dem Laden
 */
export function getGameSize(callback) {
    {
        connection({
            path: roundsPath,
            doneFunction: function (response) {
                callback(response);
            },
            malFunction: function (xhr, status) {
                callback(xhr, status);
            }
        })
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
            callback(response);
        },
        malFunction: function (xhr, status) {
            callback(xhr, status);
        }
    })
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
            callback(response);
        },
        malFunction: function (xhr, status) {
            callback(xhr, status);
        }
    })
}

/**
 * Diese function erstellt ein Spiel auf dem Server
 * @param {*} category Die Kategorie als String
 * @param {*} size Die anzahl der Runden als Zahl ohne ''
 * @param callback Die Function die ausgeführt werden soll, wenn das Ergebnis da ist.
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
            callback(response);
        },
        malFunction: function (xhr, status) {
            callback(xhr, status);
        }
    })
}

/**
 * Diese function lädt ein Bild als Base64 encoding hoch.
 * @param data
 * @param callback
 */
export function updatePicture(data, callback) {
    connection({
        path: uploadthumbnailstrPath,
        body: {"file": data, "playername": decodeCookie("playername")},
        method: "POST",
        doneFunction: function (response) {
            console.log(response);
            callback(response);
        },
        malFunction: function (xhr, status) {
            callback(xhr, status);
        }
    })
}

/**
 * Diese function setzt ein neues Password
 * @param oldPassword das alte Password
 * @param newPassword das neue Password
 * @param callback
 */
export function updatePassword(oldPassword, newPassword, callback) {
    connection({
        path: updatePasswordPath,
        datatype: "Text",
        body: {"playername": decodeCookie("playername"), "oldpassword": oldPassword, "newpassword": newPassword},
        method: "POST",
        doneFunction: function () {
            callback();
        },
        malFunction: function (xhr, status) {
            callback(xhr, status);
        }
    })
}


/**
 * Diese Function löscht die Statistik eines Users. – Nicht erschrecken, falls 404 geworfen wird.
 * Dann sind keine Daten in der DB gewesen.
 * @param callback Die Function die ausgeführt werden soll cobalt eine 200 ergebnis da ist.
 */
export function deleteStatistics(callback) {

    connection({
        method: "PUT",
        path: historydeletePath + "?playername=" + decodeCookie("playername"),
        doneFunction: function (response) {
            callback(response);
        },
        malFunction: function (xhr, status) {
            callback(xhr, status);
        }
    })
}

/**
 * Diese function holt den Highscore vom Server
 * @returns
 */
export function getHighscore(callback) {
    connection({
        path: highscorePath,
        doneFunction: function (response) {
            callback(response);
        },
        malFunction: function (xhr, status) {
            callback(xhr, status);
        }
    })
}

/**
 * Diese Methode holt die Statistik für einen User vom Server
 * @param callback Die Function die ausgeführt werden soll, wenn eine 200 kommt.
 */
export function getStatistic(callback) {
    connection({
        method: "GET",
        path: historyPath + "?playername=" + decodeCookie("playername"),
        doneFunction: function (response) {
            callback(response);
        },
        malFunction: function (xhr, status) {
            callback(xhr, status);
        }
    })
}

/**
 * Diese function läd ein Spiel vom Server nach einer gegebenen ID und gib diese in einen Callback zurück.
 * @param id Die ID des gesuchten Spiels
 * @param callback die callback function
 */
export function getGameByID(id, callback){

    connection({
        method: 'GET',
        path: getGameByIDPath+id,
        doneFunction: function (response){
            callback(response);
        }, malFunction: function (xhr, status) {
            callback(xhr, status);
        }
    })
}

export function sendAnswer(gameID, isPlayerOne, answer, time, callback){

    connection({

        method: "PUT",
        path: sendAnswerPath,
        body: {
            "gamesid": gameID,
            "isplayerone": isPlayerOne,
            "answers":answer,
            "time": time
        },
        doneFunction: (response)=>{
            callback(response);

        },
        malFunction: (xhr, status)=>{
            callback(xhr, status)
        }
    })
}