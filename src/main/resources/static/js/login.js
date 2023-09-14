let siginBtn = document.querySelector('.siginBtn');
let sigupBtn = document.querySelector('.sigupBtn');
let body = document.querySelector('body');

sigupBtn.onclick = function (){
    body.classList.add('slide');
}

siginBtn.onclick = function (){
    body.classList.remove('slide');
}


let passwordLog = document.getElementById('passwordLog');
let passwordReg = document.getElementById('passwordReg');
let occhioLog = document.getElementById('occhioLog');
let occhioReg = document.getElementById('occhioReg');

function showHide(password){
    if(password)
    {
        if(passwordLog.type == 'password')
        {
            passwordLog.setAttribute('type', 'text');
            occhioLog.src = 'hide.png';
        }
        else
        {
            passwordLog.setAttribute('type', 'password');
            occhioLog.src = 'view.png';
        }
    }

    if(!password)
    {
        if(passwordReg.type == 'password')
        {
            passwordReg.setAttribute('type', 'text');
            occhioReg.src = 'hide.png';
        }
        else
        {
            passwordReg.setAttribute('type', 'password');
            occhioReg.src = 'view.png';
        }
    }
}