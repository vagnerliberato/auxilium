/*
 * Documento     : ConexaoAgenda.java
 * Criação     : 14/05/2011
 * Autor         : arthemus
 * Descrição   : Classe java para conexao com o banco Agenda.FDB (Firebird)
 * Modificação : 20/06/2011
 */
package br.com.softland.baseConhecimento.model.connection;

import java.sql.*;

public class ConexaoAgenda {

    private static String driver = null;
    private static String url = null;
    private static String login = null;
    private static String senha = null;

    public static Connection getConnection() {

        driver = "org.firebirdsql.jdbc.FBDriver";

        //Arthemus - Casa
        //url = "jdbc:firebirdsql:arthemus/3060:C:/ArvoreDeTrabalho/Bancos/AGENDA.FDB";
        //login = "SYSDBA";
        //senha = "masterkey";

        //Arthemus - Softland
        url = "jdbc:firebirdsql:192.168.1.120/3060:D:/Bancos/Agenda/AGENDA.FDB";
        login = "SYSDBA";
        senha = "buana";

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, login, senha);
            return con;
        } catch (ClassNotFoundException erro) {
            return null;
        } catch (SQLException erro) {
            return null;
        }
    }
}