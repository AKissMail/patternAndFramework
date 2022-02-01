import * as base from "../controller/C_controller.js";
import * as apiCalls from "../model/M_model.js";
import * as gameModus from "./V_gameModus.js";
import * as lobbyNewGame from "./V_lobbyNewGame.js";
import * as view from "./V_view.js";

/**
 * Diese Function setzt ein Zurück button in den Dom und ruft am Ende die apiCalls.getCategory auf um die Kategorien
 * vom Server zu laden.
 */
export function showNewGamePreload(){
    base.clearStage();
    document.getElementsByTagName("header")[0].appendChild(view.createNavBar("nweGameBackHome", "zurück", "btn btn-light navbar-brand","navbar navbar-light bg-primary"));
    apiCalls.getCategory(displayCategory);
}

/**
 * Die Function stelle die vom Server abrufen Kategorien dar und ruft vom Server die Anzahl der Runden ab.
 * @param category die Kategorien als JSON
 */
export function displayCategory(category){
    let from = view.createGenericText("form", view.createGenericText("label", "Wählen sie eine Kategorie"));
    let select = view.createGenericElementWithTwoAttribute("select", "id", "category", "class", "form-select form-select-lg mb-3");
    for (let i = 0; i < category.length; i++) {
        let option = view.createOption(category[i].valueOf().categoryname, category[i].valueOf().categoryname);
        select.appendChild(option);
    }
    from.appendChild(view.createBarak());
    from.appendChild(select);
    from.appendChild(view.createBarak());
    from.appendChild(view.createGenericText("label", "Wählen sie die anzahl der Fragen"));
    from.appendChild(view.createBarak());
    document.getElementsByTagName("article")[0].appendChild(from);
    apiCalls.getGameSize(displayRounds);
}

/**
 * Diese Function stellt die Runden dar welche vom Server abrufen wurde.
 * @param rounds Die runden anzahl als JSON.
 */
export function displayRounds (rounds){
    let select =view.createGenericElementWithTwoAttribute("select", "id", "length", "class", "form-select form-select-lg mb-3");
    for (let i = 0; i < rounds.length; i++){
        let option = view.createOption(rounds[i].valueOf().rounds,rounds[i].valueOf().rounds);
        select.appendChild(option);
    }
    document.getElementsByTagName("form")[0].appendChild(select);
    document.getElementsByTagName("form")[0].appendChild(view.createBarak());
    document.getElementsByTagName("form")[0].appendChild(view.createInput("input", "button", "", "Senden", "", "submit", "btn btn-primary"));
    document.getElementById("nweGameBackHome").addEventListener("click", gameModus.show);
    document.getElementsByTagName("input")[0].addEventListener("click",()=>{
        apiCalls.createGame(document.getElementById("category").value, document.getElementById("length").value, lobbyNewGame.show)
    });
}