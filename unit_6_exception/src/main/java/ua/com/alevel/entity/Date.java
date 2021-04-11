package ua.com.alevel.entity;

public class Date {

    private int centuries;
    private int year = 2021;
    private int month = 1;
    private int day = 1;
    private int hour = 0;
    private int minute = 0;
    private int second = 0;
    private int millisecond = 0;

    @Override
    public String toString() {
        return "Date{" +
                "centuries=" + centuries +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", second=" + second +
                ", millisecond=" + millisecond +
                '}';
    }

    public void setCenturies(int centuries) {
        this.centuries = centuries;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setMillisecond(int millisecond) {
        this.millisecond = millisecond;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getMillisecond() {
        return millisecond;
    }

    public int getCenturies() {
        return centuries;
    }
}
