package br.com.softland.baseConhecimento.test;


import test.br.com.devmedia.mycompleteswingapp.entity.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class CustomerDAO extends GenericDAO{

   // private static final long SerialVesionUID = 1L;

    public int addCustomer(Customers ctm) throws SQLException{
        ctm.setId(getNextId("CUSTOMERS"));
        String query = "INSERT INTO APP.CUSTOMERS(ID, NAME, AGE, ADDRESS, TELEPHONE) VALUES(?,?,?,?, ?)";
        executeCommand(query, ctm.getId(), ctm.getName(), ctm.getAge(), ctm.getAddrress(), ctm.getTelephone());
        return ctm.getId();
    }

    public void removeCustomers(int idCustomers) throws SQLException{
        executeCommand("DELETE FROM APP.CUSTOMERS WHERE ID = ?", idCustomers);
    }

    public void updateCustomers(Customers ctm) throws SQLException{
        String query = "UPDATE APP.CUSTOMERS SET NAME = ?, AGE = ?, ADDRESS = ?, TELEPHONE = ? WHERE ID = ?";
        executeCommand(query, ctm.getName(), ctm.getAge(), ctm.getAddrress(), ctm.getTelephone(), ctm.getId());
    }

    public Customers getCustomers(int IdCustomers) throws SQLException{
        ResultSet rs = executeQuery("SELECT * FROM APP.CUSTOMERS WHERE ID=?", IdCustomers);
        Customers ctm = populateCustomersInfo(rs);
        rs.close();
        return ctm;
    }

    public static Customers populateCustomersInfo(ResultSet rs) throws SQLException {
        Customers toReturn = new Customers();
        toReturn.setId(rs.getInt("ID"));
        toReturn.setName(rs.getString("NAME"));
        toReturn.setAge(rs.getInt("AGE"));
        toReturn.setAddrress(rs.getString("ADDRESS"));
        toReturn.setTelephone(rs.getString("TELEPHONE"));
        return toReturn;
    }

    public List<Customers> getAllProducts() throws SQLException{
        List<Customers> toReturn = new LinkedList<Customers>();
        ResultSet rs = executeQuery("SELECT * FROM APP.CUSTOMERS");
        while(rs.next()){
            toReturn.add(populateCustomersInfo(rs));
        }
        return toReturn;
    }
}
