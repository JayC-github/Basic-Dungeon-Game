package unsw.dungeon;

public class MoveAway implements Movement{
    @Override
	public void move(Player player, Enemy enemy) {
		int currX = enemy.getX();
		int currY = enemy.getY();
		boolean moved = false;
		
		//while the enemy hasn't moved
		while (moved == false) {
			//if the enemy's current x position is less than the player's position
			if (currX < player.getX()) {
				//if the position on the board is empty
				if (player.checkEmpty(currX - 1, currY)) {
					enemy.x().set(currX - 1);
					break;
				}
			
			//if the enemy's current x position is greater than the player's position	
			} else if (currX > player.getX()) {
				//if the position on the board is empty
				if (player.checkEmpty(currX + 1, currY)) {
					enemy.x().set(currX + 1);
					break;
				}
			}
			
			//if the enemy's current y position is less than the player's position	
			if (currY < player.getY()) {
				//if the position on the board is empty
				if (player.checkEmpty(currX, currY-1)) {
					enemy.y().set(currY - 1);
					break;
				}
				
			//if the enemy's current y position is more than the player's position		
			} else if (currY > player.getY()) {
				//if the position on the board is empty
				if (player.checkEmpty(currX, currY + 1)) {
					enemy.y().set(currY + 1);
					break;
				}
			}
			
			moved = true;
		}

	}
}