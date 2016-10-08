package Tiles;



import java.awt.Point;
import java.io.Serializable;

import Desktop.Desktop;


/**
 * Celda que contiene un bonus de fuerza
 * @author Fede
 *
 */

public class StrBonusTile extends BonusTile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param value Valor del bonus de fuerza
	 */
	public StrBonusTile(int value, Point p, Desktop d) {
		super(value,p, d);
	}

	/**
	 * Agrega el bonus al jugador y luego reemplaza la celda por una vacia
	 */
	@Override
	public boolean onWalk()
	{
	  desk.getHero().addStrBonus(this.value);
	  this.replace();
	  return true;
	}
}
