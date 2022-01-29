import * as M_model from "../model/M_model.js";
import * as mainMenu from "../view/V_mainMenu.js";

/**
 * Dies Function setzt ein loop um der auf dem Server bis zu 2 Status von einem Spiel prüft und entsprechende eine
 * function ruft, wenn diese zutrifft.
 *
 * @param counter Eine Limiting der Anfragen
 * @param IfYes Status1
 * @param elseYes Status2
 * @param IfYesFunction Wenn der Status1 == den Spiel staus ist, wirs diese Function ausgeführt.
 * @param elseYesFunction Wenn der Status2 == den Spiel staus ist, wirs diese Function ausgeführt.
 */
export function loop (counter, IfYes,elseYes,IfYesFunction, elseYesFunction){
   if(!counter == 1){
       console.log(counter);
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