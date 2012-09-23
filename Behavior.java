//具体的な動作は抽象概念を軸としてキャラクターのクラスにベタ書きする。それらを組み合わせるのがBehavior
//Characterのクラスにベタ書きされるのは基本的な動作。Behaviorにはそれらの組み合わせを記述する。
public class Behavior{
    public Behavior(){}

    public void playerMoveBehavior(Controller Controller,Character Player,MaterialAdministrator ma){}

    public void behaviorMove(int Time,Character Character,MaterialAdministrator ma){
	if(Character.getLanding()){
	    if(Time%120 <60){
		Character.moveForward();
	    }else{
		Character.moveBackward();
	    }
	}
    }

    public void moveAndAttack(int Time,Character Character,MaterialAdministrator ma){
	if(Character.getLanding()){
	    if(Time%60 < 30){
		Character.moveForward();
	    }else if(Time%60==30){
		Character.neutral();
		Character.attack(ma);
	    }else{
		Character.moveBackward();
	    }
	}
    }

}
