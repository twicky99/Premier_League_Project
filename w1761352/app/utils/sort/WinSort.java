package utils.sort;

import entities.team.FootballClub;
import java.util.Comparator;

/**
* A Class to sort football club list by wins
*/
public class WinSort implements Comparator<FootballClub> {

	/**
	* compare 2 football club data by wins
	* @param ft Football club 
	* @param ft1 Football club
	* @return int
	*/
    @Override
    public int compare(FootballClub ft, FootballClub ft1) {
        if (ft.getWins() == ft1.getWins())
            return 0;
        else if (ft.getWins() > ft1.getWins())
            return -1;
        else
            return 1;
    }
}
