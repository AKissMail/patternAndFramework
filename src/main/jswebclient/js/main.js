import * as choice from "./view/V_start.js";
import * as model from "./model/M_model.js";
import * as mainMenu from "./view/V_mainMenu.js";
import * as quiz from "./view/V_quiz.js";

/**
 * Das ist die start function.
 */
(()=> {
           model.getGameByID("1", quiz.show);
/**
            if (model.decodeCookie("token")==="") {
                choice.show();
            } else {
                mainMenu.show();
            }
*/

}
)
(); // und das ist der Aufruf.