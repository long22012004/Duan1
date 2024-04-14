/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class XuatXu {
    private Integer iD_XuatXu;
    private String QuocGia;

    public XuatXu() {
    }

    public XuatXu(Integer iD_XuatXu, String QuocGia) {
        this.iD_XuatXu = iD_XuatXu;
        this.QuocGia = QuocGia;
    }

    public Integer getiD_XuatXu() {
        return iD_XuatXu;
    }

    public void setiD_XuatXu(Integer iD_XuatXu) {
        this.iD_XuatXu = iD_XuatXu;
    }

    public String getQuocGia() {
        return QuocGia;
    }

    public void setQuocGia(String QuocGia) {
        this.QuocGia = QuocGia;
    }
    
    
}
