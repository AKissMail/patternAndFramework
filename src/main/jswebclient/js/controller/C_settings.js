import * as apiCalls from '../model/M_model.js';
import * as choice from '../view/V_start.js';

export function updatePassword(){
    let oldPassword = prompt("Bitte geben Sie ihr altes password ein.")
    let newPassword = prompt("Bitte geben Sie ein neues Passwort ein.");
    let newPassword2 = prompt("Bitte geben Sie das Passwort erneut ein.");
    if(newPassword === newPassword2){
        apiCalls.updatePassword(oldPassword,newPassword, function (){apiCalls.logout(choice.show)});
    }else{
        alert("Die Passwörter stimmen nicht überein!");
    }
}

export function deleteStatistics (){
    if(confirm("Möchten Sie wirklich alle daten Löschen?")){
        apiCalls.deleteStatistics(console.log);
    }
}