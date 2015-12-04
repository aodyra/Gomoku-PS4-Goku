/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aodyra
 */
public class Content {
    private ArrayList<String> userInRoom;
    private ArrayList<String> userWatch;
    private ArrayList<String> chatRoom;
    private ArrayList<String> userWaiting;
    private ArrayList<String> listRoom;
    private int noroom;
    private String username;
    
    public Content() {
        userInRoom = new ArrayList<String>();
        userWatch = new ArrayList<String>();
        chatRoom = new ArrayList<String>();
        userWaiting = new ArrayList<String>();
        listRoom = new ArrayList<String>();
    }
    
    public void addUserWatch(String username){
        getUserWatch().add(username);
    }
    
    public void addUserInRoom(String username){
        getUserInRoom().add(username);
    }
    
    public void addUserWaiting(String username){
        getUserWaiting().add(username);
    }
    
    public void addChatRoom(String chat){
        getChatRoom().add(chat);
    }
    
    public void addListRoom(String room){
        getListRoom().add(room);
    }
    /**
     * @return the userInRoom
     */
    public ArrayList<String> getUserInRoom() {
        return userInRoom;
    }

    /**
     * @return the userWatch
     */
    public ArrayList<String> getUserWatch() {
        return userWatch;
    }

    /**
     * @return the chatRoom
     */
    public ArrayList<String> getChatRoom() {
        return chatRoom;
    }

    /**
     * @return the userWaiting
     */
    public ArrayList<String> getUserWaiting() {
        return userWaiting;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the listRoom
     */
    public ArrayList<String> getListRoom() {
        return listRoom;
    }

    /**
     * @param listRoom the listRoom to set
     */
    public void setListRoom(ArrayList<String> listRoom) {
        this.listRoom = listRoom;
    }

    /**
     * @param userWaiting the userWaiting to set
     */
    public void setUserWaiting(ArrayList<String> userWaiting) {
        this.userWaiting = userWaiting;
    }

    /**
     * @param userInRoom the userInRoom to set
     */
    public void setUserInRoom(ArrayList<String> userInRoom) {
        this.userInRoom = userInRoom;
    }

    /**
     * @param userWatch the userWatch to set
     */
    public void setUserWatch(ArrayList<String> userWatch) {
        this.userWatch = userWatch;
    }

    /**
     * @return the noroom
     */
    public int getNoroom() {
        return noroom;
    }

    /**
     * @param noroom the noroom to set
     */
    public void setNoroom(int noroom) {
        this.noroom = noroom;
    }
    
    
}
