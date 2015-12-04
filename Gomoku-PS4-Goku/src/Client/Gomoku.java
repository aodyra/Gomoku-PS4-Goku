package Client;


import Server.Packet;
import java.awt.Button;
import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aodyra
 */
public class Gomoku extends javax.swing.JFrame {

    private HashMap<Integer,ArrayList<ChangingButton>> buttons;
    private int dim=20;
    private int matrix[][] = new int[dim][dim];
    private Socket sock;
    private ObjectOutputStream oos;
    private String username;
    private int noroom;
    private int urutanuser;
    /**
     * Creates new form Gomoku
     */
    public Gomoku(Socket sock) throws IOException {
        this.sock = sock;
        noroom = -1;
        urutanuser = -1;
        oos = new ObjectOutputStream(this.sock.getOutputStream());
        initComponents();
        buttons = new HashMap<Integer,ArrayList<ChangingButton>>();
        for(int i = 0; i < dim; i++){
            ArrayList<ChangingButton> temp = new ArrayList<ChangingButton>();
            buttons.put(new Integer(i),temp);
        }
        begin();
        for(int i = 0; i < dim; i++)
        	for(int j = 0; j<dim; j++)
        		matrix[i][j] = -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        room = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lobby = new javax.swing.JPanel();
        button1 = new java.awt.Button();
        button2 = new java.awt.Button();
        button3 = new java.awt.Button();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        waitingroom = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane5 = new javax.swing.JTextPane();
        button4 = new java.awt.Button();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextPane6 = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        home = new javax.swing.JPanel();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        button5 = new java.awt.Button();
        textField1 = new java.awt.TextField();
        jLabel2 = new javax.swing.JLabel();
        popupwinner = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        button6 = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jTextPane1);

        jTabbedPane1.addTab("Player", jScrollPane1);

        jScrollPane2.setViewportView(jTextPane2);

        jTabbedPane1.addTab("Spectator", jScrollPane2);

        jScrollPane3.setViewportView(jTextPane3);

        jLabel1.setText("Chats:");

