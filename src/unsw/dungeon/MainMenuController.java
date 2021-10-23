package unsw.dungeon;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class MainMenuController {
    @FXML
    private Pane mainMenu;

    @FXML
    private Button playbtm;

    @FXML
    private Button howTobtm;

    @FXML
    private ImageView menuImage;

    @FXML
    public void initialize() throws Exception {
        Image menu = new Image((new File("images/background.jpg")).toURI().toString());
        menuImage.setImage(menu);

    }

    @FXML
    private void handlePlay(ActionEvent event) throws Exception {
        Stage main = (Stage) mainMenu.getScene().getWindow();
        main.close();
        
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Level Menu");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("LevelMenu.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        //root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    
    private void showInstructions() throws IOException {
    	Stage main = (Stage) mainMenu.getScene().getWindow();
		main.close();
		Stage primaryStage = new Stage();
    	primaryStage.setTitle("How to play");
    
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Help.fxml"));
    	Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    @FXML
	public void handleHelp(ActionEvent event) throws IOException {
    	showInstructions();
	}

}