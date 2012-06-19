//120329:当たり判定は矩形で、物体の移動はベクトルで行う分担分けをする
//120404:Materialに場外判定を付ける、publicなのでprivateにするための作業
//120415:プレイヤーの移動モードの切り替え、pointingがごちゃっとしてきたのでリファる。
//120417:プレイヤーの移動を実装、ベクトルが砲台用に使われているので、そこを何とかする、移動用のベクトルと発射用のベクトルとを分ける。
//120423:プレイヤー操作について、キーボードのイベント受け取りとパラメータの操作になにか一枚噛ませたほうがいいんじゃね？パラメータを操作するコードが複数メソッドにまたがっててうぜえ、コントローラクラスを新規作成、キーイベントを受け取り、ゲームにとって理解可能な命令に置き換える。命令を置き換えるだけのクラスじゃあっても意味ないどころかコードの冗長性がましてしにたくなるだけ、もっといい事が欲しい。次はいよいよベクトル計算をやりたい。
//120424:描画を分けよう、矢印を表示するクラスを別に作るのだ,ベクトルの合成がわからぬ、平面座標から極座標へ変換する方法がいまいちわからん、三角関数の逆関数でも使うのか？コントローラが行き詰まる、２つの方向キー同時押しに対応できねえ、解決策が必要
//120425:ベクトルの合成が半分完了、角度が何か違う感じ、付け焼刃でatan2()を使った報いやね、appletの座標系をチェックして、自動でテストする関数を作ろう。角度だ、とにかく角度が問題、乗ってる世界事態の座標系、角度をチェックしねーとアカンで
//120427:なんとかベクトル合成終了、ベクトルを扱う部分が分散しているのでひとつにまとめるリファクタリングを行う、pointerにはgettersetterを持たせてmaterialからそいつらを使って角度の変更を行う。関数名に注意。
//120501:いよいよ衝突判定の実装、その下準備としてキャラを四角く描画する。バグ、弾の重力影響計算を分けてやっているが、発射タイミングによっては弾の重力制御が1フレーム分ずれて弾の軌道がずれることがある、コントローラクラスから間接的に操作させることでバグは取れるものと思われる。
//120503:コントローラーに躓く、KeyPressとKeyReleaseの仕様に苦しめられた。自由落下モードの時はスティックの操作を不可能にする
//120507:1フレームごとにボタンの判定を行なっているので、ボタン押しすぎる問題が発生(弾が一度に4発くらい出る、モードチェンジが複数回為される)、数フレームごとに押させる仕組みが必要。
//120511:ボタンを5フレームに一回押すように判定させたい、弾が出すぎてる,そのような制限機能はControllerに持たせるべきかBehaviorに持たせるべきか・・・？
//120515:着地関係の一歩目、ベクトルの補正、壁にぶつかった時などにベクトルを補正する、縦横方向のベクトルが取り出せりゃ終わり
//120520:めり込んだ状態で動けちゃうので何とかしたい、4方向にぶつかった、めり込んだ時点で位置を補正してやらねばならない、座標の補正を各種Operationに持たせる。
//120522:物体の座標の補正がうまく行かない、補正された無いはずの弾と同じように動く→弾と共通のコードが何かいたしている可能性大、それさえできればめり込みの問題も解消される、あとはリファクタリングしよう。
//120528:物体の衝突問題、だいたい解決、原因はSensorDot、座標関連の情報をしっかり把握してなかったのが問題、要は俺のミス、天井にぶつかった時の補正と、接地かつ接天井の状態の時の処理を何とかしたい、
//120531:弾とか敵とかを実装する前段階として、当たり判定のクラスを作成、お次はMaterialに当たり範囲を実装してCharaを作り、Player、Enemyを実装という流れ、さらに管理者も作りたい。管理者の処理が微妙に定まってないのでちゃんと定めて取り掛かりたい
//120602:MaterialAdministratorの実装をしよう、現行のPlayerは一旦無視、物体管理者に物体を追加してきっちり動くかを確かめるノダー、
//120602:相対範囲を導入したらHitterのあたり範囲がよくわからんくなった。そこらへんをきっちり整理しろ
//120605:物体管理者に全ての物体の管理を一任させた。キャラとそいつが生成する弾は同じ扱い、プレイヤーか、敵かは、登録時にタグか何かをつけて判別するようにする。
//120606:当たり判定が動いてないくせェー、色々チェックしろ、隅から隅まで全部だ！MAの衝突判定あたりがおかしいくさい。MAの適切なリストに物体が振り分けられてないバグは直した。
//120608:当たり判定に関するバグ取りは終了。新しい関数作ったらそのTestもしようね。Materilaに管理者を保持させるのはやめにした。逐一管理者のaddを呼び出すほうがいいだろうという判断。次はダメージとかそういうのやろう。ダメージってことは敵がいるよね、それやろう。
//120611:Behaviorを少しいじる。次はそろそろ敵の追加、それともステージの実装？ステージの実装のほうを先にやるべきだろう、なので次はそっちをやる。
//120612:Stage作成開始、しかし、その前に壁への激突判定で不安なところがあったので修正、コードをいじって対応したかったが、無理だった、今後の政策に不安が残る、センサの位置をずらして対応。
//120614:センサ関連のバグ取り、リファクタリングするつもりだったが、座標情報の更新をきっちりしたら思い通りに動いた、前回いじった時にそこが正常に動作しなかったのは何だったんや・・・ステージの作成を始める、テストもきっちり作ってそれなりに順調
//120619:ステージのデータを外部CSVから読み込めるようにしたい、CSVReaderを作り始める。


