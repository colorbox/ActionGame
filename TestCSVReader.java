
public class TestCSVReader{

    public static CSVReader test = new CSVReader("Data/Stage/testStage.csv");

    public static void main(String[] args){
	int[][] tester = test.getStageData();
	for(int i=0;i<tester.length;i++){
	    for(int j=0;j<tester[0].length;j++){
		System.out.print(tester[i][j]+",");
	    }
	    System.out.println();
	}
    }
}
