package Tiles;

import java.io.Serializable;

import Desktop.Desktop;



/**
 * Clase abstracta de la cual heredan todas las celdas
 * @author Fede
 *
 */
public abstract class Tile implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		protected Desktop desk;
	
		public Tile(Desktop d){
			desk=d;
		}
		
	/**
	 * Metodo abstracto que se invoca cuando el jugador intenta caminar sobre la celda, realizando las acciones necesarias.
	 * @return true si el jugador puede caminar sobre la celda
	 */
	public abstract boolean onWalk();
	
	
}