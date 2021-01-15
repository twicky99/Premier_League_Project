package tasks;

import cli.PremierLeagueApplication;
import javax.inject.Inject;
import com.typesafe.config.Config;
import java.io.IOException; 

/** 
* This class is responsible to handle thread that make CLI application to run in Play Framework console.
* AppThread extends Java Thread which allows this process to run in background not in foreground
*/
public class AppThread extends Thread{
	private final Config config;
	private final PremierLeagueApplication premierLeagueApp;

	/**
	* Constructor of AppThread
	* @param premierLeagueApp instance of PremierLeagueApplication class
	* @param config The Class which allows to access data from configuration file like application.conf
	*/
	@Inject
	public AppThread(PremierLeagueApplication premierLeagueApp, Config config){
		this.premierLeagueApp = premierLeagueApp;
		this.config = config;
	}

	/**
	* method that called when this class instantiated or loaded
	*/
	@Override
    public void run(){
    	System.out.println("Console App Running");

        String leagueFile = config.getString("file.league_file_name");
        String matchFile = config.getString("file.match_file_name");

        try{
        	premierLeagueApp.runConsole(leagueFile, matchFile);
        }catch(IOException e){
        	System.out.println(e.toString());
        }
		
    }
	
}