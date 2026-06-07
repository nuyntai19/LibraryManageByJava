package ui;

import BUS.SachBUS;
import DAO.JDBCUtil;
import DAO.SachDAO;
import DAO.mySQLConnect;
import DTO.DocGiaDTO;
import DTO.PhieuMuonDTO;
import DTO.SachDTO;
import com.sun.jdi.connect.spi.Connection;
import java.awt.CardLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.beans.Statement;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import static DAO.JDBCUtil.getConnection;
import DAO.PhieuMuonDAO;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


public class UIu extends javax.swing.JFrame {
    
    private JButton selectedButton = null;
    public CardLayout card = new CardLayout();
    private DocGiaDTO docGia;
    private mySQLConnect db = new mySQLConnect();
    SachBUS sachBUS = new SachBUS();
    private List<SachDTO> danhSach;
    
    public UIu() {
        initComponents();
        SachBUS sachBUS = new SachBUS();
        JTableHeader headerSm = tblSachMuon.getTableHeader(); 
        headerSm.setFont(new Font("Arial", Font.BOLD, 16));
        ((DefaultTableCellRenderer) headerSm.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        JTableHeader headerS = tbSach.getTableHeader(); 
        headerS.setFont(new Font("Arial", Font.BOLD, 16));
        ((DefaultTableCellRenderer) headerS.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        pnlContent.setLayout(card);
        pnlContent.add(pnlFormTrangChu, "FormTrangchu");
        pnlContent.add(pnlFormSach, "FormSach");
        pnlContent.add(pnlFormSachMuon, "FormSachmuon");
        pnlContent.add(FormCaNhan, "FormCanhan");
        
        
        
        tblSachMuon.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblSachMuon.getColumnModel().getColumn(1).setMinWidth(0);
        tblSachMuon.getColumnModel().getColumn(1).setMaxWidth(0);
        tblSachMuon.getColumnModel().getColumn(1).setWidth(0);
        tblSachMuon.getColumnModel().getColumn(2).setPreferredWidth(530);
        tblSachMuon.getColumnModel().getColumn(3).setPreferredWidth(210);
        tblSachMuon.getColumnModel().getColumn(4).setPreferredWidth(210);
        tblSachMuon.getColumnModel().getColumn(5).setPreferredWidth(150);
        
        hienThiSachLenTable(tbSach);
        
        tbSach.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbSach.getColumnModel().getColumn(2).setPreferredWidth(250);
        tbSach.getColumnModel().getColumn(3).setPreferredWidth(70);
        tbSach.getColumnModel().getColumn(4).setPreferredWidth(150);
        tbSach.getColumnModel().getColumn(5).setPreferredWidth(100);
        tbSach.getColumnModel().getColumn(6).setPreferredWidth(200);
        tbSach.getColumnModel().getColumn(1).setMinWidth(0);
        tbSach.getColumnModel().getColumn(1).setMaxWidth(0);
        tbSach.getColumnModel().getColumn(1).setWidth(0);
        tbSach.getColumnModel().getColumn(7).setMinWidth(0);
        tbSach.getColumnModel().getColumn(7).setMaxWidth(0);
        tbSach.getColumnModel().getColumn(7).setWidth(0);
    }
    
    public void hienThiSachLenTable(JTable tableSach) {
        String[] columnNames = {"STT", "Mã sách", "Tên sách", "Thể loại", "Tác giả", "Ngôn ngữ", "Hình thức đọc", "File_pdf"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        SachDAO sachDAO = new SachDAO();
        try {
            ArrayList<SachDTO> danhSach = sachDAO.layDanhSachSach();
            int stt = 1;
            for (SachDTO sach : danhSach) {
                
                ArrayList<String> hinhThuc = new ArrayList<>();
                if (sach.isDocTaiCho() == true) hinhThuc.add("Đọc tại chỗ");
                if (sach.isMuonVe() == true) hinhThuc.add("Mượn về");
                if (sach.isDocFilePdf() == true) hinhThuc.add("Đọc PDF");

            String hinhThucDoc = String.join(", ", hinhThuc);
                
                Object[] rowData = {
                    stt++,
                    sach.getMaSach(),
                    sach.getTenSach(),
                    sach.getTheLoai(),
                    sach.getTacGia(),
                    sach.getNgonNgu(),
                    hinhThucDoc,
                    sach.getFilePdf()
                };
                model.addRow(rowData);
            }
            tableSach.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tải danh sách sách: " + e.getMessage());
        }
    }

    public void setDocGia(DocGiaDTO docGia) {
        this.docGia = docGia;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        opTenDocGiaCaNhan.setText(docGia.getTenDG());
        opGioiTinhCaNhan.setText(docGia.getGioiTinh());
        opSoDienThoaiCaNhan.setText(docGia.getSoDienThoai());

        if (docGia.getNgaySinh() != null)
            opNgaySinhCaNhan.setText(sdf.format(docGia.getNgaySinh()));
        else
            opNgaySinhCaNhan.setText("");

        opDiaChiCaNhan.setText(docGia.getDiaChi());
        opMaTheTheCaNhan.setText(docGia.getMaThe());

        if (docGia.getNgayCap() != null)
            opNgayCapTheCaNhan.setText(sdf.format(docGia.getNgayCap()));
        else
            opNgayCapTheCaNhan.setText("");

        if (docGia.getNgayHetHan() != null)
            opNgayHetHanTheCaNhan.setText(sdf.format(docGia.getNgayHetHan()));
        else
            opNgayHetHanTheCaNhan.setText("");

        hienThiPhieuMuonLenTable(tblSachMuon, docGia);
    }
    

    private void showDialogChiTietSach(SachDTO sach) {
        txTenSach3.setText(sach.getTenSach());
        txTheLoai3.setText(sach.getTheLoai());
        txNgonNgu3.setText(sach.getNgonNgu());
        txNXB6.setText(sach.getNhaXuatBan());
        txNamXuatBan3.setText(String.valueOf(sach.getNamXuatBan()));

        ArrayList<String> hinhThuc = new ArrayList<>();
        if (sach.isDocTaiCho()) hinhThuc.add("Đọc tại chỗ");
        if (sach.isMuonVe()) hinhThuc.add("Mượn về");
        if (sach.isDocFilePdf()) hinhThuc.add("Đọc PDF");
        txHinhthucMuon3.setText(String.join(", ", hinhThuc));

        Chitiet.setLocationRelativeTo(null); // canh giữa
        Chitiet.setVisible(true); // hiện dialog
    }
    
    
    public void hienThiPhieuMuonLenTable(JTable table, DocGiaDTO docGia) {
        String maDocGia = docGia.getMaDG();
        if (maDocGia == null || maDocGia.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không có mã độc giả!");
            return;
        }

        String[] columnNames = {"STT", "Mã phiếu mượn", "Tên sách mượn", "Ngày mượn", "Hạn trả", "Tình trạng"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        PhieuMuonDAO pmDAO = new PhieuMuonDAO();
        SachDAO sachDAO = new SachDAO();

        try {
            ArrayList<PhieuMuonDTO> danhSachPM = pmDAO.layPhieuMuonTheoDocGia(maDocGia);  
            int stt = 1;
            for (PhieuMuonDTO pm : danhSachPM) {
                SachDTO sach = sachDAO.timSachTheoMa(pm.getMaSach());
                String tenSach = sach != null ? sach.getTenSach() : "Không tìm thấy";

                String trangThai = "Không xác định";
                switch (pm.getTrangThai()) {
                    case 1 -> trangThai = "Đang mượn";
                    case 2 -> trangThai = "Đang đặt mượn";
                    case 3 -> trangThai = "Trễ hạn";
                    case 4 -> trangThai = "Làm hỏng sách";
                }

                Object[] rowData = {
                    stt++,
                    pm.getMaPhieuMuon(),
                    tenSach,
                    pm.getNgayMuon(),
                    pm.getHanTra(),
                    trangThai
                };
                model.addRow(rowData);
            }

            table.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tải danh sách phiếu mượn: " + e.getMessage());
        }
    }
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FormDoconline = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        Thongtin1 = new javax.swing.JLabel();
        Thongtin2 = new javax.swing.JLabel();
        btnXemPDF = new javax.swing.JButton();
        FormMuonsach = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        Giaithich1 = new javax.swing.JLabel();
        Giaithich2 = new javax.swing.JLabel();
        btnDatmuon = new javax.swing.JButton();
        Form = new javax.swing.JLabel();
        Chonngaymuon = new com.toedter.calendar.JDateChooser();
        To = new javax.swing.JLabel();
        Chonngaytra = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        FormDanhgia = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        NamXB1 = new javax.swing.JLabel();
        Danhgia = new javax.swing.JLabel();
        Nhapso = new javax.swing.JTextField();
        btnXacnhanDG = new javax.swing.JButton();
        Form2PA = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        Thongtin3 = new javax.swing.JLabel();
        Thongtin4 = new javax.swing.JLabel();
        btnMuonve = new javax.swing.JButton();
        btnDocpdf = new javax.swing.JButton();
        FormDoctaicho = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        Thongtin5 = new javax.swing.JLabel();
        Chitiet = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        TenSach3 = new javax.swing.JLabel();
        txTenSach3 = new javax.swing.JLabel();
        TheLoai3 = new javax.swing.JLabel();
        txTheLoai3 = new javax.swing.JLabel();
        NgonNgu3 = new javax.swing.JLabel();
        txNgonNgu3 = new javax.swing.JLabel();
        NXB6 = new javax.swing.JLabel();
        txNXB6 = new javax.swing.JLabel();
        NamXuatBan3 = new javax.swing.JLabel();
        txNamXuatBan3 = new javax.swing.JLabel();
        HinhthucMuon3 = new javax.swing.JLabel();
        txHinhthucMuon3 = new javax.swing.JLabel();
        pnlMain = new javax.swing.JPanel();
        pnlNavMenu = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        btnTrangChu = new javax.swing.JButton();
        btnSachMuon = new javax.swing.JButton();
        btnThongTin = new javax.swing.JButton();
        btnSach = new javax.swing.JButton();
        pnlContent = new javax.swing.JPanel();
        pnlFormTrangChu = new javax.swing.JPanel();
        pnlContactInfo = new javax.swing.JPanel();
        lblPhone = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblMainImage = new javax.swing.JLabel();
        pnlTimKiemTrangChu = new javax.swing.JPanel();
        lblTimKiemTrangChu = new javax.swing.JLabel();
        lblExtraImage = new javax.swing.JLabel();
        pnlFormSach = new javax.swing.JPanel();
        pnlTimKiemSach = new javax.swing.JPanel();
        lblTimKiemSach = new javax.swing.JLabel();
        txtTimKiemSach = new javax.swing.JTextField();
        btnTimKiemSach = new javax.swing.JButton();
        btnHinhthuc = new javax.swing.JButton();
        btnChitiet = new javax.swing.JButton();
        cboTimkiemSach = new javax.swing.JComboBox<>();
        btnReset1 = new javax.swing.JButton();
        scrTableSach = new javax.swing.JScrollPane();
        tbSach = new javax.swing.JTable();
        pnlFormSachMuon = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        scrTablePhieuMuon = new javax.swing.JScrollPane();
        tblSachMuon = new javax.swing.JTable();
        pnlTimKiemSachMuon = new javax.swing.JPanel();
        cboTimKiemSachMuon = new javax.swing.JComboBox<>();
        btnGiaHanPhieuMuon = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        FormCaNhan = new javax.swing.JPanel();
        lblTenDocGiaCaNhan = new javax.swing.JLabel();
        lblNgaySinhCaNhan = new javax.swing.JLabel();
        lblGioiTinhCaNhan = new javax.swing.JLabel();
        lblSoDienThoaiCaNhan = new javax.swing.JLabel();
        lblDiaChiCaNhan = new javax.swing.JLabel();
        opTenDocGiaCaNhan = new javax.swing.JLabel();
        opNgaySinhCaNhan = new javax.swing.JLabel();
        opGioiTinhCaNhan = new javax.swing.JLabel();
        opSoDienThoaiCaNhan = new javax.swing.JLabel();
        opDiaChiCaNhan = new javax.swing.JLabel();
        lblThongTinCaNhan = new javax.swing.JLabel();
        lblThongTinTheCaNhan = new javax.swing.JLabel();
        lblMaTheCaNhan = new javax.swing.JLabel();
        lblNgayCapTheCaNhan = new javax.swing.JLabel();
        lblNgayHetHanTheCaNhan = new javax.swing.JLabel();
        opNgayHetHanTheCaNhan = new javax.swing.JLabel();
        opNgayCapTheCaNhan = new javax.swing.JLabel();
        opMaTheTheCaNhan = new javax.swing.JLabel();

        FormDoconline.setModal(true);
        FormDoconline.setSize(new java.awt.Dimension(530, 260));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel4.setPreferredSize(new java.awt.Dimension(500, 400));

        Thongtin1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        Thongtin1.setText("Bạn có thể tới thư viện để đọc quyển này. ");

        Thongtin2.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        Thongtin2.setText("Nếu không tiện, bạn có thể đọc thông qua file pdf");

        btnXemPDF.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        btnXemPDF.setText("PDF");
        btnXemPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mofilepdf(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Thongtin2)
                    .addComponent(Thongtin1))
                .addContainerGap(106, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXemPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(Thongtin1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Thongtin2)
                .addGap(29, 29, 29)
                .addComponent(btnXemPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout FormDoconlineLayout = new javax.swing.GroupLayout(FormDoconline.getContentPane());
        FormDoconline.getContentPane().setLayout(FormDoconlineLayout);
        FormDoconlineLayout.setHorizontalGroup(
            FormDoconlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
            .addGroup(FormDoconlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FormDoconlineLayout.createSequentialGroup()
                    .addContainerGap(15, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE)))
        );
        FormDoconlineLayout.setVerticalGroup(
            FormDoconlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
            .addGroup(FormDoconlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FormDoconlineLayout.createSequentialGroup()
                    .addContainerGap(18, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(42, Short.MAX_VALUE)))
        );

        FormMuonsach.setModal(true);
        FormMuonsach.setSize(new java.awt.Dimension(590, 350));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setPreferredSize(new java.awt.Dimension(550, 400));

        Giaithich1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        Giaithich1.setText("Bạn có thể tới thư viện để đọc quyển này. ");

        Giaithich2.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        Giaithich2.setText("Nếu bạn muốn đọc ở nhà, có thể mượn sách");

        btnDatmuon.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        btnDatmuon.setText("Đặt mượn");
        btnDatmuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPhieumuon(evt);
            }
        });

        Form.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        Form.setText("Ngày mượn:");

        To.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        To.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        To.setText("Ngày trả:");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("(Nếu không chọn ngày trả, mặc định là 14 ngày)");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("(Có thể mượn tối đa là 21 ngày, kể từ ngày mượn)");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(420, Short.MAX_VALUE)
                .addComponent(btnDatmuon, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Form, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(To, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Chonngaytra, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Chonngaymuon, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Giaithich2)
                    .addComponent(Giaithich1)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(Giaithich1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Giaithich2)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Form)
                    .addComponent(Chonngaymuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Chonngaytra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(To)))
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDatmuon, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout FormMuonsachLayout = new javax.swing.GroupLayout(FormMuonsach.getContentPane());
        FormMuonsach.getContentPane().setLayout(FormMuonsachLayout);
        FormMuonsachLayout.setHorizontalGroup(
            FormMuonsachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 583, Short.MAX_VALUE)
            .addGroup(FormMuonsachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FormMuonsachLayout.createSequentialGroup()
                    .addContainerGap(16, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(17, Short.MAX_VALUE)))
        );
        FormMuonsachLayout.setVerticalGroup(
            FormMuonsachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
            .addGroup(FormMuonsachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FormMuonsachLayout.createSequentialGroup()
                    .addContainerGap(29, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(28, Short.MAX_VALUE)))
        );

        FormDanhgia.setSize(new java.awt.Dimension(500, 350));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(500, 400));

        NamXB1.setFont(new java.awt.Font("Arial", 1, 40)); // NOI18N
        NamXB1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NamXB1.setText("Đánh giá");

        Danhgia.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        Danhgia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Danhgia.setText("Đánh giá (1-5):");

        Nhapso.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Nhapso.setForeground(new java.awt.Color(153, 153, 153));
        Nhapso.setText("Nhập số");

        btnXacnhanDG.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        btnXacnhanDG.setText("Xác nhận");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(NamXB1))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(Danhgia)
                        .addGap(29, 29, 29)
                        .addComponent(Nhapso, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(130, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnXacnhanDG, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(NamXB1)
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Danhgia)
                    .addComponent(Nhapso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addComponent(btnXacnhanDG, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout FormDanhgiaLayout = new javax.swing.GroupLayout(FormDanhgia.getContentPane());
        FormDanhgia.getContentPane().setLayout(FormDanhgiaLayout);
        FormDanhgiaLayout.setHorizontalGroup(
            FormDanhgiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(FormDanhgiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(FormDanhgiaLayout.createSequentialGroup()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        FormDanhgiaLayout.setVerticalGroup(
            FormDanhgiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
            .addGroup(FormDanhgiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(FormDanhgiaLayout.createSequentialGroup()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        Form2PA.setSize(new java.awt.Dimension(550, 250));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Thongtin3.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        Thongtin3.setText("Bạn có thể tới thư viện để đọc quyển này. ");

        Thongtin4.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        Thongtin4.setText("Bạn cũng có thể đọc qua file pdf, hoặc bạn có thể đặt sách để mượn về");

        btnMuonve.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        btnMuonve.setText("Mượn về");
        btnMuonve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenDKMuon(evt);
            }
        });

        btnDocpdf.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        btnDocpdf.setText("Đọc PDF");
        btnDocpdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mofilepdf(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Thongtin4)
                            .addComponent(Thongtin3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(btnMuonve, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btnDocpdf, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(Thongtin3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Thongtin4)
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMuonve, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDocpdf, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Form2PALayout = new javax.swing.GroupLayout(Form2PA.getContentPane());
        Form2PA.getContentPane().setLayout(Form2PALayout);
        Form2PALayout.setHorizontalGroup(
            Form2PALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Form2PALayout.setVerticalGroup(
            Form2PALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Form2PALayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        FormDoctaicho.setSize(new java.awt.Dimension(550, 200));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Thongtin5.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        Thongtin5.setText("Xin lỗi vì sự bất tiện, hiện tại sách này chỉ có thể đọc tại thư viện");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Thongtin5)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(Thongtin5)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout FormDoctaichoLayout = new javax.swing.GroupLayout(FormDoctaicho.getContentPane());
        FormDoctaicho.getContentPane().setLayout(FormDoctaichoLayout);
        FormDoctaichoLayout.setHorizontalGroup(
            FormDoctaichoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(FormDoctaichoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FormDoctaichoLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        FormDoctaichoLayout.setVerticalGroup(
            FormDoctaichoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
            .addGroup(FormDoctaichoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FormDoctaichoLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        Chitiet.setSize(new java.awt.Dimension(440, 360));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        TenSach3.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        TenSach3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TenSach3.setText("Tên sách:");

        txTenSach3.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        txTenSach3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txTenSach3.setText("truy xuất");

        TheLoai3.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        TheLoai3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        TheLoai3.setText("Thể loại:");

        txTheLoai3.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        txTheLoai3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txTheLoai3.setText("truy xuất");

        NgonNgu3.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        NgonNgu3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        NgonNgu3.setText("Ngôn ngữ:");

        txNgonNgu3.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        txNgonNgu3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txNgonNgu3.setText("truy xuất");

        NXB6.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        NXB6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        NXB6.setText("Nhà xuất bản:");

        txNXB6.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        txNXB6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txNXB6.setText("truy xuất");

        NamXuatBan3.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        NamXuatBan3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        NamXuatBan3.setText("Năm xuất bản:");

        txNamXuatBan3.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        txNamXuatBan3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txNamXuatBan3.setText("truy xuất");

        HinhthucMuon3.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        HinhthucMuon3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        HinhthucMuon3.setText("Hình thức mượn:");

        txHinhthucMuon3.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        txHinhthucMuon3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txHinhthucMuon3.setText("truy xuất");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(HinhthucMuon3)
                    .addComponent(NamXuatBan3)
                    .addComponent(NXB6)
                    .addComponent(NgonNgu3)
                    .addComponent(TheLoai3)
                    .addComponent(TenSach3))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txTenSach3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txTheLoai3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txNgonNgu3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txNXB6, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txNamXuatBan3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txHinhthucMuon3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TenSach3)
                    .addComponent(txTenSach3))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TheLoai3)
                    .addComponent(txTheLoai3))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NgonNgu3)
                    .addComponent(txNgonNgu3))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NXB6)
                    .addComponent(txNXB6))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NamXuatBan3)
                    .addComponent(txNamXuatBan3))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HinhthucMuon3)
                    .addComponent(txHinhthucMuon3))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ChitietLayout = new javax.swing.GroupLayout(Chitiet.getContentPane());
        Chitiet.getContentPane().setLayout(ChitietLayout);
        ChitietLayout.setHorizontalGroup(
            ChitietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
            .addGroup(ChitietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ChitietLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ChitietLayout.setVerticalGroup(
            ChitietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
            .addGroup(ChitietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ChitietLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1400, 700));

        pnlMain.setPreferredSize(new java.awt.Dimension(1400, 700));

        pnlNavMenu.setBackground(new java.awt.Color(255, 255, 255));
        pnlNavMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        pnlNavMenu.setPreferredSize(new java.awt.Dimension(1200, 120));

        logo.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logo3.png"))); // NOI18N
        logo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        logo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btnTrangChu.setBackground(new java.awt.Color(238, 242, 240));
        btnTrangChu.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnTrangChu.setText("Trang chủ");
        btnTrangChu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0), 2));
        btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenTrangchu(evt);
            }
        });

        btnSachMuon.setBackground(new java.awt.Color(238, 242, 240));
        btnSachMuon.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnSachMuon.setText("Quản lý sách mượn");
        btnSachMuon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0), 2));
        btnSachMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenSachmuon(evt);
            }
        });

        btnThongTin.setBackground(new java.awt.Color(238, 242, 240));
        btnThongTin.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnThongTin.setText("Thông tin cá nhân");
        btnThongTin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0), 2));
        btnThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenCanhan(evt);
            }
        });

        btnSach.setBackground(new java.awt.Color(238, 240, 242));
        btnSach.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnSach.setText("Sách");
        btnSach.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0), 2));
        btnSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChuyenSach(evt);
            }
        });

        javax.swing.GroupLayout pnlNavMenuLayout = new javax.swing.GroupLayout(pnlNavMenu);
        pnlNavMenu.setLayout(pnlNavMenuLayout);
        pnlNavMenuLayout.setHorizontalGroup(
            pnlNavMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNavMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo)
                .addGap(110, 110, 110)
                .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btnSach, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(btnSachMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(btnThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlNavMenuLayout.setVerticalGroup(
            pnlNavMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNavMenuLayout.createSequentialGroup()
                .addGroup(pnlNavMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNavMenuLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(pnlNavMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSachMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSach, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlNavMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(logo)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pnlContent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 3));
        pnlContent.setLayout(new java.awt.CardLayout());

        pnlFormTrangChu.setPreferredSize(new java.awt.Dimension(1200, 553));

        pnlContactInfo.setBackground(new java.awt.Color(0, 122, 77));
        pnlContactInfo.setPreferredSize(new java.awt.Dimension(1200, 60));

        lblPhone.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lblPhone.setForeground(new java.awt.Color(255, 255, 255));
        lblPhone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/phone3.png"))); // NOI18N
        lblPhone.setText("0123.456.789");

        lblEmail.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/email.png"))); // NOI18N
        lblEmail.setText("library.vippro@gmail.com");

        javax.swing.GroupLayout pnlContactInfoLayout = new javax.swing.GroupLayout(pnlContactInfo);
        pnlContactInfo.setLayout(pnlContactInfoLayout);
        pnlContactInfoLayout.setHorizontalGroup(
            pnlContactInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContactInfoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblPhone)
                .addGap(30, 30, 30)
                .addComponent(lblEmail)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlContactInfoLayout.setVerticalGroup(
            pnlContactInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContactInfoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlContactInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhone)
                    .addComponent(lblEmail))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        lblMainImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/poster.png"))); // NOI18N
        lblMainImage.setText("jLabel1");
        lblMainImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        pnlTimKiemTrangChu.setBackground(new java.awt.Color(238, 242, 240));
        pnlTimKiemTrangChu.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 1, 3, 1, new java.awt.Color(0, 122, 77)));

        lblTimKiemTrangChu.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblTimKiemTrangChu.setText("Bạn có thể tìm kiếm mọi thứ thông qua \"Sách\"");

        javax.swing.GroupLayout pnlTimKiemTrangChuLayout = new javax.swing.GroupLayout(pnlTimKiemTrangChu);
        pnlTimKiemTrangChu.setLayout(pnlTimKiemTrangChuLayout);
        pnlTimKiemTrangChuLayout.setHorizontalGroup(
            pnlTimKiemTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemTrangChuLayout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addComponent(lblTimKiemTrangChu)
                .addGap(80, 80, 80))
        );
        pnlTimKiemTrangChuLayout.setVerticalGroup(
            pnlTimKiemTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemTrangChuLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(lblTimKiemTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblExtraImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/decor.png"))); // NOI18N
        lblExtraImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout pnlFormTrangChuLayout = new javax.swing.GroupLayout(pnlFormTrangChu);
        pnlFormTrangChu.setLayout(pnlFormTrangChuLayout);
        pnlFormTrangChuLayout.setHorizontalGroup(
            pnlFormTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContactInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 1222, Short.MAX_VALUE)
            .addComponent(lblMainImage, javax.swing.GroupLayout.PREFERRED_SIZE, 1222, Short.MAX_VALUE)
            .addGroup(pnlFormTrangChuLayout.createSequentialGroup()
                .addComponent(pnlTimKiemTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblExtraImage))
        );
        pnlFormTrangChuLayout.setVerticalGroup(
            pnlFormTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormTrangChuLayout.createSequentialGroup()
                .addComponent(pnlContactInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblMainImage, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(pnlFormTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTimKiemTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblExtraImage, javax.swing.GroupLayout.PREFERRED_SIZE, 195, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlContent.add(pnlFormTrangChu, "FormTrangchu");

        pnlFormSach.setBackground(new java.awt.Color(255, 255, 255));

        pnlTimKiemSach.setBackground(new java.awt.Color(0, 122, 77));

        lblTimKiemSach.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblTimKiemSach.setForeground(new java.awt.Color(255, 255, 255));
        lblTimKiemSach.setText("Tìm kiếm:");

        txtTimKiemSach.setBackground(new java.awt.Color(238, 242, 240));
        txtTimKiemSach.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        btnTimKiemSach.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnTimKiemSach.setForeground(new java.awt.Color(0, 122, 77));
        btnTimKiemSach.setText("Tìm kiếm");
        btnTimKiemSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Timkiem(evt);
            }
        });

        btnHinhthuc.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnHinhthuc.setForeground(new java.awt.Color(0, 122, 77));
        btnHinhthuc.setText("Hình thức");
        btnHinhthuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hinhthucmuon(evt);
            }
        });

        btnChitiet.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnChitiet.setForeground(new java.awt.Color(0, 122, 77));
        btnChitiet.setText("Chi tiết");
        btnChitiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Xemchitiet(evt);
            }
        });

        cboTimkiemSach.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cboTimkiemSach.setForeground(new java.awt.Color(0, 122, 77));
        cboTimkiemSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Sách", "Tác giả", "Thể loại" }));

        btnReset1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnReset1.setForeground(new java.awt.Color(0, 122, 77));
        btnReset1.setText("Reset");
        btnReset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetSach(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemSachLayout = new javax.swing.GroupLayout(pnlTimKiemSach);
        pnlTimKiemSach.setLayout(pnlTimKiemSachLayout);
        pnlTimKiemSachLayout.setHorizontalGroup(
            pnlTimKiemSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTimKiemSach)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiemSach, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboTimkiemSach, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiemSach, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
                .addComponent(btnReset1)
                .addGap(18, 18, 18)
                .addComponent(btnChitiet, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnHinhthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlTimKiemSachLayout.setVerticalGroup(
            pnlTimKiemSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemSachLayout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(pnlTimKiemSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTimKiemSach)
                    .addComponent(txtTimKiemSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiemSach)
                    .addComponent(btnHinhthuc)
                    .addComponent(btnChitiet)
                    .addComponent(cboTimkiemSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset1))
                .addGap(9, 9, 9))
        );

        tbSach.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        tbSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sách", "Tên sách", "Thể loại", "Tác giả", "Ngôn ngữ", "Hình thức đọc", "File_pdf"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
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
        tbSach.setGridColor(new java.awt.Color(0, 0, 0));
        tbSach.setRowHeight(35);
        tbSach.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tbSach.setSelectionForeground(new java.awt.Color(0, 51, 51));
        tbSach.setShowVerticalLines(true);
        scrTableSach.setViewportView(tbSach);

        javax.swing.GroupLayout pnlFormSachLayout = new javax.swing.GroupLayout(pnlFormSach);
        pnlFormSach.setLayout(pnlFormSachLayout);
        pnlFormSachLayout.setHorizontalGroup(
            pnlFormSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTimKiemSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrTableSach)
        );
        pnlFormSachLayout.setVerticalGroup(
            pnlFormSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormSachLayout.createSequentialGroup()
                .addComponent(pnlTimKiemSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrTableSach, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE))
        );

        pnlContent.add(pnlFormSach, "FormSach");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblSachMuon.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        tblSachMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã phiếu mượn", "Tên sách mượn", "Ngày mượn", "Hạn trả", "Tình trạng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tblSachMuon.setGridColor(new java.awt.Color(0, 0, 0));
        tblSachMuon.setRowHeight(35);
        tblSachMuon.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tblSachMuon.setSelectionForeground(new java.awt.Color(0, 51, 51));
        tblSachMuon.setShowVerticalLines(true);
        scrTablePhieuMuon.setViewportView(tblSachMuon);

        pnlTimKiemSachMuon.setBackground(new java.awt.Color(0, 122, 77));

        cboTimKiemSachMuon.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        cboTimKiemSachMuon.setForeground(new java.awt.Color(0, 122, 77));
        cboTimKiemSachMuon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang mượn", "Trễ hạn" }));

        btnGiaHanPhieuMuon.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnGiaHanPhieuMuon.setForeground(new java.awt.Color(0, 122, 77));
        btnGiaHanPhieuMuon.setText("Gia hạn");
        btnGiaHanPhieuMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dangkigh(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnReset.setForeground(new java.awt.Color(0, 122, 77));
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetDB(evt);
            }
        });

        javax.swing.GroupLayout pnlTimKiemSachMuonLayout = new javax.swing.GroupLayout(pnlTimKiemSachMuon);
        pnlTimKiemSachMuon.setLayout(pnlTimKiemSachMuonLayout);
        pnlTimKiemSachMuonLayout.setHorizontalGroup(
            pnlTimKiemSachMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimKiemSachMuonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboTimKiemSachMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGiaHanPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        pnlTimKiemSachMuonLayout.setVerticalGroup(
            pnlTimKiemSachMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTimKiemSachMuonLayout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(pnlTimKiemSachMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTimKiemSachMuon)
                    .addComponent(btnGiaHanPhieuMuon)
                    .addComponent(btnReset))
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrTablePhieuMuon, javax.swing.GroupLayout.DEFAULT_SIZE, 1222, Short.MAX_VALUE)
            .addComponent(pnlTimKiemSachMuon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(pnlTimKiemSachMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrTablePhieuMuon, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlFormSachMuonLayout = new javax.swing.GroupLayout(pnlFormSachMuon);
        pnlFormSachMuon.setLayout(pnlFormSachMuonLayout);
        pnlFormSachMuonLayout.setHorizontalGroup(
            pnlFormSachMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlFormSachMuonLayout.setVerticalGroup(
            pnlFormSachMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormSachMuonLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlContent.add(pnlFormSachMuon, "FormSachmuon");

        FormCaNhan.setBackground(new java.awt.Color(255, 255, 255));

        lblTenDocGiaCaNhan.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        lblTenDocGiaCaNhan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenDocGiaCaNhan.setText("Tên độc giả:");

        lblNgaySinhCaNhan.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        lblNgaySinhCaNhan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNgaySinhCaNhan.setText("Ngày sinh:");

        lblGioiTinhCaNhan.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        lblGioiTinhCaNhan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGioiTinhCaNhan.setText("Giới tính:");

        lblSoDienThoaiCaNhan.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        lblSoDienThoaiCaNhan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSoDienThoaiCaNhan.setText("Số điện thoại:");

        lblDiaChiCaNhan.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        lblDiaChiCaNhan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDiaChiCaNhan.setText("Địa chỉ:");

        opTenDocGiaCaNhan.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        opTenDocGiaCaNhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        opTenDocGiaCaNhan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        opNgaySinhCaNhan.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        opNgaySinhCaNhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        opNgaySinhCaNhan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        opGioiTinhCaNhan.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        opGioiTinhCaNhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        opGioiTinhCaNhan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        opSoDienThoaiCaNhan.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        opSoDienThoaiCaNhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        opSoDienThoaiCaNhan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        opDiaChiCaNhan.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        opDiaChiCaNhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        opDiaChiCaNhan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        lblThongTinCaNhan.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        lblThongTinCaNhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblThongTinCaNhan.setText("Thông tin cá nhân:");

        lblThongTinTheCaNhan.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        lblThongTinTheCaNhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblThongTinTheCaNhan.setText("Thông tin thẻ:");

        lblMaTheCaNhan.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        lblMaTheCaNhan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaTheCaNhan.setText("Mã thẻ:");

        lblNgayCapTheCaNhan.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        lblNgayCapTheCaNhan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNgayCapTheCaNhan.setText("Ngày cấp:");

        lblNgayHetHanTheCaNhan.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        lblNgayHetHanTheCaNhan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNgayHetHanTheCaNhan.setText("Ngày hết hạn:");

        opNgayHetHanTheCaNhan.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        opNgayHetHanTheCaNhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        opNgayHetHanTheCaNhan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        opNgayCapTheCaNhan.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        opNgayCapTheCaNhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        opNgayCapTheCaNhan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        opMaTheTheCaNhan.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        opMaTheTheCaNhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        opMaTheTheCaNhan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout FormCaNhanLayout = new javax.swing.GroupLayout(FormCaNhan);
        FormCaNhan.setLayout(FormCaNhanLayout);
        FormCaNhanLayout.setHorizontalGroup(
            FormCaNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FormCaNhanLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(FormCaNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(FormCaNhanLayout.createSequentialGroup()
                        .addGroup(FormCaNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNgayHetHanTheCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNgayCapTheCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(FormCaNhanLayout.createSequentialGroup()
                                .addComponent(lblThongTinTheCaNhan)
                                .addGap(18, 18, 18)
                                .addComponent(lblMaTheCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(46, 46, 46)
                        .addGroup(FormCaNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(opMaTheTheCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(opNgayCapTheCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(opNgayHetHanTheCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(FormCaNhanLayout.createSequentialGroup()
                        .addGroup(FormCaNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDiaChiCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSoDienThoaiCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGioiTinhCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNgaySinhCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(FormCaNhanLayout.createSequentialGroup()
                                .addComponent(lblThongTinCaNhan)
                                .addGap(18, 18, 18)
                                .addComponent(lblTenDocGiaCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(46, 46, 46)
                        .addGroup(FormCaNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(opTenDocGiaCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(opNgaySinhCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(opGioiTinhCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(opSoDienThoaiCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(opDiaChiCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(488, Short.MAX_VALUE))
        );
        FormCaNhanLayout.setVerticalGroup(
            FormCaNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FormCaNhanLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(FormCaNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FormCaNhanLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(FormCaNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTenDocGiaCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(opTenDocGiaCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblThongTinCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(FormCaNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNgaySinhCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(opNgaySinhCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(FormCaNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGioiTinhCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(opGioiTinhCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(FormCaNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSoDienThoaiCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(opSoDienThoaiCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(FormCaNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDiaChiCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(opDiaChiCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(FormCaNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FormCaNhanLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(FormCaNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaTheCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(opMaTheTheCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblThongTinTheCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(FormCaNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNgayCapTheCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(opNgayCapTheCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(FormCaNhanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNgayHetHanTheCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(opNgayHetHanTheCaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pnlContent.add(FormCaNhan, "FormCanhan");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlNavMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 1228, Short.MAX_VALUE)
                    .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(pnlNavMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlContent, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ChuyenTrangchu(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenTrangchu
        CardLayout card = (CardLayout) pnlContent.getLayout();
        card.show(pnlContent, "FormTrangchu");
    }//GEN-LAST:event_ChuyenTrangchu

    private void ChuyenSach(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenSach
        CardLayout card = (CardLayout) pnlContent.getLayout();
        card.show(pnlContent, "FormSach");
    }//GEN-LAST:event_ChuyenSach

    private void ChuyenSachmuon(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenSachmuon
        CardLayout card = (CardLayout) pnlContent.getLayout();
        card.show(pnlContent, "FormSachmuon");
    }//GEN-LAST:event_ChuyenSachmuon

    private void ChuyenCanhan(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenCanhan
        CardLayout card = (CardLayout) pnlContent.getLayout();
        card.show(pnlContent, "FormCanhan");
    }//GEN-LAST:event_ChuyenCanhan

    private void Hinhthucmuon(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Hinhthucmuon
        int selectedRow = tbSach.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng trước!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }


        String hinhThuc = tbSach.getValueAt(selectedRow, 6).toString();
        
        if (hinhThuc.equalsIgnoreCase("Đọc PDF") || hinhThuc.equalsIgnoreCase("Đọc PDF, Đọc tại chỗ") || hinhThuc.equalsIgnoreCase("Đọc tại chỗ, Đọc PDF")) {
            FormDoconline.setLocationRelativeTo(null);
            FormDoconline.setVisible(true);
        }
        
        else if (hinhThuc.equalsIgnoreCase("Mượn về") || hinhThuc.equalsIgnoreCase("Mượn về, Đọc tại chỗ") || hinhThuc.equalsIgnoreCase("Đọc tại chỗ, Mượn về")) {
            FormMuonsach.setLocationRelativeTo(null);
            FormMuonsach.setVisible(true);
        }
        else if (hinhThuc.equalsIgnoreCase("Đọc tại chỗ")){
            FormDoctaicho.setLocationRelativeTo(null);
            FormDoctaicho.setVisible(true);
        }
        
        else {
            Form2PA.setLocationRelativeTo(null);
            Form2PA.setVisible(true);
        }
        
    }//GEN-LAST:event_Hinhthucmuon

    
    
    
    
    
    private void ChuyenDKMuon(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChuyenDKMuon
        int selectedRow = tbSach.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng trước!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Form2PA.setVisible(false);
        FormMuonsach.setLocationRelativeTo(null);
        FormMuonsach.setVisible(true);
    }//GEN-LAST:event_ChuyenDKMuon

    private void Mofilepdf(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mofilepdf
        int selectedRow = tbSach.getSelectedRow();
        String fileName = tbSach.getValueAt(selectedRow, 7).toString();
        
        
        File pdfFile = new File("D://java//java_pdf//" + fileName);
        if (!pdfFile.exists()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy file: " + fileName);
            return;
        }

        try {
            Desktop.getDesktop().open(pdfFile);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi mở file: " + e.getMessage());
        }
    }//GEN-LAST:event_Mofilepdf

    private void Xemchitiet(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Xemchitiet
        int selectedRow = tbSach.getSelectedRow();
        String maSach = tbSach.getValueAt(selectedRow, 1).toString();
        SachDTO sach = new SachDAO().timSachTheoMa(maSach);

        if (sach != null) {
            showDialogChiTietSach(sach);
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy sách.");
        }
    }//GEN-LAST:event_Xemchitiet

    private void AddPhieumuon(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPhieumuon
       int selectedRow = tbSach.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Mày phải chọn sách trước đã!");
            return;
        }

        String maSach = tbSach.getValueAt(selectedRow, 1).toString();
        String maDocGia = this.docGia != null ? this.docGia.getMaDG() : null;

        if (maDocGia == null || maDocGia.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bạn cần phải đăng nhập!");
            return;
        }

        Date ngayMuonDate = Chonngaymuon.getDate();
        if (ngayMuonDate == null) {
            JOptionPane.showMessageDialog(null, "Phải chọn ngày mượn!");
            return;
        }

        LocalDate ngayMuon = ngayMuonDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();

        if (!ngayMuon.isAfter(today)) {
            JOptionPane.showMessageDialog(null, "Ngày mượn phải sau hôm nay trở đi!");
            return;
        }

        // Xử lý hạn trả
        Date hanTraDate = Chonngaytra.getDate();
        LocalDate hanTra;

        if (hanTraDate == null) {
            // Nếu không chọn hạn trả => tự +14 ngày
            hanTra = ngayMuon.plusDays(14);
        } else {
            hanTra = hanTraDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long daysBetween = ChronoUnit.DAYS.between(ngayMuon, hanTra);
            if (daysBetween > 21) {
                JOptionPane.showMessageDialog(null, "Hạn trả không được quá 21 ngày kể từ ngày mượn!");
                return;
            }
            if (hanTra.isBefore(ngayMuon)) {
                JOptionPane.showMessageDialog(null, "Hạn trả không được trước ngày mượn!");
                return;
            }
        }

        try {
            
            SachDAO sachDAO = new SachDAO();
            SachDTO sach = sachDAO.timSachTheoMa(maSach); // Hàm cần tạo

            if (sach == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy sách này!");
                return;
            }

            if (sach.getSoLuong() <= 0) {
                JOptionPane.showMessageDialog(null, "Sách đã hết, không thể mượn!");
                return;
            }
            
            
            PhieuMuonDAO dao = new PhieuMuonDAO();
            String maPM = dao.taoMaPhieumuonNgauNhien();
            PhieuMuonDTO pm = new PhieuMuonDTO();

            pm.setMaPhieuMuon(maPM);
            pm.setMaDocGia(maDocGia);
            pm.setMaSach(maSach);
            pm.setNgayMuon(ngayMuon);
            pm.setHanTra(hanTra);
            pm.setNgayTraThucTe(null);
            pm.setTrangThai(2);
            pm.setTienPhat(0.0);

            boolean success = dao.Them(pm);
            if (success) {
                sach.setSoLuong(sach.getSoLuong() - 1);
                sachDAO.capNhatSoLuong(sach);
                JOptionPane.showMessageDialog(null, "Thêm phiếu mượn thành công!");
            } else {
                JOptionPane.showMessageDialog(null, "Không thêm được phiếu mượn!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi SQL khi tạo phiếu mượn");
        }
    }//GEN-LAST:event_AddPhieumuon

    private void ResetDB(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetDB
        if (docGia != null) {
            hienThiPhieuMuonLenTable(tblSachMuon, docGia);
        } else {
            JOptionPane.showMessageDialog(null, "Không có thông tin độc giả để làm mới!");
        }
    }//GEN-LAST:event_ResetDB

    private void Timkiem(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Timkiem
        String kieuTim = cboTimkiemSach.getSelectedItem().toString();
        String duLieuTim = txtTimKiemSach.getText().trim();

        if (duLieuTim.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập dữ liệu tìm kiếm");
            return;
        }


        SachBUS bus = new SachBUS();
        List<SachDTO> ketQua = new ArrayList<>();
        switch (kieuTim) {
            case "Tất cả":
                try {
                    ketQua = bus.timKiemTatCa(duLieuTim);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Sách":
                ketQua = bus.timKiemSach("Tên sách", duLieuTim);
                break;
            case "Thể loại":
                ketQua = bus.timKiemSach("Thể loại", duLieuTim);
                break;
            case "Tác giả":
                ketQua = bus.timKiemSach("Tác giả", duLieuTim);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Chưa hỗ trợ kiểu tìm này");
                return;
        }

        DefaultTableModel model = (DefaultTableModel) tbSach.getModel();
        model.setRowCount(0);

        int stt = 1;
        for (SachDTO sach : ketQua) {

            ArrayList<String> hinhThuc = new ArrayList<>();
                    if (sach.isDocTaiCho() == true) hinhThuc.add("Đọc tại chỗ");
                    if (sach.isMuonVe() == true) hinhThuc.add("Mượn về");
                    if (sach.isDocFilePdf() == true) hinhThuc.add("Đọc PDF");

                String hinhThucDoc = String.join(", ", hinhThuc);

            model.addRow(new Object[] {
                stt++, 
                sach.getMaSach(), 
                sach.getTenSach(), 
                sach.getTheLoai(), 
                sach.getTacGia(),
                sach.getNgonNgu(), 
                hinhThucDoc, 
                "",
                sach.getFilePdf()
            });
        }
    }//GEN-LAST:event_Timkiem

    private void ResetSach(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetSach
            cboTimkiemSach.setSelectedIndex(0); // reset về "Tất cả"
    txtTimKiemSach.setText(""); // xóa ô nhập tìm kiếm

    SachBUS bus = new SachBUS();
    List<SachDTO> danhSachSach = new ArrayList<>();
    try {
        danhSachSach = bus.layDanhSachSach(); // load lại toàn bộ
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi load lại danh sách sách");
        return;
    }

    // Đổ lại vào bảng
    DefaultTableModel model = (DefaultTableModel) tbSach.getModel();
    model.setRowCount(0); // clear bảng
    int stt = 1;
    for (SachDTO sach : danhSachSach) {
        
        ArrayList<String> hinhThuc = new ArrayList<>();
                    if (sach.isDocTaiCho() == true) hinhThuc.add("Đọc tại chỗ");
                    if (sach.isMuonVe() == true) hinhThuc.add("Mượn về");
                    if (sach.isDocFilePdf() == true) hinhThuc.add("Đọc PDF");

                String hinhThucDoc = String.join(", ", hinhThuc);
                
        model.addRow(new Object[] {
            stt++, 
            sach.getMaSach(), 
            sach.getTenSach(), 
            sach.getTheLoai(), 
            sach.getTacGia(),
            sach.getNgonNgu(),
            hinhThucDoc,
            "", // để trống
            sach.getFilePdf()
        });
    }
    }//GEN-LAST:event_ResetSach

    

    private void Dangkigh(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Dangkigh
        int selectedRow = tblSachMuon.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Mày phải chọn 1 sách trong danh sách mượn để gia hạn!");
            return;
        }

        
        String maPhieuMuon = tblSachMuon.getValueAt(selectedRow, 1).toString();

        try {
            PhieuMuonDAO dao = new PhieuMuonDAO();
            PhieuMuonDTO pm = dao.timTheoMa(maPhieuMuon);
            if (pm == null) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy phiếu mượn này!");
                return;
            }

            // Kiểm tra trạng thái: nếu đã trả (0) thì không được gia hạn
            if (pm.getTrangThai() != 1) {
                JOptionPane.showMessageDialog(null, "Bạn không thể gia hạn sách này!");
                return;
            }

            LocalDate ngayMuon = pm.getNgayMuon();
            LocalDate hanCu = pm.getHanTra();
            long daysHienTai = ChronoUnit.DAYS.between(ngayMuon, hanCu);

            if (daysHienTai >= 28) {
                JOptionPane.showMessageDialog(null, "Không thể gia hạn! Tổng thời gian mượn đã đạt tối đa 28 ngày.");
                return;
            }

            long ngayConDuocCong = 28 - daysHienTai;
            LocalDate hanMoi;

            if (ngayConDuocCong >= 7) {
                hanMoi = hanCu.plusDays(7);
            } else {
                hanMoi = hanCu.plusDays(ngayConDuocCong);
                JOptionPane.showMessageDialog(null, "Thời gian mượn tối đa chỉ là 28 ngày! Nên hạn trả của bạn được cập nhật sao cho chỉ là 28 ngày ");
            }

            pm.setHanTra(hanMoi);
            boolean updated = dao.Sua(pm);
            if (updated) {
                JOptionPane.showMessageDialog(null, "Gia hạn thành công! Hạn trả mới: " + hanMoi.toString());
                // Làm mới bảng nếu cần
            } else {
                JOptionPane.showMessageDialog(null, "Gia hạn thất bại!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi gia hạn phiếu mượn!");
        }
    }//GEN-LAST:event_Dangkigh

    
    
    
    
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
            java.util.logging.Logger.getLogger(UIu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UIu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Chitiet;
    private com.toedter.calendar.JDateChooser Chonngaymuon;
    private com.toedter.calendar.JDateChooser Chonngaytra;
    private javax.swing.JLabel Danhgia;
    private javax.swing.JLabel Form;
    private javax.swing.JDialog Form2PA;
    private javax.swing.JPanel FormCaNhan;
    private javax.swing.JDialog FormDanhgia;
    private javax.swing.JDialog FormDoconline;
    private javax.swing.JDialog FormDoctaicho;
    private javax.swing.JDialog FormMuonsach;
    private javax.swing.JLabel Giaithich1;
    private javax.swing.JLabel Giaithich2;
    private javax.swing.JLabel HinhthucMuon3;
    private javax.swing.JLabel NXB6;
    private javax.swing.JLabel NamXB1;
    private javax.swing.JLabel NamXuatBan3;
    private javax.swing.JLabel NgonNgu3;
    private javax.swing.JTextField Nhapso;
    private javax.swing.JLabel TenSach3;
    private javax.swing.JLabel TheLoai3;
    private javax.swing.JLabel Thongtin1;
    private javax.swing.JLabel Thongtin2;
    private javax.swing.JLabel Thongtin3;
    private javax.swing.JLabel Thongtin4;
    private javax.swing.JLabel Thongtin5;
    private javax.swing.JLabel To;
    private javax.swing.JButton btnChitiet;
    private javax.swing.JButton btnDatmuon;
    private javax.swing.JButton btnDocpdf;
    private javax.swing.JButton btnGiaHanPhieuMuon;
    private javax.swing.JButton btnHinhthuc;
    private javax.swing.JButton btnMuonve;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnReset1;
    private javax.swing.JButton btnSach;
    private javax.swing.JButton btnSachMuon;
    private javax.swing.JButton btnThongTin;
    private javax.swing.JButton btnTimKiemSach;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.JButton btnXacnhanDG;
    private javax.swing.JButton btnXemPDF;
    private javax.swing.JComboBox<String> cboTimKiemSachMuon;
    private javax.swing.JComboBox<String> cboTimkiemSach;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblDiaChiCaNhan;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblExtraImage;
    private javax.swing.JLabel lblGioiTinhCaNhan;
    private javax.swing.JLabel lblMaTheCaNhan;
    private javax.swing.JLabel lblMainImage;
    private javax.swing.JLabel lblNgayCapTheCaNhan;
    private javax.swing.JLabel lblNgayHetHanTheCaNhan;
    private javax.swing.JLabel lblNgaySinhCaNhan;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblSoDienThoaiCaNhan;
    private javax.swing.JLabel lblTenDocGiaCaNhan;
    private javax.swing.JLabel lblThongTinCaNhan;
    private javax.swing.JLabel lblThongTinTheCaNhan;
    private javax.swing.JLabel lblTimKiemSach;
    private javax.swing.JLabel lblTimKiemTrangChu;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel opDiaChiCaNhan;
    private javax.swing.JLabel opGioiTinhCaNhan;
    private javax.swing.JLabel opMaTheTheCaNhan;
    private javax.swing.JLabel opNgayCapTheCaNhan;
    private javax.swing.JLabel opNgayHetHanTheCaNhan;
    private javax.swing.JLabel opNgaySinhCaNhan;
    private javax.swing.JLabel opSoDienThoaiCaNhan;
    private javax.swing.JLabel opTenDocGiaCaNhan;
    private javax.swing.JPanel pnlContactInfo;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlFormSach;
    private javax.swing.JPanel pnlFormSachMuon;
    private javax.swing.JPanel pnlFormTrangChu;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlNavMenu;
    private javax.swing.JPanel pnlTimKiemSach;
    private javax.swing.JPanel pnlTimKiemSachMuon;
    private javax.swing.JPanel pnlTimKiemTrangChu;
    private javax.swing.JScrollPane scrTablePhieuMuon;
    private javax.swing.JScrollPane scrTableSach;
    private javax.swing.JTable tbSach;
    private javax.swing.JTable tblSachMuon;
    private javax.swing.JLabel txHinhthucMuon3;
    private javax.swing.JLabel txNXB6;
    private javax.swing.JLabel txNamXuatBan3;
    private javax.swing.JLabel txNgonNgu3;
    private javax.swing.JLabel txTenSach3;
    private javax.swing.JLabel txTheLoai3;
    private javax.swing.JTextField txtTimKiemSach;
    // End of variables declaration//GEN-END:variables
}
