/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Wiwit
 */
public class Server {
	private static ServerSocket serverSock;
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	if(args.length < 1) {
    		System.err.println("How to Use:\n\tjava Server <port>\n");
    		System.exit(1);
    	}
        int port = Integer.parseInt(args[0]);
        try {
			serverSock = new ServerSocket(port);
			Map<String, User> mapUser = new HashMap<String, User>();
			BlockingQueue<Packet> bque = (BlockingQueue<Packet>) new LinkedBlockingQueue();
			UserRegister userReg = new UserRegister(serverSock, mapUser, bque);
			Thread th = new Thread(userReg);
			th.start();
			while(true) {
				Packet p = (Packet) bque.take();
				switch(p.getType()) {
					case Packet.CREATE_ROOM :
						System.out.println("Create Room : "+ p.getMessage() + " FROM " + p.getUserName());
						break;
					case Packet.JOIN_ROOM :
						System.out.println("JOIN ROOM : " + p.getMessage());
						break;
					case Packet.WATCH_ROOM :
						System.out.println("WATCH ROOM : " + p.getMessage());
						break;
					case Packet.START_GAME :
						System.out.println("START GAME : " + p.getMessage());
						break;
					case Packet.PUT_PAWN :
						System.out.println("PUT PAWN : " + p.getMessage());
						break;
					case Packet.SEND_CHAT :
						System.out.println("START GAME : " + p.getMessage());
						break;
					case Packet.USER_DISCONNECT :
						System.out.println("USER DISCONNECT : " + p.getMessage());
						break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
    }
}