package controllers;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

/**
* This class ensure TeamController running well
* Referred :- https://www.playframework.com/documentation/2.8.x/JavaFunctionalTest
*/
public class TeamControllerTest extends WithApplication {


    /**
    * Override play app configuration for the test
    */
    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder()
  .configure("file.league_file_name", "LeagueFileTest.txt")
        .configure("file.match_file_name", "MatchFileTest.txt")
  .build();
    }

    /**
    * Test team list order by points, should be http 200 response code
    */
    @Test
    public void getTeamsOrderByPoints() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("http://localhost:9000/premierleague/team/teams?orderBy=points");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
	
    /**
    * Test team list order by goals, should be http 200 response code
    */
	@Test
    public void getTeamsOrderByGoals() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("http://localhost:9000/premierleague/team/teams?orderBy=goals");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
	
    /**
    * Test team list order by wins, should be http 200 response code
    */
	@Test
    public void getTeamsOrderByWins() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("http://localhost:9000/premierleague/team/teams?orderBy=wins");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
}
