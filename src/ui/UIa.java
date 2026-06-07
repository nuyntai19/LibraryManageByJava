
package ui;
import DTO.PhanQuyenDTO;
import DAO.PhanQuyenDAO;
import java.sql.*;
import java.util.*;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import DTO.TaiKhoanDTO;


public class UIa extends javax.swing.JFrame {

    public CardLayout card = new CardLayout();
    private JButton selectedButton = null;
     private TaiKhoanDTO currentUser = null;
    private boolean hasPermission = false;
    
    public UIa() {
        initComponents();
        Giaodien.setLayout(card);
        Giaodien.add(new Intro(), "Intro");
        Giaodien.add(new SachGUI(), "SachGUI");
        Giaodien.add(new PhieunhapGUI(), "PhieunhapGUI");
        Giaodien.add(new PhieumuonGUI(), "PhieumuonGUI");
        Giaodien.add(new DocgiaGUI(), "DocgiaGUI");
        Giaodien.add(new NhanvienGUI(), "NhanvienGUI");
        Giaodien.add(new PhanquyenGUI(), "PhanquyenGUI");
        Giaodien.add(new ThongkeGUI(), "ThongkeGUI");
        Giaodien.add(new NguonnhapGUI(), "NguonnhapGUI");
        Giaodien.add(new TaikhoanGUI(), "TaikhoanGUI");
        
        card.show(Giaodien, "Intro");
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        decor_qltv = new javax.swing.JPanel();
        Qlythuvien = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txStaff = new javax.swing.JLabel();
        txRole = new javax.swing.JLabel();
        Bang = new javax.swing.JPanel();
        btnSach = new javax.swing.JButton();
        btnPhieunhap = new javax.swing.JButton();
        btnPhieumuon = new javax.swing.JButton();
        btnDocgia = new javax.swing.JButton();
        btnNhanvien = new javax.swing.JButton();
        btnPhanquyen = new javax.swing.JButton();
        btnThongke = new javax.swing.JButton();
        btnNguonnhap = new javax.swing.JButton();
        btnDangxuat = new javax.swing.JButton();
        btnTaikhoan = new javax.swing.JButton();
        Giaodien = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(1400, 700));

