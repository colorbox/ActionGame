import java.util.*;
import java.applet.Applet;
import java.awt.*;

public class EnemyBehaviorAdministrator{
    //Enemy ArrayList
    private ArrayList Enemies = new ArrayList();

    //add to ArrayList
    public void add(Enemy enemy){
	Enemies.add(enemy);
    }

    //constructor
    public EnemyBehaviorAdministrator(){}

    public void enemiesBehavior(int Time){
	for(int i=0;i<Enemies.size();i++){
	    Enemy enemy = (Enemy)Enemies.get(i);
	    enemy.getBehavior().behaviorMove(Time,enemy);
	}
    }
    
}
