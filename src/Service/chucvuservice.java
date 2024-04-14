/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.chucvu;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Lenovo
 */
public class chucvuservice {
     public ArrayList<chucvu> getAllchucvu() {
        ArrayList<chucvu> lst = new ArrayList<>();
        String sql = """
                     select ID_ChucVu,TenChucVu from Chucvu
                     """;

        Connection cn = DBConnect.getConnection();
        try {

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                chucvu n = new chucvu();
                n.setId(rs.getInt("ID_ChucVu"));
                
                n.setTencv(rs.getString("TenChucVu"));
                
                lst.add(n);
            }
            return lst;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public boolean addchucvu(chucvu cv) {
        String sql = """
                     insert into ChucVu(TenChucVu)
                     values(?)
                     """;
        int check = 0;
        try (Connection cn = DBConnect.getConnection(); PreparedStatement pstm = cn.prepareStatement(sql)) {
            pstm.setObject(1, cv.getTencv());
            
            check = pstm.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return check > 0;
    }

//    public boolean deletechucvu(int id, Integer trangThai) {
//        Integer row = 0;
//        String sql = """
//                    update ChucVu set TrangThai=? where ID_NhanVien=?
//                     """;
//
//        Connection cn = DBConnect.getConnection();
//        try {
//
//            PreparedStatement pstm = cn.prepareStatement(sql);
//            pstm.setObject(1, trangThai);
//
//            pstm.setObject(2, idnv);
//
//            row = pstm.executeUpdate();
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//        return row > 0;
//    }

    public boolean updatenhanvien(chucvu cv, int id) {
        Integer row = 0;
        String sql = "update ChucVu\n"
                + "set TenChucVu=?\n"
                + "where ID_ChucVu like ?";

        Connection cn = DBConnect.getConnection();
        try {

            PreparedStatement pstm = cn.prepareStatement(sql);

            pstm.setString(1, cv.getTencv());
           
            pstm.setObject(2, id);
            row = pstm.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return row > 0;
    }
    
}
