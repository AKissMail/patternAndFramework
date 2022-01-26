import * as M_model from "../model/M_model.js";
import * as mainMenu from "../view/V_mainMenu.js";


export function loop (counter, IfYes,elseYes,IfYesFunction, elseYesFunction){
   if(!counter == 1){
        setTimeout(function(){
            M_model.getGameByID(M_model.decodeCookie("gameID"), function (result){
                if (result.valueOf().gamestatus ==IfYes) {IfYesFunction();}
                else if (result.valueOf().gamestatus == elseYes) {elseYesFunction()}
                else {loop(counter - 1);}
            });
        }, 1000);
    }
}
/**
 * Das ist eine Fehlermeldung.
 */

export function leavingGame(ErrorMessage){
    alert(ErrorMessage);
    mainMenu.show();
}