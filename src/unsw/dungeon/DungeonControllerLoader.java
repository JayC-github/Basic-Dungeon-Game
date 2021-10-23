package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image exitImage; 
    private Image keyImage;
    private Image doorImage;
    private Image swordImage;
    private Image enemyImage;
    private Image boulderImage;
    private Image switchImage;
    private Image potionImage;
    private Image treasureImage;
    private Image portalImage;
    private Image magmaImage;
    private Image bowImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image((new File("images/human_new.png")).toURI().toString());
        wallImage = new Image((new File("images/brick_brown_0.png")).toURI().toString());
        // new image
        exitImage = new Image((new File("images/exit.png")).toURI().toString()); 
        keyImage = new Image((new File("images/key.png")).toURI().toString()); 
        doorImage = new Image((new File("images/closed_door.png")).toURI().toString()); 
        swordImage = new Image((new File("images/greatsword_1_new.png")).toURI().toString()); 
        enemyImage = new Image((new File("images/enemy3.png")).toURI().toString()); 
        boulderImage = new Image((new File("images/boulder.png")).toURI().toString()); 
        switchImage = new Image((new File("images/pressure_plate.png")).toURI().toString()); 
        potionImage = new Image((new File("images/brilliant_blue_new.png")).toURI().toString()); 
        treasureImage = new Image((new File("images/treasure.gif")).toURI().toString()); 
        portalImage = new Image((new File("images/portal.png")).toURI().toString());
        magmaImage = new Image((new File("images/magma.gif")).toURI().toString());
        bowImage = new Image((new File("images/bow.png")).toURI().toString());
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }
    @Override
    public void onLoad(Magma magma) {
        ImageView view = new ImageView(magmaImage);
        addEntity(magma, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }

    @Override
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
    }

    @Override
    public void onLoad(Switch fswitch) {
        ImageView view = new ImageView(switchImage);
        addEntity(fswitch, view);
    }
    @Override
    public void onLoad(Potion potion) {
        ImageView view = new ImageView(potionImage);
        addEntity(potion, view);
    }
    @Override
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
    }
    @Override
    public void onLoad(Portal portal) {
        ImageView view = new ImageView(portalImage);
        addEntity(portal, view);
    }

    @Override
    public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        view.setId("key");
        addEntity(key, view);
    }
    
    @Override
    public void onLoad(Door door) {
        ImageView view = new ImageView(doorImage);
        addEntity(door, view);
    }


    @Override
    public void onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
    }

    @Override
    public void onLoad(Bow bow){
        ImageView view = new ImageView(bowImage);
        addEntity(bow, view);
    }

    @Override
    public void onLoad(Enemy enemy) {
        ImageView view = new ImageView(enemyImage);
        addEntity(enemy, view);
    }


    // add the exit
    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }


    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });

        // check if the entity is get picked
        entity.getIsVisible().addListener(e -> {
            if (!entity.getIsVisible().get()) {
                node.setVisible(false);
            } else {
                node.setVisible(true);
            }
        });

        // for enemies
        entity.getExist().addListener(e -> {
            ImageView i = (ImageView) node;
            if (!entity.getExist().get()) {
                i.setImage(null);
            } 
        });


        // for player is invincible
        if (entity instanceof Player) {
            Player p = (Player) entity;
            p.isInvincible().addListener(e -> {
                if (p.isInvincible().get()) {
                    ImageView i = (ImageView) node;
                    i.setImage(new Image((new File("images/gnome.png")).toURI().toString()));
                } else {
                    ImageView i = (ImageView) node;
                    i.setImage(new Image((new File("images/human_new.png")).toURI().toString()));
                }
            });
        }    
        
        // listens for unlocking the door
        if (entity instanceof Door) {
            Door door = (Door) entity;
            door.isOpen().addListener(e -> {
                if(door.isOpen().get()) {
                    ImageView i = (ImageView) node;
                    i.setImage(new Image((new File("images/open_door.png")).toURI().toString()));
                }
            });
        }
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities, super.getlevel());
    }

}
