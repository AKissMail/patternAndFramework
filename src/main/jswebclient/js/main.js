import * as choice from "./view/V_choice.js";
import {decodeCookie} from "./model/M_model.js";
import * as mainMenu from "./view/V_mainMenu.js";

/**
 * Das ist die start function.
 */
(()=> {
            if (decodeCookie("token")==="") {
                choice.show();
            } else {
                mainMenu.show();
            }
}
)
(); // und das ist der Aufruf.