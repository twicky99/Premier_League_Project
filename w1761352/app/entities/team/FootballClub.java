package entities.team;

/**
* Class that handle Football Club data
*/
public class FootballClub extends SportsClub {
    private int wins;
    private int draws;
    private int defeats;
    private int goalsScored;
    private int goalsReceived;
    private int goalsDifference;
    private int totalMatches;
    private int points;
    
    
    public FootballClub(){
        
    }
    public FootballClub(String name, String location,String clubManager,int clubFormedYear) {
        super(name, location,clubManager,clubFormedYear);

    }
    public FootballClub(String name,String location,String clubManager,int clubFormedYear,int wins,int defeats,int draws,int goalsScored,int goalsReceived,int totalMatches,int points){
        super(name, location,clubManager,clubFormedYear);
        this.wins = wins;
        this.draws = draws;
        this.defeats = defeats;
        this.goalsScored = goalsScored;
        this.goalsReceived = goalsReceived;
        this.totalMatches = totalMatches;
        this.points = points;

    }


    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins+= wins;
        this.points+=(wins*3);
        this.totalMatches+=wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.totalMatches+=draws;
        this.draws+= draws;
        this.points+=draws;

    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.totalMatches+=defeats;
        this.defeats =this.defeats+ defeats;

    }


    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored= this.goalsScored+goalsScored;
        this.goalsDifference=goalsScored-goalsReceived;


    }

    public int getGoalsReceived() {
        return goalsReceived;
    }

    public void setGoalsReceived(int goalsReceived) {

        this.goalsReceived= this.goalsReceived+goalsReceived;
        this.goalsDifference=goalsScored-goalsReceived;

    }

    public int getGoalsDifference() {
        return goalsDifference;
    }

    public void setGoalsDifference(int goalsDifference) {
        this.goalsDifference=this.goalsScored - this.goalsReceived;
    }

    public int getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(int totalMatches) {
        this.totalMatches = totalMatches;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points+= points;
    }


    public void updateScore(int goalscore, int goalreceive) {
        this.setGoalsScored(goalscore);
        this.setGoalsReceived(goalreceive);
        this.setGoalsDifference(goalscore-goalreceive);
        if (goalscore == goalreceive) {
            setDraws(1);
        }
    }

    @Override
    public String toString() {
        return super.toString()+" FootballClub{wins=" + wins + ", draws=" + draws + ", defeats=" + defeats + ", goalsScored=" + goalsScored +
                ", goalsReceived=" + goalsReceived + ", goalsDifference= "+goalsDifference+", totalMatches=" + totalMatches + ", points=" + points + "}";
    }



}

