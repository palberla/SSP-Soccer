package de.hofuniversity.ejbbean.bean.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.Stateless;

import de.hofuniversity.core.Match;
import de.hofuniversity.core.Result;
import de.hofuniversity.core.Team;
import de.hofuniversity.ejbbean.bean.TableListRemote;
import de.hofuniversity.ejbbean.data.TeamGroupSummaryData;
import de.hofuniversity.ejbbean.data.comparator.TableListComparator;
import de.hofuniversity.ejbbean.data.impl.DefaultTeamGroupSummaryData;
import de.hofuniversity.queries.MatchQuery;
import de.hofuniversity.queries.QueryCache;
import de.hofuniversity.queries.TeamQuery;


@Stateless(name = TableListRemote.MAPPED_NAME, mappedName = TableListRemote.MAPPED_NAME)
public class TableListBean implements TableListRemote {
    
    private QueryCache queryCache;
    
    public TableListBean() {}
    
    private QueryCache getQueryCache()
    {
	if (this.queryCache == null)
	{
	    this.queryCache = QueryCache.getQueryCache();
	}
	return this.queryCache;
    }
    
    private TeamQuery getTeamQuery()
    {
	return this.getQueryCache().getTeamQuery();
    }
    
    private MatchQuery getMatchQuery()
    {
	return this.getQueryCache().getMatchQuery();
    }
    
    public List<TeamGroupSummaryData> getTableList()
    {
	SortedSet<TeamGroupSummaryData> tableSortedSet = new TreeSet<TeamGroupSummaryData>(new TableListComparator());
	
	for (Team team : this.getTeamQuery().getAllTeams())
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

	List<Match> matchList = this.getMatchQuery().getTeamPlayedMatches(id);

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