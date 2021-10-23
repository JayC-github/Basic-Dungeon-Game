package unsw.dungeon;

/// movement strategy
public interface Movement {
    public void move(Player player, Enemy enemy);
}