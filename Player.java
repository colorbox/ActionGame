import java.util.*;
import java.lang.Math;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Player extends Character{
    private PlayerBehavior playerbehavior;
    public void setPlayerBehavior(PlayerBehavior playerbehavior){this.playerbehavior=playerbehavior;}
    public PlayerBehavior getPlayerBehavior(){return playerbehavior;}

    
    //Stage Data
    private int StageWidth=0,StageHeight=0;
    //砲台
    private Pointer Cannon;
    //移動モードかどうか
    private boolean Movemode =false;

    //setter
    public void setStageWidth(int StageWidth){this.StageWidth=StageWidth;}
    public void setStageHeight(int StageHeight){this.StageHeight=StageHeight;}
    //getter
    public int getStageHeight(){return StageHeight;}
    public int getStageWidth(){return StageWidth;}
    public Pointer getCannon(){return Cannon;}

    //コンストラクタ
    public Player(double x,double y,double rad,double force,int StageWidth,int StageHeight){
	super(x,y,rad,force,false);
	Cannon=new Pointer(rad,force);
	setStageWidth(StageWidth);
	setStageHeight(StageHeight);
	setClassName("Player");
    }

    public void neutral(){
	setForce(0.0);
	setRad(0.0);
    }
    public void moveForward(){
	setForce(2.0);
	setRad(0);
	Cannon.setRad(0);
	Cannon.setForce(18);
    }
    public void moveBackward(){
	setForce(2.0);
	setRad(180);
	Cannon.setRad(180);
	Cannon.setForce(18);
    }
    public void jumpNeutral(){
	setForce(13.0);
	setRad(-1*Math.PI/2);
    }
    public void jumpForward(){
	setForce(13.0);
	setRad(-1*Math.PI/180*75);
	Cannon.setRad(0);
	Cannon.setForce(18);
    }
    public void jumpBackward(){
	setForce(13.0);
	setRad(-1*Math.PI/180*105);
	Cannon.setRad(180);
	Cannon.setForce(18);
    }
    public void attack(MaterialAdministrator ma){
	launchMaterial(ma);
    }

    //弾発射
    public void launchMaterial(MaterialAdministrator ma){
	ma.add(new Ballet(getX(),getY(),Cannon.getRad(),Cannon.getForce() ,false));
    }

    //移動
    public void move(){
	//物理ベクトルに基づいた自機と弾の移動
	materialMove();
    }

    //描写
    public void draw(Graphics g){
	//自機描写
	g.setColor(Color.green);
	//DrawObject
	int pX,pY;
	pX=(int)getDrawX();
	pY=(int)getDrawY();
       	Cannon.draw(g,pX,pY);
	g.drawRect(pX,pY,15,15);
	g.drawRect(pX+1,pY+1,13,13);
	//センサの描写
	Sensor.draw(g);
	materialDraw(g);

    }

    //override updateDrawCoordinate function
    public void updateDrawCoordinate(int StageX,int StageY,int ObjectX,int ObjectY){
	int Px = (int)getX();
	int Py = (int)getY();
	//X
	if(Px>176 && Px<getStageWidth()-176){
	    //center:Camera move
	    setDrawX(176);
	}else if(Px>=getStageWidth()-176){
	    //right:Camera stable
	    setDrawX(Px-(getStageWidth()-352) );
	}else if(Px<=176){
	    //left:Camera stable
	    setDrawX(Px);
	}

	//Y
	if(Py>176 && Py<getStageHeight()-176){
	    //center:camera move
	    setDrawY(176);
	}else if(Py>=getStageHeight()-176){
	    //bottom:camera stable
	    setDrawY(Py-(getStageHeight()-352));
	}else if(Py<=176){
	    //top:camera stable
	    setDrawY(Py);
	}
    }

    //when hit
    public void hited(Material Enemy){	
	System.out.println("hit in Player with argument Material");
    }
    public void hited(Enemy Enemy){	
	System.out.println("hit in Player with argument Enemy");
    }
    public void hited(Ballet Enemy){
	
	System.out.println("hit in Player with argument Ballet");
    }


}
