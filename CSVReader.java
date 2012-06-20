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


    public CSVReader(String str){
	try {
	    int[][] StageData;
	    //配列の大きさ
	    int x=0;
	    int y=0;
	    // CSVデータファイル
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


	    StageData = new int[y][x];

	    System.out.println(StageData.length+","+StageData[0].length);
	    
	    for(int i=0;i<arr.size();i++){
		ArrayList l = new ArrayList();
		l = (ArrayList)arr.get(i);
		for(int j=0;j<l.size();j++){
		    //String objStr = (l.get(j).toString());
		    //int num = new Integer(l.get(j));
		    int num = new Integer((l.get(j).toString()));
		    //System.out.print(num);
		    StageData[i][j]=num;
		}
	    }


	} catch (FileNotFoundException e) {
	    // Fileオブジェクト生成時の例外捕捉
	    e.printStackTrace();
	} catch (IOException e) {
	    // BufferedReaderオブジェクトのクローズ時の例外捕捉
	    e.printStackTrace();
	}


    }
    public static void main(String[] args) {
	CSVReader test = new CSVReader("Data/Stage/testStage.csv");


	/*
	try {
	    // CSVデータファイル
	    File csv = new File("Data/Stage/testStage.csv");

	    BufferedReader br = new BufferedReader(new FileReader(csv));

	    // 最終行まで読み込む
	    String line = "";
	    while ((line = br.readLine()) != null) {

		// 1行をデータの要素に分割
		StringTokenizer st = new StringTokenizer(line, ",");

		while (st.hasMoreTokens()) {
		    // 1行の各要素をタブ区切りで表示
		    //System.out.print(st.nextToken() + ",");
		}
		//System.out.println();
	    }
	    br.close();

	} catch (FileNotFoundException e) {
	    // Fileオブジェクト生成時の例外捕捉
	    e.printStackTrace();
	} catch (IOException e) {
	    // BufferedReaderオブジェクトのクローズ時の例外捕捉
	    e.printStackTrace();
	}
	*/
    }

}
