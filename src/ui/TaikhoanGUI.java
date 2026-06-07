/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package ui;
import java.util.*;
import Helper.PlaceHolder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.ArrayList;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import DAO.NhanVienDAO;
import DAO.PhanQuyenDAO;
import DAO.TaiKhoanDAO;
import DTO.PhanQuyenDTO;
import java.awt.event.*;

/**
 *
 * @author dinhp
 */
public class TaikhoanGUI extends javax.swing.JPanel {

    private NhanVienDAO nhanVienDAO;
    private TaiKhoanDAO taiKhoanDAO;
    private DefaultTableModel tableModel;
    private String currentUsername = null;
    public TaikhoanGUI() {
        initComponents();
        nhanVienDAO = new NhanVienDAO();
        taiKhoanDAO = new TaiKhoanDAO();
        txTennv.setEditable(false);
        txNgayvaolam.setEditable(false);
        JTableHeader header = tbTaikhoan.getTableHeader(); 
        header.setFont(new Font("Arial", Font.BOLD, 16));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        PlaceHolder.addPlaceHolderEffect(txNgayvaolam1,"Nhập tên đăng nhập");
         
    
        loadMaNhanVienComboBox();
    loadPhanQuyenComboBox(); // Add this line

        
       
    
    // Thêm action listener cho combobox
   cbManv.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        Object selectedItem = cbManv.getSelectedItem();
        if (selectedItem != null) {
            String selectedMaNV = selectedItem.toString();
            hienThiThongTinNhanVien(selectedMaNV);
        }
    }
});
     // Thêm action listener cho nút Reset
    btnReset.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            resetData();
        }
    });
    
    btnXacnhan.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            themTaiKhoan();
        }
    });
    
    btnXoa.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        xoaTaiKhoan();
    }
});

// Thêm MouseListener cho bảng để xử lý khi người dùng click vào một hàng
tbTaikhoan.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        int selectedRow = tbTaikhoan.getSelectedRow();
        if (selectedRow >= 0) {
            // Lấy thông tin từ hàng được chọn
            String maNV = tbTaikhoan.getValueAt(selectedRow, 0).toString();
            String tenNV = tbTaikhoan.getValueAt(selectedRow, 1).toString();
            String tenDangNhap = tbTaikhoan.getValueAt(selectedRow, 2).toString();
            String nhomQuyen = tbTaikhoan.getValueAt(selectedRow, 3).toString();
            
            // Hiển thị thông tin lên form
            for (int i = 0; i < cbManv.getItemCount(); i++) {
                if (cbManv.getItemAt(i).equals(maNV)) {
                    cbManv.setSelectedIndex(i);
                    break;
                }
            }
            
            txNgayvaolam1.setText(tenDangNhap);
            
            // Chọn nhóm quyền trong combobox
            for (int i = 0; i < cb_txPhanquyen.getItemCount(); i++) {
                if (cb_txPhanquyen.getItemAt(i).equals(nhomQuyen)) {
                    cb_txPhanquyen.setSelectedIndex(i);
                    break;
                }
            }
        }
    }
});

btnCapnhat.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        capNhatTaiKhoan();
    }
});

