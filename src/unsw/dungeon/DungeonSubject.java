package unsw.dungeon;

public interface DungeonSubject {
	public void registerObserver(EntityObserver o);
	public void removeObserver(EntityObserver o);
	public void notifyObservers();
}
