package Gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Character.Character;
import Character.Dragon;
import Character.Enemy;
import Character.Golem;
import Character.Hero;
import Character.Movements;
import Character.Snake;
import Desktop.Desktop;
import Game.Game;
import Game.GameStatus;
import Game.StatusObserver;
import Manager.GameManager;
import Manager.Parser;
import Manager.ParsingException;
import Tiles.BloodTile;
import Tiles.BonusTile;
import Tiles.EmptyTile;
import Tiles.EnemyTile;
import Tiles.HealthBonusTile;
import Tiles.StrBonusTile;
import Tiles.Tile;
import Tiles.Wall;
/**
 * Clase que se encarga de manejar el Front-end
 * @author fmenzell
 *
 */
public class Window extends JFrame{

	private static final long serialVersionUID = 1L;
	private DeskMenu menu;
	private JPanelWithBackground fondo;
	private Clip music;
	private GamePanel gPanel;
	private InfoPanel iPanel;
	private Game game;
	private Map<String, Image> sprites;
	private Map<String, Image> heroSprites;
	private LinkedList<String> invalidDesktops;
	private KeyAdapter moveAdapter;
	private StatusObserver statusO;

	/**
	 * Constructor de una ventana nueva.
	 */
	public Window(){
		super("Dungeon Desktop");
		try{
			fondo= new JPanelWithBackground(ImageUtils.loadImage("./resources/masterSwordDD.jpg"));
		}catch(IOException e){
			JOptionPane.showMessageDialog(this.getContentPane(), "An error has occurred while uploading the images");
			System.exit(0);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		initImages();
		initPanels();
		menu = new DeskMenu(this);
		menu.initMenu();
		statusO = new StatusObserver(GameStatus.PLAYING, this);
		setMoveAdapter(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch ( e.getKeyCode() ) {
				case KeyEvent.VK_UP:
					game.getDesk().getHero().move(Movements.UP);
					sprites.put(Hero.class.getName(),heroSprites.get("NORTH"));
					break;

				case KeyEvent.VK_DOWN:
					game.getDesk().getHero().move(Movements.DOWN);
					sprites.put(Hero.class.getName(),heroSprites.get("SOUTH"));
					break;

				case KeyEvent.VK_LEFT:
					game.getDesk().getHero().move(Movements.LEFT);
					sprites.put(Hero.class.getName(),heroSprites.get("WEST"));
					break;

				case KeyEvent.VK_RIGHT:
					game.getDesk().getHero().move(Movements.RIGHT);
					sprites.put(Hero.class.getName(),heroSprites.get("EAST"));
					break;
				}
				if(gPanel != null && iPanel != null){
					refreshPanel();
					iPanel.refresh(game.getDesk().getEnemyNearHero());
				}
			}
		});
	}

	public void setMoveAdapter(KeyAdapter moveAdapter) {
		this.moveAdapter = moveAdapter;
	}

	public KeyAdapter getMoveAdapter() {
		return moveAdapter;
	}
	/**
	 * Metodo que inicia todo lo referido al menu de la ventana y agrega los listeners correspondientes.
	 */

	private void initPanels(){
		fondo.setPreferredSize(new Dimension(800,600));
		setVisible(true);
		try { 
			music = AudioSystem.getClip(); 
			music.open( AudioSystem.getAudioInputStream(new File("./resources/zelda.wav"))); 
			music.start();
		}
		catch (UnsupportedAudioFileException ex) 
		{ JOptionPane.showMessageDialog(this, "No se soporta ese formato de audio", "Error", JOptionPane.ERROR_MESSAGE); }
		catch (LineUnavailableException ex) { JOptionPane.showMessageDialog(this, "No se puede abrir la linea (no hay tarjeta de sonido)", "Error", JOptionPane.ERROR_MESSAGE); }
		catch (IOException ex) { JOptionPane.showMessageDialog(this, "No se puede abrir el archivo", "Error", JOptionPane.ERROR_MESSAGE); } 
		}

	/**
	 * Metodo que desactiva el fondo, para colocar el tablero de juego.
	 */
	private void setGamePanels()
	{
		getContentPane().removeAll();
		menu.getMenuBar().getMenu(0).getItem(2).setEnabled(true);
		menu.getMenuBar().getMenu(0).getItem(3).setEnabled(true);
		gPanel= new GamePanel(game.getDesk().getHeigth(), game.getDesk().getWidth(), 30, new BaseGamePanelListener(), Color.BLACK);
		refreshPanel();
		iPanel = new InfoPanel(400, gPanel.getHeight(), Color.WHITE, game.getDesk().getHero(), game.getDesk().getPlayerName());
		iPanel.refresh(game.getDesk().getEnemyNearHero());
		getContentPane().add(gPanel,BorderLayout.WEST);
		getContentPane().add(iPanel,BorderLayout.EAST);
		iPanel.refresh(game.getDesk().getEnemyNearHero());
		gPanel.setPreferredSize(gPanel.getSize());
		iPanel.setPreferredSize(new Dimension(400,gPanel.getHeight()));
		pack();
	}




	private Map<String, Desktop> fileFilter(){
		
		File dir = new File("./boards");
		File[] files = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				if(name.endsWith(".board")){
					return true;
				}
				return false;
			}
		});
		if(files==null){
			JOptionPane.showMessageDialog(this.getContentPane(), "An error has occurred while charging the files");
			System.exit(0);
		}
		Parser p;
		Desktop d;
		Map<String, Desktop> Desktops= new HashMap<String,Desktop>();
		//Parseo todos los archivos y los separo en validos e invalidos
		invalidDesktops=new LinkedList<String>();
		for(File f:files){
			p = new Parser();
			try{
				d=p.read(f);
				Desktops.put(d.getDName(),d);

			}catch(ParsingException e){
				invalidDesktops.add(f.getName());
			}

		}
		//devuelvo los validos
		return Desktops;
	}

	private String getInvalids(){
		String invalids= new String();
		for(String s: invalidDesktops){
			invalids= invalids+s+" ";
		}
		return invalids;
	}
	private Desktop boardSelect(){

		Map<String, Desktop> validDesktops = fileFilter();
		DesktopShowList deskShowList = new DesktopShowList(validDesktops,getInvalids());
		return deskShowList.getSelectedDesk();
	}

	private Desktop getBoard(String name){
		return fileFilter().get(name);
	}

	void restart(){
		try{
			Desktop d=getBoard(game.getDesk().getDName());
			game=new GameManager().newGame(d, game.getDesk().getPName());
			game.addObserver(statusO);			
		}catch(NullPointerException e){
			JOptionPane.showMessageDialog(this.getContentPane(), "An error has occurred while restarting");
		}
		setGamePanels();
	}

	void saveGame() throws IOException{
		String name=JOptionPane.showInputDialog("Please enter your game name:");
		while(name==null||name.length()<3||name.length()>15){
			name=JOptionPane.showInputDialog("Invalid name. Please enter your game name");
		}
		new GameManager().saveGame(game, name);
	}

	void loadGame(File file) throws IOException, ClassNotFoundException {
		game = new GameManager().loadGame(file);
		newGame(game);
	}

	void newGame(){
		Desktop d;
		d=boardSelect();
		if(d!=null){
			String name=JOptionPane.showInputDialog("Please enter your name");;
			while(name==null||name.length()<3||name.length()>15){
				name=JOptionPane.showInputDialog("Invalid name. Please enter your name");
			}
			game=new GameManager().newGame(d, name);
			game.addObserver(statusO);
			removeKeyListener(getMoveAdapter());
			addKeyListener(getMoveAdapter());
			setGamePanels();
		}
	}

	private void newGame(Game game){
		game.addObserver(statusO);
		removeKeyListener(getMoveAdapter());
		addKeyListener(getMoveAdapter());
		setGamePanels();
	}

	/**
	 * Metodo que resetea los panels, colocando denuevo la imagen de fondo en la ventana.
	 */
	public void reset(){
		removeKeyListener(getMoveAdapter());
		gPanel=null;
		iPanel=null;
		menu.getMenuBar().getMenu(0).getItem(2).setEnabled(false);
		menu.getMenuBar().getMenu(0).getItem(3).setEnabled(false);
		getContentPane().removeAll();
		add(fondo);
		pack();
	}

	private class BaseGamePanelListener implements GamePanelListener {

		@Override
		public void onMouseMoved(int row, int column) {
			if(column<game.getDesk().getWidth() && row<game.getDesk().getHeigth()&&column>=0&&column>=0){
				Tile tile = game.getDesk().getTile(new Point(row,column));
				if(tile instanceof EnemyTile&&game.getDesk().getExplorer(row,column)){
					iPanel.refreshMouse(((EnemyTile)tile).getCreature());
				}
				else{
					iPanel.refreshMouse(null);
					iPanel.refresh(game.getDesk().getEnemyNearHero());
				}
			}

		}
	}

	

	/**
	 * Metodo que analiza la matriz de tiles, y en base a eso reacomoda el gamepanel.
	 */
	public void refreshPanel(){
		Point heroPos = game.getDesk().getHero().getPos();
		for(int i=(int) (heroPos.getX()-1); i<=heroPos.getX()+1; i++){
			for(int j=(int) (heroPos.getY()-1) ; j<=heroPos.getY()+1; j++){
				if(i>=0 && j>=0 && i<game.getDesk().getHeigth() && j<game.getDesk().getWidth()){
					if(game.getDesk().getExplorer(i,j)){
						Tile t=game.getDesk().getTile(new Point(i,j));
						if(t.getClass()==EnemyTile.class){
							Enemy e=((EnemyTile)t).getCreature();
							gPanel.put(drawLevel(e), i, j);			
						}
						else if(t instanceof BonusTile){
							BonusTile bt=(BonusTile)t;
							gPanel.put(ImageUtils.drawString(sprites.get(bt.getClass().getName()),((Integer)bt.getValue()).toString(),Color.RED), i, j);	
						}
						else {
							gPanel.put(sprites.get(t.getClass().getName()),i,j);
						}
					}}
			}
		}
		int x=(int)game.getDesk().getHero().getPos().getX();
		int y=(int)game.getDesk().getHero().getPos().getY();
		// Agrega al heroe a la matriz de imagenes overlapeandolo con lo que haya abajo (empty o blood)
		gPanel.put(ImageUtils.overlap(sprites.get(game.getDesk().getTile(new Point(x,y)).getClass().getName()),drawLevel(game.getDesk().getHero())),x,y);
		repaint();
	}

	private Image drawLevel(Character c){
		Image base=sprites.get(c.getClass().getName());
		return ImageUtils.drawString(base,((Integer)c.getLevel()).toString(),Color.WHITE);
	}

	/**
	 * Metodo que setea las imagenes a usar en un HashMap.
	 */
	public void initImages() {
		try{
			sprites = new HashMap<String, Image>();
			heroSprites = new HashMap<String, Image>();
			Image back=ImageUtils.loadImage("./resources/grass.png");
			sprites.put(EmptyTile.class.getName(),back);
			sprites.put(Dragon.class.getName() ,ImageUtils.overlap(back,ImageUtils.loadImage("./resources/skeleton.png")));
			sprites.put(Snake.class.getName(),ImageUtils.overlap(back,ImageUtils.loadImage("./resources/snake2.png")));
			sprites.put(Golem.class.getName(),ImageUtils.overlap(back,ImageUtils.loadImage("./resources/aguy.png")));
			sprites.put(Wall.class.getName(),ImageUtils.loadImage("./resources/tree.png"));
			sprites.put(BloodTile.class.getName(),ImageUtils.overlap(back,ImageUtils.loadImage("./resources/blood.png")));
			sprites.put(StrBonusTile.class.getName(),ImageUtils.overlap(back,ImageUtils.loadImage("./resources/attackBoost.png")));
			sprites.put(HealthBonusTile.class.getName(),ImageUtils.overlap(back,ImageUtils.loadImage("./resources/healthBoost.png")));
			heroSprites.put("SOUTH",ImageUtils.loadImage("./resources/linkS.png"));
			heroSprites.put("NORTH",ImageUtils.loadImage("./resources/linkN.png"));
			heroSprites.put("WEST",ImageUtils.loadImage("./resources/linkW.png"));
			heroSprites.put("EAST",ImageUtils.loadImage("./resources/linkE.png"));
			sprites.put(Hero.class.getName(),heroSprites.get("SOUTH"));
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this.getContentPane(), "An error has occurred while uploading the images");
			System.exit(0);
		};
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String g[]){
		Window prog = new Window();
		prog.show();
	}

	
	public Clip getMusic(){
		return music;
	}
}
