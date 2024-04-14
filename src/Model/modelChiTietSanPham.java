/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Dell
 */
public class modelChiTietSanPham {

    private Integer idCTSP;
    private String maSp;
    private String tenSanPham;
    private String tenMauSac;
    private Float donGia;
    private String moTa;
    private Integer soLuong;
    private String kichThuoc;
    private String maSP;
    private String tenSP;
    private boolean trangThai;
    private String tenChatLieu;
    private String tenKieuDang;
    private String tenNhaCungCap;
    private String tenQuocGia;

    public modelChiTietSanPham() {
    }

    public modelChiTietSanPham(Integer idCTSP, String maSp, String tenSanPham, String tenMauSac, float donGia, String moTa, Integer soLuong, String kichThuoc, String maSP, String tenSP, boolean trangThai, String tenChatLieu, String tenKieuDang, String tenNhaCungCap, String tenQuocGia) {
        this.idCTSP = idCTSP;
        this.maSp = maSp;
        this.tenSanPham = tenSanPham;
        this.tenMauSac = tenMauSac;
        this.donGia = donGia;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.kichThuoc = kichThuoc;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.trangThai = trangThai;
        this.tenChatLieu = tenChatLieu;
        this.tenKieuDang = tenKieuDang;
        this.tenNhaCungCap = tenNhaCungCap;
        this.tenQuocGia = tenQuocGia;
    }

    public Integer getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(Integer idCTSP) {
        this.idCTSP = idCTSP;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(Float donGia) {
        this.donGia = donGia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public String getTenKieuDang() {
        return tenKieuDang;
    }

    public void setTenKieuDang(String tenKieuDang) {
        this.tenKieuDang = tenKieuDang;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getTenQuocGia() {
        return tenQuocGia;
    }

    public void setTenQuocGia(String tenQuocGia) {
        this.tenQuocGia = tenQuocGia;
    }

    
}
