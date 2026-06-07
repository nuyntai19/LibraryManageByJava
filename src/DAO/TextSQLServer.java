
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

public class TextSQLServer {
    public static void main(String[] args) throws SQLException {
        Connection conn = SQLServerConnect.getConnection();
        if (conn != null) {
            try {
                System.out.println("Ket noi thanh cong den database: " + conn.getCatalog());
                conn.close(); 
            } catch (SQLException e) {
            }
        } else {
            System.out.println("Kết nối thất bại!");
        }
    }
}
