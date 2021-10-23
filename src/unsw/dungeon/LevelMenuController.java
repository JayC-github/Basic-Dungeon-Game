package unsw.dungeon;

import java.io.File;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LevelMenuController {
    @FXML
    Pane levelMenu;
    @FXML
    private ImageView menuImage;

    @FXML
    public void initialize() throws Exception {
        Image menu = new Image((new File("images/background.jpg")).toURI().toString());
        menuImage.setImage(menu);

    }

    // check the level
    private String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    
    @FXML
    private void handleEasy() throws Exception {
        Stage level = (Stage) levelMenu.getScene().getWindow();
        level.close();
        
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Maze Dungeon");

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json");
        
        
        DungeonController controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void handleMedium() throws Exception {
        Stage level = (Stage) levelMenu.getScene().getWindow();
        level.close();
        
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Boulders Dungeon");

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("boulders.json");

        DungeonController controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @FXML
    private void handleHard() throws Exception {
        Stage level = (Stage) levelMenu.getScene().getWindow();
        level.close();
        
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Hard Dungeon");

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("hard.json");
        
        DungeonController controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void handlehell() throws Exception {
        Stage level = (Stage) levelMenu.getScene().getWindow();
        level.close();
        
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Hell Dungeon");

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("hell.json");
        
        
        DungeonController controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void handleBackToMain() throws Exception {
        Stage level = (Stage) levelMenu.getScene().getWindow();
        level.close();

        Stage primaryStage = new Stage();
    	primaryStage.setTitle("Main Menu");
    	      
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
    	Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}