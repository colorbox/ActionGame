import java.awt.*;
import java.applet.Applet;

public class SensorDot extends DrawObject{

    //指定の座標がオブジェクト内部にあるかどうかの判定
    private int x=0,y=0;
    private boolean Inside=false;
    public SensorDot(int x,int y){
	super(0,0);
	setParam(x,y);
    }

    //getter
    public int getX(){return x;}
    public int getY(){return y;}
    public boolean getInside(){return Inside;}

    //setter
    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}
    public void setInside(boolean Inside){this.Inside=Inside;}

    //xとyを入力
    public void setParam(int x,int y){
	this.x=x;
	this.y=y;
    }

    //センサがオブジェクト内にいるかどうか判定
    public void isInside(Stage Stage){
	//set coordinate information
	int numX = ((x-x%16)/16);
	int numY = ((y-y%16)/16);
	//get Block information from Stage
	if(Stage.getPointTag(numX,numY)==1){
	    setInside(true);
	}else{
	    setInside(false);
	}
    }

    //描写
    public void draw(Graphics g){
	//内部に入ってる状態なら色を青に、入ってない状態なら赤に
	if(getInside()){
	    g.setColor(Color.blue);
	}else{
	    g.setColor(Color.red);
	}
	int px=(int)getX();
	int py=(int)getY();
	//点の描写
	g.drawLine(px,py,px,py);
	g.drawLine(getDrawX(),getDrawY(),getDrawX(),getDrawY());


    }

}
