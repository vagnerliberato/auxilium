/*
 * Documento   : ServletTesteConexao.java
 * Criação     : 25/05/2011, 21:30:15
 * Autor       : arthemus
 * Descrição   : Classe para testes de conexão utilizando a classe ConnectionFactory. 
 * Modificação : 26/05/2011, 21:42:33
 */
package test;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ConnectionFactory;

public class ServletTesteConexao extends HttpServlet {

    private Connection conexao = null;

    public ServletTesteConexao() {
        this.conexao = ConnectionFactory.getConnection(3);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");

            if (conexao == null) {
                request.setAttribute("conexao", "Problemas ao Conectar no Banco");
            } else {
                request.setAttribute("conexao", "Conexão OK");
            }
            
            request.setAttribute("sgbd",ConnectionFactory.getSgbd());
            
            rd.forward(request, response);

        } catch (Exception erro) {
            System.out.print(erro);
        }
    }
}
