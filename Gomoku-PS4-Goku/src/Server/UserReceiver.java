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
	BlockingQueue<Packet> bque;
	ObjectInputStream ois;
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	UserReceiver(BlockingQueue bque, ObjectInputStream out) throws IOException {
		this.bque = bque;
		this.ois = out;
	}
	@Override
	public void run() {
		Packet p = null;
		try {
			while((p = (Packet) ois.readObject()) != null) {
				bque.put(p);
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
