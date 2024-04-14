/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class ChatLieu {
    
    private Integer id_ChatLieu;
    private String tenChatLieu;

    public ChatLieu() {
    }

    public ChatLieu(String tenChatLieu) {
        this.id_ChatLieu = id_ChatLieu;
        this.tenChatLieu = tenChatLieu;
    }

    public Integer getId_ChatLieu() {
        return id_ChatLieu;
    }

    public void setId_ChatLieu(Integer id_ChatLieu) {
        this.id_ChatLieu = id_ChatLieu;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }
    
    
}
