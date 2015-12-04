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
import java.util.ArrayList;
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
	private int unUsedRoom;
	
	public Server(int port) throws IOException {
		serverSock = new ServerSocket(port);
		mapUser = new HashMap<String, User>();
		mapRoom = new HashMap<Integer, Room>();
		bque = (BlockingQueue<Packet>) new LinkedBlockingQueue();
		userReg = new UserRegister(serverSock, mapUser, bque);
		userRegThread = new Thread(userReg);
		unUsedRoom = 1;
	}
	
	public void start() {
		System.out.println("Start");
		userRegThread.start();
		while(true) {
			try {
				Packet p = (Packet) bque.take();
				User user = mapUser.get(p.getUserName());
				switch(p.getType()) {
					case Packet.REGISTER :
						System.out.println("Register : " + p.getUserName());
						ArrayList< String > roomList = new ArrayList<String>();
						for(Map.Entry<Integer, Room> entry : mapRoom.entrySet()) {
							roomList.add(""+entry.getKey());
						}
						p.setArrayString(roomList);
						user.getOutput().writeObject(p);
						break;
					case Packet.CREATE_ROOM :
						System.out.println("Create Room : ");
						createRoom(unUsedRoom++, p.getUserName());
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
						System.out.println("SEND CHAT : on Room " + p.getRoom() + " from " + p.getUserName() + " message : "  + p.getMessage());
						sendChat(p.getRoom(), p.getUserName(), p.getMessage());
						break;
					case Packet.USER_DISCONNECT :
						System.out.println("USER DISCONNECT : " + p.getUserName());
						disconnectUser(p.getUserName());						
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
		if(!mapUser.containsKey(userName)) {
			System.err.println("User doesn't exists");
			return;
		}
		User user = mapUser.get(userName);
		// Check whether this room exists or not
		if(mapRoom.containsKey(noRoom)) {
			// send response to that user
			Packet response = new Packet(Packet.CREATE_ROOM, userName);
			response.setRoom(-1);
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
			// Send broadcast to every User
			Packet response = new Packet(Packet.CREATE_ROOM, userName);
			response.setRoom(noRoom);
			response.getArrayString().add(""+noRoom);
			for(Map.Entry<String, User> entry : mapUser.entrySet()) {
				User us = entry.getValue();
				if(!us.getIsActive())
					continue;
				response.setUserName(us.getName());
				try {
					us.getOutput().writeObject(response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			// Send response to every user in room to join
			response = new Packet(Packet.JOIN_ROOM, userName);
			response.setRoom(noRoom);
			response.getArrayString().add(userName);
			response.setMessage("Room created succesfully!");
			for(User us : room.getUser()) if(us.getIsActive()){
				try {
					response.setUserName(us.getName());
					us.getOutput().writeObject(response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void joinRoom(int noRoom, String userName) {
		// Check if User exists
		if(!mapUser.containsKey(userName)) {
			return;
		}
		User user = mapUser.get(userName);
		if(mapRoom.containsKey(noRoom)) {
			Room room = mapRoom.get(noRoom);
			room.addUser(user);
			// send response to every user in room
			Packet response = new Packet(Packet.JOIN_ROOM, userName);
			response.setRoom(noRoom);
			ArrayList<String> userList = new ArrayList<String> ();
			ArrayList<String> specList = new ArrayList<String> ();
			for(int i = 0; i<room.getUser().size(); i++)
				userList.add(room.getUser().get(i).getName());
			for(int i = 0; i<room.getSpectator().size(); i++)
				specList.add(room.getSpectator().get(i).getName());
			response.setArrayString(userList);
			response.setArrayString2(specList);
			response.setMessage("Join succes!");
			for(User us : room.getUser()) if(us.getIsActive()){
				try {
					response.setUserName(us.getName());
					us.getOutput().writeObject(response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for(User us : room.getSpectator()) if(us.getIsActive()){
				try {
					response.setUserName(us.getName());
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
		// Check if user exits
		if(!mapUser.containsKey(userName))
			return;
		User user = mapUser.get(userName);
		if(mapRoom.containsKey(noRoom)) {
			Room room = mapRoom.get(noRoom);
			room.addSpectator(user);
			// send response to every user in room
			Packet response = new Packet(Packet.WATCH_ROOM, userName);
			response.setRoom(noRoom);
			ArrayList<String> userList = new ArrayList<String> ();
			ArrayList<String> specList = new ArrayList<String> ();
			for(int i = 0; i<room.getUser().size(); i++)
				userList.add(room.getUser().get(i).getName());
			for(int i = 0; i<room.getSpectator().size(); i++)
				specList.add(room.getSpectator().get(i).getName());
			response.setArrayString(userList);
			response.setArrayString2(specList);
			response.setMessage("Watch succes");
			for(User us : room.getSpectator()) if(us.getIsActive()){
				try {
					response.setUserName(us.getName());
					us.getOutput().writeObject(response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for(User us : room.getUser()) if(us.getIsActive()){
				try {
					response.setUserName(us.getName());
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
		// Check if User >= 3
		if(room.getUser().size() < 2) {
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
		if(room.getStarted())
			return;
		room.setStarted(true);
		// Broadcast to all user that game has been started
		Packet response = new Packet(Packet.START_GAME, userName);
		response.setRoom(noRoom);
		response.setTurn(0);
		for(Map.Entry<String, User> entry: mapUser.entrySet()) {
			User us = entry.getValue();
			if(!us.getIsActive())
				continue;
			try {
				response.setUserName(us.getName());
				us.getOutput().writeObject(response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		room.setTurn(-1);
		int turn = room.nextTurn();
		User nowTurn = room.getUser().get(turn);
		response = new Packet(Packet.PUT_PAWN, nowTurn.getName());
		response.setRoom(room.getNoRoom());
		try {
			nowTurn.getOutput().writeObject(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		if(noTurn != room.getTurn())
			return;
		if(room.putPawn(noTurn, x, y)) {
			// Sent to all user in room to notify the winner
			Packet response = new Packet(Packet.FINISH, userName);
			response.setRoom(noRoom);
			response.setTurn(noTurn);
			for(User us : room.getUser()) if(us.getIsActive()){
				try {
					response.setUserName(us.getName());
					us.getOutput().writeObject(response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				us.setRoom(-1);
				us.setNoTurn(-1);
			}
			for(User us : room.getSpectator()) if(us.getIsActive()){
				try {
					response.setUserName(us.getName());
					us.getOutput().writeObject(response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				us.setRoom(-1);
				us.setNoTurn(-1);
			}
			mapRoom.remove(noRoom);
		}
		else {
			// Send to user in room to update their board
			Packet response = new Packet(Packet.PUT_PAWN, userName);
			response.setRoom(noRoom);
			response.setPosX(x);
			response.setPosY(y);
			response.setTurn(noTurn);
			for(User us : room.getUser()) if(us.getIsActive()){
				try {
					response.setUserName(us.getName());
					us.getOutput().writeObject(response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for(User us : room.getSpectator()) if(us.getIsActive()){
				try {
					response.setUserName(us.getName());
					us.getOutput().writeObject(response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			// select user that can move next
			noTurn = room.nextTurn();
			User nextUser = room.getUser().get(noTurn);
			response = new Packet(Packet.PUT_PAWN, nextUser.getName());
			response.setRoom(noRoom);
			try {
				nextUser.getOutput().writeObject(response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	void sendChat(int noRoom, String userName, String message) {
		// Check if user is valid
		if(!mapUser.containsKey(userName))
			return;
		User user = mapUser.get(userName);
		// Check if room is valid
		if(!mapRoom.containsKey(noRoom)) {
			Packet packet = new Packet(Packet.SEND_CHAT, userName);
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
		Packet response = new Packet(Packet.SEND_CHAT, userName);
		response.setRoom(noRoom);
		response.setMessage(message);
		for(User us : room.getUser()) if(us.getIsActive()){
			try {
				response.setUserName(us.getName());
				us.getOutput().writeObject(response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(User us : room.getSpectator()) if(us.getIsActive()){
			try {
				response.setUserName(us.getName());
				us.getOutput().writeObject(response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void disconnectUser(String userName) {
		// Check if user is valid
		if(!mapUser.containsKey(userName))
			return;
		User user = mapUser.get(userName);
		int noRoom = user.getRoom();
		// Check if room is valid
		if(mapRoom.containsKey(noRoom)) {
			Room room = mapRoom.get(noRoom);
			if(user.getNoTurn() == -1) {
				ArrayList<User> spect = room.getSpectator();
				int id = spect.indexOf(user);
				if(id >= 0) {
					spect.remove(id);
					Packet response = new Packet(Packet.USER_DISCONNECT, "userName");
					response.setRoom(noRoom);
					for(User us : room.getUser()) if(us.getIsActive()){
						try {
							response.setUserName(userName);
							us.getOutput().writeObject(response);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					response.setRoom(noRoom);
					for(User us : room.getSpectator()) if(us.getIsActive()){
						try {
							response.setUserName(userName);
							us.getOutput().writeObject(response);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			else {
				room.removeUser(user.getNoTurn());
				Packet response = new Packet(Packet.USER_DISCONNECT, "userName");
				response.setRoom(noRoom);
				response.setTurn(user.getNoTurn());
				for(User us : room.getUser()) if(us.getIsActive()) {
					try {
						response.setUserName(userName);
						us.getOutput().writeObject(response);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				response.setRoom(noRoom);
				for(User us : room.getSpectator()) if(us.getIsActive()) {
					try {
						response.setUserName(userName);
						us.getOutput().writeObject(response);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				int before = room.getTurn()-1;
				room.setTurn(before-1);
				if(room.nextTurn() != before) {
					User nextTurn = room.getUser().get(room.getTurn());
					Packet pack = new Packet(Packet.PUT_PAWN, nextTurn.getName());
					pack.setRoom(room.getNoRoom());
					try {
						nextTurn.getOutput().writeObject(pack);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		mapUser.remove(userName);
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
			server.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}