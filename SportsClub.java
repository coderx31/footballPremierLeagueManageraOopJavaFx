import java.io.Serializable;

public abstract class SportsClub implements Serializable {
    private String clubName;
    private Address clubLocation;
    private int numOfWins;
    private int numOfDraws;
    private int numOfDefeats;
    private int points;
    private int numOfMatchPlayed;
    private static int numOfClubs;

    /*default constructor*/
    public SportsClub(){
        this.clubName = null;
        this.clubLocation = null;
        this.numOfWins = 0;
        this.numOfDraws = 0;
        this.numOfDefeats = 0;
        this.points = 0;
        this.numOfMatchPlayed = 0;
        numOfClubs++;
    }

    public SportsClub(String clubName, Address clubLocation){
        this.clubName = clubName;
        this.clubLocation = clubLocation;
    }

    public String getClubName(){
        return this.clubName;
    }

    public void setClubName(String clubName){
        this.clubName = clubName;
    }

    public Address getClubLocation(){
        return this.clubLocation;
    }

    public void setClubLocation(Address clubLocation){
        this.clubLocation = clubLocation;
    }

    public int getNumOfWins(){
        return this.numOfWins;
    }

    public void setNumOfWins(){
        this.numOfWins++;
    }

    public int getNumOfDraws(){
        return this.numOfDraws;
    }

    public void setNumOfDraws(){
        this.numOfDraws++;
    }

    public int getNumOfDefeats(){
        return this.numOfDefeats;
    }

    public void setNumOfDefeats(){
        this.numOfDefeats++;
    }

    public int getNumOfMatchPlayed(){
        return this.numOfMatchPlayed;
    }

    public void setNumOfMatchPlayed(){
        this.numOfMatchPlayed++;
    }

    public int getPoints(){
        return this.points;
    }

    public void setPoints(int points){
        this.points += points;
    }

    public static int getNumOfClubs(){
        return numOfClubs;
    }

    @Override
    public String toString(){
        return "Club Name: "+this.getClubName()+"\n"
                +this.getClubLocation()+"\n"
                +"Num of Wins: "+this.getNumOfWins()+"\n"
                +"Num of Draws: "+this.getNumOfDraws()+"\n"
                +"Num of Defeats: "+this.getNumOfDefeats()+"\n"
                +"Num of Played: "+this.getNumOfMatchPlayed()+"\n"
                +"Points: "+this.getPoints()+"\n";
    }
}
