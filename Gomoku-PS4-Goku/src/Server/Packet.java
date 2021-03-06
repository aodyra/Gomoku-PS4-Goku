package Server;

import java.io.Serializable;
import java.util.ArrayList;

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
	public static final int FINISH = 8;
	private int _type;
	private int _room;
	private String _userName;
	private int _posX, _posY;
	private String _message;
	private int _turn;
	private ArrayList< String > _arrayString;
	private ArrayList< String > _arrayString2;

	public Packet(int type, String userName) {
		this._type = type;
		this._room = -1;
		this._userName = userName;
		this._posX = this._posY = -1;
		this._turn = -1;
		this._message = "";
		this._arrayString = new ArrayList<String>();
		this._arrayString2 = new ArrayList<String>(); 
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
	public int getTurn() {
		return _turn;
	}
	public void setTurn(int _turn) {
		this._turn = _turn;
	}
	public ArrayList<String > getArrayString() {
		return this._arrayString;
	}
	public void setArrayString(ArrayList< String > arrayString) {
		this._arrayString = arrayString;
	}
	public ArrayList<String > getArrayString2() {
		return this._arrayString2;
	}
	public void setArrayString2(ArrayList< String > arrayString2) {
		this._arrayString2 = arrayString2;
	}
	@Override
	public String toString() {
		String ret =
				"Type : " + _type +
				"\n Name : " + _userName +
				"\n Room : " + _room + 
				"\n pos : (" + _posX + ", " + _posY + ")" +
				"\n turn : " + _turn +
				"\n message : " + _message + 
				"\n ArString1 : " + _arrayString + 
				"\n ArString2 : " + _arrayString2 + "\n\n";
		return ret;
	}
}