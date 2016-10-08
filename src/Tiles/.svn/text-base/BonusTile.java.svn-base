package Tiles;

import java.awt.Point;
import java.io.Serializable;



import Desktop.Desktop;

/**
 * Clase abstracta de celdas por las cuales se puede caminar
 * @author Fede
 *
 */
public abstract class BonusTile extends Tile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Point pos;
	protected int value;
	
	/**
	 * Construye una celda de bonus a partir de su valor
	 * @param value
	 */
	public BonusTile(int value, Point p, Desktop d){
		super(d);
		this.pos=p;
		this.value=value;
	}
	
	/**
	 * Reemplaza la celda actual por una celda vacia
	 */
	protected void replace()
	{
		desk.replaceTile(new EmptyTile(desk),this.pos);
	}
	
	public int getValue(){
		return value;
	}
}