import java.util.*;
import java.applet.Applet;
import java.awt.*;

public class EnemyBehaviorAdministrator{
    MaterialAdministrator ma;

    //Enemy ArrayList
    private ArrayList Enemies = new ArrayList();

    //add to ArrayList
    public void add(Enemy enemy){
	Enemies.add(enemy);
    }

    //constructor
    public EnemyBehaviorAdministrator(MaterialAdministrator ma){
	this.ma=ma;
    }

    public void enemiesBehavior(int Time){
	for(int i=0;i<Enemies.size();i++){
	    Enemy enemy = (Enemy)Enemies.get(i);
	    //enemy.getBehavior().behaviorMove(Time,enemy,ma);
	    enemy.getBehavior().moveAndAttack(Time,enemy,ma);
	}
    }
    
}
