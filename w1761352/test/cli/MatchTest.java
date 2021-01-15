package cli;

import cli.LeagueManager;
import cli.PremierLeagueManager;
import entities.match.Match;
import entities.team.FootballClub;
import org.junit.BeforeClass;
import org.junit.Test;

/**
* This Class is used to ensure Match Feature running well
*/
public class MatchTest {
    /**
     * this is to work with one instance of PremierLeagueManager
     */
    private static LeagueManager premierLeague() {
        return new PremierLeagueManager();
    }

    /**
     * Execute initTestData before all test
     */
    @BeforeClass
    public static void initMatchTestData() {
        FootballClub homeClub = new FootballClub();
        homeClub.setClubName("Barcelona");
        homeClub.setClubLocation("Spain");
        homeClub.setClubManager("John");
        homeClub.setClubFormedYear(1899);

        FootballClub awayClub = new FootballClub();
        awayClub.setClubName("Real Madrid");
        awayClub.setClubLocation("Spain");
        awayClub.setClubManager("Peter");
        awayClub.setClubFormedYear(1899);

        premierLeague().addFootballClub(homeClub);
        premierLeague().addFootballClub(awayClub);
        premierLeague().saveStatisticsData("LeagueFileTest.txt");

        premierLeague().footBallMatch(homeClub.getClubName(), 1, awayClub.getClubName(), 2, "11/12/2020");
        premierLeague().footBallMatch(homeClub.getClubName(), 3, awayClub.getClubName(), 4, "10/12/2020");
        premierLeague().footBallMatch(homeClub.getClubName(), 2, awayClub.getClubName(), 1, "28/12/2020");

        premierLeague().saveMatchesData("MatchFileTest.txt");
    }

    @Test
    public void footballMatchDatePlayedTest() {
        premierLeague().retrieveMatchesData("MatchFileTest.txt");
        boolean findMatch = false;
        for (Match match : premierLeague().getMatchData()) {
            if (match.getDatePlayed().equals("28/12/2020")) {
                findMatch = true;
                break;
            }
        }
        assert findMatch;
    }


    /**
    * test if match not exist
    */
    @Test
    public void footballMatchDatePlayedNotExistTest() {
        premierLeague().retrieveMatchesData("MatchFileTest.txt");
        boolean findMatch = false;
        for (Match match : premierLeague().getMatchData()) {
            if (match.getDatePlayed().equals("10/10/2020")) {
                findMatch = true;
                break;
            }
        }
        assert !findMatch;
    }
}
