package de.hofuniversity.io.xml;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.jdom2.Element;

import de.hofuniversity.core.Goal;
import de.hofuniversity.core.Match;
import de.hofuniversity.core.Result;
import de.hofuniversity.core.cache.TeamCache;
import de.hofuniversity.io.xml.util.GoalPlayerToTeamPlayerConnection;
import de.hofuniversity.io.xml.util.PlayerNameEqualizer;

/**
 * @author Markus Exner
 *
 */

public class XMLMatchReader {
    
    private XMLMatchResultReader xmlMatchResultReader;
    private XMLMatchGoalReader xmlGoalReader;
    private PlayerNameEqualizer playerNameEqualizer; 
    private GoalPlayerToTeamPlayerConnection goalPlayerToTeamPlayerConnection;
    
    public XMLMatchReader() {}
    
    public Match readMatch(Element matchElement) throws Exception {
	
	if (matchElement == null) {
	    throw new IllegalArgumentException("Can not read NULL from a match"); 
	}
	
	if (xmlMatchResultReader == null) {
	    xmlMatchResultReader = new XMLMatchResultReader();
	}
	
	if (xmlGoalReader == null) {
	    xmlGoalReader = new XMLMatchGoalReader();
	}
	
	if (playerNameEqualizer == null) {
	    playerNameEqualizer = new PlayerNameEqualizer();
	}
	
	if (goalPlayerToTeamPlayerConnection == null) {
	    goalPlayerToTeamPlayerConnection = new GoalPlayerToTeamPlayerConnection();
	}
	
	Match match = new Match();
	
//	Calendar cal = Calendar.getInstance();
//	SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
//	cal.setTime(sdf.parse("Mon Mar 14 16:02:37 GMT 2011"));// all done

	match.setGroupId(Integer.parseInt(matchElement.getChildText("groupOrderID")));
	
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.GERMANY);
	cal.setTime(sdf.parse(matchElement.getChildText("matchDateTime").replaceAll("T", " ")));
	match.setCalendar(cal);
		
	
	if (matchElement.getChild("idTeam1") != null) {
	    match.conntectToHomeTeam(TeamCache.getInstance().get(Integer.parseInt(matchElement.getChildText("idTeam1"))));
	    match.setStadium(match.getHomeTeam().getStadium());
	}
	
	if (matchElement.getChild("idTeam2") != null) {
	    match.conntectToGuestTeam(TeamCache.getInstance().get(Integer.parseInt(matchElement.getChildText("idTeam2"))));
	}
	
	Result result;
	
	for (Element resultElement : matchElement.getChild("matchResults").getChildren("matchResult")) {
	    result = this.xmlMatchResultReader.readMatchResult(resultElement);
	    if (result.getId() == 1)
	    {
		match.setHalfScore(result);
	    }
	    else if (result.getId() == 2)
	    {
		match.setFinalScore(result);
	    }
	}
	
	for (Element goalElement : matchElement.getChild("goals").getChildren("Goal")) {
	    Goal goal = this.xmlGoalReader.readGoal(goalElement);
	    match.conntectToGoal(goal);
	}
	
	for (Goal goal : match.getUnmodifiableGoalCollection())
	{
	    this.goalPlayerToTeamPlayerConnection.conntectGoalPlayerToTeamPlayer(goal, match); 
	}
	String numberOfViewersString = matchElement.getChildText("NumberOfViewers");
	
	if (numberOfViewersString.length() > 0)
	{
	    match.setViewers(Integer.parseInt(matchElement.getChildText("NumberOfViewers")));
	}
		
	return match;
    }

}
