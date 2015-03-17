<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="${pageContext.servletContext.contextPath}/images/logo.png">
        <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/style/index.css">
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/scripts/formChecks.js"></script>
        <title>Gentleman Chat</title>
    </head>
    <body>
        <div class="title-container">
            <div class="logo-container">
                <figure>
                    <img id="logo" src="${pageContext.servletContext.contextPath}/images/logo.png">
                </figure>
            </div>
            <div class="name-container">
                <p id="nombre">Gentleman Chat</p>
            </div>
        </div>
        <div class="content">
            <div class="passchg-container">
                <p>Cambio de contraseña</p>
                <% if(request.getAttribute("error") == "-1") { %>
                    <p id="err-email-exist">La contraseña anterior no se corresponde</p>
                <%}%>
                <form id="passchg-form" name="passchgForm" method="post" action="${pageContext.servletContext.contextPath}/changePass" onsubmit="return equalChgPass()">
                    <div>
                        <input type="password" id="oldpass" name="oldPass" placeholder="contraseña actual" autofocus="autofocus">
                    </div>
                    <div>
                        <input type="password" id="newpass" name="newPass" placeholder="nueva contraseña">
                    </div>
                    <div>
                        <input type="password" id="new-pass-conf" name="newPassConf" placeholder="confirmación de contraseña" oninput="equalChgPass()">
                        <p id="err-pass">La contraseña no coincide</p>
                    </div>
                    <div class="submit">
                        <input type="submit" id="passchg" name="passchg" value="Cambiar">
                    </div>
                </form>
            </div>
            
            <div class="delete-container">
                <form id="delete-form" name="deleteForm" action="${pageContext.servletContext.contextPath}/deleteAccount" method="post" >
                    <div class="submit">
                        <input type="submit" id="delete" name="delete" value="Eliminar cuenta">
                    </div>
                </form>
            </div>
            <div id="clearer"></div>
        </div>
        <div id="identification">
            <p id="id1">Identificado como</p>
            <p id="id2"><%= session.getAttribute("session") %></p>
        </div>
        <div class="footer">
            <footer>
            </footer>
        </div>
    </body>
</html>
