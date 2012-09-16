public class Character extends Material{
    //HP
    private int HP;
    //getter/setter
    public void setHP(int HP){this.HP=HP;}
    public int getHP(){return HP;}
    //コンストラクタ
    public Character(double x,double y,double rad,double force,boolean isEnemy){
	super(x,y,rad,force,isEnemy);
    }

    //死亡処理
    public void deathOperation(){
	if(getHP()<0){
	    setVanish(true);
	}
    }

    //API群
    //API for Behavior
    public void neutral(){System.out.println("neutral");}
    public void moveForward(){System.out.println("moveForward");}
    public void moveBackward(){System.out.println("moveBackward");}
    public void jumpForward(){System.out.println("jumpForward");}
    public void jumpNeutral(){System.out.println("jumpNackward");}
    public void jumpBackward(){System.out.println("jumpNeutral");}
    public void attack(){System.out.println("attack");}
}
