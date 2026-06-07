
package DTO;

import java.util.Date;

public class PhieuNhapDTO {
    private String maPhieuNhap;
    private String loaiNhap;
    private int soLuongSach;
    private int tongTien;
    private Date ngayNhap;
    private int maNhanVien;
    private String maCoSo;

    public PhieuNhapDTO() {
    }

    public PhieuNhapDTO(String maPhieuNhap, String loaiNhap, int soLuongSach, int tongTien, Date ngayNhap, int maNhanVien, String maCoSo) {
        this.maPhieuNhap = maPhieuNhap;
        this.loaiNhap = loaiNhap;
        this.soLuongSach = soLuongSach;
        this.tongTien = tongTien;
        this.ngayNhap = ngayNhap;
        this.maNhanVien = maNhanVien;
        this.maCoSo = maCoSo;
    }

    // Getter và Setter
    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public String getLoaiNhap() {
        return loaiNhap;
    }

    public void setLoaiNhap(String loaiNhap) {
        this.loaiNhap = loaiNhap;
    }

    public int getSoLuongSach() {
        return soLuongSach;
    }

    public void setSoLuongSach(int soLuongSach) {
        this.soLuongSach = soLuongSach;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaCoSo() {
        return maCoSo;
    }

    public void setMaCoSo(String maCoSo) {
        this.maCoSo = maCoSo;
    }
}

