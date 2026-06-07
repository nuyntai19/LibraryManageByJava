package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

import DTO.DocGiaDTO;
import DTO.TaiKhoanDTO;
import DTO.TheThanhVienDTO;

import java.text.SimpleDateFormat;


public class DocGiaDAO {
    private mySQLConnect mySQL;
    private TheThanhVienDAO theThanhVienDAO;
    
    public DocGiaDAO() {
        mySQL = new mySQLConnect();
        theThanhVienDAO = new TheThanhVienDAO();
    }
    

    public ArrayList<DocGiaDTO> getList() throws SQLException {
        ArrayList<DocGiaDTO> list = new ArrayList<>();
        
        try {
            // Kiểm tra kết nối
            if (!mySQL.isConnected()) {
                throw new SQLException("Không có kết nối database!");
            }
            
            String sql = "SELECT dg.ma_doc_gia, dg.ten_doc_gia, dg.gioi_tinh, dg.so_dien_thoai, " +
                        "dg.ngay_sinh, dg.dia_chi, " +
                        "ttv.ma_the, ttv.ngay_cap, ttv.ngay_het_han, COALESCE(ttv.trang_thai, true) as trang_thai " +
                        "FROM DocGia dg " +
                        "LEFT JOIN TheThanhVien ttv ON dg.ma_doc_gia = ttv.ma_doc_gia";
            
            ResultSet rs = mySQL.executeQuery(sql);
            if (rs == null) {
                throw new SQLException("Không thể lấy dữ liệu từ database!");
            }
            
            while(rs.next()) {
                DocGiaDTO dg = new DocGiaDTO(
                    rs.getString("ma_doc_gia"),
                    rs.getString("ten_doc_gia"),
                    rs.getString("gioi_tinh"),
                    rs.getString("so_dien_thoai"),
                    rs.getDate("ngay_sinh"),
                    rs.getString("dia_chi"),
                    rs.getString("ma_the"),
                    rs.getDate("ngay_cap"),
                    rs.getDate("ngay_het_han"),
                    rs.getBoolean("trang_thai")
                );
                list.add(dg);
            }
            
            rs.close();
            
        } catch(SQLException ex) {
            System.out.println("Lỗi SQL: " + ex.getMessage());
            throw ex;
        } catch(Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
            throw new SQLException("Lỗi không xác định: " + ex.getMessage());
        }
        
        return list;
    }
    
//    public boolean add(DocGiaDTO dg) throws SQLException {
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            
//            // Thêm độc giả
//            String sql = "INSERT INTO DocGia(ma_doc_gia, ten_doc_gia, gioi_tinh, so_dien_thoai, ngay_sinh, dia_chi) VALUES(";
//            sql += "'" + dg.getMaDG() + "',";
//            sql += "'" + dg.getTenDG() + "',";
//            sql += "'" + dg.getGioiTinh() + "',";
//            sql += (dg.getSoDienThoai() != null ? "'" + dg.getSoDienThoai() + "'" : "NULL") + ",";
//            sql += "'" + sdf.format(dg.getNgaySinh()) + "',";
//            sql += (dg.getDiaChi() != null ? "'" + dg.getDiaChi() + "'" : "NULL") + ")";
//            
//            if (mySQL.executeUpdate(sql) <= 0) {
//                return false;
//            }
//            
//            // Thêm thẻ thành viên nếu có
//            if (dg.getMaThe() != null) {
//                TheThanhVienDTO the = new TheThanhVienDTO(
//                    dg.getMaThe(),
//                    dg.getMaDG(),
//                    dg.getNgayCap(),
//                    dg.getNgayHetHan(),
//                    true
//                );
//                return theThanhVienDAO.add(the);
//            }
//            
//        TaiKhoanDAO tkDAO = new TaiKhoanDAO();            
//        String maTaiKhoan = tkDAO.taoMaTaiKhoanNgauNhien();
//        String maThe = dg.getMaThe();
//        String tenDangNhap = maThe;
//        String matKhau = "123456789";
//
//        String sql1 = "INSERT INTO lbr.TaiKhoan(ma_tai_khoan, ten_dang_nhap, mat_khau, ma_the) VALUES('";
//        sql1 += "'" + maTaiKhoan + "',";
//        sql1 += "'" + tenDangNhap + "',";
//        sql1 += "'" + matKhau + "',";
//        sql1 += "'" + maThe + "')";
//        
//        int result = mySQL.executeUpdate(sql1);
//        if (result <= 0) {
//            System.out.println("❌ Thêm tài khoản thất bại");
//            return false;
//        }
//
//        System.out.println("✅ Độc giả, thẻ, tài khoản: thêm thành công.");
//        return true;
//
//    } catch (Exception ex) {
//        System.out.println("Lỗi khi thêm độc giả: " + ex.getMessage());
//        throw new SQLException("❌ Lỗi khi thêm độc giả: " + ex.getMessage());
//    }
//}
    
