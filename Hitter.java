import java.lang.Math;
import java.awt.*;


//当たり判定に関するクラス、「当たり判定クラスを引数にとり当たってるかの判定」「当たり判定位置や半径の変更」「自在に消滅」などをしたりする
public class Hitter{
    //基準座標からの相対座標
    private int relativeX=0,relativeY=0;
    //基準座標
    private int x=0,y=0;
    //絶対座標(基準座標+相対座標)
    private int absolX=0,absolY=0;
    //半径
    private int radius=0;
    //消滅フラグ,モノによっちゃ、当たり判定を複数持つので、消滅フラグで管理できるようにする
    private boolean vanish=false;

    //setter
    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}
    public void setRelativeX(int relativeX){this.relativeX=relativeX;}
    public void setRelativeY(int relativeY){this.relativeY=relativeY;}
    public void setAbsolX(int absolX){this.absolX=absolX;}
    public void setAbsolY(int absolY){this.absolY=absolY;}
    public void setRadius(int radius){this.radius=radius;}
    public void setVanish(boolean vanish){this.vanish=vanish;}
    //getter
    public int getX(){return x;}
    public int getY(){return y;}
    public int getRelativeX(){return relativeX;}
    public int getRelativeY(){return relativeY;}
    public int getAbsolX(){return absolX;}
    public int getAbsolY(){return absolY;}
    public int getRadius(){return radius;}
    public boolean getVanish(){return vanish;}

    //コストラクタ
    public Hitter(int relativeX,int relativeY,int x,int y, int radius){
	setRelativeX(relativeX);
	setRelativeY(relativeY);
	setParam(x,y,radius);
    }

    //基準座標と相対座標による絶対座標の更新
    private void coordinateRenew(int x,int y){
	setAbsolX( x + getRelativeX() );
	setAbsolY( y + getRelativeY() );
    }


    //基準座標の変更
    public void setCoordinate(int x,int y){
	setX(x);
	setY(y);
	//絶対座標の更新
	coordinateRenew(x,y);

    }

    //値をまとめて変更
    public void setParam(int x,int y,int radius){
	setCoordinate(x,y);
	setRadius(radius);
    }

    //当たり判定を行う、当たってたらtrue
    public boolean collisionJudge(Hitter target){
	//当たり判定動詞の直線距離の算出
	double line = Math.sqrt(
				(getAbsolX()-target.getAbsolX())*(getAbsolX()-target.getAbsolX())
				+
				(getAbsolY()-target.getAbsolY())*(getAbsolY()-target.getAbsolY())
				);
	//距離と半径の合計とを比べる
	if(line<this.getRadius()+target.getRadius()){
	    return true;
	}else{
	    return false;
	}
    }

    //描写
    public void draw(Graphics g){
	g.setColor(Color.red);
	g.fillOval(getX()+(relativeX-radius),getY()+(relativeY-radius),2*radius,2*radius);
    }
}
