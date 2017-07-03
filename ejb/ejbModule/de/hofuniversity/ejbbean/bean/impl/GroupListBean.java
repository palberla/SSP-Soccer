package de.hofuniversity.ejbbean.bean.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import de.hofuniversity.core.Match;
import de.hofuniversity.core.Result;
import de.hofuniversity.core.Team;
import de.hofuniversity.ejbbean.bean.GroupListRemote;
import de.hofuniversity.ejbbean.data.MatchGroupSummaryData;
import de.hofuniversity.ejbbean.data.impl.DefaultMatchGroupSummaryData;
import de.hofuniversity.queries.MatchQuery;
import de.hofuniversity.queries.QueryCache;

@Stateless(name = GroupListRemote.MAPPED_NAME, mappedName = GroupListRemote.MAPPED_NAME)
public class GroupListBean implements GroupListRemote {
    
    private QueryCache queryCache;
    
    public GroupListBean() {}
    
    private QueryCache getQueryCache()
    {
	if (this.queryCache == null)
	{
	    this.queryCache = QueryCache.getQueryCache();
	}
	return this.queryCache;
    }
    
    private MatchQuery getMatchQuery()
    {
	return this.getQueryCache().getMatchQuery();
    }
    
    public List<MatchGroupSummaryData> getMatchGroupSummaryDataList(int groupId)
    {
	List<MatchGroupSummaryData> matchGroupSummaryList = new ArrayList<MatchGroupSummaryData>();
	
	for (Match match : this.getMatchQuery().getAllMatchesForGroupId(groupId))
	{
	    matchGroupSummaryList.add(this.getMatchGroupSummaryData(match));
	    
	}
	
	return matchGroupSummaryList;
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
}
