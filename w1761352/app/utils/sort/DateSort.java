package utils.sort;

import entities.match.Match;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

/**
* A Class to sort match list by date
*/
public class DateSort implements Comparator<Match> {

	/**
	* compare two match data by date
	* @param o1 Match
	* @param o2 Match
	* @return int
	*/
    @Override
    public int compare(Match o1, Match o2) {
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return f.parse(o1.getDatePlayed()).compareTo(f.parse(o2.getDatePlayed()));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

