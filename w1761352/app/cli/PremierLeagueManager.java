/*Full Name:- Wickramasinghege Tharindu Wickramasinghe 	   UOW ID:- w1761352
"I  confirm  that  I  understand  what  plagiarism  / collusion  /  contract  cheating  is  and  have  read  and understood the section on Assessment Offences in the Essential  Information  for  Students.  The  work  that  I have  submitted  is  entirely  my  own.  Any work  from other authors is duly referenced and acknowledged."
*/

package cli;

import entities.match.Match;
import entities.team.FootballClub;
import utils.PremierLeagueUtils;
import utils.sort.FootballClubSort;
import data.AppData;
import utils.KeyScanner;

import java.io.*;
import java.util.List;


/** 
* This Class is responsible to manage logic of CLI application
 and to save data and retrieve data from text file
*/

public class PremierLeagueManager implements LeagueManager {
    private KeyScanner keyScanner = new KeyScanner();
    private List<FootballClub> footballClubList = AppData.footballClubList;
    private List<Match> matchData = AppData.matchData;

    /**
    * add football club to ArrayList
    * @param footballClub FootballClub instance
    */
    @Override
    public void addFootballClub(FootballClub footballClub) {
        if (footballClubList.isEmpty()) {
            this.footballClubList.add(footballClub);
            System.out.println("\"" + footballClub.getClubName() + "\" club is successfully registered to the league ");
        } 
		else if (this.footballClubList.size() >= AppData.MAX_TEAMS){
			System.out.println("All the slots have been filled");
		}
		else {
            boolean findTeam = false;
            for (FootballClub footballClub1 : getClubs()) {
                if (footballClub1.getClubName().toLowerCase()
                        .equals(footballClub.getClubName().toLowerCase())) {
                    findTeam = true;
                    break;
                }
            }
            if (!findTeam) {
                AppData.modifiedCount += 1;
                this.footballClubList.add(footballClub);
                System.out.println("\"" + footballClub.getClubName() + "\" club is successfully registered to the league ");
				System.out.println("Free Slots Remaining : " + (AppData.MAX_TEAMS - this.footballClubList.size()));
            } else {
                System.out.println("The team is already in the list");
            }
        }
    }

    /**
    * remove football club by club name
    * @param name club name
    */
    @Override
    public void deleteClub(String name) {
        if (footballClubList.isEmpty()) {
            System.out.println("No teams are available in the football team list!");
        } else {
            boolean found = false;
            for (FootballClub club : footballClubList) {
                String cname = club.getClubName();
                if (cname.equals(name)) {
                    found = true;
                    footballClubList.remove(club);
                    System.out.println("\"" + name + "\" club is successfully deleted from the list");
                    AppData.modifiedCount += 1;
          System.out.println("Free Slots Remaining : " + (AppData.MAX_TEAMS - this.footballClubList.size()));
                    break;
                }
            }
            if (!found) {
                System.out.println("No such club exists in the list");
            }
        }

    }

    /**
    * find team statistics by name
    * @param name club name
    */
    @Override
    public FootballClub findTeamStatisticsByName(String name) {
        if (footballClubList.isEmpty()) {
            System.out.println("No teams are present in the list.");
        } else {
            try {
                for (FootballClub ftc : footballClubList) {
                    String cname = ftc.getClubName();
                    if (cname.equals(name)) {
                        return ftc;
                    }
                }
            } catch (Exception e) {
                System.out.println("Integers are not allowed");
            }
        }
        return null;
    }

