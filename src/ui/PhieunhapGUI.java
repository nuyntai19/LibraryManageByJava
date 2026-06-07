package ui;

import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
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
import java.util.Map;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import BUS.ChiTietPhieuNhapBUS;
import BUS.PhieuNhapBUS;
import BUS.SachBUS;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;

public class PhieunhapGUI extends javax.swing.JPanel {

    private Map<String, String> maSachToTenSachMap;
    private SachBUS sachBUS;

    public PhieunhapGUI() {
        initComponents();
        
        // Căn giữa Header
        JTableHeader header = tblDSPN.getTableHeader(); 
        header.setFont(new Font("Arial", Font.BOLD, 16));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // Căn giữa dữ liệu bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblDSPN.getColumnCount(); i++) {
            tblDSPN.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Set width cho từng cột
        tblDSPN.getColumnModel().getColumn(0).setPreferredWidth(90);
        tblDSPN.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblDSPN.getColumnModel().getColumn(2).setPreferredWidth(140);
        tblDSPN.getColumnModel().getColumn(3).setPreferredWidth(120);
        tblDSPN.getColumnModel().getColumn(4).setPreferredWidth(130);
        tblDSPN.getColumnModel().getColumn(5).setPreferredWidth(120);
        tblDSPN.getColumnModel().getColumn(6).setPreferredWidth(130);
        
        // Căn giữa Header
        JTableHeader headers = tblDSCTPN.getTableHeader(); 
        headers.setFont(new Font("Arial", Font.BOLD, 16));
        ((DefaultTableCellRenderer) headers.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // Căn giữa dữ liệu bảng
        DefaultTableCellRenderer centerRenderers = new DefaultTableCellRenderer();
        centerRenderers.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblDSCTPN.getColumnCount(); i++) {
            tblDSCTPN.getColumnModel().getColumn(i).setCellRenderer(centerRenderers);
        }

        // Set width cho từng cột
        tblDSCTPN.getColumnModel().getColumn(0).setPreferredWidth(90);
        tblDSCTPN.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblDSCTPN.getColumnModel().getColumn(2).setPreferredWidth(140);
        tblDSCTPN.getColumnModel().getColumn(3).setPreferredWidth(120);
        tblDSCTPN.getColumnModel().getColumn(4).setPreferredWidth(130);
        tblDSCTPN.getColumnModel().getColumn(5).setPreferredWidth(120);

        
        phieuNhapBUS = new PhieuNhapBUS();
        chiTietPhieuNhapBUS = new ChiTietPhieuNhapBUS();
        modelPhieuNhap = (DefaultTableModel) tblDSPN.getModel();
        modelChiTietPhieuNhap = (DefaultTableModel) tblDSCTPN.getModel();
        sachBUS = new SachBUS();
        loadComboBoxCoSoNhap();
        loadComboBoxTenSach();
        loadDSPN();
        loadDSCTPN();
        
        tblDSCTPN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tblDSCTPN.getSelectedRow();
                if (selectedRow != -1) {
                    String maPhieu = tblDSCTPN.getValueAt(selectedRow, 1).toString();
                    String tenSachDisplay = tblDSCTPN.getValueAt(selectedRow, 2).toString();
                    String donGia = tblDSCTPN.getValueAt(selectedRow, 3).toString();
                    String soLuong = tblDSCTPN.getValueAt(selectedRow, 4).toString();
                    String thanhTien = tblDSCTPN.getValueAt(selectedRow, 5).toString();

                    txtMaPhieuNhap.setText(maPhieu);
                    cbTenSach.setSelectedItem(tenSachDisplay);
                    txtSoLuongSach.setText(soLuong);
                    txtDonGia.setText(donGia);
                    txtThanhTien.setText(thanhTien);
                }
            }
        });
        
        tblDSPN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tblDSPN.getSelectedRow();
                if (selectedRow != -1) {
                    String maPhieu = tblDSPN.getValueAt(selectedRow, 0).toString();
                    String loaiNhap = tblDSPN.getValueAt(selectedRow, 1).toString();
                    String tongSoLuong = tblDSPN.getValueAt(selectedRow, 2).toString();
                    String tongTien = tblDSPN.getValueAt(selectedRow, 3).toString();
                    String ngayNhap = tblDSPN.getValueAt(selectedRow, 4).toString();
                    String maNhanVien = tblDSPN.getValueAt(selectedRow, 5).toString();
                    String maCoSo = tblDSPN.getValueAt(selectedRow, 6).toString(); 

                    txtMaPhieuNhap.setText(maPhieu);
                    cbLoaiNhap.setSelectedItem(loaiNhap);
                    txtTongSoLuongSach.setText(tongSoLuong);
                    txtTongTien.setText(tongTien);
                    txtMaNhanVien.setText(maNhanVien);
                    cbMaCoSo.setSelectedItem(maCoSo);

                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        if (!ngayNhap.isEmpty()) {
                            dcNgayNhap.setDate(sdf.parse(ngayNhap));
                        }
                        
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    
                    tblDSCTPN.clearSelection();
                        for (int i = 0; i < tblDSCTPN.getRowCount(); i++) {
                            String maPhieuCT = tblDSCTPN.getValueAt(i, 1).toString();
                            if (maPhieuCT.equals(maPhieu)) {
                                tblDSCTPN.addRowSelectionInterval(i, i);
                            }
                        }
                }
            }
        });
    }

    
    private void loadDSPN() {
        try {
            modelPhieuNhap.setRowCount(0);
            ArrayList<DTO.PhieuNhapDTO> ds = phieuNhapBUS.layDanhSachPhieuNhap();
            for (PhieuNhapDTO pn : ds) {
                modelPhieuNhap.addRow(new Object[]{
                    pn.getMaPhieuNhap(), pn.getLoaiNhap(), pn.getSoLuongSach(), pn.getTongTien(),
                    pn.getNgayNhap(), pn.getMaNhanVien(), pn.getMaCoSo()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi load danh sách phiếu nhập!");
        }
    }
    
    private void loadDSCTPN(String maPhieu) {
        try {
            modelChiTietPhieuNhap.setRowCount(0);
            ArrayList<ChiTietPhieuNhapDTO> dsCT;
            if (maPhieu == null || maPhieu.isEmpty()) {
                dsCT = chiTietPhieuNhapBUS.layTatCaChiTietPhieuNhap();
            } else {
                dsCT = chiTietPhieuNhapBUS.layDanhSachTheoMaPhieu(maPhieu);
            }

            for (ChiTietPhieuNhapDTO ct : dsCT) {
                modelChiTietPhieuNhap.addRow(new Object[]{
                    ct.getMaCTPN(), ct.getMaPhieuNhap(), ct.getTenSach(),
                    ct.getDonGia(), ct.getSoLuong(), ct.getThanhTien()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi load chi tiết phiếu nhập!");
        }
    }

    private void loadDSCTPN() {
        try {
            modelChiTietPhieuNhap.setRowCount(0);
            ArrayList<ChiTietPhieuNhapDTO> ds = chiTietPhieuNhapBUS.layTatCaChiTietPhieuNhap();
            for (ChiTietPhieuNhapDTO ct : ds) {
                modelChiTietPhieuNhap.addRow(new Object[]{
                    ct.getMaCTPN(), ct.getMaPhieuNhap(), ct.getTenSach(),
                    ct.getDonGia(), ct.getSoLuong(), ct.getThanhTien()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi load tất cả chi tiết phiếu nhập!");
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ThanhChucnang = new javax.swing.JPanel();
        btnLapPhieu = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        Giaodien = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtTimKiemPhieuNhap = new javax.swing.JTextField();
        btnTimkiemPhieuNhap = new javax.swing.JButton();
        btnResertTimKiemPhieuNhap = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSPN = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTimKiemChiTietPhieuNhap = new javax.swing.JTextField();
        btnTimkiemChiTietPhieuNhap = new javax.swing.JButton();
        btnResertTimKiemChiTietPhieuNhap = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDSCTPN = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbLoaiNhap = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        dcNgayNhap = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        cbMaCoSo = new javax.swing.JComboBox<>();
        lblTenSach = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaPhieuNhap = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSoLuongSach = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTongSoLuongSach = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        cbTenSach = new javax.swing.JComboBox<>();

        jPanel1.setPreferredSize(new java.awt.Dimension(1210, 640));

        ThanhChucnang.setBackground(new java.awt.Color(255, 255, 255));
        ThanhChucnang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));

        btnLapPhieu.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnLapPhieu.setText("Lập phiếu");
        btnLapPhieu.setContentAreaFilled(false);
        btnLapPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LapPhieu(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setContentAreaFilled(false);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Them(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnXoa.setText("Xóa phiếu");
        btnXoa.setContentAreaFilled(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Xoa(evt);
            }
        });

        javax.swing.GroupLayout ThanhChucnangLayout = new javax.swing.GroupLayout(ThanhChucnang);
        ThanhChucnang.setLayout(ThanhChucnangLayout);
        ThanhChucnangLayout.setHorizontalGroup(
            ThanhChucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThanhChucnangLayout.createSequentialGroup()
                .addContainerGap(888, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnXoa)
                .addGap(18, 18, 18)
                .addComponent(btnLapPhieu)
                .addGap(147, 147, 147))
        );
        ThanhChucnangLayout.setVerticalGroup(
            ThanhChucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThanhChucnangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnLapPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Giaodien.setBackground(new java.awt.Color(0, 122, 77));
        Giaodien.setPreferredSize(new java.awt.Dimension(1357, 514));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tìm kiếm:");

        txtTimKiemPhieuNhap.setBackground(new java.awt.Color(238, 242, 240));
        txtTimKiemPhieuNhap.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        btnTimkiemPhieuNhap.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnTimkiemPhieuNhap.setForeground(new java.awt.Color(0, 122, 77));
        btnTimkiemPhieuNhap.setText("Tìm kiếm");
        btnTimkiemPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemPhieuNhapActionPerformed(evt);
            }
        });

        btnResertTimKiemPhieuNhap.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnResertTimKiemPhieuNhap.setForeground(new java.awt.Color(0, 122, 77));
        btnResertTimKiemPhieuNhap.setText("Reset");
        btnResertTimKiemPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResertTimKiemPhieuNhapActionPerformed(evt);
            }
        });

        tblDSPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu nhập", "Loại nhập", "Tổng số lượng sách", "Tổng tiền", "Ngày nhập", "Mã nhân viên", "Mã cơ sở"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDSPN.setRowHeight(30);
        jScrollPane1.setViewportView(tblDSPN);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CHI TIẾT PHIẾU NHẬP");

        jLabel11.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tìm kiếm:");

        txtTimKiemChiTietPhieuNhap.setBackground(new java.awt.Color(238, 242, 240));
        txtTimKiemChiTietPhieuNhap.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        btnTimkiemChiTietPhieuNhap.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnTimkiemChiTietPhieuNhap.setForeground(new java.awt.Color(0, 122, 77));
        btnTimkiemChiTietPhieuNhap.setText("Tìm kiếm");
        btnTimkiemChiTietPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemChiTietPhieuNhapActionPerformed(evt);
            }
        });

        btnResertTimKiemChiTietPhieuNhap.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnResertTimKiemChiTietPhieuNhap.setForeground(new java.awt.Color(0, 122, 77));
        btnResertTimKiemChiTietPhieuNhap.setText("Reset");
        btnResertTimKiemChiTietPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResertTimKiemChiTietPhieuNhapActionPerformed(evt);
            }
        });

        tblDSCTPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã ct_phiếu nhập", "Mã phiếu nhập", "Tên sách", "Đơn giá", "Số lượng sách", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
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
        tblDSCTPN.setRowHeight(30);
        jScrollPane2.setViewportView(tblDSCTPN);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Mã phiếu nhập:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Loại nhập:");

        cbLoaiNhap.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbLoaiNhap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "_", "Mua-bán", "Nhà xuất bản tài trợ", "Được quyên góp hỗ trợ" }));
        cbLoaiNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLoaiNhapActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Ngày nhập:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Mã cơ sở:");

        cbMaCoSo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbMaCoSo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "_" }));

        lblTenSach.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTenSach.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenSach.setText("Tên sách:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Đơn giá:");

        txtMaPhieuNhap.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtMaPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaPhieuNhapActionPerformed(evt);
            }
        });

        txtDonGia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Số lượng sách:");

        txtSoLuongSach.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Thành tiền:");

        txtThanhTien.setEditable(false);
        txtThanhTien.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtThanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThanhTienActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Tổng số lượng sách:");

        txtTongSoLuongSach.setEditable(false);
        txtTongSoLuongSach.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Tổng tiền:");

        txtTongTien.setEditable(false);
        txtTongTien.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Mã nhân viên");

        txtMaNhanVien.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaPhieuNhap)
                    .addComponent(dcNgayNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(txtThanhTien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbLoaiNhap, 0, 163, Short.MAX_VALUE)
                                .addGap(21, 21, 21))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbMaCoSo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(25, 25, 25)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel14))
                            .addComponent(lblTenSach))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTenSach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                .addComponent(cbLoaiNhap, 0, 1, Short.MAX_VALUE)
                                .addGap(21, 21, 21)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(txtLoaiSach, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTongTien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDonGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuongSach, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongSoLuongSach, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(cbLoaiNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenSach)
                    .addComponent(jLabel8)
                    .addComponent(txtMaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuongSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTenSach))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbMaCoSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel7)
                                .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)
                                .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dcNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(txtTongSoLuongSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14))
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        javax.swing.GroupLayout GiaodienLayout = new javax.swing.GroupLayout(Giaodien);
        Giaodien.setLayout(GiaodienLayout);
        GiaodienLayout.setHorizontalGroup(
            GiaodienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GiaodienLayout.createSequentialGroup()
                .addGroup(GiaodienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GiaodienLayout.createSequentialGroup()
                        .addGap(507, 507, 507)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(GiaodienLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(GiaodienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(GiaodienLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTimKiemChiTietPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTimkiemChiTietPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnResertTimKiemChiTietPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(124, 124, 124))
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2))))
                .addContainerGap())
            .addGroup(GiaodienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(GiaodienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GiaodienLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiemPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimkiemPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnResertTimKiemPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GiaodienLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        GiaodienLayout.setVerticalGroup(
            GiaodienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GiaodienLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(GiaodienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTimKiemPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimkiemPhieuNhap)
                    .addComponent(btnResertTimKiemPhieuNhap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(GiaodienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimkiemChiTietPhieuNhap)
                    .addComponent(txtTimKiemChiTietPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(btnResertTimKiemChiTietPhieuNhap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ThanhChucnang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Giaodien, javax.swing.GroupLayout.PREFERRED_SIZE, 1202, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ThanhChucnang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Giaodien, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1469, 1469, 1469))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1209, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 2106, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void LapPhieu(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LapPhieu
        
    try {
        String maPhieu = txtMaPhieuNhap.getText();
        String loaiNhap = cbLoaiNhap.getSelectedItem().toString();
        java.util.Date ngayNhap = dcNgayNhap.getDate(); 
        int maNhanVien = Integer.parseInt(txtMaNhanVien.getText());
        String maCoSo = cbMaCoSo.getSelectedItem().toString();

        DAO.NhanVienDAO nhanVienDAO = new DAO.NhanVienDAO();
        if (!nhanVienDAO.kiemTraSuTonTai(maNhanVien)) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không tồn tại!");
            return;
        }

        // Lấy danh sách chi tiết phiếu nhập
        ArrayList<ChiTietPhieuNhapDTO> dsCT = chiTietPhieuNhapBUS.layDanhSachTheoMaPhieu(maPhieu);
        if (dsCT.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có chi tiết phiếu nhập nào cho mã phiếu này!");
            return;
        }

        // Tính tổng số lượng và tổng tiền
        int tongSoLuong = 0;
        int tongTien = 0;
        for (ChiTietPhieuNhapDTO ct : dsCT) {
            tongSoLuong += ct.getSoLuong();
            tongTien += ct.getThanhTien();
        }

        // Tạo phiếu nhập
        PhieuNhapDTO phieu = new PhieuNhapDTO(maPhieu, loaiNhap, tongSoLuong, tongTien, ngayNhap, maNhanVien, maCoSo);

        // Lập phiếu nhập
        phieuNhapBUS.lapPhieuNhap(phieu, dsCT);

        // Update book quantities
        for (ChiTietPhieuNhapDTO ct : dsCT) {
            try {
                sachBUS.congDonSoLuongSachKhiNhap(ct.getMaSach(), ct.getSoLuong());
            } catch (SQLException ex_sach) {
                // Log or show a specific error for this book update
                System.err.println("Lỗi khi cập nhật số lượng cho sách: " + ct.getMaSach() + " - " + ex_sach.getMessage());
                // Optionally, inform user about partial success/failure
            }
        }

        // Load lại danh sách phiếu nhập và tất cả chi tiết phiếu nhập
        loadDSPN();  // Để cập nhật bảng phiếu nhập
        loadDSCTPN(null);  // Tải tất cả chi tiết phiếu nhập

        JOptionPane.showMessageDialog(this, "Lập phiếu nhập thành công!");
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi lập phiếu nhập!");
    }

    }//GEN-LAST:event_LapPhieu

    private void Them(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Them
        try {
            String maPhieu = txtMaPhieuNhap.getText();
            if (maPhieu.isEmpty()){
                JOptionPane.showMessageDialog(this, "Mã phiếu nhập không được để trống!");
                return;
            }

            Object selectedItem = cbTenSach.getSelectedItem();
            if (selectedItem == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một tên sách!");
                return;
            }
            String selectedTenSach = selectedItem.toString();
            String maSach = getMaSachFromTenSach(selectedTenSach);

            if (maSach == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã sách cho tên sách đã chọn!");
                return; // Should not happen if map is populated correctly
            }

            int donGia = Integer.parseInt(txtDonGia.getText());
            int soLuong = Integer.parseInt(txtSoLuongSach.getText());
            int thanhTien = donGia * soLuong;

            // Kiểm tra trùng lặp trong bảng chi tiết phiếu nhập (comparing maSach)
            for (int i = 0; i < tblDSCTPN.getRowCount(); i++) {
                String maPhieuCT = tblDSCTPN.getValueAt(i, 1).toString();
                // To get maSach from table, we need to retrieve it. 
                // If table displays tenSach, we need to convert it back or store maSach in a hidden column or DTO linked to row.
                // For simplicity, we'll assume the displayed TenSach is unique enough for this check in context of a single MaPhieu.
                // Or, better, retrieve the ma_sach corresponding to the ten_sach in the table for comparison.
                // Let's rely on the DAO's kiemTraChiTietTonTai which uses ma_sach.
                // String tenSachCT = tblDSCTPN.getValueAt(i, 2).toString(); 
                // if (maPhieuCT.equals(maPhieu) && tenSachCT.equals(selectedTenSach)) { 
                //     JOptionPane.showMessageDialog(this, "Chi tiết phiếu nhập (sách) đã tồn tại trong phiếu này!");
                //     return;
                // }
            }

            ChiTietPhieuNhapDTO ct = new ChiTietPhieuNhapDTO(0, maPhieu, maSach, selectedTenSach, donGia, soLuong, thanhTien);
            // The DTO can be simpler for DAO: new ChiTietPhieuNhapDTO(0, maPhieu, maSach, donGia, soLuong, thanhTien);
            // The tenSach in DTO for DAO.themChiTiet is not strictly needed as DAO inserts maSach.
            // It's useful if DAO returns the full DTO after insert.
            
            chiTietPhieuNhapBUS.themChiTietPhieuNhap(ct); // DAO will handle duplicate check with ma_sach

            loadDSCTPN(maPhieu); // Reload details for the current maPhieu to see the new item
            // Or loadDSCTPN(null); if you want to refresh all.
            JOptionPane.showMessageDialog(this, "Thêm chi tiết phiếu nhập thành công!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Đơn giá và số lượng phải là số!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm chi tiết: " + e.getMessage());
        }
    }//GEN-LAST:event_Them

    private void Xoa(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Xoa
        int selectedRowDSPN = tblDSPN.getSelectedRow(); // Lấy dòng được chọn trong bảng Phiếu Nhập
        int selectedRowDSCTPN = tblDSCTPN.getSelectedRow(); // Lấy dòng được chọn trong bảng Chi Tiết Phiếu Nhập

        if (selectedRowDSPN >= 0) {
            // Xóa từ bảng Phiếu Nhập
            String maPhieu = (String) modelPhieuNhap.getValueAt(selectedRowDSPN, 0);

            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa phiếu nhập này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    phieuNhapBUS.xoaPhieuNhap(maPhieu); // Chỉ xóa phiếu nhập
                    loadDSPN(); // Tải lại danh sách phiếu nhập
                    loadDSCTPN(null); // Tải lại tất cả chi tiết phiếu nhập
                    JOptionPane.showMessageDialog(this, "Xóa phiếu nhập thành công!");
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa phiếu nhập!");
                }
            }
        } else if (selectedRowDSCTPN >= 0) {
            // Xóa từ bảng Chi Tiết Phiếu Nhập
            int maCTPN = (int) modelChiTietPhieuNhap.getValueAt(selectedRowDSCTPN, 0);
            String maPhieu = (String) modelChiTietPhieuNhap.getValueAt(selectedRowDSCTPN, 1);

            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa chi tiết phiếu nhập này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    chiTietPhieuNhapBUS.xoaChiTietVaPhieuNhap(maCTPN, maPhieu); // Xóa chi tiết phiếu nhập
                    loadDSPN();
                    loadDSCTPN(null); // Tải lại tất cả chi tiết phiếu nhập
                    JOptionPane.showMessageDialog(this, "Xóa chi tiết phiếu nhập thành công!");
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa chi tiết phiếu nhập!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xóa!");
        }
    }//GEN-LAST:event_Xoa

    private void btnTimkiemPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemPhieuNhapActionPerformed
        try {
            String tuKhoa = txtTimKiemPhieuNhap.getText();
            ArrayList<PhieuNhapDTO> ds = phieuNhapBUS.timKiemPhieuNhap(tuKhoa);
            modelPhieuNhap.setRowCount(0);
            for (PhieuNhapDTO pn : ds) {
                modelPhieuNhap.addRow(new Object[]{
                    pn.getMaPhieuNhap(), pn.getLoaiNhap(), pn.getSoLuongSach(), pn.getTongTien(),
                    pn.getNgayNhap(), pn.getMaNhanVien(), pn.getMaCoSo()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi tìm kiếm!");
        }
    }//GEN-LAST:event_btnTimkiemPhieuNhapActionPerformed

    private void btnResertTimKiemPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResertTimKiemPhieuNhapActionPerformed
        txtTimKiemPhieuNhap.setText("");
        loadDSPN();
    }//GEN-LAST:event_btnResertTimKiemPhieuNhapActionPerformed

    private void btnTimkiemChiTietPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemChiTietPhieuNhapActionPerformed
        try {
            String tuKhoa = txtTimKiemChiTietPhieuNhap.getText();
            ArrayList<ChiTietPhieuNhapDTO> ds = chiTietPhieuNhapBUS.timKiemChiTiet(tuKhoa);
            modelChiTietPhieuNhap.setRowCount(0);
            for (ChiTietPhieuNhapDTO ct : ds) {
                modelChiTietPhieuNhap.addRow(new Object[]{
                    ct.getMaCTPN(), ct.getMaPhieuNhap(), ct.getTenSach(),
                    ct.getDonGia(), ct.getSoLuong(), ct.getThanhTien()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi tìm kiếm chi tiết!");
        }
    }//GEN-LAST:event_btnTimkiemChiTietPhieuNhapActionPerformed

    private void btnResertTimKiemChiTietPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResertTimKiemChiTietPhieuNhapActionPerformed
        txtTimKiemChiTietPhieuNhap.setText("");
        loadDSCTPN();
    }//GEN-LAST:event_btnResertTimKiemChiTietPhieuNhapActionPerformed

    private void loadComboBoxCoSoNhap() {
        try {
            cbMaCoSo.removeAllItems();
            ArrayList<String> danhSachMaCoSo = phieuNhapBUS.layDanhSachMaCoSo();
            for (String maCoSo : danhSachMaCoSo) {
                cbMaCoSo.addItem(maCoSo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi load danh sách mã cơ sở!");
        }
    }

    private void loadComboBoxTenSach() {
        try {
            maSachToTenSachMap = chiTietPhieuNhapBUS.getMaSachTenSachMap();
            cbTenSach.removeAllItems();
            if (maSachToTenSachMap != null) {
                // To ensure a somewhat predictable order, you could sort keys or values if map is not ordered
                // For HashMap, order is not guaranteed. If using LinkedHashMap in BUS/DAO, order is preserved.
                ArrayList<String> tenSachList = new ArrayList<>(maSachToTenSachMap.values());
                java.util.Collections.sort(tenSachList); // Sort names alphabetically
                for (String tenSach : tenSachList) {
                    cbTenSach.addItem(tenSach);
                }
            }
             if (cbTenSach.getItemCount() > 0) {
                cbTenSach.setSelectedIndex(0); // Select first item by default if list is not empty
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi load danh sách tên sách!");
        }
    }

    // Helper method to get ma_sach from ten_sach using the map
    private String getMaSachFromTenSach(String tenSach) {
        if (maSachToTenSachMap != null) {
            for (Map.Entry<String, String> entry : maSachToTenSachMap.entrySet()) {
                if (entry.getValue().equals(tenSach)) {
                    return entry.getKey(); // Returns the first ma_sach if ten_sach values are not unique
                }
            }
        }
        return null; // Or throw an exception if tenSach not found
    }

    private void txtThanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThanhTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThanhTienActionPerformed

    private void txtMaPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaPhieuNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaPhieuNhapActionPerformed

    private void cbLoaiNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLoaiNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbLoaiNhapActionPerformed

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
    private javax.swing.JPanel Giaodien;
    private javax.swing.JPanel ThanhChucnang;
    private javax.swing.JButton btnLapPhieu;
    private javax.swing.JButton btnResertTimKiemChiTietPhieuNhap;
    private javax.swing.JButton btnResertTimKiemPhieuNhap;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimkiemChiTietPhieuNhap;
    private javax.swing.JButton btnTimkiemPhieuNhap;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbLoaiNhap;
    private javax.swing.JComboBox<String> cbMaCoSo;
    private javax.swing.JComboBox<String> cbTenSach;
    private com.toedter.calendar.JDateChooser dcNgayNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTenSach;
    private javax.swing.JTable tblDSCTPN;
    private javax.swing.JTable tblDSPN;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtMaPhieuNhap;
    private javax.swing.JTextField txtSoLuongSach;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTimKiemChiTietPhieuNhap;
    private javax.swing.JTextField txtTimKiemPhieuNhap;
    private javax.swing.JTextField txtTongSoLuongSach;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
    private final DefaultTableModel modelPhieuNhap;
    private final DefaultTableModel modelChiTietPhieuNhap;
    private final BUS.PhieuNhapBUS phieuNhapBUS;
    private final BUS.ChiTietPhieuNhapBUS chiTietPhieuNhapBUS;
}

