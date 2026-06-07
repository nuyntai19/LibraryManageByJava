package ui;

import Thongke.TkeSachtheoNam;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import Thongke.tkdg;
import Thongke.tksach;
import Thongke.tkhinhthuc;


public class ThongkeGUI extends javax.swing.JPanel {
    public CardLayout card = new CardLayout();
    private JButton selectedButton = null;
    public ThongkeGUI() {
        initComponents();
        GdThongke.setLayout(new CardLayout());
        
//        GdThongke.add(TKDocgia, "TKDocgia");
//        GdThongke.add(TKSach, "TKSach");
//        GdThongke.add(TKHinhthucmuon, "TKHinhthucmuon");

        GdThongke.add(new tkdg(), "tkdg");
        GdThongke.add(new tksach(), "tksach");
        GdThongke.add(new tkhinhthuc(), "tkhinhthuc");



        JTableHeader header = tbTkeDocgia.getTableHeader(); 
        header.setFont(new Font("Arial", Font.BOLD, 16));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        for(int i=2; i<=6; i++){
            tbTkeDocgia.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        tbTkeDocgia.getColumnModel().getColumn(5).setPreferredWidth(100);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TKDocgia = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTkeDocgia = new javax.swing.JTable();
        TKSach = new javax.swing.JPanel();
        Tk_YM = new javax.swing.JButton();
        Tk_M = new javax.swing.JButton();
        Tk_DM = new javax.swing.JButton();
        GdTk = new javax.swing.JPanel();
        TKHinhthucmuon = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        decor_background_xanh = new javax.swing.JPanel();
        btnDocgia = new javax.swing.JButton();
        btnSach = new javax.swing.JButton();
        btnHinhthucmuon = new javax.swing.JButton();
        GdThongke = new javax.swing.JPanel();

        TKDocgia.setBackground(new java.awt.Color(255, 255, 255));

        tbTkeDocgia.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        tbTkeDocgia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"12", "ko biết",  new Integer(5),  new Integer(2),  new Integer(3), "21/12/2023", "1200"},
                {"13", "hfadf ",  new Integer(2),  new Integer(0),  new Integer(0), "10/4/2025", "0"},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã độc giả", "Tên độc giả", "Mượn (lần)", "Trễ hạn (lần)", "Hỏng sách (lần)", "Ngày làm thành viên", "Tổng tiền phạt"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTkeDocgia.setGridColor(new java.awt.Color(0, 0, 0));
        tbTkeDocgia.setRowHeight(40);
        tbTkeDocgia.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tbTkeDocgia.setSelectionForeground(new java.awt.Color(0, 51, 51));
        tbTkeDocgia.setShowVerticalLines(true);
        jScrollPane1.setViewportView(tbTkeDocgia);

        javax.swing.GroupLayout TKDocgiaLayout = new javax.swing.GroupLayout(TKDocgia);
        TKDocgia.setLayout(TKDocgiaLayout);
        TKDocgiaLayout.setHorizontalGroup(
            TKDocgiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1126, Short.MAX_VALUE)
        );
        TKDocgiaLayout.setVerticalGroup(
            TKDocgiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );

