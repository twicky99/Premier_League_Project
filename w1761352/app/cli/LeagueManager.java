package cli;

import com.google.inject.ImplementedBy;
import entities.match.Match;
import entities.team.FootballClub;
import java.util.List;

@ImplementedBy(PremierLeagueManager.class)
public interface LeagueManager {
    void addFootballClub(FootballClub footballClub);

    void deleteClub(String name);

    FootballClub findTeamStatisticsByName(String name);

    void displayLeague();

    Match footBallMatch(String homeTeamName, int homeScore, String awayTeamName, int awayScore, String dateFormat);

    void displayMatchesPlayed();

    List<Match> getMatchData();

    List<FootballClub> getClubs();

    void saveMatchesData(String filename);

    void saveStatisticsData(String filename);

    void retrieveStatisticsData(String fileName);

    void retrieveMatchesData(String fileName);

    void openWebGUI();

}

