package services;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import entities.match.Match;
import entities.team.FootballClub;
import services.match.*;
import services.team.*;
import cli.PremierLeagueManager;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;


/**
* This class ensure MatchService Class running well.
* MatchService.class is usefull to deliver match data to controller
*/
public class MatchServiceTest {
	private static MatchService matchService;
	private static TeamService teamService;
	private static PremierLeagueManager premierLeagueManager;


	/**
	* Initial data & object before run test
	*/
	@BeforeClass
	public static void initializeTest(){
		premierLeagueManager = new PremierLeagueManager();
		premierLeagueManager.retrieveStatisticsData("LeagueFileTest.txt");
		premierLeagueManager.retrieveMatchesData("MatchFileTest.txt");

		String configFile = "conf/application_test.conf";
      	Config config = ConfigFactory.parseFile(new File(configFile));
      	teamService = new TeamServiceImpl(config);
      	matchService = new MatchServiceImpl(config, teamService);
	}

	/**
	* Test randomMatch() method ensure it returns Match data
	*/
	@Test
	public void generateRandomMatchTest(){
		boolean findClub = false;
		Match randomMatch = matchService.randomMatch();

		for (FootballClub footballClub: teamService.getFootballLeague()) {
			if(footballClub.getClubName().equalsIgnoreCase(randomMatch.getHomeTeam())){
				findClub = true;
			}
		}
		
		System.out.println();
		System.out.println(randomMatch.getHomeTeam()+" vs "+randomMatch.getAwayTeam());
		Assert.assertEquals(true, findClub);
	}


	/**
	* This test ensure match data sorted correctly
	*/
	@Test
	public void sortedMatchDataTest(){
		boolean sorted = false;
		List<Match> sortedMatchs = matchService.getMatchesByDateAsc();
		int totalMatch = sortedMatchs.size();

		Match firstMatch = sortedMatchs.get(0);
		Match lastMatch = sortedMatchs.get(totalMatch - 1);

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		try{
			Date firstMatchDate = format.parse(firstMatch.getDatePlayed());
			Date lastMatchDate = format.parse(lastMatch.getDatePlayed());
			if (firstMatchDate.before(lastMatchDate)) {
				sorted = true;
			}
		}catch (ParseException pe) {
			System.out.println(pe.toString());
		}catch (Exception e){
			System.out.println(e.toString());
		}
		

		Assert.assertEquals(true, sorted);
	}

}