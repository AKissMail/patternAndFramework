export function GetToken(user, password, url){
    if(url=== "0"){
        return "demo"
    }else{
        //@todo hier muss der JWT geholt werden
    }
}

export function GetOwnHighscore (user, token){
    if(token === "demo"){
        return [[1, "User A", 650],[2, "User A", 650]];
    }else{
        //@todo hier muss der Highscore des local spielers geholt werden
    }
}



/**
 * @todo
 * @returns {undefined}
 */
function deleteStatistics() {
    alert("deleteStatistics()");
    return undefined;
}

/**
 * @todo
 * @returns {undefined}
 */
function updatePassword() {

    return undefined;
}

/**
 * @todo
 * @returns {undefined}
 */
function updatePicture() {
    return undefined;
}
