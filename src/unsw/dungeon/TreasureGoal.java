package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class TreasureGoal implements Goal {
    private Dungeon dungeon;
    private BooleanProperty status;

    public TreasureGoal(Dungeon dungeon) {
        this.dungeon = dungeon;
        this.status = new SimpleBooleanProperty(false);
    }

    @Override
    public boolean completed() {
        // TODO Auto-generated method stub
        return status.get();
    }

    @Override
    public boolean addSubGoal(Goal goal) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeSubGoal(Goal goal) {
        // TODO Auto-generated method stub
        return false;
    }

    // all treasures are collected
    @Override
    public void update() {
        // TODO Auto-generated method stub
        if (dungeon.collectAllTreausre()) {
            //System.out.println("Treasure goal completed");
            status.set(true);
        } else {
            //System.out.println("Treasure goal not completed");
            status.set(false);
        }

    }

    @Override
    public BooleanProperty getStatusProperty() {
        // TODO Auto-generated method stub
        return status;
    }

}