IF EXISTS (SELECT name FROM sys.databases WHERE name = N'DB_NHOM_5_DUAN')
   DROP DATABASE DB_NHOM_5_DUAN
GO
-- Create database
CREATE DATABASE DB_NHOM_5_DUAN
GO
USE DB_NHOM_5_DUAN
go
CREATE TABLE [NhaCungCap] (
  [ID_NhaCungCap] Integer identity  PRIMARY KEY,
  [TenNhaCungCap] nvarchar(255),
  [DiaChi] nvarchar(255),
  [Sdt] nvarchar(255),
  [Email] nvarchar(255),
  [TrangThai] bit
)
GO

CREATE TABLE [MauSac] (
  [ID_MauSac] Integer identity PRIMARY KEY,
  [TenMauSac] nvarchar(255)
)
GO

CREATE TABLE [KieuDang] (
  [ID_KieuDang] Integer identity PRIMARY KEY,
  [TenKieuDang] nvarchar(255)
)
GO

CREATE TABLE [KichThuoc] (
  [ID_KichThuoc] Integer identity PRIMARY KEY,
  [TenKichThuoc] nvarchar(255)
)
GO

CREATE TABLE [ChatLieu] (
  [ID_ChatLieu] Integer identity PRIMARY KEY,
  [TenChatLieu] nvarchar(255)
)
GO

create table [XuatXu](
[ID_XuatXu] Integer identity PRIMARY KEY,
[QuocGia] nvarchar(255)
)
go

CREATE TABLE [Dep] (
  [ID_Dep] Integer identity PRIMARY KEY,
  [MaDep] nvarchar(255),
  [TenDep] nvarchar(255),
   [ID_ChatLieu] Integer,
  [ID_NhaCungCap] Integer,
  [ID_XuatXu] Integer,
  [ID_KieuDang] Integer,
  [TrangThai] bit,
)
GO

CREATE TABLE [DepChiTiet] (
  [Id_DepChiTiet] Integer identity PRIMARY KEY,
  [ID_Dep] Integer,
  [MoTa] nvarchar(255),
  [ID_MauSac] Integer,
  [ID_KichThuoc] Integer,
  [DonGia] Float,
  [SoLuong] Integer
)
GO


CREATE TABLE [ChucVu] (
  [ID_ChucVu] Integer identity PRIMARY KEY,
  [TenChucVu] nvarchar(255)
)
GO




CREATE TABLE [TaiKhoanNhanvien] (
  [ID_TaiKhoanNhanVien] Integer identity PRIMARY KEY,
  [Username] nvarchar(255),
  [Password] nvarchar(255),
  [TrangThai] bit
)
GO

CREATE TABLE [NhanVien] (
  [ID_NhanVien] Integer identity PRIMARY KEY,
  [MaNhanVien] nvarchar(255),
  [TenNhanVien] nvarchar(255),
  [DiaChi] nvarchar(255),
  [Sdt] nvarchar(255),
  [Email] nvarchar(255),
  [TrangThai] bit,
  [GioiTinh] bit,
  [Luong] float,
  [NamSinh] Integer,
  [ID_ChucVu] Integer,
  [ID_TaiKhoanNhanVien] Integer
)
GO



CREATE TABLE [KhachHang] (
  [ID_KhachHang] Integer identity PRIMARY KEY,
  [MaKhachHang] nvarchar(255),
  [TenKhachHang] nvarchar(255),
  [DiaChi] nvarchar(255),
  [Sdt] nvarchar(255),
  [Email] nvarchar(255),
  [GioiTinh] bit,
  [NamSinh] Integer
)
GO


CREATE TABLE [HinhThucThanhToan] (
  [ID_HinhThucThanhToan] Integer identity PRIMARY KEY,
  [LoaiHinhThucThanhToan] nvarchar(255)
)
GO



CREATE TABLE [HOADON] (
  [ID_HoaDon] Integer identity PRIMARY KEY,
  [MaHoaDon] nvarchar(255),
  [NgayTao] date,
  [GhiChu] nvarchar(255),
  [ID_KhachHang] Integer,
  [ID_NhanVien] Integer,
  [TongTien] float,
  [TienKhachDua] float,
  [TienTraLai] float,
  [TrangThai] nvarchar(255),
  [ID_HinhThucThanhToan] Integer
)
GO

