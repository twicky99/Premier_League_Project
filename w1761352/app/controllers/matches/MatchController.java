/**
* Cannot use "match" keyword because in Scala (the main language that builds the play framework Play Framework)  
* "match" keyword is reverse keyword 
* 
*/

package controllers.matches;

import javax.inject.Inject;

import play.mvc.*;
import play.libs.Json;

import services.match.MatchService;
import entities.match.Match;
import utils.PremierLeagueUtils;

import java.util.List;

/**
* Class that handle MatchesPlayed API data
*/
public class MatchController extends Controller {
	private final MatchService matchService;

	@Inject
	public MatchController(MatchService matchService){
		this.matchService = matchService;
	}

    /**
     * To Find a match by date.The result is {@code List<Match>} with all matches in the given date {@code String date}
     * path = "/matches"
     *
     * @param date (default value = "") date parameter to get matches
     * @return {@code List<Match>} or ResponseEntity no content in case of no results
     * 
     */
    public Result findMatchesByDate(String date) {
        if (!date.isEmpty() && !PremierLeagueUtils.checkValidDate(date)) {
            return status(409, "Malformed date");
        }
        try {
            List<Match> matches = (date.isEmpty()) ? matchService.getAllMatches() : matchService.getMatchesByDate(date);
            if (matches.size() > 0) {
                return ok(Json.toJson(matches));
            } else {
                System.out.println("no content");
                return noContent();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return badRequest();
        }
    }

    /**
     * Gets all matches in ascending order by date. The result is {@code List<Match>} with all matches order by date
     * path = "/matches/order-by-date"
     *
     * @return {@code List<Match>} order descending date or ResponseEntity no content in case of no results.
     */
    public Result getMatchesOrderAscByDate() {
        try {
            List<Match> matches = matchService.getMatchesByDateAsc();
            if (matches.size() > 0) {
                return ok(Json.toJson(matches));
            } else {
                return noContent();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return badRequest();
        }
    }


	/**
     * Start a match between two randoms {@code FootballClub} teams the result is a {@code Match}
     * otherwise a bad request is returned.
     * path = "/random"
     *
     * @return Random {@code Match} or ResponseEntity bad request.
     */
    public Result generateRandomMatch() {
        Match randomMatch = matchService.randomMatch();
        try {
            return ok(Json.toJson(randomMatch));
        } catch (Exception ex) {
            return badRequest();
        }
    }
    
}
