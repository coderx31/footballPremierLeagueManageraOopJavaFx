import java.io.Serializable;

public class UniversityFootballClub extends FootballClub implements Serializable {
    private static final int ageLimit = 23;
    private String universityName;

    /*default constructor*/
    public UniversityFootballClub(){
        super();
        this.universityName = null;
    }

    public UniversityFootballClub(String universityName, String clubName,
                                  Address clubLocation){
        super(clubName, clubLocation);
        this.universityName = universityName;
    }

    public String getUniversityName(){
        return this.universityName;
    }

    public void setUniversityName(String universityName){
        this.universityName = universityName;
    }

    public static int getAgeLimit(){
        return ageLimit;
    }

    @
    Override
    public String toString(){
        return super.toString()
                +"University Name: "+this.getUniversityName()+"\n";
    }
}
