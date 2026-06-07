
package Thongke;

import java.awt.CardLayout;

public class tksach extends javax.swing.JPanel {

    public CardLayout card = new CardLayout();
    
    public tksach() {
        initComponents();
        Gdtk.setLayout(new CardLayout());
        Gdtk.add(new TkeSachtheoNam(), "TkeSachtheoThang");
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Tk_M = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        Gdtk = new javax.swing.JPanel();

        Tk_M.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        Tk_M.setText("Thống kê theo năm");
        Tk_M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tk_MtestChuyen(evt);
            }
        });

        setBackground(new java.awt.Color(255, 255, 255));

        Gdtk.setBackground(new java.awt.Color(255, 255, 255));
        Gdtk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Gdtk.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Gdtk, javax.swing.GroupLayout.PREFERRED_SIZE, 1131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Gdtk, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void Tk_MtestChuyen(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tk_MtestChuyen
//        card.show(Gdtk, "TkeSachtheoNam");
        ((CardLayout) Gdtk.getLayout()).show(Gdtk, "TkeSachtheoNam");
    }//GEN-LAST:event_Tk_MtestChuyen


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Gdtk;
    private javax.swing.JButton Tk_M;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
