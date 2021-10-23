package unsw.dungeon;

public class Switch extends Entity{
    private boolean triggered;
    public Switch(int x, int y) {
        super(x, y);
        triggered = false;
    }

    public boolean isTriggered() {
        return triggered;
    }

    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    } 
    
}