package DAO;

import DTO.NguonNhapDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;

public class NguonNhapDAO {
    private mySQLConnect mySQL;
    
    public NguonNhapDAO() {
        mySQL = new mySQLConnect();
    }
    
    // Lấy danh sách tất cả nguồn nhập
    public ArrayList<NguonNhapDTO> getList() {
        ArrayList<NguonNhapDTO> list = new ArrayList<>();
        
        try {
            // Kiểm tra kết nối
            if (!mySQL.isConnected()) {
                throw new SQLException("Không có kết nối database!");
            }
            
            String sql = "SELECT * FROM lbr.CoSoNhap";
            System.out.println("Executing SQL: " + sql);
            
            ResultSet rs = mySQL.executeQuery(sql);
            if (rs == null) {
                throw new SQLException("Không thể lấy dữ liệu từ database!");
            }
            
            while(rs.next()) {
                    NguonNhapDTO nguonNhap = new NguonNhapDTO(
                        rs.getString("ma_co_so"),
                        rs.getString("ten_co_so"),
                        rs.getInt("hinhthuc_chuyeu"),
                        rs.getString("dia_chi"),
                        rs.getString("email"),
                        rs.getDate("ngay_hoptac"),
                        rs.getString("sdt"),
                        rs.getBoolean("trangthai")
                    );
                    list.add(nguonNhap);
                }
            
            rs.close();
            
        } catch(SQLException ex) {
            System.out.println("Lỗi SQL: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi đọc dữ liệu từ database: " + ex.getMessage());
        } catch(Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi không xác định: " + ex.getMessage());
        }
        
        return list;
    }

    // Thêm nguồn nhập mới
    public boolean add(NguonNhapDTO nguonNhap) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            String sql = "INSERT INTO lbr.CoSoNhap(ma_co_so, ten_co_so, hinhthuc_chuyeu, dia_chi, email, ngay_hoptac, sdt, trangthai) VALUES(";
            sql += "'" + nguonNhap.getMaCoSo() + "',";
            sql += "'" + nguonNhap.getTenCoSo() + "',";
            sql += nguonNhap.getHinhThucChuYeu() + ",";
            sql += (nguonNhap.getDiaChi() != null ? "'" + nguonNhap.getDiaChi() + "'" : "NULL") + ",";
            sql += (nguonNhap.getEmail() != null ? "'" + nguonNhap.getEmail() + "'" : "NULL") + ",";
            sql += "'" + sdf.format(nguonNhap.getNgayHopTac()) + "',";
            sql += (nguonNhap.getSdt() != null ? "'" + nguonNhap.getSdt() + "'" : "NULL") + ",";
            sql += nguonNhap.isTrangThai() + ")";
            
