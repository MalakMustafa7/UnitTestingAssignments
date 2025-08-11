package stopwatch;
public class StopWatch {
    private int minutes=0;
    private int hours = 0;
    private int days = 0;
    private int dailyWorkingHours=24 ;
    public StopWatch(){

    }
    public StopWatch(int dailyWorkingHours){
        this.dailyWorkingHours=dailyWorkingHours;
    }

    public void record(int minutes) {
        if (minutes < 0) return;

        this.minutes += minutes;

        if (this.minutes >= 60) {
            this.hours += this.minutes / 60;
            this.minutes = this.minutes % 60;
        }

        if (this.hours >= dailyWorkingHours) {
            this.days += this.hours / dailyWorkingHours;
            this.hours = this.hours % dailyWorkingHours;
        }

    }

    public int getMinutes(){
        return this.minutes;
    }

    public int getHours() {
        return this.hours;
    }
    public  int getDays(){
        return this.days;
    }
}
