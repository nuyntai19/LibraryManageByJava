package DTO;

public class TaiKhoanDTO {
    private String maTaiKhoan;
    private String tenDangNhap;
    private String matKhau;
    private String maPhanQuyen;
    private Integer maNhanVien;
    private String maThe;
    
    // Constructor đầy đủ
    public TaiKhoanDTO(String maTaiKhoan, String tenDangNhap, String matKhau, String maPhanQuyen, Integer maNhanVien, String maThe) {
        this.maTaiKhoan = maTaiKhoan;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maPhanQuyen = maPhanQuyen;
        this.maNhanVien = maNhanVien;
        this.maThe = maThe;
    }
    
    // Constructor với maNhanVien (trường hợp tài khoản nhân viên)
    public TaiKhoanDTO(String maTaiKhoan, String tenDangNhap, String matKhau, String maPhanQuyen, Integer maNhanVien) {
        this.maTaiKhoan = maTaiKhoan;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maPhanQuyen = maPhanQuyen;
        this.maNhanVien = maNhanVien;
        this.maThe = null;
    }
    
    // Constructor với maThe (trường hợp tài khoản đọc giả)
    public TaiKhoanDTO(String maTaiKhoan, String tenDangNhap, String matKhau, String maPhanQuyen, String maThe) {
        this.maTaiKhoan = maTaiKhoan;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maPhanQuyen = maPhanQuyen;
        this.maNhanVien = null;
        this.maThe = maThe;
    }

    public TaiKhoanDTO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    // Getters và Setters
    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }
    
    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }
    
    public String getTenDangNhap() {
        return tenDangNhap;
    }
    
    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }
    
    public String getMatKhau() {
        return matKhau;
    }
    
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    public String getMaPhanQuyen() {
        return maPhanQuyen;
    }
    
    public void setMaPhanQuyen(String maPhanQuyen) {
        this.maPhanQuyen = maPhanQuyen;
    }
    
    public Integer getMaNhanVien() {
        return maNhanVien;
    }
    
    public void setMaNhanVien(Integer maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
    
    public String getMaThe() {
        return maThe;
    }
    
    public void setMaThe(String maThe) {
        this.maThe = maThe;
    }
}