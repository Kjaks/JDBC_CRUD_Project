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
}
