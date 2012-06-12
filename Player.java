import java.util.*;
import java.lang.Math;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Player extends Character{
    //砲台
    private Pointer Cannon;
    //移動モードかどうか
    private boolean Movemode =false;

    //getter/setter
    public Pointer getCannon(){return Cannon;}

    //コンストラクタ
    public Player(double x,double y,double rad,double force){
	super(x,y,rad,force,false);
	Cannon=new Pointer(rad,force);
    }

    //API for Behavior
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
    }
    public void jumpBackward(){
	    setForce(13.0);
	    setRad(-1*Math.PI/180*105);
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
       	Cannon.draw(g,this.x,this.y);
	g.drawRect((int)getX(),(int)getY(),15,15);
	g.drawRect((int)getX()+1,(int)getY()+1,13,13);

	//センサの描写
	Sensor.draw(g);
	materialDraw(g);
	/*
	g.setColor((new Color(200, 200,200)));
	g.drawLine((int)getX(),(int)getY(),(int)getX(),(int)getY());
	*/

    }
}
