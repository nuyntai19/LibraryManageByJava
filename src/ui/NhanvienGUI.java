
package ui;

import java.awt.Font;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
 

public class NhanvienGUI extends javax.swing.JPanel {
    
    private ArrayList<DTO.NhanVienDTO> danhSachNhanVien = new ArrayList<>();
    
    public NhanvienGUI() {
        initComponents();
        JTableHeader header = tblDSNhanvien.getTableHeader(); 
        header.setFont(new Font("Arial", Font.BOLD, 16));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        tblDSNhanvien.getColumnModel().getColumn(0).setPreferredWidth(70);
        tblDSNhanvien.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblDSNhanvien.getColumnModel().getColumn(2).setPreferredWidth(70);
        tblDSNhanvien.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblDSNhanvien.getColumnModel().getColumn(4).setPreferredWidth(150);
        tblDSNhanvien.getColumnModel().getColumn(5).setPreferredWidth(120);
        tblDSNhanvien.getColumnModel().getColumn(6).setPreferredWidth(150);
        tblDSNhanvien.getColumnModel().getColumn(7).setPreferredWidth(120);
        tblDSNhanvien.getColumnModel().getColumn(8).setPreferredWidth(120);
        
        nhanVienBUS = new BUS.NhanVienBUS();
        model = (DefaultTableModel) tblDSNhanvien.getModel(); 
        
        loadData();
        
        tblDSNhanvien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tblDSNhanvien.getSelectedRow();
                if (selectedRow != -1) {

                    String tenNhanVien = (tblDSNhanvien.getValueAt(selectedRow, 1) != null) ? tblDSNhanvien.getValueAt(selectedRow, 1).toString() : "";
                    String gioiTinh = (tblDSNhanvien.getValueAt(selectedRow, 2) != null) ? tblDSNhanvien.getValueAt(selectedRow, 2).toString() : "";
                    String ngaySinh = (tblDSNhanvien.getValueAt(selectedRow, 3) != null) ? tblDSNhanvien.getValueAt(selectedRow, 3).toString() : "";
                    String diaChi = (tblDSNhanvien.getValueAt(selectedRow, 4) != null) ? tblDSNhanvien.getValueAt(selectedRow, 4).toString() : "";
                    String soDienThoai = (tblDSNhanvien.getValueAt(selectedRow, 5) != null) ? tblDSNhanvien.getValueAt(selectedRow, 5).toString() : "";
                    String email = (tblDSNhanvien.getValueAt(selectedRow, 6) != null) ? tblDSNhanvien.getValueAt(selectedRow, 6).toString() : "";
                    String ngayVaoLam = (tblDSNhanvien.getValueAt(selectedRow, 7) != null) ? tblDSNhanvien.getValueAt(selectedRow, 7).toString() : "";
                    String trangThai = (tblDSNhanvien.getValueAt(selectedRow, 8) != null) ? tblDSNhanvien.getValueAt(selectedRow, 8).toString() : "";

                    // Gán dữ liệu vào TextField
                    txtTenNhanVien.setText(tenNhanVien);
                    txtDiaChi.setText(diaChi);
                    txtSoDienThoai.setText(soDienThoai);
                    txtEmail.setText(email);                    

                    cbGioiTinh.setSelectedItem(gioiTinh);

                    
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        if (!ngaySinh.isEmpty()) {
                            dcNgaySinh.setDate(sdf.parse(ngaySinh));
                        }
                        if (!ngayVaoLam.isEmpty()) {
                            dcNgayVaoLam.setDate(sdf.parse(ngayVaoLam));
                        }
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });
    }
    
    private void loadData() {
        try {
            model.setRowCount(0); // Xóa dữ liệu cũ
            model.setColumnCount(0);

            // Đảm bảo rằng bảng đã có các cột cần thiết
            model.setColumnIdentifiers(new String[] {
                "Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Email", "Ngày vào làm","Trạng thái"
            });

            // Lấy lại dữ liệu từ cơ sở dữ liệu
            danhSachNhanVien = nhanVienBUS.layDanhSachNhanVien();
            for (DTO.NhanVienDTO nhanvien : danhSachNhanVien) {
                model.addRow(new Object[] {
                    nhanvien.getIdNhanVien(), nhanvien.getTenNhanVien(), nhanvien.getGioiTinh(), nhanvien.getNgaySinh(),nhanvien.getDiaChi(), nhanvien.getSoDienThoai(), nhanvien.getEmail(),nhanvien.getNgayVaoLam(),nhanvien.getTrangThai()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi tải dữ liệu: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        cbGioiTinh = new javax.swing.JComboBox<>();
        dcNgayVaoLam = new com.toedter.calendar.JDateChooser();
        dcNgaySinh = new com.toedter.calendar.JDateChooser();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnKhoiPhuc = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimkiem = new javax.swing.JButton();
        btnResertTimKiem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSNhanvien = new javax.swing.JTable();

        jPanel1.setPreferredSize(new java.awt.Dimension(1210, 640));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        jPanel2.setPreferredSize(new java.awt.Dimension(930, 270));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Tên nhân viên:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Giới tính:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Ngày sinh:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Số điện thoại:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Email:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Địa chỉ:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Ngày vào làm:");

        txtTenNhanVien.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtDiaChi.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtSoDienThoai.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        cbGioiTinh.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "_", "Nam", "Nữ" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dcNgayVaoLam, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addComponent(dcNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbGioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(100, 100, 100)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtSoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmail)))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(dcNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(dcNgayVaoLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
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

        btnCapNhat.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        btnCapNhat.setContentAreaFilled(false);
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
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

        btnKhoiPhuc.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnKhoiPhuc.setText("Khôi phục");
        btnKhoiPhuc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        btnKhoiPhuc.setContentAreaFilled(false);
        btnKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 122, 77));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tìm kiếm:");

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

        btnResertTimKiem.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnResertTimKiem.setForeground(new java.awt.Color(0, 122, 77));
        btnResertTimKiem.setText("Resert");
        btnResertTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResertTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnResertTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimkiem)
                    .addComponent(btnResertTimKiem))
                .addGap(10, 10, 10))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblDSNhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên ", "Tên nhân viên", "Giới tính", "Ngày Sinh", "Địa chỉ", "Số điện thoại", "Email", "Ngày vào làm", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDSNhanvien.setGridColor(new java.awt.Color(0, 0, 0));
        tblDSNhanvien.setRowHeight(35);
        tblDSNhanvien.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tblDSNhanvien.setSelectionForeground(new java.awt.Color(0, 51, 51));
        tblDSNhanvien.setShowGrid(false);
        tblDSNhanvien.setShowVerticalLines(true);
        jScrollPane1.setViewportView(tblDSNhanvien);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
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

    private void btnKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucActionPerformed
        try {
            int selectedRow = tblDSNhanvien.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần khôi phục.");
                return;
            }

            int maNhanVien = (int) model.getValueAt(selectedRow, 0);

            nhanVienBUS.capNhatTrangThai(maNhanVien, "Đang làm");
            JOptionPane.showMessageDialog(this, "Đã cập nhật trạng thái thành 'Đang làm'.");
            loadData(); // Cập nhật lại bảng
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }

    }//GEN-LAST:event_btnKhoiPhucActionPerformed

    private void btnTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemActionPerformed
        try {
            String tuKhoa = txtTimKiem.getText().trim();
            if (tuKhoa.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm!");
                return;
            }

            // Gọi đến lớp BUS để tìm kiếm
            ArrayList<DTO.NhanVienDTO> dsnv = nhanVienBUS.timKiemNhanVien(tuKhoa);

            // Xóa dữ liệu cũ trong bảng
            model.setRowCount(0);

            // Hiển thị lại kết quả lên bảng
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (DTO.NhanVienDTO nv : dsnv) {
                model.addRow(new Object[]{
                    nv.getIdNhanVien(),
                    nv.getTenNhanVien(),
                    nv.getGioiTinh(),
                    sdf.format(nv.getNgaySinh()),
                    nv.getDiaChi(),
                    nv.getSoDienThoai(),
                    nv.getEmail(),
                    sdf.format(nv.getNgayVaoLam()),
                    nv.getTrangThai(),
                });
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm nhân viên: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnTimkiemActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
            String tenNhanVien = txtTenNhanVien.getText().trim();
            String gioiTinh = cbGioiTinh.getSelectedItem() != null ? cbGioiTinh.getSelectedItem().toString() : "";
            java.util.Date ngaySinhUtil = dcNgaySinh.getDate();
            String diaChi = txtDiaChi.getText().trim();
            String soDienThoai = txtSoDienThoai.getText().trim();
            String email = txtEmail.getText().trim();
            java.util.Date ngayVaoLamUtil = dcNgayVaoLam.getDate();
            String trangThai = "Đang làm";

            // Kiểm tra rỗng
            if (tenNhanVien.isEmpty() || gioiTinh.equals("_") || ngaySinhUtil == null ||
                diaChi.isEmpty() || soDienThoai.isEmpty() || email.isEmpty() || ngayVaoLamUtil == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
                return;
            }

            // Kiểm tra tuổi >= 18
            LocalDate ngaySinhLocal = ngaySinhUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate today = LocalDate.now();
            long age = ChronoUnit.YEARS.between(ngaySinhLocal, today);
            if (age < 18) {
                JOptionPane.showMessageDialog(this, "Nhân viên phải từ 18 tuổi trở lên.");
                return;
            }

            // Chuyển đổi ngày sang java.sql.Date
            java.sql.Date ngaySinh = new java.sql.Date(ngaySinhUtil.getTime());
            java.sql.Date ngayVaoLam = new java.sql.Date(ngayVaoLamUtil.getTime());

            // Tạo đối tượng DTO
            DTO.NhanVienDTO nv = new DTO.NhanVienDTO(
                0, // ID tự động
                tenNhanVien, gioiTinh, ngaySinh, diaChi,
                soDienThoai, email, ngayVaoLam, trangThai
            );

            // Gọi BLL thêm
            nhanVienBUS.themNhanVien(nv);
            loadData(); // Cập nhật lại bảng
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        } 
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        try {
            int selectedRow = tblDSNhanvien.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần sửa.");
                return;
            }

            // Lấy dữ liệu từ các TextFields và ComboBox
            int idNhanVien = (int) model.getValueAt(selectedRow, 0);
            String tenNhanVien = txtTenNhanVien.getText().trim();
            String gioiTinh = cbGioiTinh.getSelectedItem() != null ? cbGioiTinh.getSelectedItem().toString() : "";
            java.util.Date ngaySinhUtil = dcNgaySinh.getDate();
            String diaChi = txtDiaChi.getText().trim();
            String soDienThoai = txtSoDienThoai.getText().trim();
            String email = txtEmail.getText().trim();
            java.util.Date ngayVaoLamUtil = dcNgayVaoLam.getDate();
            String trangThai = "Đang làm";

            // Kiểm tra rỗng
            if (tenNhanVien.isEmpty() || gioiTinh.equals("_") || ngaySinhUtil == null ||
                diaChi.isEmpty() || soDienThoai.isEmpty() || email.isEmpty() || ngayVaoLamUtil == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
                return;
            }

            // Kiểm tra tuổi >= 18
            LocalDate ngaySinhLocal = ngaySinhUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate today = LocalDate.now();
            long age = ChronoUnit.YEARS.between(ngaySinhLocal, today);
            if (age < 18) {
                JOptionPane.showMessageDialog(this, "Nhân viên phải từ 18 tuổi trở lên.");
                return;
            }

            // Chuyển đổi ngày sang java.sql.Date
            java.sql.Date ngaySinh = new java.sql.Date(ngaySinhUtil.getTime());
            java.sql.Date ngayVaoLam = new java.sql.Date(ngayVaoLamUtil.getTime());

            // Tạo đối tượng DTO
            DTO.NhanVienDTO nv = new DTO.NhanVienDTO(
                idNhanVien,
                tenNhanVien, gioiTinh, ngaySinh, diaChi,
                soDienThoai, email, ngayVaoLam, trangThai
            );
            nhanVienBUS.suaNhanVien(nv);
            

            // Cập nhật bảng
            model.setValueAt(tenNhanVien, selectedRow, 1);
            model.setValueAt(gioiTinh, selectedRow, 2);
            model.setValueAt(ngaySinh.toString(), selectedRow, 3);
            model.setValueAt(diaChi, selectedRow, 4);
            model.setValueAt(soDienThoai, selectedRow, 5);
            model.setValueAt(email, selectedRow, 6);
            model.setValueAt(ngayVaoLam, selectedRow, 7);
            model.setValueAt(trangThai, selectedRow, 8);

            JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        try {
            int selectedRow = tblDSNhanvien.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa.");
                return;
            }

            int maNhanVien = (int) model.getValueAt(selectedRow, 0);

            int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn muốn đánh dấu nghỉ cho nhân viên có mã: " + maNhanVien + "?",
                "Xác nhận", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                // Gọi hàm cập nhật trạng thái
                nhanVienBUS.capNhatTrangThai(maNhanVien, "Đã nghỉ");

                JOptionPane.showMessageDialog(this, "Đã cập nhật trạng thái thành 'Đã nghỉ'.");
                loadData(); // Cập nhật lại bảng
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnResertTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResertTimKiemActionPerformed
        try {
            // 1. Xóa nội dung ô tìm kiếm
            txtTimKiem.setText("");

            // 2. Lấy toàn bộ danh sách nhân viên
            ArrayList<DTO.NhanVienDTO> dsnv = nhanVienBUS.layDanhSachNhanVien();

            // 3. Xóa dữ liệu cũ trong bảng
            model.setRowCount(0);

            // 4. Hiển thị lại toàn bộ danh sách lên bảng
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (DTO.NhanVienDTO nv : dsnv) {
                model.addRow(new Object[]{
                    nv.getIdNhanVien(),
                    nv.getTenNhanVien(),
                    nv.getGioiTinh(),
                    sdf.format(nv.getNgaySinh()),
                    nv.getDiaChi(),
                    nv.getSoDienThoai(),
                    nv.getEmail(),
                    sdf.format(nv.getNgayVaoLam()),
                    nv.getTrangThai()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi reset dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnResertTimKiemActionPerformed

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
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JButton btnResertTimKiem;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimkiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbGioiTinh;
    private com.toedter.calendar.JDateChooser dcNgaySinh;
    private com.toedter.calendar.JDateChooser dcNgayVaoLam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDSNhanvien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
    private final DefaultTableModel model;
    private final BUS.NhanVienBUS nhanVienBUS;

    private void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
