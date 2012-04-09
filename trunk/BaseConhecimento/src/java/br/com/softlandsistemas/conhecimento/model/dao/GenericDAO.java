package br.com.softlandsistemas.conhecimento.model.dao;

import br.com.softlandsistemas.conhecimento.connection.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class GenericDAO extends Conexao {

    public Statement getStatement() throws SQLException {
        return getConnection().createStatement();
    }

    public PreparedStatement getStatement(String st) throws SQLException {
        return getConnection().prepareStatement(st);
    }

    //O object pode receber varios parametros. É um Array que pode ser nulo ou receber vários parâmetros (varargs).
    public ResultSet executeQuery(String query, Object... params) throws SQLException {
        PreparedStatement ps = getStatement(query);
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
        return ps.executeQuery();
    }

    public int executeCommand(String query, Object... params) throws SQLException {
        PreparedStatement ps = getStatement(query);
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
        int result = ps.executeUpdate();
        ps.close();
        return result;
    }

    public Integer getNextId(String tableName, String id) throws SQLException {
        ResultSet rs = executeQuery("SELECT MAX(" + id + ") from " + tableName);
        rs.next();
        Object result = rs.getObject(1);

        if (result == null) {
            rs.close();
            return 1;
        } else {
            return (Integer) result + 1;
        }
    }
}
