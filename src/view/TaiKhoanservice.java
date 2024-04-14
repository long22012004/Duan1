/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import Service.*;
import Model.TaiKhoan;
import Service.DBConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
class TaiKhoanservice {
    
    public List<TaiKhoan> getAllTaiKhoan() {
        String sql = """
                     select * from TaiKhoanNhanVien
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            List<TaiKhoan> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setId(rs.getInt(1));
                tk.setUserName(rs.getString(2));
                tk.setPassword(rs.getString(3));
                tk.setTrangThai(rs.getBoolean(4));
                list.add(tk);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean add(TaiKhoan tk) {
        String sql = """
                    insert into TaiKhoanNhanVien(Username,Password,TrangThai)values(?,?,?)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tk.getUserName());
            ps.setObject(2, tk.getPassword());
            ps.setObject(3, tk.getTrangThai());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
     public boolean update(TaiKhoan tk,int id) {
        String sql = """
                   update TaiKhoanNhanVien set Username=?,Password=?,TrangThai=? where ID_TaiKhoanNhanVien=?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tk.getUserName());
            ps.setObject(2, tk.getPassword());
            ps.setObject(3, tk.getTrangThai());
            ps.setObject(4, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
     
     public boolean deletenhanvien(int idnv, Integer trangThai) {
        Integer row = 0;
        String sql = """
                    update TaiKhoanNhanVien set TrangThai=? where ID_TaiKhoanNhanVien=?
                     """;

        Connection cn = DBConnect.getConnection();
        try {

            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setObject(1, trangThai);

            pstm.setObject(2, idnv);

            row = pstm.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return row > 0;
    }

}
