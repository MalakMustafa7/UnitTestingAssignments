package stopwatch;
public class StopWatch {
    private int minutes;
    private int hours;
    private int days;
    private final int dailyWorkingHours;

    public StopWatch() {
        this(24);
    }

    public StopWatch(int dailyWorkingHours) {
        if (dailyWorkingHours <= 0) {
            throw new IllegalArgumentException("Daily working hours must be greater than zero.");
        }
        this.dailyWorkingHours = dailyWorkingHours;
    }

    public void record(int minutes) {
        if (minutes < 0) return;

        this.minutes += minutes;
        updateTimeUnits();
    }

    private void updateTimeUnits() {
        if (minutes >= 60) {
            hours += minutes / 60;
            minutes %= 60;
        }
        if (hours >= dailyWorkingHours) {
            days += hours / dailyWorkingHours;
            hours %= dailyWorkingHours;
        }
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getDays() {
        return days;
    }
}
