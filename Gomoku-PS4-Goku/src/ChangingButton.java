
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public ChangingButton(final int x, final int y, final int[][] model) {
        fX= x;
        fY= y;
        fModel= model;
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(fModel[fX][fY] == 0) {
                    fModel[fX][fY] = 1;
                    updateNameFromModel();
                }
            }
        });
        updateNameFromModel();
    }

    private void updateNameFromModel() {
        setLabel(String.valueOf(fModel[fX][fY]));
    }

}
