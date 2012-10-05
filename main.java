import java.awt.*;
import java.applet.Applet;
import java.util.Random;
import java.awt.event.*;

public class main extends Applet implements Runnable,KeyListener{
    //物体管理者
    MaterialAdministrator ma = new MaterialAdministrator();
    //グラフィック
    Graphics gBuf;
    //イメージ
    Image imgBuf;

    //描写領域の生成
    Image imgBack;
    //グラフィック生成
    Graphics gBack;


    Thread thread = null;
    //画面の大きさ
    Dimension AppletSize;
    //自機
    Player Player;
    //コントローラー
    Controller Controller =new Controller();
    //タイマー
    Timer Timer = new Timer(0);
    //CSVReader
    CSVReader reader = new CSVReader("Data/Stage/testStage.csv");
    //load Stage
    Stage StageData=new Stage(reader.getStageData());

    //アプレットの初期化時呼び出される
    public void init(){
	//キーボードリスナー登録
	addKeyListener(this);
	//アプレットサイズ取得
	AppletSize = getSize();
	//バッファ生成
	imgBuf = createImage(AppletSize.width, AppletSize.height);
	//グラフィックコンテキスト取得
	gBuf = imgBuf.getGraphics();

	//Player init
	Player = new Player(200.0,200.0,0.0,0.0,StageData.getStageWidth(),StageData.getStageHeight(),new PlayerBehavior());
	//test enemy
	Enemy enemy = new Enemy(100,100,0.0,0.0);

	//Player追加
	ma.add(Player);
	//物体管理者の敵追加
	ma.add(enemy);
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
	//game loop
	while(thread != null){

	    //draw
	    gBuf = imgBuf.getGraphics();

	    //update Background image
	    drawBackImage(gBuf);

	    //stage camera move 
	    StageData.move((int)Player.getX(),(int)Player.getY());

	    //物体管理者の物体管理
	    ma.allOperation(StageData,gBuf,Controller,Timer.getTime());

	    //画面の強制更新
	    repaint();

	    //コントローラーの値を全てリセット
	    Controller.buttonReset();

	    //グラフィックコンテキスト破棄
	    gBuf.dispose();

	    try{
		//0.016秒間(約1フレーム)スリープ。これを忘れるとハングアップする
		Thread.sleep(17);
		Timer.timePassing();
	    }catch(InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

    //オーバーライドして最低限のことだけをする
    public void update(Graphics g){
	paint(g);
    }
    public void paint(Graphics g){
	//バッファを画面に
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
