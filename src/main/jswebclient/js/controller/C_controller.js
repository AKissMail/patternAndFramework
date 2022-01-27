/**
 * Die Methode macht den DOM Leer und setzt ein minimales Gerüst in den DOM ein. Dieses Gerüst wird von
 * den show function erwartet. Das letztendliche Resultat schaut wie die ursprüngliche index.html aus.
 * Lediglich der onload event Listener ist nicht enthalten.
 */
export function clearStage() {

    document.querySelector("header").remove();
    document.querySelector("main").remove();
    document.querySelector("footer").remove();
    // die struktur wir neu aufgebaut
    let header = document.createElement("header");
    let nav = document.createElement("nav");
    let main = document.createElement("main");
    let article = document.createElement("article");
    let footer = document.createElement("footer");
    header.appendChild(nav);
    main.appendChild(article);
    document.getElementsByTagName("body")[0].appendChild(header);
    document.getElementsByTagName("body")[0].appendChild(main);
    document.getElementsByTagName("body")[0].appendChild(footer);
}


export function startGame(category, size) {
 //@todo
}
