package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class productModel {
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
            System.out.println("Error al acceder a la base de datos");
            e.printStackTrace();
        }

        return data;
    }

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
            System.out.println("Error al acceder a la base de datos");
            e.printStackTrace();
        }
        return data;
    }

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