// Cập nhật phương thức mouseClicked để lưu lại tên đăng nhập hiện tại
tbTaikhoan.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        int selectedRow = tbTaikhoan.getSelectedRow();
        if (selectedRow >= 0) {
            // Lấy thông tin từ hàng được chọn
            String maNV = tbTaikhoan.getValueAt(selectedRow, 0).toString();
            String tenNV = tbTaikhoan.getValueAt(selectedRow, 1).toString();
            String tenDangNhap = tbTaikhoan.getValueAt(selectedRow, 2).toString();
            String nhomQuyen = tbTaikhoan.getValueAt(selectedRow, 3).toString();
            
            // Lưu lại tên đăng nhập hiện tại để sử dụng khi cập nhật
            currentUsername = tenDangNhap;
            
            // Hiển thị thông tin lên form
            for (int i = 0; i < cbManv.getItemCount(); i++) {
                if (cbManv.getItemAt(i).equals(maNV)) {
                    cbManv.setSelectedIndex(i);
                    break;
                }
            }
            
            txNgayvaolam1.setText(tenDangNhap);
            
            // Chọn nhóm quyền trong combobox
            for (int i = 0; i < cb_txPhanquyen.getItemCount(); i++) {
                if (cb_txPhanquyen.getItemAt(i).equals(nhomQuyen)) {
                    cb_txPhanquyen.setSelectedIndex(i);
                    break;
                }
            }
        }
    }
});
    
    capNhatBangTaiKhoan();
    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTaikhoan = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        Manv = new javax.swing.JLabel();
        cbManv = new javax.swing.JComboBox<>();
        Tennv = new javax.swing.JLabel();
        txTennv = new javax.swing.JTextField();
        Ngayvaolam = new javax.swing.JLabel();
        txNgayvaolam = new javax.swing.JTextField();
        Nhomquyen = new javax.swing.JLabel();
        cb_txPhanquyen = new javax.swing.JComboBox<>();
        btnXacnhan = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        Ngayvaolam1 = new javax.swing.JLabel();
        txNgayvaolam1 = new javax.swing.JTextField();
        btnCapnhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        jPanel2.setPreferredSize(new java.awt.Dimension(1210, 640));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbTaikhoan.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        tbTaikhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Tên đăng nhập", "Nhóm quyền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTaikhoan.setGridColor(new java.awt.Color(0, 0, 0));
        tbTaikhoan.setRowHeight(35);
        tbTaikhoan.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tbTaikhoan.setSelectionForeground(new java.awt.Color(0, 51, 51));
        tbTaikhoan.setShowVerticalLines(true);
        jScrollPane1.setViewportView(tbTaikhoan);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));

        Manv.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Manv.setText("Mã nhân viên:");

        cbManv.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        cbManv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Truy xuất bảng nv" }));

        Tennv.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Tennv.setText("Tên nhân viên:");

        txTennv.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        txTennv.setText("Truy xuất dựa vào mã nv");
        txTennv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txTennvActionPerformed(evt);
            }
        });

        Ngayvaolam.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Ngayvaolam.setText("Ngày vào làm:");

        txNgayvaolam.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        txNgayvaolam.setText("Truy xuất dựa vào mã nv");

        Nhomquyen.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Nhomquyen.setText("Nhóm quyền:");

        cb_txPhanquyen.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        cb_txPhanquyen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Truy xuất bảng phân quyền" }));

        btnXacnhan.setBackground(new java.awt.Color(0, 122, 77));
        btnXacnhan.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnXacnhan.setForeground(new java.awt.Color(255, 255, 255));
        btnXacnhan.setText("Thêm");
        btnXacnhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242), 3));

        btnReset.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnReset.setText("Reset");

        Ngayvaolam1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Ngayvaolam1.setText("Tên đăng nhập:");

        txNgayvaolam1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        txNgayvaolam1.setText("Tự nhập thêm");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(Nhomquyen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cb_txPhanquyen, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(Ngayvaolam, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txNgayvaolam, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Manv, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tennv, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txTennv, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(cbManv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(Ngayvaolam1)
                        .addGap(25, 25, 25)
                        .addComponent(txNgayvaolam1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnReset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXacnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Manv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbManv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Tennv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txTennv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ngayvaolam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txNgayvaolam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ngayvaolam1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txNgayvaolam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Nhomquyen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_txPhanquyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXacnhan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        btnCapnhat.setBackground(new java.awt.Color(255, 255, 0));
        btnCapnhat.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnCapnhat.setText("Cập nhật");
        btnCapnhat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242), 3));

        btnXoa.setBackground(new java.awt.Color(204, 0, 51));
        btnXoa.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242), 3));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCapnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txTennvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txTennvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txTennvActionPerformed

 
   private void loadPhanQuyenComboBox() {
    try {
        PhanQuyenDAO phanQuyenDAO = new PhanQuyenDAO();
        ArrayList<PhanQuyenDTO> danhSachPhanQuyen = phanQuyenDAO.layDanhSachPhanQuyen();
        
        // Create a map to store unique permission names
        Map<String, String> uniquePermissions = new HashMap<>();
        for (PhanQuyenDTO pq : danhSachPhanQuyen) {
            uniquePermissions.put(pq.getMaPhanQuyen(), pq.getTenPhanQuyen());
        }
        
        // Clear the current items in the combobox
        cb_txPhanquyen.removeAllItems();
        
        // Add a default item
        cb_txPhanquyen.addItem("-- Chọn phân quyền --");
        
        // Add each unique permission to the combobox
        for (Map.Entry<String, String> entry : uniquePermissions.entrySet()) {
            cb_txPhanquyen.addItem(entry.getValue());
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách phân quyền: " + e.getMessage());
        e.printStackTrace();
    }
}

    
 private void themTaiKhoan() {
    try {
        // Lấy các giá trị từ form
        String maNV = cbManv.getSelectedItem().toString();
        
        // Kiểm tra xem có chọn mã nhân viên chưa
        if (maNV.equals("-- Chọn mã nhân viên --")) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn mã nhân viên!");
            return;
        }
        
        String tenDangNhap = txNgayvaolam1.getText().trim();
        
        // Kiểm tra tên đăng nhập
        if (tenDangNhap.isEmpty() || tenDangNhap.equals("Nhập tên đăng nhập")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên đăng nhập!");
            return;
        }
        
        // Tạo đối tượng DAO
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        
        // Kiểm tra tên đăng nhập tồn tại chưa
        if (tkDAO.kiemTraTenDangNhap(tenDangNhap)) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại! Vui lòng chọn tên khác.");
            return;
        }
        
        // Tạo mã tài khoản ngẫu nhiên 8 chữ số
        String maTaiKhoan = tkDAO.taoMaTaiKhoanNgauNhien();
        
        // Mật khẩu mặc định là 123456789
        String matKhau = "123456789";
        
        // Mã phân quyền - tạm thời để null như yêu cầu
        String maPhanQuyen = null;
        
        // Chuyển mã nhân viên sang kiểu int
        int maNhanVien = Integer.parseInt(maNV);
        
        // Tạo đối tượng DTO (tài khoản nhân viên nên maThe = null)
        TaiKhoanDTO tk = new TaiKhoanDTO(maTaiKhoan, tenDangNhap, matKhau, maPhanQuyen, maNhanVien);
        
        // Lưu vào CSDL
        boolean ketQua = tkDAO.themTaiKhoan(tk);
        
        if (ketQua) {
            // Hiển thị thông báo thành công
               applyPermissionToAccount(maTaiKhoan);

            JOptionPane.showMessageDialog(this, "Thêm tài khoản thành công!\nMã tài khoản: " + maTaiKhoan);
            
            // Cập nhật bảng hiển thị
            capNhatBangTaiKhoan();
            
            // Reset form
            resetData();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm tài khoản thất bại!");
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi thêm tài khoản: " + e.getMessage());
        e.printStackTrace();
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Mã nhân viên không hợp lệ!");
    }
}
 
