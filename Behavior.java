
//どんな動きをするのかはPlayerに全て記述し、Behaviorはそれを呼び出す形で動作させる。PlayerのAPI的なものを作るという事ではなかろーか
public class Behavior{
    private boolean Movemode=false;
    public boolean getMoveMode(){return Movemode;}

    //モード変更
    public void changeMode(){
	if(Movemode){
	    Movemode=false;
	}else{
	    Movemode=true;
	}
    }

    //プレイヤーの振る舞い、非移動時
    public void playerBehaviorNotMove(Controller Controller,Player Player){
	//スティック操作
	int stick = Controller.getStick();
	if(stick == 6){Player.getCannon().radConvert(+5);}
	if(stick == 4){Player.getCannon().radConvert(-5);}
	if(stick == 8){Player.getCannon().forceConvert(3.0);}
	if(stick == 2){Player.getCannon().forceConvert(-3.0);}
	//ボタン操作
	if(Controller.getZPress()){
	    Player.launchMaterial();
	}
	if(Controller.getXPress()){
	    changeMode();
	}
    }

    //プレイヤーの振る舞い、移動時
    public void playerBehaviorMove(Controller Controller,Player Player){
	//スティック操作
	int stick = Controller.getStick();
	//着地状態なら操作可能
	if(Player.getLanding()){
	    if(stick == 2){
		Player.setForce(2.0);
		Player.setRad(90);
	    }else if(stick == 4){
		Player.moveBackward();
	    }else if(stick == 5){
		Player.neutral();
	    }else if(stick == 6){
		Player.moveForward();
	    }else if(stick == 7){
		Player.jumpBackward();
	    }else if(stick == 8){
		Player.jumpNeutral();
	    }else if(stick == 9){
		Player.jumpForward();
	    }
	}
	//ボタンの処理
	if(Controller.getXPress()){
	    changeMode();
	    //移動時にモードを変える時に動きっぱなしにならないようにする
	    if(Player.getLanding()){
		Player.setForce(0.0);
	    }
	}
    }

    //プレイヤーの振る舞い、分岐
    public void playerBehavior(Controller Controller,Player Player){
	if(getMoveMode()){
	    playerBehaviorMove(Controller,Player);
	}else{
	    playerBehaviorNotMove(Controller,Player);
	}
    }

}
//やりたいこと：ボタンを押す！：5フレームに一回発射判定、今後もメンテしやすいように追加する、プレイヤーがごちゃってるので分離したい
//ふるまい、プレイヤーのごちゃごちゃをクラス化したい、コントローラを渡して、なにをすべきかを記述、ベクトル操作、弾発射、攻撃、防御、
//Bridgeパターン？キャラの動きの機能と、キャラの動きの実装を分ける？いけるか？Playerが機能でBehaviorが実装？
