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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;

public class map2collision implements Initializable {

	@FXML Rectangle recc;
	@FXML Rectangle r1;
	@FXML Rectangle r2;//隱藏
	@FXML Rectangle r3;
	@FXML Rectangle r4;
	@FXML Rectangle r5;
	@FXML Rectangle r6;
	@FXML Rectangle r7;
	@FXML Rectangle r8;
	@FXML Rectangle r9;//移動
	@FXML Rectangle r10;
	@FXML Rectangle r11;
	@FXML Rectangle r12;
	@FXML Rectangle r13;
	@FXML Rectangle r14;
	@FXML Rectangle r15;
	@FXML Rectangle r16;
	@FXML Rectangle r17;//壞人
	@FXML Rectangle r18;
	@FXML Rectangle r19;
	@FXML Rectangle r20;
	@FXML Rectangle r21;//移動
	@FXML Rectangle r81;
	@FXML Rectangle rec231;//移動
	@FXML Rectangle rec232;//移動
	@FXML Rectangle rec233;//移動
	@FXML Rectangle key;
	@FXML Rectangle r2s;//隱藏
	@FXML Rectangle fireR;
	@FXML Rectangle fireL;
	@FXML Rectangle h1;
	@FXML Rectangle h2;
	@FXML Rectangle h3;
	@FXML Label label;
	@FXML Rectangle star;
	@FXML Rectangle s1;
	@FXML Rectangle s2;
	@FXML Rectangle s3;
	@FXML Rectangle s4;
	@FXML Rectangle s5;
	@FXML Rectangle s6;
	@FXML Rectangle s7;
	@FXML Rectangle s8;
	@FXML Rectangle s9;
	@FXML Rectangle s10;
	@FXML Rectangle s11;
	@FXML Rectangle s12;
	@FXML Rectangle s13;
	@FXML Rectangle s14;
	@FXML Rectangle rrr;
	@FXML Button nextstage1;
	Stage stage = new Stage();
	Scene scene;
	@FXML ImageView keynum;
	Image img3 = new Image(getClass().getResourceAsStream("baad1_right.png"));
	Image img4 = new Image(getClass().getResourceAsStream("baad1_left.png"));
	Image img5 = new Image(getClass().getResourceAsStream("badd2_right.png"));
	Image img6 = new Image(getClass().getResourceAsStream("badd2_left.png"));
	Image img7 = new Image(getClass().getResourceAsStream("badd3_right.png"));
	Image img8 = new Image(getClass().getResourceAsStream("badd3_left.png"));
	
	@FXML private double y;
	@FXML private double x;
	Image FH = new Image(getClass().getResourceAsStream("FullHp.png"));
	Image ZH = new Image(getClass().getResourceAsStream("ZeroHp.png"));
	Image fr = new Image(getClass().getResourceAsStream("fire_right.png"));
	Image fl = new Image(getClass().getResourceAsStream("fire_left.png"));
	Image sr = new Image(getClass().getResourceAsStream("star.png"));
	Image k = new Image(getClass().getResourceAsStream("key.png"));
	
	int change1, change2, change3, change4;
	int heartNum = 3;
	boolean gameover = false, gamepass = false;
	
	ArrayList<Rectangle> rec_collect = new ArrayList<Rectangle>();
	ArrayList<Rectangle> spike_collect = new ArrayList<Rectangle>();
	public void addRec() {
		rec_collect.add(r1);rec_collect.add(r2);rec_collect.add(r3);rec_collect.add(r4);rec_collect.add(r5);rec_collect.add(r6);rec_collect.add(r7);
		rec_collect.add(r8);rec_collect.add(r9);rec_collect.add(r10);rec_collect.add(r11);rec_collect.add(r12);rec_collect.add(r13);
		rec_collect.add(r14);rec_collect.add(r15);rec_collect.add(r16);rec_collect.add(r18);rec_collect.add(r19);rec_collect.add(r20);
		rec_collect.add(r21);rec_collect.add(r81);
		
		spike_collect.add(s1);spike_collect.add(s2);spike_collect.add(s3);spike_collect.add(s4);spike_collect.add(s5);spike_collect.add(s6);spike_collect.add(s7);
		spike_collect.add(s8);spike_collect.add(s9);spike_collect.add(s10);spike_collect.add(s11);spike_collect.add(s12);spike_collect.add(s13);
		spike_collect.add(s14);
	}
	