    /**
    * display statistics of all registered teams
    */
    @Override
    public void displayLeague() {
        System.out.println();
        if (footballClubList.isEmpty()) {
            System.out.println("No Club Registered To League!");
        } else {
            footballClubList.sort(new FootballClubSort());
            System.out.println("+---------------------+-------------------------+---------------+--------------+-----------------+-----------------------+-------------------------+--------------------------+---------------+");
            System.out.println("|      Club Name      |    Matches Played(MP)   |    Wins(W)    |    Draws(D)  |    Defeats(L)   |    Goals Scored(GS)   |    Goals Received(GR)   |    Goals Difference(GD)  |    Points(P)  |");
            System.out.println("+---------------------+-------------------------+---------------+--------------+-----------------+-----------------------+-------------------------+--------------------------+---------------+");
            String leftAlignFormat = "| %-19s |           %-13d |      %-8d |       %-6d |        %-8d |           %-11d |            %-12d |            %-13d |        %-6d |%n";
            for (FootballClub fc : footballClubList) {
                System.out.format(leftAlignFormat,
                        fc.getClubName(),
                        fc.getTotalMatches(),
                        fc.getWins(),
                        fc.getDraws(),
                        fc.getDefeats(),
                        fc.getGoalsScored(),
                        fc.getGoalsReceived(),
                        fc.getGoalsDifference(),
                        fc.getPoints());
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        }
    }


    /**
    * Displays All Matches Played with date
    */
    @Override
    public void displayMatchesPlayed() {
        System.out.println("\nDiplaying All Played Matches\n");
        if (matchData.isEmpty()) {
            System.out.println("No Matches Played !");
        } else {
            for (Match mt : matchData) {
                System.out.printf("\nDate: %s\n%s    vs    %s\n%s     :     %s ", mt.getDatePlayed(), mt.getHomeTeam(), mt.getAwayTeam(), mt.getHomeScore(), mt.getAwayScore());
                System.out.println();
            }
        }
        System.out.println();
    }

    /**
    * add match
    * @param homeTeamName club name as home team
    * @param homeScore home team score
    * @param awayTeamName club name as away team
    * @param awayScore away team score
    * @param dateFormat date match
    * @return Match Object
    */
    @Override
    public Match footBallMatch(String homeTeamName, int homeScore, String awayTeamName, int awayScore, String dateFormat) {
        return prepareMatch(homeTeamName, homeScore, awayTeamName, awayScore, dateFormat);
    }

    /**
    * save all football club data from ArrayList and put it into txt file
    * @param fileName file contains football club data
    */
    @Override
    public void saveStatisticsData(String fileName) {
        if (AppData.modifiedCount > 0) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                for (FootballClub footballClub : footballClubList) {
                    objectOutputStream.writeObject(footballClub);
                }

                objectOutputStream.flush();
                fileOutputStream.close();
                objectOutputStream.close();
                System.out.println("Statistics Data has been saved successfully");
            } catch (IOException e) {
                System.out.println("No statistics data present to save in file");
            }
        }else{
            System.out.println("No statistics data present to save in file");
        }
        
    }

    /**
    * save all match data from ArrayList and put it into txt file
    * @param fileName file contains match data
    */
    @Override
    public void saveMatchesData(String fileName) {
        if (AppData.modifiedCount > 0) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                for (Match match : matchData) {
                    objectOutputStream.writeObject(match);
                }

                objectOutputStream.flush();
                fileOutputStream.close();
                objectOutputStream.close();
                System.out.println("Matches Data has been saved successfully");
            } catch (IOException e) {
                System.out.println("No matches played data present to save in file");
            }
        }else{
            System.out.println("No matches played data present to save in file");
        }
        
    }

    /**
    * load all football club data from file and put it into ArrayList
    * @param fileName file contains footballclub data
    */
    @Override
    public void retrieveStatisticsData(String fileName) {
        footballClubList.clear();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            for (; ; ) {
                try {
                    Object readObject = objectInputStream.readObject();
                    if (readObject instanceof FootballClub) {
                        footballClubList.add((FootballClub) readObject);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
            fileInputStream.close();
            objectInputStream.close();
            System.out.println("Statistics Data has been loaded successfully");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved records present to load from file");
        }
    }

    /**
    * load all match data from file and put it into ArrayList
    * @param fileName file contains match data
    */
    @Override
    public void retrieveMatchesData(String fileName) {
        matchData.clear();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            for (; ; ) {
                try {
                    Object readObject = objectInputStream.readObject();
                    if (readObject instanceof Match) {
                        matchData.add((Match) readObject);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
            fileInputStream.close();
            objectInputStream.close();
            System.out.println("Matches played Data has been loaded successfully");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved records present to load from file");
        }
    }

    /**
    * Open browser as app GUI automatically with address host:4200
    */
    //Referred link:- https://stackoverflow.com/questions/5226212/how-to-open-the-default-webbrowser-using-java
    @Override
    public void openWebGUI() {
        System.out.println("Please Wait...Opening Premier League GUI");
        String url = "http://localhost:4200";
        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();
	   try {
            if (os.contains("win")) {  // for windows
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);

            } else if (os.contains("mac")) {   // for mac
                rt.exec("open " + url);

            } else if (os.contains("nix") || os.contains("nux")) {   // for unix/linux
                rt.exec("xdg-open " + url);
            }

            Thread.sleep(3000);
        } catch (Exception ignored) {
        }
    }

    /**
    * get all match
    */
    @Override
    public List<Match> getMatchData() {
        return matchData;
    }

    /**
    * get all football club
    */
    @Override
    public List<FootballClub> getClubs() {
        return footballClubList;
    }

    /**
    * form to entry new club
    */
    private FootballClub createNewClub() {
        try {
            FootballClub fc = new FootballClub();
            String name, location, manager;
            int yearFormed = 1900;
            name = keyScanner.inputWithValidation("Enter Club Name  : ", ">>> Club Name cannot be empty");

            location = keyScanner.inputWithValidation("Enter Club Location  : ", ">>> Club location cannot be empty");

            manager = keyScanner.inputWithValidation("Enter Club manager  : ", ">>> Club manager cannot be empty");

            boolean inputValid = false;
            System.out.print("Enter Formed year (Founded year) of the Club: ");
            while (!inputValid) {
                try {
                    String input = keyScanner.readLine(); 
                    yearFormed = Integer.parseInt(input);
                    inputValid = true;
                } catch (NumberFormatException e) {
                    System.out.println("Only integers are allowed...Please re-enter Formed year (Founded year) of the Club again: ");
                }
            }

            fc.setClubName(name.trim());
            fc.setClubManager(manager.trim());
            fc.setClubFormedYear(yearFormed);
            fc.setClubLocation(location.trim());
            return fc;
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    /**
    * add match
    * @param teamname1 name as home team
    * @param score1 home team score
    * @param teamname2 club name as away team
    * @param score2 away team score
    * @param date date match
    * @return Match Object
    */
    private Match prepareMatch(String teamname1, int score1, String teamname2, int score2, String date) {
        int i = 1;
        FootballClub homeClub;
        FootballClub awayClub;
        if (footballClubList.isEmpty()) {
            System.out.println("\n" + teamname1 + " isn't registered In League\n Please re-enter the club name to register in League ");
            homeClub = createNewClub();
            addFootballClub(homeClub);
            System.out.println("\n" + teamname2 + " isn't registered In League\n Please re-enter the club name to register in League ");
            awayClub = createNewClub();
            addFootballClub(awayClub);
        }
        for (FootballClub ft1 : footballClubList) {
            if (ft1.getClubName().equals(teamname1)) {
                if (score1 > score2) {
                    ft1.setWins(1);
                } else if (score1 < score2) {
                    ft1.setDefeats(1);
                }
                ft1.updateScore(score1, score2);
                break;
            } else if (i == footballClubList.size()) {
				 if (this.footballClubList.size() >= AppData.MAX_TEAMS){
					System.out.println("All the slots have been filled");
					return null;
					}
                System.out.println("\n" + teamname1 + " isn't registered In League\n Please re-enter the club name to register in League ");
                homeClub = createNewClub();
                if (score1 > score2) {
                    homeClub.setWins(1);

                } else if (score1 < score2) {
                    homeClub.setDefeats(1);
                }
                homeClub.updateScore(score1, score2);
                addFootballClub(homeClub);
                break;
            }
            ++i;
        }
        i = 1;
        for (FootballClub ft2 : footballClubList) {
            if (ft2.getClubName().equals(teamname2)) {
                if (score1 < score2) {
                    ft2.setWins(1);
                } else if (score1 > score2) {
                    ft2.setDefeats(1);
                }
                ft2.updateScore(score2, score1);
                break;
            } else if (i == footballClubList.size()) {
				 if (this.footballClubList.size() >= AppData.MAX_TEAMS){
					System.out.println("All the slots have been filled");
					return null;
					}
                System.out.println("\n" + teamname2 + " isn't registered in League\n Please re-enter the club name to register in League ");
                awayClub = createNewClub();
                if (score1 < score2) {
                    awayClub.setWins(1);
                }else if (score1 > score2) {
                    awayClub.setDefeats(1);
                }
                awayClub.updateScore(score2, score1);
                addFootballClub(awayClub);
                break;
            }
            ++i;
        }

        AppData.modifiedCount += 1;

        Match match = new Match();
        String formatDate = PremierLeagueUtils.formatDate(date);
        match.setDatePlayed(formatDate);
        match.setHomeTeam(teamname1);
        match.setAwayTeam(teamname2);
        match.setHomeScore(score1);
        match.setAwayScore(score2);
        matchData.add(match);
        return match;
    }

    
}
