/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.HoaDonChiTiet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


/**
 *
 * @author ACER
 */
public class HoaDonChiTietService{

    public boolean addHDCT(HoaDonChiTiet hdct) {
        String sql="""
                   insert into HoaDonChiTiet(ID_DepChiTiet,ID_HoaDon,SoLuong) values(?,?,?)
                   """;
        int check=0;
        try(Connection con = DBConnect.getConnection();PreparedStatement ps=con.prepareStatement(sql)){
            ps.setObject(1, hdct.getIdSPCT());
            ps.setObject(2, hdct.getIdHoaDon());
            ps.setObject(3, hdct.getSoLuong());
            check=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check>0;
    }
    
}
