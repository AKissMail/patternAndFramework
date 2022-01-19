import * as choice from "./view/choice.js";
import {decodeCookie} from "./controller/apiCalls.js";
import * as mainMenu from "./view/mainMenu.js";

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
();