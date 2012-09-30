import java.awt.*;
import java.applet.Applet;

public class Sensor{
    //着地しているか(下方向)
    private boolean Landing;
    //着壁しているか(左右)
    private boolean WallingLeft;
    private boolean WallingRight;
    //着天井しているか
    private boolean Roofing;
    //上下左右のセンサを宣言
    private SensorDot[] SensorDots = new SensorDot[8];
    private int Width,Height;

    //コンストラクタ
    public Sensor(int x,int y){
	for(int i=0;i<8;i++){
	    SensorDots[i] = new SensorDot(0,0);
	}
    }

    //setter
    public void setLanding(boolean Landing){this.Landing=Landing;}
    public void setWallingLeft(boolean WallingLeft){this.WallingLeft=WallingLeft;}
    public void setWallingRight(boolean WallingRight){this.WallingRight=WallingRight;}
    public void setRoofing(boolean Roofing){this.Roofing=Roofing;}
    public void setWidth(int Width){this.Width=Width;}
    public void setHeight(int Height){this.Height=Height;}

    //getter
    public boolean getLanding(){return Landing;}
    public boolean getWallingRight(){return WallingRight;}
    public boolean getWallingLeft(){return WallingLeft;}
    public boolean getRoofing(){return Roofing;}
    public int getWidth(){return Width;}
    public int getHeight(){return Height;}

    //パラメータセット
    public void setParam(int x,int y){
	int up = (int)(getHeight()/4);
	int down = (int)(getHeight() - up);
	int left = (int)(getWidth()/4);
	int right = (int)(getWidth()-left);
	//Up
	SensorDots[0].setParam(x+  left,y);
	SensorDots[1].setParam(x+ right,y);
	//Down
	SensorDots[2].setParam(x+  left,y+getHeight());
	SensorDots[3].setParam(x+ right,y+getHeight());
	//Left
	SensorDots[4].setParam(x,y+ up);
	SensorDots[5].setParam(x,y+ down);
	//Right
	SensorDots[6].setParam(x+getWidth(),y+ up);
	SensorDots[7].setParam(x+getWidth(),y+ down);

    }

    //move sensor dot draw coordinate
    public void moveDrawCoordinate(int StageX,int StageY){
	for(int i=0;i<SensorDots.length;i++){
	    SensorDots[i].updateDrawCoordinate(StageX,StageY,SensorDots[i].getX(),SensorDots[i].getY());
	}
    }

    //全方向用の状態検査
    private boolean isInsideForAllDirection(int index1,int index2){
	if(SensorDots[index1].getInside() || SensorDots[index2].getInside()){
	    return true;
	}else{
	    return false;
	}
    }

    //センサーの位置とめり込み状態を同時に更新
    public void updateCoordinate(Stage Stage,int x,int y){
	setParam(x,y);
	isInsideJudge(Stage);
    }

    //物体のめり込み具合を調べる
    public void isInsideJudge(Stage Stage){
	//接地、接壁、接天井などの状態を調べる
	for(int i=0;i<8;i++){
	    SensorDots[i].isInside(Stage);
	}
	//それぞれの方向についてめり込み状態かを判別
	setRoofing(isInsideForAllDirection(0,1));
	setLanding(isInsideForAllDirection(2,3));
	setWallingLeft(isInsideForAllDirection(4,5));
	setWallingRight(isInsideForAllDirection(6,7));
    }

    //描写
    public void draw(Graphics g){
	for(int i=0;i<8;i++){
	    SensorDots[i].draw(g);
	}
    }
}
