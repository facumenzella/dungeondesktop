package Manager;



import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import Desktop.*;
import Tiles.*;
import Character.*;
import Character.Character;
/**
 * clase que levanta un archivo ".board" y inicializa un nuevo juego
 * 
 * Posee dos variables boolean para controlar que exista exactamente un juador y un enemigo de nivel maximo
 * @author Conco
 *
 */
public class Parser {

	private Desktop desk;
	private boolean existsHero;

	/**
	 * lee un archivo y devuelve una instancia de Desktop
	 * lanza una Excepcion de tipo ParsingException si hay algun error en el archivo
	 */
	public Desktop read(File file) throws ParsingException{
		try{
			existsHero=false;
			String line;
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			 //elimino los espacios y coments del principio

			//leo la dimension
			int[] dimension= getDim(getLine(buffer));
			
			//leo el nombre
			String desktName = getLine(buffer);
			desk = new Desktop(dimension[0], dimension[1], desktName);

			//leo el contenido


			int[] array;

			while((line = getLine(buffer)) != null){
				array=parse(line);
				if(!validate(array, dimension)){
					throw new ParsingException();
				}
				process(array);
			}
			if(!existsHero){
				throw new ParsingException();
			}
			buffer.close();
		}catch (IOException e){
			throw new ParsingException();
		}
		return desk;
	}

	private int[] getDim(String line) throws ParsingException{
		int[] rta=new int[2];
		String aux = deleteComent(line);
		String[] sArray = aux.split(",");

		if(sArray.length!=2){
			throw new ParsingException();
		}

		for(int i=0; i<sArray.length; i++){
			try{
				rta[i]=Integer.parseInt((sArray[i]).trim());
			} catch (NumberFormatException e){
				throw new ParsingException();
			}
		}
		return rta;

	}

	/**
	 * elimina comentarios de una linea
	 * @param s
	 * @return
	 */
	private String deleteComent(String s){
		int index = s.indexOf("#");
		if(index!=-1){
			String rta = s.substring(0, index);
			return rta;
		}
		return s;

	}

	/**
	 * Devuelve una linea sin comentarios, eliminando previamente lineas vacias
	 * @param buffer
	 * @return la linea siguiente a ser leida, o null si ya no hay mas lineas
	 * @throws IOException
	 */
	
	private String getLine(BufferedReader buffer) throws IOException{
			String s = buffer.readLine();
			if(s==null){
				return s;
			}
			s=deleteComent(s);
			s=s.trim();
			if(s.length()==0){
				return getLine(buffer);
			}
			return s;
	}

	/**
	 * recopila la informacion de una linea del archivo
	 * @param line
	 * @return un vector con la informacion de la linea
	 * @throws ParsingException si hay un error en la linea
	 */
	private int[] parse(String line) throws ParsingException{

		int [] rta= new int[6]; 
		String aux = deleteComent(line);
		String[] sArray = aux.split(",");
		if(sArray.length!=6){
			throw new ParsingException();
		}

		for(int i=0; i<6; i++){
			try{
				rta[i]=Integer.parseInt((sArray[i]).trim());
			} catch (NumberFormatException e){
				throw new ParsingException();
			}
		}

		return rta;


	}
	
	/**
	 * valida que la informacion en la linea sea correcta
	 * @param array vector con la info
	 * @param dim dimension del tablero
	 * @return true si no hay problemas, false si los hay
	 */
	private boolean validate(int[] array, int[] dim){

		int type = array[0], x=array[1], y=array[2], eType=array[3], eLevel=array[4], bonus=array[5];
		//valido las posiciones
		if(x<0 || y< 0 || x>dim[0] || y>dim[1]){
			return false;
		}
		if(type<1 || type>5){
			return false;
		}
		//valido heroe
		if(type==1 || type==2){
			if(eType!=0 || eLevel!=0 || bonus!=0){
				return false;
			}

		}
		//valido enemigo
		if(type==3){
			if(bonus!=0 || eType>3 || eLevel>Character.levelMax || eType<1 || eLevel<1){
				return false;
			}
		}
		//valido bonus
		if(type==4 || type==5){
			if(bonus<=0 || eType!=0 || eLevel!=0){
				return false;
			}

		}
		return true;

	}
	
	
	/**
	 * procesa la informacion del vector, poniendo al heore y las celdas en el tablero
	 * @param array
	 * @throws ParsingException si se quiere poner mas de un heroe en el tablero o si se quieren poner dos cosas en el mismo lugar del mismo
	 */
	private void process(int[] array) throws ParsingException{
		Point pos = new Point(array[1], array[2]);
		if(array[0]== 1){

			//valido que haya un heroe solo

			if(existsHero){
				throw new ParsingException();
			}
			existsHero=true;
			Hero hero = new Hero(pos, desk);
			desk.putHero(hero);
		}else{
			//pongo la celda, si putTile devuelve fase es porque ya habia algo ahi -> tira excepcion
			if(!(desk.putTile(createTile(array, pos), pos))){
				throw new ParsingException();
			}
		}
	}
	
	
	/**
	 * crea una nueva celda
	 * @param array indormacion que necesita
	 * @param pos posicion de la celda
	 * @return la celda creada
	 * @throws ParsingException
	 */
	private Tile createTile(int[] array, Point pos) throws ParsingException{
		int type = array[0];
		Tile tile=null;
		switch(type){
		case 2:
			tile = new Wall(desk);
			break;
		case 3:
			Enemy e=null;
			int eType=array[3], eLevel=array[4];

			
			switch(eType){
			case 1:
				e= new Golem(eLevel, pos, desk);
				break;
			case 2: 
				e= new Dragon(eLevel, pos, desk);
				break;
			case 3: 
				e= new Snake(eLevel, pos, desk);
				break;
			}
			tile = new EnemyTile(e, desk);
			desk.addEnemy(e);
			break;
		case 4: 
			tile= new HealthBonusTile(array[5], pos, desk);
			break;
		case 5:
			tile= new StrBonusTile(array[5], pos,  desk);
			break;          

		}
		return tile;

	}
}