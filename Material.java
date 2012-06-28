import java.util.*;
import java.lang.Math;
import java.awt.*;
import java.applet.Applet;

//ゲームにおける物体のスーパークラス
public class Material{
    //敵フラグ
    private boolean isEnemy;
    //重力
    public Pointer Gravity = new Pointer(Math.PI/2,0.5);
    //1度の角度
    private static double rad1 = Math.PI/180;
    //座標計算用
    protected double x=0.0,y=0.0;
    //ベクトルクラス
    private Pointer Vector;
    //接地接壁接天井検知用のセンサクラス
    protected Sensor Sensor=new Sensor(0,0);
    //当たり範囲群
    private ManyHitters manyhitters = new ManyHitters();
    //消滅フラグ
    private boolean Vanish=false;

    //getter
    public double getForce(){return Vector.getForce();}
    public double getRad(){return Vector.getRad();}
    public double getX(){return x;}
    public double getY(){return y;}
    public boolean getLanding(){return Sensor.getLanding();}
    public boolean getVanish(){return Vanish;}
    public boolean getIsEnemy(){return isEnemy;}
    public ManyHitters getManyHitters(){return manyhitters;}
    public Sensor getSensor(){return Sensor;}

    //setter
    public void setForce(double ForcePower){this.Vector.setForce(ForcePower);}
    public void setRad(double ForceRad){this.Vector.setRad(ForceRad);}
    public void setRad(int ForceRad){this.Vector.setRad(ForceRad*rad1);}
    public void setX(double x){this.x=x;}
    public void setY(double y){this.y=y;}
    public void setVanish(boolean Vanish){this.Vanish=Vanish;}
    public void setIsEnemy(boolean isEnemy){this.isEnemy=isEnemy;}

    //コンストラクタ
    public Material(double x,double y,double rad,double force,boolean isEnemy){
	this.x=x;
	this.y=y;
	this.Vector=new Pointer(rad,force);
	setIsEnemy(isEnemy);
	manyhitters.add(new Hitter(8,8,0,0,5));
    }

    //あたり範囲を追加
    public void addHitter(Hitter hitter){
	manyhitters.add(hitter);
    }

    //接壁処理左
    public void wallingLeftOperation(Stage Stage){
	//左方向のベクトルを除去
	//Vector.verticleExtractRight();
	//X座標を補正
	setX( (int)getX() + 15 - (int)(getX()-1)%16 );
	//センサを更新
	Sensor.updateCoordinate(Stage,(int)getX(),(int)getY());
    }

    //右
    public void wallingRightOperation(Stage Stage){
	//左右どちらかのセンサがオブジェクト内部に合ったら接壁状態
	//ベクトルの右方向成分を除去
	//Vector.verticleExtractLeft();
	//座標を補正
	setX( (int)getX() - (int)(getX())%16 );
	//センサを更新
	Sensor.updateCoordinate(Stage,(int)getX(),(int)getY());

    }
    //接天井処理
    public void roofingOperation(Stage Stage){
	//上2つの点がオブジェクト内に入っていたら接天井状態
	//ベクトルの上方向成分を除去
	Vector.sideExtractDown();
	//座標をオブジェクトの下に固定
	setY( (int)getY() + 15 - (int)(getY()-1)%16) ;
	//センサを更新
	Sensor.updateCoordinate(Stage,(int)getX(),(int)getY());
    }
    //接地処理
    public void landingOperation(Stage Stage){
	//下2つの点がオブジェクトの内部に入っていたら接地状態
	//下点が物質内部に入っていたらベクトルを操作、全てのベクトルを消してニュートラルにする。
	//とりあえず今はニュートラル処理
	setForce(0.0);
	setRad(0.0);
	//座標をオブジェクトの上に固定する
	setY( ( (int)getY() - (int)(getY())%16 ) );
	//センサの値を更新
	Sensor.updateCoordinate(Stage,(int)getX(),(int)getY());
    }

    //位置補正
    public void moveRevision(Stage Stage){
	//センサーの判定
	Sensor.isInsideJudge(Stage);
	//接地、接壁、接天井、それらの複合、それぞれの状態をによる処理を行う
	if(Sensor.getLanding()){landingOperation(Stage);}
	if(Sensor.getRoofing()){roofingOperation(Stage);}
	if(Sensor.getWallingRight()){wallingRightOperation(Stage);}
	if(Sensor.getWallingLeft()){wallingLeftOperation(Stage);}
    }

    //物理的移動
    public void materialMove(){
	//重力による補正、自由落下状態なら重力の影響を受ける
	if(!Sensor.getLanding()){
	    this.Vector.vectorCombination(Gravity);
	}
	//物理ベクトルに基づく移動
	setX(getX()+getForce() * Math.cos(getRad()));
	setY(getY()+getForce() * Math.sin(getRad()));

	//センサの値を更新
	Sensor.setParam((int)getX(),(int)getY());

	//当たり範囲の座標を更新
	manyhitters.setCoordinate((int)getX(),(int)getY());
    }

    //移動と補正
    public void move(){
	//物理移動
	materialMove();
    }

    //消滅判定
    public boolean isVanish(){
	//System.out.println(getVanish());
	//消えてたらそのまま消滅フラグを返す
	if(getVanish()){return getVanish();}
	//消えてなかったら画面外に判定を行う。
	return isOut();
    }

    //画面外判定
    public boolean isOut(){
	//画面外に出ていたら
	if(x<0.0||x>1000.0||y<0.0||y>1000.0 ){
	    setVanish(true);
	    return getVanish();
	}else{
	    setVanish(false);
	    return getVanish();
	}
    }


    public void materialDraw(Graphics g){
	manyhitters.draw(g);
    }
    //描写
    public void draw(Graphics g){
	g.fillOval((int)x,(int)y,15,15);
	materialDraw(g);
    }
}
