package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Key extends Entity implements PickUp{
    private int Keyid;
    private BooleanProperty picked;
    
    
    public Key(int x, int y, int id) {
        super(x, y);
        this.Keyid = id;
        this.picked = new SimpleBooleanProperty(false);
    }

    public int getKeyid() {
        return Keyid;
    }

    public void setKeyid(int keyid) {
        Keyid = keyid;
    }

    public boolean getPicked() {
        return picked.get();
    }

    public void setPicked(boolean ifpick) {
        picked.set(ifpick);
    }

    @Override
    public Entity pickUp(Player player) {
        System.out.println("Key");
        // if the player has no key, pick up
        if(!player.haskey()) {
            // player get the key
            player.setKey(this);
            // set the key is picked
            setPicked(true);
            // set the key is not visible from the ui
            setIsVisible(false);
            // remove it from the dungeron;
            player.removeEntity(this);
            return this;
        }
        
        return null;
    }

    // try to drop the key on the ground
    public void drop(Player player) {
        // drop the key at the player's position
        this.x().set(player.getX());
        this.y().set(player.getY());
        this.setIsVisible(true);
        // put it on the dungeon
        player.addEntity(this);
        // remove it from player's key;
        player.removeKey();
    }




}