package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVienBUS {
    private final NhanVienDAO nhanVienDAO;

    public NhanVienBUS() {
        nhanVienDAO = new NhanVienDAO();
    }

    public void themNhanVien(NhanVienDTO nv) throws Exception {
        if (nhanVienDAO.kiemTraSuTonTai(nv.getIdNhanVien())) {
            throw new Exception("Mã nhân viên đã tồn tại!");
        }
        nhanVienDAO.themNhanVien(nv);
    }

    public void suaNhanVien(NhanVienDTO nv) throws SQLException {
        if (!nhanVienDAO.kiemTraSuTonTai(nv.getIdNhanVien())) {
            throw new SQLException("Không tìm thấy nhân viên với mã: " + nv.getIdNhanVien());
        }
        nhanVienDAO.suaNhanVien(nv);
    }

    public ArrayList<NhanVienDTO> layDanhSachNhanVien() throws SQLException {
        return nhanVienDAO.layDanhSachNhanVien();
    }

    public void xoaNhanVien(int idNhanVien) throws SQLException {
        nhanVienDAO.xoaNhanVien(idNhanVien);
    }

    public ArrayList<NhanVienDTO> timKiemNhanVien(String tuKhoa) throws SQLException {
        return nhanVienDAO.timKiemNhanVien(tuKhoa);
    }
    
    public void capNhatTrangThai(int maNhanVien, String trang_thai) throws SQLException {
        nhanVienDAO.capNhatTrangThai(maNhanVien, trang_thai);
}
}
