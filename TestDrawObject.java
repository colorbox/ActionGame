public class TestDrawObject{
    //test object
    public DrawObject DrawObject = new DrawObject(0,0);
    
    public void confirmUpdateDrawCoordinate(int StageX,int StageY,int ObjectX,int ObjectY,int ExpectedDrawX,int ExpectedDrawY){
	//operation updateDrawCoodinate()
	DrawObject.updateDrawCoordinate(StageX,StageY,ObjectX,ObjectY);

	System.out.println(DrawObject.getDrawX() + ":" + ExpectedDrawX);
	System.out.println(DrawObject.getDrawY() + ":" + ExpectedDrawY);

	if( DrawObject.getDrawX() != ExpectedDrawX ){
	    System.out.println("actual DrawX differ from expected");
	}else{
	    System.out.println("OK!!!!");
	}

	if( DrawObject.getDrawY() != ExpectedDrawY ){
	    System.out.println("actual DrawY differ from expected");
	}else{
	    System.out.println("OK!!!!");
	}

    }


    public static void main(String[] args){
	TestDrawObject tester = new TestDrawObject();
	tester.confirmUpdateDrawCoordinate(200,200,250,250,50,50);
    }
}
