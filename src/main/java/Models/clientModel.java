package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class clientModel {

    public String consultClient(int inputID) {
        Connection con = null;
        String data = " ";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ventas_JDBC", "root", "root");
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT id , nombre FROM cliente WHERE id = " + inputID);
                while (rs.next()) {
                    data = rs.getInt("id") + " - " + rs.getString("nombre");
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
