/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class DepChiTiet {
   
    private Integer id_Dep;
    private String tenDep;
    private Integer soLuong;
    private float giaBan;
    private String tenMauSac;
    private Integer id_mauSac;
    private String tenKichThuoc;
    private Integer id_Kichthuoc;
    private String TrangThai;
    private String ma_Dep;
    private String moTa;
    private Integer iD_DepChiTiet;
    
    
    public DepChiTiet() {
    }

    public DepChiTiet(Integer id_Dep, String tenDep, Integer soLuong, float giaBan, String tenMauSac, Integer id_mauSac, String tenKichThuoc, Integer id_Kichthuoc, String TrangThai, String ma_Dep, String moTa, Integer iD_DepChiTiet) {
        this.id_Dep = id_Dep;
        this.tenDep = tenDep;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.tenMauSac = tenMauSac;
        this.id_mauSac = id_mauSac;
        this.tenKichThuoc = tenKichThuoc;
        this.id_Kichthuoc = id_Kichthuoc;
        this.TrangThai = TrangThai;
        this.ma_Dep = ma_Dep;
        this.moTa = moTa;
        this.iD_DepChiTiet = iD_DepChiTiet;
    }

    public Integer getId_Dep() {
        return id_Dep;
    }

    public void setId_Dep(Integer id_Dep) {
        this.id_Dep = id_Dep;
    }

    public String getTenDep() {
        return tenDep;
    }

    public void setTenDep(String tenDep) {
        this.tenDep = tenDep;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public Integer getId_mauSac() {
        return id_mauSac;
    }

    public void setId_mauSac(Integer id_mauSac) {
        this.id_mauSac = id_mauSac;
    }

    public String getTenKichThuoc() {
        return tenKichThuoc;
    }

    public void setTenKichThuoc(String tenKichThuoc) {
        this.tenKichThuoc = tenKichThuoc;
    }

    public Integer getId_Kichthuoc() {
        return id_Kichthuoc;
    }

    public void setId_Kichthuoc(Integer id_Kichthuoc) {
        this.id_Kichthuoc = id_Kichthuoc;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getMa_Dep() {
        return ma_Dep;
    }

    public void setMa_Dep(String ma_Dep) {
        this.ma_Dep = ma_Dep;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getiD_DepChiTiet() {
        return iD_DepChiTiet;
    }

    public void setiD_DepChiTiet(Integer iD_DepChiTiet) {
        this.iD_DepChiTiet = iD_DepChiTiet;
    }
    
    
}
