package Gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelWithBackground extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image Image;
    public JPanelWithBackground() {
    }

    public JPanelWithBackground(String nombreImage) {
        if (nombreImage != null) {
            Image = new ImageIcon(
                           getClass().getResource(nombreImage)).getImage();
        }
    }

    public JPanelWithBackground(Image ImageInitial) {
        if (ImageInitial != null) {
            Image = ImageInitial;
        }
    }

    public void setImage(String nombreImage) {
        if (nombreImage != null) {
            Image = new ImageIcon(
                   getClass().getResource(nombreImage)
                   ).getImage();
        } else {
            Image = null;
        }

        repaint();
    }

    public void setImage(Image newImage) {
        Image = newImage;

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (Image != null) {
            g.drawImage(Image, 0, 0, getWidth(), getHeight(),
                              this);

            setOpaque(false);
        } else {
            setOpaque(true);
        }

        super.paint(g);
    }
}