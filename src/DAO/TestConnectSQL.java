
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnectSQL {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/lbr?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String user = "root";
        String password = "123";  // Nếu bạn đã thiết lập mật khẩu cho root, thay đổi ở đây
        try {
            // Đăng ký driver và kết nối
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Kết nối database thành công!");
            }
        } catch (Exception e) {
            System.out.println("Lỗi kết nối: " + e.getMessage());
        }
    }

}
