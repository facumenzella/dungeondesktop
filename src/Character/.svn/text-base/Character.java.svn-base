package Character;

import java.awt.Point;
import java.io.Serializable;

import Desktop.Desktop;

/** 
 * Clase abstracta que representa un personaje
 * @author cmaderbl
 *
 */

public abstract class Character implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int levelMax = 3; 
	protected int lifeMax;
	protected int life;
	protected int str;
	protected int level;
	protected Point pos;
	protected Desktop desk;
	
	public Character(int lifeMax, int str, int level, Point pos, Desktop d){
		this.lifeMax=lifeMax;
		this.life=lifeMax;
		this.str=str;
		this.level=level;
		this.pos=pos;
		this.desk=d;
		
	}
	/**
	 * reduce la vida a otro Character en funcion de su propia fuerza
	 * @param victim Victima de el ataque
	 */
	public void attack(Character victim){
		victim.setHealth(-(this.str));
	}
	
	/**
	 * cambia la vida actual de este Character
	 * @param aLife Vida que se suma a la vida actual (si esta es negativa, se le resta)
	 */
	
	public void setHealth(int aLife){
		life+=aLife;
		if(life<=0){
			life=0;
			this.isDead();
		}else if(life>lifeMax){
			life=lifeMax;
		}
	}
	
	public int getLevel(){
		return level;
	}
	
	/**
	 * metodo abstacto que se invoca cuando el Character pierde toda su vida
	 */
	
	public abstract void isDead();
	
	public Point getPos(){
		return pos;
	}
	
	public int getLife(){
		return life;
	}
	
	public int getLifeMax(){
		return lifeMax;
	}
	
	public int getStr(){
		return str;
	}
}
