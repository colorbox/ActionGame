public class Enemy extends Character{
    //character's behavior
    private Behavior behavior;
    public void setBehavior(Behavior behavior){this.behavior=behavior;}
    public Behavior getBehavior(){return behavior;}


    public Enemy(double x,double y,double rad,double force){
	super(x,y,rad,force,true);
	setBehavior(new Behavior());
	setClassName("Enemy");
    }

    //API for Behavior
    public void neutral(){
	setForce(0.0);
	setRad(0);
    }
    public void moveForward(){
	setForce(2.0);
	setRad(0);
    }
    public void moveBackward(){
	setForce(2.0);
	setRad(180);
    }
    public void jumpForward(){}
    public void jumpNeutral(){}
    public void jumpBackward(){}
    public void attack(MaterialAdministrator ma){
	ma.add(new Ballet(getX(),getY(),-1.6,15.0,true));
    }

    //when hit
    public void hitedPlayer(Player Player){
	System.out.println("hit in Enemy with argument Player");
    }
    public void hited(Material Material){
	System.out.println("hit in Enemy with argument Material");
    }
    //when hit
    public void hitedBallet(Ballet Ballet){
	System.out.println("hit in Enemy("+getHP()+") with argument Ballet("+Ballet.getDamage()+")");
	setHP(getHP()-Ballet.getDamage());
    }

}

