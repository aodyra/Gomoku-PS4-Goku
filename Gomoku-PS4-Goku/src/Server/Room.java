/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Wiwit
 */
public class Room implements Serializable {
	private static int[] dx = {0, 1, 1, 1}, dy = {-1, -1, 0, 1};
	private int _noRoom;
    private User _createdBy;
    private int[][] _board;
    private ArrayList< User > _user;
    private int _turn;
    private ArrayList< User > _spectator;
    private ArrayList< String > _chat;
    private boolean _started; 
    /**
     * Constructor
     */
    public Room(int noRoom, User createdBy) {
    	this._noRoom = noRoom;
    	this._createdBy = createdBy;
    	_board = new int[21][21];
    	for(int i = 0; i<21; i++)
    		for(int j = 0; j<21; j++)
    			_board[i][j] = -1;
    	_user = new ArrayList<User>();
    	_turn = 0;
    	_spectator = new ArrayList<User>();
    	_chat = new ArrayList<String>();
    	addUser(createdBy);
    	_started = false;
    }
	/**
	 * @return the _createdBy
	 */
	public User getCreatedBy() {
		return _createdBy;
	}
	/**
	 * @param _createdBy the _createdBy to set
	 */
	public void setCreatedBy(User _createdBy) {
		this._createdBy = _createdBy;
	}
	/**
	 * @return the _baord
	 */
	public int[][] getBoard() {
		return _board;
	}
	/**
	 * @param _baord the _baord to set
	 */
	public void setBoard(int[][] _baord) {
		this._board = _baord;
	}
	/**
	 * @return the _user
	 */
	public ArrayList<User> getUser() {
		return _user;
	}
	/**
	 * @param _user the _user to set
	 */
	public void setUser(ArrayList<User> _user) {
		this._user = _user;
	}
	/**
	 * @return the _turn
	 */
	public int getTurn() {
		return _turn;
	}
	/**
	 * @param _turn the _turn to set
	 */
	public void setTurn(int _turn) {
		this._turn = _turn;
	}
	public int nextTurn() {
		this._turn = (this._turn + 1) % _user.size();
		while(!_user.get(this._turn).getIsActive()) 
			this._turn = (this._turn + 1) % _user.size(); 
		return this._turn;
	}
	/**
	 * @return the _spectator
	 */
	public ArrayList<User> getSpectator() {
		return _spectator;
	}
	/**
	 * @param _spectator the _spectator to set
	 */
	public void setSpectator(ArrayList<User> _spectator) {
		this._spectator = _spectator;
	}
	/**
	 * @return the _chat
	 */
	public ArrayList<String> getChat() {
		return _chat;
	}
	/**
	 * @param _chat the _chat to set
	 */
	public void setChat(ArrayList<String> _chat) {
		this._chat = _chat;
	}

	public int getNoRoom() {
		return _noRoom;
	}
	public void setNoRoom(int noRoom) {
		this._noRoom = noRoom;
	}
	public void addUser(User newUser) {
		newUser.setRoom(this._noRoom);
		newUser.setNoTurn(_user.size());
		_user.add(newUser);
	}
	public void removeUser(int id) {
		this._user.get(id).setIsActive(false);
	}
	public void addSpectator(User spect) {
		spect.setRoom(this._noRoom);
		spect.setNoTurn(-1);
		_spectator.add(spect);
	}
	public boolean putPawn(int noTurn, int x, int y) {
		if(this._turn != noTurn)
			return false;
		if(x <20 && x >= 0 && y < 20 && y >= 0 && this._board[x][y] == -1) {
			for(int i = 0; i<4; i++) {
				int x1, y1, x2, y2;
				x2 = x1 = x; y2 = y1 = y;
				while(x1 >= 0 && x1 < 20 && y1 >= 0 && y1 < 20 && this._board[x1][y1] == noTurn) {
					x1 += dx[i];
					y1 += dy[i];
				}
				while(x2 >= 0 && x2 < 20 && y2 >= 0 && y2 < 20 && this._board[x2][y2] == noTurn) {
					x2 += dx[i];
					y2 += dy[i];
				}
				int dist = Math.max(Math.abs(x1-x2), Math.abs(y1-y2));
				if(dist > 5)
					return true;
			}
		}
		return false;
	}
	public boolean getStarted() {
		return this._started;
	}
	public void setStarted(boolean start) {
		this._started = start;
	}
}