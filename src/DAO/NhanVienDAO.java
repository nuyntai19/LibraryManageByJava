package DAO;

import DTO.NhanVienDTO;
import java.sql.*;
import java.util.ArrayList;

public class NhanVienDAO {
    private mySQLConnect db = new mySQLConnect();

    public boolean kiemTraSuTonTai(int idNhanVien) throws SQLException {
        String sql = "SELECT COUNT(*) FROM nhanvien WHERE ma_nhan_vien = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, idNhanVien);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public ArrayList<NhanVienDTO> layDanhSachNhanVien() throws SQLException {
        ArrayList<NhanVienDTO> dsnv = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO(
                    rs.getInt("ma_nhan_vien"), 
                    rs.getString("ten_nhan_vien"),
                    rs.getString("gioi_tinh"),
                    rs.getDate("ngay_sinh"),
                    rs.getString("dia_chi"),
                    rs.getString("so_dien_thoai"),
                    rs.getString("email"),
                    rs.getDate("ngay_vao_lam"),
                    rs.getString("trang_thai")
                );
                dsnv.add(nv);
            }
        }
        return dsnv;
    }


    public void themNhanVien(NhanVienDTO nv) throws SQLException {
        String sql = "INSERT INTO nhanvien (ten_nhan_vien, gioi_tinh, ngay_sinh, dia_chi, so_dien_thoai, email, ngay_vao_lam, trang_thai) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setString(1, nv.getTenNhanVien());
            stmt.setString(2, nv.getGioiTinh());
            stmt.setDate(3, nv.getNgaySinh());
            stmt.setString(4, nv.getDiaChi());
            stmt.setString(5, nv.getSoDienThoai());
            stmt.setString(6, nv.getEmail());
            stmt.setDate(7, nv.getNgayVaoLam());
            stmt.setString(8, nv.getTrangThai());

            stmt.executeUpdate(); // Thực thi câu lệnh
        }
    }


    public void suaNhanVien(NhanVienDTO nv) throws SQLException {
        String sql = "UPDATE nhanvien SET ten_nhan_vien = ?, gioi_tinh = ?, ngay_sinh = ?, dia_chi = ?, so_dien_thoai = ?, email = ?, ngay_vao_lam = ?, trang_thai = ? WHERE ma_nhan_vien = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setString(1, nv.getTenNhanVien());
            stmt.setString(2, nv.getGioiTinh());
            stmt.setDate(3, nv.getNgaySinh());
            stmt.setString(4, nv.getDiaChi());
            stmt.setString(5, nv.getSoDienThoai());
            stmt.setString(6, nv.getEmail());
            stmt.setDate(7, nv.getNgayVaoLam());
            stmt.setString(8, nv.getTrangThai());
            stmt.setInt(9, nv.getIdNhanVien());
            stmt.executeUpdate();
        }
    }

    public void xoaNhanVien(int idNhanVien) throws SQLException {
        String sql = "DELETE FROM nhanvien WHERE ma_nhan_vien = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, idNhanVien);
            stmt.executeUpdate();
        }
    }

    public ArrayList<NhanVienDTO> timKiemNhanVien(String tuKhoa) throws SQLException {
        ArrayList<NhanVienDTO> ketQua = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien WHERE " +
                     "CAST(ma_nhan_vien AS CHAR) LIKE ? OR " +
                     "ten_nhan_vien LIKE ? OR " +
                     "gioi_tinh LIKE ? OR " +
                     "CAST(ngay_sinh AS CHAR) LIKE ? OR " +
                     "dia_chi LIKE ? OR " +
                     "so_dien_thoai LIKE ? OR " +
                     "email LIKE ? OR " +
                     "CAST(ngay_vao_lam AS CHAR) LIKE ? OR " +
                     "trang_thai LIKE ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            for (int i = 1; i <= 9; i++) {
                stmt.setString(i, "%" + tuKhoa + "%");
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ketQua.add(new NhanVienDTO(
                    rs.getInt("ma_nhan_vien"), 
                    rs.getString("ten_nhan_vien"),
                    rs.getString("gioi_tinh"),
                    rs.getDate("ngay_sinh"),
                    rs.getString("dia_chi"),
                    rs.getString("so_dien_thoai"),
                    rs.getString("email"),
                    rs.getDate("ngay_vao_lam"),
                    rs.getString("trang_thai")
                ));
            }
        }
        return ketQua;
    }


    public void capNhatTrangThai(int maNhanVien, String trang_thai) throws SQLException {
        String sql = "UPDATE nhanvien SET trang_thai = ? WHERE ma_nhan_vien = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setString(1, trang_thai);
            stmt.setInt(2, maNhanVien);
            stmt.executeUpdate();
        }
    }
    
    public NhanVienDTO layThongTinNhanVien(int maNhanVien) throws SQLException {
    String sql = "SELECT * FROM nhanvien WHERE ma_nhan_vien = ?";
    
    try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
        stmt.setInt(1, maNhanVien);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            NhanVienDTO nhanVien = new NhanVienDTO();
            nhanVien.setIdNhanVien(rs.getInt("ma_nhan_vien"));
            nhanVien.setTenNhanVien(rs.getString("ten_nhan_vien"));
            nhanVien.setNgayVaoLam(rs.getDate("ngay_vao_lam"));
            // Thêm các thuộc tính khác nếu cần
            
            return nhanVien;
        }
    }
    
    return null;
}

}
