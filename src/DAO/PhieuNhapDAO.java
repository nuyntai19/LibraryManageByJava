package DAO;

import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import java.sql.*;
import java.util.ArrayList;

public class PhieuNhapDAO {
    
    private Connection con;

    public PhieuNhapDAO() {
        try {
            con = new mySQLConnect().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private mySQLConnect db = new mySQLConnect();
    private ChiTietPhieuNhapDAO ctDAO = new ChiTietPhieuNhapDAO();
    
    public boolean kiemTraTonTai(String maPhieu) throws SQLException {
        String sql = "SELECT 1 FROM PhieuNhap WHERE ma_phieu_nhap = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setString(1, maPhieu);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public void lapPhieuNhap(PhieuNhapDTO phieu, ArrayList<ChiTietPhieuNhapDTO> dsCT) throws SQLException {
        if (kiemTraTonTai(phieu.getMaPhieuNhap())) {
            // Cập nhật phiếu nhập
            String sqlUpdate = "UPDATE PhieuNhap SET loai_nhap = ?, so_luong_sach = ?, tong_tien = ?, ngay_nhap = ?, ma_nhan_vien = ?, ma_co_so = ? WHERE ma_phieu_nhap = ?";
            try (PreparedStatement stmt = db.getConnection().prepareStatement(sqlUpdate)) {
                stmt.setString(1, phieu.getLoaiNhap());
                stmt.setInt(2, phieu.getSoLuongSach());
                stmt.setInt(3, phieu.getTongTien());
                stmt.setDate(4, new java.sql.Date(phieu.getNgayNhap().getTime()));
                stmt.setInt(5, phieu.getMaNhanVien());
                stmt.setString(6, phieu.getMaCoSo());
                stmt.setString(7, phieu.getMaPhieuNhap());
                stmt.executeUpdate();
            }

            // Kiểm tra và chỉ thêm các chi tiết phiếu nhập mới
            for (ChiTietPhieuNhapDTO ct : dsCT) {
                if (!kiemTraChiTietTonTai(ct)) {
                    ctDAO.themChiTietPhieuNhap(ct);
                }
            }
        } else {
            // Thêm phiếu nhập mới
            String sqlInsert = "INSERT INTO PhieuNhap(ma_phieu_nhap, loai_nhap, so_luong_sach, tong_tien, ngay_nhap, ma_nhan_vien, ma_co_so) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = db.getConnection().prepareStatement(sqlInsert)) {
                stmt.setString(1, phieu.getMaPhieuNhap());
                stmt.setString(2, phieu.getLoaiNhap());
                stmt.setInt(3, phieu.getSoLuongSach());
                stmt.setInt(4, phieu.getTongTien());
                stmt.setDate(5, new java.sql.Date(phieu.getNgayNhap().getTime()));
                stmt.setInt(6, phieu.getMaNhanVien());
                stmt.setString(7, phieu.getMaCoSo());
                stmt.executeUpdate();
            }

            // Thêm chi tiết phiếu nhập
            for (ChiTietPhieuNhapDTO ct : dsCT) {
                ctDAO.themChiTietPhieuNhap(ct);
            }
        }
    }
    
    // Hàm kiểm tra chi tiết phiếu nhập đã tồn tại hay chưa
    private boolean kiemTraChiTietTonTai(ChiTietPhieuNhapDTO ct) throws SQLException {
        String sql = "SELECT 1 FROM ChiTietPhieuNhap WHERE ma_phieu_nhap = ? AND ma_sach = ? AND don_gia = ? AND so_luong = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setString(1, ct.getMaPhieuNhap());
            stmt.setString(2, ct.getMaSach());
            stmt.setInt(3, ct.getDonGia());
            stmt.setInt(4, ct.getSoLuong());
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }


    public void xoaPhieuNhap(String maPhieuNhap) throws SQLException {
        String sql = "DELETE FROM PhieuNhap WHERE ma_phieu_nhap = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maPhieuNhap);
            stmt.executeUpdate();
        }
    }

    
    public ArrayList<PhieuNhapDTO> timKiemPhieuNhap(String tuKhoa) throws SQLException {
        ArrayList<PhieuNhapDTO> ketQua = new ArrayList<>();
        String sql = "SELECT * FROM PhieuNhap WHERE " +
                     "ma_phieu_nhap LIKE ? OR " +
                     "loai_nhap LIKE ? OR " +
                     "CAST(so_luong_sach AS CHAR) LIKE ? OR " +
                     "CAST(tong_tien AS CHAR) LIKE ? OR " +
                     "CAST(ngay_nhap AS CHAR) LIKE ? OR " +
                     "CAST(ma_nhan_vien AS CHAR) LIKE ? OR " +
                     "ma_co_so LIKE ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            for (int i = 1; i <= 7; i++) {
                stmt.setString(i, "%" + tuKhoa + "%");
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ketQua.add(new PhieuNhapDTO(
                        rs.getString("ma_phieu_nhap"),
                        rs.getString("loai_nhap"),
                        rs.getInt("so_luong_sach"),
                        rs.getInt("tong_tien"),
                        rs.getDate("ngay_nhap"),
                        rs.getInt("ma_nhan_vien"),
                        rs.getString("ma_co_so")
                ));
            }
        }
        return ketQua;
    }
    
    public ArrayList<PhieuNhapDTO> layDanhSachPhieuNhap() throws SQLException {
        ArrayList<PhieuNhapDTO> ds = new ArrayList<>();
        String sql = "SELECT * FROM PhieuNhap";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PhieuNhapDTO phieu = new PhieuNhapDTO(
                    rs.getString("ma_phieu_nhap"),
                    rs.getString("loai_nhap"),
                    rs.getInt("so_luong_sach"),
                    rs.getInt("tong_tien"),
                    rs.getDate("ngay_nhap"),
                    rs.getInt("ma_nhan_vien"),
                    rs.getString("ma_co_so")
                );
                ds.add(phieu);
            }
        }
        return ds;
    }
    
    public ArrayList<String> layDanhSachMaCoSo() throws SQLException {
        ArrayList<String> ds = new ArrayList<>();
        String sql = "SELECT ma_co_so FROM CoSoNhap";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            ds.add(rs.getString("ma_co_so"));
        }
        return ds;
    }
    
    public ArrayList<Integer> layDanhSachNam() throws SQLException {
        ArrayList<Integer> dsNam = new ArrayList<>();
        String sql = "SELECT DISTINCT YEAR(ngay_nhap) AS nam FROM PhieuNhap ORDER BY nam";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                dsNam.add(rs.getInt("nam"));
            }
        }
        return dsNam;
    }

    public ArrayList<Integer> layTongSachTheoNam() throws SQLException {
        ArrayList<Integer> dsTong = new ArrayList<>();
        String sql = "SELECT SUM(so_luong_sach) AS tong_sach " +
                     "FROM PhieuNhap " +
                     "GROUP BY YEAR(ngay_nhap) " +
                     "ORDER BY YEAR(ngay_nhap)";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                dsTong.add(rs.getInt("tong_sach"));
            }
        }
        return dsTong;
    }
    

    
}

