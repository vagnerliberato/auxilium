
package test.br.com.devmedia.mycompleteswingapp.dao;

import test.br.com.devmedia.mycompleteswingapp.entity.Animal;
import test.br.com.devmedia.mycompleteswingapp.entity.AnimalType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AnimalDAO extends GenericDAO{

    public int addAnimal(Animal an) throws SQLException{
        an.setId(getNextId("ANIMALS"));
        String query = "INSERT INTO APP.ANIMALS (ID, ID_OWNER, NAME, TYPE, BREED, AGE) VALUES (?,?,?,?,?,?)";
        executeCommand(query, an.getId(), an.getOwner().getId(), an.getName(), an.getType().toString(), an.getBreed(), an.getAge());
        return an.getId();
    }

    public void removeAnimal(int idAnimal) throws SQLException{
        executeCommand("DELETE FROM APP.ANIMALS WHERE ID=?");
    }

    public void updateAnimal(Animal an) throws SQLException{
        String query = "UPDATE APP.ANIMALS SET ID_OWNER=?, NAME=?, TYPE=?, BREED=?, AGE=? WHERE ID=?";
        executeCommand(query, an.getOwner().getId(), an.getName(), an.getType().toString(), an.getBreed(), an.getAge());
    }

    public Animal getAnimal(int idAnimal) throws SQLException{
        ResultSet rs = executeQuery("SELECT * FROM APP.ANIMALS WHERE ID = ?", idAnimal);
        Animal an = populateAnimal(rs);
        rs.close();
        return an;
    }

    public List<Animal> getAnimalByName(String name) throws SQLException{
        List<Animal> toReturn = new LinkedList<Animal>();
        ResultSet rs = executeQuery("SELECT * FROM  APP.ANIMALS WHERE NAME LIKE ?", "%"+name+"%");
        while(rs.next()) {
            toReturn.add(populateAnimal(rs));
        }
        rs.close();
        return toReturn;
    }

    private static Animal populateAnimal(ResultSet rs) throws SQLException {
        CustomerDAO customerDAO = new CustomerDAO();
        Animal toReturn = new Animal();
        toReturn.setId(rs.getInt("ID"));
        toReturn.setOwner(customerDAO.getCustomers(rs.getInt("ID_OWNER")));
        toReturn.setName(rs.getString("NAME"));
        toReturn.setType(AnimalType.valueOf(rs.getString("TYPE")));
        toReturn.setBreed(rs.getString("BREED"));
        toReturn.setAge(rs.getInt("AGE"));

        return toReturn;
    }



}
