//描写用の座標管理を行うクラス
public class DrawObject{

    //描写用座標
    protected int DrawX=0,DrawY=0;
    
    //getter
    public int getDrawX(){return DrawX;}
    public int getDrawY(){return DrawY;}
    //setter
    public void setDrawX(int DrawX){this.DrawX=DrawX;}
    public void setDrawY(int DrawY){this.DrawY=DrawY;}

    //constractor
    public DrawObject(int DrawX,int DrawY){
	setDrawX(DrawX);
	setDrawY(DrawY);
    }

    //coordinate update with Stage coordinate and object coordinate
    public void updateDrawCoordinate(int StageX,int StageY,int ObjectX,int ObjectY){
	setDrawX(ObjectX-StageX);
	setDrawY(ObjectY-StageY);
    }

}
