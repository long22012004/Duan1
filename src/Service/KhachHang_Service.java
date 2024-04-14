/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.KhachHang;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhachHang_Service implements inF_KH {

    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null;

    @Override
    public List<KhachHang> getAll() {
        sql = "select * from khachhang";
        List<KhachHang> listKH = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setId_kh(rs.getInt(1));
                kh.setMa(rs.getString(2));
                kh.setTenkh(rs.getString(3));
                kh.setDiaChi(rs.getString(4));
                kh.setSdt(rs.getString(5));
                kh.setEmail(rs.getString(6));
                kh.setGioiTinh(rs.getBoolean(7));
                kh.setNamSinh(rs.getInt(8));
                listKH.add(kh);

            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean addKH(KhachHang kh) {
        sql = "insert into KhachHang(MaKhachHang,TenKhachHang,DiaChi,Sdt,Email,GioiTinh,NamSinh) VALUES (?,?,?,?,?,?,?)";
        int check = 0;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, kh.getMa());
            ps.setObject(2, kh.getTenkh());
            ps.setObject(3, kh.getDiaChi());
            ps.setObject(4, kh.getSdt());
            ps.setObject(5, kh.getEmail());
            ps.setObject(6, kh.isGioiTinh());
            ps.setObject(7, kh.getNamSinh());
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean deleteKH(int id_kh) {
        sql = "delete from KhachHang where ID_KhachHang=?";
        int check = 0;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, id_kh);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean updateKH(KhachHang kh, int id) {
        sql = "update KhachHang set MaKhachHang=?,TenKhachHang=?,DiaChi=?,Sdt=?,Email=?,GioiTinh=?,NamSinh=? where ID_KhachHang=?";
        int check = 0;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, kh.getMa());
            ps.setObject(2, kh.getTenkh());
            ps.setObject(3, kh.getDiaChi());
            ps.setObject(4, kh.getSdt());
            ps.setObject(5, kh.getEmail());
            ps.setObject(6, kh.isGioiTinh());
            ps.setObject(7, kh.getNamSinh());
            ps.setObject(8, id);
            check = ps.executeUpdate();
        } catch (Exception e) {// k sửa được
            e.printStackTrace();

        }
        return check > 0;
    }

    public List<Object[]> KhachHangBought(int id) {
        String sql = """
           {CALL pr_KhachHangBought(?)}
                    """;
        String cols[] = {"maSanPham", "tenSanPham", "donGia", "soLuong", "tongTien"};
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

    public KhachHang selectDESC() {
        String sql = """
                    select  * from KhachHang order by ID_KhachHang desc
                   """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                KhachHang kh = new KhachHang();
                kh.setId_kh(rs.getInt(1));
                kh.setMa(rs.getString(2));
                kh.setTenkh(rs.getString(3));
                kh.setDiaChi(rs.getString(4));
                kh.setSdt(rs.getString(5));
                kh.setEmail(rs.getString(6));
                kh.setGioiTinh(rs.getBoolean(7));
                kh.setNamSinh(rs.getInt(8));
                return kh;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<KhachHang> timKiem(Object input) {
        String sql
                = """    
                select * from khachhang where MaKhachHang like ? or TenKhachHang like ? or DiaChi like ? or Sdt like ? or Email like ? 
                  """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, "%" + input + "%");
            ps.setObject(2, "%" + input + "%");
            ps.setObject(3, "%" + input + "%");
            ps.setObject(4, "%" + input + "%");
            ps.setObject(5, "%" + input + "%");
            List<KhachHang> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setId_kh(rs.getInt(1));
                kh.setMa(rs.getString(2));
                kh.setTenkh(rs.getString(3));
                kh.setDiaChi(rs.getString(4));
                kh.setSdt(rs.getString(5));
                kh.setEmail(rs.getString(6));
                kh.setGioiTinh(rs.getBoolean(7));
                kh.setNamSinh(rs.getInt(8));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

}
