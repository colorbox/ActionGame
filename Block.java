import java.awt.Point;
import java.util.*;
import java.lang.Math;
import java.awt.*;
import java.applet.Applet;


public class Block extends DrawObject{
    //tag
    int tag;
    //座標
    private Point Coordinate;
    //getter/setter
    public Point getCoordinate(){return Coordinate;}
    public int getTag(){return tag;}
    public void setTag(int tag){this.tag=tag;}
    public int getX(){return (int)getCoordinate().getX();}
    public int getY(){return (int)getCoordinate().getY();}

    //コンストラクタ
    public Block(int x,int y,int tag){
	super(x,y);
	setTag(tag);
	Coordinate=new Point(x,y);
    }

    //描写
    public void draw(Graphics g){
	if(tag==1){
	    g.setColor(Color.black);
	}else if(tag==0){
	    g.setColor(Color.white);
	}
	//g.fillRect(getX(),getY(),16,16);
	g.fillRect(getDrawX(),getDrawY(),16,16);
	
    }
}
