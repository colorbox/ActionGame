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
    private SensorDot[] SensorDots = new  SensorDot[8];

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

    //getter
    public boolean getLanding(){return Landing;}
    public boolean getWallingRight(){return WallingRight;}
    public boolean getWallingLeft(){return WallingLeft;}
    public boolean getRoofing(){return Roofing;}

    //パラメータセット
    public void setParam(int x,int y){
	//Up
	SensorDots[0].setParam(x+ 2,y-1);
	SensorDots[1].setParam(x+14,y-1);
	//Down
	SensorDots[2].setParam(x+ 2,y+16);
	SensorDots[3].setParam(x+14,y+16);
	//Left
	SensorDots[4].setParam(x-1,y+ 2);
	SensorDots[5].setParam(x-1,y+14);
	//Right
	SensorDots[6].setParam(x+16,y+ 2);
	SensorDots[7].setParam(x+16,y+14);
    }

    //全方向用の状態検査
    private boolean isInsideForAllDirection(int index1,int index2){
	if(SensorDots[index1].getInside() && SensorDots[index2].getInside()){
	    return true;
	}else{
	    return false;
	}
    }

    //物体のめり込み具合を調べる
    public void isInsideJudge(int[][] Stage){
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
