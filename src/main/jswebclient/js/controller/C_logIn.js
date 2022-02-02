import * as apiCalls from "../model/M_model.js";
import * as mainMenu from "../view/V_mainMenu.js";
import {decodeCookie} from "../model/M_model.js";

/**
 *  Diese function holt aus 2 gegeben Elementen den Inhalt und Log damit den nutzer ein.
 */
export function runLogIn(idUserName, idPassword) {

    let userName = document.getElementById(idUserName).value;
    let password = document.getElementById(idPassword).value;
    apiCalls.logInUser(userName, password, (r,err)=>{
        if(err === undefined){

            document.cookie = "token = " + r;
            document.cookie = "playername =" + userName;
            console.log(decodeCookie("token"));
            mainMenu.show();
        }else {
            console.log(err);
            alert("Login ist fehlgeschlagen!");
        }

    }
    );
}