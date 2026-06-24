/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 *
 * @author erick
 */
public class FondoPanel extends JPanel {
    
    private ImageIcon imagen;
    @Override
    protected void paintComponent(Graphics g) {
        imagen = new ImageIcon(getClass().getResource("/img/bg_weights.png"));

        g.drawImage(imagen.getImage(), 0, 0,
                getWidth(), getHeight(), this);

        setOpaque(false);
        super.paintComponent(g);
    }
}
