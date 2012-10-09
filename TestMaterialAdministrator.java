import java.util.*;

public class TestMaterialAdministrator{

    private MaterialAdministrator ma = new MaterialAdministrator();

    static public void main(String[] args){
	TestMaterialAdministrator Tester = new TestMaterialAdministrator();

	Tester.ma.add(new Enemy( 10.0 , 0.0 , 0.0 , 0.0 ));
	Tester.ma.add(new Player( 20.0 , 0.0 , 0.0 , 0.0 ,100 , 100,new Behavior()));
	Tester.ma.add(new Ballet( 10.0 , 0.0 , 0.0 , 0.0 , true));
	Tester.ma.add(new Ballet( 20.0 , 0.0 , 0.0 , 0.0 , false));

	
    }
}

