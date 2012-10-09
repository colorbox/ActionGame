public class TestMaterialManager{
    
    MaterialManager MM = new MaterialManager();
    
    public static void main(String[] args){
	TestMaterialManager testee = new TestMaterialManager();
	testee.MM.add(new Material(10.0,10.0,0.0,10.0,true));
	testee.MM.add(new Material(10.0,10.0,0.0,10.0,true));
	testee.MM.add(new Material(10.0,10.0,0.0,10.0,true));
	testee.MM.add(new Material(10.0,10.0,0.0,10.0,true));
	testee.MM.add(new Material(10.0,10.0,0.0,10.0,true));
	testee.MM.add(new Character(10.0,10.0,0.0,10.0,true));
    }
}
