package unsw.dungeon.test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPotion {

	Dungeon dungeon;
	Player player;
	Enemy enemy;
	Enemy anotherEnemy;
	Potion potion;
	
	@BeforeEach
	public void initTest() {
		
		// set up a level to avoid null errors
		
				        
		dungeon = new Dungeon(4, 4);
		potion = new Potion(1, 0);
		dungeon.addEntity(potion);
		enemy = new Enemy(2, 3);
		dungeon.addEntity(enemy);
		anotherEnemy = new Enemy(3, 3);
		dungeon.addEntity(anotherEnemy);
		Wall wall = new Wall(0, 2);
		dungeon.addEntity(wall);
		wall = new Wall(0, 1);
		dungeon.addEntity(wall);
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
	}
	
	@Test
	void pickingUpPotionsTest() {
		
		// no pick up as the player isn't on the potion
		player.interact();
		assertFalse(player.hasPotion());
		
		// move to the potion
		player.moveRight();
		// pick up potion
        player.interact();
        assertTrue(player.hasPotion());

		player.moveRight();
		assertEquals(0, enemy.getX());
		assertEquals(3, enemy.getY());
		player.moveRight();
		assertEquals(0, enemy.getX());
		assertEquals(3, enemy.getY());
		
		player.moveDown();
		player.moveDown();
		player.moveLeft();
		player.moveLeft();
		player.moveDown();
		player.moveLeft();
		
	}
	
	

}
