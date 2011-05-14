/*
 * Documento   : Connection.java
 * Criação     : 14/05/2011, 15:56:33
 * Autor       : arthemus
 * Descrição   : Classe java para conexão com os sgbd sql server, mysql e firebird.
 * Modificação : 14/05/2011, 15:56:33
*/

package model;

import java.sql.*;

public class ConnectionFactory {

    private static String driver = null;
    private static String url = null;
    private static String login = null;
    private static String senha = null;
    private static String sgbd = null;

    public static Connection getConnection(int i) {

        switch (i) {
            case 1: //MySQl
                driver = "com.mysql.jdbc.Driver";
                url = "jdbc:mysql://localhost:3306/";
                login = "root";
                senha = "carol";
                sgbd = "MySQL";
                break;
            case 2: //Sql Server
                driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                url = "jdbc:sqlserver://arthemus\\SQLExpress;1433:databasemane=American_Loja";
                login = "sa";
                senha = "carol";
                sgbd = "SQL-Server";
                break;
        }

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