/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.HoaDon1;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author viett
 */
public class HoaDon_Service1 {

    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;

    public List<HoaDon1> getAll() {
        sql = "select nv.MaNhanVien as manv,hd.TongTien as tongtien,nv.TenNhanVien as tennv,kh.TenKhachHang as tenkh,hd.NgayTao as nt,hd.TrangThai as tt,kh.Sdt as sdt,kh.DiaChi as dc,hd.ID_HoaDon as id from HoaDon hd inner join "
                + "NhanVien nv on nv.ID_NhanVien=hd.ID_NhanVien inner join KhachHang kh on kh.ID_KhachHang=hd.ID_KhachHang";
        List<HoaDon1> listH = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            List<HoaDon1> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon1 hd = new HoaDon1();
                hd.setId(rs.getInt("id"));
                hd.setMaNV(rs.getString("manv"));
                hd.setTenNhanVien(rs.getString("tennv"));
                hd.setTenKhachHang(rs.getString("tenkh"));
                hd.setTrangThai(rs.getString("tt"));
                hd.setDiaChi(rs.getString("dc"));
                hd.setNgayTao(rs.getObject("nt", Date.class));
                hd.setSdt(rs.getString("sdt"));
                hd.setTongTien(rs.getFloat("tongtien"));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }

    }
    
    
      public List<HoaDon1> timKiem(String ngaytao) {
        sql = "select nv.MaNhanVien as manv,hd.TongTien as tongtien,nv.TenNhanVien as tennv,kh.TenKhachHang as tenkh,hd.NgayTao as nt,hd.TrangThai as tt,kh.Sdt as sdt,kh.DiaChi as dc,hd.ID_HoaDon as id from HoaDon hd inner join "
                + "NhanVien nv on nv.ID_NhanVien=hd.ID_NhanVien inner join KhachHang kh on kh.ID_KhachHang=hd.ID_KhachHang where hd.NgayTao = ?";
        List<HoaDon1> listH = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ngaytao);
            List<HoaDon1> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon1 hd = new HoaDon1();
                hd.setId(rs.getInt("id"));
                hd.setMaNV(rs.getString("manv"));
                hd.setTenNhanVien(rs.getString("tennv"));
                hd.setTenKhachHang(rs.getString("tenkh"));
                hd.setTrangThai(rs.getString("tt"));
                hd.setDiaChi(rs.getString("dc"));
                hd.setNgayTao(rs.getObject("nt", Date.class));
                hd.setSdt(rs.getString("sdt"));
                hd.setTongTien(rs.getFloat("tongtien"));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }

    }

    public List<Object[]> HoaDonSanPham(int id) {
        String sql = """
           {CALL ps_HoaDonSanPham(?)}
                    """;
        String cols[] = {"maDep", "tenDep", "donGia", "soLuong", "tongTien", "trangThai"};
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
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

    public List<HoaDon1> getDTT() {
        sql = """
              select nv.MaNhanVien as manv,hd.TongTien as tongtien,nv.TenNhanVien as tennv,kh.TenKhachHang as tenkh,hd.NgayTao as nt,hd.TrangThai as tt,kh.Sdt as sdt,kh.DiaChi as dc,hd.ID_HoaDon as id from HoaDon hd inner join 
                              NhanVien nv on nv.ID_NhanVien=hd.ID_NhanVien inner join KhachHang kh on kh.ID_KhachHang=hd.ID_KhachHang where hd.TrangThai=N'Đã Thanh Toán'
                            """;
        List<HoaDon1> listH = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            List<HoaDon1> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon1 hd = new HoaDon1();
                hd.setId(rs.getInt("id"));
                hd.setMaNV(rs.getString("manv"));
                hd.setTenNhanVien(rs.getString("tennv"));
                hd.setTenKhachHang(rs.getString("tenkh"));
                hd.setTrangThai(rs.getString("tt"));
                hd.setDiaChi(rs.getString("dc"));
                hd.setNgayTao(rs.getObject("nt", Date.class));
                hd.setSdt(rs.getString("sdt"));
                hd.setTongTien(rs.getFloat("tongtien"));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }
    
        public List<HoaDon1> getCTT() {
        sql = """
              select nv.MaNhanVien as manv,hd.TongTien as tongtien,nv.TenNhanVien as tennv,kh.TenKhachHang as tenkh,hd.NgayTao as nt,hd.TrangThai as tt,kh.Sdt as sdt,kh.DiaChi as dc,hd.ID_HoaDon as id from HoaDon hd inner join 
                              NhanVien nv on nv.ID_NhanVien=hd.ID_NhanVien inner join KhachHang kh on kh.ID_KhachHang=hd.ID_KhachHang where hd.TrangThai=N'Chờ Thanh Toán'
                            """;
        List<HoaDon1> listH = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            List<HoaDon1> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon1 hd = new HoaDon1();
                hd.setId(rs.getInt("id"));
                hd.setMaNV(rs.getString("manv"));
                hd.setTenNhanVien(rs.getString("tennv"));
                hd.setTenKhachHang(rs.getString("tenkh"));
                hd.setTrangThai(rs.getString("tt"));
                hd.setDiaChi(rs.getString("dc"));
                hd.setNgayTao(rs.getObject("nt", Date.class));
                hd.setSdt(rs.getString("sdt"));
                hd.setTongTien(rs.getFloat("tongtien"));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }
    

}
