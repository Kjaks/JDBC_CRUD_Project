package Models;
import java.sql.*;
/**
 * The clientModel class represents the model for handling client-related operations in the Celia Shop application.
 * This class includes methods for retrieving, inserting, deleting, modifying, and consulting client records.
 *  * @author Karolis Jakas Stirbyte
 */
public class clientModel {

    /**
     * Retrieves information about all client records from the database, this data refresh the tableView.
     *
     * @return A string containing information about all client records.
     */
    public String getClients(){
        Connection con = null;
        String data = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ventas_JDBC", "root", "root");
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM clients;");
                while (rs.next()) {
                    data += rs.getInt("ID") + ":" + rs.getString("client_name") + ":" + rs.getString("surname1") + ":" + rs.getString("surname2") + ":" + rs.getString("phone") + ":";
                }
            }
        } catch (Exception e) {
            data = "";
        }

        return data;
    }

    /**
     * Retrieves detailed information about a specific client record from the database based on its ID.
     *
     * @param inputID The ID of the client record to retrieve information for.
     * @return A string containing detailed information about the specified client record.
     */
    public String consultClient(int inputID) {
        Connection con = null;
        String data = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ventas_JDBC", "root", "root");
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM clients WHERE id = " + inputID + ";");
                while (rs.next()) {
                    data += "ID:" + rs.getInt("ID") + " - Nombre: " + rs.getString("client_name") + " " + rs.getString("surname1") + " " + rs.getString("surname2") + " - Telefono: " + rs.getString("phone") + "\n\n";
                }

                if (!data.equals("")) data += "Productos comprados:";

                ResultSet rsproduct = st.executeQuery("SELECT product_name, product_description FROM clients INNER JOIN buy ON buy.id_client = clients.id INNER JOIN product ON product.id = buy.id_product WHERE clients.id = " + inputID + ";");

                while (rsproduct.next()){
                    data += "\n" +  "Nombre: " + rsproduct.getString("product_name") + " - Descripcion: " + rsproduct.getString("product_description");
                }
            }
        }
        catch (Exception e) {
            data = "";
        }
        return data;
    }

    /**
     * Inserts a new client record into the database.
     *
     * @param name The name of the new client.
     * @param surname1 The first surname of the new client.
     * @param surname2 The second surname of the new client.
     * @param phone The phone number of the new client.
     * @return An integer indicating the success or failure of the operation.
     */
    public int insertClient(String name, String surname1, String surname2, String phone) {
        int result = 0;
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ventas_JDBC", "root", "root");
            if (con != null) {
                Statement st = con.createStatement();
                st.executeUpdate("INSERT INTO clients(client_name, surname1, surname2, phone) VALUES ('" + name + "', '" + surname1 + "', '" + surname2 + "', '" + phone + "')" + ";");
            }
        }
        catch (Exception e) {
            result = -1;
            }
        return result;
    }

    /**
     * Deletes a client record from the database based on its ID.
     *
     * @param id The ID of the client record to delete.
     * @return An integer indicating the success or failure of the operation.
     */
    public int deleteClient(int id){
        int result = 0;
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ventas_JDBC", "root", "root");
            if (con != null) {
                Statement st = con.createStatement();
                st.executeUpdate("DELETE FROM buy WHERE id_client = " + id + ";");
                st.executeUpdate("DELETE FROM clients WHERE id = " + id + ";");
            }
        }
        catch (Exception e) {
            result = -1;
        }
        return result;
    }

    /**
     * Retrieves information about a specific client record from the database based on its ID.
     *
     * @param inputID The ID of the client record to retrieve information for.
     * @return A string containing information about the specified client record.
     */
    public String getClientInfo(int inputID){
        Connection con = null;
        String data = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ventas_JDBC", "root", "root");
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM clients WHERE id = " + inputID + ";");
                while (rs.next()) {
                    data += rs.getString("client_name") + ":" + rs.getString("surname1") + ":" + rs.getString("surname2") + ":" + rs.getString("phone") + ":";
                }
            }
        }
        catch (Exception e) {
            data = "";
        }
        return data;
    }

    /**
     * Modifies an existing client record in the database.
     *
     * @param ID The ID of the client record to modify.
     * @param newName The new name of the client.
     * @param newSurname1 The new first surname of the client.
     * @param newSurname2 The new second surname of the client.
     * @param newPhone The new phone number of the client.
     * @return An integer indicating the success or failure of the operation.
     */
    public int modifyClient(int ID,String newName, String newSurname1, String newSurname2, String newPhone) {
        int result = 0;
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ventas_JDBC", "root", "root");
            if (con != null) {
                Statement st = con.createStatement();
                st.executeUpdate("UPDATE clients SET client_name = '" + newName + "', surname1 = '" + newSurname1 + "', surname2 = '" + newSurname2 + "', phone = '" + newPhone + "' WHERE id = " + ID + ";");
            }
        }
        catch (Exception e) {
            result = -1;
        }
        return result;
    }
}

