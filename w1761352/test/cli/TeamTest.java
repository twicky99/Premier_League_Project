package cli;

import cli.LeagueManager;
import cli.PremierLeagueManager;
import entities.team.FootballClub;
import org.junit.BeforeClass;
import org.junit.Test;

public class TeamTest {
    // this is to work with one instance of PremierLeagueManager
    private static LeagueManager premierLeague() {
        return new PremierLeagueManager();
    }

    /**
     * Execute initTestData before all test
     */

    @BeforeClass
    public static void initTeamTestData() {
        System.out.println("Init data for test");
        FootballClub liverpoolTeam = new FootballClub();
        liverpoolTeam.setClubName("Liverpool");
        liverpoolTeam.setClubLocation("England");
        liverpoolTeam.setClubManager("William");
        liverpoolTeam.setClubFormedYear(1978);

        FootballClub manchesterTeam = new FootballClub();
        manchesterTeam.setClubName("Manchester United");
        manchesterTeam.setClubLocation("England");
        manchesterTeam.setClubManager("Ben");
        manchesterTeam.setClubFormedYear(1978);

        FootballClub milanTeam = new FootballClub();
        milanTeam.setClubName("AC Milan");
        milanTeam.setClubLocation("Italy");
        milanTeam.setClubManager("Carletto");
        milanTeam.setClubFormedYear(1899);

        premierLeague().addFootballClub(liverpoolTeam);
        premierLeague().addFootballClub(manchesterTeam);
        premierLeague().addFootballClub(milanTeam);

        premierLeague().saveStatisticsData("LeagueFileTest.txt");
    }

    /**
    * add football club test
    */
    @Test
    public void addFootballClubTest() {
        boolean findNewClub = false;
        FootballClub footballClub = new FootballClub();
        footballClub.setClubName("Sampdoria");
        footballClub.setClubLocation("Italy");
        footballClub.setClubManager("Genaro Gatuso");
        footballClub.setClubFormedYear(1878);

        premierLeague().addFootballClub(footballClub);  // add footballClub to array list

        premierLeague().saveStatisticsData("LeagueFileTest.txt");  // save all data in file

        premierLeague().retrieveStatisticsData("LeagueFileTest.txt");  // retrieve all data to check if the object really saves in it

        // Loop the array list (getClubs return the football array list from premier League)
        for (FootballClub footballClub1 : premierLeague().getClubs()) {
            if (footballClub1.getClubName().equals(footballClub.getClubName())) {
                findNewClub = true;
                break;
            }
        }
        assert findNewClub;
    }

    /**
    * Delete test
    */
    @Test
    public void deleteExistedFootballClubTest() {
        boolean deleteClub = true;
        premierLeague().retrieveStatisticsData("LeagueFileTest.txt");
        premierLeague().deleteClub("Sampdoria");
        premierLeague().saveStatisticsData("LeagueFileTest.txt");
        premierLeague().retrieveStatisticsData("LeagueFileTest.txt");
        for (FootballClub footballClub : premierLeague().getClubs()) {
            if (footballClub.getClubName().equals("Sampdoria")) {
                deleteClub = false;
                break;
            }
        }
        assert deleteClub;
    }

    @Test
    public void deleteNotExistedFootballClubTest() {
        boolean deleteClub = false;
        premierLeague().retrieveStatisticsData("LeagueFileTest.txt");
        premierLeague().deleteClub("Unknown Team");
        premierLeague().saveStatisticsData("LeagueFileTest.txt");
        premierLeague().retrieveStatisticsData("LeagueFileTest.txt");

        for (FootballClub footballClub : premierLeague().getClubs()) {
            if (footballClub.getClubName().equals("Unknown Team")) {
                deleteClub = true;
                break;
            }
        }
        assert !deleteClub;
    }

    /**
    * Search Test
    */
    @Test
    public void findTeamStatisticsByNameTest() {
        premierLeague().retrieveStatisticsData("LeagueFileTest.txt");
        FootballClub footballClubTest = premierLeague().findTeamStatisticsByName("AC Milan");
        assert footballClubTest != null;
    }

    /**
    * Test not existed team by name
    */

    @Test
    public void findNotExistedTeamStatisticsByNameTest() {
        premierLeague().retrieveStatisticsData("LeagueFileTest.txt");
        FootballClub footballClubTest = premierLeague().findTeamStatisticsByName("Unknown Team");
        assert footballClubTest == null;
    }

}
