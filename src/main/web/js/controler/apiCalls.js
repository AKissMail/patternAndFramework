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