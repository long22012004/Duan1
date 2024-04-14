/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author viett
 */
public class HoaDon {

    private Integer idHoaDon;
    private String maHoaDon;
    private LocalDate ngayTao;
    private String ghiChu;
    private Integer idKhachHang;
    private Integer idNhanVien;
    private Float tongTienHang;
    private Float tienKhachDua;
    private Float tienThua;
    private String trangThai;
    private Integer idHTTT;

    public HoaDon() {
    }

    public HoaDon(Integer idHoaDon, String maHoaDon, LocalDate ngayTao, String ghiChu, Integer idKhachHang, Integer idNhanVien, Float tongTienHang, Float tienKhachDua, Float tienThua, String trangThai, Integer idHTTT) {
        this.idHoaDon = idHoaDon;
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.tongTienHang = tongTienHang;
        this.tienKhachDua = tienKhachDua;
        this.tienThua = tienThua;
        this.trangThai = trangThai;
        this.idHTTT = idHTTT;
    }

    public Integer getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(Integer idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public LocalDate getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Integer getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Integer idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public Integer getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(Integer idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public Float getTongTienHang() {
        return tongTienHang;
    }

    public void setTongTienHang(Float tongTienHang) {
        this.tongTienHang = tongTienHang;
    }


    public Float getTienKhachDua() {
        return tienKhachDua;
    }

    public void setTienKhachDua(Float tienKhachDua) {
        this.tienKhachDua = tienKhachDua;
    }

    public Float getTienThua() {
        return tienThua;
    }

    public void setTienThua(Float tienThua) {
        this.tienThua = tienThua;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getIdHTTT() {
        return idHTTT;
    }

    public void setIdHTTT(Integer idHTTT) {
        this.idHTTT = idHTTT;
    }

    

    
}
