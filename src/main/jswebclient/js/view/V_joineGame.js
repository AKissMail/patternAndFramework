import * as controller from "../controller/C_controller.js";
import * as model from "../model/M_model.js";
import * as gameModus from "./V_gameModus.js";
import * as lobbyEnterGame from "./V_lobbyEnterGame.js";
import * as view from './V_view.js';

/**
 * Dies Function stellt das Formular dar, in welchen man ein Spiel auswählen kann.
 * @param games die offen Spiele vom Server.
 */
export function showEnterGame(games){
   if(!games.length == 0) {
        controller.clearStage();

        let form = view.createGenericText("form", view.createGenericText("label", "Wählen Sie ein Spiel aus"));
        let select = view.createGenericElementWithTwoAttribute("select", "class", "form-select form-select-lg mb-3", "id", "selectGame");
        for (let i = 0; i < games.length; i++) {
            let option = view.createOption(i, "Thema: " + games[i].valueOf().category.valueOf().categoryname + ", Fragen: " + games[i].valueOf().rounds.rounds + ", gegen: " + games[i].valueOf().playerone.valueOf().username);
            option.setAttribute("id", games[i].valueOf().id);
            select.appendChild(option);
        }
        form.appendChild(select);
        form.appendChild(view.createBarak());
        form.appendChild(view.createInput("input", "button", "", "Spiel starten", "", "submit", "btn btn-primary"));

        document.getElementsByTagName("article")[0].appendChild(view.createGenericText("h1", "Es wurden " + games.length + " gefunden."));
        document.getElementsByTagName("header")[0].appendChild(view.createNavBar("joinGameBackHome", "zurück", "btn btn-light navbar-brand","navbar navbar-light bg-primary"));

        document.getElementById("joinGameBackHome").addEventListener("click", gameModus.show);
        document.getElementsByTagName("article")[0].appendChild(form);
        document.getElementsByTagName("input")[0].addEventListener("click", () => {
            model.updateGame(
                games[document.getElementById("selectGame").value].valueOf().id,
                "",
                model.decodeCookie("playername"),
                "JOINED",
                lobbyEnterGame.show
            );
        });
   }else {
        controller.clearStage();
        document.getElementsByTagName("header")[0].appendChild(view.createNavBar("joinGameBackHome", "zurück", "btn btn-light navbar-brand","navbar navbar-light bg-primary"));
        document.getElementById("joinGameBackHome").addEventListener("click", gameModus.show);
        document.getElementsByTagName("article")[0].appendChild(view.createGenericText("h1", "Es gibt grade keine Spiele, Schau später nochmal vorbei!"));
    }
}