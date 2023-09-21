var lista = document.getElementById("list");
var arg = document.getElementById("argomentoRimosso");
var meta = document.getElementById("mammeta");
var gioco = document.getElementById("giocoRimosso");


function showConfermaArg(key) {
    console.log("è stata chiamata l'argomento con keyword: " + key.id);
    document.getElementById('meta').value = key.id;
    lista.style.display = "none";
    arg.style.display = "flex";
}

function hideConfermaArg() {
    lista.style.display = "flex";
    arg.style.display = "none";
}

function showConfermaMeta(key) {
    console.log("è stata chiamata la meta-info con keyword: " + key.id);
    document.getElementById('meta').value = key.id;
    lista.style.display = "none";
    meta.style.display = "flex";
}

function hideConfermaMeta() {
    lista.style.display = "flex";
    meta.style.display = "none";
}

function showConfermaGioco() {
    console.log("è stata chiamata il gioco con nome: " + key.id);
    document.getElementById('meta').value = key.id;
    lista.style.display = "none";
    gioco.style.display = "flex";
}

function hideConfermaGioco() {
    lista.style.display = "flex";
    gioco.style.display = "none";
}