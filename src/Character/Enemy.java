package Character;

import java.awt.Point;
import Game.*;
import Desktop.Desktop;
import Tiles.*;

/**
 * Clase que representa un personaje enemigo
 * @author Conco
 *
 */

public abstract class Enemy extends Character{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Enemy(double F, double S, int level, Point pos, Desktop d){
		super((int)((Math.pow(level+3, 2) - 10)*S), (int)((level*level + 5*level)*F*0.5), level, pos, d); 
	}
	
	/**
	 * metodo que se invoca cuando el enemigo muere.
	 * donde habia un enemigo ahora hay sangre
	 * si el enemigo es de nivel 3 el jugador gana el juego
	 */
	@Override
	public void isDead(){
		desk.getHero().addExp(super.level);
		if(super.level==3){
			desk.gameOver(GameStatus.WIN);
		}
		desk.replaceTile(new BloodTile(desk),super.pos);
		desk.removeEnemy(this);
	}

}
