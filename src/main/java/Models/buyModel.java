package Models;

import java.sql.*;
import java.text.SimpleDateFormat;

public class buyModel {

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
                    // Obtener la fecha como un objeto java.sql.Date
                    java.sql.Date dateSql = rs.getDate("buy_date");

                    // Convertir la fecha en una cadena usando SimpleDateFormat
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String dateString = sdf.format(dateSql);

                    data += rs.getInt("id") + ":id = " + rs.getInt("id_client") + "   " + rs.getString("client_name") + ": id = " + rs.getInt("id_product") + "   " + rs.getString("product_name") + ":" + dateString + ":";
                }
            }
        } catch (Exception e) {
            System.out.println("Error al acceder a la base de datos");
            e.printStackTrace();
        }

        return data;
    }

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
            System.out.println("Error al acceder a la base de datos");
            e.printStackTrace();
        }
        return data;
    }

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
            System.out.println("ALGO TA MAL JEFE");
            e.printStackTrace();
        }
        return result;
    }

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