create table [HoaDonChiTiet](
[ID_HoaDonChiTiet] Integer identity PRIMARY KEY,
[ID_DepChiTiet] Integer,
[ID_HoaDon] Integer,
[SoLuong] Integer
)
go

CREATE TABLE [LichSuHoaDon] (
  [ID_LichSuHoaDon] Integer identity PRIMARY KEY,
  [TrangThai] bit,
  [GhiChu] nvarchar(255),
  [ID_KhachHang] Integer,
  [ID_HoaDon] Integer
)
GO











ALTER TABLE [LichSuHoaDon] ADD FOREIGN KEY ([ID_KhachHang]) REFERENCES [KhachHang] ([ID_KhachHang])
GO

ALTER TABLE [LichSuHoaDon] ADD FOREIGN KEY ([ID_HoaDon]) REFERENCES [HOADON] ([ID_HoaDon])
GO

ALTER TABLE [HOADON] ADD FOREIGN KEY ([ID_KhachHang]) REFERENCES [KhachHang] ([ID_KhachHang])
GO

ALTER TABLE [HOADON] ADD FOREIGN KEY ([ID_NhanVien]) REFERENCES [NhanVien] ([ID_NhanVien])
GO



ALTER TABLE [HoaDon] ADD FOREIGN KEY ([ID_HinhThucThanhToan]) REFERENCES [HinhThucThanhToan] ([ID_HinhThucThanhToan])
GO

ALTER TABLE [HoaDonChiTiet] ADD FOREIGN KEY ([ID_DepChiTiet]) REFERENCES [DepChiTiet] ([ID_DepChiTiet])
GO

ALTER TABLE [NhanVien] ADD FOREIGN KEY ([ID_ChucVu]) REFERENCES [ChucVu] ([ID_ChucVu])
GO

ALTER TABLE [NhanVien] ADD FOREIGN KEY ([ID_TaiKhoanNhanVien]) REFERENCES [TaiKhoanNhanvien] ([ID_TaiKhoanNhanVien])
GO


ALTER TABLE [DepChiTiet] ADD FOREIGN KEY ([ID_Dep]) REFERENCES [Dep] ([ID_Dep])
GO

ALTER TABLE [Dep] ADD FOREIGN KEY ([ID_NhaCungCap]) REFERENCES [NhaCungCap] ([ID_NhaCungCap])
GO

ALTER TABLE [DepChiTiet] ADD FOREIGN KEY ([ID_MauSac]) REFERENCES [MauSac] ([ID_MauSac])
GO

ALTER TABLE [DepChiTiet] ADD FOREIGN KEY ([ID_KichThuoc]) REFERENCES [KichThuoc] ([ID_KichThuoc])
GO

ALTER TABLE [Dep] ADD FOREIGN KEY ([ID_KieuDang]) REFERENCES [KieuDang] ([ID_KieuDang])
GO

ALTER TABLE [Dep] ADD FOREIGN KEY ([ID_ChatLieu]) REFERENCES [ChatLieu] ([ID_ChatLieu])
GO

ALTER TABLE [Dep] ADD FOREIGN KEY ([ID_XuatXu]) REFERENCES [XuatXu] ([ID_XuatXu])
GO

ALTER TABLE [HoaDonChiTiet] ADD FOREIGN KEY (ID_HoaDon) REFERENCES [HoaDon] ([ID_HoaDon])
GO



Insert into NhaCungCap(TenNhaCungCap,DiaChi,Sdt,Email,TrangThai) values('Nike',N'131 Trần Hưng Đạo,Q5,TPHCM','098256478','thanh@gmail.com',1)
go
Insert into NhaCungCap(TenNhaCungCap,DiaChi,Sdt,Email,TrangThai) values('Adidas',N'45 Nguyễn Trãi,Q5,TPHCM','098256456','longnh@gmail.com',0)
go
Insert into NhaCungCap(TenNhaCungCap,DiaChi,Sdt,Email,TrangThai) values('Lumi',N'34 Trương Dĩnh,Q3,hà Nội','0983546287','thanhbro2004@gmail.com',1)
go
Insert into NhaCungCap(TenNhaCungCap,DiaChi,Sdt,Email,TrangThai) values('MiuShop',N'45 Nguyễn Xiễn,Thanh Xuân,Hà Nội','0782797321','longne2004zz@gmail.com',1)
go
Insert into NhaCungCap(TenNhaCungCap,DiaChi,Sdt,Email,TrangThai) values('PiLap',N'34 Lê Đại Thanh,Q4,TPHCM','0778531758','hungnv@gmail.com',0)
go

