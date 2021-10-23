package unsw.dungeon.test;

import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestSwitch {

	Dungeon dungeon;
	Player player;
	Boulder boulder;
	Switch floorSwitch;
	
	@BeforeEach
	public void initTests() {
		
		
		        
		dungeon = new Dungeon(4, 1);
		
		floorSwitch = new Switch(2, 0);
		dungeon.addEntity(floorSwitch);
		
		boulder = new Boulder(1, 0);
		dungeon.addEntity(boulder);
		
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
	}
	
	@Test
	void TriggeringSwitchTest() {
		
		//floor switch is not triggered
		assertFalse(floorSwitch.isTriggered());
		
		// move the boulder to the floor switch
		player.moveRight();
		assertEquals(1, player.getX());
		assertEquals(0, player.getY());
		assertEquals(2, boulder.getX());
		assertEquals(0, boulder.getY());
		assertEquals(2, floorSwitch.getX());
		assertEquals(0, floorSwitch.getY());
		assertTrue(floorSwitch.isTriggered());
		
		player = new Player(dungeon, 3, 0);
		dungeon.setPlayer(player);
		
		// move the boulder away of the switch
		player.moveLeft();
		assertEquals(2, player.getX());
		assertEquals(0, player.getY());
		assertEquals(1, boulder.getX());
		assertEquals(0, boulder.getY());
		assertEquals(2, floorSwitch.getX());
		assertEquals(0, floorSwitch.getY());
		assertFalse(floorSwitch.isTriggered());
	}

}