import * as apiCalls from '../model/M_model.js';
import * as mainMenu from '../view/V_mainMenu.js';

/**
 * Dies function holt aus einem Input eine Bild und mach daraus ein Base 64 String und passt diese für die Datenbank an.
 */
export function base64Picture() {
    const data = file => new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => resolve(reader.result);
        reader.onerror = error => reject(error);
    });

    $('#button').click(function() {
        const uploadedFile = document.querySelector('#inputFile').files[0];
        data(uploadedFile)
            .then(res => {
                let cutRes = res.split(',');
                let data = cutRes[1];
                apiCalls.updatePicture(data, function (){
                    mainMenu.show();
                });
            })
            .catch(err => {
                alert("Das hat leider nicht geklappt" + err);
                console.log(err);
            })
    });
}