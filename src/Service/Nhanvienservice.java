/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Nhanvienservice {

    public ArrayList<NhanVien> getAllnhanvien() {
        ArrayList<NhanVien> lst = new ArrayList<>();
        String sql = """
                     select nv.ID_NhanVien as idnv,nv.MaNhanVien as mnv,nv.TenNhanVien as tnv,nv.DiaChi as dc,nv.Email as em,nv.GioiTinh as gt,nv.Luong as l,nv.Sdt as sdt,nv.NamSinh as ns,nv.ID_ChucVu as idcv,cv.TenChucVu as tcv,nv.ID_TaiKhoanNhanVien as idtknv,nv.TrangThai as tt,tknv.Username as ttk from NhanVien nv inner join ChucVu cv on cv.ID_ChucVu=nv.ID_ChucVu inner join TaiKhoanNhanvien tknv on tknv.ID_TaiKhoanNhanVien=nv.ID_TaiKhoanNhanVien
                                          """;

        Connection cn = DBConnect.getConnection();
        try {

            PreparedStatement pstm = cn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                NhanVien n = new NhanVien();
                n.setIdnv(rs.getInt("idnv"));
                n.setManv(rs.getString("mnv"));
                n.setTennv(rs.getString("tnv"));
                n.setDiachi(rs.getString("dc"));
                n.setSdt(rs.getString("sdt"));
                n.setEmail(rs.getString("em"));
                n.setNamsinh(rs.getInt("ns"));
                n.setIdchucvu(rs.getInt("idcv"));
                n.setIdtaikhoan(rs.getInt("idtknv"));
                n.setLuong(rs.getFloat("l"));
                n.setGioitinh(rs.getBoolean("gt"));
                n.setTenTK(rs.getString("ttk"));
                n.setTenCV(rs.getString("tcv"));
                n.setTrangthai(rs.getBoolean("tt"));
                lst.add(n);
            }
            return lst;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public boolean addnhanvien(NhanVien nv) {
        String sql = """
                     insert into NhanVien(MaNhanVien,TenNhanVien,DiaChi,Sdt,Email,TrangThai,GioiTinh,Luong,NamSinh,ID_ChucVu,ID_TaiKhoanNhanVien)
                     values(?,?,?,?,?,?,?,?,?,?,?)
                     """;
        int check = 0;
        try (Connection cn = DBConnect.getConnection(); PreparedStatement pstm = cn.prepareStatement(sql)) {
            pstm.setString(1, nv.getManv());
            pstm.setString(2, nv.getTennv());
            pstm.setString(3, nv.getDiachi());
            pstm.setString(4, nv.getSdt());
            pstm.setString(5, nv.getEmail());
            pstm.setBoolean(6, nv.isTrangthai());
            pstm.setBoolean(7, nv.isGioitinh());
            pstm.setFloat(8, nv.getLuong());
            pstm.setInt(9, nv.getNamsinh());
            pstm.setInt(10, nv.getIdchucvu());
            pstm.setInt(11, nv.getIdtaikhoan());
            check = pstm.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return check > 0;
    }

    public boolean deletenhanvien(int idnv, Integer trangThai) {
        Integer row = 0;
        String sql = """
                    update NhanVien set TrangThai=? where ID_NhanVien=?
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

    public boolean updatenhanvien(NhanVien nv, int id) {
        Integer row = 0;
        String sql = "update NhanVien\n"
                + "set MaNhanVien=?,TenNhanVien=? ,DiaChi=?,Sdt=?,Email=?,TrangThai=?,GioiTinh=?,Luong=?,NamSinh=?,ID_ChucVu=?,ID_TaiKhoanNhanVien=?\n"
                + "where ID_NhanVien like ?";

        Connection cn = DBConnect.getConnection();
        try {

            PreparedStatement pstm = cn.prepareStatement(sql);

            pstm.setString(1, nv.getManv());
            pstm.setString(2, nv.getTennv());

            pstm.setString(3, nv.getDiachi());

            pstm.setString(4, nv.getSdt());
            pstm.setString(5, nv.getEmail());
            pstm.setBoolean(6, nv.isTrangthai());
            pstm.setBoolean(7, nv.isGioitinh());
            pstm.setInt(8, nv.getNamsinh());
            pstm.setFloat(9, nv.getLuong());
            pstm.setInt(10, nv.getIdchucvu());
            pstm.setInt(11, nv.getIdtaikhoan());
            pstm.setObject(12, id);
            row = pstm.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return row > 0;
    }

    public NhanVien finNhanVien(String tk,String matKhau) {
        String sql="""
select NhanVien.ID_NhanVien as idnv,NhanVien.MaNhanVien as manv,NhanVien.TenNhanVien as tennv
                     ,NhanVien.DiaChi as dc,NhanVien.Email as em,NhanVien.GioiTinh as gt,NhanVien.Luong as l,
                     NhanVien.Sdt as sdt
                     ,NhanVien.NamSinh as ns,
                     NhanVien.TrangThai as tt,ChucVu.TenChucVu as tcv,
                     TaiKhoanNhanvien.Password as pw from NhanVien inner join TaiKhoanNhanvien on 
                     TaiKhoanNhanvien.ID_TaiKhoanNhanVien=NhanVien.ID_TaiKhoanNhanVien inner join ChucVu on ChucVu.ID_ChucVu=NhanVien.ID_ChucVu 
                     where TaiKhoanNhanvien.Username = ? and TaiKhoanNhanvien.Password = ?
                    """;
        try(Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setObject(1, tk);
            ps.setObject(2, matKhau);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                NhanVien nv=new NhanVien();
                nv.setIdnv(rs.getInt("idnv"));
                nv.setManv(rs.getString("manv"));
                nv.setTennv(rs.getString("tennv"));
                nv.setGioitinh(rs.getBoolean("gt"));
                nv.setNamsinh(rs.getInt("ns"));
                nv.setDiachi(rs.getString("dc"));
                nv.setSdt(rs.getString("sdt"));
                nv.setEmail(rs.getString("em"));
                nv.setLuong(rs.getFloat("l"));
                nv.setTrangthai(rs.getBoolean("tt"));
                nv.setTenCV(rs.getString("tcv"));
                return nv;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
      public List<NhanVien> timKiem(Object input) {
        String sql
                = """    
                 select nv.ID_NhanVien as idnv,nv.MaNhanVien as mnv,nv.TenNhanVien as tnv,nv.DiaChi as dc,nv.Email as em
                                    ,nv.GioiTinh as gt,nv.Luong as l,nv.Sdt as sdt,nv.NamSinh as ns,nv.ID_ChucVu as idcv,cv.TenChucVu as tcv
                                    ,nv.ID_TaiKhoanNhanVien as idtknv,nv.TrangThai as tt,tknv.Username as ttk from NhanVien nv inner join ChucVu cv on
                                    cv.ID_ChucVu=nv.ID_ChucVu inner join TaiKhoanNhanvien tknv on tknv.ID_TaiKhoanNhanVien=nv.ID_TaiKhoanNhanVien
                                    where nv.MaNhanVien like ? or nv.TenNhanVien like ? or NV.Sdt like ? 
                  """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, "%"+input+"%");
            ps.setObject(2, "%"+input+"%");
            ps.setObject(3, "%"+input+"%");
            List<NhanVien> list = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien d = new NhanVien();
                d.setIdnv(rs.getInt("idnv"));
                d.setManv(rs.getString("mnv"));
                d.setTennv(rs.getString("tnv"));
                d.setDiachi(rs.getString("dc"));
                d.setEmail(rs.getString("em"));
                d.setGioitinh(rs.getBoolean("gt"));
                d.setLuong(rs.getFloat("l"));
                d.setSdt(rs.getString("sdt"));
                d.setNamsinh(rs.getInt("ns"));
                d.setTenCV(rs.getString("tcv"));
                d.setTenTK(rs.getString("ttk"));
                list.add(d);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
