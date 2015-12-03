package Server;

import java.io.Serializable;

/**
 * 
 */

/**
 * @author Wiwit
 *
 */
public class Packet implements Serializable {
	public static final int REGISTER = 0;
	public static final int CREATE_ROOM = 1;
	public static final int JOIN_ROOM = 2;
	public static final int WATCH_ROOM = 3;
	public static final int START_GAME = 4;
	public static final int PUT_PAWN = 5;
	public static final int SEND_CHAT = 6;
	public static final int USER_DISCONNECT = 7;
	private int _type;
	private int _room;
	private String _userName;
	private int _posX, _posY;
	private String _message;

	public Packet(int type, String userName) {
		this._type = type;
		this._room = -1;
		this._userName = userName;
		this._posX = this._posY = -1;
		this._message = "";

	}
	/**
	 * @return the _type
	 */
	public int getType() {
		return _type;
	}

	/**
	 * @param _type the _type to set
	 */
	public void setType(int _type) {
		this._type = _type;
	}

	/**
	 * @return the _room
	 */
	public int getRoom() {
		return _room;
	}

	/**
	 * @param _room the _room to set
	 */
	public void setRoom(int _room) {
		this._room = _room;
	}

	/**
	 * @return the _user_id
	 */
	public String getUserName() {
		return _userName;
	}

	/**
	 * @param _user_id the _user_id to set
	 */
	public void setUserName(String userName) {
		this._userName = userName;
	}

	/**
	 * @return the _pos_x
	 */
	public int getPosX() {
		return _posX;
	}

	/**
	 * @param _pos_x the _pos_x to set
	 */
	public void setPosX(int _pos_x) {
		this._posX = _pos_x;
	}

	/**
	 * @return the _pos_y
	 */
	public int getPosY() {
		return _posY;
	}

	/**
	 * @param _pos_y the _pos_y to set
	 */
	public void setPosY(int _pos_y) {
		this._posY = _pos_y;
	}

	/**
	 * @return the _message
	 */
	public String getMessage() {
		return _message;
	}

	/**
	 * @param _message the _message to set
	 */
	public void setMessage(String _message) {
		this._message = _message;
	}
}
