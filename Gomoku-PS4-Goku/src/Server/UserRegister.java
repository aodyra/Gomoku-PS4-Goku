package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class UserRegister implements Runnable {
	Map<String, User> userMap;
	ServerSocket sock;
	BlockingQueue<Packet> bque;
	
	public UserRegister(ServerSocket sock, Map<String, User> userMap, BlockingQueue<Packet> bque) {
		this.sock = sock;
		this.userMap= userMap;
		this.bque = bque;
	}
	@Override
	public void run() {
		System.err.println("UserRegister started");
		Socket clientSock = null;
		try {
			while((clientSock = sock.accept()) != null) {
				ObjectInputStream in = new ObjectInputStream(clientSock.getInputStream());
				Packet packet = (Packet)in.readObject(); 
				String name = packet.getUserName();
				System.err.println(name + " Connecting..");
				if(userMap.containsKey(name)) {
					packet.setRoom(0);
					bque.put(packet);
				}
				else {
					userMap.put(name, new User(name, clientSock, in, bque));
					packet.setRoom(1);
					bque.put(packet);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}