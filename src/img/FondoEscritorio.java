/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package img;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
/**
 *
 * @author erick
 */
public class FondoEscritorio extends JDesktopPane {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageIcon imagen = new ImageIcon(
                getClass().getResource("/img/bg_luz.png")
        );

        g.drawImage(
                imagen.getImage(),
                0,
                0,
                getWidth(),
                getHeight(),
                this
        );
    }
    
}