	//把正方形印上圖案
	int num;
	public void createPlayer(Image img) {
		recc.setFill(new ImagePattern(img));
	}
	
	public void draw() {
		addRec();
		h1.setFill(new ImagePattern(FH));
		h2.setFill(new ImagePattern(FH));
		h3.setFill(new ImagePattern(FH));
		fireR.setFill(new ImagePattern(fr));
		fireL.setFill(new ImagePattern(fl));
		fireR.setVisible(false);
		fireL.setVisible(false);
		key.setFill(new ImagePattern(k));
	}
	
	//角色走路
	public void moveup () {
		if(uc==5 && d==1) {
			d=0;
			recc.setTranslateY(y-=(range+9));
		}
		else if(u == 0 && d==1 ) {
			d=0;
			recc.setTranslateY(y-=45);
		} 
	}  
	public void moveright() {
		num = 0;
		if(r == 0) {
			l=0;
			recc.setTranslateX(x+=2);
			if(collision == 21) {
				d=0;
			}
		}
	}
	public void moveleft() {
		num = 1;
		if(l==0) {
			r=0;
			recc.setTranslateX(x-=2);
			if(collision == 21) {
				d=0;
			}
		}
	}
	public void movedown() {
	    if(d == 0) {
	    	recc.setTranslateY(y+=3);
	    	u=0;
	    }
	}
	public void force_movedown() {
	    if(d == 0) {
	    	recc.setTranslateY(y+=45);
	    	u=0;
	    }
	}
	public void escape_jump() {
		if(u == 0 && d==1) {
			d=l=r=0;
			recc.setTranslateY(y-=15);
		}
	}
	
	double x1,x2,x3,x4,x5,y1,y2,y3,y4,y5,x6,y6,x7,y7,y8,x8,y9,y10,y11;
	boolean BulletL_Stop , BulletR_Stop ;
	int touch_r20 = 0;
	public void setBulletR() {
		x4=0;y4=0;
		fireR.setTranslateX(x4-=fireR.getLayoutX());
		fireR.setTranslateX(x4+=(recc.getLayoutX()+recc.getTranslateX()+recc.getWidth()));
		fireR.setTranslateY(y4-=fireR.getLayoutY());
		fireR.setTranslateY(y4+=(recc.getLayoutY()+recc.getTranslateY()+recc.getHeight()/2-10));
	}
	public void setBulletL() {
		x5=0;y5=0;
		fireL.setTranslateX(x5-=fireL.getLayoutX());
		fireL.setTranslateX(x5+=recc.getLayoutX()+recc.getTranslateX()-fireL.getWidth());
		fireL.setTranslateY(y5-=fireL.getLayoutY());
		fireL.setTranslateY(y5+=(recc.getLayoutY()+recc.getTranslateY()+recc.getHeight()/2-10));
	}
	public void shootR() {
		fireR.setVisible(true);
		fireR.setTranslateX(x4+=3);
		for(int i=0; i<rec_collect.size(); i++) {
			BulletCollision(recc, rec_collect.get(i), fireR);
		}
		if(fireR.getLayoutX()+fireR.getTranslateX()-recc.getLayoutX()-recc.getTranslateX()-recc.getWidth()>350) {
			fireR.setLayoutY(y4+=1000);
			}
		if(fireR.getBoundsInParent().intersects(r17.getBoundsInParent())) {
			r17.setVisible(false);
			r17.setLayoutY(y1-=1000);
			fireR.setLayoutY(y4+=1000);
		}
	}
	
	public void shootL() {
		fireL.setVisible(true);
		fireL.setTranslateX(x5-=3);
		for(int i=0; i<rec_collect.size(); i++) {
			BulletCollision(recc, rec_collect.get(i), fireL);
		}
		if(recc.getLayoutX()+recc.getTranslateX()-fireL.getLayoutX()-fireL.getTranslateX()-fireL.getWidth()>350) {
			fireL.setLayoutY(y5+=1000);
		}
		if(fireL.getBoundsInParent().intersects(r17.getBoundsInParent())) {
			r17.setVisible(false);
			r17.setLayoutY(y1-=1000);
			fireL.setLayoutY(y5+=1000);
		}
	}
	
