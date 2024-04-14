/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.HinhThucThanhToan;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class HinhThucThanhToanSerVice {
    
    public List<HinhThucThanhToan> getAll(){
        String sql = """
                     select * from HinhThucThanhToan
                     """;
        try(Connection con = DBConnect.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {
            List<HinhThucThanhToan> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                HinhThucThanhToan httt = new HinhThucThanhToan();
                httt.setId(rs.getInt(1));
                httt.setHinhThucThanhToan(rs.getString(2));
                list.add(httt);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    
    public HinhThucThanhToan findHTTTByName(String name) {
        String sql="""
                   select * from HinhThucThanhToan where LoaiHinhThucThanhToan=?
                   """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, name);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                HinhThucThanhToan httt =new HinhThucThanhToan();
                httt.setId(rs.getInt(1));
                httt.setHinhThucThanhToan(rs.getString(2));
                return httt;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
