package Character;

import java.io.Serializable;

/**
 * enumerativo que representa los movimientos del jugador 
 * @author Conco
 *
 */
public enum Movements implements Serializable{
UP(-1,0), DOWN(1,0), LEFT(0,-1), RIGHT(0,1);
	
	private int dx;
	private int dy;
	
	private Movements(int x, int y){
		dx=x;
		dy=y;
	}
	/**
	 * devuelve su coordenada X
	 */
	public int getX(){
		return dx;
	}
	
	/**
	 * devuelve su coordenada Y
	 */
	public int getY(){
		return dy;
	}
}
	