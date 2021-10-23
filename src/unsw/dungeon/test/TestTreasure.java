package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import unsw.dungeon.*;
import org.junit.jupiter.api.BeforeEach;

class TestTreasure {
	
	Dungeon dungeon;
	Player player;
	Treasure treasure1;
	Treasure treasure2;
	Treasure treasure3;
	Treasure treasure4;
	
	@BeforeEach
	public void initTest() {
		
		
        
		dungeon = new Dungeon(5, 5);
		
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
		
		
		
		treasure1 = new Treasure(0,2);
		dungeon.addEntity(treasure1);
		
		treasure2 = new Treasure(0,1);
		dungeon.addEntity(treasure2);
		
		
		treasure4 = new Treasure(0,3);
		dungeon.addEntity(treasure4);
		
	}
	
	@Test
	void collectTreasureTest() {
		player.moveDown();
		
		
		assertEquals(player.getTreasure(),0);
	
		//player picks up the treasure on the same square
		player.interact();
		assertEquals(player.getTreasure(),1);
		
		player.moveDown();
		player.interact();
		
		assertEquals(player.getTreasure(),2);

		player.moveDown();
		player.interact();
		
		assertEquals(player.getTreasure(),3);
		
		
	}

}
