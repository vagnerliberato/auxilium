package test.br.com.devmedia.mycompleteswingapp.dao;

import test.br.com.devmedia.mycompleteswingapp.entity.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductDAO extends GenericDAO{

     public int addProducts(Product prod) throws SQLException{
        prod.setId(getNextId("PRODUTS"));
        String query = "INSERT INTO APP.PRODUTS(ID, NAME, PRICE, STOCK) VALUES(?,?,?,?)";
        executeCommand(query, prod.getId(), prod.getName(), prod.getPrice(), prod.getStock());
        return prod.getId();
    }

    public void removeProducts(int idProducts) throws SQLException{
        executeCommand("DELETE FROM APP.PRODUTS WHERE ID = ?", idProducts);
    }

    public void updateProducts(Product prod) throws SQLException{
        String query = "UPDATE APP.PRODUTS SET NAME = ?, PRICE = ?, STOCK = ? WHERE ID = ?";
        executeCommand(query, prod.getName(), prod.getPrice(), prod.getStock(), prod.getId());
    }

    public Product getProducts(int IdProducts) throws SQLException{
        ResultSet rs = executeQuery("SELECT * FROM APP.PRODUTS WHERE ID=?", IdProducts);
        Product prod = populateProductsInfo(rs);
        rs.close();
        return prod;
    }

    private static Product populateProductsInfo(ResultSet rs) throws SQLException {
        Product toReturn = new Product();
        toReturn.setId(rs.getInt("ID"));
        toReturn.setName(rs.getString("NAME"));
        toReturn.setPrice(rs.getFloat("PRICE"));
        toReturn.setStock(rs.getInt("STOCK"));
        return toReturn;
    }

    public List<Product> getAllProducts() throws SQLException{
        List<Product> toReturn = new LinkedList<Product>();
        ResultSet rs = executeQuery("SELECT * FROM APP.PRODUCTS");
        while(rs.next()){
            toReturn.add(populateProductsInfo(rs));
        }
        return toReturn;
    }

}