INSERT INTO [MauSac] ( [TenMauSac]) VALUES
(N'Red'),          -- Màu đỏ
( N'Blue'),         -- Màu xanh dương
( N'Green'),        -- Màu xanh lá cây
( N'Yellow'),       -- Màu vàng
( N'Black');        -- Màu đen

INSERT INTO [KieuDang] ( [TenKieuDang]) VALUES
  (N'Dép platform với đế cao'),
  (N'Dép mules mũi nhọn'),
  (N'Dép chunky sneakers'),
  (N'Dép thể thao với đèn LED'),
  (N'Dép suốt với quai da chéo');

  INSERT INTO [KichThuoc] ([TenKichThuoc]) VALUES
  ('35'),
  ('38'),
  ('42'),
  ('39'),
  ('40');


  INSERT INTO [ChatLieu] ([TenChatLieu]) VALUES
  (N'Da bò'),
  (N'Da lộn'),
  (N'Vải canvas'),
  (N'Cao su tổng hợp'),
  (N'Da nhân tạo');

  
  INSERT INTO [XuatXu] ([QuocGia]) VALUES
  (N'Trung Quốc'),
  (N'Việt Nam'),
  (N'Lào'),
  (N'Nhật Bản'),
  (N'Thái Lan');

  INSERT INTO [Dep] ([MaDep],[TenDep], [ID_ChatLieu],[ID_NhaCungCap], [ID_XuatXu],[ID_KieuDang], [TrangThai]) VALUES
  ('SP01',N'Dép Sneakers Nam', 1, 3,4,2,1),
  ('SP02',N'Dép Sandal Nữ', 1, 2,1, 4,0),
  ('SP03',N'Dép Bít Nữ', 1, 4,5 ,5,1),
  ('SP04',N'Dép Thể Thao Unisex', 1,5 ,3, 1,0),
  ('SP05',N'Dép Slip-On Nam', 1,  1,2, 3,0);

  INSERT INTO [DepChiTiet] ([ID_Dep], [MoTa], [ID_MauSac], [ID_KichThuoc],[DonGia],[SoLuong]) VALUES
  (1, N'Đôi sneakers nam phong cách và thoải mái', 1, 1, 50000, 15),
  (2,  N'Sandal nữ đẹp và thoải mái cho mùa hè', 2, 2, 60000, 25),
  (3, N'Dép bít nữ kiểu dáng sang trọng',3, 3, 57000, 20),
  (4, N'Dép thể thao unisex phù hợp cho mọi hoạt động', 4, 4, 45000, 4),
  (5, N'Dép slip-on nam đơn giản và tiện lợi', 5, 5, 55000, 9);

  insert into [ChucVu] ([TenChucVu]) values (N'Nhân Viên'),(N'Quản Lý');

   INSERT INTO [TaiKhoanNhanVien] ([Username], [Password], [TrangThai]) VALUES
  (N'quanly1', N'password123', 1),
  (N'nhanvien1', N'employeepass', 1),
  (N'keToanUser', N'accountingpass', 1),
  (N'khoUser', N'warehousepass', 1),
  (N'marketingUser', N'marketingpass', 1);

   INSERT INTO [NhanVien] ([MaNhanVien],[TenNhanVien], [DiaChi], [Sdt], [Email], [TrangThai], [GioiTinh], [Luong], [NamSinh], [ID_ChucVu], [ID_TaiKhoanNhanVien]) VALUES
  ('NV01',N'Nguyễn Văn A', N'123 Đường ABC, Quận 1, TP.HCM', N'0123456789', N'van.a@example.com', 1, 1, 10000000, 1990, 1, 1),
  ('NV02',N'Lê Thị B', N'456 Đường XYZ, Quận 2, TP.HCM', N'0987654321', N'thi.b@example.com', 1, 0, 8000000, 1985, 2, 2),
  ('NV03',N'Trần Văn C', N'789 Đường MNO, Quận 3, TP.HCM', N'0369871234', N'van.c@example.com', 1, 1, 12000000, 1988, 2, 3),
  ('NV04',N'Hồ Thị D', N'101 Đường PQR, Quận 4, TP.HCM', N'0123456780', N'thi.d@example.com', 1, 0, 9000000, 1987, 1, 4),
  ('NV05',N'Nguyễn Nam E', N'202 Đường LMN, Quận 5, TP.HCM', N'0987123456', N'nam.e@example.com', 1, 1, 11000000, 1992, 2, 5);



  INSERT INTO [KhachHang] ([MaKhachHang],[TenKhachHang], [DiaChi], [Sdt], [Email], [GioiTinh], [NamSinh]) VALUES
  ('KH01',N'Nguyễn Thị A', N'123 Đường ABC, Quận 1, TP.HCM', N'0123456789', N'a.nguyen@example.com', 0, 1985),
  ('KH02',N'Trần Văn B', N'456 Đường XYZ, Quận 2, TP.HCM', N'0987654321', N'b.tran@example.com', 1, 1990),
  ('KH03',N'Lê Thị C', N'789 Đường MNO, Quận 3, TP.HCM', N'0369871234', N'c.le@example.com', 0, 1988),
  ('KH04',N'Phạm Văn D', N'101 Đường PQR, Quận 4, TP.HCM', N'0123456780', N'd.pham@example.com', 1, 1992),
  ('KH05',N'Huỳnh Nam E', N'202 Đường LMN, Quận 5, TP.HCM', N'0987123456', N'e.huynh@example.com', 1, 1987);


