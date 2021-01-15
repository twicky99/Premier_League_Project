package services.team;

import entities.team.FootballClub;
import utils.sort.FootballClubSort;
import utils.sort.GoalsSort;
import utils.sort.WinSort;
import data.AppData;

import java.io.*;
import java.util.List;
import java.util.ListIterator;
import javax.inject.Inject;
import com.typesafe.config.Config;

/**
* Class that handles Football Club data
*/
public class TeamServiceImpl implements TeamService {

    private final Config config;

    @Inject
    public TeamServiceImpl(Config config) {
        this.config = config;
    }

    /**
    * get all football club
    * @return {@code List<FootballClub>} 
    */
    @Override
    public List<FootballClub> getFootballLeague() {
        return AppData.footballClubList;
    }

    /**
    * get football club order by option descending
    * @param orderBy sort option (goals, wins, points)
    * @return {@code List<FootballClub>} 
    */
    @Override
    public List<FootballClub> getFootballLeagueOrder(String orderBy) {
        List<FootballClub> footballClubList = this.getFootballLeague();
        switch (orderBy) {
            case "goals":
                footballClubList.sort(new GoalsSort());
                break;
            case "wins":
                footballClubList.sort(new WinSort());
                break;
            case "points":
                footballClubList.sort(new FootballClubSort());
                break;
        }
        return footballClubList;
    }

    /**
    * save all football club data from ArrayList and put it into txt file
    * @param footballClub file contains football club data
    */
    @Override
    public void save(FootballClub footballClub) {
        List<FootballClub> allTeams = this.getFootballLeague();
        ListIterator<FootballClub> footballClubListIterator = allTeams.listIterator();
        while (footballClubListIterator.hasNext()) {
            if (footballClubListIterator.next().getClubName().equals(footballClub.getClubName())) {
                footballClubListIterator.set(footballClub);
            }
        }
        try {
            String leagueFile = config.getString("file.league_file_name");
            FileOutputStream fileOutputStream = new FileOutputStream(leagueFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            allTeams.forEach(el -> {
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

