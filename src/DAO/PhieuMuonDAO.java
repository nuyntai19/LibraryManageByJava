/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import static DAO.JDBCUtil.getConnection;
import java.sql.PreparedStatement;
import java.time.ZoneId;

import DTO.PhieuMuonDTO;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.awt.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.Random;
import javax.lang.model.util.Types;
/**
 *
 * @author TRUONG THI NGOC NHI
 */
public class PhieuMuonDAO {
    
    private mySQLConnect db = new mySQLConnect();
    private ResultSet rs=null;
    
    
    private mySQLConnect db = new mySQLConnect();
    private ResultSet rs=null;
    
    
public ArrayList<PhieuMuonDTO> getAllPhieuMuonDTO() {
    ArrayList<PhieuMuonDTO> list = new ArrayList<>();
    try {
        Connection conn = JDBCUtil.getConnection(); // kết nối CSDL
        Statement st = conn.createStatement();
        String sql = "SELECT * FROM phieumuon"; // lấy toàn bộ dữ liệu
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            LocalDate ngayMuon = rs.getDate("ngay_muon") != null ? rs.getDate("ngay_muon").toLocalDate() : null;
            LocalDate hanTra = rs.getDate("han_tra") != null ? rs.getDate("han_tra").toLocalDate() : null;

            java.sql.Date ngayTraSQL = rs.getDate("ngay_tra_thuc_te");
            LocalDate ngayTraThucTe = ngayTraSQL != null ? ngayTraSQL.toLocalDate() : null;

            PhieuMuonDTO pm = new PhieuMuonDTO(
                rs.getString("ma_phieu_muon"),
                rs.getString("ma_doc_gia"),
                rs.getString("ma_sach"),
                ngayMuon,
                hanTra,
                ngayTraThucTe,
                rs.getInt("trang_thai"),
                rs.getDouble("tien_phat")
            );

            list.add(pm);
        }

        JDBCUtil.closeConnection(conn);
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}


public boolean Them(PhieuMuonDTO pm) {
    boolean result = false;
    try {
        Connection conn = JDBCUtil.getConnection();
        Statement st = conn.createStatement();

        String ngayTraThucTe = (pm.getNgayTraThucTe() != null) ? 
            "'" + java.sql.Date.valueOf(pm.getNgayTraThucTe()) + "'" : "null";

        String sql = String.format(
            "INSERT INTO phieumuon (ma_phieu_muon, ma_doc_gia, ma_sach, ngay_muon, han_tra, ngay_tra_thuc_te, trang_thai, tien_phat) " +
            "VALUES ('%s', '%s', '%s', '%tF', '%tF', %s, %d, %.2f)",
            pm.getMaPhieuMuon(),
            pm.getMaDocGia(),
            pm.getMaSach(),
            pm.getNgayMuon(),
            pm.getHanTra(),
            ngayTraThucTe,
            pm.getTrangThai(),
            pm.getTienPhat()
        );

        int rowsAffected = st.executeUpdate(sql);
        result = rowsAffected > 0;
        JDBCUtil.closeConnection(conn);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return result;
}
public boolean Sua(PhieuMuonDTO pm) {
    boolean result = false;
    try {
        Connection conn = JDBCUtil.getConnection();
        Statement st = conn.createStatement();

        String ngayTraThucTe = (pm.getNgayTraThucTe() != null) ? 
            "'" + java.sql.Date.valueOf(pm.getNgayTraThucTe()) + "'" : "null";

        String sql = String.format(
            "UPDATE phieumuon SET ma_doc_gia = '%s', ma_sach = '%s', ngay_muon = '%tF', han_tra = '%tF', " +
            "ngay_tra_thuc_te = %s, trang_thai = %d, tien_phat = %.2f WHERE ma_phieu_muon = '%s'",
            pm.getMaDocGia(),
            pm.getMaSach(),
            pm.getNgayMuon(),
            pm.getHanTra(),
            ngayTraThucTe,
            pm.getTrangThai(),
            pm.getTienPhat(),
            pm.getMaPhieuMuon()
        );

        int rowsAffected = st.executeUpdate(sql);
        result = rowsAffected > 0;
        JDBCUtil.closeConnection(conn);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return result;
}
//public boolean Xoa(String maPhieuMuon) {
//    boolean result = false;
//    try {
//        Connection conn = JDBCUtil.getConnection();
//        Statement st = conn.createStatement();
//
//        String sql = String.format(
//            "UPDATE phieumuon SET trang_thai = 0 WHERE ma_phieu_muon = '%s'",
//            maPhieuMuon
//        );
//
//        int rowsAffected = st.executeUpdate(sql);
//        result = rowsAffected > 0;
//        JDBCUtil.closeConnection(conn);
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//    return result;
//}
public boolean Xoa(String maPhieuMuon) {
    try {
        Connection conn = JDBCUtil.getConnection();
        String sql = "DELETE FROM phieumuon WHERE ma_phieu_muon = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, maPhieuMuon);
        int affected = pst.executeUpdate();
        JDBCUtil.closeConnection(conn);
        return affected > 0;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

//public boolean DaTra(String maPhieuMuon) {
//    boolean result = false;
//    try {
//        Connection conn = JDBCUtil.getConnection();
//        Statement st = conn.createStatement();
//
//        String sql = String.format(
//            "UPDATE phieumuon SET trang_thai = 0 WHERE ma_phieu_muon = '%s'",
//            maPhieuMuon
//        );
//
//        int rowsAffected = st.executeUpdate(sql);
//        result = rowsAffected > 0;
//        JDBCUtil.closeConnection(conn);
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//    return result;
//}
public boolean DaTra(String maPhieuMuon) {
    boolean result = false;
    try {
        Connection conn = JDBCUtil.getConnection();
        
        String sql = "UPDATE phieumuon SET trang_thai = ?, ngay_tra_thuc_te = ? WHERE ma_phieu_muon = ?";
        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setInt(1, 0); // 0: đã trả
        pst.setDate(2, java.sql.Date.valueOf(LocalDate.now())); // ngày hiện tại
        pst.setString(3, maPhieuMuon);

        int rowsAffected = pst.executeUpdate();
        result = rowsAffected > 0;

        JDBCUtil.closeConnection(conn);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return result;
}

public String getMaPhieuMuonCuoi() {
    String maCuoi = null;
    try {
        Connection conn = JDBCUtil.getConnection();
        String sql = "SELECT ma_phieu_muon FROM phieumuon ORDER BY ma_phieu_muon DESC LIMIT 1";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            maCuoi = rs.getString("ma_phieu_muon"); // ví dụ: PM009
        }

        JDBCUtil.closeConnection(conn);
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return maCuoi; // nếu null thì xử lý phía BUS
}

private ArrayList<PhieuMuonDTO> danhSachPhieuMuon = new ArrayList<>();

public PhieuMuonDTO timTheoMa(String maPhieuMuon) {
    PhieuMuonDTO pm = null;
    try {
        Connection conn = JDBCUtil.getConnection();
        String sql = "SELECT * FROM phieumuon WHERE ma_phieu_muon = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, maPhieuMuon);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            pm = new PhieuMuonDTO();
            pm.setMaPhieuMuon(rs.getString("ma_phieu_muon"));
            pm.setMaDocGia(rs.getString("ma_doc_gia"));
            pm.setMaSach(rs.getString("ma_sach"));
            pm.setNgayMuon(rs.getDate("ngay_muon").toLocalDate());
            pm.setHanTra(rs.getDate("han_tra").toLocalDate());
Date ngayTraTT = rs.getDate("ngay_tra_thuc_te");
if (ngayTraTT != null) {
    pm.setNgayTraThucTe(((java.sql.Date) ngayTraTT).toLocalDate()); 
    pm.setNgayTraThucTe(((java.sql.Date) ngayTraTT).toLocalDate()); 
}

            pm.setTrangThai(rs.getInt("trang_thai"));
            pm.setTienPhat(rs.getDouble("tien_phat"));
        }

        JDBCUtil.closeConnection(conn);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return pm;
}

        public String taoMaPhieumuonNgauNhien() throws SQLException {
        Random rand = new Random();
        String maPhieumuon;
        boolean maTonTai;
        
        do {
            // Tạo số ngẫu nhiên 8 chữ số
            int randomNum = 100000 + rand.nextInt(900000);
            maPhieumuon = String.valueOf(randomNum);
            
            // Kiểm tra mã đã tồn tại chưa
            String sql = "SELECT COUNT(*) FROM taikhoan WHERE ma_tai_khoan = ?";
            try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
                stmt.setString(1, maPhieumuon);
                ResultSet rs = stmt.executeQuery();
                maTonTai = rs.next() && rs.getInt(1) > 0;
            }
        } while (maTonTai); // Lặp lại nếu mã đã tồn tại
        
        return maPhieumuon;
    }
     
        
        public ArrayList<PhieuMuonDTO> layPhieuMuonTheoDocGia(String maDocGia) throws SQLException {
        ArrayList<PhieuMuonDTO> danhSach = new ArrayList<>();
        Connection conn = db.getConnection(); // Kết nối DB, mày nhớ xử lý đóng sau khi dùng
        String sql = "SELECT * FROM PhieuMuon WHERE ma_doc_gia = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, maDocGia);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            PhieuMuonDTO pm = new PhieuMuonDTO();
            pm.setMaPhieuMuon(rs.getString("ma_phieu_muon"));
            pm.setMaDocGia(rs.getString("ma_doc_gia"));
            pm.setMaSach(rs.getString("ma_sach"));

            // Convert java.sql.Date -> LocalDate
            java.sql.Date sqlNgayMuon = rs.getDate("ngay_muon");
            java.sql.Date sqlHanTra = rs.getDate("han_tra");
            java.sql.Date sqlNgayTraThucTe = rs.getDate("ngay_tra_thuc_te");

            pm.setNgayMuon(sqlNgayMuon != null ? sqlNgayMuon.toLocalDate() : null);
            pm.setHanTra(sqlHanTra != null ? sqlHanTra.toLocalDate() : null);
            pm.setNgayTraThucTe(sqlNgayTraThucTe != null ? sqlNgayTraThucTe.toLocalDate() : null);

            pm.setTrangThai(rs.getInt("trang_thai"));
            pm.setTienPhat(rs.getDouble("tien_phat"));

            danhSach.add(pm);
        }

        // Đóng kết nối sau khi xài xong (tốt nhất nên dùng try-with-resources nhưng tạm vậy)
        rs.close();
        stmt.close();
        conn.close();

        return danhSach;
    }
        
//    public boolean capNhatTrangThai(String maPhieuMuon, String trangThaiMoi) {
//        String sql = "UPDATE PhieuMuon SET trang_thai = ? WHERE ma_phieu = ?";
//        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
//            stmt.setString(1, trangThaiMoi);
//            stmt.setString(2, maPhieuMuon);
//            int rows = stmt.executeUpdate();
//            return rows > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
        
        public boolean capNhatTrangThai(String maPhieuMuon, int trangThaiMoi) throws SQLException {
    String sql = "UPDATE PhieuMuon SET trang_thai = ? WHERE ma_phieu_muon = ?";
    try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
        stmt.setInt(1, trangThaiMoi);
        stmt.setString(2, maPhieuMuon);
        return stmt.executeUpdate() > 0;
    }
}
        public String taoMaPhieumuonNgauNhien() throws SQLException {
        Random rand = new Random();
        String maPhieumuon;
        boolean maTonTai;
        
        do {
            // Tạo số ngẫu nhiên 8 chữ số
            int randomNum = 100000 + rand.nextInt(900000);
            maPhieumuon = String.valueOf(randomNum);
            
            // Kiểm tra mã đã tồn tại chưa
            String sql = "SELECT COUNT(*) FROM taikhoan WHERE ma_tai_khoan = ?";
            try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
                stmt.setString(1, maPhieumuon);
                ResultSet rs = stmt.executeQuery();
                maTonTai = rs.next() && rs.getInt(1) > 0;
            }
        } while (maTonTai); // Lặp lại nếu mã đã tồn tại
        
        return maPhieumuon;
    }
     
        
        public ArrayList<PhieuMuonDTO> layPhieuMuonTheoDocGia(String maDocGia) throws SQLException {
        ArrayList<PhieuMuonDTO> danhSach = new ArrayList<>();
        Connection conn = db.getConnection(); // Kết nối DB, mày nhớ xử lý đóng sau khi dùng
        String sql = "SELECT * FROM PhieuMuon WHERE ma_doc_gia = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, maDocGia);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            PhieuMuonDTO pm = new PhieuMuonDTO();
            pm.setMaPhieuMuon(rs.getString("ma_phieu_muon"));
            pm.setMaDocGia(rs.getString("ma_doc_gia"));
            pm.setMaSach(rs.getString("ma_sach"));

            // Convert java.sql.Date -> LocalDate
            java.sql.Date sqlNgayMuon = rs.getDate("ngay_muon");
            java.sql.Date sqlHanTra = rs.getDate("han_tra");
            java.sql.Date sqlNgayTraThucTe = rs.getDate("ngay_tra_thuc_te");

            pm.setNgayMuon(sqlNgayMuon != null ? sqlNgayMuon.toLocalDate() : null);
            pm.setHanTra(sqlHanTra != null ? sqlHanTra.toLocalDate() : null);
            pm.setNgayTraThucTe(sqlNgayTraThucTe != null ? sqlNgayTraThucTe.toLocalDate() : null);

            pm.setTrangThai(rs.getInt("trang_thai"));
            pm.setTienPhat(rs.getDouble("tien_phat"));

            danhSach.add(pm);
        }

        // Đóng kết nối sau khi xài xong (tốt nhất nên dùng try-with-resources nhưng tạm vậy)
        rs.close();
        stmt.close();
        conn.close();

        return danhSach;
    }

}
