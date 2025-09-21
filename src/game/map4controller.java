package game;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Application;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class map4controller implements Initializable {
	@FXML Rectangle rec;
	@FXML Rectangle r1;
	@FXML Rectangle r2;
	@FXML Rectangle r3;
	@FXML Rectangle r4;
	@FXML Rectangle r5;
	@FXML Rectangle r6;
	@FXML Rectangle r7;
	@FXML Rectangle r8;
	@FXML Rectangle r9;
	@FXML Rectangle floor;
	@FXML Rectangle door;
	@FXML Rectangle fireL;
	@FXML Rectangle fireR;
	@FXML Rectangle princess;
	@FXML Label L1;
	@FXML Label L2;
	@FXML Button BackToMenu;
	Scene scene;
	Stage stage = new Stage();
	Image fr = new Image(getClass().getResourceAsStream("fire_right.png"));
	Image fl = new Image(getClass().getResourceAsStream("fire_left.png"));
	Image dc = new Image(getClass().getResourceAsStream("door_close.png"));
	Image dopen = new Image(getClass().getResourceAsStream("door_open.png"));
	Image pcs = new Image(getClass().getResourceAsStream("princess.png"));
	
	boolean gamepass = false, BulletL_Stop , BulletR_Stop;
	double x,y,x1,x2,x3,x4,x5,y1,y2,y3,y4,y5,x6,y6,x7,y7,y8,x8,y9,y10,y11,y12,y13,y14;
	public int d, r, l, u, uc, count, collision, touch_r1 = 0;
	public double range = 0;
	
	ArrayList<Rectangle> rec_collect = new ArrayList<Rectangle>();
	public void addRec() {
		rec_collect.add(floor);rec_collect.add(r1);
	}
	int num;
	public void createPlayer(Image img) {
		rec.setFill(new ImagePattern(img));
	}
	
	public void draw() {
		addRec();
		fireR.setFill(new ImagePattern(fr));
		fireL.setFill(new ImagePattern(fl));
		fireR.setVisible(false);
		fireL.setVisible(false);
		door.setFill(new ImagePattern(dopen));
		BackToMenu.setVisible(false);
		princess.setFill(new ImagePattern(pcs));
	}
	
	//角色走路
	public void moveup () {
		if(uc==5 && d==1) {
			d=0;
			rec.setTranslateY(y-=(range+9));
		}
		else if(u == 0 && d==1 ) {
			d=0;
			rec.setTranslateY(y-=45);
		} 
	}  
	public void moveright() {
		num = 0;
		if(r == 0) {
			rec.setTranslateX(x+=2);
			l=0;
			if(collision == 21) {
				d=0;
			}
		}
	}
	public void moveleft() {
		num = 1;
		if(l==0) {
			rec.setTranslateX(x-=2);
			r=0;
			if(collision == 21) {
				d=0;
			}
		}
	}
	public void movedown() {
	    if(d == 0) {
	    	rec.setTranslateY(y+=3);
	    	u=0;
	    }
	}
	public void force_movedown() {
	    if(d == 0) {
	    	rec.setTranslateY(y+=45);
	    	u=0;
	    }
	}
	public void escape_jump() {
		if(u == 0 && d==1) {
			d=l=r=0;
			rec.setTranslateY(y-=15);
		}
	}
	
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
		for(int i=0; i<rec_collect.size(); i++) {
			BulletCollision(rec_collect.get(i), fireR);
		}
		if(fireR.getLayoutX()+fireR.getTranslateX()-rec.getLayoutX()-rec.getTranslateX()-rec.getWidth()>350) {
			fireR.setLayoutY(y4+=1000);
			}
	}
	
	public void shootL() {
		fireL.setVisible(true);
		fireL.setTranslateX(x5-=3);
		for(int i=0; i<rec_collect.size(); i++) {
			BulletCollision(rec_collect.get(i), fireL);
		}
		if(rec.getLayoutX()+rec.getTranslateX()-fireL.getLayoutX()-fireL.getTranslateX()-fireL.getWidth()>350) {
			fireL.setLayoutY(y5+=1000);
		}
	}
	
	public void checkCollision(Rectangle rec, Rectangle collisionShape) {
		double aMaxX=0, bMaxX=0, aMinX=0, bMinX=0, aMaxY=0, aMinY=0, bMaxY=0, bMinY=0;
		aMaxX = rec.getLayoutX() + rec.getWidth() + rec.getTranslateX();
		aMinX = rec.getLayoutX() + rec.getTranslateX();
		bMaxX = collisionShape.getLayoutX() + collisionShape.getWidth() + collisionShape.getTranslateX();
		bMinX = collisionShape.getLayoutX() + collisionShape.getTranslateX();
		
		//Y越大在越下面
		aMinY = rec.getLayoutY() + rec.getTranslateY();
		aMaxY = rec.getLayoutY() + rec.getHeight() + rec.getTranslateY();
		bMinY = collisionShape.getLayoutY() + collisionShape.getTranslateY();;
		bMaxY = collisionShape.getLayoutY() + collisionShape.getHeight() + collisionShape.getTranslateY();;
		
		if(rec.getBoundsInParent().intersects(collisionShape.getBoundsInParent())) {
			if((bMaxX - aMinX <1 && bMinY - aMaxY<1) || (aMaxX - bMinX<1 && aMinY - bMaxY<1) || (aMaxX - bMinX<1 && aMinY - bMaxY<1) || (bMaxX - aMinX<1 && bMinY - aMaxY<1)) {
				l=r=0;
			}
			else {
				if(aMinY < bMinY && bMinY < aMaxY && bMinY-aMinY>rec.getHeight()-3.5){// b在下
					if(((bMaxX-1.2>aMaxX && aMaxX>bMinX+1.2) || (bMaxX-1.2>aMinX && aMinX>bMinX+1.2))) {
						d=1;
					}else {
						d=0;
					}
					u=0;
				}
				if(aMaxX >= 1500 || !(bMaxX>aMaxX && aMinX>bMinX) && bMaxX > aMaxX && aMaxX > bMinX){// a在左
					if(aMaxY-bMinY<=2.0) {
						r=0;
					}else {
						r=2;
					}
				}
				if(aMinX <= 0 || (bMaxX-aMinX>1.5 && !(aMaxX>bMaxX && bMinX>aMinX) && aMaxX > bMaxX && bMaxX > aMinX)){// b在左
					if(aMaxY-bMinY<=2.0) {
						l=0;
					}else {
						l=3;
					}
				}
				if(bMinY < aMinY && aMinY < bMaxY ){// a在下
					u=4;
				}
			}
			count++;
		}
		else {
			collision ++;
		}
	}
	
	//子彈的碰撞
	public void BulletCollision(Rectangle collisionShape, Rectangle fire) {
		//有沒有碰撞
		if(fire.getBoundsInParent().intersects(collisionShape.getBoundsInParent())) {
			if(fire.equals(fireR)) {
				fireR.setTranslateY(y4+=1000);
				touch_r1++;
			}
		}
	}
	
	AnimationTimer collisionTimer = new AnimationTimer() {
		@Override 
		public void handle(long timestamp) {
				count = 0;
				collision = 0;
				
				for(int i=0; i<rec_collect.size(); i++) {
					checkCollision(rec, rec_collect.get(i));
				}
				if(count == 0 && collision == rec_collect.size()) {
					d=0;
				}
				if(rec.getTranslateX()>350) {
					L1.setText("謝謝大家");
					L2.setText("新鮮的肝在此跟大家道別");
				}
				
				if(rec.getBoundsInParent().intersects(door.getBoundsInParent())) {
					door.setFill(new ImagePattern(dopen));
				}
				else {
					door.setFill(new ImagePattern(dc));
				}
				
				BulletCollision(r1,fireR);
				if(touch_r1 == 3) {
					r1.setTranslateY(y2-=3000);
					r2.setVisible(false);
					r3.setVisible(false);
					r4.setVisible(false);
					r5.setVisible(false);
					r6.setVisible(false);
					r7.setVisible(false);
					r8.setVisible(false);
					r9.setVisible(false);
				}
				
				if(r2.isVisible() == false && princess.getTranslateY()+216<397) {
					princess.setTranslateY(y3+=1);
				}
				
				if(rec.getBoundsInParent().intersects(princess.getBoundsInParent())) {
					BackToMenu.setVisible(true);
				}
		}
	};
	
	public void ResetRole() {
		rec.setTranslateX(x-=rec.getTranslateX());
		rec.setTranslateY(y-=rec.getTranslateY());
		r=l=u=0;
		FadeTransition a = new FadeTransition();
		a.setCycleCount(8);
		a.setNode(rec);
		a.setByValue(10);
		a.setToValue(0.1);
		a.setAutoReverse(true);
		a.setDuration(Duration.millis(250));
		a.play();
	}

	
	public void btm(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setResizable(false);
		stage.setScene(scene);
		stage.setTitle("2D Game");
		stage.show();
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		collisionTimer.start();
	}
	
	public int getD() {return d;}
	public int getL() {return l;}
	public int getR() {return r;}
	public boolean getGamePass() {return gamepass;}
}

