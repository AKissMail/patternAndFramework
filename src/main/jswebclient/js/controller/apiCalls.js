import * as choice from '../view/choice.js';

/**
 * Diese Methode erstellt einen neuen Spieler.
 * @param {*} user
 * @param {*} password
 */
export function createPlayer(user, password) {
    alert("todo");
    //@todo
    return true;
}

/**
 * Diese Methode loggt den Spieler ein.
 * @param {*} user
 * @param {*} password
 * @returns
 */
export function logInUser (user, password){
    alert("todo");
    return true;
}

/**
 * Diese Methode gibt die Daten eines lokalen Spielers zurück.
 */
export function getLocalUser(){
    return [1, "Andy", "42", "42", "https://hub.dummyapis.com/image?text=Test&height=120&width=120", "online"];

}

/**
 * Diese Methode loggt den User aus.
 */
export function logout (){
    //todo
    choice.show();
}

/**
 * Diese Methode läd die katigurien vom Server und gib diese zurück.
 */
export function getCatigroy(){
    return ["DemoA", "DemoB", "DemoC"]; // todo hier muss die funkion alle katigurie hin.
}
/**
 *
 */
export function getGameSize (){
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
 * @param {*} password
 */
export function updatePassword(password){
    //tod!
}
/**
 *
 * @param {*} data
 */
export function updatePicture(data){
    const endpoint = "./img";
    fetch(endpoint,{
        method: "post",
        body: data
    }).catch(console.error);
}
/**
 *
 * @returns
 */
export function getHighscore(){
    return [["User A", 650], ["User B", 550],["User C", 450], ["User D", 350], ["User F", 250], ["User G", 150], ["User H", 50], ["User I", 0]];
}
/**
 *
 * @returns
 */
export function getStatistic(){
    return [["User A", 650,300], ["User B", 550,350],["User C", 450,400], ["User D", 350,370], ["User F", 250,220], ["User G", 150,200], ["User H", 50,500], ["User I", 0,50]];
}
/**
 *
 * @returns
 */
export function getOpenGames(){
    return  [["User A","DemoA",20,1],["User B","DemoB",10,2],["User C","DemoC",20,3],["User D","DemoD",10,4]];
}
/**
 *
 * @param {*} category
 * @param {*} size
 * @returns
 */
export function createGame(category, size){
    return 10;
}
/**
 *
 * @param {*} category
 * @param {*} size
 * @returns
 */
export function getMyQuestions(category, size){
    let allquestion =getallquestion(category);
    let question = [];
    for(let i = 0; i<size; i++){
        question.push(allquestion[Math.floor(Math.random() * size)]);
    }
    return question;
}
/**
 *
 * @param {*} c
 * @returns
 */
function getallquestion(c){

    return [
        {"question":"FrageA","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageB","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageC","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageD","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageF","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageG","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageH","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageI","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageJ","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageK","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageL","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageM","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageN","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageO","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageP","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageQ","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageR","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageS","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageT","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageU","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageV","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageW","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageX","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageY","a":"A","b":"B","c":"C","d":"D"},
        {"question":"FrageZ","a":"A","b":"B","c":"C","d":"D"}
    ];
    //todo
}
export function submitAnswer(bool, time, gameID){
    console.log(bool, time, gameID);
}
export function getResult(gameID){
    return {"done": true, "localPoint": 750, "remodePoint": 650, "nameOponent": "Hans"};
    //return {"done": false, "localPoint": 750, "remodePoint": null}
}