    public boolean add(DocGiaDTO dg) throws SQLException {
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Thêm độc giả
        String sql = "INSERT INTO DocGia(ma_doc_gia, ten_doc_gia, gioi_tinh, so_dien_thoai, ngay_sinh, dia_chi) VALUES(";
        sql += "'" + dg.getMaDG() + "',";
        sql += "'" + dg.getTenDG() + "',";
        sql += "'" + dg.getGioiTinh() + "',";
        sql += (dg.getSoDienThoai() != null ? "'" + dg.getSoDienThoai() + "'" : "NULL") + ",";
        sql += "'" + sdf.format(dg.getNgaySinh()) + "',";
        sql += (dg.getDiaChi() != null ? "'" + dg.getDiaChi() + "'" : "NULL") + ")";

        if (mySQL.executeUpdate(sql) <= 0) {
            return false;
        }

        // Thêm thẻ thành viên nếu có
        boolean theOK = true;
        if (dg.getMaThe() != null) {
            TheThanhVienDTO the = new TheThanhVienDTO(
                dg.getMaThe(),
                dg.getMaDG(),
                dg.getNgayCap(),
                dg.getNgayHetHan(),
                true
            );
            theOK = theThanhVienDAO.add(the);
        }

        if (!theOK) {
            System.out.println("❌ Thêm thẻ thành viên thất bại");
            return false;
        }

        // Tạo tài khoản cho độc giả
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();            
        String maTaiKhoan = tkDAO.taoMaTaiKhoanNgauNhien();
        String maThe = dg.getMaThe();
        String tenDangNhap = maThe;
        String matKhau = "123456789";

        String sql1 = "INSERT INTO lbr.TaiKhoan(ma_tai_khoan, ten_dang_nhap, mat_khau, ma_phan_quyen, ma_nhan_vien, ma_the) VALUES(";
        sql1 += "'" + maTaiKhoan + "',";
        sql1 += "'" + tenDangNhap + "',";
        sql1 += "'" + matKhau + "',";
        sql1 += "NULL,"; // không phải nhân viên
        sql1 += "NULL,";
        sql1 += "'" + maThe + "')";

        int result = mySQL.executeUpdate(sql1);
        if (result <= 0) {
            System.out.println("❌ Thêm tài khoản thất bại");
            return false;
        }

        System.out.println("✅ Độc giả, thẻ, tài khoản: thêm thành công.");
        return true;

    } catch (Exception ex) {
        System.out.println("Lỗi khi thêm độc giả: " + ex.getMessage());
        throw new SQLException("❌ Lỗi khi thêm độc giả: " + ex.getMessage());
    }
}
    
    
    public boolean update(DocGiaDTO dg) throws SQLException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            boolean success = true;
            
            // Cập nhật độc giả
            String sql = "UPDATE DocGia SET ";
            sql += "ten_doc_gia = '" + dg.getTenDG() + "',";
            sql += "gioi_tinh = '" + dg.getGioiTinh() + "',";
            sql += "so_dien_thoai = " + (dg.getSoDienThoai() != null ? "'" + dg.getSoDienThoai() + "'" : "NULL") + ",";
            sql += "ngay_sinh = '" + sdf.format(dg.getNgaySinh()) + "',";
            sql += "dia_chi = " + (dg.getDiaChi() != null ? "'" + dg.getDiaChi() + "'" : "NULL");
            sql += " WHERE ma_doc_gia = '" + dg.getMaDG() + "'";
            
            int docGiaResult = mySQL.executeUpdate(sql);
            if (docGiaResult < 0) {
                return false;
            }
            
            // Cập nhật thẻ thành viên nếu có
            if (dg.getMaThe() != null && !dg.getMaThe().trim().isEmpty()) {
                // Kiểm tra xem thẻ đã tồn tại chưa
                String checkSql = "SELECT ma_the FROM TheThanhVien WHERE ma_doc_gia = '" + dg.getMaDG() + "'";
                ResultSet rs = mySQL.executeQuery(checkSql);
                
                if (rs != null && rs.next()) {
                    String oldMaThe = rs.getString("ma_the");
                    // Nếu mã thẻ thay đổi, kiểm tra xem mã thẻ mới có tồn tại không
                    if (!oldMaThe.equals(dg.getMaThe())) {
                        String checkNewCardSql = "SELECT ma_the FROM TheThanhVien WHERE ma_the = '" + dg.getMaThe() + "'";
                        ResultSet rsNewCard = mySQL.executeQuery(checkNewCardSql);
                        
                        if (rsNewCard != null && rsNewCard.next()) {
                            // Nếu mã thẻ đã tồn tại, báo lỗi
                            if (rsNewCard != null) rsNewCard.close();
                            if (rs != null) rs.close();
                            throw new SQLException("Mã thẻ " + dg.getMaThe() + " đã tồn tại trong hệ thống!");
                        }
                        if (rsNewCard != null) rsNewCard.close();
                    }
                    
                    // Cập nhật thông tin thẻ
                    sql = "UPDATE TheThanhVien SET ";
                    sql += "ma_the = '" + dg.getMaThe() + "',";
                    if (dg.getNgayCap() != null) {
                        sql += "ngay_cap = '" + sdf.format(dg.getNgayCap()) + "',";
                    }
                    if (dg.getNgayHetHan() != null) {
                        sql += "ngay_het_han = '" + sdf.format(dg.getNgayHetHan()) + "',";
                    }
                    sql += "trang_thai = " + dg.isTrangThai();
                    sql += " WHERE ma_doc_gia = '" + dg.getMaDG() + "'";
                } else {
                    // Kiểm tra xem mã thẻ mới có tồn tại không
                    String checkNewCardSql = "SELECT ma_the FROM TheThanhVien WHERE ma_the = '" + dg.getMaThe() + "'";
                    ResultSet rsNewCard = mySQL.executeQuery(checkNewCardSql);
                    
                    if (rsNewCard != null && rsNewCard.next()) {
                        // Nếu mã thẻ đã tồn tại, báo lỗi
                        if (rsNewCard != null) rsNewCard.close();
                        if (rs != null) rs.close();
                        throw new SQLException("Mã thẻ " + dg.getMaThe() + " đã tồn tại trong hệ thống!");
                    }
                    
                    // Nếu chưa có thẻ và mã thẻ mới chưa tồn tại thì thêm mới
                    sql = "INSERT INTO TheThanhVien(ma_the, ma_doc_gia, ngay_cap, ngay_het_han, trang_thai) VALUES(";
                    sql += "'" + dg.getMaThe() + "',";
                    sql += "'" + dg.getMaDG() + "',";
                    sql += (dg.getNgayCap() != null ? "'" + sdf.format(dg.getNgayCap()) + "'" : "NULL") + ",";
                    sql += (dg.getNgayHetHan() != null ? "'" + sdf.format(dg.getNgayHetHan()) + "'" : "NULL") + ",";
                    sql += dg.isTrangThai() + ")";
                    
                    if (rsNewCard != null) rsNewCard.close();
                }
                
                if (rs != null) rs.close();
                
                int theThanhVienResult = mySQL.executeUpdate(sql);
                if (theThanhVienResult < 0) {
                    success = false;
                }
            }
            
            return success;
            
        } catch(Exception ex) {
            System.out.println("Lỗi cập nhật độc giả: " + ex.getMessage());
            throw new SQLException("Lỗi khi cập nhật độc giả: " + ex.getMessage());
        }
    }
    
    public boolean delete(String maDG) throws SQLException {
        try {
            // Kiểm tra xem độc giả có thẻ thành viên không
            String checkSql = "SELECT ma_the FROM TheThanhVien WHERE ma_doc_gia = '" + maDG + "'";
            ResultSet rs = mySQL.executeQuery(checkSql);
            
            if (rs != null && rs.next()) {
                // Nếu có thẻ thành viên thì cập nhật trạng thái
                String sql = "UPDATE TheThanhVien SET trang_thai = false " +
                           "WHERE ma_doc_gia = '" + maDG + "'";
                boolean result = mySQL.executeUpdate(sql) > 0;
                rs.close();
                return result;
            } else {
                // Nếu không có thẻ thành viên thì thêm mới với trạng thái false
                String sql = "INSERT INTO TheThanhVien(ma_the, ma_doc_gia, trang_thai) " +
                           "VALUES('THE" + System.currentTimeMillis() + "', '" + maDG + "', false)";
                boolean result = mySQL.executeUpdate(sql) > 0;
                if (rs != null) rs.close();
                return result;
            }
        } catch(Exception ex) {
            System.out.println("Lỗi xóa độc giả: " + ex.getMessage());
            throw new SQLException("Lỗi khi xóa độc giả: " + ex.getMessage());
        }
    }
    
    public boolean restore(String maDG) throws SQLException {
        try {
            String sql = "UPDATE TheThanhVien SET trang_thai = true " +
                        "WHERE ma_doc_gia = '" + maDG + "'";
            return mySQL.executeUpdate(sql) > 0;
        } catch(Exception ex) {
            System.out.println("Lỗi khôi phục độc giả: " + ex.getMessage());
            throw new SQLException("Lỗi khi khôi phục độc giả: " + ex.getMessage());
        }
    }
    
    public ArrayList<DocGiaDTO> search(String keyword, String selectedOption) throws SQLException {
        ArrayList<DocGiaDTO> list = new ArrayList<>();
        
        try {
            String sql = "SELECT dg.ma_doc_gia, dg.ten_doc_gia, dg.gioi_tinh, dg.so_dien_thoai, " +
                        "dg.ngay_sinh, dg.dia_chi, " +
                        "ttv.ma_the, ttv.ngay_cap, ttv.ngay_het_han, ttv.trang_thai " +
                        "FROM DocGia dg " +
                        "LEFT JOIN TheThanhVien ttv ON dg.ma_doc_gia = ttv.ma_doc_gia " +
                        "WHERE ";
            
            // Thêm điều kiện tìm kiếm dựa trên option được chọn
            switch(selectedOption) {
                case "Mã độc giả":
                    sql += "dg.ma_doc_gia LIKE '%" + keyword + "%'";
                    break;
                case "Tên độc giả":
                    sql += "dg.ten_doc_gia LIKE '%" + keyword + "%'";
                    break;
                case "Số điện thoại":
                    sql += "dg.so_dien_thoai LIKE '%" + keyword + "%'";
                    break;
                case "Địa chỉ":
                    sql += "dg.dia_chi LIKE '%" + keyword + "%'";
                    break;
                case "Mã thẻ":
                    sql += "ttv.ma_the LIKE '%" + keyword + "%'";
                    break;
                default: // Tất cả
                    sql += "dg.ma_doc_gia LIKE '%" + keyword + "%' OR " +
                           "dg.ten_doc_gia LIKE '%" + keyword + "%' OR " +
                           "dg.so_dien_thoai LIKE '%" + keyword + "%' OR " +
                           "dg.dia_chi LIKE '%" + keyword + "%' OR " +
                           "ttv.ma_the LIKE '%" + keyword + "%'";
            }
                    
            ResultSet rs = mySQL.executeQuery(sql);
            
            if (rs == null) {
                throw new SQLException("Không thể lấy dữ liệu từ database!");
            }
            
            while(rs.next()) {
                DocGiaDTO dg = new DocGiaDTO(
                    rs.getString("ma_doc_gia"),
                    rs.getString("ten_doc_gia"),
                    rs.getString("gioi_tinh"),
                    rs.getString("so_dien_thoai"),
                    rs.getDate("ngay_sinh"),
                    rs.getString("dia_chi"),
                    rs.getString("ma_the"),
                    rs.getDate("ngay_cap"),
                    rs.getDate("ngay_het_han"),
                    rs.getBoolean("trang_thai")
                );
                list.add(dg);
            }
            
            rs.close();
            
        } catch(SQLException ex) {
            System.out.println("Lỗi SQL: " + ex.getMessage());
            throw ex;
        } catch(Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
            throw new SQLException("Lỗi không xác định khi tìm kiếm: " + ex.getMessage());
        }
        
        return list;
    }
} 