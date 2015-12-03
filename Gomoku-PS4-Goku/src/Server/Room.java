/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Wiwit
 */
public class Room {
	private int _noRoom;
    private User _createdBy;
    private int[][] _board;
    private ArrayList< User > _user;
    private int _turn;
    private ArrayList< User > _spectator;
    private ArrayList< String > _chat;
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
	public boolean putPawn(String UserName, int x, int y) {
//		int id = 
//		if(x <20 && x >= 0 && y < 20 && y >= 0 && this._board[x][y] == -1) {
//			this._board[x][y] = 
//			
//		}
		return false;
	}
}
