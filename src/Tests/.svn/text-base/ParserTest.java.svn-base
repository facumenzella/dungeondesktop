package Tests;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import Manager.*;

public class ParserTest {
	
	private Parser p;
	
	@Before
	public void setUp(){
		p = new Parser();
	}
		
	@Test
	public void ParserShouldReadRight(){
		p.read(new File("./src/Tests/ParserBoardTests/test1.board"));
	}
	
	@Test(expected=ParsingException.class)
	public void ParserPutsTwoThingInTheSamePosition(){
		p.read(new File("./src/Tests/ParserBoardTests/test2.board"));
	}
	@Test(expected=ParsingException.class)
	public void ParserPutsTwoHeroes(){
		p.read(new File("./src/Tests/ParserBoardTests/test3.board"));
	}
	
	@Test(expected=ParsingException.class)
	public void TheresNoHero(){
		p.read(new File("./src/Tests/ParserBoardTests/test4.board"));

	}
	
	
}
