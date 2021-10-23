/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon implements DungeonSubject {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private List<EntityObserver> listobservers;
    // the goal of the dungeon
    private Goal goals;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.goals = null;
        this.listobservers = new ArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGoal(Goal goal) {
        this.goals = goal;
    }

    public Goal getGoal() {
        return goals;
    }

    // check if the goal of dungeon is finished
    // if the goals complete, return true,
    // if the goals is no finish or there is no goal, return false
    public boolean updateGoals() {
        if (goals!= null) {
            goals.update();
            return goals.completed();
        }

        return false;
    }

    @Override
    public void notifyObservers() {
        for (EntityObserver obs: listobservers) {
            obs.update(player);
        }

        // since we cannot delete the enemies by update observers
        // check here to see if player can kill enemy
        Enemy killedEnemy = null;
        
        for (Entity e: entities) {
            if (e instanceof Enemy && e.samePosition(player.getX(), player.getY())) {
                killedEnemy = (Enemy) e;
            }

        }
        

        // if we have some enemy need to kill, then kill and remove the enemy from the list
        if (killedEnemy != null) {
            player.touchEnemy(killedEnemy);
        }


        // since we cannot remove the entitis by update observers
        // check here to see if the magma destroy the entity
        Entity destroyed = null;
        for(Entity e: entities) {
            if (e instanceof Magma) {
                int magmax = e.getX();
                int magmay = e.getY();
                for(Entity ee: entities) {
                    // for entity is not magma, destroy it
                    if (!(ee instanceof Magma)) {
                        if (ee.getX() == magmax && ee.getY() == magmay) {
                            ee.setIsVisible(false);
                            ee.setExist((false));
                            destroyed = ee;
                        }
                    }
                }
            }
        }    
        
        removeEntity(destroyed);

    }

    @Override
    public void registerObserver(EntityObserver o) {
        listobservers.add(o);
    }

    @Override
    public void removeObserver(EntityObserver o) {
        listobservers.remove(o);
    }

    // checks if the player is able to move to the place, (x,y) is the place player try to move to
    public boolean checkLocation(String move, int x, int y) {
        Entity entity = getEntity(x,y);
        
        // check if x,y not only has switch but also has boulder
        for (Entity e: entities) {
            if (e.samePosition(x, y) && e instanceof Boulder) {
                entity = (Boulder) e;
            }

        }
        
        // if the entity is a wall
        if (entity instanceof Wall) {
            return false;
        }

        // if the entity is a boulder        
        if (entity instanceof Boulder) {
            if (entity.samePosition(x, y) && !checkBoulder(x, y, move)) {
    			return false;
    		}
        }
        
        // if the entity is a door
        if (entity instanceof Door) {
            // if the door is locked, just like the wall
            Door d = (Door) entity;
            if (!d.DoorOpen()) {
                return false;
            }
        }

        return true;
    }
    
    // check if the boulder can be move in a particular direction to the new sqaure
    public boolean checkBoulder(int x, int y, String move) {
		//calculates and checks the new position of the boulder
    	if (move == "up") {
			y = y - 1;
			if (!freeBoulderSquare(x, y)) {
				return false;
			}
		} else if (move == "down") {
			y = y + 1;
			if (!freeBoulderSquare(x, y)) {
				return false;
			}
		} else if (move == "left") {
			x = x - 1;
			if (!freeBoulderSquare(x, y)) {
				return false;
			}
		} else {
			x = x + 1;
			if (!freeBoulderSquare(x, y)) {
				return false;
			}
		}
    	return true;
    }


    // check if the new square try to move to is empty
    public boolean freeBoulderSquare(int x, int y) {
    	// checks the boulder remains on the board
    	if (x >= width || x < 0 || y >= height || y < 0) {
    		return false;
        }

        Entity entity = getEntity(x,y);
        
        // check if it has another boulder on this square
        for (Entity e: entities) {
            if (e.samePosition(x, y) && e instanceof Boulder) {
                entity = (Boulder) e;
            }
        }

        // if it's a boulder,portal, wall or enemy, block the boulder
        if (entity instanceof Boulder || entity instanceof Wall || entity instanceof Enemy || entity instanceof Portal) {
            return false;
        }
        

        // if it's a locked door, block the boulder
        if (entity instanceof Door) {
            Door d = (Door) entity;
            // if the door is locked
            if (!d.DoorOpen()) {
                return false;
            }
        }
   
        return true;
    }


    // get the boulder from a given location
    public Boulder getBoulder(int x, int y) {
    	Entity entity = getEntity(x,y);
        if (entity instanceof Boulder) {
            return (Boulder) entity;
        }
    	return null;
    }
    
    // get all the boulders in the dungeon
    private List <Boulder> getBoulders() {
    	List <Boulder> boulders = new ArrayList<Boulder>();
    	for (Entity e : entities) {
    		if (e instanceof Boulder) {
    			boulders.add((Boulder) e);
    		}
    	}
    	return boulders;
    }
    
    // get all the switches in the dungeon
    private List <Switch> getSwitches() {
    	List <Switch> switches = new ArrayList<Switch>();
    	for (Entity e : entities) {
    		if (e instanceof Switch) {
    			switches.add((Switch) e);
    		}
    	}
    	return switches;
    }
    
    // updates the status all the switches
    public void updateSwitches() {
    	List <Boulder> boulders = getBoulders();
    	List <Switch> switches = getSwitches();
    	for (Switch s : switches) {
    		boolean check = false;
    		for (Boulder b : boulders) {
    			if (b.samePosition(s.getX(), s.getY())) {
    				s.setTriggered(true);
    				check = true;
    				break;
    			}
    		}
    		if (check == false) {
    			s.setTriggered(false);
    		}
    	}
    }
    
    // whether all the floor switched are triggerd
    public boolean allTriggeredSwitches() {
    	for (Entity e: entities) {
    		if (e instanceof Switch) {
    			if (!((Switch)e).isTriggered()) {
    				return false;
    			}
    		}
    	}
    	return true;
    }

    // check the corresponding portal with the same id
    public Portal locatePortal(int id) {
    	for (Entity e: entities) {
    		if (e != null && e instanceof Portal && !e.samePosition(player.getX(), player.getY()) && e.checkId(id)) {
                System.out.print(id);
    			return (Portal)e;
    		}
    	}
    	return null;
    }

    // check if a square is empty for an entity to move to
    // (x,y) is the entity try to move to
    public boolean freeSquare(int x, int y) {
        // out of the dungeon
        if (x >= width || x < 0 || y >= height || y < 0) 
    		return false;
    	for (Entity e : entities) {
            // if the entity is wall/Boulder/Portal, block the entity(enemy)
            if ((e instanceof Wall) || (e instanceof Boulder) || (e instanceof Portal)) {
	    		if (e.samePosition(x, y)) {
	    			return false;
	    		}
            }
            
            // if the entity is a locked door, block the entity, if not, let the entity pass.
            if (e instanceof Door) {
                Door d = (Door) e;
                if (e.samePosition(x, y) && !d.DoorOpen()) {
                    return false;
                }
            }
    	}
        
        return true;
    }

    // get entity at (x,y)
    public Entity getEntity(int x, int y) {
        for (Entity e: entities) {
            if (e != null && e.getX() == x && e.getY() == y) {
                return e;
            }
        }
        return null;
    }

    // get entities list for dungeonloader goal
    public List<Entity> getEntities() {
        return entities;
    }
    
    // when player drop the entity, add the entity to the dungeon
    public void addEntity(Entity entity) {
        entities.add(entity);
        registerObserver((EntityObserver)entity);
    }

    // when player pickup the entity, remove the entity from the dungeon
    public void removeEntity(Entity entity) {
        entities.remove(entity);
        //observer
        removeObserver(entity);
    }

    // finds the door with the matching id to a key
    public Door DoorByKey(int keyid) {
        for (Entity e: entities) {
            if (e instanceof Door) {
                Door d = (Door) e;
                if (keyid == d.getId()) {
                    return d;
                }
            }
        }
        return null; 
    }

    public PickUp pickUpEntity(int x, int y) {
        for (Entity e: entities) {
            if (e.samePosition(x, y) && (e instanceof Potion || e instanceof Sword || e instanceof Key || e instanceof Treasure || e instanceof Bow)) {
                return (PickUp) e;
            }
        }
    
        return null;    
    }
    
    // find the enemy next to the player
    public Enemy findEnemy() {
        for (Entity enemy: entities) {
            if (enemy instanceof Enemy && player.checkAdjacent(enemy.getX(), enemy.getY())) {
                return (Enemy) enemy;
            }

        }
        
        return null;
    }

    // find the enemy next to the player
    public Enemy findRadomEnemy() {
        for (Entity enemy: entities) {
            if (enemy instanceof Enemy) {
                return (Enemy) enemy;
            }
        }      
        return null;
    }

    // set the movement of all the enemies on the board depent on if the player has potion
    public void setEnemyState(Movement enemyState) {
    	for (Entity enemy : entities) {
    		if (enemy instanceof Enemy) {
    			((Enemy) enemy).setMovement(enemyState);
    		}
    	}
    }

    // check if there are no enemies left
    public boolean killAllEnemy() {
        for(Entity entity: entities) {
            if (entity instanceof Enemy) {
                return false;
            }
        }
        return true;
    }

    // check if all treasure are collected
    public boolean collectAllTreausre() {
        for(Entity entity: entities) {
            if (entity instanceof Treasure) {
                return false;
            }
        }
        return true;
    }

    // check if the player go through any of the exits in the dungeon
    public boolean reachExit() {
        for (Entity e : entities) {
            // if the entity is an exit and player is in the same position
            if (e instanceof Exit && e.samePosition(player.getX(), player.getY())) {
                return true;
            }
        }
        return false;
    }

}
