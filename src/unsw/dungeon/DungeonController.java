package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    // try to add a button-> the menu has restart, level, exit
    @FXML
    private Button menubtm;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    // for restart the same game
    private String level;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, String level) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.level = level;
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());
        // menu button
        Image MenuButton = new Image((new File("images/brick_brown_0.png")).toURI().toString());
        ImageView menu = new ImageView(MenuButton);
        menubtm.setGraphic(menu);
        

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        // add a new Button
        // menubtm = new Button("Menu");
        // squares.add(menubtm, dungeon.getWidth()-3, dungeon.getHeight()-1);
        GameCompleteListener(dungeon.getGoal());
        PlayerStatusListener(dungeon.getPlayer());
    }

    // check if the goal of the dungeon is completed or not
    private void GameCompleteListener(Goal goal) {
        goal.getStatusProperty().addListener(e -> {
            // if the goal is completed
            if (goal.getStatusProperty().get()) {
                try {
                    Stage dungeon = (Stage) squares.getScene().getWindow();
                    dungeon.close();
                    GameOver(true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

        });
    }



    // have a new screen for the main menu
    public void GameOver(boolean gameStatus) throws Exception {
        // System.out.println("?????????????????????");
        Stage primaryStage = new Stage();
        primaryStage.setTitle("GameOver Menu");

        GameOverController controller = new GameOverController();
        controller.setLevel(level);
        controller.setCompleted(gameStatus);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOver.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        // root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // check if the player is die
    private void PlayerStatusListener(Player player) {
        player.isAlive().addListener(e -> {
            if (!player.isAlive().get()) {
                try {
                    Stage dungeon = (Stage) squares.getScene().getWindow();
                    dungeon.close();
                    GameOver(false);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
			}

    	});
    }

    @FXML
	public void handleMenu(ActionEvent event) throws IOException {
        //Stage level = (Stage) squares.getScene().getWindow();
        //level.close();
        
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Game Menu");

        GameMenuController controller = new GameMenuController();
        controller.setLevel(level);
        controller.setGamePane(squares);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameMenu.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        // root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        // space key for pick up/drop entities/open the door
        case SPACE:
            player.interact();
            break; 
        default:
            break;
        }
    }

    


}

