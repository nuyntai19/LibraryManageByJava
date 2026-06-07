package BUS;

import DAO.PhieuNhapDAO;
import DTO.PhieuNhapDTO;
import DTO.ChiTietPhieuNhapDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class PhieuNhapBUS {
    private PhieuNhapDAO phieuNhapDAO = new PhieuNhapDAO();

    public void lapPhieuNhap(PhieuNhapDTO phieu, ArrayList<ChiTietPhieuNhapDTO> chiTiets) throws SQLException {
        phieuNhapDAO.lapPhieuNhap(phieu, chiTiets);
    }
    
    public void xoaPhieuNhap(String maPhieuNhap) throws SQLException {
        phieuNhapDAO.xoaPhieuNhap(maPhieuNhap);
    }

    public ArrayList<PhieuNhapDTO> timKiemPhieuNhap(String tuKhoa) throws SQLException {
        return phieuNhapDAO.timKiemPhieuNhap(tuKhoa);
    }
    
    public ArrayList<PhieuNhapDTO> layDanhSachPhieuNhap() throws SQLException {
        return phieuNhapDAO.layDanhSachPhieuNhap();
    }
    
    public ArrayList<String> layDanhSachMaCoSo() throws SQLException {
        return phieuNhapDAO.layDanhSachMaCoSo();
    }
    
    public ArrayList<Integer> layDanhSachNam() throws SQLException {
        return phieuNhapDAO.layDanhSachNam();
    }

    public ArrayList<Integer> layTongSachTheoNam() throws SQLException {
        return phieuNhapDAO.layTongSachTheoNam();
    }
    
    
}
