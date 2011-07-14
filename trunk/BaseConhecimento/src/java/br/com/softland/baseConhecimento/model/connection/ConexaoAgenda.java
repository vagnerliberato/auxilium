package br.com.softland.baseConhecimento.model.connection;

import java.sql.*;

public class ConexaoAgenda {

    private static String driver = null;
    private static String url = null;
    private static String login = null;
    private static String senha = null;

    public static Connection getConnection() {

        driver = "org.firebirdsql.jdbc.FBDriver";

        url = "jdbc:firebirdsql:192.168.1.2/3060:f:/agenda/AGENDA.FDB";
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