package BUS;

import DAO.DocGiaDAO;
import DTO.DocGiaDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class DocGiaBUS {
    private final DocGiaDAO docGiaDAO;

    public DocGiaBUS() {
        docGiaDAO = new DocGiaDAO();
    }

    public ArrayList<DocGiaDTO> layDanhSachDocGia() throws SQLException {
        return docGiaDAO.getList();
    }

    public boolean themDocGia(DocGiaDTO dg) throws Exception {
        // Kiểm tra dữ liệu đầu vào
        if (dg == null) {
            throw new Exception("Dữ liệu độc giả không hợp lệ!");
        }
        
        // Kiểm tra thông tin bắt buộc
        if (dg.getTenDG() == null || dg.getTenDG().trim().isEmpty()) {
            throw new Exception("Tên độc giả không được để trống!");
        }
        
        if (dg.getNgaySinh() == null) {
            throw new Exception("Ngày sinh không được để trống!");
        }
        
        // Kiểm tra xem độc giả đã tồn tại chưa
        if (kiemTraDocGiaTonTai(dg.getTenDG(), dg.getSoDienThoai(), dg.getDiaChi(), dg.getNgaySinh())) {
            throw new Exception("Thông tin độc giả đã tồn tại trong hệ thống!");
        }
        
        // Tạo mã độc giả mới nếu chưa có
        if (dg.getMaDG() == null || dg.getMaDG().trim().isEmpty()) {
            dg.setMaDG(taoMaDocGiaMoi());
        }
        
        // Tạo mã thẻ thành viên từ số điện thoại
        if (dg.getSoDienThoai() != null && !dg.getSoDienThoai().trim().isEmpty()) {
            dg.setMaThe(taoMaTheTuSoDienThoai(dg.getSoDienThoai()));
        }
        
        // Đặt trạng thái mặc định là hoạt động
        dg.setTrangThai(true);
        
        // Gọi phương thức thêm độc giả từ DAO
        return docGiaDAO.add(dg);
    }

    public boolean capNhatDocGia(DocGiaDTO dg) throws Exception {
        // Kiểm tra dữ liệu đầu vào
        if (dg == null) {
            throw new Exception("Dữ liệu độc giả không hợp lệ!");
        }
        
        // Kiểm tra mã độc giả
        if (dg.getMaDG() == null || dg.getMaDG().trim().isEmpty()) {
            throw new Exception("Mã độc giả không được để trống!");
        }
        
        // Kiểm tra thông tin bắt buộc
        if (dg.getTenDG() == null || dg.getTenDG().trim().isEmpty()) {
            throw new Exception("Tên độc giả không được để trống!");
        }
        
        if (dg.getNgaySinh() == null) {
            throw new Exception("Ngày sinh không được để trống!");
        }
        
        // Kiểm tra xem độc giả có tồn tại không
        ArrayList<DocGiaDTO> danhSachDocGia = layDanhSachDocGia();
        boolean docGiaTonTai = false;
        
        for (DocGiaDTO existingDocGia : danhSachDocGia) {
            if (existingDocGia.getMaDG().equals(dg.getMaDG())) {
                docGiaTonTai = true;
                break;
            }
        }
        
        if (!docGiaTonTai) {
            throw new Exception("Không tìm thấy độc giả với mã: " + dg.getMaDG());
        }
        
        // Gọi phương thức cập nhật độc giả từ DAO
        return docGiaDAO.update(dg);
    }

    public boolean xoaDocGia(String maDG) throws Exception {
        // Kiểm tra dữ liệu đầu vào
        if (maDG == null || maDG.trim().isEmpty()) {
            throw new Exception("Mã độc giả không được để trống!");
        }
        
        // Kiểm tra xem độc giả có tồn tại không
        ArrayList<DocGiaDTO> danhSachDocGia = layDanhSachDocGia();
        boolean docGiaTonTai = false;
        
        for (DocGiaDTO dg : danhSachDocGia) {
            if (dg.getMaDG().equals(maDG)) {
                docGiaTonTai = true;
                break;
            }
        }
        
        if (!docGiaTonTai) {
            throw new Exception("Không tìm thấy độc giả với mã: " + maDG);
        }
        
        // Gọi phương thức xóa độc giả từ DAO
        return docGiaDAO.delete(maDG);
    }

    public boolean khoiPhucDocGia(String maDG) throws Exception {
        // Kiểm tra dữ liệu đầu vào
        if (maDG == null || maDG.trim().isEmpty()) {
            throw new Exception("Mã độc giả không được để trống!");
        }
        
        // Kiểm tra xem độc giả có tồn tại không
        ArrayList<DocGiaDTO> danhSachDocGia = layDanhSachDocGia();
        boolean docGiaTonTai = false;
        
        for (DocGiaDTO dg : danhSachDocGia) {
            if (dg.getMaDG().equals(maDG)) {
                docGiaTonTai = true;
                break;
            }
        }
        
        if (!docGiaTonTai) {
            throw new Exception("Không tìm thấy độc giả với mã: " + maDG);
        }
        
        // Gọi phương thức khôi phục độc giả từ DAO
        return docGiaDAO.restore(maDG);
    }

    public ArrayList<DocGiaDTO> timKiemDocGia(String keyword, String selectedOption) throws Exception {
        // Kiểm tra dữ liệu đầu vào
        if (keyword == null) {
            throw new Exception("Từ khóa tìm kiếm không được để trống!");
        }
        
        if (selectedOption == null || selectedOption.trim().isEmpty()) {
            throw new Exception("Tiêu chí tìm kiếm không được để trống!");
        }
        
        // Gọi phương thức tìm kiếm độc giả từ DAO
        return docGiaDAO.search(keyword, selectedOption);
    }

    public boolean kiemTraDocGiaTonTai(String tenDG, String soDienThoai, String diaChi, Date ngaySinh) {
        try {
            ArrayList<DocGiaDTO> danhSachDocGia = layDanhSachDocGia();
            
            // Chuẩn hóa dữ liệu đầu vào
            String tenDGNormalized = chuanHoaChuoi(tenDG);
            String soDienThoaiNormalized = chuanHoaChuoi(soDienThoai);
            String diaChiNormalized = chuanHoaChuoi(diaChi);
            
            for (DocGiaDTO dg : danhSachDocGia) {
                // Chuẩn hóa dữ liệu từ database
                String tenDBNormalized = chuanHoaChuoi(dg.getTenDG());
                String sdtDBNormalized = chuanHoaChuoi(dg.getSoDienThoai());
                String diaChiDBNormalized = chuanHoaChuoi(dg.getDiaChi());
                
                // So sánh từng trường thông tin
                boolean tenTrung = tenDGNormalized != null && 
                                 tenDGNormalized.equals(tenDBNormalized);
                
                boolean sdtTrung = (soDienThoaiNormalized == null && sdtDBNormalized == null) || 
                                 (soDienThoaiNormalized != null && 
                                  soDienThoaiNormalized.equals(sdtDBNormalized));
                
                boolean diaChiTrung = (diaChiNormalized == null && diaChiDBNormalized == null) || 
                                    (diaChiNormalized != null && 
                                     diaChiNormalized.equals(diaChiDBNormalized));
                
                boolean ngaySinhTrung = ngaySinh != null && 
                                       dg.getNgaySinh() != null && 
                                       ngaySinh.equals(dg.getNgaySinh());
                
                // Chỉ trả về true khi tất cả đều trùng
                if (tenTrung && sdtTrung && diaChiTrung && ngaySinhTrung) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi kiểm tra độc giả tồn tại: " + e.getMessage());
        }
        
        return false;
    }

    // Phương thức chuẩn hóa chuỗi
    private String chuanHoaChuoi(String input) {
        if (input == null) return null;
        
        // 1. Chuyển về chữ thường
        String result = input.toLowerCase();
        
        // 2. Loại bỏ khoảng trắng dư thừa
        result = result.trim().replaceAll("\\s+", " ");
        
        // 3. Loại bỏ dấu câu và ký tự đặc biệt (giữ lại dấu tiếng Việt)
        result = result.replaceAll("[^a-z0-9\\sàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]", "");
        
        return result;
    }

    public String taoMaDocGiaMoi() {
        try {
            ArrayList<DocGiaDTO> danhSachDocGia = layDanhSachDocGia();
            return "DG" + (danhSachDocGia.size() + 1);
        } catch (SQLException e) {
            System.out.println("Lỗi khi tạo mã độc giả mới: " + e.getMessage());
            return "DG" + System.currentTimeMillis();
        }
    }

    // Phương thức tạo mã thẻ thành viên từ số điện thoại
    public String taoMaTheTuSoDienThoai(String soDienThoai) {
        if (soDienThoai == null || soDienThoai.trim().isEmpty()) {
            return null;
        }
        return soDienThoai.trim();
    }

    /**
     * Tính tuổi của độc giả
     * @param ngaySinh Ngày sinh của độc giả
     * @return int Tuổi của độc giả
     */
    public int tinhTuoi(Date ngaySinh) {
        if (ngaySinh == null) return 0;
        return java.time.LocalDate.now().getYear() - 
               (ngaySinh.getYear() + 1900);
    }
    
    public String kiemTraThongTinDocGia(DocGiaDTO dg) {
        if (dg == null) {
            return "Dữ liệu độc giả không hợp lệ!";
        }
        
        if (dg.getTenDG() == null || dg.getTenDG().trim().isEmpty()) {
            return "Tên độc giả không được để trống!";
        }
        
        if (dg.getNgaySinh() == null) {
            return "Ngày sinh không được để trống!";
        }
        
        // Kiểm tra tuổi
        int tuoi = tinhTuoi(dg.getNgaySinh());
        if (tuoi < 6 || tuoi > 100) {
            return "Tuổi độc giả không hợp lệ (phải từ 6-100 tuổi)!";
        }
        
        // Kiểm tra số điện thoại nếu có
        if (dg.getSoDienThoai() != null && !dg.getSoDienThoai().trim().isEmpty()) {
            if (!dg.getSoDienThoai().matches("\\d{10,11}")) {
                return "Số điện thoại không hợp lệ (phải có 10-11 chữ số)!";
            }
        }
        
        // Kiểm tra thông tin thẻ thành viên nếu có
        if (dg.getMaThe() != null && !dg.getMaThe().trim().isEmpty()) {
            if (dg.getNgayCap() == null) {
                return "Ngày cấp thẻ không được để trống!";
            }
            
            if (dg.getNgayHetHan() == null) {
                return "Ngày hết hạn thẻ không được để trống!";
            }
            
            // Kiểm tra ngày hết hạn phải sau ngày cấp
            if (dg.getNgayHetHan().before(dg.getNgayCap())) {
                return "Ngày hết hạn thẻ phải sau ngày cấp!";
            }
        }
        
        return null;
    }
} 