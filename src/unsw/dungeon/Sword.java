package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Sword extends Entity implements PickUp {
    private int times;
    private BooleanProperty picked;


    public Sword(int x, int y) {
        super(x, y);
        this.times = 5;
        this.picked = new SimpleBooleanProperty(false);
    }

    public int getTimes() {
        return times;
    }

    public void setPicked() {
        picked.set(true);
    }

    @Override
    public Entity pickUp(Player player) {
        System.out.println("Sword"); 
        // if the player has no sword, pick up
         if(!player.hasSword()) {
            // player get the sword
            player.setSword(this);
            // set the sword is picked
            setPicked();
            // set not visible from the UI
            setIsVisible(false);          
            player.removeEntity(this);
            return this;
        }
        
        return null;
    }

    // if u used the 5 times, remove it
    public void checkUsage(Player player) {
		if (this.times == 0) {
			//removes the sword from the player
			//player.setSword(null);
            setExist(false);
            // remove it from the player
            player.removeSword();
		}
    }
    
    // hit the enemy, and check usage everytime
    public boolean hitEnemy(Player player) {
		//finds the enemy closest to the player
		Enemy e = player.findEnemy();
        
        if (e != null) {
			//kills the enemy 
			e.killEnemy(player);
			//reduces the number of turns the sword can be used 
			this.times--;
			checkUsage(player);
			return true;
		}
        
        return false;
	}

}