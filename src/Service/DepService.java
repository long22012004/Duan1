/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Dep;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DepService implements DepInterface {

    @Override
    public List<Dep> getAll() {
        String sql = """
               select Dep.ID_Dep as id,Dep.MaDep as ma,Dep.TenDep as tenDep,XuatXu.QuocGia as quocGia,KieuDang.TenKieuDang as kieuDang,ChatLieu.TenChatLieu as chatLieu,NhaCungCap.TenNhaCungCap as nhaCungCap,Dep.TrangThai as tt from Dep inner join XuatXu on XuatXu.ID_XuatXu=Dep.ID_XuatXu inner join KieuDang on KieuDang.ID_KieuDang=Dep.ID_KieuDang
               inner join ChatLieu on ChatLieu.ID_ChatLieu=Dep.ID_ChatLieu inner join NhaCungCap on NhaCungCap.ID_NhaCungCap=Dep.ID_NhaCungCap """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            List<Dep> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Dep d = new Dep();
                d.setId(rs.getInt("id"));
                d.setMaSP(rs.getString("ma"));
                d.setTenSP(rs.getString("tenDep"));
                d.setTenQuocGia(rs.getString("quocGia"));
                d.setTenNhaCungCap(rs.getString("nhaCungCap"));
                d.setTenChatLieu(rs.getString("chatLieu"));
                d.setTenKieuDang(rs.getString("kieuDang"));
                d.setTrangThai(rs.getBoolean("tt"));
                list.add(d);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public boolean addDep(Dep d) {
        String sql = """
                     insert into Dep(MaDep,TenDep,ID_ChatLieu,ID_NhaCungCap,ID_XuatXu,ID_KieuDang,TrangThai) values(?,?,?,?,?,?,?)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, d.getMaSP());
            ps.setObject(2, d.getTenSP());
            ps.setObject(3, d.getId_ChatLieu());
            ps.setObject(4, d.getId_NhaCungCap());
            ps.setObject(5, d.getId_XuatXu());
            ps.setObject(6, d.getId_KieuDang());
            ps.setObject(7, d.isTrangThai());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean xoaDep(int id, Integer trangThai) {
        String sql = """
                    update Dep set TrangThai=? where ID_Dep=?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, trangThai);
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
        }
        return check > 0;
    }

    @Override
    public boolean update(Dep d, int id) {
        String sql = """
                     update Dep set MaDep=?,TenDep=?,ID_ChatLieu=?,ID_NhaCungCap=?,ID_XuatXu=?,ID_KieuDang=?,TrangThai=? where ID_Dep=?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, d.getMaSP());
            ps.setObject(2, d.getTenSP());
            ps.setObject(3, d.getId_ChatLieu());
            ps.setObject(4, d.getId_NhaCungCap());
            ps.setObject(5, d.getId_XuatXu());
            ps.setObject(6, d.getId_KieuDang());
            ps.setObject(7, d.isTrangThai());
            ps.setObject(8, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public List<Dep> getTen() {
        String sql = """
                     select TenDep from Dep
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            List<Dep> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Dep d = new Dep();
                d.setTenSP(rs.getString(1));
                list.add(d);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public List<Dep> getTrangThai() {
        String sql = """
                     select TrangThai from Dep
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            List<Dep> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Dep d = new Dep();
                d.setTrangThai(rs.getBoolean(1));
                list.add(d);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

  


    @Override
    public List<Dep> timKiem(Object input) {
        String sql
                = """    
                 select Dep.ID_Dep as id,Dep.MaDep as ma,Dep.TenDep as tenDep,XuatXu.QuocGia as quocGia,KieuDang.TenKieuDang as kieuDang,ChatLieu.TenChatLieu as chatLieu,NhaCungCap.TenNhaCungCap as nhaCungCap,Dep.TrangThai as tt from Dep inner join XuatXu on XuatXu.ID_XuatXu=Dep.ID_XuatXu inner join KieuDang on KieuDang.ID_KieuDang=Dep.ID_KieuDang
                  inner join ChatLieu on ChatLieu.ID_ChatLieu=Dep.ID_ChatLieu inner join NhaCungCap on NhaCungCap.ID_NhaCungCap=Dep.ID_NhaCungCap
                  where Dep.MaDep like ? or Dep.TenDep like ? or KieuDang.TenKieuDang like ? or NhaCungCap.TenNhaCungCap like ? or ChatLieu.TenChatLieu like ? or XuatXu.QuocGia like ?
                  """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, "%"+input+"%");
            ps.setObject(2, "%"+input+"%");
            ps.setObject(3, "%"+input+"%");
            ps.setObject(4, "%"+input+"%");
            ps.setObject(5, "%"+input+"%");
            ps.setObject(6, "%"+input+"%");
            List<Dep> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Dep d = new Dep();
                d.setId(rs.getInt("id"));
                d.setMaSP(rs.getString("ma"));
                d.setTenSP(rs.getString("tenDep"));
                d.setTenQuocGia(rs.getString("quocGia"));
                d.setTenNhaCungCap(rs.getString("nhaCungCap"));
                d.setTenChatLieu(rs.getString("chatLieu"));
                d.setTenKieuDang(rs.getString("kieuDang"));
                d.setTrangThai(rs.getBoolean("tt"));
                list.add(d);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
     public int countSoLuong(int id) {
        String sql = """
                     select Sum(DepChiTiet.SoLuong) as soLuong from Dep inner join DepChiTiet on DepChiTiet.ID_Dep=Dep.ID_Dep where Dep.ID_Dep=?
                     """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("soLuong");
                }
            }
        } catch (Exception e) {
            // Log the exception or print the stack trace for debugging
            e.printStackTrace();
        }
        return 0;

    }

    @Override
    public List<Object[]> DepBouth(int id) {
 String sql = """
           {CALL ps_SanPhamChiTiet(?)}
                    """;
        String cols[] = {"tenDep", "tenMau","tenKichThuoc", "soLuong", "donGia"};
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
    
 
}
