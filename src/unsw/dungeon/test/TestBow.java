
package unsw.dungeon.test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import unsw.dungeon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
public class TestBow {
    Dungeon dungeon;
    Player player;
    Enemy enemy;
    Bow Bow;

    @BeforeEach
	public void initTests() {
		
		
		dungeon = new Dungeon(8, 8);
		
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
		
		
		Wall wall = new Wall(2, 0);
		dungeon.addEntity(wall);
		wall = new Wall(3, 0);
		dungeon.addEntity(wall);
		wall = new Wall(4, 0);
		dungeon.addEntity(wall);
		wall = new Wall(5, 0);
		dungeon.addEntity(wall);
		wall = new Wall(5, 1);
		dungeon.addEntity(wall);
		wall = new Wall(3, 4);
		dungeon.addEntity(wall);
		wall = new Wall(3, 5);
		dungeon.addEntity(wall);
		wall = new Wall(3, 6);
		dungeon.addEntity(wall);
		wall = new Wall(3, 7);
		dungeon.addEntity(wall);
		wall = new Wall(4, 7);
		dungeon.addEntity(wall);
		wall = new Wall(5, 7);
		dungeon.addEntity(wall);
		wall = new Wall(6, 4);
		dungeon.addEntity(wall);
		wall = new Wall(7, 4);
		dungeon.addEntity(wall);
		wall = new Wall(7, 5);
		dungeon.addEntity(wall);
		wall = new Wall(7, 6);
		dungeon.addEntity(wall);
		
		
		
		Bow = new Bow(3,2);
		dungeon.addEntity(Bow);

	}
    @Test
	void test() {
		
		player.moveDown();
		player.moveDown();
		player.moveRight();
		player.moveRight();
		player.moveRight();
		
		assertFalse(player.hasBow());
	
		//player picks up the Bow
		player.interact();
		
		assertTrue(player.hasBow());
	}
	
	@Test
	void enemyMovementTest() {
		enemy = new Enemy(6,5);
		dungeon.addEntity(enemy);
		Enemy enemy2 = new Enemy(7,0);
		dungeon.addEntity(enemy2);
		
		player.moveDown();
		assertEquals(0, player.getX());
		assertEquals(1, player.getY());
		assertEquals(5, enemy.getX());
		assertEquals(5, enemy.getY());	
		assertEquals(6, enemy2.getX());
		assertEquals(0, enemy2.getY());
		
		player.moveDown();
		assertEquals(0, player.getX());
		assertEquals(2, player.getY());	
		assertEquals(4, enemy.getX());
		assertEquals(5, enemy.getY());
		assertEquals(6, enemy2.getX());
		assertEquals(1, enemy2.getY());
		
		player.moveRight();
		assertEquals(1, player.getX());
		assertEquals(2, player.getY());
		assertEquals(4, enemy.getX());
		assertEquals(4, enemy.getY());	
		assertEquals(6, enemy2.getX());
		assertEquals(2, enemy2.getY());
		
		player.moveRight();
		assertEquals(2, player.getX());
		assertEquals(2, player.getY());
		assertEquals(4, enemy.getX());
		assertEquals(3, enemy.getY());
		assertEquals(5, enemy2.getX());
		assertEquals(2, enemy2.getY());
		
		player.moveRight();
		assertEquals(3, player.getX());
		assertEquals(2, player.getY());
		assertEquals(3, enemy.getX());
		assertEquals(3, enemy.getY());
		assertEquals(4, enemy2.getX());
		assertEquals(2, enemy2.getY());
		
	}
	
	@Test
	void killEnemyTest() {
		//player moves to the Bow location
		player.moveDown();
		player.moveDown();
		player.moveRight();
		player.moveRight();
		player.moveRight();
		
		
		
		
		assertEquals(3, player.getX());
		assertEquals(2, player.getY());
		
		//player picks up the Bow and kills the enemy
		player.interact();
		enemy = new Enemy(4,2);
		dungeon.addEntity(enemy);
		player.interact();
		assertTrue(dungeon.killAllEnemy());		
		// assertTrue(player.findInventory(Bow),"Player must find Bow in inventory after pickup");
		
	}
    @AfterEach
	public void resetTest() {
		dungeon.removeEntity(enemy);
		dungeon.removeEntity(Bow);
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
		enemy = new Enemy(6, 5);
		dungeon.addEntity(enemy);
		Bow = new Bow(3, 2);
		dungeon.addEntity(Bow);

	}
	

}
