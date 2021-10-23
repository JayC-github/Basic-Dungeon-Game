package unsw.dungeon;

import javafx.beans.property.BooleanProperty;

public interface Goal {
    // check if the goal is complete
    public boolean completed();

    // for the composite goal to add subgoal
    // true-> add, false-> no subgoal list
    public boolean addSubGoal(Goal subGoal);
    
    // for the composite goal to remove the subgoal
    // true-> remove, false-> no subgoal list
    public boolean removeSubGoal(Goal subGoal);

    // let the subgoal update status;
    public void update();

    // get the status of the goal/ subgoal
    public BooleanProperty getStatusProperty();
}