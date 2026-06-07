package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

    public class mySQLConnect {
    private String user = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost:3306/lbr?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private Connection conn = null;
    private Statement statement = null;
    
    public mySQLConnect() {
        if (!connect()) {
            JOptionPane.showMessageDialog(null, "Không thể kết nối đến database!");
        }
    }
    
    // Phương thức kết nối riêng
    private boolean connect() {
        try {
            // Đăng ký MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Thiết lập kết nối
            conn = DriverManager.getConnection(url, user, password);
            
            if (conn != null) {
                System.out.println("Kết nối database thành công!");
                return true;
            }
            
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy Driver MySQL!\n" + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi kết nối database!\nVui lòng kiểm tra:\n1. MySQL đã được khởi động\n2. Database 'lbr' đã được tạo\n3. Username và password chính xác\n\nChi tiết lỗi: " + e.getMessage());
        }
        return false;
    }
    
    public Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }
    
    // Thực thi câu lệnh SELECT
    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        
        try {
            // Kiểm tra kết nối
            if (conn == null || conn.isClosed()) {
                if (!connect()) {
                    throw new SQLException("Không có kết nối database!");
                }
            }
            
            // Tạo statement mới nếu null hoặc đã đóng
            if (statement == null || statement.isClosed()) {
                statement = conn.createStatement();
            }
            
            // Thực thi câu lệnh
            rs = statement.executeQuery(sql);
            
        } catch (SQLException e) {
            System.out.println("Lỗi thực thi SQL: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thực thi câu lệnh SQL: " + e.getMessage() + "\nCâu lệnh: " + sql);
        }
        
        return rs;
    }
    
    // Thực thi INSERT, DELETE, UPDATE
    public int executeUpdate(String sql) {
        int result = 0;
        try {
            // Kiểm tra kết nối
            if (conn == null || conn.isClosed()) {
                if (!connect()) {
                    throw new SQLException("Không có kết nối database!");
                }
            }
            
            // Tạo statement mới nếu null hoặc đã đóng
            if (statement == null || statement.isClosed()) {
                statement = conn.createStatement();
            }
            
            // Thực thi câu lệnh
            result = statement.executeUpdate(sql);
            
        } catch (SQLException e) {
            System.out.println("Lỗi thực thi SQL: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thực thi câu lệnh SQL: " + e.getMessage() + "\nCâu lệnh: " + sql);
        }
        return result;
    }
    
    // Đóng kết nối
    public void closeConnection() {
        try {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Lỗi đóng kết nối: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi đóng kết nối: " + e.getMessage());
        }
    }
    
    // Phương thức kiểm tra kết nối
    public boolean isConnected() {
        try {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
