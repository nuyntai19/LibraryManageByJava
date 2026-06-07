
package DTO;

public class SachDTO {
    private String maSach;
    private String tenSach;
    private String tacGia;
    private String theLoai;
    private String ngonNgu;
    private int soLuong;
    private String nhaXuatBan;
    private int namXuatBan;
    private String filePdf;
    private boolean docTaiCho;
    private boolean muonVe;
    private boolean docFilePdf;
    

    // Constructors
    public SachDTO() {}

    public SachDTO(String maSach, String tenSach, String tacGia, String theLoai, String ngonNgu,
                   int soLuong, String nhaXuatBan, int namXuatBan, String filePdf,
                   boolean docTaiCho, boolean muonVe, boolean docFilePdf) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.ngonNgu = ngonNgu;
        this.soLuong = soLuong;
        this.nhaXuatBan = nhaXuatBan;
        this.namXuatBan = namXuatBan;
        this.filePdf = filePdf;
        this.docTaiCho = docTaiCho;
        this.muonVe = muonVe;
        this.docFilePdf = docFilePdf;
    }

    public SachDTO(String string, String string0, String string1, String string2, int aInt, int aInt0, double aDouble) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public SachDTO(String string, String string0, String string1, String string2, int aInt, int aInt0, double aDouble, String string3) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters and Setters
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

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(String ngonNgu) {
        this.ngonNgu = ngonNgu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public String getFilePdf() {
        return filePdf;
    }

    public void setFilePdf(String filePdf) {
        this.filePdf = filePdf;
    }

    public boolean isDocTaiCho() {
        return docTaiCho;
    }

    public void setDocTaiCho(boolean docTaiCho) {
        this.docTaiCho = docTaiCho;
    }

    public boolean isMuonVe() {
        return muonVe;
    }

    public void setMuonVe(boolean muonVe) {
        this.muonVe = muonVe;
    }

    public boolean isDocFilePdf() {
        return docFilePdf;
    }

    public void setDocFilePdf(boolean docFilePdf) {
        this.docFilePdf = docFilePdf;
    }
    
    
}

