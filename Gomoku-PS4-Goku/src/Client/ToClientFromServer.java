/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Server.Packet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aodyra
 */
public class ToClientFromServer implements Runnable{
    BlockingQueue<Packet> bque;
    Socket sock;

    ToClientFromServer(Socket _sock, BlockingQueue<Packet> _bque){
        this.sock = _sock;
        this.bque = _bque;
    }
    @Override
    public void run() {
        Packet p = null;
        try {
            ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
            while((p = (Packet) in.readObject()) != null){
            	System.out.println(p+"\n");
                bque.put(p);
            }
        } catch (IOException ex) {
            Logger.getLogger(ToClientFromServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ToClientFromServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ToClientFromServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
