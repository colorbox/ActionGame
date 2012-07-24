public class Enemy extends Character{
    //character's behavior
    private Behavior behavior;
    public void setBehavior(Behavior behavior){this.behavior=behavior;}
    public Behavior getBehavior(){return behavior;}


    public Enemy(double x,double y,double rad,double force){
	super(x,y,rad,force,true);
    }

    //API for Behavior
    public void neutral(){}
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
    public void attack(){}

}

