/**
 * 
 */
package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

/**
 * @author iafir
 *
 */
public class UserReceiver implements Runnable {
	String userName;
	BlockingQueue<Packet> bque;
	ObjectInputStream ois;
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	UserReceiver(String userName, BlockingQueue bque, ObjectInputStream out) throws IOException {
		this.userName = userName;
		this.bque = bque;
		this.ois = out;
	}
	@Override
	public void run() {
		Packet p = null;
		try {
			while((p = (Packet) ois.readObject()) != null) {
				p.setUserName(userName);
				bque.put(p);
			}
		} catch (ClassNotFoundException | IOException | InterruptedException e) {
			Packet report = new Packet(Packet.USER_DISCONNECT, userName);
			try {
				bque.put(report);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			e.printStackTrace();
		}
	}
}
