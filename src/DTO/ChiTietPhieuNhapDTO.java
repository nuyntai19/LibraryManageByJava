package DTO;

public class ChiTietPhieuNhapDTO {
    private int maCTPN;
    private String maPhieuNhap;
    private String maSach;
    private String tenSach;
    private int donGia;
    private int soLuong;
    private int thanhTien;

    public ChiTietPhieuNhapDTO() {
    }

    public ChiTietPhieuNhapDTO(int maCTPN, String maPhieuNhap, String maSach, String tenSach, int donGia, int soLuong, int thanhTien) {
        this.maCTPN = maCTPN;
        this.maPhieuNhap = maPhieuNhap;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    public ChiTietPhieuNhapDTO(int maCTPN, String maPhieuNhap, String maSach, int donGia, int soLuong, int thanhTien) {
        this.maCTPN = maCTPN;
        this.maPhieuNhap = maPhieuNhap;
        this.maSach = maSach;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    // Getter và Setter
    public int getMaCTPN() {
        return maCTPN;
    }

    public void setMaCTPN(int maCTPN) {
        this.maCTPN = maCTPN;
    }

    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

}