        decor_qltv.setBackground(new java.awt.Color(0, 122, 77));
        decor_qltv.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        Qlythuvien.setFont(new java.awt.Font("Arial", 1, 40)); // NOI18N
        Qlythuvien.setForeground(new java.awt.Color(255, 255, 255));
        Qlythuvien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Qlythuvien.setText("Quản lý thư viện");
        Qlythuvien.setPreferredSize(new java.awt.Dimension(1400, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Icojam-Blue-Bits-User-settings.48.png"))); // NOI18N

        txStaff.setBackground(new java.awt.Color(0, 122, 77));
        txStaff.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txStaff.setForeground(new java.awt.Color(255, 255, 255));
        txStaff.setText("ADMIN");

        txRole.setBackground(new java.awt.Color(0, 122, 77));
        txRole.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txRole.setForeground(new java.awt.Color(255, 255, 255));
        txRole.setText("CHỦ QUÁN SÁCH");

        javax.swing.GroupLayout decor_qltvLayout = new javax.swing.GroupLayout(decor_qltv);
        decor_qltv.setLayout(decor_qltvLayout);
        decor_qltvLayout.setHorizontalGroup(
            decor_qltvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decor_qltvLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(decor_qltvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txRole, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(Qlythuvien, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        decor_qltvLayout.setVerticalGroup(
            decor_qltvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decor_qltvLayout.createSequentialGroup()
                .addGroup(decor_qltvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, decor_qltvLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(Qlythuvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(decor_qltvLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txStaff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txRole)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        Bang.setBackground(new java.awt.Color(255, 255, 255));
        Bang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 5, new java.awt.Color(153, 153, 153)));
        Bang.setPreferredSize(new java.awt.Dimension(190, 642));

        btnSach.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnSach.setText("SÁCH");
        btnSach.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242), 3));
        btnSach.setBorderPainted(false);
        btnSach.setFocusPainted(false);
        btnSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Change_Color(evt);
            }
        });
        btnSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenSach(evt);
            }
        });

        btnPhieunhap.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnPhieunhap.setText("PHIẾU NHẬP");
        btnPhieunhap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242), 3));
        btnPhieunhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Change_Color(evt);
            }
        });
        btnPhieunhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenPhieunhap(evt);
            }
        });

        btnPhieumuon.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnPhieumuon.setText("PHIẾU MƯỢN");
        btnPhieumuon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242), 3));
        btnPhieumuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Change_Color(evt);
            }
        });
        btnPhieumuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenPhieumuon(evt);
            }
        });

        btnDocgia.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnDocgia.setText("ĐỘC GIẢ");
        btnDocgia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242), 3));
        btnDocgia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Change_Color(evt);
            }
        });
        btnDocgia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenDocgia(evt);
            }
        });

        btnNhanvien.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnNhanvien.setText("NHÂN VIÊN");
        btnNhanvien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242), 3));
        btnNhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Change_Color(evt);
            }
        });
        btnNhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenNhanvien(evt);
            }
        });

        btnPhanquyen.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnPhanquyen.setText("PHÂN QUYỀN");
        btnPhanquyen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242), 3));
        btnPhanquyen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Change_Color(evt);
            }
        });
        btnPhanquyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenPhanquyen(evt);
            }
        });

        btnThongke.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnThongke.setText("THỐNG KÊ");
        btnThongke.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242), 3));
        btnThongke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Change_Color(evt);
            }
        });
        btnThongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenThongke(evt);
            }
        });

        btnNguonnhap.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnNguonnhap.setText("NGUỒN NHẬP");
        btnNguonnhap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242), 3));
        btnNguonnhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Change_Color(evt);
            }
        });
        btnNguonnhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenNguonnhap(evt);
            }
        });

        btnDangxuat.setBackground(new java.awt.Color(255, 51, 51));
        btnDangxuat.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnDangxuat.setText("ĐĂNG XUẤT");
        btnDangxuat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242), 3));

        btnTaikhoan.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnTaikhoan.setText("TÀI KHOẢN");
        btnTaikhoan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242), 3));
        btnTaikhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Change_Color(evt);
            }
        });
        btnTaikhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenTaikhoan(evt);
            }
        });

        javax.swing.GroupLayout BangLayout = new javax.swing.GroupLayout(Bang);
        Bang.setLayout(BangLayout);
        BangLayout.setHorizontalGroup(
            BangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDocgia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPhieumuon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPhieunhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnNhanvien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPhanquyen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnThongke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnNguonnhap, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
            .addComponent(btnDangxuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTaikhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
        );
        BangLayout.setVerticalGroup(
            BangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BangLayout.createSequentialGroup()
                .addComponent(btnSach, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnPhieunhap, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnPhieumuon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnDocgia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnPhanquyen, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnThongke, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnNguonnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDangxuat, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Giaodien.setBackground(new java.awt.Color(255, 255, 255));
        Giaodien.setPreferredSize(new java.awt.Dimension(1210, 640));
        Giaodien.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(decor_qltv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Bang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Giaodien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(decor_qltv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Bang, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                    .addComponent(Giaodien, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ChuyenSach(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenSach
        card.show(Giaodien, "SachGUI");
    }//GEN-LAST:event_ChuyenSach

    private void ChuyenPhieunhap(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenPhieunhap
        card.show(Giaodien, "PhieunhapGUI");
    }//GEN-LAST:event_ChuyenPhieunhap

    private void ChuyenPhieumuon(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenPhieumuon
        card.show(Giaodien, "PhieumuonGUI");
    }//GEN-LAST:event_ChuyenPhieumuon

    private void ChuyenDocgia(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenDocgia
        card.show(Giaodien, "DocgiaGUI");
    }//GEN-LAST:event_ChuyenDocgia

    private void ChuyenNhanvien(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenNhanvien
        card.show(Giaodien, "NhanvienGUI");
    }//GEN-LAST:event_ChuyenNhanvien

    private void ChuyenPhanquyen(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenPhanquyen
        card.show(Giaodien, "PhanquyenGUI");
    }//GEN-LAST:event_ChuyenPhanquyen

    private void ChuyenThongke(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenThongke
        card.show(Giaodien, "ThongkeGUI");
    }//GEN-LAST:event_ChuyenThongke

    private void ChuyenNguonnhap(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenNguonnhap
        card.show(Giaodien, "NguonnhapGUI");
    }//GEN-LAST:event_ChuyenNguonnhap

    private void Change_Color(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Change_Color
        JButton clickedButton = (JButton) evt.getSource();

        if (selectedButton != null && selectedButton != clickedButton) {
            selectedButton.setBackground(Color.white);
        }

        selectedButton = clickedButton;
        selectedButton.setBackground(new Color(0,122,77));
    }//GEN-LAST:event_Change_Color

    private void ChuyenTaikhoan(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenTaikhoan
        card.show(Giaodien, "TaikhoanGUI");
    }//GEN-LAST:event_ChuyenTaikhoan

    public void setTaiKhoan(TaiKhoanDTO taiKhoan, String tenNhanVien) {
        this.currentUser = taiKhoan;
        
        // Hiển thị thông tin người dùng
        if (tenNhanVien != null && !tenNhanVien.isEmpty()) {
            txStaff.setText(tenNhanVien);
        } else {
            txStaff.setText("Không xác định");
        }
        
        // Thiết lập quyền hạn dựa vào mã phân quyền
        if (taiKhoan.getMaPhanQuyen() == null || taiKhoan.getMaPhanQuyen().isEmpty()) {
            txRole.setText("Cụ Lao Công");
            // Vô hiệu hóa tất cả nút chức năng nếu chưa phân quyền
            disableAllFunctionButtons();
        } else {
            try {
                // Lấy tên phân quyền từ mã phân quyền
                PhanQuyenDAO pqDAO = new PhanQuyenDAO();
                ArrayList<PhanQuyenDTO> permissions = pqDAO.layDanhSachPhanQuyenTheoMa(taiKhoan.getMaPhanQuyen());
                if (!permissions.isEmpty()) {
                    txRole.setText(permissions.get(0).getTenPhanQuyen());
                } else {
                    txRole.setText("CHỦ QUÁN SÁCH");
                }
                // Cấu hình quyền truy cập cho các nút dựa trên phân quyền
                setupButtonPermissions();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi tải phân quyền: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    // Phương thức mới để thiết lập quyền cho các nút dựa trên phân quyền của người dùng hiện tại
    private void setupButtonPermissions() {
        try {
            if (currentUser != null && currentUser.getMaPhanQuyen() != null) {
                PhanQuyenDAO pqDAO = new PhanQuyenDAO();
                // Lấy danh sách mã chức năng mà người dùng có quyền
                ArrayList<String> userFunctions = pqDAO.layDanhSachChucNang(currentUser.getMaPhanQuyen());
                Set<String> userFunctionsSet = new HashSet<>(userFunctions);
                
                // Thiết lập quyền cho từng nút dựa trên mã chức năng
                setupButtonPermission(btnSach, "Q1", userFunctionsSet, this::ChuyenSach);
                setupButtonPermission(btnNhanvien, "Q2", userFunctionsSet, this::ChuyenNhanvien);
                setupButtonPermission(btnPhieunhap, "Q3", userFunctionsSet, this::ChuyenPhieunhap);
                setupButtonPermission(btnDocgia, "Q4", userFunctionsSet, this::ChuyenDocgia);
                setupButtonPermission(btnPhanquyen, "Q5", userFunctionsSet, this::ChuyenPhanquyen);
                setupButtonPermission(btnPhieumuon, "Q6", userFunctionsSet, this::ChuyenPhieumuon);
                setupButtonPermission(btnTaikhoan, "Q7", userFunctionsSet, this::ChuyenTaikhoan);
                setupButtonPermission(btnThongke, "Q8", userFunctionsSet, this::ChuyenThongke);
                setupButtonPermission(btnNguonnhap, "Q9", userFunctionsSet, this::ChuyenNguonnhap);
            } else {
                // Nếu không có phân quyền, vô hiệu hóa tất cả các nút
                disableAllFunctionButtons();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thiết lập quyền cho các nút: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
     // Phương thức để thiết lập quyền cho một nút cụ thể
    private void setupButtonPermission(JButton button, String requiredFunction, Set<String> userFunctions, ButtonAction action) {
        // Xóa tất cả action listeners cũ
        for (java.awt.event.ActionListener listener : button.getActionListeners()) {
            button.removeActionListener(listener);
        }
        
        // Thêm action listener mới kiểm tra quyền
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (!userFunctions.contains(requiredFunction)) {
                    JOptionPane.showMessageDialog(UIa.this, 
                        "Bạn không có quyền để xử lý chức năng này!", 
                        "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    // Nếu có quyền, thực hiện hành động
                    action.execute(evt);
                }
            }
        });
    }
    
    // Interface để xử lý hành động nút khi có quyền
    private interface ButtonAction {
        void execute(java.awt.event.ActionEvent evt);
    }
    
    private void disableAllFunctionButtons() {
        JButton[] buttons = {btnSach, btnNhanvien, btnPhieunhap, btnDocgia, 
                           btnPhanquyen, btnPhieumuon, btnTaikhoan, btnThongke, btnNguonnhap};
        
        for (JButton button : buttons) {
            // Xóa tất cả action listeners cũ
            for (java.awt.event.ActionListener listener : button.getActionListeners()) {
                button.removeActionListener(listener);
            }
            
            // Thêm action listener mới hiển thị thông báo không có quyền
            button.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    JOptionPane.showMessageDialog(UIa.this, 
                        "Bạn không có quyền để xử lý chức năng này!", 
                        "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            });
        }
    }

private void addSpecificPermissionCheckToButton(JButton button, String requiredFunction, Set<String> userFunctions) {

    java.awt.event.ActionListener[] oldListeners = button.getActionListeners();
    
    // Remove all old listeners
    for (java.awt.event.ActionListener listener : oldListeners) {
        button.removeActionListener(listener);
    }
    
    button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (!userFunctions.contains(requiredFunction)) {
                JOptionPane.showMessageDialog(UIa.this, 
                        "Bạn không có quyền để xử lý chức năng này!", 
                        "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            } else {
                // If has permission, call the old listeners
                for (java.awt.event.ActionListener listener : oldListeners) {
                    listener.actionPerformed(evt);
                }
            }
        }
    });
}

    
    // Thêm kiểm tra quyền cho một nút
    private void addPermissionCheckToButton(JButton button) {
        // Lưu lại ActionListener cũ
        java.awt.event.ActionListener[] oldListeners = button.getActionListeners();
        
        // Xóa tất cả ActionListener cũ
        for (java.awt.event.ActionListener listener : oldListeners) {
            button.removeActionListener(listener);
        }
        
        // Thêm ActionListener mới kiểm tra quyền
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (!hasPermission) {
                    JOptionPane.showMessageDialog(UIa.this, 
                            "Bạn không có quyền để xử lý chức năng này!", 
                            "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    // Nếu có quyền, gọi lại ActionListener cũ
                    for (java.awt.event.ActionListener listener : oldListeners) {
                        listener.actionPerformed(evt);
                    }
                }
            }
        });
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
    private javax.swing.JPanel Bang;
    private javax.swing.JPanel Giaodien;
    private javax.swing.JLabel Qlythuvien;
    private javax.swing.JButton btnDangxuat;
    private javax.swing.JButton btnDocgia;
    private javax.swing.JButton btnNguonnhap;
    private javax.swing.JButton btnNhanvien;
    private javax.swing.JButton btnPhanquyen;
    private javax.swing.JButton btnPhieumuon;
    private javax.swing.JButton btnPhieunhap;
    private javax.swing.JButton btnSach;
    private javax.swing.JButton btnTaikhoan;
    private javax.swing.JButton btnThongke;
    private javax.swing.JPanel decor_qltv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txRole;
    private javax.swing.JLabel txStaff;
    // End of variables declaration//GEN-END:variables
}
