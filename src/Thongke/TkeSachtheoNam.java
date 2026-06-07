package Thongke;
import BUS.PhieuNhapBUS;
import BUS.SachBUS;
import com.raven.chart.ModelChart;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Map;
public class TkeSachtheoNam extends JPanel {
    private double tongSoSach;
    private com.raven.chart.Chart chart;
    public TkeSachtheoNam() {
        initComponents();
        setBackground(new Color(250, 250, 250));
        chart.addLegend("Tổng số sách", new Color(255, 179, 71));
        loadDuLieuThongKe();
        
    }
    public void loadDuLieuThongKe() {
        try {
            PhieuNhapBUS pnBUS = new PhieuNhapBUS();
            ArrayList<Integer> dsNam = pnBUS.layDanhSachNam();
            ArrayList<Integer> dsTong = pnBUS.layTongSachTheoNam();
            int tong=0;
            
            for (int i = 0; i < dsNam.size(); i++) {
                
                int nam = dsNam.get(i);
                tong +=  dsTong.get(i);
//                chart.addData(new ModelChart("Năm " + (i + 1), new double[]{tong}));
                 chart.addData(new ModelChart("Năm " + nam + " (" + tong + ")", new double[]{tong}));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi thống kê: " + e.getMessage());
        }
    }
    
    private void initComponents() {
        chart = new com.raven.chart.Chart();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }
}