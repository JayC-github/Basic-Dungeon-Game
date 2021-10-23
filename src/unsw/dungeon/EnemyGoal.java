package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class EnemyGoal implements Goal {
    private Dungeon dungeon;
    private BooleanProperty status;

    public EnemyGoal(Dungeon dungeon) {
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

    // all treasures are collected
    @Override
    public void update() {
        // TODO Auto-generated method stub
        if (dungeon.killAllEnemy()) {
            //System.out.println("Enemy goal completed");
            status.set(true);
        } else {
            //System.out.println("Enemy goal not completed");
            status.set(false);
        }

    }

    @Override
    public BooleanProperty getStatusProperty() {
        // TODO Auto-generated method stub
        return status;
    }
}