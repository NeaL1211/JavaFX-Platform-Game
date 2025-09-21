package game;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class maincontroller {
	
	@FXML Button start;
	@FXML Button Quit;
	@FXML Button Guide;
	Stage stage;
	Scene scene;
	
	@FXML Button one;
	@FXML Button two;
	@FXML Button three;
	Image img1;
	Image img2;
	static int get_img = 0;
	
	@FXML Button pause;
	@FXML Button G2;
	
	Image img9 = new Image(getClass().getResourceAsStream("princessR.png"));
	Image img10 = new Image(getClass().getResourceAsStream("princess.png"));
	
	//換到選角介面
	public void start(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("chooseRole.fxml"));
		Parent root = loader.load();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}
	
	public void guide(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("guide.fxml"));
		Parent root = loader.load();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setResizable(false);
		playercontroller plc = loader.getController();

		plc.createPlayer(img9);
		plc.draw();
		
		//按鍵按下後觸發
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {	
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case W:
					Wpress = true;
					if(jump == 0) {
						jump = 1;
					}
					break;
				case A:
					Apress = true;
					break;
				case D:
					Dpress = true;
					break;
				case J:
					Jpress = true;
					if(BulletL_cooldown > 150) {
						plc.setBulletL();
						BulletL_cooldown = 0;
					}
					break;
				case K:
					Kpress = true;
					if(BulletR_cooldown > 150) {
						plc.setBulletR();
						BulletR_cooldown = 0;
					}
					break;
				case R:
					Rpress = true;
					break;
				default:
					break;
				}
			}
		});
		
		//按鍵鬆開後觸發
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {	
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case W:
					Wpress = false; 
					jump = 2;
					break;
				case A:
					Apress = false;
					break;
				case D:
					Dpress = false;
					break;
				case R:
					Rpress = false;
					break;
				default:
					break;
				}
			}
		});
		
		//角色走哪個方向
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				BulletL_cooldown++;
				BulletR_cooldown++;
				if(Wpress == true && jump != 2 ) {
					if(plc.getD() == 1 && (plc.getL() == 1 || plc.getR() == 1 ) && cooldown >= 50 ) {
						plc.escape_jump();
					}
					else if(plc.getD() == 1 && cooldown >= 50) {
						plc.moveup();
					}
					st++;
					if(st >= 25) {
						jump = 2;
					}
				}
				if(Apress == false || Dpress == false) {
					if(Apress == true) {
						plc.createPlayer(img10);
						plc.moveleft();
					}
					if(Dpress == true) {
						plc.createPlayer(img9);
						plc.moveright();
					}
				}
				if((plc.getD() == 0 && jump!=1 )) {
					plc.movedown();
					cooldown = 0;
				}
				else if(jump == 2 && cooldown >= 50) {
					plc.force_movedown();
					cooldown = 0;
				}
				if(plc.getD() == 1) {
					jump = 0;
					st = 0;
					cooldown++;
				}
				if(Jpress == true) {
					plc.shootL();
				}
				if(Kpress == true ) {
					plc.shootR();
				}
				if(Rpress == true) {
					plc.ResetRole();
				}
			}
		};
		timer.start();
		stage.setScene(scene);
		stage.show();
	}
	
	//離開遊戲
	@FXML AnchorPane menu;
	public void Quit(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to logout");
		alert.setContentText("Do you want to save before leaving?");
		
		if(alert.showAndWait().get() == ButtonType.OK) {
			stage = (Stage)menu.getScene().getWindow();
			stage.close();
		}
	}
	
	//選角色
	public void one() {
		img1 = new Image(getClass().getResourceAsStream("LittleNew_right.png"));
		img2 = new Image(getClass().getResourceAsStream("LittleNew_left.png"));
		get_img = 1;
	}
	public void two() {
		img1 = new Image(getClass().getResourceAsStream("cabiR.png"));
		img2 = new Image(getClass().getResourceAsStream("cabiL.png"));
		get_img = 2;
	}
	public void three() {
		img1 = new Image(getClass().getResourceAsStream("oldpeeR.png"));
		img2 = new Image(getClass().getResourceAsStream("oldpeeL.png")); 
		get_img = 3;
	}
	public void four() {
		img1 = new Image(getClass().getResourceAsStream("Dino_right.png"));
		img2 = new Image(getClass().getResourceAsStream("Dino_left.png")); 
		get_img = 4;
	}
	
	//判斷有沒有按按鍵
	boolean Wpress = false, Dpress = false, Apress = false, Jpress = false, Kpress = false, Rpress = false;;
	int jump = 0;
	double st = new Duration(0).toSeconds();
	double cooldown = new Duration(0).toSeconds();
	double BulletL_cooldown = new Duration(0).toSeconds();
	double BulletR_cooldown = new Duration(0).toSeconds();
	
	//開始遊戲
	public void enter(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("map1.fxml"));
		Parent root = loader.load();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root, 1500, 500);
		stage.setResizable(false);
		
		map1collision m1c = loader.getController();
		//嘗試設定角色圖片
		try {
			m1c.createPlayer(img1);
			m1c.draw();
		}catch(NullPointerException e){ //失敗則不變場景
			loader = new FXMLLoader(getClass().getResource("chooseRole.fxml"));
			root = loader.load();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
		//按鍵按下後觸發
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {	
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case W:
					Wpress = true;
					if(jump == 0) {
						jump = 1;
					}
					break;
				case A:
					Apress = true;
					break;
				case D:
					Dpress = true;
					break;
				case J:
					Jpress = true;
					if(BulletL_cooldown > 150) {
						m1c.setBulletL();
						BulletL_cooldown = 0;
					}
					break;
				case K:
					Kpress = true;
					if(BulletR_cooldown > 150) {
						m1c.setBulletR();
						BulletR_cooldown = 0;
					}
					break;
				case R:
					Rpress = true;
					break;
				default:
					break;
				}
			}
		});
		
		//按鍵鬆開後觸發
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {	
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
				case W:
					Wpress = false; 
					jump = 2;
					break;
				case A:
					Apress = false;
					break;
				case D:
					Dpress = false;
					break;
				case R:
					Rpress = false;
					break;
				default:
					break;
				}
			}
		});
		
		//角色走哪個方向
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				BulletL_cooldown++;
				BulletR_cooldown++;
				if(Wpress == true && jump != 2 ) {
					if(m1c.getD() == 1 && (m1c.getL() == 1 || m1c.getR() == 1 ) && cooldown >= 50 ) {
						m1c.escape_jump();
					}
					else if( m1c.getD() == 1 && cooldown >= 50 ) {
						m1c.moveup();
					}
					st++;
					if(st >= 25) {
						jump = 2;
					}
				}
				if(Apress == false || Dpress == false) {
					if(Apress == true) {
						m1c.createPlayer(img2);
						m1c.moveleft();
					}
					if(Dpress == true) {
						m1c.createPlayer(img1);
						m1c.moveright();
					}
				}
				if((m1c.getD() == 0 && jump!=1 )) {
					m1c.movedown();
					cooldown = 0;
				}
				else if(jump == 2 && cooldown >= 50) {
					m1c.force_movedown();
					cooldown = 0;
				}
				if(m1c.getD() == 1) {
					jump = 0;
					st = 0;
					cooldown++;
				}
				if(Jpress == true) {
					m1c.shootL();
				}
				if(Kpress == true ) {
					m1c.shootR();
				}
				if(Rpress == true ) {
					m1c.ResetRole();;
				}
				if(m1c.getGameOver() == true) {
					stage.close();
				}
			}
		};
		timer.start();
		
		stage.setScene(scene);
		stage.show();
	}
	
	static int get_img() {
		return get_img;
	}
	
}