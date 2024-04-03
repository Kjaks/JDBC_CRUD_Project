package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class clientModel {

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
            System.out.println("Error al acceder a la base de datos");
            e.printStackTrace();
        }

        return data;
    }

    public String consultClient(int inputID) {
        Connection con = null;
        String data = "";

        int i = 0;
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
            System.out.println("Error al acceder a la base de datos");
            e.printStackTrace();
        }
        return data;
    }
}
