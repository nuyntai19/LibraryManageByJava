/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import BUS.NguonNhapBUS;
import DTO.NguonNhapDTO;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class NguonnhapGUI extends javax.swing.JPanel {
    private NguonNhapBUS nguonNhapBUS;
    private ArrayList<NguonNhapDTO> listNguonNhap;
    private DefaultTableModel model;

    /**
     * Creates new form NguonnhapGUI
     */
    public NguonnhapGUI() {
        initComponents();
        setupComponents();
        loadDataToTable();
    }
        

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContent = new javax.swing.JPanel();
        pnlForm = new javax.swing.JPanel();
        lblTenCoSo = new javax.swing.JLabel();
        lblHinhThucChuYeu = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        txtTenCoSo = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtHinhThucChuYeu = new javax.swing.JComboBox<>();
        lblDiaChi = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        lblNgayHopTac = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtNgayHopTac = new com.toedter.calendar.JDateChooser();
        btnReset = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnKhoiPhuc = new javax.swing.JButton();
        pnlSearch = new javax.swing.JPanel();
        Timkiem = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        cboTimKiem = new javax.swing.JComboBox<>();
        scrTable = new javax.swing.JScrollPane();
        tblNguonNhap = new javax.swing.JTable();

        pnlContent.setPreferredSize(new java.awt.Dimension(1210, 640));

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));

        lblTenCoSo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTenCoSo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenCoSo.setText("Tên cơ sở:");

        lblHinhThucChuYeu.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblHinhThucChuYeu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHinhThucChuYeu.setText("Hình thức chủ yếu:");

        lblSoDienThoai.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblSoDienThoai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSoDienThoai.setText("Số điện thoại:");

        txtTenCoSo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtSoDienThoai.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtHinhThucChuYeu.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtHinhThucChuYeu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mua bán", "Nhà xuất bản", "Quyên góp" }));

        lblDiaChi.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblDiaChi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDiaChi.setText("Địa chỉ:");

        txtDiaChi.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lblEmail.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEmail.setText("Email:");

        lblNgayHopTac.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNgayHopTac.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNgayHopTac.setText("Ngày hợp tác:");

        txtEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnReset.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnReset.setText("Reset");

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTenCoSo)
                    .addComponent(lblHinhThucChuYeu, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenCoSo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHinhThucChuYeu, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addComponent(lblDiaChi)
                        .addGap(28, 28, 28)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNgayHopTac)
                            .addComponent(lblSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayHopTac, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(100, 100, 100))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnReset))
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDiaChi))
                        .addGap(25, 25, 25)
                        .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFormLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblEmail)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNgayHopTac)
                                    .addComponent(txtNgayHopTac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblHinhThucChuYeu)
                                .addComponent(txtHinhThucChuYeu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTenCoSo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTenCoSo)))
                .addGap(28, 28, 28)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSoDienThoai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnThem.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        btnThem.setContentAreaFilled(false);

        btnCapNhat.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        btnCapNhat.setContentAreaFilled(false);

        btnXoa.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        btnXoa.setContentAreaFilled(false);

        btnKhoiPhuc.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnKhoiPhuc.setText("Khôi phục");
        btnKhoiPhuc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        btnKhoiPhuc.setContentAreaFilled(false);

        pnlSearch.setBackground(new java.awt.Color(0, 122, 77));

        Timkiem.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        Timkiem.setForeground(new java.awt.Color(255, 255, 255));
        Timkiem.setText("Tìm kiếm:");

        txtTimKiem.setBackground(new java.awt.Color(238, 242, 240));
        txtTimKiem.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        btnTimKiem.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnTimKiem.setForeground(new java.awt.Color(0, 122, 77));
        btnTimKiem.setText("Tìm kiếm");

        cboTimKiem.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cboTimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã cơ sở", "Tên cơ sở", "Địa chỉ", "Email", "Số điện thoại" }));

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(Timkiem)
                .addGap(18, 18, 18)
                .addComponent(cboTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(433, Short.MAX_VALUE))
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchLayout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Timkiem)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem)
                    .addComponent(cboTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        scrTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblNguonNhap.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblNguonNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"đá", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã cơ sở", "Tên cơ sở", "Hình thức chủ yếu", "Địa chỉ", "Email", "Số điện thoại", "Ngày hợp tác", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNguonNhap.setGridColor(new java.awt.Color(0, 0, 0));
        tblNguonNhap.setRowHeight(35);
        tblNguonNhap.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tblNguonNhap.setSelectionForeground(new java.awt.Color(0, 51, 51));
        tblNguonNhap.setShowVerticalLines(true);
        scrTable.setViewportView(tblNguonNhap);

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrTable, javax.swing.GroupLayout.PREFERRED_SIZE, 1166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlContentLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlContentLayout.createSequentialGroup()
                                .addComponent(pnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCapNhat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnThem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContentLayout.createSequentialGroup()
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addContainerGap(25, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(pnlForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(10, 10, 10)
                .addComponent(pnlSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrTable, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    /**
     * Thiết lập các thành phần giao diện
     */
    private void setupComponents(){
        nguonNhapBUS = new NguonNhapBUS();
        
        // Xóa text mặc định
        xoaDuLieuForm();

        // Thiết lập font cho header của bảng
        JTableHeader header = tblNguonNhap.getTableHeader(); 
        header.setFont(new Font("Arial", Font.BOLD, 16));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        // Thiết lập độ rộng cho các cột
        tblNguonNhap.getColumnModel().getColumn(0).setPreferredWidth(80);
        tblNguonNhap.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblNguonNhap.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblNguonNhap.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblNguonNhap.getColumnModel().getColumn(4).setPreferredWidth(150);
        tblNguonNhap.getColumnModel().getColumn(5).setPreferredWidth(120);
        tblNguonNhap.getColumnModel().getColumn(6).setPreferredWidth(120);
        tblNguonNhap.getColumnModel().getColumn(7).setPreferredWidth(100);
        
        // Thêm sự kiện cho các nút
        btnReset.addActionListener(e -> xoaDuLieuForm());
        btnThem.addActionListener(e -> themNguonNhap());
        btnCapNhat.addActionListener(e -> capNhatNguonNhap());
        btnXoa.addActionListener(e -> xoaNguonNhap());
        btnKhoiPhuc.addActionListener(e -> khoiPhucNguonNhap());
        btnTimKiem.addActionListener(e -> timKiemNguonNhap());
    
        tblNguonNhap.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()){
                hienThiChiTietNguonNhap();
            } 
        });
    }
    
    private void loadDataToTable() {
        try {
            listNguonNhap = nguonNhapBUS.layDanhSachNguonNhap();
            DefaultTableModel model = (DefaultTableModel) tblNguonNhap.getModel();
            model.setRowCount(0);
            for (NguonNhapDTO nguonNhap : listNguonNhap) {
                model.addRow(new Object[]{
                    nguonNhap.getMaCoSo(),
                    nguonNhap.getTenCoSo(),
                    getHinhThucString(nguonNhap.getHinhThucChuYeu()),
                    nguonNhap.getDiaChi(),
                    nguonNhap.getEmail(),
                    nguonNhap.getSdt(),
                    nguonNhap.getNgayHopTac(),
                    nguonNhap.isTrangThai() ? "Hoạt động" : "Ngưng hợp tác"
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }
    
    private String getHinhThucString(int hinhThuc) {
        switch(hinhThuc) {
            case 1: return "Mua bán";
            case 2: return "Nhà xuất bản";
            case 3: return "Quyên góp";
            default: return "Khác";
        }
    }
    
    private void xoaDuLieuForm(){
        txtTenCoSo.setText("");
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtSoDienThoai.setText("");
        txtTimKiem.setText("");
        txtHinhThucChuYeu.setSelectedIndex(0);
        txtNgayHopTac.setDate(null);
    }

    private void themNguonNhap(){
        try {
            NguonNhapDTO nguonNhap = layThongTinForm();
            if (nguonNhap == null) return;
            
            if (nguonNhapBUS.themNguonNhap(nguonNhap)) {
                JOptionPane.showMessageDialog(this, "Thêm cơ sở thành công!");
                loadDataToTable();
                xoaDuLieuForm();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm cơ sở thất bại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
    private void capNhatNguonNhap(){
        try {
            int selectedRow = tblNguonNhap.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nguồn nhập cần cập nhật!");
                return;
            }

            NguonNhapDTO nguonNhap = layThongTinForm();
            if (nguonNhap == null) return;
            
            nguonNhap.setMaCoSo(listNguonNhap.get(selectedRow).getMaCoSo());
            nguonNhap.setTrangThai(listNguonNhap.get(selectedRow).isTrangThai());
            
            if (nguonNhapBUS.capNhatNguonNhap(nguonNhap)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                loadDataToTable();
                xoaDuLieuForm();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
    private void xoaNguonNhap() {
        int selectedRow = tblNguonNhap.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nguồn nhập cần xóa!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa nguồn nhập này?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION); 
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                String maCoSo = listNguonNhap.get(selectedRow).getMaCoSo();
                
                // Gọi phương thức xóa nguồn nhập từ BUS
                if (nguonNhapBUS.xoaNguonNhap(maCoSo)) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    loadDataToTable();
                    xoaDuLieuForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
            }
        }            
    }

    private void khoiPhucNguonNhap() {
        int selectedRow = tblNguonNhap.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nguồn nhập cần khôi phục!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn khôi phục nguồn nhập này?", 
            "Xác nhận khôi phục", 
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                String maCoSo = listNguonNhap.get(selectedRow).getMaCoSo();
                
                if (nguonNhapBUS.khoiPhucNguonNhap(maCoSo)) {
                    JOptionPane.showMessageDialog(this, "Khôi phục thành công!");
                    loadDataToTable();
                    xoaDuLieuForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Khôi phục thất bại!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
            }
        }
    }
    
    private void timKiemNguonNhap() {
        try {
            String keyword = txtTimKiem.getText().trim();
            String selectedOption = (String) cboTimKiem.getSelectedItem();

            if (!keyword.isEmpty()) {
                // Gọi phương thức tìm kiếm nguồn nhập từ BUS
                listNguonNhap = nguonNhapBUS.timKiemNguonNhap(keyword, selectedOption);
                DefaultTableModel model = (DefaultTableModel) tblNguonNhap.getModel();
                model.setRowCount(0);
                for (NguonNhapDTO nguonNhap : listNguonNhap) {
                    model.addRow(new Object[]{
                        nguonNhap.getMaCoSo(),
                        nguonNhap.getTenCoSo(),
                        getHinhThucString(nguonNhap.getHinhThucChuYeu()),
                        nguonNhap.getDiaChi(),
                        nguonNhap.getEmail(),
                        nguonNhap.getSdt(),
                        nguonNhap.getNgayHopTac(),
                        nguonNhap.isTrangThai() ? "Hoạt động" : "Ngưng hợp tác"
                    });
                }
            } else {
                loadDataToTable();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm: " + e.getMessage());
        }
    }
    
    private NguonNhapDTO layThongTinForm() {
        String tenCoSo = txtTenCoSo.getText().trim();
        int hinhThucIndex = txtHinhThucChuYeu.getSelectedIndex();
        int hinhThuc = hinhThucIndex + 1;
        String diaChi = txtDiaChi.getText().trim();
        String email = txtEmail.getText().trim();
        String sdt = txtSoDienThoai.getText().trim();
        Date ngayHopTac = txtNgayHopTac.getDate();

        if (tenCoSo.isEmpty() || diaChi.isEmpty() || email.isEmpty() || sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return null;
        }

        if (ngayHopTac == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày hợp tác!");
            return null;
        }

        if (ngayHopTac.after(new Date())) {
            JOptionPane.showMessageDialog(this, "Ngày hợp tác không hợp lệ!");
            return null;
        }

        return new NguonNhapDTO("", tenCoSo, hinhThuc, diaChi, email, ngayHopTac, sdt, true);
    }

    private void hienThiChiTietNguonNhap(){
        int selectedRow = tblNguonNhap.getSelectedRow();
        if (selectedRow >= 0) {
            NguonNhapDTO nguonNhap = listNguonNhap.get(selectedRow);
            txtTenCoSo.setText(nguonNhap.getTenCoSo());
            txtDiaChi.setText(nguonNhap.getDiaChi());
            txtHinhThucChuYeu.setSelectedIndex(nguonNhap.getHinhThucChuYeu() - 1);
            txtEmail.setText(nguonNhap.getEmail());
            txtSoDienThoai.setText(nguonNhap.getSdt());
            txtNgayHopTac.setDate(nguonNhap.getNgayHopTac());
        }
    }

        
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Timkiem;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboTimKiem;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblHinhThucChuYeu;
    private javax.swing.JLabel lblNgayHopTac;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblTenCoSo;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JScrollPane scrTable;
    private javax.swing.JTable tblNguonNhap;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JComboBox<String> txtHinhThucChuYeu;
    private com.toedter.calendar.JDateChooser txtNgayHopTac;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenCoSo;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

}
