
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLServerConnect {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLiThuVien;encrypt=true;trustServerCertificate=true";
    private static final String USER = "tnptp"; // Thay bằng tài khoản SQL Server của bạn
    private static final String PASSWORD = "123"; // Thay bằng mật khẩu của bạn

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if(rs != null) rs.close();
            if(stmt != null) stmt.close();
            if(conn != null) conn.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }    
}
