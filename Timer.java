public class Timer{
    //時間を保持
    private int Time=0;
    //コンストラクタ
    public Timer(int Time){
	this.Time=Time;
    }
    //getter/setter
    public int getTime(){return Time;}
    public void setTime(int Time){this.Time=Time;}
    //時間経過
    public void timePassing(){
	Time++;
    }

}
