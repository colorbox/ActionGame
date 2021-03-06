import java.lang.Math;
import java.awt.*;
import java.applet.Applet;


public class Pointer{
    //limit of gravity
    private double GravityLimit = 7.0;
    //1度の角度
    private static double rad1 = Math.PI/180;
    //ベクトルの角度
    private double Rad;
    //ベクトルの大きさ
    private double Force;

    //getter
    public double getGravityLimit(){return GravityLimit;}
    public double getForce(){return Force;}
    public double getRad(){return Rad;}

    //setter
    //力のセット
    public void setForce(double Force){
	this.Force=Force;
	forceRevision();
    }

    //角度のセット(double)
    public void setRad(double Rad){
	this.Rad=Rad;
	this.radRevision();
    }
    //角度のセット(int)
    public void setRad(int Rad){
	this.Rad=Rad*rad1;
	this.radRevision();
    }
    public void setGravityLimit(double GravityLimit){this.GravityLimit=GravityLimit;}

    //コンストラクタ
    public Pointer(double Rad,double Force){
	setRad(Rad);
	setForce(Force);
	forceRevision();
    }

    //力補正(+15.0〜-15.0)
    private void forceRevision(){
	//System.out.println(getForce());
	if(getForce()>GravityLimit){
	    setForce(GravityLimit);
	}else if(getForce()<-1*GravityLimit){
	    setForce(-1*GravityLimit);
	}
    }

    //角度補正(+Pi〜-Piの間)
    private void radRevision(){
	if(Rad>Math.PI){
	    Rad-=2*Math.PI;
	}
	if(Rad<-1*Math.PI){
	    Rad+=2*Math.PI;
	}
    }

    //角度増加double
    public void radConvert(double conv){
	setRad((getRad() + conv)%(2*Math.PI));
	radRevision();
    }

    //角度増加int
    public void radConvert(int conv){
	setRad((getRad() + conv * rad1)%(2*Math.PI));
	radRevision();
    }

    //力変更
    public void forceConvert(double conv){
	setForce(getForce()+conv);
    }

    //パラメータセット
    public void setParam(double Rad,double Force){
	setRad(Rad);
	setForce(Force);
    }

    //描写
    public void draw(Graphics g,double x,double y){
	int pointingx=(int)( x + Force * Math.cos(Rad) );
	int pointingy=(int)( y + Force * Math.sin(Rad) );
	g.setColor((new Color(0, 200,0)));
	g.drawLine( (int)x, (int)y,pointingx ,pointingy );
	g.fillOval(pointingx-2,pointingy-2,4,4);
    }

    //ベクトルの合成
    public void vectorCombination(Pointer p2){
	double VecX = this.getForce() * Math.cos(this.getRad())  + p2.getForce() * Math.cos(p2.getRad());
	double VecY = this.getForce() * Math.sin(this.getRad())  + p2.getForce() * Math.sin(p2.getRad());
	double NewRad,NewForce;
	NewRad=-1*Math.atan2(VecX,VecY)+Math.PI/2;
	NewForce=Math.hypot(VecX,VecY);
	this.setParam(NewRad,NewForce);
	radRevision();
    }

    //ベクトル縦成分抽出
    public void verticleExtract(){
	double VecY = this.getForce() * Math.sin(this.getRad());
	double NewRad,NewForce;
	NewRad = -1 * Math.atan2( 0.0 , VecY ) + Math.PI/2;
	NewForce = Math.hypot( 0.0 , VecY );
	this.setParam( NewRad , NewForce );
    }

    //左方向成分を除去
    public void verticleExtractRight(){
	//ベクトルの方向が左なら横成分を除去
	if(this.getRad()<-1*Math.PI/2 || this.getRad()>Math.PI/2){
	    verticleExtract();
	}
    }

    //右方向成分を除去
    public void verticleExtractLeft(){
	//ベクトルの方向が右なら、横成分を除去
	if(this.getRad()>-1*Math.PI/2 || this.getRad()<Math.PI/2){
	    verticleExtract();
	}
    }

    //ベクトル横成分抽出
    public void sideExtract(){
	double VecX = this.getForce() * Math.cos(this.getRad());
	double NewRad,NewForce;
	NewRad=-1*Math.atan2( VecX , 0.0 ) + Math.PI/2;
	NewForce=Math.hypot( VecX , 0.0 );
	this.setParam( NewRad , NewForce );
    }

    //下方向成分を除去
    public void sideExtractUp(){
	if(this.getRad()>0.0){
	    sideExtract();
	}
    }

    //上方向成分を除去
    public void sideExtractDown(){
	if(this.getRad()<0.0){
	    sideExtract();
	}
    }
}
