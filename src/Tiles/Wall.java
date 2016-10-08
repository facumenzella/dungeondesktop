package Tiles;

import java.io.Serializable;

import Desktop.Desktop;

/**
 * Pared por la cual no se puede caminar
 * @author Fede
 *
 */
public class Wall extends Tile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Wall(Desktop d){
		super(d);
	}
	
	/**
	 * @return false
	 */
	@Override
	public boolean onWalk()
	{
	  return false;
	}
}
