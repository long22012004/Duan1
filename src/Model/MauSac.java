/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class MauSac {
    
    private Integer iD_MauSac;
    private String tenMauSac;

    public MauSac() {
    }

    public MauSac(String tenMauSac) {
        this.iD_MauSac = iD_MauSac;
        this.tenMauSac = tenMauSac;
    }

    public Integer getiD_MauSac() {
        return iD_MauSac;
    }

    public void setiD_MauSac(Integer iD_MauSac) {
        this.iD_MauSac = iD_MauSac;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }
    
    
}
