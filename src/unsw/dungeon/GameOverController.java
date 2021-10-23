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

public class GameOverController {
    @FXML
	private Button returnButton;

	@FXML
	private Pane pane;

	@FXML
    private Button exitButton;
    
    @FXML
    private Button restartbtm;

    @FXML
    private ImageView menuImage;

    @FXML
    private ImageView gameover;

    @FXML
    private ImageView youwin;

    // the string level we need to restart
    private String level;
    
    // the game status
    private boolean completed;
    


    @FXML
    public void initialize() throws Exception {
        Image menu = new Image((new File("images/gameComplete.png")).toURI().toString());
        BackgroundImage bImage = new BackgroundImage(menu, null, null, BackgroundPosition.CENTER, new BackgroundSize(470, 400, false, false, false, false));
        pane.setBackground(new Background(bImage));

        if (completed) {
            Image youwinImage = new Image((new File("images/you_win.png")).toURI().toString());
            youwin.setImage(youwinImage);
        } else {
            Image gameoverImage = new Image((new File("images/gg.gif")).toURI().toString());
            gameover.setImage(gameoverImage);
        }
    }
    
    @FXML
	void handleExitButton(ActionEvent event) {
		System.exit(0);
    }
    
    @FXML
	void handleReturnButton(ActionEvent event) throws IOException {
        Stage level = (Stage) pane.getScene().getWindow();
        level.close();
        
        Stage primaryStage = new Stage();
		primaryStage.setTitle("MainMenu");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void handleRestart(ActionEvent event) throws Exception{

        Stage level = (Stage) pane.getScene().getWindow();
        level.close();
        
        Stage primaryStage = new Stage();

        // set the title name of the restart game
        String game = this.level.substring(0, this.level.indexOf("."));
        primaryStage.setTitle(game);

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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}