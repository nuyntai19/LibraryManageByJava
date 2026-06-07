package DAO;

import DTO.TaiKhoanDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class TaiKhoanDAO {
    private mySQLConnect db = new mySQLConnect();
    
    // Kiểm tra tên đăng nhập đã tồn tại chưa
    public boolean kiemTraTenDangNhap(String tenDangNhap) throws SQLException {
        String sql = "SELECT COUNT(*) FROM taikhoan WHERE ten_dang_nhap = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setString(1, tenDangNhap);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
    
    public TaiKhoanDTO kiemTraDangNhap(String tenDangNhap, String matKhau) throws SQLException {
    String sql = "SELECT * FROM taikhoan WHERE ten_dang_nhap = ? AND mat_khau = ?";
    
    try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
        stmt.setString(1, tenDangNhap);
        stmt.setString(2, matKhau);
        
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            String maTaiKhoan = rs.getString("ma_tai_khoan");
            String maPhanQuyen = rs.getString("ma_phan_quyen");
            String maThe = rs.getString("ma_the");
            
            // Kiểm tra xem ma_nhan_vien có phải là NULL không
            Integer maNhanVien = null;
            if (rs.getObject("ma_nhan_vien") != null) {
                maNhanVien = rs.getInt("ma_nhan_vien");
            }
            
            return new TaiKhoanDTO(maTaiKhoan, tenDangNhap, matKhau, maPhanQuyen, maNhanVien, maThe);
        }
    }
    
    return null; // Không tìm thấy tài khoản phù hợp
}
    
    // Tạo mã tài khoản ngẫu nhiên 8 chữ số
    public String taoMaTaiKhoanNgauNhien() throws SQLException {
        Random rand = new Random();
        String maTaiKhoan;
        boolean maTonTai;
        
        do {
            // Tạo số ngẫu nhiên 8 chữ số
            int randomNum = 100000 + rand.nextInt(900000);
            maTaiKhoan = String.valueOf(randomNum);
            
            // Kiểm tra mã đã tồn tại chưa
            String sql = "SELECT COUNT(*) FROM taikhoan WHERE ma_tai_khoan = ?";
            try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
                stmt.setString(1, maTaiKhoan);
                ResultSet rs = stmt.executeQuery();
                maTonTai = rs.next() && rs.getInt(1) > 0;
            }
        } while (maTonTai); // Lặp lại nếu mã đã tồn tại
        
        return maTaiKhoan;
    }
    
    // Thêm tài khoản mới
    public boolean themTaiKhoan(TaiKhoanDTO tk) throws SQLException {
        // Kiểm tra xem tên đăng nhập đã tồn tại chưa
        if (kiemTraTenDangNhap(tk.getTenDangNhap())) {
            return false; // Tên đăng nhập đã tồn tại
        }
        
        String sql = "INSERT INTO taikhoan (ma_tai_khoan, ten_dang_nhap, mat_khau, ma_phan_quyen, ma_nhan_vien, ma_the) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
                     
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            // Thiết lập giá trị cho câu lệnh SQL
            stmt.setString(1, tk.getMaTaiKhoan());
            stmt.setString(2, tk.getTenDangNhap());
            stmt.setString(3, tk.getMatKhau() != null ? tk.getMatKhau() : "123456789"); // Mặc định là 123456789
            
            // Ma phan quyen có thể là null
            if (tk.getMaPhanQuyen() != null) {
                stmt.setString(4, tk.getMaPhanQuyen());
            } else {
                stmt.setNull(4, Types.VARCHAR);
            }
            
            // Thiết lập ma_nhan_vien và ma_the theo điều kiện check
            if (tk.getMaNhanVien() != null) {
                stmt.setInt(5, tk.getMaNhanVien());
                stmt.setNull(6, Types.VARCHAR); // Nếu có ma_nhan_vien thì ma_the phải NULL
            } else {
                stmt.setNull(5, Types.INTEGER);
                stmt.setString(6, tk.getMaThe()); // Nếu có ma_the thì ma_nhan_vien phải NULL
            }
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
    // Lấy danh sách tất cả tài khoản
    public ArrayList<TaiKhoanDTO> layDanhSachTaiKhoan() throws SQLException {
        ArrayList<TaiKhoanDTO> dstk = new ArrayList<>();
        String sql = "SELECT * FROM taikhoan";
        
        try (Statement stmt = db.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String maTaiKhoan = rs.getString("ma_tai_khoan");
                String tenDangNhap = rs.getString("ten_dang_nhap");
                String matKhau = rs.getString("mat_khau");
                String maPhanQuyen = rs.getString("ma_phan_quyen");
                String maThe = rs.getString("ma_the");
                
                // Kiểm tra xem ma_nhan_vien có phải là NULL không
                Integer maNhanVien = null;
                if (rs.getObject("ma_nhan_vien") != null) {
                    maNhanVien = rs.getInt("ma_nhan_vien");
                }
                
                TaiKhoanDTO tk = new TaiKhoanDTO(maTaiKhoan, tenDangNhap, matKhau, maPhanQuyen, maNhanVien, maThe);
                dstk.add(tk);
            }
        }
        
        return dstk;
    }
    
    // Lấy thông tin tài khoản theo mã tài khoản
    public TaiKhoanDTO layTaiKhoanTheoMa(String maTaiKhoan) throws SQLException {
        String sql = "SELECT * FROM taikhoan WHERE ma_tai_khoan = ?";
        
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setString(1, maTaiKhoan);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String tenDangNhap = rs.getString("ten_dang_nhap");
                String matKhau = rs.getString("mat_khau");
                String maPhanQuyen = rs.getString("ma_phan_quyen");
                String maThe = rs.getString("ma_the");
                
                Integer maNhanVien = null;
                if (rs.getObject("ma_nhan_vien") != null) {
                    maNhanVien = rs.getInt("ma_nhan_vien");
                }
                
                return new TaiKhoanDTO(maTaiKhoan, tenDangNhap, matKhau, maPhanQuyen, maNhanVien, maThe);
            }
        }
        
        return null;
    }
    
    // Lấy thông tin tài khoản theo mã nhân viên
    public TaiKhoanDTO layTaiKhoanTheoMaNhanVien(int maNhanVien) throws SQLException {
        String sql = "SELECT * FROM taikhoan WHERE ma_nhan_vien = ?";
        
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, maNhanVien);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String maTaiKhoan = rs.getString("ma_tai_khoan");
                String tenDangNhap = rs.getString("ten_dang_nhap");
                String matKhau = rs.getString("mat_khau");
                String maPhanQuyen = rs.getString("ma_phan_quyen");
                String maThe = rs.getString("ma_the");
                
                return new TaiKhoanDTO(maTaiKhoan, tenDangNhap, matKhau, maPhanQuyen, maNhanVien, maThe);
            }
        }
        
        return null;
    }
    
 public boolean ganPhanQuyen(String maTaiKhoan, String maPhanQuyen) throws SQLException {
    String sql = String.format(
        "UPDATE lbr.TaiKhoan SET ma_phan_quyen = '%s' WHERE ma_tai_khoan = '%s'",
        maPhanQuyen, maTaiKhoan
    );
    
    return db.executeUpdate(sql) > 0;
}
 
 // Xóa tài khoản theo tên đăng nhập
