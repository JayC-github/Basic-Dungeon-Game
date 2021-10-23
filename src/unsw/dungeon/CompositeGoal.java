package unsw.dungeon;

import java.util.ArrayList;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
 
public class CompositeGoal implements Goal {
    private String operator;
    private ArrayList<Goal> goals;
    private Dungeon dungeon;
    private BooleanProperty status;

    public CompositeGoal (Dungeon dungeon, String operator) {
        this.dungeon = dungeon;
        this.operator = operator;
        this.goals = new ArrayList<Goal> ();
        this.status = new SimpleBooleanProperty(false);
    }

    @Override
    public boolean completed() {
        // if the logic operator is AND, check if we complete all the subgoals in the list
        if (operator.equals("AND")) {
            for (Goal g: goals) {
                if (!g.completed()) {
                    return false;
                }
            }
            
            return true;
        // if the logic operator is or, check if we complete any one of the subgaols in the list
        } else {
            for (Goal g: goals) {
                if (g.completed()) {
                    return true;
                }
            }        
            
            return false;
        }
    }

    @Override
    public boolean addSubGoal(Goal subGoal) {
        // TODO Auto-generated method stub
        goals.add(subGoal);
        return true;
    }

    // we don't need to remove the subgoal
    @Override
    public boolean removeSubGoal(Goal subGoal) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void update() {
        // update the goals in the list
        for (Goal g: goals) {
            g.update();
        }
        // if we meet the complete requirement
        if (completed()) {
            System.out.println("Complete the goal of the game!");
            status.set(true);
        } else {
            status.set(false);
        }

    }


    @Override
    public BooleanProperty getStatusProperty() {
        return this.status;
    }
    
}