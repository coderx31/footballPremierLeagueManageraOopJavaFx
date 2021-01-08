import java.io.Serializable;

public class SchoolFootballClub extends FootballClub implements Serializable {
    private static final int ageLimit = 18;
    private String schoolName;

    /*default constructor*/
    public SchoolFootballClub(){
        super();
        this.schoolName = null;
    }

    public SchoolFootballClub(String schoolName, String clubName, Address clubLocation){
        super( clubName, clubLocation);
        this.schoolName = schoolName;
    }

    public String getSchoolName(){
        return this.schoolName;
    }

    public void setSchoolName(String schoolName){
        this.schoolName = schoolName;
    }

    public static int getAgeLimit(){
        return ageLimit;
    }

    @Override
    public String toString(){
        return super.toString()
                +"School Name: "+this.getSchoolName()+"\n";
    }



}
