import java.util.*;
import java.applet.Applet;
import java.awt.*;


//Player属性とEnemy属性の物体を一元管理する。当たり判定が生じるのはPlayerとEnemy間のみなので、これでよいはず
public class MaterialAdministrator{
    //管理対象となる物体群、Player属性
    private ArrayList MaterialsPlayer = new ArrayList();
    //Enemy属性
    private ArrayList MaterialsEnemy = new ArrayList();
    //コンストラクタ
    public MaterialAdministrator(){}
    //物体を新しく追加する。
    public void add(Material material){
	if(material.getIsEnemy()){
	    MaterialsEnemy.add(material);
	}else{
	    MaterialsPlayer.add(material);
	}
    }

    //物体に関する全処理をこのメソッドで行う
    public void allOperation(Stage Stage,Graphics g){
	checkVanish();
	allMove();
	allRevision(Stage);
	allDraw(g);
	checkCollision();
    }


    //物体リストに適用できる削除
    private void checkVanishPrim(ArrayList list){
	for(int i=0;i<list.size();i++){
	    Material material = (Material)list.get(i);
	    if(material.isVanish()){
		list.remove(i);
	    }
	}
    }
    //物体リストから、消滅した物体を削除する
    public void checkVanish(){
	checkVanishPrim(MaterialsEnemy);
	checkVanishPrim(MaterialsPlayer);
    }

    //物体リストに適用できる移動
    private void allMovePrime(ArrayList Materials){
	for(int i=0;i<Materials.size();i++){
	    Material material = (Material)Materials.get(i);
	    material.move();
	}
    }
    //移動
    public void allMove(){
	allMovePrime(MaterialsEnemy);
	allMovePrime(MaterialsPlayer);
    }

    //物体リストに適用できる補正
    private void allRevisionPrime(ArrayList Materials,Stage Stage){
	for(int i=0;i<Materials.size();i++){
	    Material material = (Material)Materials.get(i);
	    material.moveRevision(Stage);
	}
    }
    //補正
    public void allRevision(Stage Stage){
	allRevisionPrime(MaterialsEnemy,Stage);
	allRevisionPrime(MaterialsPlayer,Stage);
    }

    //物体リストに適用できる描写
    private void allDrawPrime(ArrayList Materials,Graphics g){
	for(int i=0;i<Materials.size();i++){
	    Material material = (Material)Materials.get(i);
	    material.draw(g);
	}	
    }
    //描写
    public void allDraw(Graphics g){
	allDrawPrime(MaterialsEnemy,g);
	allDrawPrime(MaterialsPlayer,g);
    }

    //管理している物体との当たり判定
    //複数の当たり判定を持つ物体との判定はどうするか？
    //→物体側に判定を行うメソッドを持たせて、当たり範囲を複数持つサブクラスでそれらをオーバーライドさせる。
    //→Materialとして扱ってそれらを呼べば、適切に処理されるようにする、当たる方、当てられる方両方ある、呼び出し方に気をつける。

    //Check collision Player's and Enemy's Materials
    public void checkCollision(){
	for(int i=0;i<MaterialsEnemy.size();i++){
	    ManyHitters Enemymh = ((Material)(MaterialsEnemy.get(i))).getManyHitters();
	    for(int j=0;j<MaterialsPlayer.size();j++){
		ManyHitters Playermh = ((Material)(MaterialsPlayer.get(j))).getManyHitters();
		if( Playermh.collisionCheck(Enemymh) ){
		    //当たり判定時の処理をここに書く
		    //System.out.println("あたってる");
		}
	    }
	}
    }
    
}
