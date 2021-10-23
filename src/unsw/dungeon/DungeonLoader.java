package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    // get the file name of the dungeon
    private String level;


    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
        this.level = filename;
    }

    public String getlevel() {
        return level;
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }

        JSONObject jsonGoals = json.getJSONObject("goal-condition");

        Goal goals = loadGoal(dungeon, jsonGoals);
        dungeon.setGoal(goals);
        
        return dungeon;
    }

    // load the goals in the json file, it's like a tree structure
    private Goal loadGoal(Dungeon dungeon, JSONObject json) {
        Goal goals = null;
        // if the goal is the logic operator 'AND' or 'OR', which means it's a composite goal
        if (json.getString("goal").equals("AND") || json.getString("goal").equals("OR")) {
            goals = new CompositeGoal(dungeon, json.getString("goal"));
            JSONArray subgoals = json.getJSONArray("subgoals");
            for (int i = 0; i < subgoals.length(); i++) {
                JSONObject subgoal = subgoals.getJSONObject(i);
                goals.addSubGoal(loadGoal(dungeon, subgoal));
            }
        // simple goal
        } else {
            String goal = json.getString("goal");
            switch(goal) {
                case "exit":
                    goals = new ExitGoal(dungeon);
                    break;
                case "enemies":
                    goals = new EnemyGoal(dungeon);
                    break;
                case "boulders":
                    goals = new BoulderGoal(dungeon);
                    break;
                case "treasure":
                    goals = new TreasureGoal(dungeon);
                    break;
            }
        }

        return goals;
    }


    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        int id;

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        // TODO Handle other possible entities
        case "key":
            id = json.getInt("id");
            Key key = new Key(x,y,id);
            onLoad(key);
            entity = key;
            break;
        case "door":
            id = json.getInt("id");
            Door door = new Door(x,y,id);
            onLoad(door);
            entity = door;
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit);
            entity = exit;
            break;
        case "sword":
        	Sword sword = new Sword(x,y);
        	onLoad(sword);
        	entity = sword;
        	break;
        case "enemy":
        	Enemy enemy = new Enemy(x,y);
        	onLoad(enemy);
        	entity = enemy;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(x,y);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "switch":
        	Switch fswitch = new Switch(x,y);
        	onLoad(fswitch);
        	entity = fswitch;
        	break;
        case "invincibility":
        	Potion potion = new Potion(x,y);
        	onLoad(potion);
        	entity = potion;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x,y);
        	onLoad(treasure);
        	entity = treasure;
            break;
        case "portal":
            id = json.getInt("id");
        	Portal portal = new Portal(x,y,id);
        	onLoad(portal);
        	entity = portal;
        	break;
        case "magma":
            Magma magma = new Magma(x,y);
            onLoad(magma);
            entity = magma;
            break;
        case "bow":
            Bow bow = new Bow(x, y);
            onLoad(bow);
            entity = bow;
            break;
        }
        
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    // TODO Create additional abstract methods for the other entities
    public abstract void onLoad(Key key);
    public abstract void onLoad(Door door);
    public abstract void onLoad(Exit exit);
    public abstract void onLoad(Sword sword);
    public abstract void onLoad(Enemy enemy);
    public abstract void onLoad(Boulder boulder);
    public abstract void onLoad(Switch fswitch);
    public abstract void onLoad(Potion potion);
    public abstract void onLoad(Treasure treasure);
    public abstract void onLoad(Portal portal);
    public abstract void onLoad(Magma magma);
    public abstract void onLoad(Bow bow);

}