            System.out.println("Executing SQL: " + sql);
            return mySQL.executeUpdate(sql) > 0;
            
        } catch(Exception ex) {
            System.out.println("Lỗi thêm nguồn nhập: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm nguồn nhập: " + ex.getMessage());
            return false;
        }
    }
    
    // Cập nhật thông tin nguồn nhập
    public boolean update(NguonNhapDTO nguonNhap) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            String sql = "UPDATE lbr.CoSoNhap SET ";
            sql += "ten_co_so = '" + nguonNhap.getTenCoSo() + "',";
            sql += "hinhthuc_chuyeu = " + nguonNhap.getHinhThucChuYeu() + ",";
            sql += "dia_chi = " + (nguonNhap.getDiaChi() != null ? "'" + nguonNhap.getDiaChi() + "'" : "NULL") + ",";
            sql += "email = " + (nguonNhap.getEmail() != null ? "'" + nguonNhap.getEmail() + "'" : "NULL") + ",";
            sql += "ngay_hoptac = '" + sdf.format(nguonNhap.getNgayHopTac()) + "',";
            sql += "sdt = " + (nguonNhap.getSdt() != null ? "'" + nguonNhap.getSdt() + "'" : "NULL") + ",";
            sql += "trangthai = " + nguonNhap.isTrangThai();
            sql += " WHERE ma_co_so = '" + nguonNhap.getMaCoSo() + "'";
            
            System.out.println("Executing SQL: " + sql);
            return mySQL.executeUpdate(sql) > 0;
            
        } catch(Exception ex) {
            System.out.println("Lỗi cập nhật nguồn nhập: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật nguồn nhập: " + ex.getMessage());
            return false;
        }
    }
    
    // Xóa nguồn nhập (cập nhật trạng thái = false)
    public boolean delete(String maCoSo) {
        try {
            // Kiểm tra xem nguồn nhập có tồn tại không
            String checkSql = "SELECT ma_co_so FROM lbr.CoSoNhap WHERE ma_co_so = '" + maCoSo + "'";
            ResultSet rs = mySQL.executeQuery(checkSql);
            
            if (rs != null && rs.next()) {
                // Nếu tồn tại thì cập nhật trạng thái
                String sql = "UPDATE lbr.CoSoNhap SET trangthai = false " +
                           "WHERE ma_co_so = '" + maCoSo + "'";
                System.out.println("Executing SQL: " + sql);
                int result = mySQL.executeUpdate(sql);
                System.out.println("Update result: " + result);
                rs.close();
                return result > 0;
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy nguồn nhập có mã: " + maCoSo);
                if (rs != null) rs.close();
                return false;
            }
        } catch(Exception ex) {
            System.out.println("Lỗi xóa nguồn nhập: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa nguồn nhập: " + ex.getMessage());
            return false;
        }
    }
    
    // Khôi phục nguồn nhập đã xóa
    public boolean restore(String maCoSo) {
        try {
            String sql = "UPDATE lbr.CoSoNhap SET trangthai = true " +
                        "WHERE ma_co_so = '" + maCoSo + "'";
            System.out.println("Executing SQL: " + sql);
            return mySQL.executeUpdate(sql) > 0;
        } catch(Exception ex) {
            System.out.println("Lỗi khôi phục nguồn nhập: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi khôi phục nguồn nhập: " + ex.getMessage());
            return false;
        }
    }
    
    // Tìm kiếm nguồn nhập
    public ArrayList<NguonNhapDTO> search(String keyword, String selectedOption) {
        ArrayList<NguonNhapDTO> list = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM lbr.CoSoNhap WHERE ";
            
            switch(selectedOption) {
                case "Mã cơ sở":
                    sql += "ma_co_so LIKE '%" + keyword + "%'";
                    break;
                case "Tên cơ sở":
                    sql += "ten_co_so LIKE '%" + keyword + "%'";
                    break;
                case "Địa chỉ":
                    sql += "dia_chi LIKE '%" + keyword + "%'";
                    break;
                case "Email":
                    sql += "email LIKE '%" + keyword + "%'";
                    break;
                case "Số điện thoại":
                    sql += "sdt LIKE '%" + keyword + "%'";
                    break;
                default: // Tất cả
                    sql += "ma_co_so LIKE '%" + keyword + "%' OR " +
                           "ten_co_so LIKE '%" + keyword + "%' OR " +
                           "dia_chi LIKE '%" + keyword + "%' OR " +
                           "email LIKE '%" + keyword + "%' OR " +
                           "sdt LIKE '%" + keyword + "%'";
            }
                    
            System.out.println("Executing SQL: " + sql);
            ResultSet rs = mySQL.executeQuery(sql);
            
            if (rs == null) {
                throw new SQLException("Không thể lấy dữ liệu từ database!");
            }
            
            while(rs.next()) {
                    NguonNhapDTO nguonNhap = new NguonNhapDTO(
                        rs.getString("ma_co_so"),
                        rs.getString("ten_co_so"),
                        rs.getInt("hinhthuc_chuyeu"),
                        rs.getString("dia_chi"),
                        rs.getString("email"),
                        rs.getDate("ngay_hoptac"),
                        rs.getString("sdt"),
                        rs.getBoolean("trangthai")
                    );
                    list.add(nguonNhap);
                }
            
            rs.close();
            
        } catch(SQLException ex) {
            System.out.println("Lỗi SQL: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm dữ liệu: " + ex.getMessage());
        } catch(Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi không xác định khi tìm kiếm: " + ex.getMessage());
        }
        
        return list;
    }
} 