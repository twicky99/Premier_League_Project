package cli;

import entities.match.Date;
import entities.team.FootballClub;
import entities.match.Match;
import utils.KeyScanner;
import data.AppData;

import java.util.Objects;
import java.io.IOException;

/**
* Class to run CLI Application
*
*/
public class PremierLeagueApplication {
    private LeagueManager manager = new PremierLeagueManager();
    private KeyScanner keyScanner = new KeyScanner();

    /**
    * Run console app
    * @param leagueFile path file / filename that store Football Club data
    * @param matchFile path file / filename that store Match data
    */
    public void runConsole(String leagueFile, String matchFile) throws IOException {
        System.out.println("======================================================================================\n");
        System.out.println("**~~**~~**~~**~~**~~* - WELCOME TO PREMIER LEAGUE APPLICATION -  *~~**~~**~~**~~**~~**");


        boolean showMenu = true;
        while (showMenu) {
            displayMenu();

            String choice = keyScanner.readLine();

            switch (choice) {
                case "1":
                    addFootballClub();
                    break;
                case "2":
                    deleteClub();
                    break;
                case "3":
                    displayStatistics();
                    break;
                case "4":
                    displayLeague();
                    break;
                case "5":
                    setMatches();
                    break;
                case "6":
                    displayMatchesPlayed();
                    break;
                case "7":
                    manager.saveStatisticsData(leagueFile);
                    manager.saveMatchesData(matchFile);
                    AppData.modifiedCount = 0;
                    break;
                case "8":
                    manager.openWebGUI();
                    break;
                case "0":
                    showMenu = false;
                    System.out.println("Thank you for using the system......");
                    break ;
                default:
                    System.out.println("Invalid option entered!!!....Please re-enter");
                    break;
            }
        }
    }

    /** 
    * Display menu option for Premier League CLI App.
    */
    private void displayMenu(){
        System.out.println("\n======================================================================================\n");
        System.out.println("Enter (1) - Register (Create) A Club");
        System.out.println("Enter (2) - Delete A Club");
        System.out.println("Enter (3) - Display Statistics of a Selected Club ");
        System.out.println("Enter (4) - Display PremierLeague Table");
        System.out.println("Enter (5) - Add a Played Match with date (Set a Match) ");        
        System.out.println("Enter (6) - Display All Matches Played ");
        System.out.println("Enter (7) - Save Data to File ");
        System.out.println("Enter (8) - Open Premier League Web GUI ");
        System.out.println("Enter (0) - Quit");
        System.out.println("=====================================================================================\n");
        System.out.println("> Please Select An Option From The Following");
    }

    

    /** 
    * Add football club
    */
    private void addFootballClub() throws IOException{
        FootballClub footballClub;
        String clubName = keyScanner.inputWithValidation("\nEnter Club Name : ", ">>> Club cannot be empty");

        String clubLoc = keyScanner.inputWithValidation("\nEnter Club Location : ", ">>> Club location cannot be empty");

        String managerName = keyScanner.inputWithValidation("\nEnter name of the Club Manager : ", ">>> Club manager cannot be empty");

        boolean inputValid = false;
        System.out.print("\nEnter Formed year (Founded year) of the Club: ");
        while (!inputValid) {
            try {
                String input = keyScanner.readLine(); 
                int formedYear = Integer.parseInt(input);
                inputValid = true;

                footballClub = new FootballClub(clubName.trim(), clubLoc.trim(), managerName.trim(), formedYear);
                manager.addFootballClub(footballClub);
            } catch (NumberFormatException e) {
                System.out.println("Only integers are allowed...Please re-enter Formed year (Founded year) of the Club again: ");
            }
        }
       
        
    }

    /** 
    * Delete football club
    */
    private void deleteClub() throws IOException {
        String name = keyScanner.inputWithValidation("\nPlease enter club name you want to delete : ", ">>> Club name cannot be empty");
        manager.deleteClub(name.trim());
    }

