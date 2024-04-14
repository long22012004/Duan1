/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;


import Model.XuatXu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class XuatXuService {
     public List<XuatXu> getAll() {
        String sql = """
                     select ID_XuatXu,QuocGia from XuatXu
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            List<XuatXu> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                XuatXu xx = new XuatXu();
                xx.setiD_XuatXu(rs.getInt(1));
                xx.setQuocGia(rs.getString(2));
                list.add(xx);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<XuatXu> getTen() {
        String sql = """
                       select QuocGia from XuatXu
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            List<XuatXu> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                XuatXu xx = new XuatXu();
              xx.setQuocGia(rs.getString(1));
                list.add(xx);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean add(XuatXu xx) {
        String sql = """
                    Insert into XuatXu(QuocGia)values(?)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, xx.getQuocGia());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean update(XuatXu xx, int id) {
        String sql = """
                    Update KieuDang set TenKieuDang=? where ID_KieuDang=?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, xx.getQuocGia());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
}
