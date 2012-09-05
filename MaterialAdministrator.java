import java.util.*;
import java.applet.Applet;
import java.awt.*;


//Player属性とEnemy属性の物体を一元管理する。当たり判定が生じるのはPlayerとEnemy間のみなので、これでよいはず
public class MaterialAdministrator{
    /*
    private ArrayList<Character> Player = new ArrayList<Character>();
    private ArrayList<Character> Enemy = new ArrayList<Character>();
    private ArrayList<Ballet> PlayerBallet = new ArrayList<Ballet>();
    private ArrayList<Ballet> EnemyBallet = new ArrayList<Ballet>();
    //管理対象となる物体群、Character:0,Ballet:1,Player属性
    private ArrayList[] MaterialsPlayer = {Player,PlayerBallet};
    //Enemy属性
    private ArrayList[] MaterialsEnemy = {Enemy,EnemyBallet};
    */

    /*
    //管理対象となる物体群、Character:0,Ballet:1,Player属性
    private ArrayList[] MaterialsPlayer = {new ArrayList(),new ArrayList()};
    //Enemy属性
    private ArrayList[] MaterialsEnemy = {new ArrayList(),new ArrayList()};
    //private ArrayList[] test={new ArrayList(),new ArrayList()};
    */



    //管理対象となる物体群、Character:0,Ballet:1,Player属性
    private ArrayList[] MaterialsPlayer = {new ArrayList(),new ArrayList()};
    //Enemy属性
    private ArrayList[] MaterialsEnemy = {new ArrayList(),new ArrayList()};



    //コンストラクタ
    public MaterialAdministrator(){}
    /*
    public void add(Material material){
	System.out.println("add in Material");
	if(material.getIsEnemy()){
	    MaterialsEnemy.add(material);
	}else{
	    MaterialsPlayer.add(material);
	}
    }
    */
    //キャラを追加
    public void add(Character charactor){
	System.out.println("add Chara");
	if(charactor.getIsEnemy()){
	    MaterialsEnemy[0].add(charactor);
	}else{
	    MaterialsPlayer[0].add(charactor);
	}
    }
    //弾を追加。
    public void add(Ballet ballet){
	System.out.println("add Ballet");
	if(ballet.getIsEnemy()){
	    MaterialsEnemy[1].add(ballet);
	}else{
	    MaterialsPlayer[1].add(ballet);
	}
    }

    //物体に関する全処理をこのメソッドで行う
    public void allOperation(Stage Stage,Graphics g){
	checkCollision();
	checkVanish();
	allMove();
	allRevision(Stage);
	allDrawMove(Stage.getX(),Stage.getY());
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
	for(int i=0;i<2;i++){
	    checkVanishPrim(MaterialsEnemy[i]);
	    checkVanishPrim(MaterialsPlayer[i]);
	}
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
	for(int i=0;i<2;i++){
	    allMovePrime(MaterialsEnemy[i]);
	    allMovePrime(MaterialsPlayer[i]);
	}
    }

    //物体リストに適用できる描写座標の移動
    private void allDrawMovePrime(ArrayList Materials,int StageX,int StageY){
	for(int i=0;i<Materials.size();i++){
	    Material material = (Material)Materials.get(i);
	    material.drawMove(StageX,StageY);
	}
    }
    //描写座標の移動
    public void allDrawMove(int StageX,int StageY){
	for(int i=0;i<2;i++){
	    allDrawMovePrime(MaterialsEnemy[i],StageX,StageY);
	    allDrawMovePrime(MaterialsPlayer[i],StageX,StageY);
	}
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
	for(int i=0;i<2;i++){
	    allRevisionPrime(MaterialsEnemy[i],Stage);
	    allRevisionPrime(MaterialsPlayer[i],Stage);
	}
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
	for(int i=0;i<2;i++){
	    allDrawPrime(MaterialsEnemy[i],g);
	    allDrawPrime(MaterialsPlayer[i],g);
	}
    }

    //管理している物体との当たり判定
    //複数の当たり判定を持つ物体との判定はどうするか？
    //→物体側に判定を行うメソッドを持たせて、当たり範囲を複数持つサブクラスでそれらをオーバーライドさせる。
    //→Materialとして扱ってそれらを呼べば、適切に処理されるようにする、当たる方、当てられる方両方ある、呼び出し方に気をつける。

