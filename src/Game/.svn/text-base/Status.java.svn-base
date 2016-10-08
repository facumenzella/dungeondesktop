package Game;

import java.io.Serializable;
import java.util.Observable;


/**
 * Clase observable que guarda un estado que sabe si bien se sigue jugando, o si el juego se ha ganado o perdido
 */
@SuppressWarnings("serial")
public class Status extends Observable implements Serializable{
	private GameStatus status;
	
	public Status(GameStatus status){
		this.status = status;
	}
	
	public void setStatus(GameStatus status){
		this.status = status;
		
		setChanged();
		notifyObservers();
	}
	
	public GameStatus getStatus(){
		return status;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Status)){
			return false;
		}
		Status newStat = (Status)obj;
		return (this.status == newStat.status);
	}
}
