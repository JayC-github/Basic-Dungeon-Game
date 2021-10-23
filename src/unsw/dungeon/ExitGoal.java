package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ExitGoal implements Goal {
    private Dungeon dungeon;
    private BooleanProperty status;

    public ExitGoal(Dungeon dungeon) {
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


    // if the player stand on the exit, return true
    @Override
    public void update() {
        // if player stand on the exit, check if finish the other goal
        if (dungeon.reachExit()) {
            status.set(true);
            //System.out.println("exit goal completed");
        // not stand on the exit
        } else {
            //System.out.println("exit goal not completed");
            status.set(false);
        }
    }

    @Override
    public BooleanProperty getStatusProperty() {
        // TODO Auto-generated method stub
        return status;
    }




    
}