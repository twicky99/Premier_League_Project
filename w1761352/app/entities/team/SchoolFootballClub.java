package entities.team;

/**
 * Class that handle SchoolFootball Club data
 */
public class SchoolFootballClub extends FootballClub{
    private String schoolName;

    public SchoolFootballClub(){

    }

    public SchoolFootballClub(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return super.toString()+" SchoolFootballClub{schoolName= " + schoolName +"}";
    }
}
