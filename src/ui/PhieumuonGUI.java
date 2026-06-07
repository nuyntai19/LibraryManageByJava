
package ui;
import DTO.DocGiaDTO;
import BUS.DocGiaBUS;
//import DAO.DocGiaDAO;
import BUS.PhieuMuonBUS;
import BUS.SachBUS;
import DAO.PhieuMuonDAO;
import DAO.PhieuPhatDAO;
import DAO.SachDAO;
import DTO.SachDTO;
import DTO.PhieuMuonDTO;
import DTO.PhieuPhatDTO;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class PhieumuonGUI extends javax.swing.JPanel {

    PhieuMuonBUS bus = new PhieuMuonBUS();
ArrayList<PhieuMuonDTO> danhSach = bus.getDanhSachPhieuMuon();
PhieuMuonDTO x = new PhieuMuonDTO();
//DocGiaBUS docGiaBUS = new DocGiaBUS();
ArrayList<DocGiaDTO> dsDocGia;
DocGiaBUS docGiaBUS;
//ArrayList<DocGiaDTO> dsDocGia = docGiaBUS.layDanhSachDocGia();
//ArrayList<DocGiaDTO> dsDocGia = docGiaDAO.getList();

    public PhieumuonGUI() {
        initComponents();
        Giaodien.setLayout(new CardLayout());
        Giaodien.add(Phieumuon, "Phieumuon");
        Giaodien.add(Traphat, "Traphat");
        JTableHeader header = tbPhieumuon.getTableHeader(); 
        header.setFont(new Font("Arial", Font.BOLD, 16));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        JTableHeader headerTp = tbTraphat.getTableHeader(); 
        headerTp.setFont(new Font("Arial", Font.BOLD, 16));
        ((DefaultTableCellRenderer) headerTp.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ViewtablePhieuMuon();
    }
public String chuyenTrangThai(int trangThai) {
    switch (trangThai) {
        case 0:
            return "Đã trả";
        case 1:
            return "Đang mượn";
        case 2:
            return "Đặt trước";
        case 3:
            return "Đã quá hạn";
        case 4:
            return "Sách hỏng";
        
        default:
            return "Trạng thái không hợp lệ";
    }
}


public void ViewtablePhieuMuon() {
    DefaultTableModel model = (DefaultTableModel) tbPhieumuon.getModel();
    model.setRowCount(0); // Xóa dữ liệu cũ

    PhieuMuonBUS bus = new PhieuMuonBUS();
    ArrayList<PhieuMuonDTO> danhSach = bus.getDanhSachPhieuMuon();

    if (danhSach == null || danhSach.isEmpty()) {
        // Nếu danh sách rỗng hoặc null, hiển thị một thông báo hoặc hành động khác
        JOptionPane.showMessageDialog(null, "Không có dữ liệu phiếu mượn!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    for (PhieuMuonDTO pm : danhSach) {
        // Kiểm tra và định dạng từng ngày nếu không null
        String ngayMuon = (pm.getNgayMuon() != null) ? pm.getNgayMuon().format(formatter) : "---";
        String hanTra = (pm.getHanTra() != null) ? pm.getHanTra().format(formatter) : "---";
        String ngayTraThucTe = (pm.getNgayTraThucTe() != null) ? pm.getNgayTraThucTe().format(formatter) : "Chưa trả";

        model.addRow(new Object[]{
            pm.getMaPhieuMuon(),
            pm.getMaDocGia(),
            pm.getMaSach(),
            ngayMuon,
            hanTra,
            ngayTraThucTe,
            chuyenTrangThai(pm.getTrangThai()),
            pm.getTienPhat()
        });
    }
}

public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            javax.swing.JFrame frame = new javax.swing.JFrame("Quản lý phiếu mượn");
            frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600); // điều chỉnh kích thước theo ý bạn
            frame.setLocationRelativeTo(null); // hiển thị giữa màn hình
            
            PhieumuonGUI gui = new PhieumuonGUI();
            frame.setContentPane(gui);
            frame.setVisible(true);
        }
    });
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnCapnhat = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        PanThongtin = new javax.swing.JPanel();
        MaDG = new javax.swing.JLabel();
        MaSach = new javax.swing.JLabel();
        NgaytraTT = new javax.swing.JLabel();
        txtMaDG = new javax.swing.JTextField();
        Ngaymuon = new javax.swing.JLabel();
        NgaytraDK = new javax.swing.JLabel();
        txtNgaytraDK = new com.toedter.calendar.JDateChooser();
        txtNgaytraTT = new com.toedter.calendar.JDateChooser();
        txtNgaymuon = new com.toedter.calendar.JDateChooser();
        txtMaSach = new javax.swing.JTextField();
        TenDG = new javax.swing.JLabel();
        txtTenDG = new javax.swing.JTextField();
        TenSach = new javax.swing.JLabel();
        txtTenSach = new javax.swing.JTextField();
        btnReset = new javax.swing.JButton();
        Giaodien = new javax.swing.JPanel();
        Phieumuon = new javax.swing.JPanel();
        PanTimkiem1 = new javax.swing.JPanel();
        Timkiem = new javax.swing.JLabel();
        txtTimkiem = new javax.swing.JTextField();
        btnTimkiem = new javax.swing.JButton();
        cbLocTrangthai = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPhieumuon = new javax.swing.JTable();
        Traphat = new javax.swing.JPanel();
        PanTimkiem2 = new javax.swing.JPanel();
        Timkiem2 = new javax.swing.JLabel();
        txtTimkiem2 = new javax.swing.JTextField();
        btnTimkiem2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTraphat = new javax.swing.JTable();
        btnTrangthai = new javax.swing.JButton();
        btnTrangthai1 = new javax.swing.JButton();
        btnXacnhanmuon = new javax.swing.JButton();

        jPanel1.setPreferredSize(new java.awt.Dimension(1210, 640));

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

        PanThongtin.setBackground(new java.awt.Color(255, 255, 255));
        PanThongtin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));

        MaDG.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        MaDG.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        MaDG.setText("Mã ĐG:");

        MaSach.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        MaSach.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        MaSach.setText("Mã sách:");

        NgaytraTT.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        NgaytraTT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        NgaytraTT.setText("Ngày trả thực tế:");

        txtMaDG.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        Ngaymuon.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Ngaymuon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Ngaymuon.setText("Ngày mượn:");

        NgaytraDK.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        NgaytraDK.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        NgaytraDK.setText("Ngày trả dự kiến:");

        txtMaSach.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        TenDG.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TenDG.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TenDG.setText("Tên độc giả:");

        txtTenDG.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        TenSach.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        TenSach.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TenSach.setText("Tên sách:");

        txtTenSach.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnReset.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanThongtinLayout = new javax.swing.GroupLayout(PanThongtin);
        PanThongtin.setLayout(PanThongtinLayout);
        PanThongtinLayout.setHorizontalGroup(
            PanThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanThongtinLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PanThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanThongtinLayout.createSequentialGroup()
                        .addGroup(PanThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(MaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MaDG, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(PanThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaDG, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanThongtinLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(TenDG)
                        .addGap(28, 28, 28)
                        .addComponent(txtTenDG, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(PanThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(NgaytraTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Ngaymuon, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NgaytraDK, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(PanThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNgaytraDK, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaytraTT, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaymuon, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanThongtinLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReset)
                .addContainerGap())
        );
        PanThongtinLayout.setVerticalGroup(
            PanThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanThongtinLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PanThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanThongtinLayout.createSequentialGroup()
                        .addGroup(PanThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNgaymuon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Ngaymuon, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(PanThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNgaytraDK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NgaytraDK, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(PanThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NgaytraTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgaytraTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(PanThongtinLayout.createSequentialGroup()
                        .addGroup(PanThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MaDG))
                        .addGap(20, 20, 20)
                        .addGroup(PanThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TenDG))
                        .addGap(20, 20, 20)
                        .addGroup(PanThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MaSach)
                            .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addGroup(PanThongtinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TenSach)
                    .addComponent(txtTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(btnReset)
                .addContainerGap())
        );

        Giaodien.setBackground(new java.awt.Color(255, 255, 255));
        Giaodien.setLayout(new java.awt.CardLayout());

        PanTimkiem1.setBackground(new java.awt.Color(0, 122, 77));

        Timkiem.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        Timkiem.setForeground(new java.awt.Color(255, 255, 255));
        Timkiem.setText("Tìm kiếm:");

        txtTimkiem.setBackground(new java.awt.Color(238, 242, 240));
        txtTimkiem.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        btnTimkiem.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnTimkiem.setForeground(new java.awt.Color(0, 122, 77));
        btnTimkiem.setText("Tìm kiếm");
        btnTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemActionPerformed(evt);
            }
        });

        cbLocTrangthai.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        cbLocTrangthai.setForeground(new java.awt.Color(0, 122, 77));
        cbLocTrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đặt trước", "Đang mượn", "Đã trả", "Đã quá hạn", "Mất/ Hỏng" }));

        javax.swing.GroupLayout PanTimkiem1Layout = new javax.swing.GroupLayout(PanTimkiem1);
        PanTimkiem1.setLayout(PanTimkiem1Layout);
        PanTimkiem1Layout.setHorizontalGroup(
            PanTimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanTimkiem1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Timkiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(cbLocTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(438, Short.MAX_VALUE))
        );
        PanTimkiem1Layout.setVerticalGroup(
            PanTimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanTimkiem1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanTimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanTimkiem1Layout.createSequentialGroup()
                        .addComponent(cbLocTrangthai)
                        .addGap(1, 1, 1))
                    .addGroup(PanTimkiem1Layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addGroup(PanTimkiem1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Timkiem)
                            .addComponent(btnTimkiem)
                            .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10))
        );

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        tbPhieumuon.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        tbPhieumuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu", "Mã thẻ độc giả", "Mã sách", "Ngày mượn", "Hẹn trả dự kiến", "Ngày trả thực tế", "Trạng thái", "Tiền phạt"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
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
        tbPhieumuon.setGridColor(new java.awt.Color(0, 0, 0));
        tbPhieumuon.setRowHeight(35);
        tbPhieumuon.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tbPhieumuon.setSelectionForeground(new java.awt.Color(0, 51, 51));
        tbPhieumuon.setShowVerticalLines(true);
        tbPhieumuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPhieumuonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbPhieumuon);

        javax.swing.GroupLayout PhieumuonLayout = new javax.swing.GroupLayout(Phieumuon);
        Phieumuon.setLayout(PhieumuonLayout);
        PhieumuonLayout.setHorizontalGroup(
            PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PhieumuonLayout.createSequentialGroup()
                .addGroup(PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanTimkiem1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 53, Short.MAX_VALUE))
        );
        PhieumuonLayout.setVerticalGroup(
            PhieumuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PhieumuonLayout.createSequentialGroup()
                .addComponent(PanTimkiem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 38, Short.MAX_VALUE))
        );

        Giaodien.add(Phieumuon, "Phieumuon");

        PanTimkiem2.setBackground(new java.awt.Color(0, 122, 77));

        Timkiem2.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        Timkiem2.setForeground(new java.awt.Color(255, 255, 255));
        Timkiem2.setText("Tìm kiếm:");

        txtTimkiem2.setBackground(new java.awt.Color(238, 242, 240));
        txtTimkiem2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtTimkiem2.setText("jTextField9");

        btnTimkiem2.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnTimkiem2.setForeground(new java.awt.Color(0, 122, 77));
        btnTimkiem2.setText("Tìm kiếm");

        javax.swing.GroupLayout PanTimkiem2Layout = new javax.swing.GroupLayout(PanTimkiem2);
        PanTimkiem2.setLayout(PanTimkiem2Layout);
        PanTimkiem2Layout.setHorizontalGroup(
            PanTimkiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanTimkiem2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Timkiem2)
                .addGap(18, 18, 18)
                .addComponent(txtTimkiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimkiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(644, Short.MAX_VALUE))
        );
        PanTimkiem2Layout.setVerticalGroup(
            PanTimkiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanTimkiem2Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(PanTimkiem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Timkiem2)
                    .addComponent(txtTimkiem2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimkiem2))
                .addGap(10, 10, 10))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbTraphat.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        tbTraphat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"110", "df", "ckaos", "a123", "CNTT", "23", "100000"},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu mượn", "Mã độc giả", "Tên độc giả", "Mã sách", "Tên sách", "Số ngày quá hạn", "Tiền phạt"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tbTraphat.setGridColor(new java.awt.Color(0, 0, 0));
        tbTraphat.setRowHeight(35);
        tbTraphat.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tbTraphat.setSelectionForeground(new java.awt.Color(0, 51, 51));
        tbTraphat.setShowVerticalLines(true);
        jScrollPane1.setViewportView(tbTraphat);

        javax.swing.GroupLayout TraphatLayout = new javax.swing.GroupLayout(Traphat);
        Traphat.setLayout(TraphatLayout);
        TraphatLayout.setHorizontalGroup(
            TraphatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanTimkiem2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        TraphatLayout.setVerticalGroup(
            TraphatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TraphatLayout.createSequentialGroup()
                .addComponent(PanTimkiem2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        Giaodien.add(Traphat, "Traphat");

        btnTrangthai.setBackground(new java.awt.Color(255, 255, 204));
        btnTrangthai.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnTrangthai.setText("\"Đã trả\"");
        btnTrangthai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        btnTrangthai.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnTrangthai.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnTrangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenPhieumuon(evt);
            }
        });

        btnTrangthai1.setBackground(new java.awt.Color(255, 102, 102));
        btnTrangthai1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnTrangthai1.setText("\"Hỏng\"");
        btnTrangthai1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        btnTrangthai1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnTrangthai1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnTrangthai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangthai1ChuyenPhieumuon(evt);
            }
        });

        btnXacnhanmuon.setBackground(new java.awt.Color(102, 255, 51));
        btnXacnhanmuon.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        btnXacnhanmuon.setText("Xác nhận mượn");
        btnXacnhanmuon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        btnXacnhanmuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacnhanmuonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PanThongtin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnTrangthai1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCapnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(btnXacnhanmuon))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(Giaodien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnCapnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTrangthai1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXacnhanmuon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PanThongtin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Giaodien, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
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

    private void ChuyenPhieumuon(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenPhieumuon
        CardLayout card = (CardLayout) Giaodien.getLayout();
        card.show(Giaodien, "Phieumuon");

        int row = tbPhieumuon.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu mượn để đánh dấu đã trả.");
            return;
        }

        String maPhieuMuon = tbPhieumuon.getValueAt(row, 0).toString();
        String maSach = tbPhieumuon.getValueAt(row, 2).toString(); // dùng đúng biến row

        SachDAO sachDAO = new SachDAO();
        SachDTO sach = sachDAO.timSachTheoMa(maSach);

        // Tăng số lượng sách trước
        sach.setSoLuong(sach.getSoLuong() + 1);
        try {
            sachDAO.capNhatSoLuong(sach);
        } catch (SQLException ex) {
            Logger.getLogger(PhieumuonGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Sau đó đánh dấu đã trả
        PhieuMuonBUS phieuMuonBUS = new PhieuMuonBUS();
        if (phieuMuonBUS.danhDauDaTra(maPhieuMuon)) {
            JOptionPane.showMessageDialog(this, "Đã đánh dấu phiếu mượn là đã trả.");
            loadTablePhieuMuon();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật trạng thái phiếu mượn thất bại.");
        }

    }//GEN-LAST:event_ChuyenPhieumuon

    private void btnTrangthai1ChuyenPhieumuon(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangthai1ChuyenPhieumuon
                                            

int row = tbPhieumuon.getSelectedRow();
PhieuMuonBUS phieuMuonBUS = new PhieuMuonBUS();

if (row == -1) {
    JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu mượn để cập nhật.");
    return;
}

try {
    // Lấy mã phiếu mượn từ bảng
    String maPhieuMuon = tbPhieumuon.getValueAt(row, 0).toString();

    String maDG = txtMaDG.getText().trim();
    String maSach = txtMaSach.getText().trim();

    LocalDate ngayMuon = txtNgaymuon.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate hanTra = txtNgaytraDK.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    
    LocalDate ngayTraThucTe = LocalDate.now();

    
    int trangThai = 4;           // 4 = Hỏng
    double tienPhat = 50000;     // Tiền phạt 50.000 VNĐ

    PhieuMuonDTO pm = new PhieuMuonDTO(maPhieuMuon, maDG, maSach, ngayMuon, hanTra, ngayTraThucTe, trangThai, tienPhat);

    if (phieuMuonBUS.suaPhieuMuon(pm)) {
        JOptionPane.showMessageDialog(this, "Đã đánh dấu sách hỏng!");
        loadTablePhieuMuon();
    } else {
        JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
    }

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this,"Lỗi cập nhật: " + e.getMessage());
}

    }//GEN-LAST:event_btnTrangthai1ChuyenPhieumuon

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
try {
        PhieuMuonDAO dao = new PhieuMuonDAO();
        PhieuMuonBUS bus = new PhieuMuonBUS();

        String maPhieuMoi = bus.taoMaPhieuMoi(); 

        // Lấy dữ liệu từ giao diện
        String maDG = txtMaDG.getText();
        String maSach = txtMaSach.getText();
        LocalDate ngayMuon = txtNgaymuon.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate hanTra = txtNgaytraDK.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ngayTraThucTe = (txtNgaytraTT.getDate() != null) ?
        txtNgaytraTT.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;

        int trangThai = 1;  
        double tienPhat = 0;  

        PhieuMuonDTO pm = new PhieuMuonDTO(maPhieuMoi, maDG, maSach, ngayMuon, hanTra, ngayTraThucTe, trangThai, tienPhat);

        boolean success = bus.themPhieuMuon(pm);
        if (success) {
            JOptionPane.showMessageDialog(this, "Thêm phiếu mượn thành công!");
            
        } else {
            JOptionPane.showMessageDialog(this, "Không thể thêm phiếu mượn!");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
    }  
    }//GEN-LAST:event_btnThemActionPerformed

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void tbPhieumuonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPhieumuonMouseClicked
     int row = tbPhieumuon.getSelectedRow();
    PhieuPhatDAO phieuPhatDAO = new PhieuPhatDAO();
    PhieuMuonDAO dao = new PhieuMuonDAO();

    if (row != -1) {
        String maPhieuMuon = tbPhieumuon.getValueAt(row, 0).toString();
        String trangThai = tbPhieumuon.getValueAt(row, 6).toString();

        // Nếu trạng thái là "Đã quá hạn"
        if (trangThai.equalsIgnoreCase("Đã quá hạn")) {
            if (!phieuPhatDAO.daCoPhieuPhat(maPhieuMuon)) {
                // Tạo phiếu phạt mới
                String maPhieuPhat = "PP" + System.currentTimeMillis();
              PhieuMuonDTO pm = dao.timTheoMa(maPhieuMuon);

                if (!danhSach.isEmpty()) {
                  

                    double tienPhat = tinhTienPhat(pm); 

                    PhieuPhatDTO pp = new PhieuPhatDTO(maPhieuPhat, maPhieuMuon, tienPhat);
                    phieuPhatDAO.Them(pp);

                    JOptionPane.showMessageDialog(null, 
                        "Đã tạo phiếu phạt cho phiếu mượn " + maPhieuMuon + 
                        "\nMã phiếu phạt: " + maPhieuPhat +
                        "\nTiền phạt: " + tienPhat + " VND", 
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String strNgayMuon = tbPhieumuon.getValueAt(row, 3).toString();
        String strHanTra = tbPhieumuon.getValueAt(row, 4).toString();
        String strNgayTraThucTe = tbPhieumuon.getValueAt(row, 5).toString();
       
       
// lay ten doc gia tu ma doc gia


        //DocGiaBUS docGiaBUS = new DocGiaBUS();
            //private ArrayList<DocGiaDTO> dsDocGia;
            //private DocGiaBUS docGiaBUS;
        //ArrayList<DocGiaDTO> dsDocGia = docGiaBUS.layDanhSachDocGia();
        //ArrayList<DocGiaDTO> dsDocGia = docGiaDAO.getList();
         String MaDG = tbPhieumuon.getValueAt(row, 1).toString();  // Cột 1 là Mã độc giả
        docGiaBUS = new DocGiaBUS();
        try {
            dsDocGia = docGiaBUS.layDanhSachDocGia();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy danh sách độc giả: " + e.getMessage());
        }
        for (DocGiaDTO dg : dsDocGia) {
            if (dg.getMaDG().equals(MaDG)) {
                txtTenDG.setText(dg.getTenDG()); 
            break;
    }
}
        //lay ten sach tu ma sach
        String MaS = tbPhieumuon.getValueAt(row, 2).toString();
        SachBUS sachBUS = new SachBUS();
        ArrayList<SachDTO> dsSach = new ArrayList<>();
        try {
            dsSach = new ArrayList<>(sachBUS.layDanhSachSach()); // Ensure conversion to ArrayList
            for (SachDTO dg : dsSach) {
                if (dg.getMaSach().equals(MaS)) {
                    txtTenSach.setText(dg.getTenSach());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy danh sách sách: " + e.getMessage());
        }
        

        try {
            // Chuyển từ String sang Date
            Date ngayMuon = sdf.parse(strNgayMuon);
            Date hanTra = sdf.parse(strHanTra);
           
            // Set lên JDateChooser
            txtNgaymuon.setDate(ngayMuon);
            txtNgaytraDK.setDate(hanTra);
          
            if (!strNgayTraThucTe.equalsIgnoreCase("Chưa trả")) {
                Date ngayTraThucTe = sdf.parse(strNgayTraThucTe);
                txtNgaytraTT.setDate(ngayTraThucTe);
            } else {
                txtNgaytraTT.setDate(null); // hoặc để trống nếu chưa trả
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Set các JTextField khác
        txtMaDG.setText(tbPhieumuon.getValueAt(row, 1).toString());
        txtMaSach.setText(tbPhieumuon.getValueAt(row, 2).toString());
       

    }

    }//GEN-LAST:event_tbPhieumuonMouseClicked

    
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
 
        try {
        
        loadTablePhieuMuon();  

        // Xóa trắng các trường nhập liệu
        txtMaDG.setText("");
        txtTenDG.setText("");
        txtMaSach.setText("");
        txtTenSach.setText("");
        txtNgaymuon.setDate(null);
        txtNgaytraDK.setDate(null);
        txtNgaytraTT.setDate(null);

       

        JOptionPane.showMessageDialog(this, "Đã reset!");
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi reset: " + e.getMessage());
    }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnCapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapnhatActionPerformed

       int row = tbPhieumuon.getSelectedRow();
PhieuMuonBUS phieuMuonBUS = new PhieuMuonBUS();

if (row == -1) {
    JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu mượn để cập nhật.");
    return;
}

try {
    // Lấy mã phiếu mượn từ bảng
    String maPhieuMuon = tbPhieumuon.getValueAt(row, 0).toString();

    String maDG = txtMaDG.getText().trim();
    String maSach = txtMaSach.getText().trim();

    LocalDate ngayMuon = txtNgaymuon.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate hanTra = txtNgaytraDK.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    LocalDate ngayTraThucTe = null;
    if (txtNgaytraTT.getDate() != null) {
        ngayTraThucTe = txtNgaytraTT.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    int trangThai = 1;
    double tienPhat = 0.0;

    // ✅ Kiểm tra nếu trả muộn
    if (ngayTraThucTe != null && ngayTraThucTe.isAfter(hanTra)) {
        long soNgayTre = ChronoUnit.DAYS.between(hanTra, ngayTraThucTe);
        tienPhat = soNgayTre * 20000; // 20k/ngày trễ
        trangThai = 3; // Quá hạn
        
    }

    PhieuMuonDTO pm = new PhieuMuonDTO(maPhieuMuon, maDG, maSach, ngayMuon, hanTra, ngayTraThucTe, trangThai, tienPhat);

    if (phieuMuonBUS.suaPhieuMuon(pm)) {
        JOptionPane.showMessageDialog(this, "Cập nhật trạng thái thành công!");
        loadTablePhieuMuon();
    } else {
        JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
    }

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Lỗi cập nhật: " + e.getMessage());
}

    }//GEN-LAST:event_btnCapnhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
//     int row = tbPhieumuon.getSelectedRow();
//     PhieuMuonBUS phieuMuonBUS = new PhieuMuonBUS();
//    if (row == -1) {
//        JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu mượn để xóa.");
//        return;
//    }
//
//    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
//    if (confirm == JOptionPane.YES_OPTION) {
//        try {
//            String maPhieuMuon = tbPhieumuon.getValueAt(row, 0).toString();
//            if (phieuMuonBUS.xoaPhieuMuon(maPhieuMuon)) {
//                JOptionPane.showMessageDialog(this, "Xóa thành công!");
//                loadTablePhieuMuon();
//            } else {
//                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + e.getMessage());
//        }
//    }
int row = tbPhieumuon.getSelectedRow();
    PhieuMuonBUS phieuMuonBUS = new PhieuMuonBUS();
    
    // Kiểm tra xem người dùng đã chọn phiếu mượn hay chưa
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu mượn để xóa.");
        return;
    }

    // Xác nhận hành động xóa
    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        try {
            // Lấy mã phiếu mượn từ bảng
            String maPhieuMuon = tbPhieumuon.getValueAt(row, 0).toString();
            
            // Thực hiện xóa phiếu mượn
            boolean isDeleted = phieuMuonBUS.xoaPhieuMuon(maPhieuMuon);
            
            if (isDeleted) {
                // Nếu xóa thành công, làm mới bảng và hiển thị thông báo
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                loadTablePhieuMuon();  // Làm mới bảng sau khi xóa
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemActionPerformed
// try {
//        String tuKhoa = txtTimkiem.getText().trim();
//        String trangThaiStr = (String) cbLocTrangthai.getSelectedItem();
//        Integer trangThai = null;
//
//        if (!"Tất cả".equals(trangThaiStr)) {
//            switch (trangThaiStr) {
//                case "Đã trả" -> trangThai = 0;
//                case "Đang mượn" -> trangThai = 1;
//                case "Đặt trước" -> trangThai = 2;
//                case "Đã quá hạn" -> trangThai = 3;
//                case "Mất/ Hỏng" -> trangThai = 4;
//            }
//        }
//
//        PhieuMuonDAO dao = new PhieuMuonDAO();
//        ArrayList<PhieuMuonDTO> danhSachGoc = dao.getAllPhieuMuonDTO();
//        ArrayList<PhieuMuonDTO> ketQua = new ArrayList<>();
//
//        for (PhieuMuonDTO pm : danhSachGoc) {
//            boolean match = true;
//
//            // Trường hợp 1: có nhập từ khóa
//            if (!tuKhoa.isEmpty()) {
//                boolean matchMaPM = pm.getMaPhieuMuon().toLowerCase().contains(tuKhoa.toLowerCase());
//                boolean matchMaDG = pm.getMaDocGia().toLowerCase().contains(tuKhoa.toLowerCase());
//                boolean matchMaSach = pm.getMaSach().toLowerCase().contains(tuKhoa.toLowerCase());
//
//                if (!(matchMaPM || matchMaDG || matchMaSach)) match = false;
//            }
//
//            // Trường hợp 2: không nhập từ khóa, chỉ lọc theo trạng thái
//            if (tuKhoa.isEmpty() && trangThai != null && pm.getTrangThai() != trangThai) {
//                match = false;
//            }
//
//            if (match) ketQua.add(pm);
//        }
//
//        // Đổ dữ liệu vào bảng
//        DefaultTableModel model = (DefaultTableModel) tbPhieumuon.getModel();
//        model.setRowCount(0);
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        for (PhieuMuonDTO pm : ketQua) {
//            String ngayMuonStr = (pm.getNgayMuon() != null) ? pm.getNgayMuon().format(dtf) : "N/A";
//            String hanTraStr = (pm.getHanTra() != null) ? pm.getHanTra().format(dtf) : "N/A";
//            String ngayTraStr = (pm.getNgayTraThucTe() != null) ? pm.getNgayTraThucTe().format(dtf) : "Chưa trả";
//
//            model.addRow(new Object[]{
//                pm.getMaPhieuMuon(),
//                pm.getMaDocGia(),
//                pm.getMaSach(),
//                ngayMuonStr,
//                hanTraStr,
//                ngayTraStr,
//                chuyenTrangThai(pm.getTrangThai()),
//                pm.getTienPhat()
//            });
//        }
//
//    } catch (Exception e) {
//        e.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm: " + e.getMessage());
//    }


        try {
    String tuKhoa = txtTimkiem.getText().trim();
    String trangThaiStr = (String) cbLocTrangthai.getSelectedItem();
    Integer trangThai = null;

    // Chuyển chuỗi trạng thái thành số
    if (!"Tất cả".equals(trangThaiStr)) {
        switch (trangThaiStr) {
            case "Đã trả" -> trangThai = 0;
            case "Đang mượn" -> trangThai = 1;
            case "Đặt trước" -> trangThai = 2;
            case "Đã quá hạn" -> trangThai = 3;
            case "Mất/ Hỏng" -> trangThai = 4;
        }
    }

    PhieuMuonDAO dao = new PhieuMuonDAO();
    ArrayList<PhieuMuonDTO> danhSachGoc = dao.getAllPhieuMuonDTO();
    ArrayList<PhieuMuonDTO> ketQua = new ArrayList<>();

    for (PhieuMuonDTO pm : danhSachGoc) {
        boolean match = true;

        // Lọc theo từ khóa nếu có
        if (!tuKhoa.isEmpty()) {
            boolean matchMaPM = pm.getMaPhieuMuon().toLowerCase().contains(tuKhoa.toLowerCase());
            boolean matchMaDG = pm.getMaDocGia().toLowerCase().contains(tuKhoa.toLowerCase());
            boolean matchMaSach = pm.getMaSach().toLowerCase().contains(tuKhoa.toLowerCase());

            if (!(matchMaPM || matchMaDG || matchMaSach)) {
                match = false;
            }
        }

        // Lọc theo trạng thái nếu có chọn trạng thái
        if (trangThai != null && pm.getTrangThai() != trangThai) {
            match = false;
        }

        if (match) {
            ketQua.add(pm);
        }
    }

    // Hiển thị lên bảng
    DefaultTableModel model = (DefaultTableModel) tbPhieumuon.getModel();
    model.setRowCount(0);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    for (PhieuMuonDTO pm : ketQua) {
        String ngayMuonStr = (pm.getNgayMuon() != null) ? pm.getNgayMuon().format(dtf) : "N/A";
        String hanTraStr = (pm.getHanTra() != null) ? pm.getHanTra().format(dtf) : "N/A";
        String ngayTraStr = (pm.getNgayTraThucTe() != null) ? pm.getNgayTraThucTe().format(dtf) : "Chưa trả";

        model.addRow(new Object[]{
            pm.getMaPhieuMuon(),
            pm.getMaDocGia(),
            pm.getMaSach(),
            ngayMuonStr,
            hanTraStr,
            ngayTraStr,
            chuyenTrangThai(pm.getTrangThai()),
            pm.getTienPhat()
        });
    }

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm: " + e.getMessage());
}
    }//GEN-LAST:event_btnTimkiemActionPerformed

    private void btnXacnhanmuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacnhanmuonActionPerformed
            int row = tbPhieumuon.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu mượn.");
        return;
    }

    String maPhieuMuon = tbPhieumuon.getValueAt(row, 0).toString();
    String maSach = tbPhieumuon.getValueAt(row, 2).toString();
    String trangThaiStr = tbPhieumuon.getValueAt(row, 6).toString().trim();

    if (!trangThaiStr.equals("Đặt trước")) {
        JOptionPane.showMessageDialog(this, "Chỉ có thể chuyển trạng thái từ 'Đặt trước' sang 'Đang mượn'.");
        return;
    }

    SachDAO sachDAO = new SachDAO();
    SachDTO sach = sachDAO.timSachTheoMa(maSach);

    try {
        sachDAO.capNhatSoLuong(sach);
    } catch (SQLException ex) {
        Logger.getLogger(PhieumuonGUI.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật số lượng sách.");
        return;
    }

    PhieuMuonBUS phieuMuonBUS = new PhieuMuonBUS();
    if (phieuMuonBUS.doiTrangThai(maPhieuMuon, 1)) {
        JOptionPane.showMessageDialog(this, "Đã chuyển sang trạng thái 'Đang mượn'.");
        loadTablePhieuMuon();
    } else {
        JOptionPane.showMessageDialog(this, "Cập nhật trạng thái thất bại.");
    }
    }//GEN-LAST:event_btnXacnhanmuonActionPerformed

private void loadTablePhieuMuon() {
    DefaultTableModel model = (DefaultTableModel) tbPhieumuon.getModel();
    model.setRowCount(0);  // Xóa hết các dòng cũ
    PhieuPhatDAO phieuPhatDAO = new PhieuPhatDAO();
    PhieuMuonDAO dao = new PhieuMuonDAO();
    ArrayList<PhieuMuonDTO> danhSach = dao.getAllPhieuMuonDTO();

    // Dùng formatter cho LocalDate
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    for (PhieuMuonDTO pm : danhSach) {
        String ngayMuonStr = (pm.getNgayMuon() != null) ? pm.getNgayMuon().format(dtf) : "N/A";
        String hanTraStr = (pm.getHanTra() != null) ? pm.getHanTra().format(dtf) : "N/A";
        String ngayTraThucTeStr = (pm.getNgayTraThucTe() != null) ? pm.getNgayTraThucTe().format(dtf) : "Chưa trả";

        model.addRow(new Object[]{
            pm.getMaPhieuMuon(),
            pm.getMaDocGia(),
            pm.getMaSach(),
            ngayMuonStr,
            hanTraStr,
            ngayTraThucTeStr,
            chuyenTrangThai(pm.getTrangThai()),
            pm.getTienPhat()
        });
    }
}

 private double tinhTienPhat(PhieuMuonDTO pm) {
    if (pm.getNgayTraThucTe() == null || pm.getHanTra() == null) return 0;

    long daysLate = ChronoUnit.DAYS.between(pm.getHanTra(), pm.getNgayTraThucTe());
    if (daysLate <= 0) return 0;

    double tienPhatMotNgay = 2000; // Ví dụ: 2000 đồng mỗi ngày trễ
    return daysLate * tienPhatMotNgay;
}
 public void loadTablePhieuPhat(){
      PhieuPhatDAO phieuPhatDAO = new PhieuPhatDAO();
    PhieuMuonDAO dao = new PhieuMuonDAO();
    ArrayList<PhieuMuonDTO> danhSach = dao.getAllPhieuMuonDTO();
     for (PhieuMuonDTO pm : danhSach) {
    if (pm.getTrangThai() == 3) {
        if (!phieuPhatDAO.daCoPhieuPhat(pm.getMaPhieuMuon())) {
            String maPhieuPhat = "PP" + System.currentTimeMillis();
            double tienPhat = tinhTienPhat(pm);

            PhieuPhatDTO pp = new PhieuPhatDTO(maPhieuPhat, pm.getMaPhieuMuon(), tienPhat);
            phieuPhatDAO.Them(pp);

            // Hiển thị thông báo
            JOptionPane.showMessageDialog(null, 
                "Đã tạo phiếu phạt cho phiếu mượn " + pm.getMaPhieuMuon() + 
                "\nMã phiếu phạt: " + maPhieuPhat +
                "\nTiền phạt: " + tienPhat + " VND", 
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

 }
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Giaodien;
    private javax.swing.JLabel MaDG;
    private javax.swing.JLabel MaSach;
    private javax.swing.JLabel Ngaymuon;
    private javax.swing.JLabel NgaytraDK;
    private javax.swing.JLabel NgaytraTT;
    private javax.swing.JPanel PanThongtin;
    private javax.swing.JPanel PanTimkiem1;
    private javax.swing.JPanel PanTimkiem2;
    private javax.swing.JPanel Phieumuon;
    private javax.swing.JLabel TenDG;
    private javax.swing.JLabel TenSach;
    private javax.swing.JLabel Timkiem;
    private javax.swing.JLabel Timkiem2;
    private javax.swing.JPanel Traphat;
    private javax.swing.JButton btnCapnhat;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimkiem;
    private javax.swing.JButton btnTimkiem2;
    private javax.swing.JButton btnTrangthai;
    private javax.swing.JButton btnTrangthai1;
    private javax.swing.JButton btnXacnhanmuon;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbLocTrangthai;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbPhieumuon;
    private javax.swing.JTable tbTraphat;
    private javax.swing.JTextField txtMaDG;
    private javax.swing.JTextField txtMaSach;
    private com.toedter.calendar.JDateChooser txtNgaymuon;
    private com.toedter.calendar.JDateChooser txtNgaytraDK;
    private com.toedter.calendar.JDateChooser txtNgaytraTT;
    private javax.swing.JTextField txtTenDG;
    private javax.swing.JTextField txtTenSach;
    private javax.swing.JTextField txtTimkiem;
    private javax.swing.JTextField txtTimkiem2;
    // End of variables declaration//GEN-END:variables
}
