/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.modelChiTietSanPham;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class ChiTietSanPhamRepoImpl {

    Connection con;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";

    public List<modelChiTietSanPham> getAll() {
        String sql = """
                     SELECT DepChiTiet.Id_DepChiTiet as id,Dep.MaDep as md,Dep.TenDep as td,KieuDang.TenKieuDang as kd,ChatLieu.TenChatLieu as cl,KichThuoc.TenKichThuoc as kt,NhaCungCap.TenNhaCungCap as ncc,
                     XuatXu.QuocGia as xx,MauSac.TenMauSac as ms,DepChiTiet.DonGia as dg,DepChiTiet.SoLuong as sl,DepChiTiet.MoTa as mt
                                 from DepChiTiet inner join MauSac on MauSac.ID_MauSac=DepChiTiet.ID_MauSac inner join KichThuoc on
                     			KichThuoc.ID_KichThuoc=DepChiTiet.ID_KichThuoc inner join Dep on Dep.ID_Dep=DepChiTiet.ID_Dep inner 
                     			join ChatLieu on ChatLieu.ID_ChatLieu=Dep.ID_ChatLieu inner join KieuDang on KieuDang.ID_KieuDang=Dep.ID_KieuDang
                     			inner join NhaCungCap on NhaCungCap.ID_NhaCungCap=Dep.ID_NhaCungCap inner join XuatXu on XuatXu.ID_XuatXu=Dep.ID_XuatXu
                     			order by MaDep asc
                  """;
        List<modelChiTietSanPham> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelChiTietSanPham model = new modelChiTietSanPham();
                model.setIdCTSP(rs.getInt("id"));
                model.setMaSp(rs.getString("md"));
                model.setTenSanPham(rs.getString("td"));
                model.setDonGia(rs.getFloat("dg"));
                model.setTenChatLieu(rs.getString("cl"));
                model.setTenQuocGia(rs.getString("xx"));
                model.setTenNhaCungCap(rs.getString("ncc"));
                model.setTenMauSac(rs.getString("ms"));
                model.setKichThuoc(rs.getString("kt"));
                model.setTenKieuDang(rs.getString("kd"));
                model.setMoTa(rs.getString("mt"));
                model.setSoLuong(rs.getInt("sl"));
                list.add(model);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
     public List<modelChiTietSanPham> getBanHang() {
        String sql = """
                     SELECT DepChiTiet.Id_DepChiTiet as id,Dep.MaDep as md,Dep.TenDep as td,KieuDang.TenKieuDang as kd,ChatLieu.TenChatLieu as cl,KichThuoc.TenKichThuoc as kt,NhaCungCap.TenNhaCungCap as ncc,
                                          XuatXu.QuocGia as xx,MauSac.TenMauSac as ms,DepChiTiet.DonGia as dg,DepChiTiet.SoLuong as sl,DepChiTiet.MoTa as mt
                                                      from DepChiTiet inner join MauSac on MauSac.ID_MauSac=DepChiTiet.ID_MauSac inner join KichThuoc on
                                          			KichThuoc.ID_KichThuoc=DepChiTiet.ID_KichThuoc inner join Dep on Dep.ID_Dep=DepChiTiet.ID_Dep inner 
                                          			join ChatLieu on ChatLieu.ID_ChatLieu=Dep.ID_ChatLieu inner join KieuDang on KieuDang.ID_KieuDang=Dep.ID_KieuDang
                                          			inner join NhaCungCap on NhaCungCap.ID_NhaCungCap=Dep.ID_NhaCungCap inner join XuatXu on XuatXu.ID_XuatXu=Dep.ID_XuatXu where Dep.TrangThai=1
                                          			order by MaDep asc 
                  """;
        List<modelChiTietSanPham> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelChiTietSanPham model = new modelChiTietSanPham();
                model.setIdCTSP(rs.getInt("id"));
                model.setMaSp(rs.getString("md"));
                model.setTenSanPham(rs.getString("td"));
                model.setDonGia(rs.getFloat("dg"));
                model.setTenChatLieu(rs.getString("cl"));
                model.setTenQuocGia(rs.getString("xx"));
                model.setTenNhaCungCap(rs.getString("ncc"));
                model.setTenMauSac(rs.getString("ms"));
                model.setKichThuoc(rs.getString("kt"));
                model.setTenKieuDang(rs.getString("kd"));
                model.setMoTa(rs.getString("mt"));
                model.setSoLuong(rs.getInt("sl"));
                list.add(model);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public modelChiTietSanPham getMCTSPByID(Integer idCTSP) {
        String sql = """
                                  SELECT DepChiTiet.Id_DepChiTiet as id,Dep.MaDep as md,Dep.TenDep as td,KieuDang.TenKieuDang as kd,ChatLieu.TenChatLieu as cl,KichThuoc.TenKichThuoc as kt,NhaCungCap.TenNhaCungCap as ncc,
                              XuatXu.QuocGia as xx,MauSac.TenMauSac as ms,DepChiTiet.DonGia as dg,DepChiTiet.SoLuong as sl,DepChiTiet.MoTa as mt
                              from DepChiTiet inner join MauSac on MauSac.ID_MauSac=DepChiTiet.ID_MauSac inner join KichThuoc on
                           KichThuoc.ID_KichThuoc=DepChiTiet.ID_KichThuoc inner join Dep on Dep.ID_Dep=DepChiTiet.ID_Dep inner 
                            join ChatLieu on ChatLieu.ID_ChatLieu=Dep.ID_ChatLieu inner join KieuDang on KieuDang.ID_KieuDang=Dep.ID_KieuDang
                            inner join NhaCungCap on NhaCungCap.ID_NhaCungCap=Dep.ID_NhaCungCap inner join XuatXu on XuatXu.ID_XuatXu=Dep.ID_XuatXu
                   """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, idCTSP);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelChiTietSanPham model = new modelChiTietSanPham();
                model.setIdCTSP(rs.getInt("id"));
                model.setMaSp(rs.getString("md"));
                model.setTenSanPham(rs.getString("td"));
                model.setDonGia(rs.getFloat("dg"));
                model.setTenChatLieu(rs.getString("cl"));
                model.setTenQuocGia(rs.getString("xx"));
                model.setTenNhaCungCap(rs.getString("ncc"));
                model.setTenMauSac(rs.getString("ms"));
                model.setKichThuoc(rs.getString("kt"));
                model.setTenKieuDang(rs.getString("kd"));
                model.setMoTa(rs.getString("mt"));
                model.setSoLuong(rs.getInt("sl"));
                return model;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
        public List<modelChiTietSanPham> timKiem(Object input) {
        String sql = """
                     SELECT DepChiTiet.Id_DepChiTiet as id,Dep.MaDep as md,Dep.TenDep as td,KieuDang.TenKieuDang as kd,ChatLieu.TenChatLieu as cl,KichThuoc.TenKichThuoc as kt,NhaCungCap.TenNhaCungCap as ncc,
                                          XuatXu.QuocGia as xx,MauSac.TenMauSac as ms,DepChiTiet.DonGia as dg,DepChiTiet.SoLuong as sl,DepChiTiet.MoTa as mt
                                                      from DepChiTiet inner join MauSac on MauSac.ID_MauSac=DepChiTiet.ID_MauSac inner join KichThuoc on
                                          			KichThuoc.ID_KichThuoc=DepChiTiet.ID_KichThuoc inner join Dep on Dep.ID_Dep=DepChiTiet.ID_Dep inner 
                                          			join ChatLieu on ChatLieu.ID_ChatLieu=Dep.ID_ChatLieu inner join KieuDang on KieuDang.ID_KieuDang=Dep.ID_KieuDang
                                          			inner join NhaCungCap on NhaCungCap.ID_NhaCungCap=Dep.ID_NhaCungCap inner join XuatXu on XuatXu.ID_XuatXu=Dep.ID_XuatXu
                                          			 where Dep.MaDep like ? or Dep.TenDep like ?
                  """;
        List<modelChiTietSanPham> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, "%"+input+"%");
            ps.setObject(2, "%"+input+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelChiTietSanPham model = new modelChiTietSanPham();
                model.setIdCTSP(rs.getInt("id"));
                model.setMaSp(rs.getString("md"));
                model.setTenSanPham(rs.getString("td"));
                model.setDonGia(rs.getFloat("dg"));
                model.setTenChatLieu(rs.getString("cl"));
                model.setTenQuocGia(rs.getString("xx"));
                model.setTenNhaCungCap(rs.getString("ncc"));
                model.setTenMauSac(rs.getString("ms"));
                model.setKichThuoc(rs.getString("kt"));
                model.setTenKieuDang(rs.getString("kd"));
                model.setMoTa(rs.getString("mt"));
                model.setSoLuong(rs.getInt("sl"));
                list.add(model);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
