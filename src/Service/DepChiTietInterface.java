/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import Model.DepChiTiet;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface DepChiTietInterface {
    List<DepChiTiet> getAll();
    List<DepChiTiet> tim(Float min,Float max);
    boolean addDepChiTiet(DepChiTiet dct);
    boolean sua(DepChiTiet dct,Integer ma);
}
