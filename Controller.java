import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;


public class Controller{
    //timer
    private int Time;
    //スティック
    private int stick=5;
    //Zボタン、Xボタン,STARTボタン
    private boolean ZPress=false,XPress=false,StatPress=false;
    private int ZPressTime=0,XPressTime=0,StatPressTime=0;
    //スティック
    private boolean UpPress=false,DownPress=false,LeftPress=false,RightPress=false;

    public Controller(int Time){
	setTime(Time);
    }

    //スティックの状態取得
    public int getStick(){return stick;}
    //getter
    public boolean getUpPress(){return UpPress;}
    public boolean getDownPress(){return DownPress;}
    public boolean getLeftPress(){return LeftPress;}
    public boolean getRightPress(){return RightPress;}
    public boolean getZPress(){return ZPress;}
    public boolean getXPress(){return XPress;}
    public boolean getStatPress(){return StatPress;}
    public int getZPressTime(){return ZPressTime;}
    public int getXPressTime(){return XPressTime;}
    public int getStatPressTime(){return StatPressTime;}
    public int getTime(){return Time;}
    //setter
    public void setUpPress(boolean UpPress){this.UpPress=UpPress;}
    public void setDownPress(boolean DownPress){this.DownPress=DownPress;}
    public void setLeftPress(boolean LeftPress){this.LeftPress=LeftPress;}
    public void setRightPress(boolean RightPress){this.RightPress=RightPress;}
    public void setZPress(boolean ZPress){this.ZPress=ZPress;}
    public void setXPress(boolean XPress){this.XPress=XPress;}
    public void setStatPress(boolean StatPress){this.StatPress=StatPress;}
    public void setZPressTime(int ZPressTime){this.ZPressTime=ZPressTime;}
    public void setXPressTime(int XPressTime){this.XPressTime=XPressTime;}
    public void setStatPressTime(int StatPRessTime){this.StatPressTime=StatPressTime;}
    public void setTime(int Time){this.Time=Time;}
    //ボタンのリセット
    public void buttonReset(){
	setZPress(false);
	setXPress(false);
	setStatPress(false);
    }

    //proceeding Time
    public void timeProceeding(){
	setTime(getTime()+1);
    }

    //ボタン状態判定
    public void setButton(){
	//各ボタンの状態で分岐
	//最後にボタンが押されていた時間を取得
	//現在の時間-最後に押された時間 が十分に大きく、かつボタンが押されていなければ、ボタンを押下状態にする	
	if(getTime() - ZPressTime>10 && !ZPress){
	    ZPress=true;
	    ZPressTime=getTime();
	}
	if(getTime() - XPressTime>10 && !XPress){
	    XPress=true;
	    XPressTime=getTime();
	}
    }

    //スティック状態判定
    public void setStick(){
	if(RightPress){
	    stick=6;
	}else if(LeftPress){
	    stick=4;
	}else if(UpPress){
	    stick=8;
	}else if(DownPress){
	    stick=2;
	}else{
	    stick=5;
	}
	if(RightPress && UpPress){
	    stick=9;
	}else if(UpPress && LeftPress){
	    stick=7;
	}else if(LeftPress && DownPress){
	    stick=1;
	}else if(DownPress && RightPress){
	    stick=3;
	}
    }

    //キー押下時
    public void keyPressed(KeyEvent e){
	int keycode = e.getKeyCode();
	if(keycode == KeyEvent.VK_RIGHT){
	    RightPress=true;
	}
	if(keycode == KeyEvent.VK_UP){
	    UpPress=true;
	}
	if(keycode == KeyEvent.VK_LEFT){
	    LeftPress=true;
	}
	if(keycode == KeyEvent.VK_DOWN){
	    DownPress=true;
	}
	if(keycode == KeyEvent.VK_Z && getTime() - ZPressTime>10 && !ZPress){
	    ZPress=true;
	    setZPressTime(getTime());
	}
	if(keycode == KeyEvent.VK_X && getTime() - XPressTime>10 && !XPress){
	    XPress=true;
	    setXPressTime(getTime());
	}
	setStick();	
    }

    //キー離してる時
    public void keyReleased(KeyEvent e){
	int keycode = e.getKeyCode();
	if(keycode == KeyEvent.VK_RIGHT){
	    RightPress=false;
	}
	if(keycode == KeyEvent.VK_UP){
	    UpPress=false;
	}
	if(keycode == KeyEvent.VK_LEFT){
	    LeftPress=false;
	}
	if(keycode == KeyEvent.VK_DOWN){
	    DownPress=false;
	}
	if(keycode == KeyEvent.VK_Z){
	    ZPress=false;
	}
	if(keycode == KeyEvent.VK_X){
	    XPress=false;
	}
	setStick();
    }
}


