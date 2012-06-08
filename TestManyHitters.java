public class TestManyHitters{
    public static void main(String[] args){
	ManyHitters unko1 = new ManyHitters();
	unko1.add(new Hitter(0,0,1,0,1));
	unko1.add(new Hitter(0,0,3,0,1));
	unko1.add(new Hitter(8,8,96,304,5));
	ManyHitters unko2 = new ManyHitters();
	unko2.add(new Hitter(0,0,3,2,1));
	unko2.add(new Hitter(8,8,100,304,5));
	System.out.println(unko1.collisionCheck(unko2));
    }
}
