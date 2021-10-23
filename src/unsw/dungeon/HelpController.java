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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelpController {
	@FXML
	private Pane instructions;
	
	@FXML 
	private Button backButton;

	@FXML
	private ImageView exit;
	@FXML
	private ImageView door;
	@FXML
	private ImageView portal;
	@FXML
	private ImageView floorswitch;
	@FXML
	private ImageView boulder;
	@FXML
	private ImageView treasure;
	@FXML
	private ImageView key;
	@FXML
	private ImageView sword;
	@FXML
	private ImageView potion;
	@FXML
	private ImageView enemy;
	@FXML
	private ImageView space;
	@FXML
	private ImageView arrowkey;
	@FXML
	private ImageView magma;
	@FXML
	private ImageView bow;
	
	@FXML
	public void initialize() throws Exception {
		Image exitimage = new Image((new File("images/exit.png").toURI().toString()));
		exit.setImage(exitimage);
		Image boulderimage = new Image((new File("images/boulder.png").toURI().toString()));
		boulder.setImage(boulderimage);
		Image doorimage = new Image((new File("images/closed_door.png").toURI().toString()));
		door.setImage(doorimage);
		Image treasureimage = new Image((new File("images/gold_pile.png").toURI().toString()));
		treasure.setImage(treasureimage);
		Image swordimage = new Image((new File("images/greatsword_1_new.png").toURI().toString()));
		sword.setImage(swordimage);
		Image portalimage = new Image((new File("images/portal.png").toURI().toString()));
		portal.setImage(portalimage);
		Image potionimage = new Image((new File("images/brilliant_blue_new.png").toURI().toString()));
		potion.setImage(potionimage);
		Image keyimage = new Image((new File("images/key.png").toURI().toString()));
		key.setImage(keyimage);
		Image switchimage = new Image((new File("images/pressure_plate.png").toURI().toString()));
		floorswitch.setImage(switchimage);
		Image enemyimage = new Image((new File("images/deep_elf_master_archer.png").toURI().toString()));
		enemy.setImage(enemyimage);
		Image arrowkeyiImage = new Image((new File("images/arrowkey.png").toURI().toString()));
		arrowkey.setImage(arrowkeyiImage);
		Image spaceImage = new Image((new File("images/space.png").toURI().toString()));
		space.setImage(spaceImage);
		Image magmaImage = new Image((new File("images/magma.gif").toURI().toString()));
		magma.setImage(magmaImage);
		Image bowImage = new Image((new File("images/bow.png").toURI().toString()));
		bow.setImage(bowImage);
	}
	
	public void backtoMain() throws IOException {
    	Stage helpStage = (Stage) instructions.getScene().getWindow();
		helpStage.close();
		
		Stage primaryStage = new Stage();
    	primaryStage.setTitle("Main Menu");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	@FXML
	public void backtoScreen(ActionEvent event) throws IOException {
		backtoMain();
	}
}