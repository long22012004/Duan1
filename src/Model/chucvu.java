/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Lenovo
 */
public class chucvu {
     private Integer id;
     private String tencv;

    public chucvu() {
    }

    public chucvu(Integer id, String tencv) {
        this.id = id;
        this.tencv = tencv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTencv() {
        return tencv;
    }

    public void setTencv(String tencv) {
        this.tencv = tencv;
    }

    @Override
    public String toString() {
        return "chucvu{" + "id=" + id + ", tencv=" + tencv + '}';
    }
     
     
}
