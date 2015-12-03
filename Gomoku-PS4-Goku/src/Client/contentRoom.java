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
public class contentRoom {
    private ArrayList<String> userInRoom;
    private ArrayList<String> userWatch;
    private ArrayList<String> chatRoom;
    private ArrayList<String> userWaiting;
    
    public contentRoom() {
        userInRoom = new ArrayList<String>();
        userWatch = new ArrayList<String>();
        chatRoom = new ArrayList<String>();
        userWaiting = new ArrayList<String>();
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
    
    
}
