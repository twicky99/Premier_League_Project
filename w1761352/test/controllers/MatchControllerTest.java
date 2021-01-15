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
* This class ensure MatchController running well
* Referred :- https://www.playframework.com/documentation/2.8.x/JavaFunctionalTest
*/
public class MatchControllerTest extends WithApplication {

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
    * search match by date API Test , should be http 200 response code
    */
    @Test
    public void getMatchesByDateTest() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("http://localhost:9000/premierleague/match/matches?date=28/12/2020");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
	
    /**
    * match list order by date ascending API Test , should be http 200 response code
    */
	@Test
    public void getMatchesOrderByDateTest() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("http://localhost:9000/premierleague/match/matches/order-by-date");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
	
    /**
    * random match API Test , should be http 200 response code
    */
	@Test
    public void getRandomMatchTest() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("http://localhost:9000/premierleague/match/random");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }
}
