package utils.sort;

import entities.team.FootballClub;
import java.util.Comparator;

/**
* A Class to sort football club list by points
*/
public class FootballClubSort implements Comparator<FootballClub> {

	/**
	* compare 2 football club data by Points
	* @param o1 Football club 
	* @param o2 Football club
	* @return int
	*/
    @Override
    public int compare(FootballClub o1, FootballClub o2) {
       int rtrn;
        if(o1.getPoints() == o2.getPoints()){
            if(o1.getGoalsDifference() == o2.getGoalsDifference()){
                rtrn = 0;
            }else if(o1.getGoalsDifference() < o2.getGoalsDifference()){
                rtrn = 1;
            }else{
                rtrn = -1;
            }
        }
        else if(o1.getPoints() < o2.getPoints()){
            rtrn = 1;
        }
        else if(o1.getPoints() > o2.getPoints()){
            rtrn = -1;
        }
        else{
            rtrn = 0;
        }
        return rtrn;
    }
}
