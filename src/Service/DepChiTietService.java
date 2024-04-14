/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.DepChiTiet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DepChiTietService implements DepChiTietInterface {

    @Override
    public List<DepChiTiet> getAll() {
        String sql = """
              	 	select ID_DepChiTiet, Dep.ID_Dep,Dep.MaDep as MaDep,Dep.TenDep as TenDep,MauSac.TenMauSac as tenMauSac,KichThuoc.TenKichThuoc as size,
                                               SoLuong,DonGia,DepChiTiet.MoTa as MoTa from
                                                DepChiTiet inner join Dep on Dep.ID_Dep=DepChiTiet.ID_Dep inner join MauSac on
                                                MauSac.ID_MauSac=DepChiTiet.ID_MauSac
                                                inner join KichThuoc on KichThuoc.ID_KichThuoc=DepChiTiet.ID_KichThuoc 
                                                
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            List<DepChiTiet> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DepChiTiet dct = new DepChiTiet();
                dct.setiD_DepChiTiet(rs.getInt("ID_DepChiTiet"));
                dct.setMa_Dep(rs.getString("MaDep"));
                dct.setId_Dep(rs.getInt("ID_Dep"));
                dct.setTenDep(rs.getString("tenDep"));
                dct.setTenMauSac(rs.getString("tenMauSac"));
                dct.setTenKichThuoc(rs.getString("size"));
                dct.setSoLuong(rs.getInt("SoLuong"));
                dct.setGiaBan(rs.getFloat("DonGia"));
                dct.setMoTa(rs.getString("MoTa"));
                list.add(dct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public boolean addDepChiTiet(DepChiTiet dct) {
        String sql = """
                     insert into DepChiTiet(ID_Dep,MoTa,ID_MauSac,ID_KichThuoc,DonGia,SoLuong) values (?,?,?,?,?,?)
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dct.getId_Dep());
            ps.setString(2, dct.getMoTa());
            ps.setInt(3, dct.getId_mauSac());
            ps.setInt(4, dct.getId_Kichthuoc());
            ps.setObject(5, dct.getGiaBan());
            ps.setObject(6, dct.getSoLuong());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean sua(DepChiTiet dct, Integer ma) {
        String sql = """
                     UPDATE DepChiTiet
                        SET ID_Dep = ?
                           ,MoTa = ?
                           ,ID_MauSac = ?
                           ,ID_KichThuoc = ?
                           ,DonGia = ?
                           ,SoLuong = ?
                      WHERE ID_DepChiTiet=?
                     """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dct.getId_Dep());
            ps.setString(2, dct.getMoTa());
            ps.setInt(3, dct.getId_mauSac());
            ps.setInt(4, dct.getId_Kichthuoc());
            ps.setObject(5, dct.getGiaBan());
            ps.setObject(6, dct.getSoLuong());
            ps.setObject(7, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public List<DepChiTiet> tim(Float min, Float max) {
        String sql
                = """    
         select DepChiTiet.Id_DepChiTiet as id,Dep.MaDep as md,Dep.TenDep as td,MauSac.TenMauSac as ms,KichThuoc.TenKichThuoc as kt
                  ,DepChiTiet.DonGia as dg,DepChiTiet.SoLuong as sl,DepChiTiet.MoTa as mt from DepChiTiet inner join MauSac on MauSac.ID_MauSac=DepChiTiet.ID_MauSac 
                  inner join KichThuoc on KichThuoc.ID_KichThuoc=DepChiTiet.ID_KichThuoc inner join Dep on Dep.ID_Dep=DepChiTiet.ID_Dep  where DepChiTiet.DonGia >= ?
                  and DepChiTiet.DonGia <= ?
                  """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, min);
            ps.setObject(2, max);
            List<DepChiTiet> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DepChiTiet dct = new DepChiTiet();
                dct.setiD_DepChiTiet(rs.getInt("id"));
                dct.setMa_Dep(rs.getString("md"));
                dct.setTenDep(rs.getString("td"));
                dct.setGiaBan(rs.getFloat("dg"));
                dct.setMoTa(rs.getString("mt"));
                dct.setSoLuong(rs.getInt("sl"));
                dct.setTenMauSac(rs.getString("ms"));
                dct.setTenKichThuoc(rs.getString("kt"));
                list.add(dct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public DepChiTiet getCTSPByID(Integer idCTSP) {
        String sql = """
                    select * from DepChiTiet where ID_DepChiTiet=?
                    """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idCTSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DepChiTiet ctsp = new DepChiTiet();
                ctsp.setiD_DepChiTiet(rs.getInt(1));
                ctsp.setId_Dep(rs.getInt(2));
                ctsp.setMoTa(rs.getString(3));
                ctsp.setId_mauSac(rs.getInt(4));
                ctsp.setId_Kichthuoc(rs.getInt(5));
                ctsp.setGiaBan(rs.getFloat(6));
                ctsp.setSoLuong(rs.getInt(7));
                return ctsp;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean updateSoLuongCTSP(int soLuong, int idCTSP) {
        String sql = """
                   update DepChiTiet set SoLuong=? where ID_DepChiTiet=?
                   """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, soLuong);
            ps.setObject(2, idCTSP);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

   }
