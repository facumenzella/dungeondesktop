package Tiles;


import java.io.Serializable;

import Character.Enemy;
import Character.Hero;
import Desktop.Desktop;

/**
 * Celda con un enemigo
 * @author Fede
 *
 */
public class EnemyTile extends Tile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Enemy creature;

	/**
	 * Crea una celda nueva
	 * @param e enemigo que debe contener la celda
	 */
	public EnemyTile(Enemy e, Desktop d){
		super(d);
		creature = e;
	}
	
	/**
	 * Ataca al jugador y se deja atacar
	 * @return false No se puede caminar sobre esta celda
	 */
	@Override
	public boolean onWalk()
	{
		Hero hero = desk.getHero();
	  this.creature.attack(hero);
	  desk.getHero().attack(this.creature);
	  return false;
	}
	
	public Enemy getCreature(){
		return creature;
	}
	
}
