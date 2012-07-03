//描写用の座標管理を行うクラス
public class DrawObject{

    //描写用座標
    protected int DrawX=0,DrawY=0;
    
    //getter
    public int getDrawX(){return DrawX;}
    public int getDrawY(){return DrawY;}
    //setter
    public void setDrawX(int Dx){this.DrawX=DrawX;}
    public void setDrawY(int Dy){this.DrawY=DrawY;}

    //constractor
    public DrawObject(int DrawX,int DrawY){
	setDrawX(DrawX);
	setDrawY(DrawY);
    }

    //coordinate update with Stage coordinate and object coordinate
    public updateDrawCoordinate(int StageX,inta StageY,int ObjectX,int ObjectY){
	setDrawX(ObjectX-StageX);
	setDrawY(ObjectY-StageY);
    }

}
