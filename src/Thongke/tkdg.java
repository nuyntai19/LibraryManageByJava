/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Thongke;

import DAO.PhieuMuonDAO;
import DAO.SachDAO;
import DAO.mySQLConnect;
import DTO.PhieuMuonDTO;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author dinhp
 */
public class tkdg extends javax.swing.JPanel {
    private ResultSet rs=null;
    private mySQLConnect db = new mySQLConnect();
    
    public tkdg() {
        initComponents();
        
        JTableHeader header = tbTkeDocgia.getTableHeader(); 
        header.setFont(new Font("Arial", Font.BOLD, 16));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        for(int i=0; i<6; i++){
            tbTkeDocgia.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        tbTkeDocgia.getColumnModel().getColumn(5).setPreferredWidth(100);
        
        hienThiThongKeDocGia(tbTkeDocgia);
    }
    

    
    public void hienThiThongKeDocGia(JTable tableThongKe) {
    String[] columnNames = {"STT", "Mã Độc Giả", "Số Lần Mượn", "Số Lần Trễ Hạn", "Số Lần Hỏng Sách", "Tổng Tiền Phạt"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    try {
        String sql = """
            SELECT 
                ma_doc_gia,
                COUNT(*) AS so_lan_muon,
                SUM(CASE WHEN trang_thai = 3 THEN 1 ELSE 0 END) AS so_lan_tre_han,
                SUM(CASE WHEN trang_thai = 4 THEN 1 ELSE 0 END) AS so_lan_hong,
                SUM(tien_phat) AS tong_tien_phat
            FROM lbr.PhieuMuon
            GROUP BY ma_doc_gia
        """;
        ResultSet rs = db.executeQuery(sql);
        int stt = 1;
        while (rs.next()) {
            String maDocGia = rs.getString("ma_doc_gia");
            int soLanMuon = rs.getInt("so_lan_muon");
            int soLanTre = rs.getInt("so_lan_tre_han");
            int soLanHong = rs.getInt("so_lan_hong");
            double tongTienPhat = rs.getDouble("tong_tien_phat");
            Object[] rowData = {
                stt++,
                maDocGia,
                soLanMuon,
                soLanTre,
                soLanHong,
                tongTienPhat
            };
            model.addRow(rowData);
        }
        tableThongKe.setModel(model);
        rs.close();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi tải thống kê độc giả: " + e.getMessage());
    }
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTkeDocgia = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1130, 554));

        tbTkeDocgia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbTkeDocgia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã độc giả", "Mượn (Lần)", "Trễ hạn (lần)", "Hỏng sách (lần)", "Tổng phạt"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTkeDocgia.setGridColor(new java.awt.Color(0, 51, 51));
        tbTkeDocgia.setRowHeight(40);
        tbTkeDocgia.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tbTkeDocgia.setSelectionForeground(new java.awt.Color(0, 51, 51));
        tbTkeDocgia.setShowVerticalLines(true);
        jScrollPane1.setViewportView(tbTkeDocgia);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1130, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbTkeDocgia;
    // End of variables declaration//GEN-END:variables
}
