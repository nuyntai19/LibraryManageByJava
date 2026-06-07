package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class ChiTietPhieuNhapBUS {
    private ChiTietPhieuNhapDAO chiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO();

    // Thêm chi tiết phiếu nhập
    public void themChiTietPhieuNhap(ChiTietPhieuNhapDTO ct) throws SQLException {
        chiTietPhieuNhapDAO.themChiTietPhieuNhap(ct);
    }

    // Xóa chi tiết và phiếu nhập
    public void xoaChiTietVaPhieuNhap(int maCTPN, String maPhieuNhap) throws SQLException {
        chiTietPhieuNhapDAO.xoaChiTietVaPhieuNhap(maCTPN, maPhieuNhap);
    }

    // Lấy danh sách chi tiết theo mã phiếu nhập
    public ArrayList<ChiTietPhieuNhapDTO> layDanhSachTheoMaPhieu(String maPhieuNhap) throws SQLException {
        return chiTietPhieuNhapDAO.layDanhSachTheoMaPhieu(maPhieuNhap);
    }
    
    public void xoaChiTietTheoMaPhieu(String maPhieu) throws SQLException {
        chiTietPhieuNhapDAO.xoaChiTietTheoMaPhieu(maPhieu);
    }
    
    public ArrayList<ChiTietPhieuNhapDTO> layTatCaChiTietPhieuNhap() throws SQLException {
        return chiTietPhieuNhapDAO.layTatCaChiTietPhieuNhap();
    }
    // Tìm kiếm chi tiết phiếu nhập
    public ArrayList<ChiTietPhieuNhapDTO> timKiemChiTiet(String tuKhoa) throws SQLException {
        return chiTietPhieuNhapDAO.timKiemChiTiet(tuKhoa);
    }
    
    // Renamed and changed to return Map<String, String> for ma_sach -> ten_sach
    public Map<String, String> getMaSachTenSachMap() throws SQLException {
        return chiTietPhieuNhapDAO.getMaSachTenSachMapFromSachTable();
    }
}
