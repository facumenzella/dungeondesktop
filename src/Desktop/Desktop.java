package Desktop;

import java.awt.Point;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import Character.*;
import Game.GameStatus;
import Game.Game;
import Tiles.*;

/**
 * 
 * @author fmenzell
 * Clase que representa un tablero. 
 * Tiene una referencia a la partida, otra al heroe del juego, 
 * una lista de enemigos, el nombre del jugador y del mapa, y dos matrices para manejar los casilleros. 
 *
 */

public class Desktop implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game game;
	private String playerName;
	private String deskName;
	private boolean explorer[][];
	private Tile board[][];
	private Hero hero;
	private List<Enemy> eList = new LinkedList<Enemy>();

	public Desktop(int rows, int cols, String dName){
		this.explorer = new boolean[rows][cols];
		this.board = new Tile[rows][cols];
		this.deskName=dName;
		setDesktop();
	}

	public void setPlayerName(String name){
		playerName=name;
	}

	/**
	 * Ilumina los 8 casilleros alrededor de la posicion del heroe.
	 * @param position Posicion a partir del cual se explora. 
	 */
	
	public void explore(Point position){
		int cantTiles=0;
		for(int col = (int)(position.getY())-1 ; col<=(position.getY())+1 ; col++){
			for(int row = (int)(position.getX()-1) ; row<=position.getX()+1 ; row++){
				if(row>=0 && col>=0 && col<getWidth() && row<getHeigth()){
					if(this.explorer[row][col] == false){
						this.explorer[row][col]=true;
						cantTiles+=1;
					}
				}}
		}
		hero.setHealth(cantTiles*(hero.getLevel()));
		cureEnemies(cantTiles);
	}
	
	/**
	 * Cura a los enemigos en base a la cantidad de celdas iluminadas.
	 * @param cantTiles Cantidad de celdas iluminadas.
	 */

	public void cureEnemies(int cantTiles){
		for(Enemy e: eList){
			e.setHealth(cantTiles*e.getLevel());
		}
	}

	/**
	 * Coloca una tile en una determinada posicion.
	 * @param tile Tile a colocar.
	 * @param pos Posicion donde se debe colocar la tile.
	 * @return Devuelve false si lo que habia en esa posicion no era una Tile vacia.
	 */

	public boolean putTile(Tile tile, Point pos){
		Tile old = board[(int)pos.getX()][(int)pos.getY()];
		boolean rta = (old instanceof EmptyTile);
		this.replaceTile(tile,pos);
		return rta;
	}

	/**
	 * Sustituye una tile por otra.
	 * @param tile Tile a sustituir.
	 * @param pos Posicion donde se debe colocar la tile.
	 */
	public void replaceTile(Tile tile, Point pos){
		board[(int)pos.getX()][(int)pos.getY()]=tile;
	}

	public void putHero(Hero hero){
		this.hero=hero;
		this.explore(hero.getPos());
	}

	public void setDesktop(){
		for(int row = 0; row < getHeigth();row++){
			for(int col = 0; col < getWidth(); col++){
				this.board[row][col] = new EmptyTile(this);
			}
		}
	}

	public Hero getHero()
	{
		return this.hero;
	}

	public void addEnemy(Enemy e){
		eList.add(e);
	}

	public void removeEnemy(Enemy e){
		eList.remove(e);
	}

	/**
	 * Recupera una tile a partir de una posicion.
	 * @param p Posicion donde se debe obtener una Tile.
	 * @return Devuelve null si la posicion es invalida.
	 */
	public Tile getTile(Point p)
	{
		int row = (int) p.getX(), column = (int) p.getY();
		if(row<0 || column<0 || row>=getHeigth() || column>=getWidth()){
			return null;
		}else{
			return this.board[(int)p.getX()][(int)p.getY()];}	
	}

	public int getHeigth(){
		return board.length;
	}

	public int getWidth(){
		return board[0].length;
	}

	/**
	 * Recupera un enemigo a partir de una posicion.
	 * @param pos Posicion donde se busca si hay un enemigo.
	 * @return Devuelve null si no habia ningun enemigo.
	 */
	
	private Enemy getEnemy(Point pos){
		for(Enemy e: this.eList){
			if(e.getPos().equals(pos)){
				return e;
			}
		}
		return null;
	}

	/**
	 * Busca los enemigos que se encuentren cerca del heroe.
	 * @return Devuelve null si no hay enemigos cerca.
	 */
	public Enemy getEnemyNearHero(){
		Enemy e;
		Point pos;
		for(Movements m : Movements.values()){
			pos = hero.getPos().getLocation();
			pos.translate(m.getX(), m.getY());
			if((e=getEnemy(pos))!=null){
				return e;
			}
		}
		return null;
	}


	public String getPlayerName(){
		return playerName;
	}

	public String getDName(){
		return deskName;
	}

	public void gameOver(GameStatus gs){
		game.gameOver(gs);
	}

	public void setGame(Game g){
		this.game=g;
	}
	
	public boolean getExplorer(int row, int col){
		return explorer[row][col];
	}
	
	public void setExplorer(int row, int col, boolean val){
		explorer[row][col]=val;
	}
	
	public Tile getBoard(int row, int col){
		return board[row][col];
	}
	
	public void setBoard(int row, int col, Tile tile){
		board[row][col]=tile;
	}
	
	public String getPName(){
		return playerName;
	}
	
}



