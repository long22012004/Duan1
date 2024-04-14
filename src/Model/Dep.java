/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class Dep {

    private Integer id;
    private String maSP;
    private String tenSP;
    private boolean trangThai;
    private String tenChatLieu;
    private String tenKieuDang;
    private String tenNhaCungCap;
    private String tenQuocGia;
    private Integer id_ChatLieu;
    private Integer id_KieuDang;
    private Integer id_NhaCungCap;
    private Integer id_XuatXu;

    public Dep() {

    }

    public Dep(Integer id, String maSP, String tenSP, boolean trangThai, String tenChatLieu, Integer id_ChatLieu, String tenKieuDang, Integer id_KieuDang, String tenNhaCungCap, Integer id_NhaCungCap, String tenQuocGia, Integer id_XuatXu) {
        this.id = id;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.trangThai = trangThai;
        this.tenChatLieu = tenChatLieu;
        this.id_ChatLieu = id_ChatLieu;
        this.tenKieuDang = tenKieuDang;
        this.id_KieuDang = id_KieuDang;
        this.tenNhaCungCap = tenNhaCungCap;
        this.id_NhaCungCap = id_NhaCungCap;
        this.tenQuocGia = tenQuocGia;
        this.id_XuatXu = id_XuatXu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getId_ChatLieu() {
        return id_ChatLieu;
    }

    public void setId_ChatLieu(Integer id_ChatLieu) {
        this.id_ChatLieu = id_ChatLieu;
    }

    public String getTenKieuDang() {
        return tenKieuDang;
    }

    public void setTenKieuDang(String tenKieuDang) {
        this.tenKieuDang = tenKieuDang;
    }

    public Integer getId_KieuDang() {
        return id_KieuDang;
    }

    public void setId_KieuDang(Integer id_KieuDang) {
        this.id_KieuDang = id_KieuDang;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public Integer getId_NhaCungCap() {
        return id_NhaCungCap;
    }

    public void setId_NhaCungCap(Integer id_NhaCungCap) {
        this.id_NhaCungCap = id_NhaCungCap;
    }

    public String getTenQuocGia() {
        return tenQuocGia;
    }

    public void setTenQuocGia(String tenQuocGia) {
        this.tenQuocGia = tenQuocGia;
    }

    public Integer getId_XuatXu() {
        return id_XuatXu;
    }

    public void setId_XuatXu(Integer id_XuatXu) {
        this.id_XuatXu = id_XuatXu;
    }

    
    

}
