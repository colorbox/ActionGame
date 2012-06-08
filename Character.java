public class Character extends Material{

    //コンストラクタ
    public Character(double x,double y,double rad,double force,boolean isEnemy){
	super(x,y,rad,force,isEnemy);
    }

    //API群
    public void neutral(){}
    public void moveForward(){}
    public void moveBackward(){}
    public void jumpForward(){}
    public void jumpNeutral(){}
    public void jumpBackward(){}
    public void attack(){}
}
