//具体的な動作は抽象概念を軸としてキャラクターのクラスにベタ書きする。それらを組み合わせるのがBehavior
public class Behavior{
    public Behavior(){}
    public void behaviorMove(int Time,Character Character){
	if(Character.getLanding()){
	    if(Time%120 <60){
		Character.moveForward();
	    }else{
		Character.moveBackward();
	    }
	}
    }
}
