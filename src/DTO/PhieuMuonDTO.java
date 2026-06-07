
package DTO;

import java.time.LocalDate;

/**
 *
 * @author TRUONG THI NGOC NHI
 */
public class PhieuMuonDTO {
    
    private String maPhieuMuon;
    private String maDocGia;
    private String maSach;
    private LocalDate ngayMuon;
    private LocalDate hanTra;
    private LocalDate ngayTraThucTe;
    private int trangThai;
    private double tienPhat;

    public PhieuMuonDTO() {
    }



    public PhieuMuonDTO(String maPhieuMuon, String maDocGia, String maSach, LocalDate ngayMuon, LocalDate hanTra, LocalDate ngayTraThucTe, int trangThai, double tienPhat) {
        this.maPhieuMuon = maPhieuMuon;
        this.maDocGia = maDocGia;
        this.maSach = maSach;
        this.ngayMuon = ngayMuon;
        this.hanTra = hanTra;
        this.ngayTraThucTe = ngayTraThucTe;
        this.trangThai = trangThai;
        this.tienPhat = tienPhat;
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public String getMaDocGia() {
        return maDocGia;
    }

    public String getMaSach() {
        return maSach;
    }

    public LocalDate getNgayMuon() {
        return ngayMuon;
    }

    public LocalDate getHanTra() {
        return hanTra;
    }

    public LocalDate getNgayTraThucTe() {
        return ngayTraThucTe;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public double getTienPhat() {
        return tienPhat;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public void setNgayMuon(LocalDate ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public void setHanTra(LocalDate hanTra) {
        this.hanTra = hanTra;
    }

    public void setNgayTraThucTe(LocalDate ngayTraThucTe) {
        this.ngayTraThucTe = ngayTraThucTe;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public void setTienPhat(double tienPhat) {
        this.tienPhat = tienPhat;
    }

    

}