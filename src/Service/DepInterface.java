/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import Model.Dep;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface DepInterface {

    List<Dep> getAll();

    List<Dep> timKiem(Object input);

   
    List<Dep> getTen();

    List<Dep> getTrangThai();

    List<Object[]> DepBouth(int id);

    boolean addDep(Dep d);

    boolean xoaDep(int ma, Integer trangThai);

    boolean update(Dep d, int ma);

    public int countSoLuong(int id);
}
