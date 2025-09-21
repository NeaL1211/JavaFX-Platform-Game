package game;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class map1collision implements Initializable{
	
	@FXML Rectangle rec;
	@FXML Rectangle rec2;
	@FXML Rectangle rec4;
	@FXML Rectangle rec6;
	@FXML Rectangle rec8;
	@FXML Rectangle rec9;
	@FXML Rectangle rec10;
	@FXML Rectangle rec12;
	@FXML Rectangle rec14;
	@FXML Rectangle rec16;
	@FXML Rectangle rec17;
	@FXML Rectangle rec20;
	@FXML Rectangle rec22;
	@FXML Rectangle rec24;
	@FXML Rectangle rec26;
	@FXML Rectangle rec29;
	@FXML Rectangle rec31;
	@FXML Rectangle rec32;
	@FXML Rectangle rec33;
	@FXML Rectangle rec210;
	@FXML Rectangle rec2101;
	@FXML Rectangle rec21011;
	@FXML Rectangle rec210111;
	@FXML Rectangle bad1;
	@FXML Rectangle bad2;
	@FXML Rectangle bad3;
	@FXML Rectangle h1;
	@FXML Rectangle h2;
	@FXML Rectangle h3;
	@FXML Rectangle fireR;
	@FXML Rectangle fireL;
	@FXML Button nextstage;
	@FXML private double y;
	@FXML private double x;
	Stage stage = new Stage();
	Scene scene;
	Image img3 = new Image(getClass().getResourceAsStream("baad1_right.png"));
	Image img4 = new Image(getClass().getResourceAsStream("baad1_left.png"));
	Image img5 = new Image(getClass().getResourceAsStream("badd2_right.png"));
	Image img6 = new Image(getClass().getResourceAsStream("badd2_left.png"));
	Image img7 = new Image(getClass().getResourceAsStream("badd3_right.png"));
	Image img8 = new Image(getClass().getResourceAsStream("badd3_left.png"));
	Image FH = new Image(getClass().getResourceAsStream("FullHp.png"));
	Image ZH = new Image(getClass().getResourceAsStream("ZeroHp.png"));
	Image fr = new Image(getClass().getResourceAsStream("fire_right.png"));
	Image fl = new Image(getClass().getResourceAsStream("fire_left.png"));
	
	int change1, change2, change3;
	boolean gameover = false, gamepass = false;
	
	//把正方形印上圖案
	int num, findImg;
	Image img9, img10;
	public void createPlayer(Image img) {
		addRec();
		rec.setFill(new ImagePattern(img));
	}
	
	public void draw() {
		h1.setFill(new ImagePattern(FH));
		h2.setFill(new ImagePattern(FH));
		h3.setFill(new ImagePattern(FH));
		fireR.setFill(new ImagePattern(fr));
		fireL.setFill(new ImagePattern(fl));
		fireR.setVisible(false);
		fireL.setVisible(false);
		nextstage.setVisible(false);
	}
	
	//角色走路
	public void moveup () {
		if(UC==5 && D==1) {
			D=0;
			rec.setTranslateY(y-=(range+9));
		}
		else if(U == 0 && D==1 ) {
			D=0;
			rec.setTranslateY(y-=45);
		} 
	}  
	public void moveright() {
		num = 0;
		if(R == 0) {
			L=0;
			rec.setTranslateX(x+=2);
			if(collision == 21) {
				D=0;
			}
		}
	}
	public void moveleft() {
		num = 1;
		if(L==0) {
			R=0;
			rec.setTranslateX(x-=2);
			if(collision == 21) {
				D=0;
			}
		}
	}
	public void movedown() {
	    if(D == 0) {
	    	rec.setTranslateY(y+=3);
	    	U=0;
	    }
	}
	public void force_movedown() {
	    if(D == 0) {
	    	rec.setTranslateY(y+=45);
	    	U=0;
	    }
	}
	public void escape_jump() {
		if(U == 0 && D==1) {
			D=L=R=0;
			rec.setTranslateY(y-=15);
		}
	}
	
	double x4,x5, y4, y5;
	boolean BulletL_Stop , BulletR_Stop ;
	public void setBulletR() {
		x4=0;y4=0;
		fireR.setTranslateX(x4-=fireR.getLayoutX());
		fireR.setTranslateX(x4+=(rec.getLayoutX()+rec.getTranslateX()+rec.getWidth()));
		fireR.setTranslateY(y4-=fireR.getLayoutY());
		fireR.setTranslateY(y4+=(rec.getLayoutY()+rec.getTranslateY()+rec.getHeight()/2-10));
	}
	public void setBulletL() {
		x5=0;y5=0;
		fireL.setTranslateX(x5-=fireL.getLayoutX());
		fireL.setTranslateX(x5+=rec.getLayoutX()+rec.getTranslateX()-fireL.getWidth());
		fireL.setTranslateY(y5-=fireL.getLayoutY());
		fireL.setTranslateY(y5+=(rec.getLayoutY()+rec.getTranslateY()+rec.getHeight()/2-10));
	}
	public void shootR() {
		fireR.setVisible(true);
		fireR.setTranslateX(x4+=3);
		for(int i=0; i<20; i++) {
			BulletCollision(rec, rec_collect.get(i), fireR);
		}
		if(fireR.getLayoutX()+fireR.getTranslateX()-rec.getLayoutX()-rec.getTranslateX()-rec.getWidth()>350) {
			fireR.setLayoutY(y4+=1000);
			}
		if(fireR.getBoundsInParent().intersects(bad1.getBoundsInParent())) {
			bad1.setVisible(false);
			bad1.setLayoutY(y1-=1000);
			fireR.setLayoutY(y4+=1000);
		}
		else if(fireR.getBoundsInParent().intersects(bad2.getBoundsInParent())) {
			bad2.setVisible(false);
			bad2.setLayoutY(y2-=1000);
			fireR.setLayoutY(y4+=1000);
		}
		else if(fireR.getBoundsInParent().intersects(bad3.getBoundsInParent())) {
			bad3.setVisible(false);
			bad3.setLayoutY(y3-=1000);
			fireR.setLayoutY(y4+=1000);
		}
	}
	
	public void shootL() {
		fireL.setVisible(true);
		fireL.setTranslateX(x5-=3);
		for(int i=0; i<20; i++) {
			BulletCollision(rec, rec_collect.get(i), fireL);
		}
		if(rec.getLayoutX()+rec.getTranslateX()-fireL.getLayoutX()-fireL.getTranslateX()-fireL.getWidth()>350) {
			fireL.setLayoutY(y5+=1000);
		}
		if(fireL.getBoundsInParent().intersects(bad1.getBoundsInParent())) {
			bad1.setVisible(false);
			bad1.setLayoutY(y1-=1000);
			fireL.setLayoutY(y5+=1000);
		}
		else if(fireL.getBoundsInParent().intersects(bad2.getBoundsInParent())) {
			bad2.setVisible(false);
			bad2.setLayoutY(y2-=1000);
			fireL.setLayoutY(y5+=1000);
		}
		else if(fireL.getBoundsInParent().intersects(bad3.getBoundsInParent())) {
			bad3.setVisible(false);
			bad3.setLayoutY(y3-=1000);
			fireL.setLayoutY(y5+=1000);
		}
	}
	
	ArrayList<Rectangle> rec_collect = new ArrayList<Rectangle>();
	public void addRec() {
		rec_collect.add(rec4);rec_collect.add(rec6);rec_collect.add(rec8);rec_collect.add(rec9);rec_collect.add(rec10);rec_collect.add(rec12);rec_collect.add(rec14);
		rec_collect.add(rec16);rec_collect.add(rec17);rec_collect.add(rec20);rec_collect.add(rec22);rec_collect.add(rec24);rec_collect.add(rec29);
		rec_collect.add(rec31);rec_collect.add(rec32);rec_collect.add(rec2);rec_collect.add(rec210);rec_collect.add(rec2101);rec_collect.add(rec21011);rec_collect.add(rec210111);
	}
	
	double x1,x2,x3, y1, y2, y3;
	int heartNum = 3;
	AnimationTimer collisionTimer = new AnimationTimer() {
		@Override 
		public void handle(long timestamp) {
				count = 0;
				collision = 0;
				
				for(int i=0; i<20; i++) {
					checkCollision(rec, rec_collect.get(i), rec2101, rec21011, rec29, rec24);
				}
				if(count == 0 && collision == 20) {
					D=0;
				}
				
				if(change1 == 0) {
					bad1.setTranslateX(x1+=1);
					bad1.setFill(new ImagePattern(img3));
					if(bad1.getLayoutX()+bad1.getTranslateX()+bad1.getWidth()>414) {
						change1 = 1;
					}
				}
				else if(change1 == 1) {
					bad1.setTranslateX(x1-=1);
					bad1.setFill(new ImagePattern(img4));
					if(bad1.getLayoutX()+bad1.getTranslateX()<191) {
						change1 = 0;
					}
				}
				if(change2 == 0) {
					bad2.setTranslateX(x2+=1);
					bad2.setFill(new ImagePattern(img5));
					if(bad2.getLayoutX()+bad2.getTranslateX()+bad2.getWidth()>953) {
						change2 = 1;
					}
				}
				else if(change2 == 1) {
					bad2.setTranslateX(x2-=1);
					bad2.setFill(new ImagePattern(img6));
					if(bad2.getLayoutX()+bad2.getTranslateX()<566) {
						change2 = 0;
					}
				}
				if(change3 == 0) {
					bad3.setTranslateX(x3+=1);
					bad3.setFill(new ImagePattern(img7));
					if(bad3.getLayoutX()+bad3.getTranslateX()+bad3.getWidth()>1463) {
						change3 = 1;
					}
				}
				else if(change3 == 1) {
					bad3.setTranslateX(x3-=1);
					bad3.setFill(new ImagePattern(img8));
					if(bad3.getLayoutX()+bad3.getTranslateX()<1064) {
						change3 = 0;
					}
				}
			
				try {
					touchBad(bad1);
				} catch (IOException e) {
				}
				try {
					touchBad(bad2);
				} catch (IOException e) {
				}
				try {
					touchBad(bad3);
				} catch (IOException e) {
				}
				
				if(rec.getBoundsInParent().intersects(rec33.getBoundsInParent())) {
					TranslateTransition go = new TranslateTransition();
					go.setNode(rec);
					go.setDuration(Duration.millis(150));
					go.setToX(835);
					go.setCycleCount(1);
					go.play();
					gamepass = true;
					nextstage.setVisible(true);
				}
		}
	};
	
	int touchCooldown = 0;
	public void touchBad(Rectangle R) throws IOException {
		touchCooldown++;
		if(touchCooldown > 1000 && rec.getBoundsInParent().intersects(R.getBoundsInParent())) {
			touchCooldown = 0;
			switch(heartNum) {
			case 3:
				h3.setFill(new ImagePattern(ZH));
				heartNum--;
				break;
			case 2:
				h2.setFill(new ImagePattern(ZH));
				heartNum--;
				break;
			case 1:
				h1.setFill(new ImagePattern(ZH));
				heartNum--;
				gameover = true;
				gameover(stage);
			}
			if(R.getLayoutX()+R.getTranslateX() > rec.getLayoutX()+rec.getTranslateX()) {
				rec.setTranslateX(x-=40);
				if(R.equals(bad1)) {
					R.setTranslateX(x1+=40);
				}
				else if(R.equals(bad2)) {
					R.setTranslateX(x2+=40);
				}
				else {
					R.setTranslateX(x3+=40);
				}
			}
			else {
				rec.setTranslateX(x+=40);
				if(R.equals(bad1)) {
					R.setTranslateX(x1-=40);
				}
				else if(R.equals(bad2)) {
					R.setTranslateX(x2-=40);
				}
				else {
					R.setTranslateX(x3-=40);
				}
			}
			rec.setTranslateY(y-=15);
			FadeTransition a = new FadeTransition();
			a.setCycleCount(8);
			a.setNode(rec);
			a.setByValue(10);
			a.setToValue(0.1);
			a.setAutoReverse(true);
			a.setDuration(Duration.millis(250));
			a.play();
			
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourcebundle) {
		collisionTimer.start();
	}
	
	public int D, R, L, U, UC, count, collision;
	public double range = 0; 
	public boolean  g;
	public void checkCollision(Rectangle rec, Rectangle collisionShape, Rectangle rec2101, Rectangle rec21011, Rectangle rec29, Rectangle rec24) {
		double aMaxX=0, bMaxX=0, aMinX=0, bMinX=0, aMaxY=0, aMinY=0, bMaxY=0, bMinY=0;
		aMaxX = rec.getLayoutX() + rec.getWidth() + rec.getTranslateX();
		aMinX = rec.getLayoutX() + rec.getTranslateX();
		bMaxX = collisionShape.getLayoutX() + collisionShape.getWidth();
		bMinX = collisionShape.getLayoutX();
		
		//Y越大在越下面
		aMinY = rec.getLayoutY() + rec.getTranslateY();
		aMaxY = rec.getLayoutY() + rec.getHeight() + rec.getTranslateY();
		bMinY = collisionShape.getLayoutY();
		bMaxY = collisionShape.getLayoutY() + collisionShape.getHeight();
		
		double r2101i = rec2101.getLayoutY();
		double r2101a = rec2101.getLayoutY() + rec2101.getHeight();
		double r21011i = rec21011.getLayoutY();
		double r21011a = rec21011.getLayoutY() + rec21011.getHeight();
		double r29i = rec29.getLayoutY();
		double r29a = rec29.getLayoutY() + rec29.getHeight();
		double r24i = rec24.getLayoutY();
		double r24a = rec24.getLayoutY() + rec24.getHeight();
		
		//跳了之後會不會超出地形
		range = 0;
		if((950<aMaxX&&aMaxX<1055||950<aMinX&&aMinX<1055) && r2101i < aMinY && aMinY-45 < r2101a){// a在下
			UC=5;
			range = aMinY - r2101a;
		}
		else if((113<aMaxX&&aMaxX<148||113<aMinX&&aMinX<148) && r21011i < aMinY && aMinY-45 < r21011a){// a在下
			UC=5;
			range = aMinY - r21011a;
		}
		else if((953<aMaxX&&aMaxX<1010||953<aMinX&&aMinX<1010) && r29i < aMinY && aMinY-45 < r29a ){// a在下
			UC=5;
			range = aMinY - r29a;
		}
		else if((1010<aMaxX&&aMaxX<1416||1010<aMinX&&aMinX<1416) && r24i < aMinY && aMinY-45 < r24a){// a在下
			UC=5;
			range = aMinY - r24a;
		}
		else {
			UC=0;
		}
		
		//有沒有碰撞
		if(rec.getBoundsInParent().intersects(collisionShape.getBoundsInParent())) {
			if((bMaxX - aMinX <1 && bMinY - aMaxY<1) || (aMaxX - bMinX<1 && aMinY - bMaxY<1) || (aMaxX - bMinX<1 && aMinY - bMaxY<1) || (bMaxX - aMinX<1 && bMinY - aMaxY<1)) {
				L=R=0;
			}
			else {
				if(aMinY < bMinY && bMinY < aMaxY && bMinY-aMinY>rec.getHeight()-3.5){// b在下
					if(((bMaxX-1.2>aMaxX && aMaxX>bMinX+1.2) || (bMaxX-1.2>aMinX && aMinX>bMinX+1.2))) {
						D=1;
					}else {
						D=0;
					}
					U=0;
				}
				if(aMaxX >= 1500 || !(bMaxX>aMaxX && aMinX>bMinX) && bMaxX > aMaxX && aMaxX > bMinX){// a在左
					if(aMaxY-bMinY<=2.0) {
						R=0;
					}else {
						R=2;
					}
				}
				if(aMinX <= 0 || (bMaxX-aMinX>1.5 && !(aMaxX>bMaxX && bMinX>aMinX) && aMaxX > bMaxX && bMaxX > aMinX)){// b在左
					if(aMaxY-bMinY<=2.0) {
						L=0;
					}else {
						L=3;
					}
				}
				
				if(bMinY < aMinY && aMinY < bMaxY ){// a在下
					U=4;
				}
			}
			count++;
		}
		else {
			collision ++;
		}
	}
	
	//子彈的碰撞
	public void BulletCollision(Rectangle rec, Rectangle collisionShape, Rectangle fire) {
		//有沒有碰撞
		if(fire.getBoundsInParent().intersects(collisionShape.getBoundsInParent())) {
			if(fire.equals(fireR)) {
				fireR.setTranslateY(y4+=1000);
			}
			else {
				fireL.setTranslateY(y5+=1000);
			}
		}
	}
	
	//GAMEOVER
	public void gameover(Stage stage) throws IOException{
		try {
            Parent root2 = FXMLLoader.load(getClass().getResource("gameover.fxml"));
            scene = new Scene(root2);
            stage.setScene(scene);
            stage.setTitle("GameOver");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void logout(Stage stage) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to logout");
		alert.setContentText("Do you want to save before leaving?");
		
		if(alert.showAndWait().get() == ButtonType.OK) {
			stage.close();
		}
	}
	
	boolean Wpress = false, Dpress = false, Apress = false, Kpress = false, Jpress = false, Rpress = false;
	int jump = 0;
	double st = new Duration(0).toSeconds();
	double cooldown = new Duration(0).toSeconds();
	@FXML Button one;
	@FXML Button two;
	@FXML Button three;
	double BulletL_cooldown = new Duration(0).toSeconds();
	double BulletR_cooldown = new Duration(0).toSeconds();
	
	public void gamepass(ActionEvent event) throws IOException {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("map2.fxml"));
			Parent root = loader.load();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root, 1500, 500);
			stage.setResizable(false);
			
			map2collision m2c = loader.getController();
			
			if(maincontroller.get_img() == 1) {
				img9 = new Image(getClass().getResourceAsStream("LittleNew_right.png"));
				img10 = new Image(getClass().getResourceAsStream("LittleNew_left.png"));
			}
			else if(maincontroller.get_img() == 2) {
				img9 = new Image(getClass().getResourceAsStream("cabiR.png"));
				img10 = new Image(getClass().getResourceAsStream("cabiL.png"));
			}
			else if(maincontroller.get_img() == 3) {
				img9 = new Image(getClass().getResourceAsStream("oldpeeR.png"));
				img10 = new Image(getClass().getResourceAsStream("oldpeeL.png"));
			}
			else if(maincontroller.get_img() == 4) {
				img9 = new Image(getClass().getResourceAsStream("Dino_right.png"));
				img10 = new Image(getClass().getResourceAsStream("Dino_left.png")); 
			}

			m2c.createPlayer(img9);
			m2c.draw();
			
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
							m2c.setBulletL();
							BulletL_cooldown = 0;
						}
						break;
					case K:
						Kpress = true;
						if(BulletR_cooldown > 150) {
							m2c.setBulletR();
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
						if(m2c.get_D() == 1 && (m2c.get_L() == 1 || m2c.get_R() == 1 ) && cooldown >= 50 ) {
							m2c.escape_jump();
						}
						else if(m2c.get_D() == 1 && cooldown >= 50) {
							m2c.moveup();
						}
						st++;
						if(st >= 25) {
							jump = 2;
						}
					}
					if(Apress == false || Dpress == false) {
						if(Apress == true) {
							m2c.createPlayer(img10);
							m2c.moveleft();
						}
						if(Dpress == true) {
							m2c.createPlayer(img9);
							m2c.moveright();
						}
					}
					if((m2c.get_D() == 0 && jump!=1 )) {
						m2c.movedown();
						cooldown = 0;
					}
					else if(jump == 2 && cooldown >= 50) {
						m2c.force_movedown();
						cooldown = 0;
					}
					if(m2c.get_D() == 1) {
						jump = 0;
						st = 0;
						cooldown++;
					}
					if(Jpress == true) {
						m2c.shootL();
					}
					if(Kpress == true ) {
						m2c.shootR();
					}
					if(m2c.get_GameOver() == true) {
						stage.close();
					}
					if(Rpress == true) {
						m2c.ResetRole();
					}
				}
			};
			timer.start();
			stage.setScene(scene);
			stage.show();
		}
	
	public void ResetRole() {
		rec.setTranslateX(x-=rec.getTranslateX());
		rec.setTranslateY(y-=rec.getTranslateY());
		R=L=U=0;
		FadeTransition a = new FadeTransition();
		a.setCycleCount(8);
		a.setNode(rec);
		a.setByValue(10);
		a.setToValue(0.1);
		a.setAutoReverse(true);
		a.setDuration(Duration.millis(250));
		a.play();
	}
	
	public int getD() {return D;}
	public int getL() {return L;}
	public int getR() {return R;}
	public boolean getGameOver() {return gameover;}
	public boolean getGamePass() {return gamepass;}
}

