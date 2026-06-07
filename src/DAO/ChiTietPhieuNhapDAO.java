package DAO;

import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChiTietPhieuNhapDAO {
    private mySQLConnect db = new mySQLConnect();

        public void themChiTietPhieuNhap(ChiTietPhieuNhapDTO ct) throws SQLException {
            if (!kiemTraChiTietTonTai(ct)) { // Kiểm tra trước khi thêm
                String sql = "INSERT INTO ChiTietPhieuNhap(ma_phieu_nhap, ma_sach, don_gia, so_luong, thanh_tien) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
                    stmt.setString(1, ct.getMaPhieuNhap());
                    stmt.setString(2, ct.getMaSach());
                    stmt.setInt(3, ct.getDonGia());
                    stmt.setInt(4, ct.getSoLuong());
                    stmt.setInt(5, ct.getThanhTien());
                    stmt.executeUpdate();
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
    
    public void xoaChiTietTheoMaPhieu(String maPhieu) throws SQLException {
        String sql = "DELETE FROM ChiTietPhieuNhap WHERE ma_phieu_nhap = ?";
        if (db.getConnection() == null || db.getConnection().isClosed()) {
            db = new mySQLConnect();
        }
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setString(1, maPhieu);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }


    public void xoaChiTietVaPhieuNhap(int maCTPN, String maPhieuNhap) throws SQLException {
        String sqlXoaCT = "DELETE FROM ChiTietPhieuNhap WHERE ma_ctpn = ?";
        String sqlXoaPhieu = "DELETE FROM PhieuNhap WHERE ma_phieu_nhap = ?";

        try (Connection conn = db.getConnection()) {
            conn.setAutoCommit(false);
            try (
                PreparedStatement stmtCT = conn.prepareStatement(sqlXoaCT);
                PreparedStatement stmtPhieu = conn.prepareStatement(sqlXoaPhieu)
            ) {
                stmtCT.setInt(1, maCTPN);
                stmtCT.executeUpdate();

                stmtPhieu.setString(1, maPhieuNhap);
                stmtPhieu.executeUpdate();

                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }


    public ArrayList<ChiTietPhieuNhapDTO> layDanhSachTheoMaPhieu(String maPhieuNhap) throws SQLException {
        ArrayList<ChiTietPhieuNhapDTO> ds = new ArrayList<>();
        String sql = "SELECT ctpn.*, s.ten_sach FROM ChiTietPhieuNhap ctpn JOIN sach s ON ctpn.ma_sach = s.ma_sach WHERE ctpn.ma_phieu_nhap = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = db.getConnection(); 
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, maPhieuNhap);

            rs = stmt.executeQuery();
            while (rs.next()) {
                ChiTietPhieuNhapDTO ct = new ChiTietPhieuNhapDTO(
                    rs.getInt("ma_ctpn"),
                    rs.getString("ma_phieu_nhap"),
                    rs.getString("ma_sach"),
                    rs.getString("ten_sach"),
                    rs.getInt("don_gia"),
                    rs.getInt("so_luong"),
                    rs.getInt("thanh_tien")
                );
                ds.add(ct);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }

        return ds;
    }
    
    public ArrayList<ChiTietPhieuNhapDTO> timKiemChiTiet(String tuKhoa) throws SQLException {
        ArrayList<ChiTietPhieuNhapDTO> ketQua = new ArrayList<>();
        String sql = "SELECT ctpn.*, s.ten_sach FROM chitietphieunhap ctpn JOIN sach s ON ctpn.ma_sach = s.ma_sach WHERE " +
                     "CAST(ctpn.ma_ctpn AS CHAR) LIKE ? OR " +
                     "ctpn.ma_phieu_nhap LIKE ? OR " +
                     "ctpn.ma_sach LIKE ? OR " +
                     "s.ten_sach LIKE ? OR " +
                     "CAST(ctpn.don_gia AS CHAR) LIKE ? OR " +
                     "CAST(ctpn.so_luong AS CHAR) LIKE ? OR " +
                     "CAST(ctpn.thanh_tien AS CHAR) LIKE ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            String searchTerm = "%" + tuKhoa + "%";
            stmt.setString(1, searchTerm);
            stmt.setString(2, searchTerm);
            stmt.setString(3, searchTerm);
            stmt.setString(4, searchTerm);
            stmt.setString(5, searchTerm);
            stmt.setString(6, searchTerm);
            stmt.setString(7, searchTerm);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ketQua.add(new ChiTietPhieuNhapDTO(
                        rs.getInt("ma_ctpn"),
                        rs.getString("ma_phieu_nhap"),
                        rs.getString("ma_sach"),
                        rs.getString("ten_sach"),
                        rs.getInt("don_gia"),
                        rs.getInt("so_luong"),
                        rs.getInt("thanh_tien")
                ));
            }
        }
        return ketQua;
    }
    
    public ArrayList<ChiTietPhieuNhapDTO> layTatCaChiTietPhieuNhap() throws SQLException {
        ArrayList<ChiTietPhieuNhapDTO> ds = new ArrayList<>();
        String sql = "SELECT ctpn.*, s.ten_sach FROM ChiTietPhieuNhap ctpn JOIN sach s ON ctpn.ma_sach = s.ma_sach";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ChiTietPhieuNhapDTO ct = new ChiTietPhieuNhapDTO(
                    rs.getInt("ma_ctpn"),
                    rs.getString("ma_phieu_nhap"),
                    rs.getString("ma_sach"),
                    rs.getString("ten_sach"),
                    rs.getInt("don_gia"),
                    rs.getInt("so_luong"),
                    rs.getInt("thanh_tien")
                );
                ds.add(ct);
            }
        }

        return ds;
    }
    
    public Map<String, String> getMaSachTenSachMapFromSachTable() throws SQLException {
        Map<String, String> sachMap = new HashMap<>();
        String sql = "SELECT ma_sach, ten_sach FROM sach ORDER BY ten_sach";
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                sachMap.put(rs.getString("ma_sach"), rs.getString("ten_sach"));
            }
        }
        return sachMap;
    }


}
