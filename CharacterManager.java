import Controller.*;

import java.util.*;
import java.applet.Applet;
import java.awt.*;

public class CharacterManager extends MaterialManager{

    //物体リストに適用できる死亡フラグ調査
    public void checkDeath(){
	for(int i=0;i<Materials.size();i++){
	    Character Character = (Character)Materials.get(i);
	    Character.deathOperation();
	}
    }


    //振る舞い処理//いずれEnemyとPlayerで分けるつもり
    public void behaviorOperationPlayer(Controller controller,int Time,MaterialAdministrator ma){
	//player
	for(int i=0;i<Materials.size();i++){
	    Player player = (Player)Materials.get(i);
	    player.getBehavior().playerMoveBehavior(controller,player,ma);
	}
    }
    public void behaviorOperationEnemy(int Time,MaterialAdministrator ma){
	//enemy
	for(int i=0;i<Materials.size();i++){
	    Enemy enemy = (Enemy)Materials.get(i);
	    enemy.getBehavior().moveAndAttack(Time,enemy,ma);
	}
    }





}
