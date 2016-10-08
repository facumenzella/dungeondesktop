package Tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.*;

import Tiles.EnemyTile;
import Tiles.StrBonusTile;
import Tiles.Wall;

import Character.*;
import Desktop.Desktop;

public class DesktopTest{

    Desktop d;

    @Before
    public void setUp(){
        d=new Desktop(10,10, "test");
        d.setDesktop();
        d.putHero(new Hero(new Point(0,0), d));
    }


    @Test
    public void theDesktopIsExplored(){
        d.explore(new Point(3,3));
        assertTrue(d.getExplorer(3,3));
    }

    @Test
    public void theDesktopPutsTIlesCorrectly(){
        d.putTile(new Wall(d), new Point(5,5));
        assertTrue(d.getBoard(5,5) instanceof Wall);
    }

    @Test
    public void itShouldntBeRightToPutTwoTilesInTheSamePosition(){
        Point p = new Point(5,5);
    	d.putTile(new Wall(d), p);
        assertFalse(d.putTile(new StrBonusTile(3, p, d), p));
    }

    
    @Test
    public void theDesktopPutsTilesWell(){
    	Point p = new Point(0,1);
        d.putTile(new EnemyTile(new Snake(1, p, d),d),p);
        assertTrue(d.getBoard(0,1) instanceof EnemyTile);
    }
    
    @Test
    public void theDesktopKnowsWhenTheHeroIsNearAnEnemy(){
    	Point p = new Point(1,0);
    	Enemy e = new Snake(1, p, d);
        d.putTile(new EnemyTile(e,d),p);
        d.addEnemy(e);
        assertTrue(d.getEnemyNearHero()!=null);
    }

    @Test
    public void theDesktopDoesntLetTheHeroMoveBeyondTheLimits(){
        d.getHero().move(Movements.UP);
        Point p = new Point(0,0);
        assertTrue((d.getHero().getPos()).equals(p));
    }

}
