import java.io.Serializable;
import java.util.Objects;

public class Date implements Serializable, Comparable<Date> {
    private int day;
    private int month;
    private int year;

    /*default constructor*/
    public  Date(){
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }

    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay(){
        return this.day;
    }

    public void setDay(int day){
        this.day = day;
    }

    public int getMonth(){
        return this.month;
    }

    public void setMonth(int month){
        this.month = month;
    }

    public int getYear(){
        return this.year;
    }

    public void setYear(int year){
        this.year = year;
    }

    public int getDays(){
        return this.year*365 + this.month*30 +this.day;
    }

    @Override
    public String toString(){
        return this.getDay()+"/"+this.getMonth()+"/"+this.getYear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;
        Date date = (Date) o;
        return getDay() == date.getDay() &&
                getMonth() == date.getMonth() &&
                getYear() == date.getYear();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDay(), getMonth(), getYear());
    }


    @Override
    public int compareTo(Date date) {
        return this.getDays() - date.getDays();
    }
}
