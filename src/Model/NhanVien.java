/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Lenovo
 */
public class NhanVien {
    private int idnv;
    private String manv;
    private String tennv;
    private String diachi;
    private String sdt;
    private String email;
    private int  namsinh;
    private float luong;
    private int idchucvu;
    private int idtaikhoan;
    private boolean gioitinh;
    private boolean trangthai;
    private String tenCV;
    private String tenTK;

    public NhanVien() {
    }

    public NhanVien(int idnv, String manv, String tennv, String diachi, String sdt, String email, int namsinh, float luong, int idchucvu, int idtaikhoan, boolean gioitinh, boolean trangthai, String tenCV, String tenTK) {
        this.idnv = idnv;
        this.manv = manv;
        this.tennv = tennv;
        this.diachi = diachi;
        this.sdt = sdt;
        this.email = email;
        this.namsinh = namsinh;
        this.luong = luong;
        this.idchucvu = idchucvu;
        this.idtaikhoan = idtaikhoan;
        this.gioitinh = gioitinh;
        this.trangthai = trangthai;
        this.tenCV = tenCV;
        this.tenTK = tenTK;
    }

    public int getIdnv() {
        return idnv;
    }

    public void setIdnv(int idnv) {
        this.idnv = idnv;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(int namsinh) {
        this.namsinh = namsinh;
    }

    public float getLuong() {
        return luong;
    }

    public void setLuong(float luong) {
        this.luong = luong;
    }

    public int getIdchucvu() {
        return idchucvu;
    }

    public void setIdchucvu(int idchucvu) {
        this.idchucvu = idchucvu;
    }

    public int getIdtaikhoan() {
        return idtaikhoan;
    }

    public void setIdtaikhoan(int idtaikhoan) {
        this.idtaikhoan = idtaikhoan;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }
 
    
    
    
    
}
