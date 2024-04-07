package Models;

import java.sql.*;
import java.text.SimpleDateFormat;
/**
 * The buyModel class represents the model for handling buy-related operations in the Celia Shop application.
 * This class includes methods for retrieving, inserting, deleting, modifying, and creating buy records.
 * @author Karolis Jakas Stirbyte
 */
public class buyModel {
    /**
     * Retrieves information about all buy records from the database, this data refresh the tableView.
     *
     * @return A string containing information about all buy records.
     */
    public String getBuys(){
        Connection con = null;
        String data = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ventas_JDBC", "root", "root");
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT buy.id, id_client, id_product, client_name, product_name, buy_date\n" +
                        "FROM clients INNER JOIN buy\n" +
                        "\tON buy.id_client = clients.id\n" +
                        "    INNER JOIN product\n" +
                        "    ON product.id = buy.id_product;");
                while (rs.next()) {
                    java.sql.Date dateSql = rs.getDate("buy_date");

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String dateString = sdf.format(dateSql);

                    data += rs.getInt("id") + ":id = " + rs.getInt("id_client") + "   " + rs.getString("client_name") + ": id = " + rs.getInt("id_product") + "   " + rs.getString("product_name") + ":" + dateString + ":";
                }
            }
        } catch (Exception e) {
            data = "";
        }

        return data;
    }

    /**
     * Inserts a new buy record into the database.
     *
     * @param clientID The ID of the client making the purchase.
     * @param productID The ID of the product being purchased.
     * @param date The date of the purchase in the format "dd/MM/yyyy".
     * @return An integer indicating the success or failure of the operation.
     */
    public int insertBuy(String clientID, String productID, String date) {
        int result = 0;
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ventas_JDBC", "root", "root");
            if (con != null) {
                Statement st = con.createStatement();
                String[] formatedDate = date.split("/");
                String formatedDateForSQL = formatedDate[2] + "-" + formatedDate[1] + "-" + formatedDate[0];

                st.executeUpdate("INSERT INTO buy(id_client, id_product, buy_date) VALUES ('" + clientID + "', '" + productID + "', '" + formatedDateForSQL + "')" + ";");

            }
        }
        catch (Exception e) {
            result = -1;
        }
        return result;
    }

    /**
     * Deletes a buy record from the database based on its ID.
     *
     * @param id The ID of the buy record to delete.
     * @return An integer indicating the success or failure of the operation.
     */
    public int deleteBuy(int id){
        int result = 0;
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ventas_JDBC", "root", "root");
            if (con != null) {
                Statement st = con.createStatement();
                st.executeUpdate("DELETE FROM buy WHERE id = " + id + ";");
            }
        }
        catch (Exception e) {
            result = -1;
        }
        return result;
    }

    /**
     * Retrieves information about a specific buy record from the database based on its ID.
     *
     * @param inputID The ID of the buy record to retrieve information for.
     * @return A string containing information about the specified buy record.
     */
    public String getBuyInfo(int inputID){
        Connection con = null;
        String data = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ventas_JDBC", "root", "root");
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM buy WHERE id = " + inputID + ";");
                while (rs.next()) {
                    java.sql.Date dateSql = rs.getDate("buy_date");

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String dateString = sdf.format(dateSql);

                    data += rs.getString("id_client") + ":" + rs.getString("id_product") + ":" + dateString + ":";
                }
            }
        }
        catch (Exception e) {
            data = "";
        }
        return data;
    }

    /**
     * Modifies an existing buy record in the database.
     *
     * @param ID The ID of the buy record to modify.
     * @param clientID The new ID of the client making the purchase.
     * @param productID The new ID of the product being purchased.
     * @param date The new date of the purchase in the format "dd/MM/yyyy".
     * @return An integer indicating the success or failure of the operation.
     */
    public int modifyBuy(int ID,String clientID, String productID, String date) {
        int result = 0;
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ventas_JDBC", "root", "root");
            if (con != null) {
                Statement st = con.createStatement();

                String[] formatedDate = date.split("/");
                String formatedDateForSQL = formatedDate[2] + "-" + formatedDate[1] + "-" + formatedDate[0];

                st.executeUpdate("UPDATE buy SET id_client = '" + clientID + "', id_product = '" + productID + "', buy_date = '" + formatedDateForSQL + "' WHERE id = " + ID + ";");
            }
        }
        catch (Exception e) {
            result = -1;
        }
        return result;
    }

    /**
     * Creates the buy table in the database if it does not already exist.
     *
     * @return An integer indicating the success or failure of the operation.
     */
    public int createTable(){
        Connection con = null;
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ventas_JDBC", "root", "root");
            if (con != null) {
                Statement st = con.createStatement();
                st.executeUpdate("CREATE TABLE buy ( id INT AUTO_INCREMENT PRIMARY KEY, id_client INT, id_product INT, buy_date DATE, FOREIGN KEY (id_client) REFERENCES clients(id), FOREIGN KEY (id_product) REFERENCES product(id));");
            }
        }
        catch (Exception e) {
            result = -1;
        }
        return result;
    }
}
