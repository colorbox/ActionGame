import java.util.*;
import java.awt.*;

//複数の当たり判定を管理するクラス
public class ManyHitters{
    //全ての当たり範囲の基準座標
    private int x=0,y=0;
    private ArrayList Hitters = new ArrayList();

    //getter/setter
    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}

    //コンストラクタ
    public ManyHitters(){}

    //当たり範囲を追加
    public void add(Hitter hitter){
	Hitters.add(hitter);
    }

    //移動による当たり範囲の移動の更新
    public void setCoordinate(int x, int y){
	for(int i=0;i<Hitters.size();i++){
	    Hitter checker = (Hitter)Hitters.get(i);
	    checker.setCoordinate(x,y);
	}
    }

    //当たり判定群同士でぶつかっているかどうかを調べる群→群
    public boolean collisionCheck(ManyHitters manyhitters){
	//保持している全ての当たり判定群と相手の当たり判定群を比べる
	for(int i=0;i<Hitters.size();i++){
	    Hitter checker = (Hitter)Hitters.get(i);
	    //相手の群→個のチェッカーを呼び出す
	    if(manyhitters.collisionCheck(checker)){return true;}
	}
	//全て当たっていなければ当たっていないのでfalseを返す
	return false;
    }

    //ある当たり判定１つと自分が保持している当たり判定達が当たっているかを調べる、群→個
    public boolean collisionCheck(Hitter hitter){
	//保持している全ての当たり範囲に対して
	for(int i=0;i<Hitters.size();i++){
	    Hitter checker = (Hitter)Hitters.get(i);
	    //ひとつでも当たっていれば当たりなので、trueを返す
	    if( checker.collisionJudge(hitter) ){return true;}
	}
	//全て調べて当たっていなければ、外れているのでfalseを返す
	return false;
    }

    //描写
    public void draw(Graphics g){
	for(int i=0;i<Hitters.size();i++){
	    Hitter drawer = (Hitter)Hitters.get(i);
	    drawer.draw(g);
	}
    }
    //draw coordinate move
    public void moveDrawCoordinate(int StageX,int StageY){
	for(int i=0;i<Hitters.size();i++){
	    Hitter drawer = (Hitter)Hitters.get(i);
	    drawer.updateDrawCoordinate(StageX,StageY,drawer.getX(),drawer.getY());
	}

    }
}



