<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Projeto Auxilium</title>
    </head>
    <body>
    <center>

        <h1>Auxilium</h1>

        <form name="FrmTestConexao" method="POST" action="ServletTesteConexao">
            
            <input type="submit" value="Testar Conexão">

            <br>
            <br>
            <%= (request.getAttribute("conexao") != null ? request.getAttribute("conexao") : "")%>
            <br>
            <%= (request.getAttribute("sgbd") != null ? request.getAttribute("sgbd") : "")%>
            <br>
            <br>
        </form>

        <form name="FrmCadConhecimento" method="POST" action="ServletCadConhecimento">
            
            <input type="submit" value="Cadastro de Conhecimento">

            <br>
            <br>
            <%= (request.getAttribute("conexao") != null ? request.getAttribute("conexao") : "")%>
            <br>
            <%= (request.getAttribute("sgbd") != null ? request.getAttribute("sgbd") : "")%>
            <br>
            <br>
        </form>    

    </center>
</body>
</html>
