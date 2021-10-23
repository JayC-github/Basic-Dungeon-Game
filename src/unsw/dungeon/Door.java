package unsw.dungeon;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity {
    private BooleanProperty open;
    private int doorid;


    public Door(int x, int y, int id) {
        super(x, y);
        this.doorid = id;
        this.open = new SimpleBooleanProperty(false);
    }

    // get the id of the door
    public int getId() {
        return doorid;
    }

    // use for show if the door is open or not
    public BooleanProperty isOpen() {
        return open;
    }

    // check if the door is open/block the player
    public boolean DoorOpen() {
        return open.get();
    }

    // check if the key user hold is the same as the door id
    public boolean checkKey(int keyid) {
        if (this.doorid == keyid) {
            return true;
        }        
        return false;
    }

    // open the door
    public void open() {
        open.set(true);
    }
    
    // check if the user is near the door
    public boolean nearDoor(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();
        if (getX() == playerX && (getY() == playerY + 1 || getY() == playerY - 1)) {
            return true;
        } else if (getY() == playerY && (getX() == playerX + 1 || getX() == playerX - 1)) {
            return true;
        }
        return false;
    }

}