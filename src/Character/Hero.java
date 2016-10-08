package Character;

import java.awt.Point;
import Desktop.Desktop;
import Tiles.*;
import Game.*;
/**
 * Clase que representa al jugador
 * @author Conco
 *
 */
public class Hero extends Character{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int healthStep = 10;
	public final static int strStep= 5;
	public final static int levelStep = 5;

	private int experience;


	public Hero(Point pos, Desktop d){
		super(healthStep, strStep, 1, pos, d);
	}

	/**
	 * metodo que sube de nivel al jugador cuando obtuvo la experiencia necesaria
	 * sube sus parametros de vida y salud m‡xima
	 */
	public void levelUp(){
		super.level+=1;
		super.lifeMax+=healthStep;
		super.str+=strStep;
		this.experience=0;
	}

	/**
	 * sube la experiencia al heroe cuando mata a un enemigo en funcion del nivel de este
	 */
	public void addExp(int n){
		if(level<levelMax){
			this.experience+=n;
			if(experience>=levelStep*getLevel()){
				this.levelUp();
			}}
	}

	/**
	 * sube la fuerza cuando consigue un bonus
	 */
	public void addStrBonus(int bonus){
		super.str+=bonus;
	}


	private void move(int row, int col)
	{
		Tile target;
		Point destination;
		destination=this.pos.getLocation();
		destination.translate(row,col);
		target=desk.getTile(destination);
		if(target!=null && target.onWalk()==true){
			this.pos=destination;
			desk.explore(destination);
		}
	}

	/**
	 * intenta mover al al jugador a una celda adyacente
	 * lo que sucede depende de la celda a la que se mueva 
	 * @param m corresponde a un elemento del enumerativo Movements (UP, DOWN, LEFT, RIGHT)
	 */
	public void move(Movements m){
		this.move(m.getX(), m.getY());
	}

	/**
	 * se ejecuta cuando el jugador pierde toda su vida
	 * GAME OVER
	 */

	@Override
	public void isDead(){
		desk.gameOver(GameStatus.LOOSE);
	}

	public int getExp(){
		return experience;
	}

	@Override
	public void attack(Character victim){
		if(life > 0){
			super.attack(victim);
		}
	}

}
