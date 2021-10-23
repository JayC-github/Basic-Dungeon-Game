package unsw.dungeon;

public class Boulder extends Entity {
    public Boulder(int x, int y) {
        super(x, y);
    }
    
	// movement of the boulder according to which direction the player try to move
	// entity b.move
	public void move(Player player, Entity entity) {
		int x = player.getX();
		int y = player.getY();
		String move = player.getMove();
		if (samePosition(x, y)) {
			if (move == "up") {
				y = y - 1;
				y().set(y);
			} else if (move == "down") {
				y = y + 1;
				y().set(y);
			} else if (move == "left") {
				x = x - 1;
				x().set(x);
			} else {
				x = x + 1;
				x().set(x);
			}
		}
	}
}