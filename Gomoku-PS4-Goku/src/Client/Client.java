/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Server.Packet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aodyra
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length < 2) {
    		System.err.println("How to Use:\n\tjava Client <hostname> <port>\n");
    		System.exit(1);
    	}
    	String hostname = args[0];
    	int port = Integer.parseInt(args[1]);
    	try {
                BlockingQueue<Packet> bque = (BlockingQueue<Packet>) new LinkedBlockingQueue();
                Socket sock = new Socket(hostname,port);
                Content content = new Content(sock);
                ToClientFromServer rev = new ToClientFromServer(sock,bque);
                Thread th = new Thread(rev);
                th.start();
                while(true){
                    Packet p = (Packet) bque.take();
                    
                    switch(p.getType()){
                        case Packet.REGISTER :
                        	System.out.println("REGISTER :\n" + p);
                            if(p.getRoom() == 1) {
                            	content.setUsername(p.getUserName());
                                content.setListRoom(p.getArrayString());
                                content.setLabelName();
                                content.toLobby();
                            }
                            else  {
                                // TODO
                                // alert multiple userName
                                content.alert();
                            }

                            break;
                        case Packet.CREATE_ROOM :
                        	System.out.println("CREATE_ROOM :\n" + p);
                                if (p.getUserName().equals(content.getUsername())){
                                    if(p.getRoom() == 1) {
                                        // TODO
                                        // add this room [p.getRoom()] to room list
                                        content.setListRoom(p.getArrayString());
                                    }
                                    else {
                                        // TODO
                                        // Alert cannot create room
                                    }
                                }
                                break;
                        case Packet.JOIN_ROOM :
                                if(p.getUserName().equals(content.getUsername())){
                                    if(p.getRoom() >= 0) {
                                        // TODO
                                        // set room = p.getRoom()
                                        // This client join this room
                                        // to WAITING ROOM and update list user = p.getArrayString() and list spectator = p.getArrayString2()
                                        content.setNoroom(p.getRoom());
                                        content.setUserPlayer(p.getArrayString());
                                        content.setUserWatch(p.getArrayString2());
                                        content.setLabelRoom();
                                        content.toWaitingRoom();
                                    }
                                    else {
                                        // TODO
                                        // Alert cannot join room
                                    }
                                }
                                break;
                        case Packet.WATCH_ROOM :
                                if(p.getUserName().equals(content.getUsername())){
                                    if(p.getRoom() >= 0) {
                                        // TODO
                                        // set room = p.getRoom()
                                        // This client watch this room
                                        // to WAITING ROOM and update list user = p.getArrayString() and list spectator = p.getArrayString2()
                                        content.setUserPlayer(p.getArrayString());
                                        content.setUserWatch(p.getArrayString2());
                                        content.toWaitingRoom();
                                    }
                                    else {
                                        // TODO
                                        // Alert cannot join room
                                    }
                                }
                                break;
                        case Packet.START_GAME :
                                if(p.getUserName().equals(content.getUsername())) {
                                    if(p.getRoom() >= 0) {
                                        // TODO
                                        // set status game to PLAY
                                        // to ROOM
                                        content.deleteFromListRoom(p.getRoom());
                                        content.toRoom();
                                    }
                                    else{
                                        // Cannot start
                                    }
                                }
                                
                                break;
                        case Packet.PUT_PAWN :
                                if(p.getUserName().equals(content.getUsername())) {
                                    if(p.getRoom() >= 0) {
                                        if(p.getTurn() < 0) {
                                            // TODO
                                            // Notify that this is your move
                                            // make client can to select position
                                            content.changeStatusBoard(true);
                                            content.outTurn();
                                        }
                                        else {
                                            // TODO
                                            // set matrix p.getPosX() and p.getPosY() to p.getTurn();
                                            content.changeStatusBoard(false);
                                            content.anotherTurn();
                                            content.setMatrix(p.getTurn(), p.getPosX(), p.getPosY());
                                        }
                                    }
                                    else{
                                        // Cannot put pawn
                                    }
                                }
                                break;
                        case Packet.SEND_CHAT :
                                if(p.getUserName().equals(content.getUsername())){
                                    if(p.getRoom() >= 0) {
                                        // TODO
                                        // Add p.getMessage() to chat box
                                        content.addChatRoom(p.getMessage());
                                    }
                                    else {
                                        // cannot update chat
                                    }
                                }
                                break;
                        case Packet.USER_DISCONNECT :
                                if(p.getUserName().equals(content.getUsername()) && p.getRoom() >= 0){
                                    if(p.getTurn() >= 0) {
                                        // TODO
                                        // mark user as disconnect that hi turn is p.getTurn()
                                    }
                                    else {
                                        // TODO
                                        // remove or mark spectator that his name is p.getUserName()
                                    }
                                }
                                break;
                        case Packet.FINISH :
                            if(p.getUserName().equals(content.getUsername()) && p.getRoom() >= 0){
                                // notify that the winner is player in turn p.getTurn();
                                // then back to lobby
                            }
                    }
                }
                
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
