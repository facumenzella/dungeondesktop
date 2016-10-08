package Tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.*;

import Tiles.EnemyTile;

import Character.*;
import Desktop.Desktop;

public class HeroTest{

    private Hero h;
    private Desktop d;

    @Before
    public void setUp(){
        d=new Desktop(10,10,"test");
        d.setDesktop();
        h=new Hero(new Point(1,1), d);
        d.putHero(h);
        Point p = new Point(1,2);
    	Enemy e = new Snake(1, p, d);
    	d.putTile(new EnemyTile(e, d), p);
        d.addEnemy(e);
    }

    @Test
    public void theHeroMovesCorrectly(){
        h.move(Movements.DOWN);
        Point p = new Point(2,1);
        assertTrue(h.getPos().equals(p));
    }

    @Test
    public void theHeroAttacksWhenMovingIntoAnEnemy(){
    	
        h.move(Movements.RIGHT);
        assertTrue(h.getLife()<h.getLifeMax());
    }

    @Test
    public void TheHeroGetsExperienceWhenKilling(){    	
    	h.move(Movements.RIGHT);
    	h.move(Movements.RIGHT);
    	assertTrue(h.getExp()>0);
    }

    @Test
    public void TheHeroLevelsUpCorrectly(){
        h.addExp(Hero.levelStep-1);
        d.getHero().move(Movements.RIGHT);
        h.move(Movements.RIGHT);
        assertTrue(h.getLevel()==2);
    }

    @Test
    public void theHeroGetsStrengthBonuses(){
        int strInit=h.getStr();
        h.addStrBonus(3);
        assertTrue(h.getStr()>strInit);
    }
    
    @Test
    public void theHeroIsHealedWhenExploring(){
    	h.setHealth(-(h.getLifeMax()-1));
    	h.move(Movements.DOWN);
    	h.move(Movements.DOWN);
    	assertTrue(h.getLife()>1);
    }
}
