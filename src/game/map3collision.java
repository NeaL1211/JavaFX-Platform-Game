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
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class map3collision implements Initializable {
	@FXML Rectangle rec;
	@FXML Rectangle r1;
	@FXML Rectangle r2;
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
	@FXML Rectangle fireR;
	@FXML Rectangle fireL;
	@FXML Rectangle h1;
	@FXML Rectangle h2;
	@FXML Rectangle h3;
	@FXML Rectangle f1;//小火球
	@FXML Rectangle f2;//
	@FXML Rectangle f3;//
	@FXML Rectangle f4;//
	@FXML Rectangle f5;//
	@FXML Rectangle f6;//
	@FXML Rectangle marshroom;//隱藏的蘑菇
	@FXML Rectangle banger1;//狀完後飛走
	@FXML Rectangle banger2;//隱藏的方塊
	@FXML Rectangle end;
	@FXML Rectangle lava;
	@FXML Label b1label;
	@FXML Label b2label;
	@FXML Button nextstage2;

	Scene scene;
	Stage stage = new Stage();
	Image fire_up = new Image(getClass().getResourceAsStream("fireball_up.png"));
	Image fire_down = new Image(getClass().getResourceAsStream("fireball_down.png"));
	Image FH = new Image(getClass().getResourceAsStream("FullHp.png"));
	Image ZH = new Image(getClass().getResourceAsStream("ZeroHp.png"));
	Image fr = new Image(getClass().getResourceAsStream("fire_right.png"));
	Image fl = new Image(getClass().getResourceAsStream("fire_left.png"));
	Image mrsh = new Image(getClass().getResourceAsStream("marshroom.png"));
	Image dc = new Image(getClass().getResourceAsStream("door_close.png"));
	Image dopen = new Image(getClass().getResourceAsStream("door_open.png"));
	
	int change1, change2, change3, change4, change5, change6;
	int heartNum = 3;
	boolean gameover = false, gamepass = false, BulletL_Stop , BulletR_Stop, eat_mrsh = false;
	double x,y,x1,x2,x3,x4,x5,y1,y2,y3,y4,y5,x6,y6,x7,y7,y8,x8,y9,y10,y11,y12,y13,y14;
	public int D, R, L, U, UC, count, collision, touch_b2 = 0;
	public double range = 0;
	
	ArrayList<Rectangle> rec_collect = new ArrayList<Rectangle>();
	public void addRec() {
		rec_collect.add(r1);rec_collect.add(r2);rec_collect.add(r3);rec_collect.add(r4);rec_collect.add(r5);rec_collect.add(r6);rec_collect.add(r7);
		rec_collect.add(r8);rec_collect.add(r9);rec_collect.add(r10);rec_collect.add(r11);rec_collect.add(r12);rec_collect.add(r13);
		rec_collect.add(r14);rec_collect.add(r15);
	}
	int num;
	public void createPlayer(Image img) {
		rec.setFill(new ImagePattern(img));
	}
	
	public void draw() {
		addRec();
		h1.setFill(new ImagePattern(FH));
		h2.setFill(new ImagePattern(FH));
		h3.setFill(new ImagePattern(FH));
		f1.setFill(new ImagePattern(fire_down));
		f2.setFill(new ImagePattern(fire_down));
		f3.setFill(new ImagePattern(fire_down));
		f4.setFill(new ImagePattern(fire_down));
		f5.setFill(new ImagePattern(fire_down));
		f6.setFill(new ImagePattern(fire_down));
		fireR.setFill(new ImagePattern(fr));
		fireL.setFill(new ImagePattern(fl));
		fireR.setVisible(false);
		fireL.setVisible(false);
		marshroom.setVisible(false);
		banger2.setVisible(false);
		b2label.setVisible(false);
		nextstage2.setVisible(false);
		end.setFill(new ImagePattern(dc));
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
			if(eat_mrsh == true) {
				rec.setTranslateX(x+=3);
			}
			else {
				rec.setTranslateX(x+=2);
			}
			L=0;
			if(collision == 21) {
				D=0;
			}
		}
	}
	public void moveleft() {
		num = 1;
		if(L==0) {
			if(eat_mrsh == true) {
				rec.setTranslateX(x-=3);
			}
			else {
				rec.setTranslateX(x-=2);
			}
			R=0;
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
	    	if(eat_mrsh == true) {
	    		rec.setTranslateY(y+=200);
	    	}else {
	    		rec.setTranslateY(y+=45);
	    	}
	    	U=0;
	    }
	}
	public void escape_jump() {
		if(U == 0 && D==1) {
			D=L=R=0;
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
			BulletCollision(rec, rec_collect.get(i), fireR);
		}
		if(fireR.getLayoutX()+fireR.getTranslateX()-rec.getLayoutX()-rec.getTranslateX()-rec.getWidth()>350) {
			fireR.setLayoutY(y4+=1000);
			}
	}
	
	public void shootL() {
		fireL.setVisible(true);
		fireL.setTranslateX(x5-=3);
		for(int i=0; i<rec_collect.size(); i++) {
			BulletCollision(rec, rec_collect.get(i), fireL);
		}
		if(rec.getLayoutX()+rec.getTranslateX()-fireL.getLayoutX()-fireL.getTranslateX()-fireL.getWidth()>350) {
			fireL.setLayoutY(y5+=1000);
		}
	}
	
	public void checkCollision(Rectangle rec, Rectangle collisionShape, Rectangle banger1, Rectangle banger2) {
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
		
		double banger1i = banger1.getLayoutY();
		double banger1a = banger1.getLayoutY() + banger1.getHeight();
		double banger2i = banger2.getLayoutY();
		double banger2a = banger2.getLayoutY() + banger2.getHeight();
		
		//跳了之後會不會超出地形
		range = 0;
		if((878<aMaxX&&aMaxX<903||878<aMinX&&aMinX<903) && banger1i < aMinY && aMinY-45 < banger1a){// a在下
			UC=5;
			range = aMinY - banger1a;
		}
		else if((776<aMaxX&&aMaxX<801||776<aMinX&&aMinX<801) && banger2i < aMinY && aMinY-45 < banger2a){
			UC=5;
			range = aMinY - banger2a;
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
	
	
	AnimationTimer collisionTimer = new AnimationTimer() {
		@Override 
		public void handle(long timestamp) {
				count = 0;
				collision = 0;
				
				for(int i=0; i<rec_collect.size(); i++) {
					checkCollision(rec, rec_collect.get(i), banger1, banger2);
				}
				if(count == 0 && collision == rec_collect.size()) {
					D=0;
				}
				
				if(change1 == 0) {
					f1.setTranslateY(y1+=1);
					f1.setFill(new ImagePattern(fire_down));
					if(f1.getLayoutY()+f1.getTranslateY()+f1.getHeight()>500) {
						change1 = 1;
					}
				}
				else if(change1 == 1) {
					f1.setTranslateY(y1-=1);
					f1.setFill(new ImagePattern(fire_up));
					if(f1.getLayoutY()+f1.getTranslateY()<279) {
						change1 = 0;
					}
				}
				if(change2 == 0) {
					f2.setTranslateY(y2+=1);
					f2.setFill(new ImagePattern(fire_down));
					if(f2.getLayoutY()+f2.getTranslateY()+f2.getHeight()>500) {
						change2 = 1;
					}
				}
				else if(change2 == 1) {
					f2.setTranslateY(y2-=1);
					f2.setFill(new ImagePattern(fire_up));
					if(f2.getLayoutY()+f2.getTranslateY()<250) {
						change2 = 0;
					}
				}
				if(change3 == 0) {
					f3.setTranslateY(y6+=1);
					f3.setFill(new ImagePattern(fire_down));
					if(f3.getLayoutY()+f3.getTranslateY()+f3.getHeight()>500) {
						change3 = 1;
					}
				}
				else if(change3 == 1) {
					f3.setTranslateY(y6-=1);
					f3.setFill(new ImagePattern(fire_up));
					if(f3.getLayoutY()+f3.getTranslateY()<258) {
						change3 = 0;
					}
				}
				if(change4 == 0) {
					f4.setTranslateY(y7+=1);
					f4.setFill(new ImagePattern(fire_down));
					if(f4.getLayoutY()+f4.getTranslateY()+f4.getHeight()>500) {
						change4 = 1;
					}
				}
				else if(change4 == 1) {
					f4.setTranslateY(y7-=1);
					f4.setFill(new ImagePattern(fire_up));
					if(f4.getLayoutY()+f4.getTranslateY()<291) {
						change4 = 0;
					}
				}
				if(change5 == 0) {
					f5.setTranslateY(y8+=1);
					f5.setFill(new ImagePattern(fire_down));
					if(f5.getLayoutY()+f5.getTranslateY()+f5.getHeight()>500) {
						change5 = 1;
					}
				}
				else if(change5 == 1) {
					f5.setTranslateY(y8-=1);
					f5.setFill(new ImagePattern(fire_up));
					if(f5.getLayoutY()+f5.getTranslateY()+f5.getHeight()<229) {
						change5 = 0;
					}
				}
				if(change6 == 0) {
					f6.setTranslateY(y9+=1);
					f6.setFill(new ImagePattern(fire_down));
					if(f6.getLayoutY()+f6.getTranslateY()+f6.getHeight()>500) {
						change6 = 1;
					}
				}
				else if(change6 == 1) {
					f6.setTranslateY(y9-=1);
					f6.setFill(new ImagePattern(fire_up));
					if(f6.getLayoutY()+f6.getTranslateY()<219) {
						change6 = 0;
					}
				}
				
				try {
					OutOfMap();
				} catch (IOException e) {
				}
				
				if(rec.getBoundsInParent().intersects(banger1.getBoundsInParent())) {
					banger1.setLayoutY(y10-=2000);
					b1label.setLayoutY(y11-=2000);
					banger2.setTranslateY(y12+=197);
					b2label.setTranslateY(y13+=197);
					marshroom.setTranslateY(y3+=195);
					banger2.setVisible(true);
					b2label.setVisible(true);
				}
				
				if(rec.getBoundsInParent().intersects(banger2.getBoundsInParent()) && touch_b2 == 0) {
					b2label.setText("!");
					marshroom.setVisible(true);
					marshroom.setFill(new ImagePattern(mrsh));
					touch_b2++;
				}
				if(marshroom.isVisible() == true) {
					marshroom.setTranslateX(x3+=0.5);
					if(rec.getBoundsInParent().intersects(marshroom.getBoundsInParent())) {
						marshroom.setTranslateY(y3-=2000);
						rec.setHeight(64);
						rec.setWidth(56);
						rec.setTranslateY(y-=32);
						eat_mrsh = true;
					}
					if(!(marshroom.getBoundsInParent().intersects(banger2.getBoundsInParent())) && !(marshroom.getBoundsInParent().intersects(r8.getBoundsInParent()))) {
						marshroom.setTranslateY(y3+=1);
					}
					if(marshroom.getLayoutY()+marshroom.getTranslateY()+marshroom.getHeight()>475) {
						marshroom.setVisible(false);
					}
				}
				
				if(rec.getBoundsInParent().intersects(end.getBoundsInParent())) {
					end.setFill(new ImagePattern(dopen));
					TranslateTransition go = new TranslateTransition();
					go.setNode(rec);
					go.setDuration(Duration.millis(150));
					go.setToX(1400);
					go.setCycleCount(1);
					go.play();
					gamepass = true;
					nextstage2.setVisible(true);
				}
		}
	};
	
	public void OutOfMap() throws IOException{
		if(rec.getLayoutY()+rec.getTranslateY()+rec.getHeight()>475 
			||rec.getBoundsInParent().intersects(f1.getBoundsInParent())
			||rec.getBoundsInParent().intersects(f2.getBoundsInParent())
			||rec.getBoundsInParent().intersects(f3.getBoundsInParent())
			||rec.getBoundsInParent().intersects(f4.getBoundsInParent())
			||rec.getBoundsInParent().intersects(f5.getBoundsInParent())
			||rec.getBoundsInParent().intersects(f6.getBoundsInParent())) {
			
			rec.setTranslateX(x-=rec.getTranslateX());
			rec.setTranslateY(y-=rec.getTranslateY());
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
			a.setNode(rec);
			a.setByValue(10);
			a.setToValue(0.1);
			a.setAutoReverse(true);
			a.setDuration(Duration.millis(250));
			a.play();
			L=R=U=0;
		}
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
	
	public void go_map4(ActionEvent event) throws IOException {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("map4.fxml"));
			Parent root = loader.load();
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root, 1500, 500);
			stage.setResizable(false);
			
			map4controller m4c = loader.getController();
			
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

			m4c.createPlayer(img11);
			m4c.draw();
			
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
							m4c.setBulletL();
							BulletL_cooldown = 0;
						}
						break;
					case K:
						Kpress = true;
						if(BulletR_cooldown > 150) {
							m4c.setBulletR();
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
						if(m4c.getD() == 1 && (m4c.getL() == 1 || m4c.getR() == 1 ) && cooldown >= 50 ) {
							m4c.escape_jump();
						}
						else if( m4c.getD() == 1 && cooldown >= 50 ) {
							m4c.moveup();
						}
						st++;
						if(st >= 25) {
							jump = 2;
						}
					}
					if(Apress == false || Dpress == false) {
						if(Apress == true) {
							m4c.createPlayer(img12);
							m4c.moveleft();
						}
						if(Dpress == true) {
							m4c.createPlayer(img11);
							m4c.moveright();
						}
					}
					if((m4c.getD() == 0 && jump!=1 )) {
						m4c.movedown();
						cooldown = 0;
					}
					else if(jump == 2 && cooldown >= 50) {
						m4c.force_movedown();
						cooldown = 0;
					}
					if(m4c.getD() == 1) {
						jump = 0;
						st = 0;
						cooldown++;
					}
					if(Jpress == true) {
						m4c.shootL();
					}
					if(Kpress == true ) {
						m4c.shootR();
					}
					if(Rpress == true) {
						m4c.ResetRole();
					}
				}
			};
			timer.start();
			
			stage.setScene(scene);
			stage.show();
		}
	
	public int get_d() {return D;}
	public int get_l() {return L;}
	public int get_r() {return R;}
	public boolean get_gameover() {return gameover;}
	public boolean get_gamepass() {return gamepass;}
	
	@Override
	public void initialize(URL url, ResourceBundle resourcebundle) {
		collisionTimer.start();
	}
}