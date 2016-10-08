package Tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import Character.Character;
import Character.Enemy;
import Character.Golem;
import Character.Hero;
import Desktop.Desktop;


public class CharacterTest {

	private Character c;
	private Desktop d;

	@Before
	public void setUp(){
		d=new Desktop(10,10,"test");
		c=new Hero(new Point(1,1),d);
	}

	@Test
	public void theCharacterHurtsWhenAttacking(){
		Enemy e=new Golem(1, new Point(1,2), d);
		c.attack(e);
		assertTrue( e.getLife() < e.getLifeMax());

	}

	@Test
	public void theCharacterGetsHurtAndHealed(){
        c.setHealth(-5);
        c.setHealth(3);
        assertTrue(c.getLife()==c.getLifeMax()-2);
}
}

