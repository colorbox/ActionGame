import java.awt.Point;
import java.util.*;
import java.lang.Math;
import java.awt.*;
import java.applet.Applet;


public class Block{
    //座標
    private Point Coordinate;
    //getter/setter
    public Point getCoordinate(){return Coordinate;}

    //コンストラクタ
    public Block(int x,int y){
	Coordinate=new Point(x,y);
    }


    public void draw(Graphics g){
	g.setColor(Color.black);
	g.fillRect((int)Coordinate.getX(),(int)Coordinate.getY(),15,15);
    }
}
