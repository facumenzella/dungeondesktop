package Character;

import java.awt.Point;

import Desktop.Desktop;
/**
 * Clase de enemigo de tipo Dragon
 * 
 * @author Conco
 *
 */
public class Dragon extends Enemy {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static double healthStep = 1.0;
	private final static double strStep = 1.35;
	
	public Dragon( int level, Point pos, Desktop d){
		super(healthStep, strStep, level, pos, d);
	}


}
