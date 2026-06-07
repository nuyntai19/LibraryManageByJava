package DTO;

public class NhanVienDTO {
    private int idNhanVien; // Đổi từ String sang int
    private String tenNhanVien;
    private String gioiTinh;
    private java.sql.Date ngaySinh;
    private String diaChi;
    private String soDienThoai;
    private String email;
    private java.sql.Date ngayVaoLam;
    private String trangThai;

    // Constructor không tham số
    public NhanVienDTO() {}

    // Constructor đầy đủ
    public NhanVienDTO(int idNhanVien, String tenNhanVien, String gioiTinh, java.sql.Date ngaySinh,
                       String diaChi, String soDienThoai, String email, java.sql.Date ngayVaoLam, String trangThai) {
        this.idNhanVien = idNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.ngayVaoLam = ngayVaoLam;
        this.trangThai = trangThai;
    }

    // Getters và Setters
    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public java.sql.Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(java.sql.Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public java.sql.Date getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(java.sql.Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
