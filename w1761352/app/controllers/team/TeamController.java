package controllers.team;

import javax.inject.Inject;

import play.mvc.*;
import play.libs.Json;

import services.team.TeamService;
import entities.team.FootballClub;

import java.util.List;

/**
* Class that handle FootballClub API data
*/
public class TeamController extends Controller {
	private final TeamService teamService;

	@Inject
	public TeamController(TeamService teamService){
		this.teamService = teamService;
	}

    /**
     * Get unsorted list of FootballClub teams registered in Premier League by default.The result is {@code List<FootballClub>} or HTTP no content in case of no results , if want to get the list sorted by wins,goals or points need to send  {@code String orderBy} parameter in the request.
     * @param orderBy (default value = null) The string to get sorted list (points,wins or goals)
     * @return {@code List<FootballClub>} or HTTP no content in case of no results
     */
    public Result getTeams(String orderBy) {
        try{
            List<FootballClub> footballClubList = (orderBy.isEmpty()) ? teamService.getFootballLeague() : teamService.getFootballLeagueOrder(orderBy);
            if (footballClubList.size() > 0) {
                return ok(Json.toJson(footballClubList));
            } else {
                return noContent();
            }
        }catch(Exception ex) {
            return badRequest();
        }
    	
    }

}
