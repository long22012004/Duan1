/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class HinhThucThanhToan {
    private Integer id;
    private String hinhThucThanhToan;

    public HinhThucThanhToan() {
    }

    public HinhThucThanhToan(Integer id, String hinhThucThanhToan) {
        this.id = id;
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(String hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }
    
    
}
