package entities.team;

import java.io.Serializable;

/**
* Class that handle SportClub data
*/
public class SportsClub implements Serializable {
    private String clubName;
    private String clubLocation;
    private String clubManager;
    private int clubFormedYear;

    public SportsClub() {

    }

    public SportsClub(String clubName, String clubLocation, String clubManager, int clubFormedYear) {
        this.clubName = clubName;
        this.clubLocation = clubLocation;
        this.clubManager = clubManager;
        this.clubFormedYear = clubFormedYear;

    }


    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubLocation() {
        return clubLocation;
    }

    public void setClubLocation(String location) {
        this.clubLocation = location;
    }

    public String getClubManager() {
        return clubManager;
    }

    public void setClubManager(String clubManager) {
        this.clubManager = clubManager;
    }

    public int getClubFormedYear() {
        return clubFormedYear;
    }

    public void setClubFormedYear(int clubFormedYear) {
        this.clubFormedYear = clubFormedYear;
    }


    @Override
    public String toString() {
        return "SportsClub{clubName=" + clubName + ", clubLocation=" + clubLocation + ", clubManager='" + clubManager +
                ", clubFormedYear=" + clubFormedYear + "}";

    }
}

