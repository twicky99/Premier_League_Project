package services.match;

import entities.match.Match;
import entities.team.FootballClub;
import utils.sort.DateSort;
import utils.PremierLeagueUtils;
import data.AppData;
import services.team.TeamService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.typesafe.config.Config;

/**
* Class that handles Match data
*/
public class MatchServiceImpl implements MatchService {
    private final Config config;
    private final TeamService teamService; 

    @Inject
    public MatchServiceImpl(Config config, TeamService teamService) {
        this.config = config;
        this.teamService = teamService;
    }

    /**
    * get all match data
    * @return {@code List<MatchData>} 
    */
    @Override
    public List<Match> getAllMatches() {
        return AppData.matchData;
    }

    /**
    * get match data by date
    * @param date
    * @return {@code List<MatchData>} 
    */
    @Override
    public List<Match> getMatchesByDate(String date) {
        List<Match> allMatches = this.getAllMatches();
        List<Match> allMatchesByDate = new ArrayList<>();
        for (Match match : allMatches) {
            String formatDate = PremierLeagueUtils.formatDate(date);
            if (formatDate.equals(match.getDatePlayed())) {
                allMatchesByDate.add(match);
            }
        }
        return allMatchesByDate;
    }

    /**
    * get all match data order by date ascending
    * @return {@code List<MatchData>} 
    */
    @Override
    public List<Match> getMatchesByDateAsc() {
        List<Match> all = this.getAllMatches();
        all.sort(new DateSort());
        return all;
    }

    /**
    * generate random Match
    * @return {@code MatchData} 
    */
    @Override
    public Match randomMatch() {
        FootballClub home = getRandomFootballClub();
        FootballClub away = getRandomFootballClub();
        while (home.getClubName().equals(away.getClubName())) {
            away = getRandomFootballClub();
        }
        return generateMatch(home, away);
    }

    /**
    * generate random Match process
    * @return {@code MatchData} 
    */
    private Match generateMatch(FootballClub home, FootballClub away) {
        int goalsScoredHome = PremierLeagueUtils.generateRandomNumber(6);
        int goalsScoredAway = PremierLeagueUtils.generateRandomNumber(6);
        if (goalsScoredHome < goalsScoredAway) {
            // Away Win
            home.setDefeats(1);
            away.setWins(1);
            away.updateScore(goalsScoredAway, goalsScoredHome);
            home.updateScore(goalsScoredHome, goalsScoredAway);
            
        } else if (goalsScoredHome > goalsScoredAway) {
            // Home Win
            away.setDefeats(1);
            home.setWins(1);
            home.updateScore(goalsScoredHome, goalsScoredAway);
            away.updateScore(goalsScoredAway, goalsScoredHome); 
        } 
	    else {
            // Match Draw
            home.updateScore(goalsScoredHome, goalsScoredAway);
            away.updateScore(goalsScoredAway, goalsScoredHome);
        }
        Match match = new Match(PremierLeagueUtils.generateNow(), home.getClubName(), away.getClubName(), goalsScoredHome, goalsScoredAway);
        this.save(match);
        teamService.save(home);
        teamService.save(away);
        return match;
    }

    /**
    * generate random football club
    * @return {@code FootballClub} 
    */
    private FootballClub getRandomFootballClub() {
        List<FootballClub> footballClubList = teamService.getFootballLeague();
        return footballClubList.get(PremierLeagueUtils.generateRandomNumber(footballClubList.size()));
    }

    /**
    * save all match data from ArrayList and put it into txt file
    * @param match file contains match data
    */
    @Override
    public void save(Match match) {
        List<Match> allMatches = this.getAllMatches();
        allMatches.add(match);
        try {
            String matchFile = config.getString("file.match_file_name");
            FileOutputStream fileOutputStream = new FileOutputStream(matchFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            allMatches.forEach(el -> {
                try {
                    objectOutputStream.writeObject(el);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fileOutputStream.close();
            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
