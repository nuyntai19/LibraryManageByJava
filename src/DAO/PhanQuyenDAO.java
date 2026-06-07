package DAO;

import DTO.PhanQuyenDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhanQuyenDAO {
    private mySQLConnect mySQL = new mySQLConnect();
    
    public PhanQuyenDAO() {
        // Constructor
    }
    
// Add these methods to your PhanQuyenDAO class
public boolean themPhanQuyen(PhanQuyenDTO phanQuyen) throws SQLException {
    String sql = String.format(
        "INSERT INTO lbr.PhanQuyen (ma_phan_quyen, ten_phan_quyen, ma_chuc_nang) VALUES ('%s', '%s', '%s')",
        phanQuyen.getMaPhanQuyen(),
        phanQuyen.getTenPhanQuyen(),
        phanQuyen.getMaChucNang()
    );
    
    return mySQL.executeUpdate(sql) > 0;
}

public ArrayList<PhanQuyenDTO> layDanhSachPhanQuyen() throws SQLException {
    ArrayList<PhanQuyenDTO> danhSach = new ArrayList<>();
    String sql = "SELECT * FROM lbr.PhanQuyen";
    ResultSet rs = mySQL.executeQuery(sql);
    
    try {
        while (rs.next()) {
            String maPhanQuyen = rs.getString("ma_phan_quyen");
            String tenPhanQuyen = rs.getString("ten_phan_quyen");
            String maChucNang = rs.getString("ma_chuc_nang");
            
            PhanQuyenDTO phanQuyen = new PhanQuyenDTO(maPhanQuyen, tenPhanQuyen, maChucNang);
            danhSach.add(phanQuyen);
        }
    } finally {
        if (rs != null) {
            rs.close();
        }
    }
    
    return danhSach;
}

// Lấy danh sách chức năng của một phân quyền
public ArrayList<String> layDanhSachChucNang(String maPhanQuyen) throws SQLException {
    ArrayList<String> dsChucNang = new ArrayList<>();
    String sql = String.format(
        "SELECT ma_chuc_nang FROM lbr.PhanQuyen WHERE ma_phan_quyen = '%s'",
        maPhanQuyen
    );
    
    ResultSet rs = mySQL.executeQuery(sql);
    try {
        while (rs.next()) {
            dsChucNang.add(rs.getString("ma_chuc_nang"));
        }
    } finally {
        if (rs != null) {
            rs.close();
        }
    }
    return dsChucNang;
}

    public ArrayList<PhanQuyenDTO> layDanhSachPhanQuyenTheoMa(String maPhanQuyen) throws SQLException {
    ArrayList<PhanQuyenDTO> danhSach = new ArrayList<>();
    String sql = String.format(
        "SELECT * FROM lbr.PhanQuyen WHERE ma_phan_quyen = '%s'",
        maPhanQuyen
    );
    ResultSet rs = mySQL.executeQuery(sql);
    
    try {
        while (rs.next()) {
            String maPQ = rs.getString("ma_phan_quyen");
            String tenPhanQuyen = rs.getString("ten_phan_quyen");
            String maChucNang = rs.getString("ma_chuc_nang");
            
            PhanQuyenDTO phanQuyen = new PhanQuyenDTO(maPQ, tenPhanQuyen, maChucNang);
            danhSach.add(phanQuyen);
        }
    } finally {
        if (rs != null) {
            rs.close();
        }
    }
    
    return danhSach;
}
    
    // Tạo mã phân quyền mới
    public String taoMaPhanQuyenMoi() throws SQLException {
        String sql = "SELECT max(CAST(SUBSTRING(ma_phan_quyen, 3) AS UNSIGNED)) as max_id FROM lbr.PhanQuyen WHERE ma_phan_quyen LIKE 'PQ%'";
        ResultSet rs = mySQL.executeQuery(sql);
        
        try {
            if (rs.next()) {
                int maxId = rs.getInt("max_id");
                return "PQ" + (maxId + 1);
            } else {
                return "PQ1";
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
    
    public String layMaPhanQuyenTuTen(String tenPhanQuyen) throws SQLException {
        String sql = String.format(
            "SELECT DISTINCT ma_phan_quyen FROM lbr.PhanQuyen WHERE ten_phan_quyen = '%s'",
            tenPhanQuyen
        );
        ResultSet rs = mySQL.executeQuery(sql);
        try {
            if (rs.next()) {
                return rs.getString("ma_phan_quyen");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }
    
    public boolean capNhatPhanQuyenTaiKhoan(String maTaiKhoan, String maPhanQuyen) throws SQLException {
    String sql = String.format(
        "UPDATE lbr.TaiKhoan SET ma_phan_quyen = '%s' WHERE ma_tai_khoan = '%s'",
        maPhanQuyen, maTaiKhoan
    );
    
    int result = mySQL.executeUpdate(sql);
    return result > 0;
}

// Lấy mã tài khoản từ mã nhân viên
public String layMaTaiKhoanTuMaNhanVien(int maNhanVien) throws SQLException {
    String sql = String.format(
        "SELECT ma_tai_khoan FROM lbr.TaiKhoan WHERE ma_nhan_vien = %d",
        maNhanVien
    );
    
    ResultSet rs = mySQL.executeQuery(sql);
    try {
        if (rs.next()) {
            return rs.getString("ma_tai_khoan");
        }
    } finally {
        if (rs != null) {
            rs.close();
        }
    }
    return null;
}

// Kiểm tra nhân viên có quyền truy cập chức năng không
public boolean kiemTraQuyenTruyCap(String maTaiKhoan, String maChucNang) throws SQLException {
    String sql = String.format(
        "SELECT pq.ma_chuc_nang FROM lbr.TaiKhoan tk " +
        "JOIN lbr.PhanQuyen pq ON tk.ma_phan_quyen = pq.ma_phan_quyen " +
        "WHERE tk.ma_tai_khoan = '%s' AND pq.ma_chuc_nang = '%s'",
        maTaiKhoan, maChucNang
    );
    
    ResultSet rs = mySQL.executeQuery(sql);
    try {
        return rs.next(); // Trả về true nếu có kết quả
    } finally {
        if (rs != null) {
            rs.close();
        }
    }
}

public boolean xoaPhanQuyen(String maPhanQuyen) throws SQLException {
    String sql = String.format(
        "DELETE FROM lbr.PhanQuyen WHERE ma_phan_quyen = '%s'",
        maPhanQuyen
    );
    
    return mySQL.executeUpdate(sql) > 0;
}

public boolean kiemTraPhanQuyenDangDuocSuDung(String maPhanQuyen) throws SQLException {
    String sql = String.format(
        "SELECT COUNT(*) as count FROM lbr.TaiKhoan WHERE ma_phan_quyen = '%s'",
        maPhanQuyen
    );
    
    ResultSet rs = mySQL.executeQuery(sql);
    try {
        if (rs.next()) {
            return rs.getInt("count") > 0;
        }
    } finally {
        if (rs != null) {
            rs.close();
        }
    }
    return false;
}

}
