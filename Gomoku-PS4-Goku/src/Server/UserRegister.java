package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		Socket clientSock;
		try {
			while((clientSock = sock.accept()) != null) {
				String name = "Aodyra";
				userMap.put(name, new User(name, clientSock, bque));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}