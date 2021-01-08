import java.io.Serializable;
import java.util.Objects;

public class Match implements Serializable, Comparable<Match> {
    private  String matchId;
    private String homeTeam;
    private String opponentTeam;
    private int homeScore;
    private int opponentScore;
    private Date date;
    private static int matchCount;

    /*default constructor*/
    public Match(){
        this.matchId = null;
        this.homeTeam = null;
        this.opponentTeam = null;
        this.homeScore = 0;
        this.opponentScore = 0;
        this.date = null;
        matchCount++;
    }

    public Match(String homeTeam, String opponentTeam,int homeScore, int opponentScore, Date date){
        this.setMatchId();
        this.homeTeam = homeTeam;
        this.opponentTeam =  opponentTeam;
        this.homeScore = homeScore;
        this.opponentScore = opponentScore;
        this.date = date;
        matchCount++;
    }

    public String getMatchId(){
        return this.matchId;
    }

    public void setMatchId(){
        this.matchId = "2020FB"+getMatchCount();
    }

    public String getHomeTeam(){
        return this.homeTeam;
    }

    public void setHomeTeam(String homeTeam){
        this.homeTeam = homeTeam;
    }

    public String getOpponentTeam(){
        return this.opponentTeam;
    }

    public void setOpponentTeam(String opponentTeam){
        this.opponentTeam = opponentTeam;
    }

    public int getHomeScore(){
        return this.homeScore;
    }

    public void setHomeScore(int homeScore){
        this.homeScore = homeScore;
    }

    public int getOpponentScore(){
        return this.opponentScore;
    }

    public void setOpponentScore(int opponentScore){
        this.opponentScore = opponentScore;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public static int getMatchCount(){
        return matchCount;
    }

    @
    Override
    public String toString(){
        return "Match ID: "+this.getMatchId()+"\n"
                +"Home Team: "+this.getHomeTeam()+"\n"
                +"Opponent Team: "+this.getOpponentTeam()+"\n"
                +"Home Team Score: "+this.getHomeScore()+"\n"
                +"Opponent Team Score: "+this.getOpponentScore()+"\n"
                +"Date: "+this.getDate().toString()+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return getHomeScore() == match.getHomeScore() &&
                getOpponentScore() == match.getOpponentScore() &&
                getMatchId().equals(match.getMatchId()) &&
                getHomeTeam().equals(match.getHomeTeam()) &&
                getOpponentTeam().equals(match.getOpponentTeam()) &&
                getDate().equals(match.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMatchId(), getHomeTeam(), getOpponentTeam(), getHomeScore(), getOpponentScore(), getDate());
    }

    @Override
    public int compareTo(Match match) {
        return this.getDate().getDays() - match.getDate().getDays();
    }
}