	public int d, r, l, u, uc, count, collision;
	public double range = 0; 
	public boolean  getKey=false;
	public void checkCollision(Rectangle rec, Rectangle collisionShape, Rectangle r13, Rectangle r20, Rectangle rrr) {
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
		
		double r13i = r13.getLayoutY();
		double r13a = r13.getLayoutY() + r13.getHeight();
		double r20i = r20.getLayoutY();
		double r20a = r20.getLayoutY() + r20.getHeight();
		double rrri = rrr.getLayoutY();
		double rrra = rrr.getLayoutY() + rrr.getHeight();
		
		//跳了之後會不會超出地形
		range = 0;
		if((918<aMaxX&&aMaxX<948||918<aMinX&&aMinX<948) && r13i < aMinY && aMinY-45 < r13a){// a在下
			uc=5;
			range = aMinY - r13a;
		}
		else if((113<aMaxX&&aMaxX<148||113<aMinX&&aMinX<148) && r20i < aMinY && aMinY-45 < r20a){// a在下
			uc=5;
			range = aMinY - r20a;
		}
		else if((861<aMaxX&&aMaxX<1116||861<aMinX&&aMinX<1116) && rrri < aMinY && aMinY-45 < rrra ){// a在下
			uc=5;
			range = aMinY - rrra;
		}
		else {
			uc=0;
		}
		
		//有沒有碰撞
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
	
	
	AnimationTimer ct = new AnimationTimer() {
		@Override 
		public void handle(long timestamp) {
				count = 0;
				collision = 0;
				
				for(int i=0; i<rec_collect.size(); i++) {
					checkCollision(recc, rec_collect.get(i), r13, r20, rrr);
				}
				if(count == 0 && collision == rec_collect.size()) {
					d=0;
				}
				
				if(change1 == 0) {
					r17.setTranslateX(x1+=1);
					r17.setFill(new ImagePattern(img3));
					if(r17.getLayoutX()+r17.getTranslateX()+r17.getWidth()>1061) {
						change1 = 1;
					}
				}
				else if(change1 == 1) {
					r17.setTranslateX(x1-=1);
					r17.setFill(new ImagePattern(img4));
					if(r17.getLayoutX()+r17.getTranslateX()<830) {
						change1 = 0;
					}
				}
				if(change2 == 0) {
					r9.setTranslateX(x2+=1);
					rec232.setTranslateX(x8+=1);
					if(recc.getBoundsInParent().intersects(r9.getBoundsInParent())) {
						recc.setTranslateX(x+=1);
					}
					if(r9.getLayoutX()+r9.getTranslateX()+r9.getWidth()>1099) {
						change2 = 1;
					}
				}
				else if(change2 == 1) {
					r9.setTranslateX(x2-=1);
					rec232.setTranslateX(x8-=1);
					if(recc.getBoundsInParent().intersects(r9.getBoundsInParent())) {
						recc.setTranslateX(x-=1);
					}
					if(r9.getLayoutX()+r9.getTranslateX()<871) {
						change2 = 0;
					}
				}
				if(change3 == 0) {
					r21.setTranslateY(y6+=1);
					rec233.setTranslateY(y9+=1);
					if(recc.getBoundsInParent().intersects(r21.getBoundsInParent())) {
						recc.setTranslateY(y+=1);
					}
					if(r21.getLayoutY()+r21.getTranslateY()+r21.getHeight()>450) {
						change3 = 1;
					}
				}
				else if(change3 == 1) {
					r21.setTranslateY(y6-=1);
					rec233.setTranslateY(y9-=1);
					if(recc.getBoundsInParent().intersects(r21.getBoundsInParent())) {
						recc.setTranslateY(y-=1);
					}
					if(r21.getLayoutY()+r21.getTranslateY()<343) {
						change3 = 0;
					}
				}
				if(change4 == 0) {
					rec231.setTranslateY(y8+=1);
					r6.setTranslateY(y7+=1);
					if(recc.getBoundsInParent().intersects(r6.getBoundsInParent())) {
						recc.setTranslateY(y+=1);
					}
					if(r6.getLayoutY()+r6.getTranslateY()+r6.getHeight()>353) {
						change4 = 1;
					}
				}
				else if(change3 == 1) {
					rec231.setTranslateY(y8-=1);
					r6.setTranslateY(y7-=1);
					if(recc.getBoundsInParent().intersects(r6.getBoundsInParent())) {
						recc.setTranslateY(y-=1);
					}
					if(r6.getLayoutY()+r6.getTranslateY()<157) {
						change4 = 0;
					}
				}
			
				try {
					touchBad(r17);
				} catch (IOException e) {
				}
				
				try {
					OutOfMap();
				} catch (IOException e) {
				}
		
				
				if(recc.getBoundsInParent().intersects(r20.getBoundsInParent()) && touch_r20 == 0) {
					label.setText("!");
					star.setVisible(true);
					star.setFill(new ImagePattern(sr));
					touch_r20++;
				}
				if(star.isVisible() == true) {
					star.setTranslateX(x3+=0.5);
					if(!(star.getBoundsInParent().intersects(r20.getBoundsInParent())) && !(star.getBoundsInParent().intersects(r1.getBoundsInParent()))) {
						star.setTranslateY(y3+=1);
					}
					if(star.getLayoutY()+star.getTranslateY()+star.getHeight()>500) {
						star.setVisible(false);
						r2.setTranslateY(y10-=82);
						r2s.setTranslateY(y11-=82);
						r2.setVisible(true);
						r2s.setVisible(true);
					}
				}
				
				if(recc.getBoundsInParent().intersects(key.getBoundsInParent())) {
					key.setVisible(false);
					getKey = true;
					keynum.setImage(new Image(getClass().getResourceAsStream("1.png")));
				}
				
				if(recc.getBoundsInParent().intersects(r19.getBoundsInParent()) && getKey == true) {
					TranslateTransition go = new TranslateTransition();
					go.setNode(recc);
					go.setDuration(Duration.millis(150));
					go.setToX(1310);
					go.setCycleCount(1);
					go.play();
					gamepass = true;
					nextstage1.setVisible(true);
				}
		}
	};
	
	public void OutOfMap() throws IOException{
		if(recc.getLayoutY()+recc.getTranslateY()+recc.getHeight()>500) {
			recc.setTranslateX(x-=recc.getTranslateX());
			recc.setTranslateY(y-=recc.getTranslateY());
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
			FadeTransition a = new FadeTransition();
			a.setCycleCount(8);
			a.setNode(recc);
			a.setByValue(10);
			a.setToValue(0.1);
			a.setAutoReverse(true);
			a.setDuration(Duration.millis(250));
			a.play();
			l=r=u=0;
		}
		for(int i=0; i<spike_collect.size(); i++) {
			if(recc.getBoundsInParent().intersects(spike_collect.get(i).getBoundsInParent())) {
				recc.setTranslateX(x-=recc.getTranslateX());
				recc.setTranslateY(y-=recc.getTranslateY());
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
				FadeTransition a = new FadeTransition();
				a.setCycleCount(8);
				a.setNode(recc);
				a.setByValue(10);
				a.setToValue(0.1);
				a.setAutoReverse(true);
				a.setDuration(Duration.millis(250));
				a.play();
				break;
			}
		}
	}
	public void ResetRole() {
		recc.setTranslateX(x-=recc.getTranslateX());
		recc.setTranslateY(y-=recc.getTranslateY());
		r=l=u=0;
		FadeTransition a = new FadeTransition();
		a.setCycleCount(8);
		a.setNode(recc);
		a.setByValue(10);
		a.setToValue(0.1);
		a.setAutoReverse(true);
		a.setDuration(Duration.millis(250));
		a.play();
	}
	
	int touchCooldown = 0;
	public void touchBad(Rectangle R) throws IOException {
		touchCooldown++; 
		if(touchCooldown > 1000 && recc.getBoundsInParent().intersects(R.getBoundsInParent())) {
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
			
			if(R.getLayoutX()+R.getTranslateX() > recc.getLayoutX()+recc.getTranslateX()) {
				recc.setTranslateX(x-=40);
				if(R.equals(r17)) {
					R.setTranslateX(x1+=40);
				}
			}
			else {
				recc.setTranslateX(x+=40);
				if(R.equals(r17)) {
					R.setTranslateX(x1-=40);
				}
			}
			recc.setTranslateY(y-=15);
			FadeTransition a = new FadeTransition();
			a.setCycleCount(8);
			a.setNode(recc);
			a.setByValue(10);
			a.setToValue(0.1);
			a.setAutoReverse(true);
			a.setDuration(Duration.millis(250));
			a.play();
		}
	}
	
	boolean Wpress = false, Dpress = false, Apress = false, Kpress = false, Jpress = false, Rpress = false;
	int jump = 0;
	double st = new Duration(0).toSeconds();
	double cooldown = new Duration(0).toSeconds();
	@FXML Button one;
	@FXML Button two;
	@FXML Button three;
	Image img11, img12;
	double BulletL_cooldown = new Duration(0).toSeconds();
	double BulletR_cooldown = new Duration(0).toSeconds();
	
	public void go_map3(ActionEvent event) throws IOException {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("map3.fxml"));
			Parent root = loader.load();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root, 1500, 500);
			stage.setResizable(false);
			