        Tk_YM.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        Tk_YM.setText("Thống kê từng tháng trong năm");
        Tk_YM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TkeSach(evt);
            }
        });

        Tk_M.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        Tk_M.setText("Thống kê từng tháng trong năm");
        Tk_M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testChuyen(evt);
            }
        });

        Tk_DM.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        Tk_DM.setText("Thống kê ngày trong tháng");

        GdTk.setBackground(new java.awt.Color(255, 255, 255));
        GdTk.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(153, 153, 153)));
        GdTk.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout TKSachLayout = new javax.swing.GroupLayout(TKSach);
        TKSach.setLayout(TKSachLayout);
        TKSachLayout.setHorizontalGroup(
            TKSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TKSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tk_YM, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tk_M, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tk_DM, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(GdTk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        TKSachLayout.setVerticalGroup(
            TKSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TKSachLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TKSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Tk_YM)
                    .addComponent(Tk_M)
                    .addComponent(Tk_DM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GdTk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TKHinhthucmuonLayout = new javax.swing.GroupLayout(TKHinhthucmuon);
        TKHinhthucmuon.setLayout(TKHinhthucmuonLayout);
        TKHinhthucmuonLayout.setHorizontalGroup(
            TKHinhthucmuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1126, Short.MAX_VALUE)
        );
        TKHinhthucmuonLayout.setVerticalGroup(
            TKHinhthucmuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );

        jPanel1.setPreferredSize(new java.awt.Dimension(1210, 640));

        decor_background_xanh.setBackground(new java.awt.Color(0, 122, 77));
        decor_background_xanh.setPreferredSize(new java.awt.Dimension(1200, 60));

        btnDocgia.setBackground(new java.awt.Color(0, 122, 77));
        btnDocgia.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        btnDocgia.setForeground(new java.awt.Color(255, 255, 255));
        btnDocgia.setText("Độc giả");
        btnDocgia.setBorder(null);
        btnDocgia.setContentAreaFilled(false);
        btnDocgia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Change(evt);
            }
        });
        btnDocgia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenDocgia(evt);
            }
        });

        btnSach.setBackground(new java.awt.Color(0, 122, 77));
        btnSach.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        btnSach.setForeground(new java.awt.Color(255, 255, 255));
        btnSach.setText("Sách");
        btnSach.setBorder(null);
        btnSach.setContentAreaFilled(false);
        btnSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Change(evt);
            }
        });
        btnSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenSach(evt);
            }
        });

        btnHinhthucmuon.setBackground(new java.awt.Color(0, 122, 77));
        btnHinhthucmuon.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        btnHinhthucmuon.setForeground(new java.awt.Color(255, 255, 255));
        btnHinhthucmuon.setText("Hình thức mượn");
        btnHinhthucmuon.setBorder(null);
        btnHinhthucmuon.setContentAreaFilled(false);
        btnHinhthucmuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Change(evt);
            }
        });
        btnHinhthucmuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenHinhthucmuon(evt);
            }
        });

        javax.swing.GroupLayout decor_background_xanhLayout = new javax.swing.GroupLayout(decor_background_xanh);
        decor_background_xanh.setLayout(decor_background_xanhLayout);
        decor_background_xanhLayout.setHorizontalGroup(
            decor_background_xanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decor_background_xanhLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnDocgia, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnSach)
                .addGap(40, 40, 40)
                .addComponent(btnHinhthucmuon)
                .addContainerGap(770, Short.MAX_VALUE))
        );
        decor_background_xanhLayout.setVerticalGroup(
            decor_background_xanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, decor_background_xanhLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(decor_background_xanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSach)
                    .addComponent(btnHinhthucmuon)
                    .addComponent(btnDocgia))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        GdThongke.setBackground(new java.awt.Color(255, 255, 255));
        GdThongke.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        GdThongke.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(GdThongke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(decor_background_xanh, javax.swing.GroupLayout.DEFAULT_SIZE, 1130, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(decor_background_xanh, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(GdThongke, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Change(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Change
        JButton clickedButton = (JButton) evt.getSource();

    if (selectedButton != null && selectedButton != clickedButton) {
        selectedButton.setBorder(UIManager.getBorder("Button.border"));
    }

    selectedButton = clickedButton;
    selectedButton.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE));

    }//GEN-LAST:event_Change

    private void ChuyenDocgia(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenDocgia
        
        ((CardLayout) GdThongke.getLayout()).show(GdThongke, "tkdg");
    }//GEN-LAST:event_ChuyenDocgia

    private void ChuyenSach(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenSach
        
        ((CardLayout) GdThongke.getLayout()).show(GdThongke, "tksach");

    }//GEN-LAST:event_ChuyenSach

    private void ChuyenHinhthucmuon(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenHinhthucmuon
        
        ((CardLayout) GdThongke.getLayout()).show(GdThongke, "tkhinhthuc");

    }//GEN-LAST:event_ChuyenHinhthucmuon

    private void TkeSach(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TkeSach
        
//        card1.show(GdTk, "TkeSach");
    }//GEN-LAST:event_TkeSach

    private void testChuyen(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testChuyen
//        card1.show(GdTk, "TkeSach11");
    }//GEN-LAST:event_testChuyen


        public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UIa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongkeGUI().setVisible(true);
            }
        });
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel GdThongke;
    private javax.swing.JPanel GdTk;
    private javax.swing.JPanel TKDocgia;
    private javax.swing.JPanel TKHinhthucmuon;
    private javax.swing.JPanel TKSach;
    private javax.swing.JButton Tk_DM;
    private javax.swing.JButton Tk_M;
    private javax.swing.JButton Tk_YM;
    private javax.swing.JButton btnDocgia;
    private javax.swing.JButton btnHinhthucmuon;
    private javax.swing.JButton btnSach;
    private javax.swing.JPanel decor_background_xanh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbTkeDocgia;
    // End of variables declaration//GEN-END:variables

}
