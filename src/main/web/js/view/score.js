
function displaySore(){
    let games = [[1, "User A", 650],[2, "User A", 650]]//apiGetHishscore();

    let backHome = document.createElement("p");
    backHome.append("Hauptmenü");
    document.getElementsByTagName("nav")[0].appendChild(backHome);

    let highscoreHading = document.createElement("h1");
    highscoreHading.append("Highscore");
    let highscoreHadingDescription = document.createElement("p");
    highscoreHadingDescription.append(games.length+" Spielrunden gefunden");
    document.getElementsByTagName("article")[0].appendChild(highscoreHading);
    document.getElementsByTagName("article")[0].appendChild(highscoreHadingDescription);


    let scoreTabel= document.createElement("table");
    scoreTabel.setAttribute("id", "scoreTabel");
    let scoreTabelHeading = document.createElement("tr");
    let scoreTabelHeadingPlatz = document.createElement("Platz");
    let scoreTabelHeadingUser = document.createElement("User");
    let scoreTabelHeadingPunkte = document.createElement("Punkte");
    scoreTabelHeading.appendChild(scoreTabelHeadingPlatz);
    scoreTabelHeading.appendChild(scoreTabelHeadingUser);
    scoreTabelHeading.appendChild(scoreTabelHeadingPunkte);
    scoreTabel.appendChild(scoreTabelHeading);

    for (let i= 1; i <= games.length; i++){
       let row = document.createElement("tr");
       let ranking = document.createElement("td");
       let user = document.createElement("td");
       let score = document.createElement("td");
       ranking.append(i.toString());
       user.append(games[i-1][1].toString());
       score.append((games[i-1][2]).toString());

        row.appendChild(ranking);
        row.appendChild(user);
        row.appendChild(score);
        scoreTabel.appendChild(row);
    }
    document.getElementsByTagName("article")[0].appendChild(scoreTabel);

}