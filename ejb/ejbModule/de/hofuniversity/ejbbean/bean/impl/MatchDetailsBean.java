package de.hofuniversity.ejbbean.bean.impl;

import javax.ejb.Stateless;

import de.hofuniversity.core.Goal;
import de.hofuniversity.core.Match;
import de.hofuniversity.core.Stadium;
import de.hofuniversity.core.Team;
import de.hofuniversity.ejbbean.bean.MatchDetailsRemote;
import de.hofuniversity.ejbbean.data.MatchDetailsSummaryData;
import de.hofuniversity.ejbbean.data.impl.DefaultMatchDetailsSummaryData;
import de.hofuniversity.queries.MatchQuery;
import de.hofuniversity.queries.QueryCache;

@Stateless(name = MatchDetailsRemote.MAPPED_NAME, mappedName = MatchDetailsRemote.MAPPED_NAME)
public class MatchDetailsBean implements MatchDetailsRemote {
    
    private QueryCache queryCache;
    
    public MatchDetailsBean() {}
    
    private QueryCache getQueryCache()
    {
	if (this.queryCache == null)
	{
	    this.queryCache = QueryCache.getInstance();
	}
	return this.queryCache;
    }
    
    private MatchQuery getMatchQuery()
    {
	return this.getQueryCache().getMatchQuery();
    }
    
    public MatchDetailsSummaryData getMatchDetails(int matchId) {
	DefaultMatchDetailsSummaryData mdsd = new DefaultMatchDetailsSummaryData();
	Match match = this.getMatchQuery().getMatch(matchId);
	
	Team homeTeam = match.getHomeTeam();
	
	mdsd.setHomeTeamName(homeTeam.getName());
	mdsd.setHomeTeamIconURL(homeTeam.getIconURL());
	
	Team guestTeam = match.getGuestTeam();
	
	mdsd.setGuestTeamName(guestTeam.getName());
	mdsd.setGuestTeamIconURL(guestTeam.getIconURL());
	
	Stadium stadium = match.getStadium();
	
	mdsd.setStadiumAdress(stadium.getAddress());
	mdsd.setStadiumCapacity(stadium.getViewers());
	mdsd.setStadiumInsideURL(stadium.getImageInside());
	mdsd.setStadiumName(stadium.getName());
	mdsd.setStadiumOutsideURL(stadium.getImageOutside());
	mdsd.setLatitude(stadium.getGeologicalCoordinates().getLatitude());
	mdsd.setLongitude(stadium.getGeologicalCoordinates().getLongitude());
	
	if (match.getFinalScore() != null)
	{
	    mdsd.setFinalGuestScore(match.getFinalScore().getPointsGuest());
	    mdsd.setFinalHomeScore(match.getFinalScore().getPointsHome());
	    mdsd.setHalfGuestScore(match.getHalfScore().getPointsGuest());
	    mdsd.setHalfHomeScore(match.getHalfScore().getPointsHome());
	} else {
	    mdsd.setFinalGuestScore(-1);
	    mdsd.setFinalHomeScore(-1);
	    mdsd.setHalfGuestScore(-1);
	    mdsd.setHalfHomeScore(-1);
	}
	
	for (Goal goal : match.getUnmodifiableGoalCollection())
	{
	    mdsd.addGoal(goal);
	}
	
	return mdsd;
    }
}
