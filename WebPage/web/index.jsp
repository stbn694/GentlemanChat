<%-- 
    Document   : index
    Created on : 12-mar-2015, 20:54:04
    Author     : Esteban Gerpe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="./images/favicon.png">
        <title>Chatina</title>
    </head>
    <body>
        <div class="title-container">
            <div class="logo-container">
                <img src="./images/logo.jpg">
            </div>
            <div class="name-container">
                <p>Chatina</p>
            </div>
        </div>
        <div class="content">
            <div class="login-container">
                <p>Inicia sesión</p>
                <form id="login-form" method="post" action="./login">
                    <div>
                        <input type="text" id="nick" name="nick" autofocus="autofocus">
                    </div>
                    <div>
                        <input type="password" id="pass" name="pass">
                    </div>
                    <div>
                        <input type="submit" id="login" name="login" value="Entrar">
                    </div>
                </form>
            </div>
            <div class="register-container">
                <p>Rexístrate</p>
                <form id="register-form" method="post" action="./registro">
                    <div>
                        <input type="text" id="new-nick" name="new-nick">
                    </div>
                    <div>
                        <input type="password" id="new-pass" name="new-pass">
                    </div>
                    <div>
                        <input type="submit" id="register" name="register" value="Rexistrarse">
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
