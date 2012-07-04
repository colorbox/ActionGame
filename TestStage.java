public class TestStage{
    public Stage Stage;


    public void TestPrint(){
	for(int i=0;i<Stage.getYLength();i++){
	    for(int j=0;j<Stage.getXLength();j++){
		System.out.print(Stage.getPointTag(j,i));
	    }
	    System.out.println();
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

    }
}
