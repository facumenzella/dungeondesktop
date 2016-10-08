package Tiles;


import java.awt.Point;
import java.io.Serializable;

import Tiles.BonusTile;


import Desktop.Desktop;

/**
 * Celda que contiene un bonus de vida
 */
public class HealthBonusTile extends BonusTile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param val Valor del bonus de vida
	 */
	public HealthBonusTile(int val,Point p, Desktop d){
		super(val,p, d);
	}
	
	/**
	 * Añade el bonus al jugador y luego reemplaza la celda por una vacia
	 * @return true Deja al jugador moverse luego de agarrar el bonus
	 */
	@Override
	public boolean onWalk()
	{
	  desk.getHero().setHealth(this.value);
	  this.replace();
	  return true;
	}
}
