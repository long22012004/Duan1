
package Service;

import Model.TaiKhoan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class TaiKhoanService1 {

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
                   update TaiKhoanNhanVien set TenTaiKhoanNhanVien=?,Username=?,Password=?,TrangThai=? where ID_TaiKhoanNhanVien=?
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

}
