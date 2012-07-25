import java.awt.*;
import java.applet.Applet;
import java.util.Random;
import java.awt.event.*;

public class main extends Applet implements Runnable,KeyListener{
    //物体管理者
    MaterialAdministrator ma = new MaterialAdministrator();
    //敵の振る舞い管理
    EnemyBehaviorAdministrator EBA = new EnemyBehaviorAdministrator();
    //オフスクリーンバッファのグラフィックコンテキスト
    Graphics gBuf;
    Image imgBuf;
    Thread thread = null;
    int width,height;
    //自機
    Player Player;
    //コントローラー
    Controller Controller =new Controller();
    //振る舞い
    PlayerBehavior PlayerBehavior = new PlayerBehavior();
    //タイマー
    Timer Timer = new Timer(0);
    //CSVReader
    CSVReader reader = new CSVReader("Data/Stage/testStage.csv");
    //load Stage
    Stage StageData=new Stage(reader.getStageData());

    //アプレットの初期化時呼び出される
    public void init(){
	//アプレットサイズ取得
	Dimension dimension = getSize();
	this.width = dimension.width;
	this.height = dimension.height;
	//Player init
	Player = new Player(200.0,200.0,0.0,0.0,StageData.getStageWidth(),StageData.getStageHeight());

	//set Player's behaviorXXX(Characterのコンストラクタと一緒にいじる)
	Player.setPlayerBehavior(PlayerBehavior);

	//バッファ生成
	imgBuf = createImage(width, height);
	//グラフィックコンテキスト取得
	gBuf = imgBuf.getGraphics();
	//キーボードリスナー登録
	addKeyListener(this);

	//test enemy
	Enemy enemy = new Enemy(100,100,0.0,0.0);


	//物体管理者の敵追加
	ma.add(enemy);
	//振る舞い管理に敵を追加
	EBA.add(enemy);

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
    //メインルーチン
    public void run(){
	//描写領域の生成
	Image imgBack = createImage(width, height);
	//グラフィック生成
	Graphics gBack;
	//game loop
	while(thread != null){
	    //draw
	    gBack = imgBack.getGraphics();
	    //update Background image
	    drawBackImage(gBack);

	    //背景画像をバッファに描画
	    gBuf.drawImage(imgBack, 0, 0, this);

	    //自機の操作XXX(Characterのコンストラクタと一緒にいじる)
	    Player.getPlayerBehavior().playerMoveBehavior(Controller,Player,ma);

	    //敵の振る舞い
	    EBA.enemiesBehavior(Timer.getTime());

	    //stage camera move 
	    StageData.move((int)Player.getX(),(int)Player.getY());


	    //物体管理者の物体管理
	    ma.allOperation(StageData,gBuf);

	    //画面の強制更新
	    repaint();

	    //コントローラーの値を全てリセット
	    Controller.buttonReset();

	    //グラフィックコンテキスト破棄
	    gBack.dispose();


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




}
