/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import Server.Packet;

/**
 *
 * @author aodyra
 */
public class ServerTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	String hostname = args[0];
    	int port = Integer.parseInt(args[1]);
    	try {
			Socket sock = new Socket(hostname, port);
			System.out.println("Socket connected");
			ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
			System.out.println("Output Stream");
			Packet name = new Packet(0, "Wiwit");
			System.out.println("jancuk");
			out.writeObject(name);

			ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
			System.out.println("Input Stream");

			Packet p = (Packet)in.readObject();
			System.err.println("Type =" + p.getType() + "\n Name : " + p.getUserName() + " List Room : " + p.getArrayString());
			
			// Create Room
			Packet pa = new Packet(1, "Wiwit");
			pa.setMessage("Helloo World!");
			pa.setRoom(8);

			System.out.println(" Type : " + pa.getType() + "\n Name : " + pa.getUserName()+ "\n Room : " + pa.getRoom() + "\n message : " + pa.getMessage() + "\n");
			out.writeObject(pa);
			Packet res = (Packet)in.readObject();
			System.err.println(" Type : " + res.getType() + "\n Name : " + res.getUserName()+ "\n Room : " + res.getRoom() + "\n message : " + res.getMessage() + "\n User : "+res.getArrayString() + "\n Spectator : "+res.getArrayString2());
			
			// Join Room
			pa = new Packet(Packet.JOIN_ROOM, "Wiwit");
			pa.setMessage("Helloo World!");
			pa.setRoom(9);

			System.out.println(" Type : " + pa.getType() + "\n Name : " + pa.getUserName()+ "\n Room : " + pa.getRoom() + "\n message : " + pa.getMessage() + "\n");
			out.writeObject(pa);
			res = (Packet)in.readObject();
			System.err.println(" Type : " + res.getType() + "\n Name : " + res.getUserName()+ "\n Room : " + res.getRoom() + "\n message : " + res.getMessage() + "\n User : "+res.getArrayString() + "\n Spectator : "+res.getArrayString2());
			
			// Watch Room
			pa = new Packet(Packet.WATCH_ROOM, "Wiwit");
			pa.setMessage("Helloo World!");
			pa.setRoom(9);

			System.out.println(" Type : " + pa.getType() + "\n Name : " + pa.getUserName()+ "\n Room : " + pa.getRoom() + "\n message : " + pa.getMessage() + "\n");
			out.writeObject(pa);
			res = (Packet)in.readObject();
			System.err.println(" Type : " + res.getType() + "\n Name : " + res.getUserName()+ "\n Room : " + res.getRoom() + "\n message : " + res.getMessage() + "\n User : "+res.getArrayString() + "\n Spectator : "+res.getArrayString2());			
			
			// Start Game
			pa = new Packet(Packet.START_GAME, "Wiwit");
			pa.setMessage("Helloo World!");
			pa.setRoom(8);

			System.out.println(" Type : " + pa.getType() + "\n Name : " + pa.getUserName()+ "\n Room : " + pa.getRoom() + "\n message : " + pa.getMessage() + "\n");
			out.writeObject(pa);
			res = (Packet)in.readObject();
			System.err.println(" Type : " + res.getType() + "\n Name : " + res.getUserName()+ "\n Room : " + res.getRoom() + "\n message : " + res.getMessage() + "\n User : "+res.getArrayString() + "\n Spectator : "+res.getArrayString2());			
			while((res = (Packet)in.readObject()) != null) {
				System.err.println(" Type : " + res.getType() + "\n Name : " + res.getUserName()+ "\n Room : " + res.getRoom() + "\n message : " + res.getMessage() + "\n User : "+res.getArrayString() + "\n Spectator : "+res.getArrayString2());
			}
			
			out.close();
			in.close();
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
