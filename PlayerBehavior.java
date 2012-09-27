//どんな動きをするのかはPlayerに全て記述し、Behaviorはそれを呼び出す形で動作させる。PlayerのAPI的なものを作るという事ではなかろーか
public class PlayerBehavior extends Behavior{
    //プレイヤーの振る舞い、移動時
    

    public void playerMoveBehavior(Controller Controller,Character Player,MaterialAdministrator ma){
	//スティック操作
	int stick = Controller.getStick();
	//着地状態なら操作可能
	if(Player.getLanding()){
	    if(stick == 2){
	    }else if(stick == 4){
		Player.moveBackward();
	    }else if(stick == 5){
		Player.neutral();
	    }else if(stick == 6){
		Player.moveForward();
	    }else if(stick == 7){
		//接天井状態ならジャンプしない
		if( !Player.getRoofing() )
		Player.jumpBackward();
	    }else if(stick == 8){
		if( !Player.getRoofing() )
		Player.jumpNeutral();
	    }else if(stick == 9){
		if( !Player.getRoofing() )
		Player.jumpForward();
	    }
	}
	if(Controller.getZPress()){
	    //Characterだとattackメソッドに対応したものがないのでPlayerクラスにキャストする
	    ((Player)Player).attack(ma);
	}
    }
    /*
    //プレイヤーの振る舞い、分岐
    public void playerBehavior(Controller Controller,Player Player,MaterialAdministrator ma){
	behaviorMove(Controller,Player,ma);
    }
    */
}
//やりたいこと：ボタンを押す！：5フレームに一回発射判定、今後もメンテしやすいように追加する、プレイヤーがごちゃってるので分離したい
//ふるまい、プレイヤーのごちゃごちゃをクラス化したい、コントローラを渡して、なにをすべきかを記述、ベクトル操作、弾発射、攻撃、防御、
//Bridgeパターン？キャラの動きの機能と、キャラの動きの実装を分ける？いけるか？Playerが機能でBehaviorが実装？
