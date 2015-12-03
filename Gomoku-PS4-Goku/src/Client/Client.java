/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Server.Packet;

/**
 *
 * @author aodyra
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	String hostname = args[0];
    	int port = Integer.parseInt(args[1]);
    	try {
			Socket sock = new Socket(hostname, port);
			ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
			
			// test 
			for(int i = 1; i<= 7; i++) {
				Packet p = new Packet(i, "Aodyra");
				p.setMessage("Helloo World!");
				out.writeObject(p);
			}
			
			out.close();
			sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