INSERT INTO [HinhThucThanhToan] ([LoaiHinhThucThanhToan]) VALUES
  (N'Tiền mặt'),
  (N'Chuyển khoản ngân hàng'),
  (N'Quẹt thẻ');




  INSERT INTO [HOADON] ([MaHoaDon],[NgayTao], [GhiChu], [ID_KhachHang], [ID_NhanVien], [TongTien], [TienKhachDua], [TienTraLai],[TrangThai],[ID_HinhThucThanhToan]) VALUES
  ('HD01','2023-01-10', N'Hóa đơn mua hàng số 001', 1, 1, 500000,  550000, 50000, N'Đã Thanh Toán',1),
  ('HD02','2023-02-15', N'Hóa đơn mua hàng số 002', 2, 2, 750000,  800000, 50000, N'Đã Thanh Toán',2),
  ('HD03','2023-03-20', N'Hóa đơn mua hàng số 003', 3, 3, 1200000,  1200000, 50000, N'Chờ Thanh Toán',3),
  ('HD04','2023-04-25', N'Hóa đơn mua hàng số 004', 4, 4, 2000000,  2000000, 50000, N'Hủy Đơn Hàng',2),
  ('HD05','2023-05-30', N'Hóa đơn mua hàng số 005', 5, 5, 900000, 900000, 50000, N'Chờ Thanh Toán',1);

  INSERT INTO [HoaDonChiTiet] ([ID_DepChiTiet],[ID_HoaDon],SoLuong) values(1,4,5),
	 (4,2,5),
	 (5,3,5),
	 (3,4,5),
	 (2,5,5)


INSERT INTO [LichSuHoaDon] ([TrangThai], [GhiChu], [ID_KhachHang], [ID_HoaDon]) VALUES
  (1, N'Hóa đơn đã được xác nhận', 1, 1),
  (1, N'Hóa đơn đã thanh toán', 2, 2),
  (1, N'Hóa đơn đã giao hàng', 3, 3),
  (1, N'Hóa đơn đã hoàn tất', 4, 4),
  (1, N'Hóa đơn đã được xác nhận', 5, 5);







create proc ps_SanPhamChiTiet(@idDep int)
as begin 
select Dep.TenDep tenDep,
MauSac.TenMauSac tenMau,
KichThuoc.TenKichThuoc tenKichThuoc,
DepChiTiet.SoLuong soLuong,
DepChiTiet.DonGia donGia 
from DepChiTiet inner join MauSac on MauSac.ID_MauSac=DepChiTiet.ID_MauSac inner join KichThuoc on KichThuoc.ID_KichThuoc = DepChiTiet.ID_KichThuoc inner join Dep
on Dep.ID_Dep=DepChiTiet.ID_Dep where Dep.ID_Dep=@idDep
end
go



