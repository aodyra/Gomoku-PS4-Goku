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
                contentRoom content = new contentRoom();
                ToClientFromServer rev = new ToClientFromServer(sock,bque);
                Thread th = new Thread(rev);
                th.start();
                while(true){
                    Packet p = (Packet) bque.take();
                    switch(p.getType()){
                        case Packet.REGISTER:
                            
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
