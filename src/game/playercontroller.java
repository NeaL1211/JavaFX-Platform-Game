package game;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class playercontroller implements Initializable{
	@FXML Rectangle rec;
	@FXML Rectangle floor;
	@FXML Rectangle fireL;
	@FXML Rectangle fireR;
	@FXML Label label;
	@FXML Button BackToMenu;
	Scene scene;
	Stage stage = new Stage();
	
	boolean gamepass = false, BulletL_Stop , BulletR_Stop;
	double x,y,x1,x2,x3,x4,x5,y1,y2,y3,y4,y5,x6,y6,x7,y7,y8,x8,y9,y10,y11,y12,y13,y14;
	public int d, r, l, u, uc, count, collision, touch_r1 = 0;
	public double range = 0;
	int aa, ww, dd, jj, kk, rr;
	Image fr = new Image(getClass().getResourceAsStream("fire_right.png"));
	Image fl = new Image(getClass().getResourceAsStream("fire_left.png"));
	
	public void createPlayer(Image img) {
		rec.setFill(new ImagePattern(img));
	}
	
	public void draw() {
		fireR.setFill(new ImagePattern(fr));
		fireL.setFill(new ImagePattern(fl));
		fireR.setVisible(false);
		fireL.setVisible(false);
	}
	
	
	public void moveup () {
		if(u == 0 && d==1 ) {
			d=0;
			rec.setTranslateY(y-=45);
			ww++;
		} 
	}  
	public void moveright() {
		if(r == 0) {
			rec.setTranslateX(x+=2);
			l=0;
			if(collision == 21) {
				d=0;
			}
			dd++;
		}
	}
	public void moveleft() {
		if(l==0) {
			rec.setTranslateX(x-=2);
			r=0;
			if(collision == 21) {
				d=0;
			}
			aa++;
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
		kk++;
		fireR.setVisible(true);
		fireR.setTranslateX(x4+=3);
		if(fireR.getLayoutX()+fireR.getTranslateX()-rec.getLayoutX()-rec.getTranslateX()-rec.getWidth()>350) {
			fireR.setLayoutY(y4+=1000);
			}
	}
	
	public void shootL() {
		jj++;
		fireL.setVisible(true);
		fireL.setTranslateX(x5-=3);
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
	
	AnimationTimer collisionTimer = new AnimationTimer() {
		@Override 
		public void handle(long timestamp) {
				checkCollision(rec, floor);
				if(rr >= 1) {
					label.setText("You,ve done the tutorial ! !");
				}
				else if(kk >= 1) {
					label.setText("Press R to reset Role");
				}
				else if(jj >= 1) {
					label.setText("Press K to split fire to right");
				}
				else if(ww >= 2) {
					label.setText("Press J to split fire to left");
				}
				else if(aa >= 5) {
					label.setText("Press W to move up");
				}
				else if(dd >= 5) {
					label.setText("Press A to move left");
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
		rr++;
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		collisionTimer.start();
	}
	
	public int getD() {return d;}
	public int getL() {return l;}
	public int getR() {return r;}
}
	


	
	

