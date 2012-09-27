import java.util.*;
import java.lang.Math;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Ballet extends Material{
    int Damage;

    public void setDamage(int Damage){this.Damage=Damage;}
    public int getDamage(){return Damage;}

    //コンストラクタ
    public Ballet(double x,double y,double rad,double force,boolean isEnemy){
	super(x,y,rad,force,isEnemy);
	setDamage(1);
	setFlying(true);
	setWidth(8);
	setHeight(8);
    }
    //Balletは弾、弾なので、飛んで行ったり当たり判定のための範囲が設定されてる、
    //でも壁に当たると消えたりする処理が必要。
    //弾は壁に当たると消える。

    public void wallingLeftOperation(Stage Stage){
	//System.out.println("Left");
	setVanish(true);
    }

    public void wallingRightOperation(Stage Stage){
	//System.out.println("Right");
	setVanish(true);
    }

    public void roofingOperation(Stage Stage){
	//System.out.println("roof");
	setVanish(true);
    }

    public void landingOperation(Stage Stage){
	//System.out.println("land");
	setVanish(true);
    }

    //when hitted
    //when hit
    public void hitedBallet(Ballet ballet){
	System.out.println("hit in Ballet with argument Ballet");
    }
    public void hitedCharacter(Character Character){
	System.out.println("hit in Ballet with argument Enemy");
	setVanish(true);
    }

    /*
    public void hitedPlayer(Player player){
	System.out.println("hit in Ballet with argument Player");
    }
    */

}
