package BUS;

import DAO.PhieuMuonDAO;
import DTO.PhieuMuonDTO;
import DTO.SachDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PhieuMuonBUS {
    private ArrayList<PhieuMuonDTO> danhSachPhieuMuon;
    private PhieuMuonDAO phieuMuonDAO;
    SachBUS sachBUS = new SachBUS();

    public PhieuMuonBUS() {
        phieuMuonDAO = new PhieuMuonDAO();
        danhSachPhieuMuon = phieuMuonDAO.getAllPhieuMuonDTO(); // Lấy toàn bộ phiếu mượn từ CSDL
    }

    public boolean kiemTraMaTonTai(String maPhieuMuon) {
        for (PhieuMuonDTO pm : danhSachPhieuMuon) {
            if (pm.getMaPhieuMuon().equalsIgnoreCase(maPhieuMuon)) {
                return true;
            }
        }
        return false;
    }
  

//    public boolean themPhieuMuon(PhieuMuonDTO pm) {
//        if (kiemTraMaTonTai(pm.getMaPhieuMuon())) {
//            System.out.println("Mã phiếu mượn đã tồn tại!");
//            return false;
//        }
//
//        try {
//            if (phieuMuonDAO.Them(pm)) {
//                danhSachPhieuMuon.add(pm);
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
    public boolean themPhieuMuon(PhieuMuonDTO pm) {
        if (kiemTraMaTonTai(pm.getMaPhieuMuon())) {
            System.out.println("Mã phiếu mượn đã tồn tại!");
            return false;
        }
    
        SachDTO sach = sachBUS.timSachTheoMa(pm.getMaSach()); // mày phải có hàm này
            if (sach == null) {
                System.out.println("Không tìm thấy sách!");
                return false;
            }
            if (sach.getSoLuong() <= 0) {
                System.out.println("Sách đã hết!");
                return false;
            }
    // ✅ Kiểm tra xem đã có phiếu mượn nào trùng mã độc giả, mã sách và ngày mượn chưa
//    for (PhieuMuonDTO existing : danhSachPhieuMuon) {
//        if (existing.getMaDocGia().equals(pm.getMaDocGia()) &&
//            existing.getMaSach().equals(pm.getMaSach()) &&
//            existing.getNgayMuon().equals(pm.getNgayMuon())) {
//            
//            System.out.println("Độc giả đã mượn sách này vào ngày đó rồi!");
//            return false;
//        }
//    }


    // Kiểm tra nếu ngày trả thực tế lớn hơn ngày trả dự kiến
    if (pm.getNgayTraThucTe() != null && pm.getNgayTraThucTe().isAfter(pm.getHanTra())) {
        // Đặt trạng thái phiếu mượn là "Đã quá hạn" (trạng thái = 3)
        pm.setTrangThai(3);

        // Tính tiền phạt (giả sử bạn có một phương thức tính tiền phạt)
        double tienPhat = tinhTienPhat(pm.getNgayTraThucTe(), pm.getHanTra());
        pm.setTienPhat(tienPhat);
    } else {
        // Nếu ngày trả thực tế không lớn hơn ngày trả dự kiến, giữ trạng thái bình thường
        pm.setTrangThai(1); // Giả sử "Đang mượn" có trạng thái = 1
    }

    try {
        if (phieuMuonDAO.Them(pm)) {
            danhSachPhieuMuon.add(pm);
            sachBUS.giamSoLuong(pm.getMaSach(), 1);
            return true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

// Phương thức tính tiền phạt (ví dụ)
public double tinhTienPhat(LocalDate ngayTraThucTe, LocalDate hanTra) {
    long daysLate = java.time.temporal.ChronoUnit.DAYS.between(hanTra, ngayTraThucTe);
    
    if (daysLate > 0) {
        // Giả sử tiền phạt là 5000 đồng/ngày
        return daysLate * 5000;
    }
    return 0; // Không có phạt nếu không quá hạn
}

    public boolean suaPhieuMuon(PhieuMuonDTO pm) {
        if (phieuMuonDAO.Sua(pm)) {
            for (int i = 0; i < danhSachPhieuMuon.size(); i++) {
                if (danhSachPhieuMuon.get(i).getMaPhieuMuon().equals(pm.getMaPhieuMuon())) {
                    danhSachPhieuMuon.set(i, pm);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean xoaPhieuMuon(String maPhieuMuon) {
        if (phieuMuonDAO.Xoa(maPhieuMuon)) {
            for (PhieuMuonDTO pm : danhSachPhieuMuon) {
                if (pm.getMaPhieuMuon().equals(maPhieuMuon)) {
                    pm.setTrangThai(5); 
                    return true;
                }
            }
        }
        return false;
    }

//    public boolean danhDauDaTra(String maPhieuMuon) {
//        if (phieuMuonDAO.DaTra(maPhieuMuon)) {
//            for (PhieuMuonDTO pm : danhSachPhieuMuon) {
//                if (pm.getMaPhieuMuon().equals(maPhieuMuon)) {
//                    pm.setTrangThai(1); // Cập nhật trạng thái là đã trả
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
public boolean danhDauDaTra(String maPhieuMuon) {
    // Kiểm tra nếu phiếu mượn đã được trả
    if (phieuMuonDAO.DaTra(maPhieuMuon)) {
        // Duyệt qua danh sách phiếu mượn
        for (PhieuMuonDTO pm : danhSachPhieuMuon) {
            // Nếu tìm thấy phiếu mượn có mã trùng khớp
            if (pm.getMaPhieuMuon().equals(maPhieuMuon)) {
                // Kiểm tra nếu trạng thái là "Đang mượn"
                if (pm.getTrangThai() == 1) {
                    pm.setTrangThai(0); // Cập nhật trạng thái là "Đã trả"
                    return true; // Cập nhật thành công
                }
                // Nếu phiếu mượn đang ở trạng thái "Đã quá hạn", không cho phép cập nhật
                else if (pm.getTrangThai() == 3) {
                    JOptionPane.showMessageDialog(null, "Phiếu mượn đã quá hạn, không thể đánh dấu là đã trả.");
                    return false; // Không cập nhật được do quá hạn
                }
            }
        }
    }
    return false; // Trả về false nếu không tìm thấy hoặc không đáp ứng điều kiện
}

    public ArrayList<PhieuMuonDTO> getDanhSachPhieuMuon() {
        return danhSachPhieuMuon;
    }
    public String taoMaPhieuMoi() {
    PhieuMuonDAO dao = new PhieuMuonDAO();
    String maCuoi = dao.getMaPhieuMuonCuoi(); 

    if (maCuoi == null) return "PM001";

    try {
        int so = Integer.parseInt(maCuoi.substring(2)); // lấy 009
        so++;
        return String.format("PM%03d", so); // trả về PM010
    } catch (Exception e) {
        e.printStackTrace();
        return "PM001";
    }
}
//public ArrayList<PhieuMuonDTO> timKiemPhieuMuon(String maDG, String maSach, LocalDate ngayMuon, LocalDate hanTra, LocalDate ngayTraThucTe, Integer trangThai) {
//    ArrayList<PhieuMuonDTO> ketQua = new ArrayList<>();
//     ArrayList<PhieuMuonDTO> danhSachPhieuMuon = new PhieuMuonDAO().getAllPhieuMuonDTO(); 
//    for (PhieuMuonDTO pm : danhSachPhieuMuon) {
//        boolean match = true;
//
//        if (!maDG.isEmpty() && !pm.getMaDocGia().contains(maDG)) match = false;
//        if (!maSach.isEmpty() && !pm.getMaSach().contains(maSach)) match = false;
//        if (ngayMuon != null && (pm.getNgayMuon() == null || !ngayMuon.equals(pm.getNgayMuon()))) match = false;
//        if (hanTra != null && (pm.getHanTra() == null || !hanTra.equals(pm.getHanTra()))) match = false;
//        if (ngayTraThucTe != null && (pm.getNgayTraThucTe() == null || !ngayTraThucTe.equals(pm.getNgayTraThucTe()))) match = false;
//        if (trangThai != null && pm.getTrangThai() != trangThai) match = false;
//
//        if (match) ketQua.add(pm);
//    }
//    return ketQua;
//}
    public ArrayList<PhieuMuonDTO> timKiemPhieuMuon(String maDG, String maSach, LocalDate ngayMuon, LocalDate hanTra, LocalDate ngayTraThucTe, Integer trangThai) {
    ArrayList<PhieuMuonDTO> ketQua = new ArrayList<>();
    ArrayList<PhieuMuonDTO> danhSachPhieuMuon = new PhieuMuonDAO().getAllPhieuMuonDTO(); 

    for (PhieuMuonDTO pm : danhSachPhieuMuon) {
        boolean match = true;

        // Kiểm tra mã độc giả nếu có giá trị
        if (!maDG.isEmpty() && !pm.getMaDocGia().contains(maDG)) {
            match = false;
        }

        // Kiểm tra mã sách nếu có giá trị
        if (!maSach.isEmpty() && !pm.getMaSach().contains(maSach)) {
            match = false;
        }

        // Kiểm tra ngày mượn nếu có giá trị
        if (ngayMuon != null && (pm.getNgayMuon() == null || !ngayMuon.equals(pm.getNgayMuon()))) {
            match = false;
        }

        // Kiểm tra hạn trả nếu có giá trị
        if (hanTra != null && (pm.getHanTra() == null || !hanTra.equals(pm.getHanTra()))) {
            match = false;
        }

        // Kiểm tra ngày trả thực tế nếu có giá trị
        if (ngayTraThucTe != null && (pm.getNgayTraThucTe() == null || !ngayTraThucTe.equals(pm.getNgayTraThucTe()))) {
            match = false;
        }

        // Kiểm tra trạng thái nếu có giá trị
        if (trangThai != null && pm.getTrangThai() != trangThai) {
            match = false;
        }

        // Nếu tất cả các điều kiện đều đúng, thêm vào kết quả
        if (match) {
            ketQua.add(pm);
        }
    }

    return ketQua;
}
    
    public boolean doiTrangThai(String maPhieuMuon, int trangThaiMoi) {
    PhieuMuonDAO dao = new PhieuMuonDAO();
    try {
        return dao.capNhatTrangThai(maPhieuMuon, trangThaiMoi);
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
    

}
