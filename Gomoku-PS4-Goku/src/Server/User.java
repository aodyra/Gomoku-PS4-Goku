/**
 * 
 */
package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

/**
 * @author Wiwit
 *
 */
public class User {
	private int _room;
	private Socket _sock;
	private String _name;
	private UserReceiver _receiver;
	private Thread thread;
	private boolean _isActive;
	private int _noTurn; 
	public User(String _name, Socket sock, BlockingQueue bque) throws IOException {
		this._sock = sock;
		this._room = -1;
		this._name = _name;
		this._isActive = true;
		this._receiver = new UserReceiver(bque, new ObjectInputStream(sock.getInputStream()));
		thread = new Thread(this._receiver);
		thread.start();
	}
	public int getRoom() {
		return _room;
	}
	public void setRoom(int room) {
		this._room = room;
	}
	/**
	 * @return the _sock
	 */
	public Socket getSock() {
		return _sock;
	}
	/**
	 * @param _sock the _sock to set
	 */
	public void setSock(Socket _sock) {
		this._sock = _sock;
	}
	/**
	 * @return the _name
	 */
	public String getName() {
		return _name;
	}
	/**
	 * @param _name the _name to set
	 */
	public void setName(String _name) {
		this._name = _name;
	}
	/**
	 * @return the _receiver
	 */
	public UserReceiver getReceiver() {
		return _receiver;
	}
	/**
	 * @param _receiver the _receiver to set
	 */
	public void setReceiver(UserReceiver _receiver) {
		this._receiver = _receiver;
	}
	public boolean getIsActive() {
		return _isActive;
	}
	public void setIsActive(boolean isActive) {
		this._isActive = isActive;
	}
	public int getNoTurn() {
		return this._noTurn;
	}
	public void setNoTurn(int turn) {
		this._noTurn = turn;
	}
}
