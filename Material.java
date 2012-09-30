import java.util.*;
import java.lang.Math;
import java.awt.*;
import java.applet.Applet;

//ゲームにおける物体のスーパークラス
public class Material extends DrawObject{
    //敵フラグ
    private boolean isEnemy;
    //重力
    public Pointer Gravity = new Pointer(Math.PI/2,0.5);
    //消滅フラグ
    private boolean Vanish=false;
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
    //flying object flag
    private boolean Flying;
    //Material's Size
    private int Width,Height;

    //getter
    public double getForce(){return Vector.getForce();}
    public double getRad(){return Vector.getRad();}
    public double getX(){return x;}
    public double getY(){return y;}
    public boolean getLanding(){return Sensor.getLanding();}
    public boolean getRoofing(){return Sensor.getRoofing();}
    public boolean getVanish(){return Vanish;}
    public boolean getIsEnemy(){return isEnemy;}
    public ManyHitters getManyHitters(){return manyhitters;}
    public Sensor getSensor(){return Sensor;}
    public boolean getFlying(){return Flying;}
    public int getWidth(){return this.Sensor.getWidth();}
    public int getHeight(){return this.Sensor.getHeight();}
    public double getGravityLimit(){return this.Vector.getGravityLimit();}


    //setter
    public void setForce(double ForcePower){this.Vector.setForce(ForcePower);}
    public void setRad(double ForceRad){this.Vector.setRad(ForceRad);}
    public void setRad(int ForceRad){this.Vector.setRad(ForceRad*rad1);}
    public void setX(double x){this.x=x;}
    public void setY(double y){this.y=y;}
    public void setVanish(boolean Vanish){this.Vanish=Vanish;}
    public void setIsEnemy(boolean isEnemy){this.isEnemy=isEnemy;}
    public void setFlying(boolean flying){this.Flying = flying;}
    public void setWidth(int Width){this.Sensor.setWidth(Width);}
    public void setHeight(int Height){this.Sensor.setHeight(Height);}
    public void setGravityLimit(double GravityLimit){this.Vector.setGravityLimit(GravityLimit);}
    public void setSize(int size){
	setWidth(size);
	setHeight(size);
    }


    //コンストラクタ
    public Material(double x,double y,double rad,double force,boolean isEnemy){
	super((int)x,(int)y);
	setX(x);
	setY(y);
	this.Vector=new Pointer(rad,force);
	setIsEnemy(isEnemy);
	setSize(8);
	manyhitters.add(new Hitter(getWidth()/2,getHeight()/2,(int)getX(),(int)getY(),3));
	setFlying(false);
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
	setX( (int)getX() + getWidth()-1 - (int)(getX()-1)%getWidth() );
	//センサを更新
	Sensor.updateCoordinate(Stage,(int)getX(),(int)getY());
    }

    //接壁処理右
    public void wallingRightOperation(Stage Stage){
	//左右どちらかのセンサがオブジェクト内部に合ったら接壁状態
	//ベクトルの右方向成分を除去
	//Vector.verticleExtractLeft();
	//座標を補正
	setX( (int)getX() - (int)(getX())%getWidth() );
	//センサを更新
	Sensor.updateCoordinate(Stage,(int)getX(),(int)getY());

    }
    //接天井処理
    public void roofingOperation(Stage Stage){
	//上2つの点がオブジェクト内に入っていたら接天井状態
	//ベクトルの上方向成分を除去
	Vector.sideExtractDown();
	//座標をオブジェクトの下に固定
	setY( (int)getY() + getHeight()-1 - (int)(getY()-1)%getHeight()) ;
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
	setY( ( (int)getY() - (int)(getY())%getHeight() ) );
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
	//重力による補正、飛行状態でなく自由落下状態なら重力の影響を受ける
	if(!Sensor.getLanding() && !getFlying()){
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

    //move draw coordinate
    public void drawMove(int StageX,int StageY){
	//描写座標の調整
	updateDrawCoordinate(StageX,StageY,(int)getX(),(int)getY());
	//move Draw Coordinate for Hitter
	getManyHitters().moveDrawCoordinate(StageX,StageY);
	//move Draw Coordinate for SensorDots
	getSensor().moveDrawCoordinate(StageX,StageY);
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
	if(x<0.0||x>10000.0||y<0.0||y>10000.0 ){
	    setVanish(true);
	    return getVanish();
	}else{
	    setVanish(false);
	    return getVanish();
	}
    }


    //物体描写
    public void materialDraw(Graphics g){
	manyhitters.draw(g);
    }

    //描写
    public void draw(Graphics g){
	g.setColor(Color.red);
	
	g.fillOval(getDrawX(),getDrawY(),getWidth()-1,getHeight()-1);
	//g.fillOval((int)x,(int)y,15,15);
	materialDraw(g);
    }

    //when hit
    public void hited(Material material){
	System.out.println("hit in Material with argument Material");
    }
    public void hited(Player Player){
	System.out.println("hit in Material with argument Player");
    }
    //when hit
    public void hited(Character Character){
	System.out.println("hit in Material with argument Character");
    }
    //when hit
    public void hited(Ballet Ballet){
	System.out.println("hit in Material with argument Ballet");
    }
}
