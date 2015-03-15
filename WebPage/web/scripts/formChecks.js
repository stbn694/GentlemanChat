function equalPass(){
    if(document.registerForm.newPass.value == document.registerForm.newPassConf.value){
        document.getElementById("err-pass").style.display="none";
        document.getElementById("register").disabled=false;
    }
    else{
        document.getElementById("err-pass").style.display="block";
        document.getElementById("register").disabled=true;
    }
}

function checkMail(){
	var exp=/^[_a-z0-9]+(\.[_a-z0-9]+)*@[a-z0-9]+(\.[a-z0-9]+)*(\.[a-z]{2,4})$/i;
        
	if(exp.test(document.registerForm.newEmail.value)){
		document.getElementById("err-email").style.display="none";
                document.getElementById("register").disabled=true;
	}
	else{
		document.getElementById("err-email").style.display="block";
                document.getElementById("register").disabled=false;
	}
}