import java.awt.*;
import java.applet.Applet;
import java.util.Random;
import java.awt.event.*;

public class main extends Applet implements Runnable,KeyListener{
    //テスト用物体管理者
    MaterialAdministrator ma = new MaterialAdministrator();
    //オフスクリーンバッファのグラフィックコンテキスト
    Graphics gBuf;
    Image imgBuf;
    Thread thread = null;
    int w, h;
    //自機
    Player Player = new Player(200.0,200.0,0.0,0.0);
    //コントローラー
    Controller Controller =new Controller();
    //振る舞い
    Behavior Behavior = new Behavior();
    //タイマー
    Timer Timer = new Timer(0);
    //ステージデータ
    int[][] Stage ={
	{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
	{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,},
	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,},
	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,},
	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,},

	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,},
	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,},
	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,},
	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,},
	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,},

	{1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,},
	{1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,},
	{1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,},
	{1,0,1,1,1,0,0,0,0,0,1,0,0,0,0,0,0,1,1,0,0,1,},
	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,},

	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,},
	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,},
	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,1,},
	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,},
	{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
	{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
    };
    Stage StageData=new Stage(Stage);

    //アプレットの初期化時呼び出される
    public void init(){
	//アプレットサイズ取得
	Dimension d = getSize();
	this.w = d.width;
	this.h = d.height;
	//バッファ生成
	imgBuf = createImage(w, h);
	//グラフィックコンテキスト取得
	gBuf = imgBuf.getGraphics();
	//キーボードリスナー登録
	addKeyListener(this);
	//物体管理者の物体追加
	ma.add(new Material(100,100,0.0,0.0,true));
	//new Material(200,50,0.0,0.0,ma,true);
	//new Ballet(300,50,0.0,0.0,ma,true);
	//Player追加
	ma.add(Player);
    }

    //背景イメージ描画メソッド
    private void drawBackImage(Graphics g){
	//draw with stage
	StageData.draw(g);
    }

    //アプレットが表示されると呼び出される
    public void start(){ 
	if(thread == null){
	    //スレッドの生成
	    thread = new Thread(this);
	    //スレッドを開始させ、runメソッドを呼び出す
	    thread.start();
	}
    }

    //キー押下時
    public void keyPressed(KeyEvent e){
	Controller.keyPressed(e,Timer.getTime());
    }

    //キー離し
    public void keyReleased(KeyEvent e){
	Controller.keyReleased(e,Timer.getTime());
    }

    //keyTyped
    public void keyTyped(KeyEvent e){}

    //メインルーチン
    public void run(){
	//背景画像の作成
	Image imgBack = createImage(w, h);
	//グラフィックコンテキスト取得
	Graphics gBack = imgBack.getGraphics();


	drawBackImage(gBack);


	//グラフィックコンテキスト破棄
	gBack.dispose();

	while(thread != null){

	    //背景画像をバッファに描画
	    gBuf.drawImage(imgBack, 0, 0, this);

	    //自機の操作
	    Behavior.playerBehavior(Controller,Player,ma);

	    //物体管理者の物体管理
	    ma.allOperation(StageData,gBuf);

	    //画面の強制更新
	    repaint();

	    //コントローラーの値を全てリセット
	    Controller.buttonReset();

	    try{
		//0.016秒間(約1フレーム)スリープ。これを忘れるとハングアップする
		Thread.sleep(17);
		Timer.timePassing();
	    }catch(InterruptedException e) {}
	}
    }
    //オーバーライドして最低限のことだけをする
    public void update(Graphics g){
	paint(g);
    }
    public void paint(Graphics g){
	//バッファを画面に描画
	g.drawImage(imgBuf, 0, 0, this);
    }
    //アプレットが画面から消えると呼び出される
    public void stop(){
	if(thread != null){
	    thread = null;
	}
    }
}
