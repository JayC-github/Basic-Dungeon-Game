package unsw.dungeon;

//import javax.swing.Timer;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

import java.util.Timer;
import java.util.TimerTask;

public class Potion extends Entity implements PickUp {
    public Potion(int x, int y) {
        super(x, y);
    }

    @Override
    public Entity pickUp(Player player) {
        System.out.println("Potion");
        if (!player.hasPotion()) {
            // player get the potion
            player.setPotion(this);
            System.out.println("player should be invisible now");
            player.setInvincible(true);
            // set the potion is not visible from the ui
            setIsVisible(false);
            // remove the potion from dungeon
            player.removeEntity(this);
            // eneny will move away from the player
            MoveAway enemyState = new MoveAway();
            player.setEnemyState(enemyState);
            potionEffect(player);
            return this;
        }
        return null;
    }

    // set the state of player effect back to normal after 10 seconds
    public void potionEffect(Player player) {
        /*
        int delay = 10000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //...Perform a task...
                // player back to normal
                System.out.println("player should be not invisible now");
                player.setInvincible(false);
                player.removePotion();
                MoveTowards enemyState = new MoveTowards();
                player.setEnemyState(enemyState);
                setExist(false);
            }
        };
        
        Timer potionTimer = new Timer(delay, taskPerformer);
        potionTimer.setRepeats(false);
        potionTimer.start();
        */

        Timer t = new Timer();
        t.schedule(new TimerTask(){

            @Override
            public void run() {
                System.out.println("player should be not invisible now");
                player.setInvincible(false);
                player.removePotion();
                MoveTowards enemyState = new MoveTowards();
                player.setEnemyState(enemyState);
                setExist(false);
                t.cancel(); // terminate the timer thread
            }
            
        }, 10000);
    }
}