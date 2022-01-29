import * as choice from "./view/V_start.js";
import * as model from "./model/M_model.js";
import * as mainMenu from "./view/V_mainMenu.js";

/**
 * Das ist die start function.
 */
(()=> {
            if (model.decodeCookie("token")==="") {
                choice.show();
            } else {
                mainMenu.show();
            }
}
)
(); // und das ist der Aufruf.