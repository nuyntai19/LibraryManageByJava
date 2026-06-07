/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.PhieuMuonDTO;
import DTO.PhieuPhatDTO;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.temporal.ChronoUnit;
import java.util.Date;
/**
 *
 * @author TRUONG THI NGOC NHI
 */
public class PhieuPhatDAO {
    public boolean Them(PhieuPhatDTO pp) {
    boolean result = false;
    try {
        Connection conn = JDBCUtil.getConnection();
        Statement st = conn.createStatement();

        String sql = String.format(
            "INSERT INTO phieuphat (ma_phieu_phat, ma_phieu_muon, tienphat) " +
            "VALUES ('%s', '%s', %.2f)",
            pp.getMaPhieuPhat(),
            pp.getMaPhieuMuon(),
            pp.getTienPhat()
        );

        int rowsAffected = st.executeUpdate(sql);
        result = rowsAffected > 0;

        JDBCUtil.closeConnection(conn);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return result;
}

public boolean daCoPhieuPhat(String maPhieuMuon) {
    boolean tonTai = false;
    try {
        Connection conn = JDBCUtil.getConnection();
        String sql = "SELECT * FROM phieuphat WHERE ma_phieu_muon = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, maPhieuMuon);
        ResultSet rs = pst.executeQuery();

        tonTai = rs.next(); // Nếu có kết quả -> đã tồn tại

        JDBCUtil.closeConnection(conn);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return tonTai;
}




}
