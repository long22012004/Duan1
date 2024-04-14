/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.KichThuoc;
import Model.KieuDang;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author ADMIN
 */
public class KichThuocService {

    public List<KichThuoc> getAll() {
        String sql = """
                     select ID_KichThuoc,TenKichThuoc from KichThuoc
                     """;
        try(Connection con = DBConnect.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {
            List<KichThuoc> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                KichThuoc kt = new KichThuoc();
                kt.setiD_KichThuoc(rs.getInt(1));
                kt.setSize(rs.getString(2));
                list.add(kt);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public List<KichThuoc> getTen() {
        String sql = """
                       select TenKichThuoc from KichThuoc
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            List<KichThuoc> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               KichThuoc kt = new KichThuoc();
                kt.setSize(rs.getString(1));
                list.add(kt);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean add(KichThuoc kt) {
        String sql = """
                    Insert into KichThuoc(TenKichThuoc)values(?)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, kt.getSize());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean update(KichThuoc kt, int id) {
        String sql = """
                    Update kichThuoc set Size=? where ID_KichThuoc=?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, kt.getSize());
            ps.setObject(2, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
    
}
