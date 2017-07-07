/**
 * 
 */
package de.hofuniversity;


import de.hofuniversity.core.Goal;
import de.hofuniversity.core.Match;
import de.hofuniversity.core.Player;
import de.hofuniversity.core.Result;
import de.hofuniversity.core.Stadium;
import de.hofuniversity.core.Team;
import de.hofuniversity.core.cache.GoalCache;
import de.hofuniversity.core.cache.MatchCache;
import de.hofuniversity.core.cache.PlayerCache;
import de.hofuniversity.core.cache.ResultCache;
import de.hofuniversity.core.cache.StadiumCache;
import de.hofuniversity.core.cache.TeamCache;
import de.hofuniversity.io.db.Database;
import de.hofuniversity.io.db.DatabaseGoalWriter;
import de.hofuniversity.io.db.DatabaseMatchWriter;
import de.hofuniversity.io.db.DatabasePlayerWriter;
import de.hofuniversity.io.db.DatabaseResultWriter;
import de.hofuniversity.io.db.DatabaseStadiumWriter;
import de.hofuniversity.io.db.DatabaseTeamWriter;
import de.hofuniversity.io.xml.XMLMatchesReader;
import de.hofuniversity.io.xml.XMLStadiumsReader;
import de.hofuniversity.io.xml.XMLTeamsReader;

/**
 * @author Michael Jahn
 *
 */
public class MainWritingData {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
	
	long milliseconds = System.currentTimeMillis();
	
	System.out.println("Erstelle Datenbank...");
	Database db = new Database();
	db.createSchema();
	System.out.println("...fertig");
	
	System.out.println("Lade teams.xml...");
	
	new XMLTeamsReader(XMLTeamsReader.class.getResourceAsStream("data/teams.xml")).readAllTeams();
//	new XMLTeamsReader(new FileReader("data/teams.xml")).readAllTeams();
	System.out.println("...fertig");
	System.out.println("Lade stadium.xml...");
	new XMLStadiumsReader(XMLStadiumsReader.class.getResourceAsStream("data/stadium.xml")).readAllStadiums();
//	new XMLStadiumsReader(new FileReader("data/stadium.xml")).readAllStadiums();
	System.out.println("...fertig");
	System.out.println("Lade matches.xml...");
	new XMLMatchesReader(XMLMatchesReader.class.getResourceAsStream("data/matches.xml")).readAllMatches();
//	new XMLMatchesReader(new FileReader("data/matches.xml")).readAllMatches();
	System.out.println("...fertig");
	
	int index = 0;
//	System.out.println("Vereine");
//	for (Team team : TeamCache.getInstance().getUnmodifiableCollection())
//	{
//	    idSysout(++index, team.toString());
//	}
//	System.out.println();
	
	index = 0;
//	System.out.println("Spieler");
//	for (Player player : PlayerCache.getInstance().getUnmodifiableCollection())
//	{
//	    idSysout(++index, player.toString());
//	}
//	System.out.println();
	
	index = 0;
//	System.out.println("Stadien");
//	for (Stadium stadium : StadiumCache.getInstance().getUnmodifiableCollection())
//	{
//	    idSysout(++index, stadium.toString());
//	}
//	System.out.println();
	
	index = 0;
//	System.out.println("Tore");
//	for (Goal goal : GoalCache.getInstance().getUnmodifiableCollection())
//	{
//	    idSysout(++index, goal.toString());
//	}
//	System.out.println();
	
	index = 0;
//	System.out.println("Ergebnisse");
//	for (Result result : ResultCache.getInstance().getUnmodifiableCollection())
//	{
//	    idSysout(++index, result.toString());
//	}
//	System.out.println();
	
	index = 0;
//	System.out.println("Begegnungen");
//	for (Match match : MatchCache.getInstance().getUnmodifiableCollection())
//	{
//	    idSysout(++index, match.toString());
//	}
//	System.out.println();
	
	
	System.out.println("Schreibe Stadien...");
	DatabaseStadiumWriter dbStadiumWriter = new DatabaseStadiumWriter(db);
	for (Stadium stadium : StadiumCache.getInstance().getUnmodifiableCollection()) {
	    dbStadiumWriter.writeStadium(stadium);
	}
	System.out.println("...fertig");
	System.out.println("Schreibe Vereine...");
	DatabaseTeamWriter dbTeamWriter = new DatabaseTeamWriter(db);
	for (Team team : TeamCache.getInstance().getUnmodifiableCollection()) {
	    dbTeamWriter.writeTeam(team);
	}
	System.out.println("...fertig");
	System.out.println("Schreibe Spieler...");
	DatabasePlayerWriter dbPlayerWriter = new DatabasePlayerWriter(db);
	for (Player player : PlayerCache.getInstance().getUnmodifiableCollection()) {
	    dbPlayerWriter.writePlayer(player);
	}
	System.out.println("...fertig");
	System.out.println("Schreibe Ergebnisse...");
	DatabaseResultWriter dbResultWriter = new DatabaseResultWriter(db);
	for (Result result : ResultCache.getInstance().getUnmodifiableCollection()) {
	    dbResultWriter.writeResult(result);
	}
	System.out.println("...fertig");
	System.out.println("Schreibe Begegnungen...");
	DatabaseMatchWriter dbMatchWriter = new DatabaseMatchWriter(db);
	for (Match match : MatchCache.getInstance().getUnmodifiableCollection()) {
	    dbMatchWriter.writeMatch(match);
	}
	System.out.println("...fertig");
	System.out.println("Schreibe Tore...");
	DatabaseGoalWriter dbGoalWriter = new DatabaseGoalWriter(db);
	for (Goal goal : GoalCache.getInstance().getUnmodifiableCollection()) {
	    dbGoalWriter.writeGoal(goal);
	}
	System.out.println("...fertig");
	System.out.println("Done");
	milliseconds = System.currentTimeMillis() - milliseconds;
	long seconds = milliseconds / 1000;
	long minutes = seconds / 60;
	seconds = seconds % 60;
//	System.out.println("Zeit: " + minutes + " Minuten und " + seconds + "Sekunden.");
    }
    
    private static void idSysout(int index, String string)
    {
	System.out.println("(" + index + ")" + string);
    }
}
