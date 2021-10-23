package unsw.dungeon;

public class Enemy extends Entity {
    // movement moveTowards or moveAway
    private Movement state;
    
    public Enemy(int x, int y) {
		super(x, y);
		this.state = new MoveTowards();
	}

	// change the movement state of the enemy
	public void setMovement(Movement enemyState) {
		this.state = enemyState;
	}
	
	//
	public void move(Player p) {
		state.move(p, this);
	}
	public void killEnemy(Player p) {
		p.removeEntity(this);
		// enemy died
		setExist(false);
	}


}