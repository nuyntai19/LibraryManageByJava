CREATE DATABASE QuanLiThuVien;
GO

USE QuanLiThuVien;
GO

SELECT name FROM sys.sql_logins;
CREATE USER tnptp FOR LOGIN tnptp;
EXEC sp_addrolemember 'db_owner', 'tnptp';
ALTER LOGIN tnptp WITH PASSWORD = '123';
EXEC sp_addrolemember 'db_owner', 'tnptp';

-- Bảng Nhân viên
CREATE TABLE NhanVien (
    ID_NhanVien INT IDENTITY(1,1) PRIMARY KEY,
    TenNhanVien NVARCHAR(100),
    GioiTinh NVARCHAR(10),
    NgaySinh DATE,
    DiaChi NVARCHAR(50),
    SoDienThoai NVARCHAR(15),
    Email NVARCHAR(30),
    VaiTro NVARCHAR(50),
	NgayVaoLam DATE,
	TrangThai NVARCHAR(20)
);

select * from NhanVien

CREATE TABLE Sach (
    maSach VARCHAR(255) PRIMARY KEY,
    tenSach VARCHAR(255) NOT NULL,
    tacGia VARCHAR(255),
    theLoai VARCHAR(100),
    ngonNgu VARCHAR(50),
    soLuong INT DEFAULT 0,
    nhaXuatBan VARCHAR(255),
    namXuatBan INT,
    filePdf VARCHAR(255),
    trangThai INT,
    chinhSachMuon INT
);

CREATE TABLE CoSoNhap (
    maCoSo VARCHAR(255) PRIMARY KEY,
    tenCoSo VARCHAR(255) NOT NULL,
    hinhThucChuyeu INT, 
    diaChi VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    ngayHoptac DATE,
    sdt VARCHAR(20),
    trangThai BIT DEFAULT 1
);


CREATE TABLE PhieuNhap (
    maPhieuNhap VARCHAR(255) PRIMARY KEY,
    loaiNhap VARCHAR(100),
    soLuongSach INT,
    tongTien INT,
    ngayNhap DATE DEFAULT GETDATE(),
    maNhanVienXacNhan INT,
    maCoSo VARCHAR(255),
    FOREIGN KEY (maNhanVienXacNhan) REFERENCES NhanVien(maNhanVien),
    FOREIGN KEY (maCoSo) REFERENCES CoSoNhap(maCoSo)
);

CREATE TABLE ChiTietPhieuNhap (
    maCtpn VARCHAR(255) PRIMARY KEY,
    maPhieuNhap VARCHAR(255),
    loaiSach VARCHAR(100),
    donGia DECIMAL(12, 2),
    soLuong INT,
    thanhTien AS (donGia * soLuong) PERSISTED,
    hinhThucNhap VARCHAR(50),
    FOREIGN KEY (maPhieuNhap) REFERENCES PhieuNhap(maPhieuNhap)
);


CREATE TABLE DocGia (
    maDocGia VARCHAR(255) PRIMARY KEY,
    tenDocGia VARCHAR(255) NOT NULL,
    ngaySinh DATE,
    diaChi VARCHAR(255)
);

CREATE TABLE TheThanhVien (
    maThe VARCHAR(255) PRIMARY KEY,
    maDocGia VARCHAR(255) UNIQUE,
    ngayCap DATE,
    ngayHetHan DATE,
    trangThai BIT DEFAULT 1,
    FOREIGN KEY (maDocGia) REFERENCES DocGia(maDocGia)
);

CREATE TABLE TaiKhoan (
    maThe VARCHAR(255) PRIMARY KEY,
    matKhau VARCHAR(255) DEFAULT '123456789',
    vaiTro VARCHAR(50) DEFAULT 'DocGia',
    FOREIGN KEY (maThe) REFERENCES TheThanhVien(maThe)
);

CREATE TABLE ThongBao (
    maThongBao INT IDENTITY(1,1) PRIMARY KEY,
    maThe VARCHAR(255),
    loaiThongBao VARCHAR(100),
    noiDung TEXT,
    ngayTao DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (maThe) REFERENCES TaiKhoan(maThe)
);

CREATE TABLE PhieuMuon (
    maPhieuMuon VARCHAR(255) PRIMARY KEY,
    maDocGia VARCHAR(255),
    maSach VARCHAR(255),
    ngayMuon DATE,
    hanTra DATE,
    ngayTraThucTe DATE,
    trangThai VARCHAR(50),
    FOREIGN KEY (maDocGia) REFERENCES DocGia(maDocGia),
    FOREIGN KEY (maSach) REFERENCES Sach(maSach)
);

CREATE TABLE PhieuPhat (
    maPhieuPhat VARCHAR(255) PRIMARY KEY,
    maPhieuMuon VARCHAR(255) UNIQUE,
    maDocGia VARCHAR(255),
    tenDocGia VARCHAR(255),
    maSach VARCHAR(255),
    tenSach VARCHAR(255),
    soNgayQuaHan INT,
    tienPhat INT,
    FOREIGN KEY (maPhieuMuon) REFERENCES PhieuMuon(maPhieuMuon)
);

CREATE TABLE DatTruoc (
    maDatTruoc VARCHAR(255) PRIMARY KEY,
    maDocGia VARCHAR(255),
    maSach VARCHAR(255),
    ngayDat DATE DEFAULT GETDATE(),
    maPhieuMuon VARCHAR(255) UNIQUE,
    FOREIGN KEY (maDocGia) REFERENCES DocGia(maDocGia),
    FOREIGN KEY (maSach) REFERENCES Sach(maSach),
    FOREIGN KEY (maPhieuMuon) REFERENCES PhieuMuon(maPhieuMuon)
);

CREATE TABLE DanhGia (
    maDanhGia VARCHAR(255) PRIMARY KEY,
    maDocGia VARCHAR(255),
    maSach VARCHAR(255),
    soSao INT CHECK (soSao BETWEEN 1 AND 5),
    nhanXet TEXT,
    ngayDanhGia DATE,
    FOREIGN KEY (maDocGia) REFERENCES DocGia(maDocGia),
    FOREIGN KEY (maSach) REFERENCES Sach(maSach)
);

CREATE TABLE ChucNang (
    maChucNang VARCHAR(255) PRIMARY KEY,
    tenChucNang VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO ChucNang VALUES
('Q1', 'QuanLySach'),
('Q2', 'QuanLyNhanVien'),
('Q3', 'QuanLyPhieuNhapSach'),
('Q4', 'QuanLyDocGia'),
('Q5', 'QuanLyPhanQuyen'),
('Q6', 'QuanLyPhieuMuon'),
('Q7', 'QuanLyTaiKhoan'),
('Q8', 'QuanLyThongKe'),
('Q9', 'QuanLyNguonNhap');

CREATE TABLE PhanQuyen (
    maPhanQuyen VARCHAR(255) PRIMARY KEY,
    tenPhanQuyen VARCHAR(255),
    maThe VARCHAR(255),
    maChucNang VARCHAR(255),
    duocXem BIT DEFAULT 0,
    duocCapNhat BIT DEFAULT 0,
    duocXoa BIT DEFAULT 0,
    FOREIGN KEY (maThe) REFERENCES TaiKhoan(maThe),
    FOREIGN KEY (maChucNang) REFERENCES ChucNang(maChucNang)
);
