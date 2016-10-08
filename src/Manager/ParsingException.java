package Manager;
/**
 * excepcion que lanza el Parser si hay algun error con el archivo a leer
 * @author Conco
 *
 */
public class ParsingException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ParsingException() {
	}
	
	public ParsingException(String s){
		super(s);
	}
	
}