			map3collision m3c = loader.getController();
			
			if(maincontroller.get_img() == 1) {
				img11 = new Image(getClass().getResourceAsStream("LittleNew_right.png"));
				img12 = new Image(getClass().getResourceAsStream("LittleNew_left.png"));
			}
			else if(maincontroller.get_img() == 2) {
				img11 = new Image(getClass().getResourceAsStream("cabiR.png"));
				img12 = new Image(getClass().getResourceAsStream("cabiL.png"));
			}
			else if(maincontroller.get_img() == 3) {
				img11 = new Image(getClass().getResourceAsStream("oldpeeR.png"));
				img12 = new Image(getClass().getResourceAsStream("oldpeeL.png"));
			}
			else if(maincontroller.get_img() == 4) {
				img11 = new Image(getClass().getResourceAsStream("Dino_right.png"));
				img12 = new Image(getClass().getResourceAsStream("Dino_left.png")); 
			}

			m3c.createPlayer(img11);
			m3c.draw();
			
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
							m3c.setBulletL();
							BulletL_cooldown = 0;
						}
						break;
					case K:
						Kpress = true;
						if(BulletR_cooldown > 150) {
							m3c.setBulletR();
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
						if(m3c.get_d() == 1 && (m3c.get_l() == 1 || m3c.get_r() == 1 ) && cooldown >= 50 ) {
							m3c.escape_jump();
						}
						else if( m3c.get_d() == 1 && cooldown >= 50 ) {
							m3c.moveup();
						}
						st++;
						if(st >= 25) {
							jump = 2;
						}
					}
					if(Apress == false || Dpress == false) {
						if(Apress == true) {
							m3c.createPlayer(img12);
							m3c.moveleft();
						}
						if(Dpress == true) {
							m3c.createPlayer(img11);
							m3c.moveright();
						}
					}
					if((m3c.get_d() == 0 && jump!=1 )) {
						m3c.movedown();
						cooldown = 0;
					}
					else if(jump == 2 && cooldown >= 50) {
						m3c.force_movedown();
						cooldown = 0;
					}
					if(m3c.get_d() == 1) {
						jump = 0;
						st = 0;
						cooldown++;
					}
					if(Jpress == true) {
						m3c.shootL();
					}
					if(Kpress == true ) {
						m3c.shootR();
					}
					if(m3c.get_gameover() == true) {
						stage.close();
					}
					if(Rpress == true) {
						m3c.ResetRole();
					}
				}
			};
			timer.start();
			
			stage.setScene(scene);
			stage.show();
		}
	
	public int get_D() {return d;}
	public int get_L() {return l;}
	public int get_R() {return r;}
	public boolean get_GameOver() {return gameover;}
	public boolean get_GamePass() {return gamepass;}
	
	@Override
	public void initialize(URL url, ResourceBundle resourcebundle) {
		ct.start();
	}
}