private void hienThiThongTinNhanVien(String maNV) {
    try {
        if (maNV == null || maNV.equals("-- Chọn mã nhân viên --")) {
            txTennv.setText("");
            txNgayvaolam.setText("");
            return;
        }
        
        NhanVienDAO nvDAO = new NhanVienDAO();
        ArrayList<NhanVienDTO> dsnv = nvDAO.layDanhSachNhanVien();
        
        // Tìm nhân viên có mã tương ứng
        for (NhanVienDTO nv : dsnv) {
            if (String.valueOf(nv.getIdNhanVien()).equals(maNV)) {
                // Hiển thị thông tin nhân viên
                txTennv.setText(nv.getTenNhanVien());
                // Định dạng ngày tháng để hiển thị
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                txNgayvaolam.setText(sdf.format(nv.getNgayVaoLam()));
                return;
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi tải thông tin nhân viên: " + e.getMessage());
    }
}

private void loadMaNhanVienComboBox() {
    try {
        // Tạm thời xóa ActionListener để tránh sự kiện kích hoạt trong quá trình load
        ActionListener[] listeners = cbManv.getActionListeners();
        for (ActionListener listener : listeners) {
            cbManv.removeActionListener(listener);
        }
        
        NhanVienDAO nvDAO = new NhanVienDAO();
        ArrayList<NhanVienDTO> dsnv = nvDAO.layDanhSachNhanVien();
        
        // Xóa dữ liệu cũ trong combobox
        cbManv.removeAllItems();
        
        // Thêm một item mặc định (tùy chọn)
        cbManv.addItem("-- Chọn mã nhân viên --");
        
        // Thêm các mã nhân viên từ database vào combobox
        for (NhanVienDTO nv : dsnv) {
            cbManv.addItem(String.valueOf(nv.getIdNhanVien()));
        }
        
        // Đảm bảo có item được chọn
        if (cbManv.getItemCount() > 0) {
            cbManv.setSelectedIndex(0);
        }
        
        // Thêm lại ActionListener
        for (ActionListener listener : listeners) {
            cbManv.addActionListener(listener);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu nhân viên: " + e.getMessage());
    }
}
private void resetData() {
    try {
        // Xóa nội dung trong các trường nhập liệu trước khi làm mới combobox
        txTennv.setText("");
        txNgayvaolam.setText("");
        txNgayvaolam1.setText("");
        currentUsername = null; // Reset biến lưu tên đăng nhập hiện tại
        
        // Reset ComboBox phân quyền về giá trị mặc định
        if (cb_txPhanquyen.getItemCount() > 0) {
            cb_txPhanquyen.setSelectedIndex(0);
        }
        
        // Làm mới dữ liệu ComboBox mã nhân viên
        loadMaNhanVienComboBox();
        loadPhanQuyenComboBox();
        
        JOptionPane.showMessageDialog(this, "Đã làm mới dữ liệu thành công!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi làm mới dữ liệu: " + e.getMessage());
    }
}

 // Phương thức để cập nhật bảng hiển thị tài khoản
private void capNhatBangTaiKhoan() {
    try {
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        ArrayList<TaiKhoanDTO> dstk = tkDAO.layDanhSachTaiKhoan();
        
        // Tạo model mới cho bảng
        DefaultTableModel model = (DefaultTableModel) tbTaikhoan.getModel();
        model.setRowCount(0); // Xóa tất cả dữ liệu hiện có
        
        // Thêm dữ liệu mới vào bảng
        for (TaiKhoanDTO tk : dstk) {
            // Chỉ hiển thị tài khoản của nhân viên (không hiển thị tài khoản của đọc giả)
            if (tk.getMaNhanVien() != null) {
                // Lấy tên nhân viên từ mã nhân viên
                NhanVienDAO nvDAO = new NhanVienDAO();
                ArrayList<NhanVienDTO> dsnv = nvDAO.layDanhSachNhanVien();
                String tenNhanVien = "";
                
                for (NhanVienDTO nv : dsnv) {
                    if (nv.getIdNhanVien() == tk.getMaNhanVien()) {
                        tenNhanVien = nv.getTenNhanVien();
                        break;
                    }
                }
                
                // Hiển thị chỉ các thông tin cần thiết lên bảng tbTaikhoan
                Object[] row = {
                    tk.getMaNhanVien(),         // Mã nhân viên
                    tenNhanVien,                // Tên nhân viên
                    tk.getTenDangNhap(),        // Tên đăng nhập
                    tk.getMaPhanQuyen() == null ? "Chưa phân quyền" : tk.getMaPhanQuyen() // Nhóm quyền
                };
                
                model.addRow(row);
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu tài khoản: " + e.getMessage());
        e.printStackTrace();
    }
}

private void applyPermissionToAccount(String accountId) {
    try {
        String selectedPermissionName = cb_txPhanquyen.getSelectedItem().toString();
        
        // Bỏ qua nếu đang chọn mục mặc định
        if (selectedPermissionName.equals("-- Chọn phân quyền --")) {
            return;
        }
        
        // Lấy mã phân quyền từ tên
        PhanQuyenDAO pqDAO = new PhanQuyenDAO();
        String maPhanQuyen = pqDAO.layMaPhanQuyenTuTen(selectedPermissionName);
        
        if (maPhanQuyen != null) {
            // Cập nhật tài khoản với phân quyền đã chọn
            TaiKhoanDAO tkDAO = new TaiKhoanDAO();
            boolean success = tkDAO.ganPhanQuyen(accountId, maPhanQuyen);
            
            if (!success) {
                JOptionPane.showMessageDialog(this, "Không thể gán phân quyền cho tài khoản!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy mã phân quyền cho: " + selectedPermissionName);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi gán phân quyền: " + e.getMessage());
        e.printStackTrace();
    }
}
    
  private void xoaTaiKhoan() {
    try {
        // Kiểm tra xem đã chọn hàng nào chưa
        int selectedRow = tbTaikhoan.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần xóa!");
            return;
        }
        
        // Lấy thông tin tài khoản từ hàng được chọn
        String tenDangNhap = tbTaikhoan.getValueAt(selectedRow, 2).toString();
        String tenNhanVien = tbTaikhoan.getValueAt(selectedRow, 1).toString();
        
        // Hiển thị hộp thoại xác nhận
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn xóa tài khoản của nhân viên " + tenNhanVien + " (Tên đăng nhập: " + tenDangNhap + ")?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Thực hiện xóa tài khoản
            TaiKhoanDAO tkDAO = new TaiKhoanDAO();
            boolean ketQua = tkDAO.xoaTaiKhoanTheoTenDangNhap(tenDangNhap);
            
            if (ketQua) {
                JOptionPane.showMessageDialog(this, "Xóa tài khoản thành công!");
                // Cập nhật lại bảng hiển thị
                capNhatBangTaiKhoan();
                // Reset form
                resetData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa tài khoản thất bại!");
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi xóa tài khoản: " + e.getMessage());
        e.printStackTrace();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage());
        e.printStackTrace();
    }
}
    
  private void capNhatTaiKhoan() {
    try {
        // Kiểm tra xem đã chọn tài khoản để cập nhật chưa
        if (currentUsername == null || currentUsername.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần cập nhật từ bảng!");
            return;
        }
        
        // Lấy thông tin mới từ form
        String tenDangNhapMoi = txNgayvaolam1.getText().trim();
        
        // Kiểm tra tên đăng nhập mới
        if (tenDangNhapMoi.isEmpty() || tenDangNhapMoi.equals("Nhập tên đăng nhập")) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không được để trống!");
            return;
        }
        
        // Lấy phân quyền đã chọn
        String selectedPermissionName = cb_txPhanquyen.getSelectedItem().toString();
        String maPhanQuyenMoi = null;
        
        // Nếu đã chọn phân quyền hợp lệ
        if (!selectedPermissionName.equals("-- Chọn phân quyền --")) {
            // Lấy mã phân quyền từ tên đã chọn
            PhanQuyenDAO pqDAO = new PhanQuyenDAO();
            maPhanQuyenMoi = pqDAO.layMaPhanQuyenTuTen(selectedPermissionName);
        }
        
        // Lấy mã tài khoản dựa trên tên đăng nhập hiện tại
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        String maTaiKhoan = tkDAO.layMaTaiKhoanTheoTenDangNhap(currentUsername);
        
        if (maTaiKhoan == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin tài khoản!");
            return;
        }
        
        // Thực hiện cập nhật
        boolean ketQua = tkDAO.capNhatTaiKhoan(maTaiKhoan, tenDangNhapMoi, maPhanQuyenMoi);
        
        if (ketQua) {
            JOptionPane.showMessageDialog(this, "Cập nhật tài khoản thành công!");
            // Cập nhật lại bảng hiển thị
            capNhatBangTaiKhoan();
            // Reset form và dữ liệu hiện tại
            resetData();
            currentUsername = null;
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật tài khoản thất bại! Tên đăng nhập có thể đã tồn tại.");
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật tài khoản: " + e.getMessage());
        e.printStackTrace();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage());
        e.printStackTrace();
    }
}



  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Manv;
    private javax.swing.JLabel Ngayvaolam;
    private javax.swing.JLabel Ngayvaolam1;
    private javax.swing.JLabel Nhomquyen;
    private javax.swing.JLabel Tennv;
    private javax.swing.JButton btnCapnhat;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnXacnhan;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbManv;
    private javax.swing.JComboBox<String> cb_txPhanquyen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbTaikhoan;
    private javax.swing.JTextField txNgayvaolam;
    private javax.swing.JTextField txNgayvaolam1;
    private javax.swing.JTextField txTennv;
    // End of variables declaration//GEN-END:variables
}
