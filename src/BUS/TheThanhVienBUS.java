package BUS;

import DAO.TheThanhVienDAO;
import DTO.TheThanhVienDTO;
import java.util.ArrayList;
import java.util.Date;

/**
 * Lớp TheThanhVienBUS - Business Logic Layer
 * Xử lý logic nghiệp vụ liên quan đến thẻ thành viên
 * Kiểm tra tính hợp lệ của dữ liệu trước khi thực hiện các thao tác với cơ sở dữ liệu
 */
public class TheThanhVienBUS {
    private TheThanhVienDAO theThanhVienDAO;
    
    /**
     * Khởi tạo đối tượng TheThanhVienBUS
     */
    public TheThanhVienBUS() {
        theThanhVienDAO = new TheThanhVienDAO();
    }
    
    /**
     * Lấy thông tin thẻ thành viên theo mã độc giả
     * @param maDocGia mã độc giả
     * @return đối tượng TheThanhVienDTO hoặc null nếu không tìm thấy
     * @throws Exception nếu có lỗi xảy ra
     */
    public TheThanhVienDTO layTheThanhVienTheoMaDocGia(String maDocGia) throws Exception {
        return theThanhVienDAO.getByMaDocGia(maDocGia);
    }
    
    /**
     * Thêm thẻ thành viên mới
     * @param theThanhVien đối tượng TheThanhVienDTO chứa thông tin thẻ cần thêm
     * @return true nếu thêm thành công, false nếu thất bại
     * @throws Exception nếu có lỗi xảy ra
     */
    public boolean themTheThanhVien(TheThanhVienDTO theThanhVien) throws Exception {
        // Kiểm tra tính hợp lệ của dữ liệu
        if (!kiemTraThongTinTheThanhVien(theThanhVien)) {
            return false;
        }
        
        // Kiểm tra xem thẻ đã tồn tại chưa
        TheThanhVienDTO theHienTai = theThanhVienDAO.getByMaDocGia(theThanhVien.getMaDocGia());
        if (theHienTai != null) {
            throw new Exception("Độc giả này đã có thẻ thành viên!");
        }
        
        // Thêm thẻ thành viên vào cơ sở dữ liệu
        return theThanhVienDAO.add(theThanhVien);
    }
    
    /**
     * Cập nhật thông tin thẻ thành viên
     * @param theThanhVien đối tượng TheThanhVienDTO chứa thông tin thẻ cần cập nhật
     * @return true nếu cập nhật thành công, false nếu thất bại
     * @throws Exception nếu có lỗi xảy ra
     */
    public boolean capNhatTheThanhVien(TheThanhVienDTO theThanhVien) throws Exception {
        // Kiểm tra tính hợp lệ của dữ liệu
        if (!kiemTraThongTinTheThanhVien(theThanhVien)) {
            return false;
        }
        
        // Kiểm tra xem thẻ có tồn tại không
        TheThanhVienDTO theHienTai = theThanhVienDAO.getByMaDocGia(theThanhVien.getMaDocGia());
        if (theHienTai == null) {
            throw new Exception("Không tìm thấy thẻ thành viên cho độc giả này!");
        }
        
        // Cập nhật thông tin thẻ thành viên
        return theThanhVienDAO.update(theThanhVien);
    }
    
    /**
     * Xóa thẻ thành viên (cập nhật trạng thái = false)
     * @param maThe mã thẻ cần xóa
     * @return true nếu xóa thành công, false nếu thất bại
     * @throws Exception nếu có lỗi xảy ra
     */
    public boolean xoaTheThanhVien(String maThe) throws Exception {
        // Kiểm tra xem thẻ có tồn tại không
        TheThanhVienDTO theHienTai = theThanhVienDAO.getByMaDocGia(maThe);
        if (theHienTai == null) {
            throw new Exception("Không tìm thấy thẻ thành viên có mã: " + maThe);
        }
        
        // Xóa thẻ thành viên
        return theThanhVienDAO.delete(maThe);
    }
    
    /**
     * Khôi phục thẻ thành viên đã xóa (cập nhật trạng thái = true)
     * @param maThe mã thẻ cần khôi phục
     * @return true nếu khôi phục thành công, false nếu thất bại
     * @throws Exception nếu có lỗi xảy ra
     */
    public boolean khoiPhucTheThanhVien(String maThe) throws Exception {
        // Kiểm tra xem thẻ có tồn tại không
        TheThanhVienDTO theHienTai = theThanhVienDAO.getByMaDocGia(maThe);
        if (theHienTai == null) {
            throw new Exception("Không tìm thấy thẻ thành viên có mã: " + maThe);
        }
        
        // Khôi phục thẻ thành viên
        return theThanhVienDAO.restore(maThe);
    }
    
    /**
     * Kiểm tra tính hợp lệ của thông tin thẻ thành viên
     * @param theThanhVien đối tượng TheThanhVienDTO cần kiểm tra
     * @return true nếu thông tin hợp lệ, false nếu không hợp lệ
     */
    private boolean kiemTraThongTinTheThanhVien(TheThanhVienDTO theThanhVien) {
        // Kiểm tra mã thẻ
        if (theThanhVien.getMaThe() == null || theThanhVien.getMaThe().trim().isEmpty()) {
            return false;
        }
        
        // Kiểm tra mã độc giả
        if (theThanhVien.getMaDocGia() == null || theThanhVien.getMaDocGia().trim().isEmpty()) {
            return false;
        }
        
        // Kiểm tra ngày cấp
        if (theThanhVien.getNgayCap() == null) {
            return false;
        }
        
        // Kiểm tra ngày hết hạn
        if (theThanhVien.getNgayHetHan() == null) {
            return false;
        }
        
        // Kiểm tra ngày hết hạn phải sau ngày cấp
        if (theThanhVien.getNgayHetHan().before(theThanhVien.getNgayCap())) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Tạo mã thẻ mới
     * @return mã thẻ mới
     */
    public String taoMaTheMoi() {
        // Logic để tạo mã thẻ mới, có thể dựa trên số lượng thẻ hiện có
        // Ví dụ: "TT001", "TT002", ...
        return "TT" + System.currentTimeMillis();
    }
    
    /**
     * Kiểm tra thẻ có còn hiệu lực không
     * @param theThanhVien đối tượng TheThanhVienDTO cần kiểm tra
     * @return true nếu thẻ còn hiệu lực, false nếu thẻ hết hạn
     */
    public boolean kiemTraTheConHieuLuc(TheThanhVienDTO theThanhVien) {
        if (theThanhVien == null || !theThanhVien.isTrangThai()) {
            return false;
        }
        
        Date ngayHienTai = new Date();
        return !ngayHienTai.after(theThanhVien.getNgayHetHan());
    }
}
