package Game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Gui.ImageUtils;
import Gui.JPanelWithBackground;
import Gui.Window;

/**
 * Clase que se encarga de controlar el fin de juego.
 * @author fmenzell
 *
 */

public class StatusObserver implements Observer{

	Window window;

	public StatusObserver(GameStatus statusObserved, Window window){
		this.window = window;
	}

	/**
	 * crea una ventana con una imagen que informa al jugador cuando este gano o perdio
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		JFrame frame = new JFrame("Game Over");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		JPanelWithBackground panel;
		try {
			if(arg0.equals(new Status(GameStatus.LOOSE))){
				panel = new JPanelWithBackground(ImageUtils.loadImage("./resources/lost.png"));
				
			}
			else{
				panel = new JPanelWithBackground(ImageUtils.loadImage("./resources/won.png"));
				
			}
			panel.setPreferredSize(new Dimension(800,600));
			frame.getContentPane().add(panel, BorderLayout.CENTER);	
			frame.setVisible(true);
			frame.pack();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(window.getContentPane(), "There's has been an error while loading the images");
		} finally{
			window.reset();
			window.removeKeyListener(window.getMoveAdapter());			
		}}

}