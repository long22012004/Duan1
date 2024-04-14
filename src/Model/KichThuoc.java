/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class KichThuoc {
    private Integer iD_KichThuoc;
    private String size;

    public KichThuoc() {
    }

    public KichThuoc(Integer iD_KichThuoc, String size) {
        this.iD_KichThuoc = iD_KichThuoc;
        this.size = size;
    }

    public Integer getiD_KichThuoc() {
        return iD_KichThuoc;
    }

    public void setiD_KichThuoc(Integer iD_KichThuoc) {
        this.iD_KichThuoc = iD_KichThuoc;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    
    
}
