package entities.match;

import java.io.Serializable;

/**
* Class that handle Match data
*/
public class Match implements Serializable {
    private String datePlayed;
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;

    public Match() {
    }

    public Match(String datePlayed, String homeTeam, String awayTeam, int homeScore, int awayScore) {
        this.datePlayed = datePlayed;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }


    public String getDatePlayed() {
        return datePlayed;
    }

    public void setDatePlayed(String datePlayed) {
        this.datePlayed = datePlayed;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }


    @Override
    public String toString() {
        return "Match{datePlayed=" + datePlayed + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", homeScore=" + homeScore +", awayScore=" + awayScore + "}";
    }


}

