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
	for(int i=0;i<TheStage.length;i++){
	    for(int j=0;j<TheStage[0].length;j++){
		TheStage[i][j].draw(g);
	    }
	}
    }

    //update Stage Coordinate
    public void updateCoordinate(int x, int y){
	Coordinate.move(x,y);

	System.out.print(getX()+","+getY()+":");
	System.out.println(x+","+y);
    }

    //camera move
    public void move(int Px,int Py){
	//update coordinate
	updateCoordinate(Px,Py);

	if(Px>168 && Px<getStageWidth()-168){
	    Coordinate.move(Px,getY());
	}else if(Px>=getStageWidth()-168){
	    Coordinate.move(getStageWidth()-168,getY());
	}else if(Px<=168){
	    Coordinate.move(Px,getY());
	}
	
	if(Py>168 && Py<getStageHeight()-168){
	    Coordinate.move(getX(),Py);
	}else if(Py>=getStageHeight()-168){
	    Coordinate.move(getX(),getStageHeight()-168);
	}else if(Py<=168){
	    Coordinate.move(getX(),0);
	}
	
    }

}
