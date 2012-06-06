public class TestManyHitters{
    public static void main(String[] args){
	ManyHitters unko1 = new ManyHitters(new Hitter(0,0,1,0,1));
	unko1.add(new Hitter(0,0,3,0,1));
	unko1.add(new Hitter(0,0,5,0,1));
	ManyHitters unko2 = new ManyHitters(new Hitter(0,0,3,2,1));
	unko2.add(new Hitter(0,0,5,2,1));
	System.out.println(unko1.collisionCheck(unko2));
    }
}