    /** 
    * Display football club statistics
    */
    private void displayStatistics() {
        boolean inputValid = false;
        System.out.println("\nEnter Name of Club to display statistics: ");
        while (!inputValid) {
            try {
                String name = keyScanner.inputWithValidation("Enter Club Name  : ", ">>> Club Name cannot be empty");
                FootballClub ftc = manager.findTeamStatisticsByName(name.trim());
                if (Objects.isNull(ftc)) {
                    System.out.println("No such club exists");
                    break;
                } else {
                    System.out.printf("\nClub Name: %s\nClub Location: %s\nClub Manager: %s\nClub Formed Year: %s\nMatches played: %s\nWins: %s\nDraws: %s\nDefeat: %s\nGoals Scored: %s\nGoals Received: %s\nGoals Difference: %s\nPoints: %s\n", ftc.getClubName(), ftc.getClubLocation(),ftc.getClubManager(), ftc.getClubFormedYear(),ftc.getTotalMatches(), ftc.getWins(), ftc.getDraws(), ftc.getDefeats(), ftc.getGoalsScored(), ftc.getGoalsReceived(), ftc.getGoalsDifference(), ftc.getPoints());
                    System.out.println();
                    inputValid = true;
                }

            } catch (Exception e) {
                System.out.println("No integers are allowed...Please re-enter name of the club to display statistics ");
            }
        }

    }

    /** 
    * Set Match by entry home team and away team. If teams that are entered is not registered, 
    * App will open prompt to add football club
    */
    private void setMatches() throws IOException {
        Date date = new Date();
        System.out.println("\nSetting Matches...\n");
        String homeTeamName, awayTeamName;
        int homeScore = -1, awayScore = -1;
        System.out.print("Enter Date Of Match(DD/MM/YYY)");
        int year, month, day;
        String dateFormat = "";
        boolean inputValidDay = false, inputValidMonth = false, inputValidYear = false,
                inputValidHomeScore = false, inputValidAwayScore = false;

        while (!inputValidDay) {
            try {
                System.out.print("\nDay: ");
                String dd = keyScanner.readLine();
                day = Integer.parseInt(dd);
                if (date.isValidDay(day)) {
                    dateFormat += "" + day;
                    inputValidDay = true;
                } else {
                    System.out.println("Invalid Day Input!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Day Input!");
            }
        }


        while (!inputValidMonth) {
            try {
                System.out.print("Month: ");
                String mm = keyScanner.readLine();

                month = Integer.parseInt(mm);
                if (date.isValidMonth(month)) {
                    dateFormat += "/" + month + "/";
                    inputValidMonth = true;
                } else {
                    System.out.println("Invalid Month Input!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Month Input!");
            }
        }

        while (!inputValidYear) {
            try {
                System.out.print("Year: ");
                String yy = keyScanner.readLine();

                year = Integer.parseInt(yy);
                if (date.isValidYear(year)) {
                    dateFormat += "" + year;
                    inputValidYear = true;
                } else {
                    System.out.println("Invalid Year Input!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Year Input!");
            }
        }

        System.out.println();

        System.out.print("Home Team Setup\n");
        homeTeamName = keyScanner.inputWithValidation("Enter Club Name  : ", ">>> Club Name cannot be empty");

        while (!inputValidHomeScore) {
            try {
                System.out.print("Enter Club Score: ");
                String scoreHome = keyScanner.readLine();

                homeScore = Integer.parseInt(scoreHome);
                if (homeScore >= 0) {
                    inputValidHomeScore = true;
                } else {
                    System.out.println("Invalid Score Input!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Score Input!");
            }
        }

        System.out.println();

        System.out.print("Away Team Setup\n");
        awayTeamName = keyScanner.inputWithValidation("Enter Club Name  : ", ">>> Club Name cannot be empty");

        while (!inputValidAwayScore) {
            try {
                System.out.print("Enter Club Score: ");
                String scoreAway = keyScanner.readLine();

                awayScore = Integer.parseInt(scoreAway);
                if (awayScore >= 0) {
                    inputValidAwayScore = true;
                } else {
                    System.out.println("Invalid Score Input!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Score Input!");
            }
        }
        Match match = manager.footBallMatch(homeTeamName.trim(), homeScore, awayTeamName.trim(), awayScore, dateFormat);
		if(match != null){
		  System.out.println("\nMatch has been added successfully");
		}
    }

    /** 
    * Display football club list with their statistics
    */
    private void displayLeague()  {
        manager.displayLeague();
    }

    /** 
    * Display all match
    */
    private void displayMatchesPlayed(){
        manager.displayMatchesPlayed();
    }
}
