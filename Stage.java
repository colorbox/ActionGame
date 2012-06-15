import java.awt.Point;
import java.util.*;
import java.lang.Math;
import java.awt.*;
import java.applet.Applet;

public class Stage{
    //Stage information hold Block arrangement
    private Block[][] TheStage;
    //Stage infrmation with integer arrangement
    private int[][] Tags;

    public int[][] getTags(){return Tags;}

    //constracter
    public Stage(int[][] Tags){
	this.Tags=Tags;
	TheStage = new Block[Tags.length][Tags[0].length];
	for(int i=0;i<Tags.length;i++){
	    for(int j=0;j<Tags[0].length;j++){
		TheStage[i][j]= new Block(j*16,i*16,Tags[i][j]);
	    }
	}
    }

    //draw Stage
    public void draw(Graphics g){
	//public void draw(){
	for(int i=0;i<TheStage.length;i++){
	    for(int j=0;j<TheStage[0].length;j++){
		TheStage[i][j].draw(g);
		//System.out.print(TheStage[i][j].getTag());
	    }
	    //System.out.println();
	}
    }


}
