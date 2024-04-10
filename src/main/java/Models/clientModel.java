package Models;
import java.sql.*;
/**
 * The clientModel class represents the model for handling client-related operations in the Celia Shop application.
 * This class includes methods for retrieving, inserting, deleting, modifying, and consulting client records.
 * @author Karolis Jakas Stirbyte
 */
public class clientModel {
    private final String url = "jdbc:mysql://localhost:3306/Ventas_JDBC";
    private final String user = "root";
    private final String password = "root";

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
            con = DriverManager.getConnection(url, user, password);
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
            con = DriverManager.getConnection(url, user, password);
            if (con != null) {
                String queryClient = "SELECT * FROM clients WHERE id = ?";
                PreparedStatement pstClient = con.prepareStatement(queryClient);
                pstClient.setInt(1, inputID);
                ResultSet rs = pstClient.executeQuery();
                while (rs.next()) {
                    data += "ID:" + rs.getInt("ID") + " - Nombre: " + rs.getString("client_name") + " " + rs.getString("surname1") + " " + rs.getString("surname2") + " - Telefono: " + rs.getString("phone") + "\n\n";
                }

                if (!data.equals("")) data += "Productos comprados:";

                String queryProducts = "SELECT product_name, product_description FROM clients INNER JOIN buy ON buy.id_client = clients.id INNER JOIN product ON product.id = buy.id_product WHERE clients.id = ?";
                PreparedStatement pstProducts = con.prepareStatement(queryProducts);
                pstProducts.setInt(1, inputID);
                ResultSet rsproduct = pstProducts.executeQuery();

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
        PreparedStatement pst = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            if (con != null) {
                String query = "INSERT INTO clients(client_name, surname1, surname2, phone) VALUES (?, ?, ?, ?)";
                pst = con.prepareStatement(query);
                pst.setString(1, name);
                pst.setString(2, surname1);
                pst.setString(3, surname2);
                pst.setString(4, phone);
                pst.executeUpdate();
                con.commit();
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
    public int deleteClient(int id) {
        int result = 0;
        Connection con = null;
        PreparedStatement pst = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);

            if (con != null) {
                String deleteBuyQuery = "DELETE FROM buy WHERE id_client = ?";
                pst = con.prepareStatement(deleteBuyQuery);
                pst.setInt(1, id);
                pst.executeUpdate();

                String deleteClientQuery = "DELETE FROM clients WHERE id = ?";
                pst = con.prepareStatement(deleteClientQuery);
                pst.setInt(1, id);
                pst.executeUpdate();
            }
        } catch (Exception e) {
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
    public String getClientInfo(int inputID) {
        Connection con = null;
        String data = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            if (con != null) {
                String query = "SELECT * FROM clients WHERE id = ?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, inputID);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    data += rs.getString("client_name") + ":" + rs.getString("surname1") + ":" + rs.getString("surname2") + ":" + rs.getString("phone") + ":";
                }
            }
        } catch (Exception e) {
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
    public int modifyClient(int ID, String newName, String newSurname1, String newSurname2, String newPhone) {
        int result = 0;
        Connection con = null;
        PreparedStatement pst = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);

            if (con != null) {
                String updateQuery = "UPDATE clients SET client_name = ?, surname1 = ?, surname2 = ?, phone = ? WHERE id = ?";
                pst = con.prepareStatement(updateQuery);
                pst.setString(1, newName);
                pst.setString(2, newSurname1);
                pst.setString(3, newSurname2);
                pst.setString(4, newPhone);
                pst.setInt(5, ID);
                pst.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            result = -1;
        }
        return result;
    }
}

