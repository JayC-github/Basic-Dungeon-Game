package unsw.dungeon;

public class Portal extends Entity{
    private int id;
    
    public Portal(int x, int y, int id) {
		super(x, y);
		this.id = id;
		System.out.println(id);
	}
	
	public int getId() {
		return this.id;
	}
	
	// p.teleport(player) entity, teleport the player to the correspondig portal
	public void teleport(Player p) {
		Portal correspond = p.locatePortal(this.id);
		int correspondX = correspond.getX();
		int correspondY = correspond.getY();
		if (correspond != null) {
			if (p.checkEmpty(correspondX + 1,correspondY)) {
				p.teleportPlayer(correspondX  + 1,correspondY);
			} else if (p.checkEmpty(correspondX  - 1, correspondY)) {
				p.teleportPlayer(correspondX  - 1 ,correspondY);
			} else if (p.checkEmpty(correspondX , correspondY + 1)) {
				p.teleportPlayer(correspondX ,correspondY + 1);
			} else if (p.checkEmpty(correspondX , correspondY - 1)) {
				p.teleportPlayer(correspondX ,correspondY - 1);
			} 	
		}		
	}
	
	@Override
	public boolean checkId(int q) {
		return (this.id == q);
	}

}