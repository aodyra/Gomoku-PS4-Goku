/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import Server.Packet;

/**
 *
 * @author Wiwit
 */
public class ServerTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	String hostname = args[0];
    	int port = Integer.parseInt(args[1]);
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	try {
			String name = br.readLine();
			
			System.out.println("Hello " + name);
			System.out.flush();
			Socket sock = new Socket(hostname, port);
			System.out.println("Socket connected");
			ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
			System.out.println("Output Stream");
			Packet pname = new Packet(0, name);
			out.writeObject(pname);

			ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
			System.out.println("Input Stream");

			Packet p = (Packet)in.readObject();
			System.err.println(p);
			
			// Create Room
			int noRoom = br.read();
			Packet pa = new Packet(Packet.CREATE_ROOM, name);
			pa.setMessage("Helloo World!");
			pa.setRoom(noRoom-1);

			System.out.println(pa);
			out.writeObject(pa);
			Packet res = (Packet)in.readObject();
			System.err.println(res);
			
			// Join Room
//			pa = new Packet(Packet.JOIN_ROOM, name);
//			pa.setMessage("Helloo World!");
//			pa.setRoom(noRoom);
//
//			System.out.println(pa);
//			out.writeObject(pa);
//			res = (Packet)in.readObject();
//			System.err.println(res);
			
			// Watch Room
			pa = new Packet(Packet.WATCH_ROOM, name);
			pa.setMessage("Helloo World!");
			pa.setRoom(noRoom);

			System.out.println(pa);
			out.writeObject(pa);
			res = (Packet)in.readObject();
			System.err.println(res);			
			
			// Start Game
			pa = new Packet(Packet.START_GAME, name);
			pa.setMessage("Helloo World!");
			pa.setRoom(noRoom);

			System.out.println(pa);
			out.writeObject(pa);
			res = (Packet)in.readObject();
			System.err.println(res);			
			while((res = (Packet)in.readObject()) != null) {
				System.err.println(res);
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
