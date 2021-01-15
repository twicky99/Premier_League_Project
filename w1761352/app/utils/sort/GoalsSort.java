package utils.sort;

import entities.team.FootballClub;
import java.util.Comparator;

/**
* A Class to sort football club list by goals score
*/
public class GoalsSort implements Comparator<FootballClub> {

	/**
	* compare 2 football club data by goals score
	* @param ft Football club 
	* @param ft1 Football club
	* @return int
	*/
    @Override
    public int compare(FootballClub ft, FootballClub ft1) {
        if (ft.getGoalsScored() == ft1.getGoalsScored())
            return 0;
        else if (ft.getGoalsScored() > ft1.getGoalsScored())
            return -1;
        else
            return 1;
    }
}

