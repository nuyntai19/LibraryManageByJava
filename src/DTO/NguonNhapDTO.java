package DTO;

import java.util.Date;

public class NguonNhapDTO {
    private String maCoSo;
    private String tenCoSo;
    private int hinhThucChuYeu;
    private String diaChi;
    private String email;
    private Date ngayHopTac;
    private String sdt;
    private boolean trangThai;

    public NguonNhapDTO() {
    }

    public NguonNhapDTO(String maCoSo, String tenCoSo, int hinhThucChuYeu, String diaChi, 
                       String email, Date ngayHopTac, String sdt, boolean trangThai) {
        this.maCoSo = maCoSo;
        this.tenCoSo = tenCoSo;
        this.hinhThucChuYeu = hinhThucChuYeu;
        this.diaChi = diaChi;
        this.email = email;
        this.ngayHopTac = ngayHopTac;
        this.sdt = sdt;
        this.trangThai = trangThai;
    }

    // Getters
    public String getMaCoSo() {
        return maCoSo;
    }

    public String getTenCoSo() {
        return tenCoSo;
    }

    public int getHinhThucChuYeu() {
        return hinhThucChuYeu;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getEmail() {
        return email;
    }

    public Date getNgayHopTac() {
        return ngayHopTac;
    }

    public String getSdt() {
        return sdt;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    // Setters
    public void setMaCoSo(String maCoSo) {
        this.maCoSo = maCoSo;
    }

    public void setTenCoSo(String tenCoSo) {
        this.tenCoSo = tenCoSo;
    }

    public void setHinhThucChuYeu(int hinhThucChuYeu) {
        this.hinhThucChuYeu = hinhThucChuYeu;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNgayHopTac(Date ngayHopTac) {
        this.ngayHopTac = ngayHopTac;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
} 