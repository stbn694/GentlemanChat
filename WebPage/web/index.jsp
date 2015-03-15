<%-- 
    Document   : index
    Created on : 12-mar-2015, 20:54:04
    Author     : Esteban Gerpe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="${pageContext.servletContext.contextPath}/images/favicon.png">
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/style/index.css">
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/scripts/formChecks.js"></script>
        <title>Gentleman Chat</title>
    </head>
    <body>
        <div class="title-container">
            <div class="logo-container">
                <figure>
                    <img id="logo" src="./images/logo.png">
                </figure>
            </div>
            <div class="name-container">
                <p id="nombre">Gentleman Chat</p>
            </div>
        </div>
        <div class="content">
            <div class="login-container">
                <p>Inicia sesión</p>
                <form id="login-form" method="post" action="./login">
                    <div>
                        <input type="email" id="email" name="email" placeholder="email" autofocus="autofocus">
                    </div>
                    <div>
                        <input type="password" id="pass" name="pass" placeholder="contraseña">
                    </div>
                    <div class="submit">
                        <input type="submit" id="login" name="login" value="Entrar">
                    </div>
                </form>
            </div>
            
            <div class="register-container">
                <% if(request.getAttribute("error") == null || request.getAttribute("error") == "-1") { %>
                
                <p>Regístrate</p>
                <% if(request.getAttribute("error") == "-1") { %>
                    <p id="err-email-exist">El usuario ya existe</p>
                <%}%>
                <form id="register-form" name="registerForm" action="${pageContext.servletContext.contextPath}/register" method="post" >
                    <div>
                        <input type="text" id="new-name" name="newName" placeholder="nombre">
                    </div>
                    <div>
                        <input type="email" id="new-email" name="newEmail" placeholder="email" oninput="checkMail()">
                        <p id="err-email">EL formato de e-mail es incorrecto</p>
                        
                    </div>
                    <div>
                        <input type="password" id="new-pass" name="newPass" placeholder="contraseña">
                    </div>
                    <div>
                        <input type="password" id="new-pass-conf" name="newPassConf" placeholder="confirmación de contraseña" oninput="equalPass()">
                        <p id="err-pass">La contraseña no coincide</p>
                    </div>
                    <div class="submit">
                        <input type="submit" id="register" name="register" value="Registrarse">
                    </div>
                </form>
                <%} else if (request.getAttribute("error") == "1"){%>
                <p>Datos del Registro:</p>
                <p class="registerData">Nombre: <span style="color: black"><%= request.getAttribute("name") %></span></p>
                <p class="registerData">E-Mail: <span style="color: black"><%= request.getAttribute("email") %></span></p>
                <p class="registerData">Contraseña: <span style="color: black"><%= request.getAttribute("pass") %></span>
                <%}%>
            </div>
            <div id="clearer"></div>
        </div>
        <div class="footer">
            <footer>
            </footer>
        </div>
    </body>
</html>