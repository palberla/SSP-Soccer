package de.hofuniversity.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import de.hofuniversity.bean.tablelist.DefaultTeamGroupSummaryData;
import de.hofuniversity.bean.tablelist.TableListComparator;
import de.hofuniversity.bean.tablelist.TeamGroupSummaryData;
import de.hofuniversity.core.Match;
import de.hofuniversity.core.Result;
import de.hofuniversity.core.Team;
import de.hofuniversity.queries.MatchQuery;
import de.hofuniversity.queries.TeamQuery;

//@Transactional
@ManagedBean
@ViewScoped
@Stateless
public class TableListBean {
    
    private TeamQuery teamQuery;
    private MatchQuery matchQuery;

    public TableListBean() { this.teamQuery = new TeamQuery(); this.matchQuery = new MatchQuery(); }
    
    public List<TeamGroupSummaryData> getTableList()
    {
	SortedSet<TeamGroupSummaryData> tableSortedSet = new TreeSet<TeamGroupSummaryData>(new TableListComparator());
	
	for (Team team : this.teamQuery.getAllTeams())
	{
	    tableSortedSet.add(this.getTeamGroupSummaryData(team));
	}
	
	return new ArrayList<TeamGroupSummaryData>(tableSortedSet);
    }
    
    private TeamGroupSummaryData getTeamGroupSummaryData(Team team) {
	DefaultTeamGroupSummaryData teamGroupSummaryData = new DefaultTeamGroupSummaryData();
	
	teamGroupSummaryData.setTeamName(team.getName());
	teamGroupSummaryData.setTeamIconUrl(team.getIconURL());
	
	int id = team.getId();

	List<Match> matchList = this.matchQuery.getTeamPlayedMatches(id);

	int points = 0, goalPlus = 0, goalMinus = 0, gameAmount = 0;
	boolean isHome = false;

	for (Match match : matchList) {
	    if (match.getHomeTeam().getId() == id) {
		isHome = true;
	    } else {
		isHome = false;
	    }
	    Result finalResult = match.getFinalScore();
	    gameAmount++;
	    if (finalResult.getPointsHome() == finalResult.getPointsGuest()) {
		points += 1;
	    }
	    if (isHome) {
		if (finalResult.getPointsHome() > finalResult.getPointsGuest()) {
		    points += 3;
		}
		goalPlus += finalResult.getPointsHome();
		goalMinus += finalResult.getPointsGuest();
	    } else {
		if (finalResult.getPointsGuest() > finalResult.getPointsHome()) {
		    points += 3;
		}
		goalPlus += finalResult.getPointsGuest();
		goalMinus += finalResult.getPointsHome();
	    }
	}

	teamGroupSummaryData.setGameAmount(gameAmount);
	teamGroupSummaryData.setPoints(points);
	teamGroupSummaryData.setGoalPlus(goalPlus);
	teamGroupSummaryData.setGoalMinus(goalMinus);

	return teamGroupSummaryData;
    }
}