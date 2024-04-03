package Models;

import java.sql.*;

public class buyModel {

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
