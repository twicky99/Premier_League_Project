package tasks;

import entities.team.FootballClub;
import entities.match.Match;
import data.AppData;
import cli.PremierLeagueManager;

import java.util.List;
import javax.inject.Inject;
import com.typesafe.config.Config;

import play.DefaultApplication;

public class InitActorTask {
    private final Config config;
    private final AppThread appThread;
    private final PremierLeagueManager premierLeagueManager;

    private List<FootballClub> footballClubList = AppData.footballClubList;
    private List<Match> matchData = AppData.matchData;

    private final DefaultApplication defaultApp;

    /**
    * The constructor of InitActorTask Class
    * this method calls initialize method and start CLI application Thread
    */
    @Inject
    public InitActorTask(Config config, AppThread appThread, PremierLeagueManager premierLeagueManager, DefaultApplication defaultApp){
        this.config = config;
        this.appThread = appThread;
        this.premierLeagueManager = premierLeagueManager;
        this.defaultApp = defaultApp;
        this.initialize();

        if(!defaultApp.isTest()){
            appThread.start();
        }
    }

    /**
    * this method load footballclub(league) data and match data from txt file
    */
    private void initialize(){
        System.out.println("Load Inital Data from File");

        String leagueFile = config.getString("file.league_file_name");
        String matchFile = config.getString("file.match_file_name");

        premierLeagueManager.retrieveStatisticsData(leagueFile);
        premierLeagueManager.retrieveMatchesData(matchFile);
    }
}