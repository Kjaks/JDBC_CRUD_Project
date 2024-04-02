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
                st.executeUpdate("CREATE TABLE Compran (\n" +
                        "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                        "    idCliente INT,\n" +
                        "    idProducto INT,\n" +
                        "    fecha DATE,\n" +
                        "    FOREIGN KEY (idCliente) REFERENCES Clientes(id),\n" +
                        "    FOREIGN KEY (idProducto) REFERENCES Productos(id)\n" +
                        ");");
            }
        }
        catch (Exception e) {
            result = -1;
        }
        return result;
    }
}
