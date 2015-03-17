function equalPass() {
    if (document.registerForm.newPass.value == document.registerForm.newPassConf.value) {
        document.getElementById("err-pass").style.display = "none";
        return true;
    }
    else {
        document.getElementById("err-pass").style.display = "block";
        return false;
    }
}

function equalChgPass() {
    if (document.passchgForm.newPass.value == document.passchgForm.newPassConf.value) {
        document.getElementById("err-pass").style.display = "none";
        return true;
    }
    else {
        document.getElementById("err-pass").style.display = "block";
        return false;
    }
}

function checkMail() {
    var exp = /^[_a-z0-9]+(\.[_a-z0-9]+)*@[a-z0-9]+(\.[a-z0-9]+)*(\.[a-z]{2,4})$/i;

    if (exp.test(document.registerForm.newEmail.value)) {
        document.getElementById("err-email").style.display = "none";
        return true;
    }
    else {
        document.getElementById("err-email").style.display = "block";
        return false;
    }
}

function allChecks() {
    if (checkMail() && equalPass()) {
        return true;
    }
    else {
        return false;
    }
}