create proc [dbo].[pr_DanhSachHoaDon]
as begin
select	HoaDon.MaHoaDon maHoaDon,
		NhanVien.TenNhanVien tenNhanVien,
		HOADON.TrangThai trangThaiHoaDon,
		HoaDon.ngayTao ngayTao
from HoaDon 
join NhanVien on HoaDon.ID_NhanVien=NhanVien.ID_NhanVien
end
go

create proc pr_KhachHangBought(@idKhachHang int)
as begin
select Dep.MaDep maSanPham,
		Dep.TenDep tenSanPham,
		DepChiTiet.DonGia donGia,
		HOADONChiTiet.SoLuong soLuong,
		(DepChiTiet.DonGia * HoaDonChiTiet.SoLuong) tongTien
from HoaDon inner join HoaDonChiTiet on HoaDonChiTiet.ID_HoaDon=HOADON.ID_HoaDon
	join KhachHang on HoaDon.ID_KhachHang=KhachHang.ID_KhachHang
	join DepChiTiet on DepChiTiet.Id_DepChiTiet=HoaDonChiTiet.ID_DepChiTiet
	inner join Dep on Dep.ID_Dep=DepChiTiet.ID_Dep
	where KhachHang.ID_KhachHang=@idKhachHang
end
go

create proc ps_HoaDonSanPham(@idHoaDon int)
as begin 
select 
d.MaDep maDep,
d.TenDep tenDep,
dct.DonGia donGia,
hdct.SoLuong soLuong,
(dct.DonGia*hdct.SoLuong) tongTien,
hd.TrangThai trangThai
 from HoaDon hd inner join HoaDonChiTiet hdct on hdct.ID_HoaDon=hd.ID_HoaDon
 inner join DepChiTiet dct on dct.Id_DepChiTiet=hdct.ID_DepChiTiet inner join Dep d on d.ID_Dep=dct.ID_Dep
where hd.ID_HoaDon=@idHoaDon
end
go

create proc [dbo].[pr_LocDoanhThuTheoNgay](@from date,@to date)
as begin
	select HoaDon.NgayTao ngayTao,
	SUM(TongTien) doanhThu
	from HoaDon where HoaDon.NgayTao between @from and @to and TrangThai=N'Đã Thanh Toán' group by HoaDon.NgayTao
end
GO

create proc [dbo].[pr_DoanhThuSanPham]
as begin
select distinct	Dep.MaDep maSanPham,
		Dep.TenDep tenSanPham,
		DepChiTiet.SoLuong soLuongTon,
		DepChiTiet.DonGia donGia,
		SUM(HoaDonChiTiet.SoLuong) soLuongBanDuoc,
		SUM(HoaDonChiTiet.SoLuong*DepChiTiet.DonGia) doanhThuTuSanPham
from DepChiTiet join Dep on DepChiTiet.ID_Dep=Dep.ID_Dep
					join HoaDonChiTiet on HoaDonChiTiet.ID_DepChiTiet=DepChiTiet.Id_DepChiTiet
					join HOADON on HoaDonChiTiet.ID_HoaDon=HOADON.ID_HoaDon
					where HOADON.TrangThai=N'Đã Thanh Toán'
					group by Dep.MaDep,Dep.TenDep,DepChiTiet.SoLuong,DepChiTiet.DonGia
end
GO


create proc [dbo].[pr_DoanhThuSanPhamTheoTenSanPham](@loaiSanPham nvarchar(50))
as begin
select distinct	Dep.MaDep maSanPham,
		Dep.TenDep tenSanPham,
		DepChiTiet.SoLuong soLuongTon,
		DepChiTiet.donGia donGia,
		SUM(HoaDonChiTiet.SoLuong) soLuongBanDuoc,
		SUM(HoaDonChiTiet.SoLuong*DepChiTiet.DonGia) doanhThuTuSanPham
from DepChiTiet join Dep on DepChiTiet.ID_Dep=Dep.ID_Dep
					join HoaDonChiTiet on HoaDonChiTiet.ID_DepChiTiet=DepChiTiet.Id_DepChiTiet
					join HoaDon on HoaDonChiTiet.ID_HoaDon=HoaDon.ID_HoaDon
					where Dep.TenDep=@loaiSanPham and HoaDon.TrangThai=N'Đã Thanh Toán'
					group by Dep.MaDep,Dep.TenDep,DepChiTiet.SoLuong,DepChiTiet.DonGia
