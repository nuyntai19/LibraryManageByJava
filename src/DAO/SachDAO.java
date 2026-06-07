package DAO;

import DTO.SachDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private Connection conn;

    public SachDAO() {
        // Khởi tạo kết nối từ lớp mySQLConnect
        mySQLConnect connection = new mySQLConnect();
        try {
            this.conn = connection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Thêm sách
    public boolean themSach(SachDTO sach) throws SQLException {
        String sql = "INSERT INTO lbr.Sach VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, sach.getMaSach());
        ps.setString(2, sach.getTenSach());
        ps.setString(3, sach.getTacGia());
        ps.setString(4, sach.getTheLoai());
        ps.setString(5, sach.getNgonNgu());
        ps.setInt(6, sach.getSoLuong());
        ps.setString(7, sach.getNhaXuatBan());
        ps.setInt(8, sach.getNamXuatBan());
        ps.setString(9, sach.getFilePdf());
        ps.setBoolean(10, sach.isDocTaiCho());
        ps.setBoolean(11, sach.isMuonVe());
        ps.setBoolean(12, sach.isDocFilePdf());
        return ps.executeUpdate() > 0;
    }

    // Sửa sách
    public boolean suaSach(SachDTO sach) throws SQLException {
        String sql = "UPDATE lbr.Sach SET ten_sach=?, tac_gia=?, the_loai=?, ngon_ngu=?, so_luong=?, nha_xuat_ban=?, nam_xuat_ban=?, file_pdf=?, doc_tai_cho=?, muon_ve=?, doc_file_pdf=? WHERE ma_sach=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, sach.getTenSach());
        ps.setString(2, sach.getTacGia());
        ps.setString(3, sach.getTheLoai());
        ps.setString(4, sach.getNgonNgu());
        ps.setInt(5, sach.getSoLuong());
        ps.setString(6, sach.getNhaXuatBan());
        ps.setInt(7, sach.getNamXuatBan());
        ps.setString(8, sach.getFilePdf());
        ps.setBoolean(9, sach.isDocTaiCho());
        ps.setBoolean(10, sach.isMuonVe());
        ps.setBoolean(11, sach.isDocFilePdf());
        ps.setString(12, sach.getMaSach());
        return ps.executeUpdate() > 0;
    }

    // Xóa sách
    public boolean xoaSach(String maSach) throws SQLException {
        String sql = "DELETE FROM lbr.Sach WHERE ma_sach=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, maSach);
        return ps.executeUpdate() > 0;
    }

    // Tìm kiếm tất cả các trường
    public List<SachDTO> timKiemTatCa(String tuKhoa) throws SQLException {
        String sql = "SELECT * FROM lbr.Sach WHERE " +
                     "ma_sach LIKE ? OR " +
                     "ten_sach LIKE ? OR " +
                     "tac_gia LIKE ? OR " +
                     "the_loai LIKE ? OR " +
                     "ngon_ngu LIKE ? OR " +
                     "CAST(so_luong AS CHAR) LIKE ? OR " +
                     "nha_xuat_ban LIKE ? OR " +
                     "CAST(nam_xuat_ban AS CHAR) LIKE ? OR " +
                     "file_pdf LIKE ? OR " +
                     "CAST(doc_tai_cho AS CHAR) LIKE ? OR " +
                     "CAST(muon_ve AS CHAR) LIKE ? OR " +
                     "CAST(doc_file_pdf AS CHAR) LIKE ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        String keyword = "%" + tuKhoa + "%";
        for (int i = 1; i <= 12; i++) {
            ps.setString(i, keyword);
        }

        ResultSet rs = ps.executeQuery();
        List<SachDTO> ds = new ArrayList<>();
        while (rs.next()) {
            ds.add(mapResultSetToSach(rs));
        }
        return ds;
    }

    // Giảm số lượng sách (sử dụng khi mượn sách)
    public boolean giamSoLuong(String maSach, int soLuongGiam) throws SQLException {
        String sql = "UPDATE lbr.Sach SET so_luong = so_luong - ? WHERE ma_sach = ? AND so_luong >= ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, soLuongGiam);
        ps.setString(2, maSach);
        ps.setInt(3, soLuongGiam);  // kiểm tra không bị âm
        return ps.executeUpdate() > 0;
    }
    
    // Lấy tổng số lượng sách đang có trong bảng Sach theo thể loại
    public int laySoLuongSachDaThem(String theLoai) throws SQLException {
        String sql = "SELECT SUM(so_luong) FROM lbr.Sach WHERE the_loai = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, theLoai);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1); // Nếu không có kết quả, trả về 0
        }

        return 0;
    }

    // Lấy tổng số lượng sách đã được nhập theo thể loại (từ ChiTietPhieuNhap)
    public int laySoLuongNhapTheoTheLoai(String theLoai) throws SQLException {
        String sql = "SELECT SUM(so_luong) AS tong FROM lbr.ChiTietPhieuNhap WHERE loai_sach = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, theLoai);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("tong");
        }
        return 0;
    }
    
    
    // Lấy tổng số lượng sách trong bảng Sach
    public int laySoLuongSach() throws SQLException {
        String sql = "SELECT COUNT(*) AS tong FROM lbr.Sach";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("tong");
        }
        return 0;
    }

    // Hàm hỗ trợ: ánh xạ kết quả từ ResultSet sang SachDTO
    private SachDTO mapResultSetToSach(ResultSet rs) throws SQLException {
        return new SachDTO(
            rs.getString("ma_sach"),
            rs.getString("ten_sach"),
            rs.getString("tac_gia"),
            rs.getString("the_loai"),
            rs.getString("ngon_ngu"),
            rs.getInt("so_luong"),
            rs.getString("nha_xuat_ban"),
            rs.getInt("nam_xuat_ban"),
            rs.getString("file_pdf"),
            rs.getBoolean("doc_tai_cho"),
            rs.getBoolean("muon_ve"),
            rs.getBoolean("doc_file_pdf")
        );
    }

    // Lấy danh sách sách
    public ArrayList<SachDTO> layDanhSachSach() throws SQLException {
        ArrayList<SachDTO> ds = new ArrayList<>();
        String sql = "SELECT * FROM lbr.Sach";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                SachDTO sach = new SachDTO(
                    rs.getString("ma_sach"),
                    rs.getString("ten_sach"),
                    rs.getString("tac_gia"),
                    rs.getString("the_loai"),
                    rs.getString("ngon_ngu"),
                    rs.getInt("so_luong"),
                    rs.getString("nha_xuat_ban"),
                    rs.getInt("nam_xuat_ban"),
                    rs.getString("file_pdf"),
                    rs.getBoolean("doc_tai_cho"),
                    rs.getBoolean("muon_ve"),
                    rs.getBoolean("doc_file_pdf")
                );
                ds.add(sach);
            }
        }
        return ds;
    }
    
    
    public SachDTO timSachTheoMa(String maSach) {
        SachDTO sach = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            mySQLConnect db = new mySQLConnect();
            conn = db.getConnection();

            String sql = "SELECT * FROM lbr.Sach WHERE ma_sach = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maSach);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                sach = new SachDTO();
                sach.setMaSach(rs.getString("ma_sach"));
                sach.setTenSach(rs.getString("ten_sach"));
                sach.setTheLoai(rs.getString("the_loai"));
                sach.setTacGia(rs.getString("tac_gia"));
                sach.setNgonNgu(rs.getString("ngon_ngu"));
                sach.setNhaXuatBan(rs.getString("nha_xuat_ban"));
                sach.setNamXuatBan(rs.getInt("nam_xuat_ban"));
                sach.setDocTaiCho(rs.getBoolean("doc_tai_cho"));
                sach.setMuonVe(rs.getBoolean("muon_ve"));
                sach.setDocFilePdf(rs.getBoolean("doc_file_pdf"));
                sach.setFilePdf(rs.getString("file_pdf"));
                sach.setSoLuong(rs.getInt("so_luong"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return sach;
    }
    
    
    public List<SachDTO> timSachTheoTen(String ten) {
        List<SachDTO> ds = new ArrayList<>();
        try {
            String sql = "SELECT * FROM sach WHERE tenSach LIKE ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + ten + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ds.add(new SachDTO(
                    rs.getString("maSach"),
                    rs.getString("tenSach"),
                    rs.getString("tacGia"),
                    rs.getString("nhaXuatBan"),
                    rs.getInt("namXuatBan"),
                    rs.getInt("soLuong"),
                    rs.getDouble("gia"),
                    rs.getString("theLoai")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }
    
    public boolean capNhatSoLuong(SachDTO sach) throws SQLException {
        String sql = "UPDATE lbr.Sach SET so_luong = ? WHERE ma_sach = ?";
        PreparedStatement stmt = conn.prepareStatement(sql); // Dùng biến conn của class
        stmt.setInt(1, sach.getSoLuong());
        stmt.setString(2, sach.getMaSach());
        return stmt.executeUpdate() > 0;
    }
    
        public boolean tangSoLuong(String maSach, int soLuongGiam) throws SQLException {
        String sql = "UPDATE lbr.Sach SET so_luong = so_luong + ? WHERE ma_sach = ? AND so_luong >= ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, soLuongGiam);
        ps.setString(2, maSach);
        ps.setInt(3, soLuongGiam);  // kiểm tra không bị âm
        return ps.executeUpdate() > 0;
    }

    // Cộng dồn số lượng sách khi nhập hàng
    public boolean congDonSoLuongSach(String maSach, int soLuongNhap) throws SQLException {
        String sql = "UPDATE lbr.Sach SET so_luong = so_luong + ? WHERE ma_sach = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, soLuongNhap);
            ps.setString(2, maSach);
            return ps.executeUpdate() > 0;
        }
    }
    
}
