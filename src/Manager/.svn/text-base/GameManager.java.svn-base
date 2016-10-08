package Manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Desktop.Desktop;
import Game.Game;
import Game.GameStatus;

/**
 * Clase que se encarga del guardado y salvado de partidas
 * @author cmaderbl
 *
 */
public class GameManager {
	
	public Game newGame(Desktop d, String name){
			Game game=new Game(d, name);
			return game;
		}
	/**
	 * carga una partida
	 * @param name el archivo de donde cargar
	 * @return el juego cargado
	 * @throws IOException si no se puede cargar
	 * @throws ClassNotFoundException
	 */
	public Game loadGame(File name) throws IOException, ClassNotFoundException {
		ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream(name));
		Desktop desktop = (Desktop) fileIn.readObject();
		Game game = new Game(desktop, desktop.getPlayerName());
		game.setStatus(GameStatus.PLAYING);
		desktop.setGame(game);
		return game;
	}
	/**
	 * Guarda una partida
	 * @param game juego a guarar
	 * @param name nombre del archivo con que se guarda
	 * @throws IOException si no se puede guardar
	 */
	public void saveGame(Game game, String name) throws IOException{
		ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream("./Games/" + name));
		fileOut.writeObject(game.getDesk());
		fileOut.close();
	}
	
}
