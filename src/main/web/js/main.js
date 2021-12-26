// controller
import * as apiCalls from './controler/apiCalls.js';
import * as base from './controler/base.js';
// model
import * as game from './model/game.js';
import * as player from './model/player.js';
import * as question from './model/question.js';
// view
import * as category from './view/category.js';
import * as highscore from './view/highscore.js';
import * as lobby from './view/lobby.js';
import * as logIn from './view/logIn.js';
import * as mainMenue from './view/mainMenue.js';
import * as opponentFound from './view/oponentView.js';
import * as quiz from './view/quiz.js';
import * as result from './view/result.js';
import  * as settings from './view/settings.js';

function init(){
    logIn.show();
}



