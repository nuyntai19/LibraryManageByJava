package DAO;

import DTO.TheThanhVienDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TheThanhVienDAO {
    private mySQLConnect mySQL;
    
    public TheThanhVienDAO() {
        mySQL = new mySQLConnect();
    }
    
    public TheThanhVienDTO getByMaDocGia(String maDocGia) {
        TheThanhVienDTO the = null;
        
        try {
            if (!mySQL.isConnected()) {
                throw new SQLException("Không có kết nối database!");
            }
            
            String sql = "SELECT * FROM TheThanhVien WHERE ma_doc_gia = '" + maDocGia + "'";
            System.out.println("Executing SQL: " + sql);
            
            ResultSet rs = mySQL.executeQuery(sql);
            if (rs == null) {
                throw new SQLException("Không thể lấy dữ liệu từ database!");
            }
            
            if (rs.next()) {
                the = new TheThanhVienDTO(
                    rs.getString("ma_the"),
                    rs.getString("ma_doc_gia"),
                    rs.getDate("ngay_cap"),
                    rs.getDate("ngay_het_han"),
                    rs.getBoolean("trang_thai")
                );
            }
            
            rs.close();
            
        } catch (SQLException ex) {
            System.out.println("Lỗi SQL: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi đọc thông tin thẻ: " + ex.getMessage());
        }
        
        return the;
    }
    
    public boolean add(TheThanhVienDTO the) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sql = "INSERT INTO TheThanhVien(ma_the, ma_doc_gia, ngay_cap, ngay_het_han, trang_thai) VALUES(";
            sql += "'" + the.getMaThe() + "',";
            sql += "'" + the.getMaDocGia() + "',";
            sql += the.getNgayCap() != null ? "'" + sdf.format(the.getNgayCap()) + "'" : "NULL";
            sql += ",";
            sql += the.getNgayHetHan() != null ? "'" + sdf.format(the.getNgayHetHan()) + "'" : "NULL";
            sql += ",";
            sql += the.isTrangThai() + ")";
            
            System.out.println("Executing SQL: " + sql);
            return mySQL.executeUpdate(sql) > 0;
            
        } catch (Exception ex) {
            System.out.println("Lỗi thêm thẻ: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm thẻ thành viên: " + ex.getMessage());
            return false;
        }
    }
    
    public boolean update(TheThanhVienDTO the) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String sql = "UPDATE TheThanhVien SET ";
            sql += "ngay_cap = " + (the.getNgayCap() != null ? "'" + sdf.format(the.getNgayCap()) + "'" : "NULL") + ",";
            sql += "ngay_het_han = " + (the.getNgayHetHan() != null ? "'" + sdf.format(the.getNgayHetHan()) + "'" : "NULL") + ",";
            sql += "trang_thai = " + the.isTrangThai();
            sql += " WHERE ma_the = '" + the.getMaThe() + "'";
            
            System.out.println("Executing SQL: " + sql);
            return mySQL.executeUpdate(sql) > 0;
            
        } catch (Exception ex) {
            System.out.println("Lỗi cập nhật thẻ: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật thẻ thành viên: " + ex.getMessage());
            return false;
        }
    }
    
    public boolean delete(String maThe) {
        try {
            String sql = "UPDATE TheThanhVien SET trang_thai = false WHERE ma_the = '" + maThe + "'";
            System.out.println("Executing SQL: " + sql);
            return mySQL.executeUpdate(sql) > 0;
        } catch (Exception ex) {
            System.out.println("Lỗi xóa thẻ: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa thẻ thành viên: " + ex.getMessage());
            return false;
        }
    }
    
    public boolean restore(String maThe) {
        try {
            String sql = "UPDATE TheThanhVien SET trang_thai = true WHERE ma_the = '" + maThe + "'";
            System.out.println("Executing SQL: " + sql);
            return mySQL.executeUpdate(sql) > 0;
        } catch (Exception ex) {
            System.out.println("Lỗi khôi phục thẻ: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi khôi phục thẻ thành viên: " + ex.getMessage());
            return false;
        }
    }
} 