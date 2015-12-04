package Client;


import Server.Packet;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aodyra
 */
public class ChangingButton extends java.awt.Button {

    private final int[][] fModel;
    private final int fX;
    private final int fY;
    private Socket sock;
    private ObjectOutputStream oos;
    private boolean status;
    
    
    public ChangingButton(final int x, final int y, final int[][] model,String hostname, int port, int urutan, int noroom, String username) throws IOException {
        sock = new Socket(hostname, port);
        oos = new ObjectOutputStream(sock.getOutputStream());
        status = false;
        fX= x;
        fY= y;
        fModel= model;
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!isStatus()){
                    return;
                }
                if(fModel[fX][fY] == 0) {
                    fModel[fX][fY] = urutan;
                    putPawn(noroom, username, x, y);
                    updateNameFromModel();
                }
            }
        });
        updateNameFromModel();
    }
    
    private char simbol(int k){
        switch(k){
            case 1: return 'X';
            case 2: return 'O';
            case 3: return (char) 0x25A0;
            case 4: return (char) 0x03A6;
            case 5: return (char) 0x03C9;
        }
        return (char)0;
    }
    public void updateNameFromModel() {
        setLabel(String.valueOf(simbol(fModel[fX][fY])));
        if(fModel[fX][fY] != 0) {
            setEnabled(false);
            setBackground(Color.ORANGE);
        }
    }
    
    void putPawn(int noRoom, String name, int x, int y) {
        Packet packet = new Packet(Packet.PUT_PAWN, name);
        packet.setRoom(noRoom);
        packet.setPosX(x);
        packet.setPosY(y);
        try {
            oos.writeObject(packet);
        } catch (IOException ex) {
            Logger.getLogger(Gomoku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

}