        jTextField1.setToolTipText("Chat disini...");
        jTextField1.setSelectionColor(new java.awt.Color(204, 153, 0));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));
        jPanel1.setLayout(new java.awt.GridLayout(20, 20, 3, 3));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("Player Name : ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("Room : ");

        javax.swing.GroupLayout roomLayout = new javax.swing.GroupLayout(room);
        room.setLayout(roomLayout);
        roomLayout.setHorizontalGroup(
            roomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roomLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(roomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roomLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(roomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(roomLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(169, 169, 169)
                        .addComponent(jLabel7)
                        .addGap(161, 161, 161)
                        .addComponent(jLabel10))))
        );
        roomLayout.setVerticalGroup(
            roomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roomLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(roomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roomLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roomLayout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(roomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)))
        );

        button1.setLabel("Create Room");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setLabel("Watch");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button3.setLabel("Join");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(0, 1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox1);

        jScrollPane6.setViewportView(jPanel2);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("Player Name : ");

        javax.swing.GroupLayout lobbyLayout = new javax.swing.GroupLayout(lobby);
        lobby.setLayout(lobbyLayout);
        lobbyLayout.setHorizontalGroup(
            lobbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lobbyLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(lobbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addGroup(lobbyLayout.createSequentialGroup()
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        lobbyLayout.setVerticalGroup(
            lobbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lobbyLayout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(185, 185, 185)
                .addGroup(lobbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        label1.setFont(new java.awt.Font("Dialog", 0, 48)); // NOI18N
        label1.setText("Waiting Room");

        jScrollPane5.setViewportView(jTextPane5);

        button4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        button4.setLabel("START");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        jScrollPane7.setViewportView(jTextPane6);

        jLabel3.setText("Watch:");

        jLabel4.setText("Player:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("Room : ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("Player Name : ");

        javax.swing.GroupLayout waitingroomLayout = new javax.swing.GroupLayout(waitingroom);
        waitingroom.setLayout(waitingroomLayout);
        waitingroomLayout.setHorizontalGroup(
            waitingroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(waitingroomLayout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(waitingroomLayout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addComponent(jLabel6))
            .addGroup(waitingroomLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(waitingroomLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(waitingroomLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel9))
            .addGroup(waitingroomLayout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        waitingroomLayout.setVerticalGroup(
            waitingroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(waitingroomLayout.createSequentialGroup()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addGap(16, 16, 16)
                .addGroup(waitingroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(waitingroomLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(waitingroomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(waitingroomLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jLabel9)
                .addGap(10, 10, 10)
                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        label2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        label2.setForeground(new java.awt.Color(255, 0, 0));
        label2.setText("GOMOKU");

        label3.setFont(new java.awt.Font("Hobo Std", 2, 48)); // NOI18N
        label3.setText("Multiplayer");

        button5.setLabel("Play");
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        textField1.setText("Insert Your Name");
        textField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField1ActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
        home.setLayout(homeLayout);
        homeLayout.setHorizontalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(homeLayout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(homeLayout.createSequentialGroup()
                .addGap(247, 247, 247)
                .addComponent(jLabel2))
            .addGroup(homeLayout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(homeLayout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        homeLayout.setVerticalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("Selamat");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("Pemenangnya Aodyra");

        button6.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        button6.setLabel("OK");
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout popupwinnerLayout = new javax.swing.GroupLayout(popupwinner);
        popupwinner.setLayout(popupwinnerLayout);
        popupwinnerLayout.setHorizontalGroup(
            popupwinnerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(popupwinnerLayout.createSequentialGroup()
                .addGroup(popupwinnerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(popupwinnerLayout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(jLabel11))
                    .addGroup(popupwinnerLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel12))
                    .addGroup(popupwinnerLayout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        popupwinnerLayout.setVerticalGroup(
            popupwinnerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(popupwinnerLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(51, 51, 51)
                .addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(room, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(lobby, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(waitingroom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(25, 25, 25)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addComponent(home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(20, 20, 20)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(popupwinner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(25, 25, 25)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(room, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lobby, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(87, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(waitingroom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(14, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(78, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(popupwinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(75, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        String kirim = username+" : "+jTextField1.getText();
        jTextField1.setText("");
        sendChat(noroom, username, kirim);
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        // Create Room
        createRoom(username);
    }//GEN-LAST:event_button1ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
    	noroom = new Integer(jComboBox1.getItemAt(jComboBox1.getSelectedIndex()));
        // Join Room
        joinRoom(noroom, username);
    }//GEN-LAST:event_button3ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        button3.setEnabled(false);
        button4.setEnabled(false);
        noroom = new  Integer(jComboBox1.getItemAt(jComboBox1.getSelectedIndex()));
        watchRoom(noroom, username);
    }//GEN-LAST:event_button2ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        // TODO add your handling code here:
        // Start Game
        startGame(noroom, username);
    }//GEN-LAST:event_button4ActionPerformed

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
        // TODO add your handling code here:
        // Text Field Start Game
        button5ActionPerformed(evt);
    }//GEN-LAST:event_textField1ActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        // TODO add your handling code here:
        // Button Start Game
        username = textField1.getText();
        submitName(textField1.getText());
    }//GEN-LAST:event_button5ActionPerformed

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        // TODO add your handling code here:
        toLobby();
    }//GEN-LAST:event_button6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gomoku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gomoku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gomoku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gomoku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {to
////                    new Gomoku(sock).setVisible(true);
//                } catch (IOException ex) {
//                    Logger.getLogger(Gomoku.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private java.awt.Button button2;
    private java.awt.Button button3;
    private java.awt.Button button4;
    private java.awt.Button button5;
    private java.awt.Button button6;
    private javax.swing.JPanel home;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPane3;
    private javax.swing.JTextPane jTextPane5;
    private javax.swing.JTextPane jTextPane6;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private javax.swing.JPanel lobby;
    private javax.swing.JPanel popupwinner;
    private javax.swing.JPanel room;
    private java.awt.TextField textField1;
    private javax.swing.JPanel waitingroom;
    // End of variables declaration//GEN-END:variables
   
    public void updateChat(String newChat){
            jTextPane3.setText(jTextPane3.getText()+newChat+"\n");
    }
    
    public void updateMatrix(int urutan, int x, int y){
        matrix[x][y] = urutan;
        buttons.get(x).get(y).updateNameFromModel();
    }
    
    public void createGridMatrix() throws IOException{
    	Status sbool = new Status(false);
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                ChangingButton button = new ChangingButton(i, j, matrix, oos, getUrutanuser(), noroom, username, sbool);
                buttons.get(i).add(button);
                jPanel1.add(button);
            }
        }
    }
    
    private void begin(){
        setVisible(true);
        popupwinner.setVisible(false);
        room.setVisible(false);
        lobby.setVisible(false);
        waitingroom.setVisible(false);
    }
    
    private void printMatrix(){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
    public void updateListRoom(ArrayList<String> s){
        jComboBox1.removeAllItems();
        for(int i = 0; i < s.size(); i++){
            jComboBox1.addItem(s.get(i));
        }
    }
    
    public void updateUserWaitingPlayer(ArrayList<String> s){
        jTextPane5.setText("");
        for(int i = 0; i < s.size(); i++){
            jTextPane5.setText(jTextPane5.getText()+s.get(i)+"\n");
        }
    }
    
    public void updateUserWaitingWatch(ArrayList<String> s){
        jTextPane6.setText("");
        for(int i = 0; i < s.size(); i++){
            jTextPane6.setText(jTextPane6.getText()+s.get(i)+"\n");
        }
    }
    
    public void updateUserRoomPlayer(ArrayList<String> s){
        jTextPane1.setText("");
        for(int i = 0; i < s.size(); i++){
            jTextPane1.setText(jTextPane1.getText()+s.get(i)+"\n");
        }
    }
    
    public void updateUserRoomWatch(ArrayList<String> s){
        jTextPane2.setText("");
        for(int i = 0; i < s.size(); i++){
            jTextPane2.setText(jTextPane2.getText()+s.get(i)+"\n");
        }
    }
    public void toLobby(){
    	System.out.println("Gomoku.toLobby");
        lobby.setVisible(true);
        popupwinner.setVisible(false);
        home.setVisible(false);
        waitingroom.setVisible(false);
        room.setVisible(false);
    }
    
    public void toWaitingRoom(){
        lobby.setVisible(false);
        popupwinner.setVisible(false);
        home.setVisible(false);
        waitingroom.setVisible(true);
        room.setVisible(false);
    }
    
    public void toRoom() throws IOException{
        lobby.setVisible(false);
        popupwinner.setVisible(false);
        home.setVisible(false);
        waitingroom.setVisible(false);
        this.createGridMatrix();
        room.setVisible(true);
    }
    
    public void toHome(){
        lobby.setVisible(false);
        popupwinner.setVisible(false);
        home.setVisible(true);
        waitingroom.setVisible(false);
        room.setVisible(false);
    }
    
    void submitName(String name) {
        Packet packet = new Packet(Packet.REGISTER, name);
        try {
            oos.writeObject(packet);
        } catch (IOException ex) {
            Logger.getLogger(Gomoku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void createRoom(String name) {
        Packet packet = new Packet(Packet.CREATE_ROOM, name);
        packet.setRoom(-1);
        try {
            oos.writeObject(packet);
        } catch (IOException ex) {
            Logger.getLogger(Gomoku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void joinRoom(int noRoom, String name) {
        Packet packet = new Packet(Packet.JOIN_ROOM, name);
        packet.setRoom(noRoom);
        System.err.println(packet);
        try {
            oos.writeObject(packet);
        } catch (IOException ex) {
            Logger.getLogger(Gomoku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void watchRoom(int noRoom, String name) {
        Packet packet = new Packet(Packet.WATCH_ROOM, name);
        packet.setRoom(noRoom);
        try {
            oos.writeObject(packet);
        } catch (IOException ex) {
            Logger.getLogger(Gomoku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void startGame(int noRoom, String name) {
        Packet packet = new Packet(Packet.START_GAME, name);
        packet.setRoom(noRoom);
        try {
            oos.writeObject(packet);
        } catch (IOException ex) {
            Logger.getLogger(Gomoku.class.getName()).log(Level.SEVERE, null, ex);
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
    void sendChat(int noRoom, String name, String message) {
        Packet packet = new Packet(Packet.SEND_CHAT, name);
        packet.setRoom(noRoom);
        packet.setMessage(message);
        try {
            oos.writeObject(packet);
        } catch (IOException ex) {
            Logger.getLogger(Gomoku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void alertNickNameAlready(){
        jLabel2.setText("NickName Already");
    }
    /**
     * @return the urutanuser
     */
    public int getUrutanuser() {
        return urutanuser;
    }

    /**
     * @param urutanuser the urutanuser to set
     */
    public void setUrutanuser(int urutanuser) {
        this.urutanuser = urutanuser;
    }
    
    public void changeStatusplayer(boolean status){
        for(int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++){
                buttons.get(i).get(j).setStatus(status);
            }
        }
    }

	/**
	 * @return the noroom
	 */
	public int getNoroom() {
		return noroom;
	}

	/**
	 * @param noroom the noroom to set
	 */
	public void setNoroom(int noroom) {
		this.noroom = noroom;
	}
        
        public void setLabelRoom(){
            jLabel6.setText("Room : "+getNoroom());
            jLabel10.setText("Room : "+getNoroom());
        }
        
        public void setLabelName(){
            jLabel7.setText("Player Name : "+username);
            jLabel8.setText("Player Name : "+username);
            jLabel9.setText("Player Name : "+username);
        }
        
        public void outTurn(){
            jLabel5.setText("Your Turn");
        }
        
        public void anotherTurn(){
            jLabel5.setText("another turn");
        }
        
        public void winner(String nameuser){
            String name;
            if(nameuser.equals(username)) name = "Anda";
            else name = nameuser;
            jLabel12.setText("Pemenangnya "+name);
            popupwinner.setVisible(true);
        }
}
