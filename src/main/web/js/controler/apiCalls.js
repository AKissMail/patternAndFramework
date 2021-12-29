/**
 * Hier ist noch richtig viel zu tun...
 */

// apiCalls.js
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
        return true;

    } else if(token.length !== 0){
        console.log("apiCalls_checkToken false");
        return false;

    }
    else {
        console.log("apiCalls_checkToken true");
        return true;
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