package br.com.softland.dthelp.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class GenericDAO {


    public Connection getConnection(){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection cx = DriverManager.getConnection("jdbc:derby://localhost:1527/petshop", "petshop", "petshop");
            return cx;
        } catch (Exception ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Statement getStatement() throws SQLException{
        return getConnection().createStatement();
    }

    public PreparedStatement getStatement(String st) throws SQLException{
        return getConnection().prepareStatement(st);
    }

    //O object pode receber varios parametros. É um Array que pode ser nulo ou receber vários parâmetros.
    public ResultSet executeQuery(String query, Object... params) throws SQLException{
        PreparedStatement ps = getStatement(query);
        for (int i = 0; i < params.length; i++){
            ps.setObject(i+1, params[i]);
        }
        return ps.executeQuery();
    }

    public int executeCommand(String query, Object... params) throws SQLException{
        PreparedStatement ps = getStatement(query);
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i+1, params[i]);
        }
        int result = ps.executeUpdate();
        ps.close();
        return result;
    }

    public Integer getNextId(String tableName) throws SQLException{
        ResultSet rs = executeQuery("select Max(ID) from APP."+tableName);
        Object result = rs.getObject(1);
        if(result == null){
            rs.close();
            return 1;
        } else{
            return (Integer)result+1;
        }
    }
}
