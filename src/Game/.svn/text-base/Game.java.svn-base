package Game;

import java.io.Serializable;
import Desktop.Desktop;

/**
 * Clase en la cual se almacenan todos los datos de la partida.
 * @author fmenzell
 * 
 * Almacena un desktop, el cual funciona como tablero y un status, que hace referencia al estado del juego.
 */

public class Game implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Desktop desktop;
	private transient Status status;

	/**
	 * Constructor basico (cuando se inica un nuevo juego)
	 * @param desk Desktop con el cual se comenzara una nueva partida.
	 * @param playerName Nombre del jugador.
	 */
		
	public Game(Desktop desk, String playerName){
		status=new Status(GameStatus.PLAYING);
		desktop = desk;
		desktop.setPlayerName(playerName);
		desktop.setGame(this);
		
	}
			
		
	/**
	 * metodo que se llama cuando termina el juego
	 * @param gs Estado nuevo.
	 */
	
	public void gameOver(GameStatus gs){
		status.setStatus(gs);
	}
	
	public Desktop getDesk(){
		return desktop;
	}
	
	public void addObserver(StatusObserver statusO){
		status.addObserver(statusO);
	}
	
	public void setStatus(GameStatus status){
		this.status = new Status(status);
	}
	
}
