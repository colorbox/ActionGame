import java.util.*;
import java.applet.Applet;
import java.awt.*;


public class MaterialManager{
    //管理対象となる物体群,Character
    protected ArrayList<Material> Materials = new ArrayList<Material>();
    public MaterialManager(){}
    public void add(Material Material){
	Materials.add(Material);
    }

    //消滅処理
    public void checkVanish(){
	for(int i=0;i<Materials.size();i++){
	    Material material = Materials.get(i);
	    if(material.isVanish()){
		Materials.remove(i);
	    }
	}
    }

    //物体移動
    public void moveMaterial(){
	for(int i=0;i<Materials.size();i++){
	    Material material = Materials.get(i);
	    material.move();
	}
    }
    //物体リストに適用できる描写座標の移動
    public void drawMove(int StageX,int StageY){
	for(int i=0;i<Materials.size();i++){
	    Material material = Materials.get(i);
	    material.drawMove(StageX,StageY);
	}
    }

    //物体リストに適用できる補正
    public void revisionPosition(Stage Stage){
	for(int i=0;i<Materials.size();i++){
	    Material material = Materials.get(i);
	    material.moveRevision(Stage);
	}
    }


    //物体リストに適用できる描写
    public void draw(Graphics g){
	for(int i=0;i<Materials.size();i++){
	    Material material = Materials.get(i);
	    material.draw(g);
	}
    }



}