end
GO

create proc [dbo].[pr_DoanhThuSanPhamNhieuNhat]
as begin
select distinct	top 1 Dep.MaDep maSanPham,
		Dep.TenDep tenSanPham,
		DepChiTiet.SoLuong soLuongTon,
		DepChiTiet.donGia donGia,
		SUM(HoaDonChiTiet.SoLuong) soLuongBanDuoc,
		SUM(HoaDonChiTiet.SoLuong*DepChiTiet.DonGia) doanhThuTuSanPham
from DepChiTiet join Dep on DepChiTiet.ID_Dep=Dep.ID_Dep
					join HoaDonChiTiet on HoaDonChiTiet.ID_DepChiTiet=DepChiTiet.Id_DepChiTiet
					join HoaDon on HoaDonChiTiet.ID_HoaDon=HoaDon.ID_HoaDon
					where HoaDon.TrangThai=N'Đã Thanh Toán'
					group by Dep.MaDep,Dep.TenDep,DepChiTiet.SoLuong,DepChiTiet.DonGia
					order by doanhThuTuSanPham desc
end
GO

create proc [dbo].[pr_DoanhThuSanPhamItNhat]
as begin
select distinct top 1 Dep.MaDep maSanPham,
		Dep.TenDep tenSanPham,
		DepChiTiet.SoLuong soLuongTon,
		DepChiTiet.donGia donGia,
		SUM(HoaDonChiTiet.SoLuong) soLuongBanDuoc,
		SUM(HoaDonChiTiet.SoLuong*DepChiTiet.DonGia) doanhThuTuSanPham
from DepChiTiet join Dep on DepChiTiet.ID_Dep=Dep.ID_Dep
					join HoaDonChiTiet on HoaDonChiTiet.ID_DepChiTiet=DepChiTiet.Id_DepChiTiet
					join HoaDon on HoaDonChiTiet.ID_HoaDon=HoaDon.ID_HoaDon
					where HoaDon.TrangThai=N'Đã Thanh Toán'
					group by Dep.MaDep,Dep.TenDep,DepChiTiet.SoLuong,DepChiTiet.DonGia
					order by doanhThuTuSanPham asc
end
GO

create proc [dbo].[pr_SanPhamBanNhieuNhat]
as begin
select distinct top 1 Dep.MaDep maSanPham,
		Dep.TenDep tenSanPham,
		DepChiTiet.SoLuong soLuongTon,
		DepChiTiet.donGia donGia,
		SUM(HoaDonChiTiet.SoLuong) soLuongBanDuoc,
		SUM(HoaDonChiTiet.SoLuong*DepChiTiet.DonGia) doanhThuTuSanPham
from DepChiTiet join Dep on DepChiTiet.ID_Dep=Dep.ID_Dep
					join HoaDonChiTiet on HoaDonChiTiet.ID_DepChiTiet=DepChiTiet.Id_DepChiTiet
					join HoaDon on HoaDonChiTiet.ID_HoaDon=HoaDon.ID_HoaDon
					where HoaDon.TrangThai=N'Đã Thanh Toán'
					group by Dep.MaDep,Dep.TenDep,DepChiTiet.SoLuong,DepChiTiet.DonGia
					order by soLuongBanDuoc desc
end
GO


create proc [dbo].[pr_SanPhamBanItNhat]
as begin
select distinct top 1 Dep.MaDep maSanPham,
		Dep.TenDep tenSanPham,
		DepChiTiet.SoLuong soLuongTon,
		DepChiTiet.donGia donGia,
		SUM(HoaDonChiTiet.SoLuong) soLuongBanDuoc,
		SUM(HoaDonChiTiet.SoLuong*DepChiTiet.DonGia) doanhThuTuSanPham
from DepChiTiet join Dep on DepChiTiet.ID_Dep=Dep.ID_Dep
					join HoaDonChiTiet on HoaDonChiTiet.ID_DepChiTiet=DepChiTiet.Id_DepChiTiet
					join HoaDon on HoaDonChiTiet.ID_HoaDon=HoaDon.ID_HoaDon
					where HoaDon.TrangThai=N'Đã Thanh Toán'
					group by Dep.MaDep,Dep.TenDep,DepChiTiet.SoLuong,DepChiTiet.DonGia
					order by soLuongBanDuoc asc
end
GO

