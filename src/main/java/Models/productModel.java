package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * The productModel class represents the model for handling product-related operations in the Celia Shop application.
 * This class includes methods for retrieving, inserting, deleting, and modifying product records.
 * @author Karolis Jakas Stirbyte
 */
public class productModel {
    /**
     * Retrieves information about all products from the database, this data refresh the tableView.
     *
     * @return A string containing information about all products.
     */
    public String getProducts(){
        Connection con = null;
        String data = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ventas_JDBC", "root", "root");
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM product;");
                while (rs.next()) {
                    data += rs.getInt("ID") + ":" + rs.getString("product_name") + ":" + rs.getString("product_description") + ":" + rs.getDouble("pvp") + ":";
                }
            }
        } catch (Exception e) {
            data = "";
        }

        return data;
    }

    /**
     * Retrieves detailed information about a specific product from the database based on its ID.
     *
     * @param inputID The ID of the product to retrieve information for.
     * @return A string containing detailed information about the specified product.
     */
    public String getProductInfo(int inputID){
        Connection con = null;
        String data = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ventas_JDBC", "root", "root");
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM product WHERE id = " + inputID + ";");
                while (rs.next()) {
                    data += rs.getString("product_name") + ":" + rs.getString("product_description") + ":" + rs.getDouble("pvp") + ":";
                }
            }
        }
        catch (Exception e) {
            data = "";
        }
        return data;
    }

    /**
     * Inserts a new product into the database.
     *
     * @param product_name The name of the new product.
     * @param product_description The description of the new product.
     * @param pvp The price of the new product.
     * @return An integer indicating the success or failure of the operation.
     */
    public int insertProduct(String product_name, String product_description, Double pvp) {
        int result = 0;
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ventas_JDBC", "root", "root");
            if (con != null) {
                Statement st = con.createStatement();
                st.executeUpdate("INSERT INTO product(product_name, product_description, pvp) VALUES ('" + product_name + "', '" + product_description + "', " + pvp + ");");
            }
        }
        catch (Exception e) {
            result = -1;
        }
        return result;
    }

    /**
     * Deletes a product record from the database based on its ID.
     *
     * @param id The ID of the product record to delete.
     * @return An integer indicating the success or failure of the operation.
     */
    public int deleteProduct(int id){
        int result = 0;
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ventas_JDBC", "root", "root");
            if (con != null) {
                Statement st = con.createStatement();
                st.executeUpdate("DELETE FROM buy WHERE id_product = " + id + ";");
                st.executeUpdate("DELETE FROM product WHERE id = " + id + ";");
            }
        }
        catch (Exception e) {
            result = -1;
        }
        return result;
    }

    /**
     * Modifies an existing product record in the database.
     *
     * @param ID The ID of the product record to modify.
     * @param newName The new name of the product.
     * @param newDescription The new description of the product.
     * @param newPVP The new price of the product.
     * @return An integer indicating the success or failure of the operation.
     */
    public int modifyProduct(int ID,String newName, String newDescription, Double newPVP) {
        int result = 0;
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ventas_JDBC", "root", "root");
            if (con != null) {
                Statement st = con.createStatement();
                st.executeUpdate("UPDATE product SET product_name = '" + newName + "', product_description = '" + newDescription + "', pvp = " + newPVP + " WHERE id = " + ID + ";");
            }
        }
        catch (Exception e) {
            result = -1;
        }
        return result;
    }
}
