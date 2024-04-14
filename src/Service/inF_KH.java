/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import Model.KhachHang;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface inF_KH {
    public List<KhachHang> getAll();
    public boolean addKH(KhachHang kh);
    public boolean deleteKH(int id_kh);
    public boolean updateKH(KhachHang kh,int id_kh);
    
}
