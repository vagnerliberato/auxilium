/*
 * Documento   : ConexaoAgenda.java
 * Criação     : 14/05/2011
 * Autor       : arthemus
 * Descrição   : Classe java para conexão com o banco Agenda da Softland
 * Modificação : 14/06/2011
 */
package br.com.softland.dthelp.model.agenda;

import java.sql.*;

public class ConexaoAgenda {

    private static String driver = null;
    private static String url = null;
    private static String login = null;
    private static String senha = null;
    private static String sgbd = null;

    public static Connection getConnection() {

        driver = "org.firebirdsql.jdbc.FBDriver";
        url = "jdbc:firebirdsql:192.168.1.120/3060:D:/Bancos/Agenda/AGENDA.FDB";
        login = "SYSDBA";
        senha = "buana";
        sgbd = "Firebird";

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

    public static String getSgbd() {
        return sgbd;
    }
}