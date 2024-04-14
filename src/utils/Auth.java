/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import Model.NhanVien;



/**
 *
 * @author ACER
 */
public class Auth {
    public static NhanVien user=null;
    
    public static void clear(){
        Auth.user=null;
    }
    
    public static boolean isLogin(){
        return Auth.user!=null;
    }
    
    public static int idNhanVien(){
        return user.getIdnv();
    }
    
    public static String maNhanVien(){
        return user.getManv();
    }
    public static String tenNhanVien(){
        return user.getTennv();
        }
}
