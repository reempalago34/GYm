/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author erick
 */
public class PanelMembresias extends JPanel{
    public PanelMembresias() {
        setBackground(java.awt.Color.RED);

        setLayout(null);

        JLabel titulo = new JLabel("MEMBRESÍAS");
        titulo.setBounds(20, 20, 200, 30);

        add(titulo);
    }
    
}
