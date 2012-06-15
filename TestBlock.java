public class TestBlock{
    public static void testOutput(Block block){
	System.out.println("tag:"+block.getTag()+" coordinate:"+block.getCoordinate());
    }
    public static void main(String[] args){
	Block test1 = new Block(1,1,1);
	testOutput(test1);

    }
}
