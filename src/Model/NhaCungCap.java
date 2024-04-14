/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class NhaCungCap {
    
    private Integer iD_NhaCungCap;
    private String tenNhaCungCap;

    public NhaCungCap() {
    }

    public NhaCungCap(String tenNhaCungCap) {
        this.iD_NhaCungCap = iD_NhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public Integer getiD_NhaCungCap() {
        return iD_NhaCungCap;
    }

    public void setiD_NhaCungCap(Integer iD_NhaCungCap) {
        this.iD_NhaCungCap = iD_NhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }
    
    
}
