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
    Player Player;
    //コントローラー
    Controller Controller =new Controller();
    //振る舞い
    Behavior Behavior = new Behavior();
    //タイマー
    Timer Timer = new Timer(0);
    //CSVReader
    CSVReader reader = new CSVReader("Data/Stage/testStage.csv");
    //load Stage
    Stage StageData=new Stage(reader.getStageData());

    //アプレットの初期化時呼び出される
    public void init(){
	//アプレットサイズ取得
	Dimension d = getSize();
	this.w = d.width;
	this.h = d.height;
	//Player init
	Player = new Player(200.0,200.0,0.0,0.0,w,h);
	//バッファ生成
	imgBuf = createImage(w, h);
	//グラフィックコンテキスト取得
	gBuf = imgBuf.getGraphics();
	//キーボードリスナー登録
	addKeyListener(this);
	//物体管理者の物体追加
	ma.add(new Material(100,100,0.0,0.0,true));
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



	while(thread != null){

	    //update Background image
	    drawBackImage(gBack);

	    //stage camera move 
	    StageData.move((int)Player.getX(),(int)Player.getY());


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
}