public boolean xoaTaiKhoanTheoTenDangNhap(String tenDangNhap) throws SQLException {
    String sql = "DELETE FROM taikhoan WHERE ten_dang_nhap = ?";
    
    try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
        stmt.setString(1, tenDangNhap);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }
}

// Xóa tài khoản theo mã tài khoản
public boolean xoaTaiKhoanTheoMa(String maTaiKhoan) throws SQLException {
    String sql = "DELETE FROM taikhoan WHERE ma_tai_khoan = ?";
    
    try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
        stmt.setString(1, maTaiKhoan);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }
}
    
public boolean capNhatTaiKhoan(String maTaiKhoan, String tenDangNhapMoi, String maPhanQuyenMoi) throws SQLException {
    // Kiểm tra xem tên đăng nhập mới đã tồn tại cho tài khoản khác chưa
    String sqlCheck = "SELECT COUNT(*) FROM taikhoan WHERE ten_dang_nhap = ? AND ma_tai_khoan != ?";
    try (PreparedStatement stmt = db.getConnection().prepareStatement(sqlCheck)) {
        stmt.setString(1, tenDangNhapMoi);
        stmt.setString(2, maTaiKhoan);
        ResultSet rs = stmt.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            return false; // Tên đăng nhập đã tồn tại cho tài khoản khác
        }
    }
    
    // Cập nhật thông tin tài khoản
    String sql = "UPDATE taikhoan SET ten_dang_nhap = ?, ma_phan_quyen = ? WHERE ma_tai_khoan = ?";
    try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
        stmt.setString(1, tenDangNhapMoi);
        
        // Kiểm tra xem ma_phan_quyen có phải là giá trị mặc định không
        if (maPhanQuyenMoi != null && !maPhanQuyenMoi.equals("-- Chọn phân quyền --")) {
            stmt.setString(2, maPhanQuyenMoi);
        } else {
            stmt.setNull(2, Types.VARCHAR);
        }
        
        stmt.setString(3, maTaiKhoan);
        
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }
}

// Thêm phương thức cho việc lấy mã tài khoản dựa trên tên đăng nhập
public String layMaTaiKhoanTheoTenDangNhap(String tenDangNhap) throws SQLException {
    String sql = "SELECT ma_tai_khoan FROM taikhoan WHERE ten_dang_nhap = ?";
    try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
        stmt.setString(1, tenDangNhap);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getString("ma_tai_khoan");
        }
    }
    return null;
}

}