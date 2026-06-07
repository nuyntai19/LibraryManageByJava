CREATE DATABASE lbr;
USE lbr;
SET FOREIGN_KEY_CHECKS=0; -- Added for robust import

CREATE TABLE `sach` (
  `ma_sach` varchar(255) NOT NULL,
  `ten_sach` varchar(255) NOT NULL,
  `tac_gia` varchar(255) DEFAULT NULL,
  `the_loai` varchar(100) DEFAULT NULL,
  `ngon_ngu` varchar(50) DEFAULT NULL,
  `so_luong` int(11) DEFAULT 0,
  `nha_xuat_ban` varchar(255) DEFAULT NULL,
  `nam_xuat_ban` year(4) DEFAULT NULL,
  `file_pdf` varchar(255) DEFAULT NULL,
  `doc_tai_cho` tinyint(1) DEFAULT 0,
  `muon_ve` tinyint(1) DEFAULT 0,
  `doc_file_pdf` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sach`
--

INSERT INTO `sach` (`ma_sach`, `ten_sach`, `tac_gia`, `the_loai`, `ngon_ngu`, `so_luong`, `nha_xuat_ban`, `nam_xuat_ban`, `file_pdf`, `doc_tai_cho`, `muon_ve`, `doc_file_pdf`) VALUES
('S001', 'Chuyện Xưa Tích Cũ', 'Nguyễn Văn A', 'Văn học', 'Tiếng Việt', 4, 'NXB Kim Đồng', '2020', 'chuyenxua.pdf', 1, 1, 1),
('S002', 'Khoa Học Vui', 'Trần B', 'Khoa học', 'Tiếng Việt', 5, 'NXB Trẻ', '2021', 'khoahocvui.pdf', 1, 0, 1),
('S003', 'Câu Chuyện Thiếu Nhi', 'Nguyễn C', 'Thiếu nhi', 'Tiếng Việt', 5, 'NXB Kim Đồng', '2019', 'thieunhi.pdf', 1, 1, 0),
('S004', 'Lược Sử Thế Giới', 'John Doe', 'Lịch sử', 'Tiếng Anh', 5, 'NXB Giáo Dục', '2018', 'lichsu.pdf', 0, 1, 1),
('S005', 'Học Tiếng Anh Cơ Bản', 'Jane Smith', 'Ngoại ngữ', 'Tiếng Anh', 5, 'NXB Ngoại Ngữ', '2022', 'tienganh.pdf', 1, 1, 1),
('S006', 'Tập Làm Văn', 'Nguyễn D', 'Văn học', 'Tiếng Việt', 5, 'NXB Giáo Dục', '2023', 'taplamvan.pdf', 1, 0, 0),
('S007', 'Toán Cho Mọi Người', 'Trần E', 'Toán học', 'Tiếng Việt', 5, 'NXB Giáo Dục', '2020', 'toan.pdf', 1, 1, 1),
('S008', 'Lập Trình Python', 'Nguyễn F', 'Tin học', 'Tiếng Việt', 5, 'NXB Khoa Học', '2021', 'python.pdf', 1, 0, 1),
('S009', 'Kinh Tế Vĩ Mô', 'Trần G', 'Kinh tế', 'Tiếng Việt', 5, 'NXB Kinh Tế', '2022', 'kinhte.pdf', 1, 1, 0),
('S010', 'Hiểu Luật Giao Thông', 'Nguyễn H', 'Pháp luật', 'Tiếng Việt', 5, 'NXB Tư Pháp', '2017', 'phapluat.pdf', 0, 1, 1);

-- --------------------------------------------------------

CREATE TABLE `chitietphieunhap` (
  `ma_ctpn` int(11) NOT NULL,
  `ma_phieu_nhap` varchar(255) DEFAULT NULL,
  `ma_sach` varchar(255) DEFAULT NULL,
  `don_gia` int(11) DEFAULT NULL,
  `so_luong` int(11) DEFAULT NULL,
  `thanh_tien` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`ma_ctpn`, `ma_phieu_nhap`, `ma_sach`, `don_gia`, `so_luong`, `thanh_tien`) VALUES
(1, 'PN001', 'S001', 10000, 10, 100000),
(2, 'PN001', 'S002', 15000, 10, 150000),
(3, 'PN002', 'S003', 12000, 8, 96000),
(4, 'PN002', 'S004', 18000, 10, 180000),
(5, 'PN003', 'S005', 20000, 12, 240000),
(6, 'PN003', 'S001', 20000, 10, 200000),
(7, 'PN004', 'S007', 16000, 8, 128000),
(8, 'PN004', 'S008', 16000, 8, 128000),
(9, 'PN005', 'S009', 20000, 12, 240000),
(10, 'PN005', 'S010', 20000, 12, 240000);

-- --------------------------------------------------------

--
-- Table structure for table `chucnang`
--

CREATE TABLE `chucnang` (
  `ma_chuc_nang` varchar(255) NOT NULL,
  `ten_chuc_nang` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chucnang`
--

INSERT INTO `chucnang` (`ma_chuc_nang`, `ten_chuc_nang`) VALUES
('Q4', 'Quanlydocgia'),
('Q9', 'Quanlynguonnhap'),
('Q2', 'Quanlynhanvien'),
('Q5', 'Quanlyphanquyen'),
('Q6', 'Quanlyphieumuon'),
('Q3', 'Quanlyphieunhapsach'),
('Q1', 'Quanlysach'),
('Q7', 'Quanlytaikhoan'),
('Q8', 'Quanlythongke');

-- --------------------------------------------------------

--
-- Table structure for table `cosonhap`
--

CREATE TABLE `cosonhap` (
  `ma_co_so` varchar(255) NOT NULL,
  `ten_co_so` varchar(255) DEFAULT NULL,
  `hinhthuc_chuyeu` int(11) DEFAULT NULL,
  `dia_chi` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `ngay_hoptac` date DEFAULT NULL,
  `sdt` varchar(20) DEFAULT NULL,
  `trangthai` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cosonhap`
--

INSERT INTO `cosonhap` (`ma_co_so`, `ten_co_so`, `hinhthuc_chuyeu`, `dia_chi`, `email`, `ngay_hoptac`, `sdt`, `trangthai`) VALUES
('CS001', 'Công ty Sách ABC', 1, 'Hà Nội', 'abc@sach.vn', '2020-01-15', '0901234561', 1),
('CS002', 'Nhà Xuất Bản Trẻ', 2, 'TP. HCM', 'nxbtre@sach.vn', '2019-05-10', '0901234562', 1),
('CS003', 'Tổ chức Thiện nguyện A', 3, 'Đà Nẵng', 'thiennguyenA@gmail.com', '2021-03-20', '0901234563', 1),
('CS004', 'Công ty Văn hóa B', 1, 'Huế', 'vanhoaB@gmail.com', '2018-07-30', '0901234564', 0),
('CS005', 'Nhà Xuất Bản Kim Đồng', 2, 'TP. HCM', 'kimdong@nxb.vn', '2017-11-25', '0901234565', 1),
('CS006', 'Nhóm Tình Nguyện X', 3, 'Cần Thơ', 'tnx@gmail.com', '2022-02-18', '0901234566', 1),
('CS007', 'Công ty Sách Minh Long', 1, 'Bình Dương', 'minhlong@sach.vn', '2019-08-05', '0901234567', 0),
('CS008', 'Nhà Xuất Bản Giáo Dục', 2, 'Hà Nội', 'giaoduc@nxb.vn', '2015-12-12', '0901234568', 1),
('CS009', 'Tổ chức Sách Cho Em', 3, 'Quảng Ninh', 'sachem@gmail.com', '2023-06-01', '0901234569', 1),
('CS010', 'Công ty Phát hành Sách Z', 1, 'Nha Trang', 'phsz@sach.vn', '2020-09-09', '0901234570', 0);

-- --------------------------------------------------------

--
-- Table structure for table `dattruoc`
--

CREATE TABLE `dattruoc` (
  `ma_dat_truoc` varchar(255) NOT NULL,
  `ma_doc_gia` varchar(255) DEFAULT NULL,
  `ma_sach` varchar(255) DEFAULT NULL,
  `ngay_dat` date DEFAULT curdate(),
  `ma_phieu_muon` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `docgia`
--

CREATE TABLE `docgia` (
  `ma_doc_gia` varchar(255) NOT NULL,
  `ten_doc_gia` varchar(255) NOT NULL,
  `gioi_tinh` varchar(10) DEFAULT NULL,
  `so_dien_thoai` varchar(15) DEFAULT NULL,
  `ngay_sinh` date DEFAULT NULL,
  `dia_chi` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `docgia`
--

INSERT INTO `docgia` (`ma_doc_gia`, `ten_doc_gia`, `gioi_tinh`, `so_dien_thoai`, `ngay_sinh`, `dia_chi`) VALUES
('DG001', 'Nguyễn Văn A', 'Nam', '0901000001', '1995-01-10', 'Hà Nội'),
('DG002', 'Trần Thị B', 'Nữ', '0901000002', '1997-05-20', 'TP. Hồ Chí Minh'),
('DG003', 'Lê Văn C', 'Nam', '0901000003', '1990-12-15', 'Đà Nẵng'),
('DG004', 'Phạm Thị D', 'Nữ', '0901000004', '2000-07-30', 'Hải Phòng'),
('DG005', 'Hoàng Văn E', 'Nam', '0901000005', '1988-09-22', 'Cần Thơ'),
('DG006', 'Đặng Thị F', 'Nữ', '0901000006', '1993-03-11', 'Huế'),
('DG007', 'Ngô Văn G', 'Nam', '0901000007', '1998-06-17', 'Nha Trang'),
('DG008', 'Bùi Thị H', 'Nữ', '0901000008', '1996-04-08', 'Vũng Tàu'),
('DG009', 'Vũ Văn I', 'Nam', '0901000009', '1992-11-25', 'Quảng Ninh'),
('DG010', 'Lý Thị J', 'Nữ', '0901000010', '1994-08-05', 'Bình Dương');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `ma_nhan_vien` int(11) NOT NULL,
  `ten_nhan_vien` varchar(100) NOT NULL,
  `gioi_tinh` varchar(20) DEFAULT NULL,
  `ngay_sinh` date DEFAULT NULL,
  `dia_chi` varchar(20) DEFAULT NULL,
  `so_dien_thoai` varchar(15) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `ngay_vao_lam` date DEFAULT NULL,
  `trang_thai` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`ma_nhan_vien`, `ten_nhan_vien`, `gioi_tinh`, `ngay_sinh`, `dia_chi`, `so_dien_thoai`, `email`, `ngay_vao_lam`, `trang_thai`) VALUES
(1, 'Nguyễn Văn A', 'Nam', '1990-05-15', 'Hà Nội', '0912345678', 'a.nguyen@example.com', '2022-01-10', 'Đang làm'),
(2, 'Trần Thị B', 'Nữ', '1992-08-20', 'Hồ Chí Minh', '0987654321', 'b.tran@example.com', '2023-03-01', 'Đang làm'),
(3, 'Lê Văn C', 'Nam', '1988-12-01', 'Đà Nẵng', '0909123456', 'c.le@example.com', '2021-11-15', 'Đang làm'),
(4, 'Phạm Thị D', 'Nữ', '1995-04-22', 'Cần Thơ', '0934567890', 'd.pham@example.com', '2024-06-20', 'Đang làm'),
(5, 'Hoàng Văn E', 'Nam', '1993-07-09', 'Hải Phòng', '0978123456', 'e.hoang@example.com', '2020-09-05', 'Đang làm');

-- --------------------------------------------------------

--
-- Table structure for table `phanquyen`
--

CREATE TABLE `phanquyen` (
  `ma_phan_quyen` varchar(255) NOT NULL,
  `ten_phan_quyen` varchar(255) DEFAULT NULL,
  `ma_chuc_nang` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phanquyen`
--

INSERT INTO `phanquyen` (`ma_phan_quyen`, `ten_phan_quyen`, `ma_chuc_nang`) VALUES
('PQ1', 'Thủ thư', 'Q1'),
('PQ2', 'Thủ thư', 'Q4'),
('PQ3', 'Thủ thư', 'Q6'),
('PQ4', 'Thủ thư', 'Q3'),
('PQ5', 'oke', 'Q1'),
('PQ5', 'oke', 'Q2'),
('PQ5', 'oke', 'Q3');

-- --------------------------------------------------------

--
-- Table structure for table `phieumuon`
--

CREATE TABLE `phieumuon` (
  `ma_phieu_muon` varchar(255) NOT NULL,
  `ma_doc_gia` varchar(255) DEFAULT NULL,
  `ma_sach` varchar(255) DEFAULT NULL,
  `ngay_muon` date DEFAULT NULL,
  `han_tra` date DEFAULT NULL,
  `ngay_tra_thuc_te` date DEFAULT NULL,
  `trang_thai` varchar(50) DEFAULT NULL,
  `tien_phat` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieumuon`
--

INSERT INTO `phieumuon` (`ma_phieu_muon`, `ma_doc_gia`, `ma_sach`, `ngay_muon`, `han_tra`, `ngay_tra_thuc_te`, `trang_thai`, `tien_phat`) VALUES
('452956', 'DG001', 'S001', '2025-05-10', '2025-05-31', NULL, '2', 0),
('PM001', 'DG001', 'S001', '2025-04-01', '2025-04-10', '2025-04-09', '0', 0),
('PM002', 'DG002', 'S002', '2025-04-02', '2025-04-11', '2025-04-13', '3', 5000),
('PM003', 'DG003', 'S003', '2025-04-15', '2025-05-02', '2025-05-07', '0', 0),
('PM004', 'DG004', 'S004', '2025-04-03', '2025-04-12', '2025-04-12', '0', 0),
('PM005', 'DG005', 'S005', '2025-04-17', '2025-04-27', NULL, '1', 0),
('PM006', 'DG006', 'S006', '2025-03-25', '2025-04-05', '2025-04-07', '3', 10000),
('PM007', 'DG007', 'S007', '2025-04-18', '2025-04-28', NULL, '1', 0),
('PM008', 'DG008', 'S008', '2025-03-30', '2025-04-09', '2025-04-09', '0', 0),
('PM009', 'DG009', 'S009', '2025-04-01', '2025-04-10', '2025-04-12', '3', 5000),
('PM010', 'DG010', 'S010', '2025-04-19', '2025-04-29', NULL, '1', 0);

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

CREATE TABLE `phieunhap` (
  `ma_phieu_nhap` varchar(255) NOT NULL,
  `loai_nhap` varchar(100) DEFAULT NULL,
  `so_luong_sach` int(11) DEFAULT NULL,
  `tong_tien` int(11) DEFAULT NULL,
  `ngay_nhap` date DEFAULT NULL,
  `ma_nhan_vien` int(11) DEFAULT NULL,
  `ma_co_so` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieunhap`
--

INSERT INTO `phieunhap` (`ma_phieu_nhap`, `loai_nhap`, `so_luong_sach`, `tong_tien`, `ngay_nhap`, `ma_nhan_vien`, `ma_co_so`) VALUES
('PN001', 'Mua-bán', 20, 400000, '2025-04-01', 1, 'CS001'),
('PN002', 'Nhà xuất bản tài trợ', 18, 360000, '2025-04-05', 1, 'CS001'),
('PN003', 'Mua-bán', 22, 440000, '2025-04-10', 1, 'CS002'),
('PN004', 'Nhà xuất bản tài trợ', 16, 320000, '2025-04-12', 1, 'CS001'),
('PN005', 'Mua-bán', 24, 480000, '2025-04-15', 1, 'CS002');

-- --------------------------------------------------------

--
-- Table structure for table `phieuphat`
--

CREATE TABLE `phieuphat` (
  `ma_phieu_phat` varchar(255) NOT NULL,
  `ma_phieu_muon` varchar(255) DEFAULT NULL,
  `tienphat` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `ma_tai_khoan` varchar(255) NOT NULL,
  `ten_dang_nhap` varchar(255) DEFAULT NULL,
  `mat_khau` varchar(255) DEFAULT '123456789',
  `ma_phan_quyen` varchar(255) DEFAULT NULL,
  `ma_nhan_vien` int(11) DEFAULT NULL,
  `ma_the` varchar(255) DEFAULT NULL
) ;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`ma_tai_khoan`, `ten_dang_nhap`, `mat_khau`, `ma_phan_quyen`, `ma_nhan_vien`, `ma_the`) VALUES
('551688', 'sad', '123456789', 'PQ5', 3, NULL),
('TK1', '0901000001', '123456789', NULL, NULL, '0901000001'),
('TK10', '0901000010', '123456789', NULL, NULL, '0901000010'),
('TK2', '0901000002', '123456789', NULL, NULL, '0901000002'),
('TK3', '0901000003', '123456789', NULL, NULL, '0901000003'),
('TK4', '0901000004', '123456789', NULL, NULL, '0901000004'),
('TK5', '0901000005', '123456789', NULL, NULL, '0901000005'),
('TK6', '0901000006', '123456789', NULL, NULL, '0901000006'),
('TK7', '0901000007', '123456789', NULL, NULL, '0901000007'),
('TK8', '0901000008', '123456789', NULL, NULL, '0901000008'),
('TK9', '0901000009', '123456789', NULL, NULL, '0901000009');

-- --------------------------------------------------------

--
-- Table structure for table `thethanhvien`
--

CREATE TABLE `thethanhvien` (
  `ma_the` varchar(255) NOT NULL,
  `ma_doc_gia` varchar(255) DEFAULT NULL,
  `ngay_cap` date DEFAULT NULL,
  `ngay_het_han` date DEFAULT NULL,
  `trang_thai` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `thethanhvien`
--

INSERT INTO `thethanhvien` (`ma_the`, `ma_doc_gia`, `ngay_cap`, `ngay_het_han`, `trang_thai`) VALUES
('0901000001', 'DG001', '2023-01-01', '2026-01-01', 1),
('0901000002', 'DG002', '2023-02-01', '2026-02-01', 1),
('0901000003', 'DG003', '2023-03-01', '2026-03-01', 1),
('0901000004', 'DG004', '2023-04-01', '2026-04-01', 1),
('0901000005', 'DG005', '2023-05-01', '2026-05-01', 1),
('0901000006', 'DG006', '2023-06-01', '2026-06-01', 1),
('0901000007', 'DG007', '2023-07-01', '2026-07-01', 1),
('0901000008', 'DG008', '2023-08-01', '2026-08-01', 1),
('0901000009', 'DG009', '2023-09-01', '2026-09-01', 1),
('0901000010', 'DG010', '2023-10-01', '2026-10-01', 1);

-- --------------------------------------------------------

--
-- Table structure for table `thongbao`
--

CREATE TABLE `thongbao` (
  `ma_thong_bao` varchar(255) NOT NULL,
  `ma_the` varchar(255) DEFAULT NULL,
  `noi_dung` text DEFAULT NULL,
  `ngay_tao` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `sach`
--
ALTER TABLE `sach`
  ADD PRIMARY KEY (`ma_sach`);

--
-- Indexes for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD PRIMARY KEY (`ma_ctpn`),
  ADD KEY `idx_ctpn_ma_sach` (`ma_sach`),
  ADD CONSTRAINT `fk_ctpn_sach` FOREIGN KEY (`ma_sach`) REFERENCES `sach` (`ma_sach`);

--
-- Indexes for table `chucnang`
--
ALTER TABLE `chucnang`
  ADD PRIMARY KEY (`ma_chuc_nang`),
  ADD UNIQUE KEY `ten_chuc_nang` (`ten_chuc_nang`);

--
-- Indexes for table `cosonhap`
--
ALTER TABLE `cosonhap`
  ADD PRIMARY KEY (`ma_co_so`);

--
-- Indexes for table `dattruoc`
--
ALTER TABLE `dattruoc`
  ADD PRIMARY KEY (`ma_dat_truoc`),
  ADD UNIQUE KEY `ma_phieu_muon` (`ma_phieu_muon`),
  ADD KEY `ma_doc_gia` (`ma_doc_gia`),
  ADD KEY `ma_sach` (`ma_sach`);

--
-- Indexes for table `docgia`
--
ALTER TABLE `docgia`
  ADD PRIMARY KEY (`ma_doc_gia`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`ma_nhan_vien`);

--
-- Indexes for table `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`ma_phan_quyen`,`ma_chuc_nang`),
  ADD KEY `ma_chuc_nang` (`ma_chuc_nang`);

--
-- Indexes for table `phieumuon`
--
ALTER TABLE `phieumuon`
  ADD PRIMARY KEY (`ma_phieu_muon`),
  ADD KEY `ma_doc_gia` (`ma_doc_gia`),
  ADD KEY `ma_sach` (`ma_sach`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`ma_phieu_nhap`),
  ADD KEY `ma_nhan_vien` (`ma_nhan_vien`),
  ADD KEY `ma_co_so` (`ma_co_so`);

--
-- Indexes for table `phieuphat`
--
ALTER TABLE `phieuphat`
  ADD PRIMARY KEY (`ma_phieu_phat`),
  ADD KEY `ma_phieu_muon` (`ma_phieu_muon`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`ma_tai_khoan`),
  ADD UNIQUE KEY `ten_dang_nhap` (`ten_dang_nhap`),
  ADD KEY `ma_nhan_vien` (`ma_nhan_vien`),
  ADD KEY `ma_the` (`ma_the`),
  ADD KEY `ma_phan_quyen` (`ma_phan_quyen`);

--
-- Indexes for table `thethanhvien`
--
ALTER TABLE `thethanhvien`
  ADD PRIMARY KEY (`ma_the`),
  ADD UNIQUE KEY `ma_doc_gia` (`ma_doc_gia`);

--
-- Indexes for table `thongbao`
--
ALTER TABLE `thongbao`
  ADD PRIMARY KEY (`ma_thong_bao`),
  ADD KEY `ma_the` (`ma_the`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  MODIFY `ma_ctpn` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `ma_nhan_vien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dattruoc`
--
ALTER TABLE `dattruoc`
  ADD CONSTRAINT `dattruoc_ibfk_1` FOREIGN KEY (`ma_doc_gia`) REFERENCES `docgia` (`ma_doc_gia`),
  ADD CONSTRAINT `dattruoc_ibfk_2` FOREIGN KEY (`ma_sach`) REFERENCES `sach` (`ma_sach`),
  ADD CONSTRAINT `dattruoc_ibfk_3` FOREIGN KEY (`ma_phieu_muon`) REFERENCES `phieumuon` (`ma_phieu_muon`);

--
-- Constraints for table `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD CONSTRAINT `phanquyen_ibfk_1` FOREIGN KEY (`ma_chuc_nang`) REFERENCES `chucnang` (`ma_chuc_nang`);

--
-- Constraints for table `phieumuon`
--
ALTER TABLE `phieumuon`
  ADD CONSTRAINT `phieumuon_ibfk_1` FOREIGN KEY (`ma_doc_gia`) REFERENCES `docgia` (`ma_doc_gia`),
  ADD CONSTRAINT `phieumuon_ibfk_2` FOREIGN KEY (`ma_sach`) REFERENCES `sach` (`ma_sach`);

--
-- Constraints for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`ma_nhan_vien`) REFERENCES `nhanvien` (`ma_nhan_vien`),
  ADD CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`ma_co_so`) REFERENCES `cosonhap` (`ma_co_so`);

--
-- Constraints for table `phieuphat`
--
ALTER TABLE `phieuphat`
  ADD CONSTRAINT `phieuphat_ibfk_1` FOREIGN KEY (`ma_phieu_muon`) REFERENCES `phieumuon` (`ma_phieu_muon`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`ma_nhan_vien`) REFERENCES `nhanvien` (`ma_nhan_vien`),
  ADD CONSTRAINT `taikhoan_ibfk_2` FOREIGN KEY (`ma_the`) REFERENCES `thethanhvien` (`ma_the`),
  ADD CONSTRAINT `taikhoan_ibfk_3` FOREIGN KEY (`ma_phan_quyen`) REFERENCES `phanquyen` (`ma_phan_quyen`);

--
-- Constraints for table `thethanhvien`
--
ALTER TABLE `thethanhvien`
  ADD CONSTRAINT `thethanhvien_ibfk_1` FOREIGN KEY (`ma_doc_gia`) REFERENCES `docgia` (`ma_doc_gia`);

--
-- Constraints for table `thongbao`
--
ALTER TABLE `thongbao`
  ADD CONSTRAINT `thongbao_ibfk_1` FOREIGN KEY (`ma_the`) REFERENCES `taikhoan` (`ma_the`);

SET FOREIGN_KEY_CHECKS=1; -- Added for robust import
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
