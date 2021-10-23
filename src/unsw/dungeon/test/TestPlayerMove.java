package unsw.dungeon.test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestPlayerMove {
	
	@Test
	void test() {
		int i, j;
		Dungeon dungeon = new Dungeon(9, 6);
		Player player = new Player(dungeon, 1, 2);
		
		for (i = 0 ; i < 6; i++) {
			for (j = 0 ; j < 6; j++) {
				if (i == 0 || j == 0 || i == 7 || j == 4) {
					if ((i == 7 && j == 3) || (i == 4 && j == 0)) {
						continue;
					}
					Wall wall = new Wall(i, j);
					dungeon.addEntity(wall);
				}
			}
		}
		// initialize
		dungeon.setPlayer(player);
		assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 2);
        
        // move to left
		player.moveLeft();
		assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 2);
        
        // move to right
		player.moveRight();
		assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 2);
        
        // move up
		player.moveUp();
		assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 1);
        
        // move down x 2
		player.moveDown();
		player.moveDown();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 3);
		
	}

}
