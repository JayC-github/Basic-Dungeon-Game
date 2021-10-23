package unsw.dungeon.test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import unsw.dungeon.*;
import org.junit.jupiter.api.BeforeEach;

public class TestDoorAndKey {

    @Test
	void test() {
        Dungeon dungeon;
        Player player;
        Door door;
        Key key, anotherKey;
        Sword sword;
        dungeon = new Dungeon(4, 4);
            
        door = new Door(3, 3, 0);
        dungeon.addEntity(door);
        
        key = new Key(0, 0, 0);
        dungeon.addEntity(key);
        
        anotherKey = new Key(2, 0, 1);
        dungeon.addEntity(anotherKey);
        
        Door anotherDoor = new Door(0, 3, 1);
        dungeon.addEntity(anotherDoor);
        
        sword = new Sword(1, 1);
        dungeon.addEntity(sword);
        
        
        player = new Player(dungeon, 0, 0);
        dungeon.setPlayer(player);

        player.interact();
		assertTrue(player.haskey());
		player.moveRight();
		player.moveRight();
		player.moveDown();
        player.moveDown();
        player.moveDown();
		int x = player.getX();
        int y = player.getY();
        System.out.println(x);
        System.out.println(y);
		assertTrue(door.nearDoor(player));
		
        player.moveRight();
        System.out.println(player.getX());
        System.out.println(player.getY());
		assertEquals(x, player.getX());
		assertEquals(y, player.getY());
		player.interact();
		
		player.moveRight();
		assertEquals(x+1, player.getX());
		assertEquals(y, player.getX());
    }
}