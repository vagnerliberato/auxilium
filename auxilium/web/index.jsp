<%-- 
    Document   : index
    Created on : 14/05/2011, 15:56:33
    Author     : arthemus
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Projeto Auxilium</title>
    </head>
    <body>
    <center>
        <form name="Form" method="POST" action="TestConexao">
            <h1>Auxilium</h1>
            <input type="submit" value="Testar Conexão">
        </form>
        
        <%= (request.getAttribute("conexao") != null ? request.getAttribute("conexao") : "") %>
        <br>
        <%= (request.getAttribute("sgbd") != null ? request.getAttribute("sgbd") : "") %>
        
    </center>
</body>
</html>
