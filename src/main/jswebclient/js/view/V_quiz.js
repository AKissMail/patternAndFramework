import * as controller from  '../controller/C_controller.js';
import * as quizController from '../controller/C_quiz.js'
import * as model from '../model/M_model.js';
import * as lobbyResult from './V_lobbyResult.js';
import * as view from './V_view.js';
import * as mainMenu from './V_mainMenu.js';

/**
 * Mit dieser Function wird die Frage sowie die Antworten angezeigt. @todo nochmaldurchdenken
 * @param round Das Spiel als JSON
 */
let send = false;
export function show(round){
    let game = round;
    if (game.valueOf().rounds.valueOf().rounds > 0) {
        game.valueOf().rounds.valueOf().rounds = game.valueOf().rounds.valueOf().rounds - 1;
        counter(5, game);
        let start = new Date();
        let answersArray = quizController.mixArray([game.valueOf().questions[game.valueOf().rounds.valueOf().rounds].correctAnswer, game.valueOf().questions[game.valueOf().rounds.valueOf().rounds].falseAnswer1,
                                           game.valueOf().questions[game.valueOf().rounds.valueOf().rounds].falseAnswer2,  game.valueOf().questions[game.valueOf().rounds.valueOf().rounds].falseAnswer3]);
        let wrapper = view.createGenericElementWithTwoAttribute("span", "id", "wrapperQuestion", "class", "btnGrid");
        for(let i = 0; i < answersArray.length; i++){
            wrapper.append(view.createQuestionButton("button", i,"btn btn-primary", answersArray[i], ));
        }

        controller.clearStage();
        document.getElementsByTagName("header")[0].appendChild(view.createNavBar("exitGame", "Beenden", "btn btn-light navbar-brand","navbar navbar-light bg-primary"));
        document.getElementsByTagName("nav")[0].appendChild(view.createGenericElementWithTwoAttribute("span", "id", "timer", "class", "icon"));
        document.getElementById("timer").append(view.createGenericText("p", "5"));

        document.getElementsByTagName("article")[0].appendChild(view.createGenericText("h1", game.valueOf().questions[game.valueOf().rounds.valueOf().rounds].valueOf().question));
        document.getElementsByTagName("article")[0].appendChild(wrapper);


        document.getElementById("0").addEventListener('click', ()=>{evaluate( document.getElementById("0").value, game, start)});
        document.getElementById("1").addEventListener('click', ()=>{evaluate( document.getElementById("1").value, game, start)});
        document.getElementById("2").addEventListener('click', ()=>{evaluate( document.getElementById("2").value, game, start)});
        document.getElementById("3").addEventListener('click', ()=>{evaluate( document.getElementById("3").value, game, start)});
        document.getElementById("exitGame").addEventListener('click', ()=>{
            if (model.decodeCookie("isplayerone")){
                model.updateGame(game.valueOf().id, model.decodeCookie("playername"),"" ,"CLOSE", mainMenu.show);
            }else {
                model.updateGame(game.valueOf().id, "",model.decodeCookie("playername") ,"CLOSE", mainMenu.show);
            }
        })
    }else {
        lobbyResult.show(game);
    }
}


/**
 * Diese Function prüft ob die gegebene Antwort korrekt ist.
 * @param answer Die geben Antwort.
 * @param game Das Spiel als JSON.
 * @param start Der Zeitpunkt der ersten Anzeige.
 */
    function evaluate(answer, game, start){
        send = true;
        if (answer == game.valueOf().questions[game.valueOf().rounds.valueOf().rounds].correctAnswer){
            model.sendAnswer(game.valueOf().id, model.decodeCookie("isplayerone"), true, new Date()-start, ()=>{
                show(game);
            } );
        }else {
            model.sendAnswer(game.valueOf().id, model.decodeCookie("isplayerone"), false, new Date()-start, ()=>{
                show(game);
            });
        }
    }

/**
 * Diese Function setzt den Countdown um. Nachdem der Countdown auf 0 gelaufen ist, wird als falsch Antwort gesendet.
 * @param i Die Anzahl der Sekunden für den Countdown,
 * @param game das Spiel als JSON,
 */
function counter(i, game){
    setTimeout(()=>{
        if(i>=0) {
            if (!send){
            let timer = document.getElementById("timer");
                timer.removeChild(timer.childNodes[0]);
                timer.append(view.createGenericText("p", i));
            counter(i - 1, game);
            }
        }else if (!send) {
            alert("Die Zeit ist abgelaufen!");
            model.sendAnswer(game.valueOf().id, model.decodeCookie("isplayerone"), false, 5000, () => {
                show(game);
            });
        }
    }, 1000)
    }