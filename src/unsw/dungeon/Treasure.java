package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Treasure extends Entity implements PickUp {
    private BooleanProperty collected;

    public Treasure(int x, int y) {
        super(x, y);
        this.collected = new SimpleBooleanProperty(false);
    }

    @Override
    public Entity pickUp(Player player) {
        System.out.println("Treasure");
        this.collected.set(true);
        // add the treasure to the player
        player.addTreasure();
        // set the key is not visible from the ui
        setIsVisible(false);
        // remove it from the entity list of dungeon
        player.removeEntity(this);
        return this;
    }
}