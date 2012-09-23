public class Character extends Material{
    //character's behavior
    private Behavior behavior;
    public void setBehavior(Behavior behavior){this.behavior=behavior;}
    public Behavior getBehavior(){return behavior;}


    //HP
    private int HP;
    //getter/setter
    public void setHP(int HP){this.HP=HP;}
    public int getHP(){return HP;}
    //コンストラクタ
    public Character(double x,double y,double rad,double force,boolean isEnemy){
	super(x,y,rad,force,isEnemy);
	setHP(3);
    }

    //死亡処理
    public void deathOperation(){
	if(getHP()<0){
	    setVanish(true);
	    System.out.println(getHP());
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
    public void attack(MaterialAdministrator ma){System.out.println("attack");}

    //when hit
    public void hitedBallet(Ballet Ballet){
	System.out.println("hit in Character with argument Ballet");
	setHP(getHP()-Ballet.getDamage());
    }

}
