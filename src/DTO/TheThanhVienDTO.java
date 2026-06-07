package DTO;

import java.util.Date;

public class TheThanhVienDTO {
    private String maThe;
    private String maDocGia;
    private Date ngayCap;
    private Date ngayHetHan;
    private boolean trangThai;
    
    public TheThanhVienDTO() {
    }
    
    public TheThanhVienDTO(String maThe, String maDocGia, Date ngayCap, Date ngayHetHan, boolean trangThai) {
        this.maThe = maThe;
        this.maDocGia = maDocGia;
        this.ngayCap = ngayCap;
        this.ngayHetHan = ngayHetHan;
        this.trangThai = trangThai;
    }
    
    public String getMaThe() {
        return maThe;
    }
    
    public void setMaThe(String maThe) {
        this.maThe = maThe;
    }
    
    public String getMaDocGia() {
        return maDocGia;
    }
    
    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }
    
    public Date getNgayCap() {
        return ngayCap;
    }
    
    public void setNgayCap(Date ngayCap) {
        this.ngayCap = ngayCap;
    }
    
    public Date getNgayHetHan() {
        return ngayHetHan;
    }
    
    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }
    
    public boolean isTrangThai() {
        return trangThai;
    }
    
    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
} 