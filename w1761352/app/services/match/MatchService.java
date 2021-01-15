package services.match;

import com.google.inject.ImplementedBy;

import entities.match.Match;

import java.util.List;

@ImplementedBy(MatchServiceImpl.class)
public interface MatchService {
    List<Match> getAllMatches();

    List<Match> getMatchesByDate(String date);

    List<Match> getMatchesByDateAsc();

    Match randomMatch();

    void save(Match match);
}
