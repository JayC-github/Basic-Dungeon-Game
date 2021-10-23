package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Bow extends Entity implements PickUp {
    private BooleanProperty picked;


    public Bow(int x, int y) {
        super(x, y);
        this.picked = new SimpleBooleanProperty(false);
    }

    public void setPicked() {
        picked.set(true);
    }

    @Override
    public Entity pickUp(Player player) {
        // System.out.println("bow!!!!!!!!"); 
        // if the player has no bow, pick up
         if(!player.hasBow()) {
            // player get the bow
            player.setBow(this);
            // set the bow is picked
            setPicked();
            // set not visible from the UI
            setIsVisible(false);         
            player.removeEntity(this);
            return this;
        }
        
        return null;
    }

    // shot the enemy, and remove the gun
    public boolean shotEnemy(Player player) {
	    // find a radom enemy on theb bourlder
		Enemy e = player.findRandomEnemy();

        // if u find one
        if (e != null) {
            System.out.println("shot the enemy!!!!!!!!!!!!!!!!");
            // kills the enemy 
			e.killEnemy(player);
            // remove the gun from the player
            setExist(false);
            player.removeBow();

			return true;
		}
        
        return false;
	}

}