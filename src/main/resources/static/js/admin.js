var lista = document.getElementById("list");
var conf = document.getElementById("conf");
var per = document.getElementById("percentuali")


function showConferma() {
    console.log('wewe')
    lista.style.display = "none";
    per.style.display = "none";
    console.log('wewe')
    conf.style.display = "flex";
}

function hideConferma() {
    lista.style.display = "flex";
    per.style.display = "flex";
    conf.style.display = "none";
}