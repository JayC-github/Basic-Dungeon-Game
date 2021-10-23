package unsw.dungeon.test;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.*;


import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;

class TestBoulder {
	
	Dungeon dungeon;
	Player player;
	Boulder boulder;
	Boulder boulderBlock;
	
	@BeforeEach
	public void initTests() {
		
        
		dungeon = new Dungeon(7, 7);
		
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
		wall = new Wall(6, 4);
		dungeon.addEntity(wall);
		wall = new Wall(7, 4);
		dungeon.addEntity(wall);
		wall = new Wall(7, 5);
		dungeon.addEntity(wall);
		wall = new Wall(7, 6);
		dungeon.addEntity(wall);
		
		Door door = new Door(1, 5, 0);
		dungeon.addEntity(door);
		
		boulderBlock = new Boulder(1, 2);
		dungeon.addEntity(boulderBlock);
		
	}
	
	@AfterEach
	public void resetTest() {
		dungeon.removeEntity(boulder);
	}
	
	@Test
	public void blockedByDungeonSizeTest() {
		initTestHelper(6, 0, 7, 0);
		player.moveRight();
		assertEquals(6, player.getX());
		assertEquals(0, player.getY());
		assertEquals(7, boulder.getX());
		assertEquals(0, boulder.getY());
	}
	
	@Test
	public void blockedByBoulderTest() {
		initTestHelper(3, 2, 2, 2);
		player.moveLeft();
		assertEquals(3, player.getX());
		assertEquals(2, player.getY());
		assertEquals(2, boulder.getX());
		assertEquals(2, boulder.getY());
		assertEquals(1, boulderBlock.getX());
		assertEquals(2, boulderBlock.getY());
	}
	
	@Test
	public void blockedByWallTest() {
		initTestHelper(2, 2, 2, 1);
		player.moveUp();
		assertEquals(2, player.getX());
		assertEquals(2, player.getY());
		assertEquals(2, boulder.getX());
		assertEquals(1, boulder.getY());
	}
	
	@Test
	public void blockedByDoorTest() {
		initTestHelper(1, 3, 1, 4);
		player.moveDown();
		assertEquals(1, player.getX());
		assertEquals(3, player.getY());
		assertEquals(1, boulder.getX());
		assertEquals(4, boulder.getY());
	}
	
	
	private void initTestHelper(int playerX, int playerY, int boulderX, int boulderY) {
		boulder = new Boulder(boulderX, boulderY);
		dungeon.addEntity(boulder);
		player = new Player(dungeon, playerX, playerY);
		dungeon.setPlayer(player);
	}

}