    //Check collision Player's and Enemy's Materials
    /*

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
    */
    //XXX
    //checkCollisionPrimeに相当するメソッドが必要。リストを受け取って各々のリスト同士で当たり判定のチェック。
    //敵キャラ-味方キャラ,敵キャラ-味方弾,味方キャラ-敵弾,味方弾-敵弾,の4つに適用できるものが相応しい。
    //当たり判定が起こるクラスによって要求される事が異なるので、個別のメソッドにしたほうが良いだろうか？
    //XXX
    //そもそも9割方同じ動きをしているクラスが3つもあって見苦しい。一つにまとめたい。
    //キャストする必要性に迫られて3つある、最初からリストの型を決めてやればキャストせずに適切にオーバーロードが為されるのでは？
    /*XXX delete
    //管理対象となる物体群、Character:0,Ballet:1,Player属性
    private ArrayList[] MaterialsPlayer = {new ArrayList(),new ArrayList()};
    //Enemy属性
    private ArrayList[] MaterialsEnemy = {new ArrayList(),new ArrayList()};
     */

    public void checkCollision(){
	checkCollision_CharaChara(MaterialsEnemy[0],MaterialsPlayer[0]);
	checkCollision_CharaBallet(MaterialsEnemy[0],MaterialsPlayer[1]);
	checkCollision_CharaBallet(MaterialsPlayer[0],MaterialsEnemy[1]);
	checkCollision_BalletBallet(MaterialsEnemy[1],MaterialsPlayer[1]);
    }

    //ここから下はうんこ
    public void checkCollision_CharaBallet(ArrayList Characters,ArrayList Ballets){
	for(int i=0;i<Characters.size();i++){
	    Material CharacterMaterial = (Material)Characters.get(i);
	    ManyHitters EnemyMH = CharacterMaterial.getManyHitters();
	    for(int j=0;j<Ballets.size();j++){
		Material BalletMaterial = (Material)Ballets.get(j);
		ManyHitters BalletMH = BalletMaterial.getManyHitters();
		if(EnemyMH.collisionCheck(BalletMH)){
		    System.out.println("there is collision with Character & Ballet.");
		}
	    }
	}
    }
    public void checkCollision_CharaChara(ArrayList Enemies,ArrayList Players){
	for(int i=0;i<Enemies.size();i++){
	    Material EnemyMaterial =(Material)Enemies.get(i);
	    ManyHitters EnemyMH = EnemyMaterial.getManyHitters();
	    for(int j=0;j<Players.size();j++){
		Material PlayerMaterial = (Material)Players.get(j);
		ManyHitters PlayerMH = PlayerMaterial.getManyHitters();
		if(EnemyMH.collisionCheck(PlayerMH)){
		    System.out.println("there is collision with EnemyCharacter & PlayerCharacter");
		}
	    }
	}
    }
    public void checkCollision_BalletBallet(ArrayList EnemyBallets,ArrayList PlayerBallets){
	for(int i=0;i<EnemyBallets.size();i++){
	    Material EnemyMaterial = (Material)EnemyBallets.get(i);
	    ManyHitters EnemyBalletsMH = EnemyMaterial.getManyHitters();
	    for(int j=0;j<PlayerBallets.size();j++){
		Material PlayerMaterial = (Material)PlayerBallets.get(j);
		ManyHitters PlayerBalletsMH = PlayerMaterial.getManyHitters();
		if(EnemyBalletsMH.collisionCheck(PlayerBalletsMH)){
		    System.out.println("there is collision with EnemyBallet & PlayerBallet");
		}
	    }
	}
    }
    //for debug
    public void checkTypeAll(){
	for(int i=0;i<2;i++){
	    checkType(MaterialsEnemy[i]);
	    checkType(MaterialsPlayer[i]);
	}
    }

    public void checkType(ArrayList arr){
	for(int i=0;i<arr.size();i++){
	    System.out.println(arr.get(i)+"is Character?:"+ (arr.get(i) instanceof Character));
	    System.out.println(arr.get(i)+"is Ballet?   :"+ (arr.get(i) instanceof Ballet));
	    System.out.println((Material)arr.get(i));
	    //T test = (T)arr.get(i);
	    //test.getManyHitters();
	}
    }



}
