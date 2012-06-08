import java.util.*;
import java.lang.Math;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Ballet extends Material{

    //コンストラクタ
    public Ballet(double x,double y,double rad,double force,boolean isEnemy){
	super(x,y,rad,force,isEnemy);
    }
    //Balletは弾、弾なので、飛んで行ったり当たり判定のための範囲が設定されてる、
    //でも壁に当たると消えたりする処理が必要。
    //弾は壁に当たると消える。

    public void wallingLeftOperation(int[][] Stage){
	//System.out.println("Left");
	setVanish(true);
    }

    public void wallingRightOperation(int[][] Stage){
	//System.out.println("Right");
	setVanish(true);
    }

    public void roofingOperation(int[][] Stage){
	//System.out.println("roof");
	setVanish(true);
    }

    public void landingOperation(int[][] Stage){
	//System.out.println("land");
	setVanish(true);
    }

}
