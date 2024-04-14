/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class KieuDang {
    
    private Integer iD_KieuDang;
    private String tenKieuDang;

    public KieuDang() {
    }

    public KieuDang(String tenKieuDang) {
        this.iD_KieuDang = iD_KieuDang;
        this.tenKieuDang = tenKieuDang;
    }

    public Integer getiD_KieuDang() {
        return iD_KieuDang;
    }

    public void setiD_KieuDang(Integer iD_KieuDang) {
        this.iD_KieuDang = iD_KieuDang;
    }

    public String getTenKieuDang() {
        return tenKieuDang;
    }

    public void setTenKieuDang(String tenKieuDang) {
        this.tenKieuDang = tenKieuDang;
    }
    
    
}
