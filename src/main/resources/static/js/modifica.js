function validateTitolo(titolo) {
    if (titolo.value.length != 0 && titolo.value.length <= 255) {
        console.log("La lunghezza del titolo è: " + titolo.value.length)
        return true;
    }
    return false
}

function validateArgomento(obj) {
    var titolo = document.getElementById('titolo');
    if (validateTitolo(titolo)) {
        console.log("va")
        return true
    } else {
        return false;
    }
}


