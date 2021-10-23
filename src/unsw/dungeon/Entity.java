package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Entity implements EntityObserver {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;

    // exit or not
    private BooleanProperty isExist;
    // check if the entity is visible or not
    private BooleanProperty isVisible;

    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.isExist = new SimpleBooleanProperty(true);
        this.isVisible = new SimpleBooleanProperty(true);
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }


    // if it's the same position
    public boolean samePosition(int x, int y) {
        if (this.getX() == x && this.getY() == y) {
            return true;
        }
        return false;
    }

    @Override
    public void update(Player player) {
        boolean samePosition = samePosition(player.getX(), player.getY());
        
        // check if the entity is a boulder and move to the new position
    	if (this instanceof Boulder && samePosition) {
	    	Boulder b = (Boulder) this;
            b.move(player, null);
	    	player.updateSwitches();
        }
        // check if the entity is a portal and teleport the player to the new portal
    	if (this instanceof Portal && samePosition) {
	    	Portal p = (Portal) this;
	    	p.teleport(player);
        }
        
        // if the entity is an enemy let it move around by position with the player
        if (this instanceof Enemy && !samePosition) {
            Enemy e = (Enemy) this;
            e.move(player);
        }

        // if the entity is an magma let the player die
        if (this instanceof Magma && samePosition) {
            player.setAlive(false);
        }
        
        // update the goals if it has
        player.updateGoals();
    }

	public boolean checkId(int id) {
		return false;
	}

    public BooleanProperty getExist() {
        return isExist;
    }

    public void setExist(boolean isExist) {
        this.isExist.set(isExist);
    }

    public BooleanProperty getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible.set(isVisible);
    }

}
