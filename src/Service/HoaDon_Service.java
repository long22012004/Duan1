/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.HoaDon;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author viett
 */
public class HoaDon_Service {

    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;

    public List<HoaDon> getAll() {
        String sql = """
                     select * from HoaDon
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            List<HoaDon> listHD = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setIdHTTT(rs.getInt(1));
                hd.setMaHoaDon(rs.getString(2));
                hd.setNgayTao(rs.getObject(3, LocalDate.class));
                hd.setGhiChu(rs.getString(4));
                hd.setIdKhachHang(rs.getInt(5));
                hd.setIdNhanVien(rs.getInt(6));
                hd.setTongTienHang(rs.getFloat(7));
                hd.setTienKhachDua(rs.getFloat(8));
                hd.setTienThua(rs.getFloat(9));
                hd.setTrangThai(rs.getString(10));
                hd.setIdHTTT(rs.getInt(11));
                listHD.add(hd);
            }
            return listHD;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Object[]> getSPDonHang(String maHoaDon) {
        String sql = """
                   {CALL pr_HoaDonChiTiet(?)}
                   """;
        String cols[] = {"maHoaDon", "maSanPham", "tenSanPham", "donGia", "soLuong", "tongTien"};
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, maHoaDon);
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<Object[]> hoanHang(String maHoaDon) {
        String sql = """
                   {CALL pr_hoanHang(?)}
                   """;
        String cols[] = {"idCTSP", "soLuong"};
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, maHoaDon);
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean updateTrangThaiHoanHang(String maHoaDon) {
        String sql = """
                    update HoaDon set TrangThai where MaHoaDon=?
                    """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, maHoaDon);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public List<Object[]> getHoaDon(Integer index) {
        String sql = """
                   {CALL pr_HoaDon(?)}
                   """;
        String cols[] = {"maHoaDon", "maNhanVien", "tenNhanVien", "tenKhachHang", "sdt", "tongTienHang", "ngayTao", "tenTTHD"};
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, (index - 1) * 4);
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Integer countHD() {
        String sql = """
                   select count(*) from hoaDon
                   """;
        int count = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return count;
    }

    public List<Object[]> getLichSuHoaDon() {
        String sql = """
                   {CALL pr_LichSuHoaDon}
                   """;
        String cols[] = {"maHoaDon", "maNhanVien", "tenNhanVien", "tenKhachHang", "tongTienHang", "ngayTao", "tenTTHD"};
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public Object[] getChiTietLSHoaDon(String maHoaDon) {
        String sql = """
                   {CALL pr_ChiTietLSHoaDon(?)}
                   """;
        String cols[] = {"maHoaDon", "tenNhanVien", "tenKhachHang", "sdtKhachHang", "giamGiaDiem", "tenNguoiNhan", "sdtNguoiNhan",
            "diaChiNguoiNhan", "ngayTao", "ghiChu", "hinhThucThanhToan", "trangThaiHoaDon", "tongTienHang", "khachTraTM",
            "khachTraCK", "tienThua", "shipDuTinh", "denDuTinh", "phiShip", "maGiaoDich"};
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maHoaDon);
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                return vals;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<Object[]> getSanPhamLichSuHoaDon(String maHoaDon) {
        String sql = """
                   {CALL pr_SanPhamLichSuHoaDon(?)}
                   """;
        String cols[] = {"maSanPham", "tenSanPham", "donGia", "soLuong", "tongTien"};
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, maHoaDon);
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<Object[]> findHoaDonTheoGia(Double from, Double to) {
        String sql = """
                   {CALL pr_TimKiemHoaDonTheoGia(?,?)}
                   """;
        String cols[] = {"maHoaDon", "maNhanVien", "tenNhanVien", "tenKhachHang", "sdt", "tongTienHang", "ngayTao", "tenTTHD"};
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, from);
            ps.setObject(2, to);
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean deleteHoaDon(Integer idHoaDon) {
        String sql = """
                   delete HoaDon where idHoaDon=?
                   """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, idHoaDon);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean deleteHoaDonChiTiet(Integer idHoaDon) {
        String sql = """
                   delete hoaDonChiTiet where idHoaDon=?
                   """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, idHoaDon);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean deleteHoaDonChiTietByID(Integer idCTSP, Integer idHoaDon) {
        String sql = """
                   delete hoaDonChiTiet where idSPCT=? and idHoaDon=?
                   """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, idCTSP);
            ps.setObject(2, idHoaDon);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public List<Object[]> DanhSachHoaDon() {
        String sql = """
                   {CALL pr_DanhSachHoaDon}
                   """;
        String cols[] = {"maHoaDon", "tenNhanVien", "trangThaiHoaDon", "ngayTao"};
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public HoaDon getHoaDonByMa(String maHoaDon) {
        String sql = """
                    select ID_HoaDon from HoaDon where MAHoaDon=?
                    """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, maHoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setIdHoaDon(rs.getInt(1));
                return hd;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean createBill(HoaDon hd) {
        String sql = """
                   insert into HoaDon(MaHoaDon,ID_NhanVien,TrangThai,ngayTao) values(?,?,?,?)
                   """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, hd.getMaHoaDon());
            ps.setObject(2, hd.getIdNhanVien());
            ps.setObject(3, hd.getTrangThai());
            ps.setObject(4, hd.getNgayTao());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public HoaDon selectTop1DESC() {
        String sql = """
                   select top 1 * from HoaDon order by HoaDon.ID_HoaDon desc
                   """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setIdHoaDon(rs.getInt(1));
                hd.setMaHoaDon(rs.getString(2));
                hd.setNgayTao(rs.getObject(3, LocalDate.class));
                hd.setGhiChu(rs.getString(4));
                hd.setIdKhachHang(rs.getInt(5));
                hd.setIdNhanVien(rs.getInt(6));
                hd.setTongTienHang(rs.getFloat(7));
                hd.setTienKhachDua(rs.getFloat(8));
                hd.setTienThua(rs.getFloat(9));
                hd.setTrangThai(rs.getString(10));
                hd.setIdHTTT(rs.getInt(11));

                return hd;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean ThanhToanHoaDon(HoaDon hd, String maHD) {
        String sql = """
                  UPDATE HOADON
                       SET 
                          NgayTao = ?
                          ,GhiChu = ?
                          ,ID_KhachHang = ?
                          ,ID_NhanVien = ?
                          ,TongTien = ?
                          ,TienKhachDua = ?
                          ,TienTraLai = ?
                          ,TrangThai = ?
                          ,ID_HinhThucThanhToan =?
                     WHERE MaHoaDon=?
                    """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, hd.getNgayTao());
            ps.setObject(2, hd.getGhiChu());
            ps.setObject(3, hd.getIdKhachHang());
            ps.setObject(4, hd.getIdNhanVien());
            ps.setObject(5, hd.getTongTienHang());
            ps.setObject(6, hd.getTienKhachDua());
            ps.setObject(7, hd.getTienThua());
            ps.setObject(8, hd.getTrangThai());
            ps.setObject(9, hd.getIdHTTT());
            ps.setObject(10, maHD);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public List<HoaDon> getDaThanhToan(String tt) {
        String sql = """
                   select * from HoaDon where TrangThai=?
                   """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, tt);
            ResultSet rs = ps.executeQuery();
            List<HoaDon> list = new ArrayList<>();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setIdHoaDon(rs.getInt(1));
                hd.setMaHoaDon(rs.getString(2));
                hd.setNgayTao(rs.getObject(3, LocalDate.class));
                hd.setGhiChu(rs.getString(4));
                hd.setIdKhachHang(rs.getInt(5));
                hd.setIdNhanVien(rs.getInt(6));
                hd.setTongTienHang(rs.getFloat(7));
                hd.setTienKhachDua(rs.getFloat(8));
                hd.setTienThua(rs.getFloat(9));
                hd.setTrangThai(rs.getString(10));
                hd.setIdHTTT(rs.getInt(11));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

 
    
    public List<HoaDon> getDTT() {
        String sql = """
                     select * from HoaDon where TrangThai=N'Đã Thanh Toán'
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            List<HoaDon> listHD = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setIdHTTT(rs.getInt(1));
                hd.setMaHoaDon(rs.getString(2));
                hd.setNgayTao(rs.getObject(3, LocalDate.class));
                hd.setGhiChu(rs.getString(4));
                hd.setIdKhachHang(rs.getInt(5));
                hd.setIdNhanVien(rs.getInt(6));
                hd.setTongTienHang(rs.getFloat(7));
                hd.setTienKhachDua(rs.getFloat(8));
                hd.setTienThua(rs.getFloat(9));
                hd.setTrangThai(rs.getString(10));
                hd.setIdHTTT(rs.getInt(11));
                listHD.add(hd);
            }
            return listHD;
        } catch (Exception e) {
        }
        return null;
    }
    
     
    public List<HoaDon> getCTT() {
        String sql = """
                     select * from HoaDon where TrangThai=N'Chờ Thanh Toán'
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            List<HoaDon> listHD = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setIdHTTT(rs.getInt(1));
                hd.setMaHoaDon(rs.getString(2));
                hd.setNgayTao(rs.getObject(3, LocalDate.class));
                hd.setGhiChu(rs.getString(4));
                hd.setIdKhachHang(rs.getInt(5));
                hd.setIdNhanVien(rs.getInt(6));
                hd.setTongTienHang(rs.getFloat(7));
                hd.setTienKhachDua(rs.getFloat(8));
                hd.setTienThua(rs.getFloat(9));
                hd.setTrangThai(rs.getString(10));
                hd.setIdHTTT(rs.getInt(11));
                listHD.add(hd);
            }
            return listHD;
        } catch (Exception e) {
        }
        return null;
    }

   
}
