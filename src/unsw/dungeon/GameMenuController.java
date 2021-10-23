package unsw.dungeon;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameMenuController {
    @FXML
	private Pane pane;
    
    @FXML
	private Button mainbtm;

	@FXML
    private Button exitbtm;
    
    @FXML
    private Button restartbtm;

    @FXML
    private ImageView menuImage;

    @FXML
    private ImageView gameStatus;

    // the game we need to close
    private Pane gamePane;

    // the string level we need to restart
    private String level;

    @FXML
    public void initialize() throws Exception {
        Image menu = new Image((new File("images/gameComplete.png")).toURI().toString());
        BackgroundImage bImage = new BackgroundImage(menu, null, null, BackgroundPosition.CENTER, new BackgroundSize(500, 500, false, false, false, false));
        pane.setBackground(new Background(bImage));
    }
    
    
    @FXML
	void handleExit(ActionEvent event) {
		System.exit(0);
    }
    
    @FXML
	void handleReturnMain(ActionEvent event) throws IOException {
        Stage game = (Stage) gamePane.getScene().getWindow();
        game.close();
        
        Stage level = (Stage) pane.getScene().getWindow();
        level.close();
        
        Stage primaryStage = new Stage();
		primaryStage.setTitle("Main Menu");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
	void handleReturnLevel(ActionEvent event) throws IOException {
        Stage game = (Stage) gamePane.getScene().getWindow();
        game.close();
        
        Stage level = (Stage) pane.getScene().getWindow();
        level.close();
        
        Stage primaryStage = new Stage();
		primaryStage.setTitle("Level Menu");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LevelMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @FXML
    private void handleRestart(ActionEvent event) throws Exception{
        Stage game = (Stage) gamePane.getScene().getWindow();
        game.close();

        Stage level = (Stage) pane.getScene().getWindow();
        level.close();
        
        Stage primaryStage = new Stage();

        // set the title name of the restart game
        String newgame = this.level.substring(0, this.level.indexOf("."));
        primaryStage.setTitle(newgame);

        // String choose = LevelMenuController.getChoose();
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(this.level);
        

        DungeonController controller = dungeonLoader.loadController();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Pane getGamePane() {
        return gamePane;
    }

    public void setGamePane(Pane gamePane) {
        this.gamePane = gamePane;
    }

}