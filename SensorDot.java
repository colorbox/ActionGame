import java.awt.*;
import java.applet.Applet;

public class SensorDot{

    //指定の座標がオブジェクト内部にあるかどうかの判定
    private int x=0,y=0;
    private boolean Inside=false;
    public SensorDot(int x,int y){
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
    public void isInside(int[][] Stage){
	int numX = ((x-x%16)/16);
	int numY = ((y-y%16)/16);

	//System.out.println("(x,y,Stage[y][x],numX,numY)"+x+","+y+","+Stage[numY][numX]+","+numX+","+numY);

	if(Stage[numY][numX]==1){
	    //System.out.println("inside!");
	    setInside(true);
	}else{
	    setInside(false);
	}
    }

    //描写
    public void draw(Graphics g){
	//内部に入ってる状態なら色を青に、入ってない状態なら赤に
	if(getInside()){
	    //System.out.println("BLUE");
	    g.setColor(Color.blue);
	}else{
	    g.setColor(Color.red);
	}
	//点の描写
	g.drawLine((int)getX(),(int)getY(),(int)getX(),(int)getY());

	g.setColor((new Color(0, 200,0)));

    }

}
