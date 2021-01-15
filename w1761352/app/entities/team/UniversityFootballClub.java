package entities.team;

/**
 * Class that handle UniversityFootball Club data
 */
public class UniversityFootballClub extends FootballClub{
    private String universityName;

    public UniversityFootballClub(){

    }
    public UniversityFootballClub(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        return super.toString()+" UniversityFootballClub{universityName= " + universityName +"}";
    }
}
