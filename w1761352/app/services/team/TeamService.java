package services.team;

import com.google.inject.ImplementedBy;

import entities.team.FootballClub;

import java.util.List;

@ImplementedBy(TeamServiceImpl.class)
public interface TeamService {
    List<FootballClub> getFootballLeague();

    List<FootballClub> getFootballLeagueOrder(String orderBy);

    void save(FootballClub footballClub);
}
