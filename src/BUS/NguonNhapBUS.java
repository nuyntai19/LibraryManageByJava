package BUS;

import DAO.NguonNhapDAO;
import DTO.NguonNhapDTO;
import java.util.ArrayList;
import java.util.Date;

public class NguonNhapBUS {
    private NguonNhapDAO nguonNhapDAO;
    
    public NguonNhapBUS() {
        nguonNhapDAO = new NguonNhapDAO();
    }
    
    public ArrayList<NguonNhapDTO> layDanhSachNguonNhap() throws Exception {
        return nguonNhapDAO.getList();
    }
    
    public boolean themNguonNhap(NguonNhapDTO nguonNhap) throws Exception {
        // Kiểm tra tính hợp lệ của dữ liệu
        if (!kiemTraThongTinNguonNhap(nguonNhap)) {
            return false;
        }
        
        // Kiểm tra xem nguồn nhập đã tồn tại chưa
        ArrayList<NguonNhapDTO> danhSach = nguonNhapDAO.getList();
        for (NguonNhapDTO nguonNhapHienTai : danhSach) {
            if (nguonNhapHienTai.getTenCoSo().equals(nguonNhap.getTenCoSo()) && 
                nguonNhapHienTai.getDiaChi().equals(nguonNhap.getDiaChi()) && 
                nguonNhapHienTai.getEmail().equals(nguonNhap.getEmail()) && 
                nguonNhapHienTai.getSdt().equals(nguonNhap.getSdt())) {
                throw new Exception("Thông tin nguồn nhập đã tồn tại trong hệ thống!");
            }
        }
        
        // Tạo mã nguồn nhập mới
        nguonNhap.setMaCoSo("CS" + (danhSach.size() + 1));
        nguonNhap.setTrangThai(true);
        
        // Thêm nguồn nhập vào cơ sở dữ liệu
        return nguonNhapDAO.add(nguonNhap);
    }
    
    public boolean capNhatNguonNhap(NguonNhapDTO nguonNhap) throws Exception {
        // Kiểm tra tính hợp lệ của dữ liệu
        if (!kiemTraThongTinNguonNhap(nguonNhap)) {
            return false;
        }
        
        // Kiểm tra xem nguồn nhập có tồn tại không
        ArrayList<NguonNhapDTO> danhSach = nguonNhapDAO.getList();
        boolean tonTai = false;
        for (NguonNhapDTO nguonNhapHienTai : danhSach) {
            if (nguonNhapHienTai.getMaCoSo().equals(nguonNhap.getMaCoSo())) {
                tonTai = true;
                break;
            }
        }
        
        if (!tonTai) {
            throw new Exception("Không tìm thấy nguồn nhập có mã: " + nguonNhap.getMaCoSo());
        }
        
        // Cập nhật thông tin nguồn nhập
        return nguonNhapDAO.update(nguonNhap);
    }
    
    public boolean xoaNguonNhap(String maCoSo) throws Exception {
        // Kiểm tra xem nguồn nhập có tồn tại không
        ArrayList<NguonNhapDTO> danhSach = nguonNhapDAO.getList();
        boolean tonTai = false;
        for (NguonNhapDTO nguonNhapHienTai : danhSach) {
            if (nguonNhapHienTai.getMaCoSo().equals(maCoSo)) {
                tonTai = true;
                break;
            }
        }
        
        if (!tonTai) {
            throw new Exception("Không tìm thấy nguồn nhập có mã: " + maCoSo);
        }
        
        // Xóa nguồn nhập
        return nguonNhapDAO.delete(maCoSo);
    }
    
    public boolean khoiPhucNguonNhap(String maCoSo) throws Exception {
        // Kiểm tra xem nguồn nhập có tồn tại không
        ArrayList<NguonNhapDTO> danhSach = nguonNhapDAO.getList();
        boolean tonTai = false;
        for (NguonNhapDTO nguonNhapHienTai : danhSach) {
            if (nguonNhapHienTai.getMaCoSo().equals(maCoSo)) {
                tonTai = true;
                break;
            }
        }
        
        if (!tonTai) {
            throw new Exception("Không tìm thấy nguồn nhập có mã: " + maCoSo);
        }
        
        // Khôi phục nguồn nhập
        return nguonNhapDAO.restore(maCoSo);
    }
    
    public ArrayList<NguonNhapDTO> timKiemNguonNhap(String keyword, String selectedOption) throws Exception {
        return nguonNhapDAO.search(keyword, selectedOption);
    }
    
    private boolean kiemTraThongTinNguonNhap(NguonNhapDTO nguonNhap) {
        // Kiểm tra tên cơ sở
        if (nguonNhap.getTenCoSo() == null || nguonNhap.getTenCoSo().trim().isEmpty()) {
            return false;
        }
        
        // Kiểm tra địa chỉ
        if (nguonNhap.getDiaChi() == null || nguonNhap.getDiaChi().trim().isEmpty()) {
            return false;
        }
        
        // Kiểm tra email
        if (nguonNhap.getEmail() == null || nguonNhap.getEmail().trim().isEmpty()) {
            return false;
        }
        
        // Kiểm tra số điện thoại
        if (nguonNhap.getSdt() == null || nguonNhap.getSdt().trim().isEmpty()) {
            return false;
        }
        
        // Kiểm tra ngày hợp tác
        if (nguonNhap.getNgayHopTac() == null) {
            return false;
        }
        
        // Kiểm tra ngày hợp tác không được lớn hơn ngày hiện tại
        if (nguonNhap.getNgayHopTac().after(new Date())) {
            return false;
        }
        
        return true;
    }
}
