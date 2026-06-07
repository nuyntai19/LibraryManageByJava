package DTO;

import java.util.Date;

public class DocGiaDTO {
    private String maDG;
    private String tenDG;
    private String gioiTinh;
    private String soDienThoai;
    private Date ngaySinh;
    private String diaChi;
    // Thông tin thẻ thành viên
    private String maThe;
    private Date ngayCap;
    private Date ngayHetHan;
    private boolean trangThai;

    public DocGiaDTO() {
    }

    public DocGiaDTO(String maDG, String tenDG, String gioiTinh, String soDienThoai, 
                    Date ngaySinh, String diaChi, String maThe, Date ngayCap, 
                    Date ngayHetHan, boolean trangThai) {
        this.maDG = maDG;
        this.tenDG = tenDG;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.maThe = maThe;
        this.ngayCap = ngayCap;
        this.ngayHetHan = ngayHetHan;
        this.trangThai = trangThai;
    }

    // Getters
    public String getMaDG() {
        return maDG;
    }

    public String getTenDG() {
        return tenDG;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getMaThe() {
        return maThe;
    }

    public Date getNgayCap() {
        return ngayCap;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    // Setters
    public void setMaDG(String maDG) {
        this.maDG = maDG;
    }

    public void setTenDG(String tenDG) {
        this.tenDG = tenDG;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setMaThe(String maThe) {
        this.maThe = maThe;
    }

    public void setNgayCap(Date ngayCap) {
        this.ngayCap = ngayCap;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
} 