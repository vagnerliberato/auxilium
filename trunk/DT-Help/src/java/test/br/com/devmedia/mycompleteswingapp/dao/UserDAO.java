
package test.br.com.devmedia.mycompleteswingapp.dao;

import test.br.com.devmedia.mycompleteswingapp.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends GenericDAO {

    public int addUser(User usr) throws SQLException{
        usr.setId(getNextId("USERS"));
        String query = "INSERT INTO APP.USERS(ID, NAME,LOGIN, SENHA) VALUES(?,?,?,?)";
        executeCommand(query, usr.getId(), usr.getName(), usr.getLogin(), usr.getSenha());
        return usr.getId();
    }

    public void removeUser(int idUser) throws SQLException{
        executeCommand("DELETE FROM APP.USERS WHERE ID = ?", idUser);
    }

    public void updateUser(User usr) throws SQLException{
        String query = "UPDATE APP.USERS SET NAME=?, LOGIN=?, SENHA=? WHERE ID=?";
        executeCommand(query, usr.getName(), usr.getLogin(), usr.getSenha(), usr.getId());
    }

    public User getUser(int IdUser) throws SQLException{
        ResultSet rs = executeQuery("SELECT * FROM APP.USERS WHERE ID=?", IdUser);
        User usr = populateUserInfo(rs);
        rs.close();
        return usr;
    }

    public static  User populateUserInfo(ResultSet rs) throws SQLException {
        User toReturn = new User();
        toReturn.setId(rs.getInt("ID"));
        toReturn.setLogin(rs.getString("LOGIN"));
        toReturn.setName(rs.getString("NAME"));
        toReturn.setSenha(rs.getString("SENHA"));
        return toReturn;
    }
}
