public class Character extends Material{
    //敵フラグ
    private boolean isEnemy;
    //getter/setter
    public boolean getIsEnemy(){return isEnemy;}

    public void setIsEnemy(boolean isEnemy){this.isEnemy=isEnemy;}

    //コンストラクタ
    public Character(double x,double y,double rad,double force,MaterialAdministrator ma,boolean isEnemy){
	super(x,y,rad,force,ma);
	setIsEnemy(isEnemy);
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
