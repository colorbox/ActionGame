public class TestHitter{
    public static void main(String[] args){
	Hitter hitter= new Hitter(0,0,1,0,1);
	Hitter target= new Hitter(0,0,2,0,1);
	System.out.println("(0,0,1,0,1):(0,0,2,0,1):"+hitter.collisionJudge(target));
	//target.setParam(10,10,9);
	//System.out.println(hitter.collisionJudge(target));
    }
}
