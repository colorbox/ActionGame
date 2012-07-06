public class TestStage{
    public Stage Stage;
    
    //test print
    public void TestPrint(){
	for(int i=0;i<Stage.getYLength();i++){
	    for(int j=0;j<Stage.getXLength();j++){
		System.out.print(Stage.getPointTag(j,i));
	    }
	    System.out.println();
	}
    }

    //confirm move
    public void confirm_move(int Px,int Py,int ExpectedX,int ExpectedY){
	Stage.move(Px,Py);
	if(Stage.getX()==ExpectedX && Stage.getY()==ExpectedY){
	    System.out.println("Good("+Stage.getX()+","+Stage.getY()+"):("+ExpectedX+","+ExpectedY+")");
	}else{
	    System.out.println("Bad!("+Stage.getX()+","+Stage.getY()+"):("+ExpectedX+","+ExpectedY+")");
	}
    }

    public TestStage(Stage stage){
	Stage = stage;
    }

    public static void main(String[] args){
	//CSVReader
	CSVReader reader = new CSVReader("Data/Stage/testStage.csv");
	//load Stage
	TestStage tester = new TestStage( new Stage(reader.getStageData() ));

	tester.TestPrint();

	tester.confirm_move(175,175,0,0);
	tester.confirm_move(176,176,0,0);
	tester.confirm_move(177,177,1,0);

    }
}
