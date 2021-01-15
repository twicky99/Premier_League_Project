package data;

import entities.match.Match;
import entities.team.FootballClub;

import java.util.ArrayList;
import java.util.List;

/**
* Store Array List data of football clubs registered and data of played matches
*/
public final class AppData {
    public static List<FootballClub> footballClubList = new ArrayList<>(); //arraylist that stores football clubs data
    public static List<Match> matchData = new ArrayList<>();  //arraylist that stores matches data
    public static final int MAX_TEAMS = 20; //maximum number of clubs in the league
    public static int modifiedCount = 0; //This variable is used to note whether data has modified or not by adding football club, set match or deleting club

    
}
