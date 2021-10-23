package unsw.dungeon;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class BoulderGoal implements Goal {
    private Dungeon dungeon;
    private BooleanProperty status;

    public BoulderGoal(Dungeon dungeon) {
        this.dungeon = dungeon;
        this.status = new SimpleBooleanProperty(false);
    }
    
    @Override
    public boolean completed() {
        // TODO Auto-generated method stub
        return status.get();
    }

    @Override
    public boolean addSubGoal(Goal subGoal) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeSubGoal(Goal subGoal) {
        // TODO Auto-generated method stub
        return false;
    }

    // all the switches are triggered, boulder on the switch
    @Override
    public void update() {
        // TODO Auto-generated method stub
        if (dungeon.allTriggeredSwitches()) {
            //System.out.println("boulder goal completed");
            status.set(true);
        } else {
            //System.out.println("boulder goal not completed");
            status.set(false);
        }

    }

    @Override
    public BooleanProperty getStatusProperty() {
        // TODO Auto-generated method stub
        return status;
    }
    
}