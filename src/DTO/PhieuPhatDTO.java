/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

public class PhieuPhatDTO {
    private String maPhieuPhat;
    private String maPhieuMuon;
    private double tienPhat;

    public PhieuPhatDTO(String maPhieuPhat, String maPhieuMuon, double tienPhat) {
        this.maPhieuPhat = maPhieuPhat;
        this.maPhieuMuon = maPhieuMuon;
        this.tienPhat = tienPhat;
    }

    public String getMaPhieuPhat() {
        return maPhieuPhat;
    }

    public void setMaPhieuPhat(String maPhieuPhat) {
        this.maPhieuPhat = maPhieuPhat;
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public double getTienPhat() {
        return tienPhat;
    }

    public void setTienPhat(double tienPhat) {
        this.tienPhat = tienPhat;
    }
}

