package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private Key key;
    private Potion potion;
    private Sword sword;
    private int treasure;
    // player has a bow
    private Bow bow;
    private BooleanProperty invincible;
    // wanna do something not yet
    private BooleanProperty alive;
    // up/down/left/right/space
    private String move;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.key = null;
        this.potion = null;
        this.sword = null;
        this.bow = null;
        this.treasure = 0;
        this.invincible = new SimpleBooleanProperty(false);
        this.alive = new SimpleBooleanProperty(true);
    }

    // String move part
    public void setMove(String string) {
    	move = string;
    }
    
    public String getMove() {
    	return move;
    }

    public BooleanProperty isAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive.set(alive);
    }


    public void moveUp() {
        if (getY() > 0 && canMove("up"))
            y().set(getY() - 1);
            setMove("up");
            dungeon.notifyObservers();
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1 && canMove("down"))
            y().set(getY() + 1);
            setMove("down");
        	dungeon.notifyObservers();
    }

    public void moveLeft() {
        if (getX() > 0 && canMove("left"))
            x().set(getX() - 1);
            setMove("left");
        	dungeon.notifyObservers();
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1 && canMove("right"))
            x().set(getX() + 1);
            setMove("right");
        	dungeon.notifyObservers();

    }

    public Entity interact() {
        Entity entity = null;
        setMove("space");

        // try to pickup the entity on the player's position
        PickUp entityPickUp = dungeon.pickUpEntity(getX(), getY());
        Entity interactE = (Entity) entityPickUp;
        
        // if u get something pickable, pick up it, sword/treasure/key/potion
        // but if the enemy is nearby, try to kill the enemy or eat the potion first;
        if (interactE != null && (findEnemy() == null || interactE instanceof Potion)) {
            entity = entityPickUp.pickUp(this);
        // if there is nothing pickable, then kill enemy or open the door
        } else {
            // if u have a key, try open the door, but if enemy is nearby, kill the enemy first
            if (key != null && findEnemy() == null) {
                Door d = findDoor();
                // if player near the door
                if (d.nearDoor(this)) {
                    // set the door open
                    d.open();
                    key.setExist(false);
                    removeKey();
                    removeEntity(key);
                } else {
                    key.drop(this);
                }    
            // if u have a gun, kill a radom enemy, else, use the sword to hitenemy
            } else {
                if (bow != null) {
                    bow.shotEnemy(this);
                } else if (sword != null) {
                    sword.hitEnemy(this);
                }
            }
        }

        dungeon.notifyObservers();
        return entity;
    }

    // check if the position u try to move is available
    public boolean canMove(String move) {
        int x = getX();
        int y = getY();
        if (move == "up") y--;
        else if (move == "down") y++;
        else if (move == "left") x--;
        else x++;
     
        return dungeon.checkLocation(move, x, y);
    }

    ///////////////////// Boulder and switch part
    public boolean checkBoulder() {
    	Boulder boulder = dungeon.getBoulder(getX(), getY());
    	if (boulder != null) {
    		return true;
    	}
    	return false;
    }
    
    // updates all the switches in the dungeon
    public void updateSwitches() {
    	dungeon.updateSwitches();
    }
    
    // return the corresponding portal
    public Portal locatePortal(int id) {
		return dungeon.locatePortal(id);
	}
    
    // check if a square is empty for the player to teleport
    public boolean checkEmpty(int x, int y) {
		return dungeon.freeSquare(x, y);
	}
    
    // teleport the player to the corresponding portal(x,y) if it's available
    public void teleportPlayer(int x,int y) {
    	x().set(x);
    	y().set(y);
    }
    
    // pick up difference entities in the dungeon- Key/Sword/Potion/Treasure
    public void pickUp(PickUp pickupEntity) {
        pickupEntity.pickUp(this);
    }

    ///////////////////////// key and open the door
    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public void removeKey() {
        this.key = null;
    }

    // get the corresponding door by the key player hold
    public Door findDoor() {
        int keyid = this.key.getKeyid();
        Door d = dungeon.DoorByKey(keyid);
        return d;
    }

    // check if the player has a key
    public boolean haskey() {
        if (this.key != null) {
            return true;
        }
        return false;
    }


    //////////////////////////// sword and kill the enemy
    // set sword
    public void setSword(Sword sword) {
        this.sword = sword;
    }

    // check if the player has a sword
    public boolean hasSword() {
        if (this.sword != null) {
            return true;
        }
        return false;
    }

    // remove sword since used 5 times
    public void removeSword() {
        this.sword = null;
    }

    /////////////////////////  bow, new extend entity
    public void setBow(Bow bow) {
        this.bow = bow;
    }

    public boolean hasBow() {
        if (this.bow != null) {
            return true;
        }
        return false;
    }

    public void removeBow() {
        this.bow = null;
    }

    ////////////////////// treasure
    // get the treasure and add to the player's treasure
    public void addTreasure() {
        this.treasure++; 
    }

    public int getTreasure() {
        return treasure;
    }

    public void setTreasure(int treasure) {
        this.treasure = treasure;
    }
    
    ////////////////////////////// potion
    // check if the player has a potion
    public boolean hasPotion() {
        if (this.potion != null) {
            return true;
        }
        return false;
    }

    public void setPotion(Potion potion) {
        this.potion = potion;
    }

    // set the player to be invincible
    public void setInvincible(boolean b) {
        this.invincible.set(b);
    }

    // check if the player is invincible or not
    public BooleanProperty isInvincible() {
        return invincible;
    }

    // remove the postion after use
    public void removePotion() {
        this.potion = null;
    }

    ///////////////////////////Enemy 
    // check enemy is nearbt or not, if null will return
    public Enemy findEnemy() {
        return dungeon.findEnemy();
    }

    public Enemy findRandomEnemy() {
        return dungeon.findRadomEnemy();
    }

    public void setEnemyState(Movement enemyState) {
        dungeon.setEnemyState(enemyState);
    }
    
    // if the player touch enemy in different state
    public void touchEnemy(Enemy enemy) {
        // enemy die
        if (isInvincible().get()) {
            System.out.println("kill the enemy!!!!!");
            // remove the entity
            removeEntity(enemy);
            // enemy
            enemy.setExist(false);
        // player die
        } else { 
            System.out.println("player die!!!!!!!!!!!!!!");
            setAlive(false);
        }

    }

    // entiity picked by the player, remove it from the dungeon
    public void removeEntity(Entity entity) {
        dungeon.removeEntity(entity);
    }

    // entity dropped on the dungeon by the player
    public void addEntity(Entity entity) {
        dungeon.addEntity(entity);
    }

    // check if the entity is close to the player
    public boolean checkAdjacent(int x, int y) {
    	if (getX() + 1 == x && getY() == y) {
    		return true;
    	} else if (getX() - 1 == x && getY() == y) {
    		return true;
    	} else if (getX() == x && getY() + 1 == y) {
    		return true;
    	} else if (getX() == x && getY() - 1 == y) {
    		return true;
    	}
    	return false;
    }

    // dungeon goals
    public void updateGoals() {
        dungeon.updateGoals();
    }

    public boolean arriveExit() {
        return dungeon.reachExit();
    }
}
