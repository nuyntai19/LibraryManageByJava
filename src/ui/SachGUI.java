package ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import java.awt.CardLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


import BUS.ChiTietPhieuNhapBUS;
import DTO.ChiTietPhieuNhapDTO;
import DTO.SachDTO;
import BUS.SachBUS;
import DAO.SachDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import java.util.Collections;


public class SachGUI extends javax.swing.JPanel {
    
    private final DefaultTableModel modelSach;
    private final BUS.ChiTietPhieuNhapBUS chiTietPhieuNhapBUS;
    private final BUS.SachBUS sachBUS ;
    private final SachDAO sachDAO ;

    public SachGUI() {
        initComponents();
        
        chiTietPhieuNhapBUS = new ChiTietPhieuNhapBUS();
        sachBUS = new SachBUS();
        sachDAO = new SachDAO();
        modelSach = (DefaultTableModel) tblSach.getModel();
        loadComboxTenSach();
        loadDSSach();
        
        // Đặt sự kiện cho ComboBox cbLoaiSach
        cbLoaiSach.addActionListener((ActionEvent e) -> {
            // Lấy loại sách được chọn
            String selectedLoaiSach = (String) cbLoaiSach.getSelectedItem();
            
            // Kiểm tra nếu chọn "_", hiển thị thông báo yêu cầu chọn loại sách
            if (selectedLoaiSach.equals("_")) {
                JOptionPane.showMessageDialog(SachGUI.this, "Vui lòng chọn loại sách!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            } else {
                // Gọi phương thức lấy tổng số lượng sách theo thể loại nếu không chọn "_"
                loadSoLuongLoaiSach(selectedLoaiSach);
            }
        });
        
        

        tblSach.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tblSach.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy dữ liệu từ các cột trong bảng
                    String tenSach = tblSach.getValueAt(selectedRow, 1).toString();
                    String tacGia = tblSach.getValueAt(selectedRow, 2).toString();
                    String loaiSach = tblSach.getValueAt(selectedRow, 3).toString();
                    String ngonNgu = tblSach.getValueAt(selectedRow, 4).toString();
                    String soLuong = tblSach.getValueAt(selectedRow, 5).toString();
                    String nhaXuatBan = tblSach.getValueAt(selectedRow, 6).toString();
                    String namXuatBan = tblSach.getValueAt(selectedRow, 7).toString();
                    String filePDF = tblSach.getValueAt(selectedRow, 8).toString();

                    boolean docTaiCho = Boolean.parseBoolean(tblSach.getValueAt(selectedRow, 9).toString());
                    boolean muonVe = Boolean.parseBoolean(tblSach.getValueAt(selectedRow, 10).toString());
                    boolean docFilePDF = Boolean.parseBoolean(tblSach.getValueAt(selectedRow, 11).toString());

                    // Đưa dữ liệu lên form
                    txtTenSach.setText(tenSach);
                    txtTacGia.setText(tacGia);
                    cbLoaiSach.setSelectedItem(loaiSach);
                    txtNgonNgu.setText(ngonNgu);
                    txtSoLuong.setText(soLuong);
                    txtNhaXuatBan.setText(nhaXuatBan);
                    txtNamXuatBan.setText(namXuatBan);
                    txtFilePDF.setText(filePDF);

                    // Cập nhật checkboxes
                    cbDocTaiCho.setSelected(docTaiCho);
                    cbMuonVe.setSelected(muonVe);
                    cbDocFilePDF.setSelected(docFilePDF);
                }
            }
        });






        
        JTableHeader header = tblSach.getTableHeader(); 
        header.setFont(new Font("Arial", Font.BOLD, 16));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        
        tblSach.getColumnModel().getColumn(0).setPreferredWidth(60);
        tblSach.getColumnModel().getColumn(1).setPreferredWidth(170);
        tblSach.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblSach.getColumnModel().getColumn(3).setPreferredWidth(90);
        tblSach.getColumnModel().getColumn(4).setPreferredWidth(70);
        tblSach.getColumnModel().getColumn(5).setPreferredWidth(70);
        tblSach.getColumnModel().getColumn(6).setPreferredWidth(80);
        tblSach.getColumnModel().getColumn(7).setPreferredWidth(80);
        tblSach.getColumnModel().getColumn(9).setPreferredWidth(20);
        tblSach.getColumnModel().getColumn(10).setPreferredWidth(20);
        tblSach.getColumnModel().getColumn(11).setPreferredWidth(20);
        

        
        
    }
    
    private void loadSoLuongLoaiSach(String theLoai) { 
        try { 
            // Lấy tổng số lượng sách theo thể loại từ BUS
            int soLuong = sachBUS.laySoLuongNhapTheoTheLoai(theLoai); 

            // Cập nhật lên TextField txtSoLuongLoaiSach
            txtSoLuongLoaiSach.setText(String.valueOf(soLuong)); 
        } catch (SQLException e) { 
            e.printStackTrace(); 
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy tổng số lượng sách theo thể loại!"); 
        } 
    }
    
    private void loadDSSach() {
        try {
            modelSach.setRowCount(0);
            List<SachDTO> ds = sachBUS.layDanhSachSach();
            for (SachDTO sach : ds) {
                modelSach.addRow(new Object[]{
                    sach.getMaSach(), 
                    sach.getTenSach(), 
                    sach.getTacGia(), 
                    sach.getTheLoai(),
                    sach.getNgonNgu(), 
                    sach.getSoLuong(), 
                    sach.getNhaXuatBan(), 
                    sach.getNamXuatBan(),
                    sach.getFilePdf(), 
                    sach.isDocTaiCho(), 
                    sach.isMuonVe(), 
                    sach.isDocFilePdf()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi load danh sách sách!");
        }
    }

    
    private boolean dangLoadComboBox = false;

    private void loadComboxTenSach() { 
        try {
            dangLoadComboBox = true; 
            cbLoaiSach.removeAllItems();
            
            Map<String, String> sachTenSachMap = chiTietPhieuNhapBUS.getMaSachTenSachMap(); 
            
            cbLoaiSach.addItem("_");

            if (sachTenSachMap != null && !sachTenSachMap.isEmpty()) {
                ArrayList<String> tenSachList = new ArrayList<>(sachTenSachMap.values());
                Collections.sort(tenSachList);

                for (String tenSach : tenSachList) {
                    cbLoaiSach.addItem(tenSach);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi load danh sách tên sách!");
        } finally {
            dangLoadComboBox = false; 
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        txtSoLuongLoaiSach = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        panThongtin = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTenSach = new javax.swing.JTextField();
        txtTacGia = new javax.swing.JTextField();
        txtNgonNgu = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbDocTaiCho = new javax.swing.JCheckBox();
        cbDocFilePDF = new javax.swing.JCheckBox();
        cbMuonVe = new javax.swing.JCheckBox();
        txtNhaXuatBan = new javax.swing.JTextField();
        txtNamXuatBan = new javax.swing.JTextField();
        txtFilePDF = new javax.swing.JTextField();
        cbLoaiSach = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnCapnhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        Timkiem = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimkiem = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSach = new javax.swing.JTable();

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Số lượng thể loại sách:");

        txtSoLuongLoaiSach.setEditable(false);
        txtSoLuongLoaiSach.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtSoLuongLoaiSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongLoaiSachActionPerformed(evt);
            }
        });

        setPreferredSize(new java.awt.Dimension(1210, 640));

        jPanel1.setPreferredSize(new java.awt.Dimension(1210, 640));

        panThongtin.setBackground(new java.awt.Color(255, 255, 255));
        panThongtin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        panThongtin.setPreferredSize(new java.awt.Dimension(930, 270));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Tên sách:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Tác giả:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Thể loại:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Nhà xuất bản:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Năm xuất bản:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Ngôn ngữ:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("File PDF:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Số lượng:");

        txtTenSach.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtTacGia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtNgonNgu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtSoLuong.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Chính sách mượn:");

        cbDocTaiCho.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbDocTaiCho.setText("Đọc tại chỗ (1)");

        cbDocFilePDF.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbDocFilePDF.setText("Đọc file pdf (3)");

        cbMuonVe.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbMuonVe.setText("Mượn về (2)");

        txtNhaXuatBan.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtNamXuatBan.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtFilePDF.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        cbLoaiSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbLoaiSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLoaiSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panThongtinLayout = new javax.swing.GroupLayout(panThongtin);
        panThongtin.setLayout(panThongtinLayout);
        panThongtinLayout.setHorizontalGroup(
            panThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panThongtinLayout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addGroup(panThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panThongtinLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addGroup(panThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbDocFilePDF, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(cbMuonVe, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                    .addGroup(panThongtinLayout.createSequentialGroup()
                        .addGroup(panThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGroup(panThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panThongtinLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                                .addGroup(panThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNamXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFilePDF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14))
                            .addGroup(panThongtinLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(panThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbLoaiSach, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNgonNgu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                                        .addGroup(panThongtinLayout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(txtTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbDocTaiCho, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        panThongtinLayout.setVerticalGroup(
            panThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panThongtinLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtNhaXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(panThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtNamXuatBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(panThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtFilePDF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cbLoaiSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(panThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNgonNgu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(cbDocTaiCho, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(panThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panThongtinLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addGroup(panThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbMuonVe)
                            .addComponent(jLabel9))
                        .addGap(9, 9, 9)
                        .addComponent(cbDocFilePDF)
                        .addGap(19, 19, 19))
                    .addGroup(panThongtinLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        btnThem.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        btnThem.setContentAreaFilled(false);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapnhat.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnCapnhat.setText("Cập nhật");
        btnCapnhat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        btnCapnhat.setContentAreaFilled(false);
        btnCapnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapnhatActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        btnXoa.setContentAreaFilled(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 122, 77));

        Timkiem.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        Timkiem.setForeground(new java.awt.Color(255, 255, 255));
        Timkiem.setText("Tìm kiếm:");

        txtTimKiem.setBackground(new java.awt.Color(238, 242, 240));
        txtTimKiem.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        btnTimkiem.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnTimkiem.setForeground(new java.awt.Color(0, 122, 77));
        btnTimkiem.setText("Tìm kiếm");
        btnTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Timkiem)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReset)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Timkiem)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimkiem)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblSach.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", "", null, null, null, null, null, null, null, "", null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sách", "Tên sách", "Tác giả", "Thể loại", "Ngôn ngữ", "Số lượng", "NXB", "Năm XB", "File PDF", "(1)", "(2)", "(3)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSach.setGridColor(new java.awt.Color(0, 0, 0));
        tblSach.setRowHeight(35);
        tblSach.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tblSach.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblSach.setShowVerticalLines(true);
        jScrollPane1.setViewportView(tblSach);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panThongtin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCapnhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panThongtin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnCapnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)))
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
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

    private void txtSoLuongLoaiSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongLoaiSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongLoaiSachActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            // Lấy thông tin từ form
            String tenSach = txtTenSach.getText().trim();
            String tacGia = txtTacGia.getText().trim();
            String loaiSach = cbLoaiSach.getSelectedItem().toString();
            String ngonNgu = txtNgonNgu.getText().trim();
            String nhaXuatBan = txtNhaXuatBan.getText().trim();
            String namXuatBan = txtNamXuatBan.getText().trim();
            String filePDF = txtFilePDF.getText().trim();
            String soLuongStr = txtSoLuong.getText().trim();

            boolean docTaiCho = cbDocTaiCho.isSelected();
            boolean muonVe = cbMuonVe.isSelected();
            boolean docFilePDF = cbDocFilePDF.isSelected();

            // Kiểm tra rỗng
            if (tenSach.isEmpty() || tacGia.isEmpty() || loaiSach.equals("_") || ngonNgu.isEmpty() ||
                nhaXuatBan.isEmpty() || namXuatBan.isEmpty() || filePDF.isEmpty() || soLuongStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
                return;
            }

            // Kiểm tra ít nhất chọn 1 chính sách
            if (!docTaiCho && !muonVe && !docFilePDF) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một chính sách mượn.");
                return;
            }

            int namXuatBanInt = Integer.parseInt(namXuatBan); 
            int soLuong = Integer.parseInt(soLuongStr);
            int soLuongLoaiSach = Integer.parseInt(txtSoLuongLoaiSach.getText().trim());

            System.out.println("Năm xuất bản: " + namXuatBanInt);

            if (soLuong > soLuongLoaiSach) {
                JOptionPane.showMessageDialog(this, "Số lượng nhập không được vượt quá số lượng còn lại.");
                return;
            }

            int soLuongHienTai = sachBUS.laySoLuongSach();
            String maSach = String.format("S%02d", soLuongHienTai + 1);

            // Thêm sách vào DB
            DTO.SachDTO sachDTO = new DTO.SachDTO(maSach, tenSach, tacGia, loaiSach, ngonNgu, soLuong,
                    nhaXuatBan, namXuatBanInt, filePDF, docTaiCho, muonVe, docFilePDF);

            sachDAO.themSach(sachDTO);

            // Cập nhật lại số lượng loại sách còn lại
            int soLuongConLai = soLuongLoaiSach - soLuong;
            txtSoLuongLoaiSach.setText(String.valueOf(soLuongConLai));

            loadDSSach(); // load lại bảng
            JOptionPane.showMessageDialog(this, "Thêm sách thành công!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void cbLoaiSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLoaiSachActionPerformed
         if (!dangLoadComboBox) {
            capNhatSoLuongLoaiSachConLai();
        }
    }//GEN-LAST:event_cbLoaiSachActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int selectedRow = tblSach.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sách cần xóa trong bảng.");
            return;
        }

        String maSach = tblSach.getValueAt(selectedRow, 0).toString(); 

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sách này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            // Xóa sách
            sachBUS.xoaSach(maSach);

            // Load lại bảng sách
            loadDSSach();

            // Cập nhật lại số lượng còn lại của loại sách
            capNhatSoLuongLoaiSachConLai();

            JOptionPane.showMessageDialog(this, "Xóa sách thành công!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa sách: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapnhatActionPerformed
        int selectedRow = tblSach.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sách cần sửa trong bảng.");
            return;
        }

        // Lấy mã sách
        String maSach = tblSach.getValueAt(selectedRow, 0).toString(); 



        // Lấy thông tin từ form (đã sửa)
        String tenSachMoi = txtTenSach.getText().trim();
        String tacGiaMoi = txtTacGia.getText().trim();
        String loaiSachMoi = cbLoaiSach.getSelectedItem().toString();
        String ngonNguMoi = txtNgonNgu.getText().trim();
        String nhaXuatBanMoi = txtNhaXuatBan.getText().trim();
        String namXuatBanMoi = txtNamXuatBan.getText().trim();
        String filePDFMoi = txtFilePDF.getText().trim();
        String soLuongMoiStr = txtSoLuong.getText().trim();

        boolean docTaiChoMoi = cbDocTaiCho.isSelected();
        boolean muonVeMoi = cbMuonVe.isSelected();
        boolean docFilePDFMoi = cbDocFilePDF.isSelected();

        if (tenSachMoi.isEmpty() || tacGiaMoi.isEmpty() || loaiSachMoi.equals("_") || ngonNguMoi.isEmpty() ||
            nhaXuatBanMoi.isEmpty() || namXuatBanMoi.isEmpty() || filePDFMoi.isEmpty() || soLuongMoiStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        try {
            int namXuatBanInt = Integer.parseInt(namXuatBanMoi); 
            int soLuongMoi = Integer.parseInt(soLuongMoiStr);

            // Lấy số lượng cũ từ bảng (không phải từ form)
            int soLuongCu = Integer.parseInt(tblSach.getValueAt(selectedRow, 5).toString());
            int chenhLech = soLuongMoi - soLuongCu;

            String loaiSach = loaiSachMoi;
            // Kiểm tra số lượng còn lại của thể loại sách
            int tongSoLuongNhap = sachBUS.laySoLuongNhapTheoTheLoai(loaiSach); // Lấy từ phiếu nhập
            int daThem = sachBUS.laySoLuongSachDaThem(loaiSach); // Lấy số lượng đã thêm vào bảng Sach
            int soLuongConLai = tongSoLuongNhap - daThem;


            if (chenhLech > soLuongConLai) {
                JOptionPane.showMessageDialog(this, "Số lượng bạn muốn cập nhật vượt quá số lượng thể loại sách còn lại (" + soLuongConLai + ").");
                return;
            }

            // Cập nhật thông tin sách
            DTO.SachDTO sachDTO = new DTO.SachDTO(maSach, tenSachMoi, tacGiaMoi, loaiSachMoi, ngonNguMoi, soLuongMoi,
                    nhaXuatBanMoi, namXuatBanInt, filePDFMoi, docTaiChoMoi, muonVeMoi, docFilePDFMoi);

            sachBUS.suaSach(sachDTO);

            loadDSSach();
            capNhatSoLuongLoaiSachConLai();

            JOptionPane.showMessageDialog(this, "Sửa thông tin sách thành công!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Năm xuất bản và số lượng phải là số.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi sửa thông tin sách: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnCapnhatActionPerformed

    private void btnTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemActionPerformed
        String tuKhoa = txtTimKiem.getText().trim();
        if (tuKhoa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa cần tìm.");
            return;
        }

        try {
            List<SachDTO> ketQua = sachBUS.timKiemTatCa(tuKhoa);

            // Ghi trực tiếp ra bảng
            DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
            model.setRowCount(0);

            for (SachDTO sach : ketQua) {
                model.addRow(new Object[]{
                    sach.getMaSach(),
                    sach.getTenSach(),
                    sach.getTacGia(),
                    sach.getTheLoai(),
                    sach.getNgonNgu(),
                    sach.getSoLuong(),
                    sach.getNhaXuatBan(),
                    sach.getNamXuatBan(),
                    sach.getFilePdf(),
                    sach.isDocTaiCho(),
                    sach.isMuonVe(),
                    sach.isDocFilePdf()
                });
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm sách: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnTimkiemActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
         txtTimKiem.setText("");
         loadDSSach();
    }//GEN-LAST:event_btnResetActionPerformed

    private void capNhatSoLuongLoaiSachConLai() {
        Object selected = cbLoaiSach.getSelectedItem();
        if (selected == null || selected.toString().equals("_")) {
            txtSoLuongLoaiSach.setText("");
            return;
        }

        String loaiSach = selected.toString();
        try {
            int tongSoLuongNhap = sachBUS.laySoLuongNhapTheoTheLoai(loaiSach); // Từ ChiTietPhieuNhap
            int soLuongDaThem = sachBUS.laySoLuongSachDaThem(loaiSach); // Lấy riêng số lượng đã thêm vào Sach từ nhập (không dựa vào tồn kho)

            int conLai = tongSoLuongNhap - soLuongDaThem;
            txtSoLuongLoaiSach.setText(String.valueOf(conLai));
        } catch (Exception e) {
            e.printStackTrace();
            txtSoLuongLoaiSach.setText("0");
        }
    }
    
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
                    new UIa().setVisible(true);
                }
            });
        }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Timkiem;
    private javax.swing.JButton btnCapnhat;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimkiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JCheckBox cbDocFilePDF;
    private javax.swing.JCheckBox cbDocTaiCho;
    private javax.swing.JComboBox<String> cbLoaiSach;
    private javax.swing.JCheckBox cbMuonVe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panThongtin;
    private javax.swing.JTable tblSach;
    private javax.swing.JTextField txtFilePDF;
    private javax.swing.JTextField txtNamXuatBan;
    private javax.swing.JTextField txtNgonNgu;
    private javax.swing.JTextField txtNhaXuatBan;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtSoLuongLoaiSach;
    private javax.swing.JTextField txtTacGia;
    private javax.swing.JTextField txtTenSach;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
