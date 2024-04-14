/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class ThuongHieu {
    
    private Integer ID_ThuongHieu;
    private String tenThuongHieu;

    public ThuongHieu() {
    }

    public ThuongHieu(String tenThuongHieu) {
        this.ID_ThuongHieu = ID_ThuongHieu;
        this.tenThuongHieu = tenThuongHieu;
    }

    public Integer getID_ThuongHieu() {
        return ID_ThuongHieu;
    }

    public void setID_ThuongHieu(Integer ID_ThuongHieu) {
        this.ID_ThuongHieu = ID_ThuongHieu;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }
    
    
}
