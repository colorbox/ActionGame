import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class CSVReader{
    //stage data
    private int[][] StageData;
    //getter/setter
    public int[][] getStageData(){return StageData;}

    //init arrangement StageData
    private ArrayList initStageData(String str){
	try {
	    //配列の大きさ
	    int x=0;
	    int y=0;
	    // CSVデータファイルを読み込み
	    File csv = new File(str);
	    //BufferReader
	    BufferedReader br = new BufferedReader(new FileReader(csv));
	    // 最終行まで読み込む
	    String line = "";
	    ArrayList arr = new ArrayList();
	    ArrayList arrElement;
	    while ((line = br.readLine()) != null) {
		// 1行をデータの要素に分割
		StringTokenizer st = new StringTokenizer(line, ",");
		//要素格納用のリストを宣言
		arrElement = new ArrayList();
		while (st.hasMoreTokens()) {
		    //1行の各要素をリストに格納
		    arrElement.add( Integer.parseInt(st.nextToken()) );
		}
		arr.add(arrElement);
		//配列の大きさを入力
		y=arr.size();
		x=arrElement.size();
	    }
	    br.close();
	    //配列を初期化
	    StageData = new int[y][x];
	    return arr;
	} catch (FileNotFoundException e) {
	    // Fileオブジェクト生成時の例外捕捉
	    e.printStackTrace();
	} catch (IOException e) {
	    // BufferedReaderオブジェクトのクローズ時の例外捕捉
	    e.printStackTrace();
	}
	return null;
    }

    //Constractor
    public CSVReader(String str){
	//get StageData as an ArrayList
	ArrayList arr = initStageData(str);
	//System.out.println(StageData.length+","+StageData[0].length);

	//init Arrangement
	for(int i=0;i<StageData.length;i++){
	    ArrayList l = new ArrayList();
	    l = (ArrayList)arr.get(i);
	    for(int j=0;j<StageData[0].length;j++){
		StageData[i][j] = new Integer( l.get(j).toString() );
	    }
	}
    }

    public void print(){
	for(int i=0;i<StageData.length;i++){
	    for(int j=0;j<StageData[0].length;j++){
		System.out.print(StageData[i][j]+",");
	    }
	    System.out.println();
	}
    }

    public static void main(String[] args) {
	CSVReader test = new CSVReader("Data/Stage/testStage.csv");
    }

}
