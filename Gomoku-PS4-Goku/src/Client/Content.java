/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aodyra
 */
public class Content {
    private ArrayList<String> userPlayer;
    private ArrayList<String> userWatch;
    private ArrayList<String> chatRoom;
    private ArrayList<String> listRoom;
    private int noroom;
    private String username;
    private Gomoku gomoku;
    private int urutan;
    
    public Content(Socket sock) throws IOException {
        userPlayer = new ArrayList<String>();
        userWatch = new ArrayList<String>();
        chatRoom = new ArrayList<String>();
        listRoom = new ArrayList<String>();
        gomoku = new Gomoku(sock);
        noroom = -1;
        username = "";
        urutan = -1;
    }
    
    public void deleteFromListRoom(int noRoom){
        int indeks = getListRoom().indexOf(String.valueOf(noRoom));
        if ( indeks > -1) {
            getListRoom().remove(indeks);
            gomoku.updateListRoom(getListRoom());
        }
    }
    
    public void addUserWatch(String username){
        getUserWatch().add(username);
        gomoku.updateUserWaitingWatch(userWatch);
        gomoku.updateUserRoomWatch(userWatch);
    }
    
    public void addUserPlayer(String username){
        getUserPlayer().add(username);
        gomoku.updateUserWaitingPlayer(userPlayer);
        gomoku.updateUserRoomPlayer(userPlayer);
    }
    
    public void addChatRoom(String chat){
        gomoku.updateChat(chat);
    }
    
    public void addListRoom(String room){
        getListRoom().add(room);
        gomoku.updateListRoom(listRoom);
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
        gomoku.setName(username);
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
        gomoku.updateListRoom(listRoom);
    }

    /**
     * @param userWatch the userWatch to set
     */
    public void setUserWatch(ArrayList<String> userWatch) {
        this.userWatch = userWatch;
        gomoku.updateUserWaitingWatch(userWatch);
        gomoku.updateUserRoomWatch(userWatch);
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
        gomoku.setNoroom(noroom);
    }

    /**
     * @return the userPlayer
     */
    public ArrayList<String> getUserPlayer() {
        return userPlayer;
    }

    /**
     * @param userPlayer the userPlayer to set
     */
    public void setUserPlayer(ArrayList<String> userPlayer) throws IOException {
        this.userPlayer = userPlayer;
        urutan = getUserPlayer().indexOf(username);
        gomoku.setUrutanuser(urutan);
        gomoku.updateUserWaitingPlayer(userPlayer);
        gomoku.updateUserRoomPlayer(userPlayer);
    }
    
    public void toLobby(){
    	System.err.println("toLobby");
        gomoku.toLobby();
    }
    
    public void toWaitingRoom(){
        gomoku.toWaitingRoom();
    }
    
    public void toRoom() throws IOException{
        gomoku.toRoom();
    }
    
    public void toHome(){
        gomoku.toHome();
    }
    
    public void setMatrix(int noturn,int x, int y){
        gomoku.updateMatrix(noturn, x, y);
    }
    
    public void alert(){
        gomoku.alertNickNameAlready();
    }
    
    public void changeStatusBoard(boolean status){
        gomoku.changeStatusplayer(status);
    }
    
    public void outTurn(){
        gomoku.outTurn();
    }
    
    public void anotherTurn(){
        gomoku.anotherTurn();
    }
    
    public void setLabelName(){
        gomoku.setLabelName();
    }
    
    public void setLabelRoom(){
        gomoku.setLabelRoom();
    }
    
    public void winner(int urut){
        String nameuser = getUserPlayer().get(urut);
        gomoku.winner(nameuser);
    }
}
