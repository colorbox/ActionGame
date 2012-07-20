import java.awt.Point;
import java.util.*;
import java.lang.Math;
import java.awt.*;
import java.applet.Applet;

public class Stage{
    //Stage information hold Block arrangement
    private Block[][] TheStage;
    private Point Coordinate = new Point(0,0);

    //getter/setter
    public int getPointTag(int x,int y){return TheStage[y][x].getTag();}
    public int getXLength(){return TheStage[0].length;}
    public int getYLength(){return TheStage.length;}
    public int getStageHeight(){return TheStage.length*16;}
    public int getStageWidth(){return TheStage[0].length*16;}
    public int getX(){return (int)Coordinate.getX();}
    public int getY(){return (int)Coordinate.getY();}

    //constracter
    public Stage(int[][] Tags){
	TheStage = new Block[Tags.length][Tags[0].length];
	for(int i=0;i<Tags.length;i++){
	    for(int j=0;j<Tags[0].length;j++){
		TheStage[i][j]= new Block(j*16,i*16,Tags[i][j]);
	    }
	}
    }

    //draw Stage
    public void draw(Graphics g){
	/*
	//ステージの位置から描写位置を割り出す。
	//ステージの座標を取得
	int x = getX(),y = getY();
	int difx=24,dify=24;
	//描写する長さは画面の大きさ352*352よりも四辺が一ブロック分長い384*384、22*22→24*24の部分を描写する。
	//どのブロック位置から描写するかを算出する
	x = (int)(x- x%16)/16;
	y = (int)(y- y%16)/16;
	//for文を開始のブロック位置から始めて+24の位置まで描写するように変更する
	if(TheStage.length<24){
	    dify=22;
	}
	if(TheStage[0].length<24){
	    difx=22;
	}
	System.out.println("x:"+x+"y:"+y+"difx:"+difx+"dif:"+dify);
	*/
	for(int i=0;i<TheStage.length;i++){
	for(int j=0;j<TheStage[0].length;j++){
	//for(int i=y;i<y+dify;i++){
	//for(int j=x;j<x+difx;j++){
		TheStage[i][j].draw(g);
	    }
	}
    }

    //update each Block of Stage
    public void moveBlocks(){
	for(int i=0;i<TheStage.length;i++){
	    for(int j=0;j<TheStage[0].length;j++){
		TheStage[i][j].updateDrawCoordinate(getX(),getY(),TheStage[i][j].getX(),TheStage[i][j].getY());
	    }
	}
	//check coordinate
	//System.out.println(getX()+","+getY()+","+TheStage[3][3].getX()+","+TheStage[3][3].getY()+","+TheStage[3][3].getDrawX()+","+TheStage[3][3].getDrawY()+",");

    }

    //update Stage Coordinate
    public void updateCoordinate(int x, int y){
	Coordinate.move(x,y);
    }

    //camera move linked with Player move
    public void move(int Px,int Py){
	//update coordinate
	updateCoordinate(Px,Py);

	//X
	if(Px>176 && Px<getStageWidth()-176){
	    //center:Camera move
	    updateCoordinate(Px-176,getY());
	}else if(Px>=getStageWidth()-176){
	    //right:Camera stable
	    updateCoordinate(getStageWidth()-352,getY());
	}else if(Px<=176){
	    //left:Camera stable
	    updateCoordinate(0,getY());
	}

	//Y
	if(Py>176 && Py<getStageHeight()-176){
	    //center:camera move
	    updateCoordinate(getX(),Py-176);
	}else if(Py>=getStageHeight()-176){

	    //System.out.println("tes:"+Py);

	    //bottom:camera stable
	    updateCoordinate(getX(),getStageHeight()-352);
	}else if(Py<=176){
	    //top:camera stable
	    updateCoordinate(getX(),0);
	}

	//move Blocks of Stage
	moveBlocks();

	//System.out.print("Stage:"+getX()+","+getY()+":");
	//System.out.println("Player:"+Px+","+Py);
	
    }

}
