//期末project
//資工一A 洪佳宇111201029、資工一A 郭拓海111502512

package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class main extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.setTitle("2D Game");
		stage.show();
		
		stage.setOnCloseRequest(event->{
			event.consume();
			logout(stage);
		});
	}
	
	//登出確認視窗
	public void logout(Stage stage) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to logout");
		alert.setContentText("Do you want to save before leaving?");
		
		if(alert.showAndWait().get() == ButtonType.OK) {
			stage.close();
		}
	}
	
	public static void main(String[] args) {launch(args);}
}