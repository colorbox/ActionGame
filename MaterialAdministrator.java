import java.util.*;
import java.applet.Applet;
import java.awt.*;

public class MaterialAdministrator{

    //管理対象となる物体
    private ArrayList Materials = new ArrayList();
    //コンストラクタ
    public MaterialAdministrator(){}
    //物体を新しく追加する。
    public void add(Material material){
	this.Materials.add(material);
    }

    //物体リストから、消滅した物体を削除する
    public void checkVanish(){
	//消えてる物体の削除
	for(int i=0;i<Materials.size();i++){
	    Material material = (Material)Materials.get(i);
	    if(material.isVanish()){
		Materials.remove(i);
	    }
	}
    }

    //物体に関する全処理をこのメソッドで行う
    public void allOperation(int[][] Stage,Graphics g){
	checkVanish();
	allMove();
	allRevision(Stage);
	allDraw(g);
    }

    //移動
    public void allMove(){
	for(int i=0;i<Materials.size();i++){
	    Material material = (Material)Materials.get(i);
	    material.move();
	}
    }

    //補正
    public void allRevision(int[][] Stage){
	for(int i=0;i<Materials.size();i++){
	    Material material = (Material)Materials.get(i);
	    material.moveRevision(Stage);
	}
    }

    //描写
    public void allDraw(Graphics g){
	for(int i=0;i<Materials.size();i++){
	    Material material = (Material)Materials.get(i);
	    material.draw(g);
	}
    }

    public int size(){
	return Materials.size();
    }

    //管理している物体との当たり判定
    //複数の当たり判定を持つ物体との判定はどうするか？
    //→物体側に判定を行うメソッドを持たせて、当たり範囲を複数持つサブクラスでそれらをオーバーライドさせる。
    //→Materialとして扱ってそれらを呼べば、適切に処理されるようにする、当たる方、当てられる方両方ある、呼び出し方に気をつける。
    /*
    public void checkCollision(){
	for(int i=0;i<Materials.size();i++){
	    Material material = (Material)Materials.get(i);;
		//if(Material)
	}
    }
    */
}
