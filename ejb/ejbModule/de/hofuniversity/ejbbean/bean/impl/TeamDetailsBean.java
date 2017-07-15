package de.hofuniversity.ejbbean.bean.impl;

import java.util.List;

import javax.ejb.Stateless;

import de.hofuniversity.core.Match;
import de.hofuniversity.core.Player;
import de.hofuniversity.core.Result;
import de.hofuniversity.core.Stadium;
import de.hofuniversity.core.Team;
import de.hofuniversity.ejbbean.bean.TeamDetailsRemote;
import de.hofuniversity.ejbbean.data.MatchGroupSummaryData;
import de.hofuniversity.ejbbean.data.TeamDetailsSummaryData;
import de.hofuniversity.ejbbean.data.impl.DefaultMatchGroupSummaryData;
import de.hofuniversity.ejbbean.data.impl.DefaultTeamDetailsSummaryData;
import de.hofuniversity.queries.MatchQuery;
import de.hofuniversity.queries.QueryCache;
import de.hofuniversity.queries.TeamQuery;

/**
 * 
 * @author Michael Jahn
 *
 */

@Stateless(name = TeamDetailsRemote.MAPPED_NAME, mappedName = TeamDetailsRemote.MAPPED_NAME)
public class TeamDetailsBean implements TeamDetailsRemote {
    
    private QueryCache queryCache;
    
    public TeamDetailsBean() {}
    
    private QueryCache getQueryCache()
    {
	if (this.queryCache == null)
	{
	    this.queryCache = QueryCache.getInstance();
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
    public TeamDetailsSummaryData getTeamDetails(int id) {
	Team team = this.getTeamQuery().getTeam(id);
	return this.getTeamDetails(team);
    }
    
    private TeamDetailsSummaryData getTeamDetails(Team team) {
	DefaultTeamDetailsSummaryData tdsd = new DefaultTeamDetailsSummaryData();
	
	List<Match> matchList = this.getMatchQuery().getTeamMatches(team.getId());
	
	tdsd.setTeamName(team.getName());
	tdsd.setTeamIconURL(team.getIconURL());
	
	Stadium stadium = team.getStadium();
	tdsd.setLatitude(stadium.getGeologicalCoordinates().getLatitude());
	tdsd.setLongitude(stadium.getGeologicalCoordinates().getLongitude());
	tdsd.setStadiumAdress(stadium.getAddress());
	tdsd.setStadiumCapacity(stadium.getViewers());
	tdsd.setStadiumName(stadium.getName());
	tdsd.setStadiumInsideURL(stadium.getImageInside());
	tdsd.setStadiumOutsideURL(stadium.getImageOutside());
	
	for (Player player : team.getUnmodifiablePlayerCollection())
	{
	    tdsd.addPlayer(player);
	}
	
	for (Match match : matchList)
	{
	    tdsd.addTeamMatch(this.getMatchGroupSummaryData(match));
	}
	
	return tdsd;
    }
    
    private MatchGroupSummaryData getMatchGroupSummaryData(Match match) {
	DefaultMatchGroupSummaryData dmgsd = new DefaultMatchGroupSummaryData();
	
	Team homeTeam = match.getHomeTeam();
	Team guestTeam = match.getGuestTeam();
	Result finalResult = match.getFinalScore();
	Result halfResult = match.getHalfScore();
	
	boolean hasFinalResult = finalResult != null;
	
	if (hasFinalResult)
	{
		dmgsd.setHomeTeamFinalPoints(finalResult.getPointsHome());
		dmgsd.setGuestTeamFinalPoints(finalResult.getPointsGuest());
		
		dmgsd.setHomeTeamHalfPoints(halfResult.getPointsHome());
		dmgsd.setGuestTeamHalfPoints(halfResult.getPointsGuest());
	}
	else
	{
		dmgsd.setHomeTeamFinalPoints(-1);
		dmgsd.setGuestTeamFinalPoints(-1);
		
		dmgsd.setHomeTeamHalfPoints(-1);
		dmgsd.setGuestTeamHalfPoints(-1);
	}
	
	dmgsd.setHomeTeamName(homeTeam.getName());
	dmgsd.setHomeTeamIconUrl(homeTeam.getIconURL());
	dmgsd.setGuestTeamName(guestTeam.getName());
	dmgsd.setGuestTeamIconUrl(guestTeam.getIconURL());
	
	return dmgsd;
    }

    @Override
    public TeamDetailsSummaryData getTeamDetailsForIconURL(String iconURL) {
	Team team = this.getTeamQuery().getTeam(iconURL);
	return this.getTeamDetails(team);
    }
}