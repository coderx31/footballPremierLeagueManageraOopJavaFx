

public interface LeagueManager {
    void addFootballClub(FootballClub footballClub);
    void deleteFootballClub(String clubName);
    void displayStatistics(String clubName);
    void displayLeagueTable(String choice);
    void addPlayedMatch(Match match);
    void saveToFile();
    void loadFromFile();
    void gui();
}
