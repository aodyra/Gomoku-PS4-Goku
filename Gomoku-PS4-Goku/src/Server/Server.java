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
	private Map<String, User> mapUser;
	private Map<Integer, Room> mapRoom;
	private ServerSocket serverSock;
	private BlockingQueue<Packet> bque;
	private UserRegister userReg;
	private Thread userRegThread;
	
	public Server(int port) throws IOException {
		serverSock = new ServerSocket(port);
		mapUser = new HashMap<String, User>();
		bque = (BlockingQueue<Packet>) new LinkedBlockingQueue();
		userReg = new UserRegister(serverSock, mapUser, bque);
		userRegThread = new Thread(userReg);
	}
	
	public void start() {
		userRegThread.start();
		while(true) {
			try {
				Packet p = (Packet) bque.take();
				if(mapUser.containsKey(p.getUserName()))
					continue;
				User user = mapUser.get(p.getUserName());
				switch(p.getType()) {
					case Packet.REGISTER :
						System.out.println("Register : " + p.getUserName());
						user.getOutput().writeObject(p);
						break;
					case Packet.CREATE_ROOM :
						System.out.println("Create Room : "+ p.getRoom());
						createRoom(p.getRoom(), p.getUserName());
						break;
					case Packet.JOIN_ROOM :
						System.out.println("JOIN ROOM : " + p.getUserName() + " to Room " + p.getRoom());
						joinRoom(p.getRoom(), p.getUserName());
						break;
					case Packet.WATCH_ROOM :
						System.out.println("WATCH ROOM : " + p.getUserName() + " to Room " + p.getRoom());
						watchRoom(p.getRoom(), p.getUserName());
						break;
					case Packet.START_GAME :
						System.out.println("START GAME : " + p.getRoom() + " from " + p.getUserName());
						startGame(p.getRoom(), p.getUserName());
						break;
					case Packet.PUT_PAWN :
						System.out.println("PUT PAWN : on Room " + p.getRoom() + " from " + p.getUserName());
						putPawn(p.getRoom(),p.getUserName(), p.getPosX(),p.getPosY());
						break;
					case Packet.SEND_CHAT :
						System.out.println("START GAME : " + p.getMessage());
						break;
					case Packet.USER_DISCONNECT :
						System.out.println("USER DISCONNECT : " + p.getMessage());
						break;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void createRoom(int noRoom, String userName) {
		// Check whether this user exists or not
		if(!mapUser.containsKey(userName))
			return;
		User user = mapUser.get(userName);
		// Check whether this room exists or not
		if(mapRoom.containsKey(noRoom)) {
			// send response to that user
			Packet response = new Packet(Packet.JOIN_ROOM, userName);
			try {
				user.getOutput().writeObject(response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			Room room = new Room(noRoom, user);
			mapRoom.put(noRoom, room);
			
			// Send response to every user in room
			Packet response = new Packet(Packet.JOIN_ROOM, userName);
			response.setRoom(noRoom);
			for(User us : room.getUser()) {
				try {
					us.getOutput().writeObject(response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void joinRoom(int noRoom, String userName) {
		if(!mapUser.containsKey(userName))
			return;
		User user = mapUser.get(userName);
		if(mapRoom.containsKey(noRoom)) {
			Room room = mapRoom.get(noRoom);
			room.addUser(user);
			// send response to every user in room
			Packet response = new Packet(Packet.JOIN_ROOM, userName);
			response.setRoom(noRoom);
			for(User us : room.getUser()){
				try {
					us.getOutput().writeObject(response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else {
			Packet response = new Packet(Packet.JOIN_ROOM, userName);
			response.setRoom(-1);
			try {
				user.getOutput().writeObject(response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void watchRoom(int noRoom, String userName) {
		if(!mapUser.containsKey(userName))
			return;
		User user = mapUser.get(userName);
		if(mapRoom.containsKey(noRoom)) {
			Room room = mapRoom.get(noRoom);
			room.addSpectator(user);
			// send response to every user in room
			Packet response = new Packet(Packet.WATCH_ROOM, userName);
			response.setRoom(noRoom);
			for(User us : room.getUser()){
				try {
					us.getOutput().writeObject(response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else {
			Packet response = new Packet(Packet.WATCH_ROOM, userName);
			response.setRoom(-1);
			try {
				user.getOutput().writeObject(response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	void startGame(int noRoom, String userName){
		// Check if user is valid
		if(!mapUser.containsKey(userName))
			return;
		User user = mapUser.get(userName);
		// Check if room is valid
		if(!mapRoom.containsKey(noRoom)) {
			Packet packet = new Packet(Packet.START_GAME, userName);
			packet.setRoom(-1);
			try {
				user.getOutput().writeObject(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		Room room = mapRoom.get(noRoom);
		// Check if request is come from user that create room
		if(!room.getCreatedBy().getName().equals(user.getName())) {
			Packet packet = new Packet(Packet.START_GAME, userName);
			packet.setRoom(-1);
			try {
				user.getOutput().writeObject(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		// Check if User >= 3
		if(room.getUser().size() < 3) {
			Packet packet = new Packet(Packet.START_GAME, userName);
			packet.setRoom(-1);
			try {
				user.getOutput().writeObject(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		room.setStarted();
		// Broadcast to all user that game has been started
		Packet response = new Packet(Packet.START_GAME, userName);
		response.setRoom(noRoom);
		for(User us : room.getUser()) {
			try {
				us.getOutput().writeObject(response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	void putPawn(int noRoom, String userName, int x, int y) {
		// Check if user is valid
		if(!mapUser.containsKey(userName))
			return;
		User user = mapUser.get(userName);
		// Check if room is valid
		if(!mapRoom.containsKey(noRoom)) {
			Packet packet = new Packet(Packet.PUT_PAWN, userName);
			packet.setRoom(-1);
			try {
				user.getOutput().writeObject(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		Room room = mapRoom.get(noRoom);
		int noTurn = user.getNoTurn();
		
		// KE BAWAH BELUM SELESAI
		if(room.putPawn(noTurn, x, y)) {
			
		}
		else {
			Packet response = new Packet(Packet.PUT_PAWN, userName);
			response.setRoom(noRoom);
			response.setPosX(x);
			response.setPosY(y);
			
		}
	}
	void send(int noRoon, String userName, String message) {
		
	}
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
			Server server = new Server(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}