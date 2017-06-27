package de.hofuniversity.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import de.hofuniversity.bean.grouplist.DefaultMatchGroupSummaryData;
import de.hofuniversity.bean.grouplist.MatchGroupSummaryData;
import de.hofuniversity.core.Match;
import de.hofuniversity.core.Result;
import de.hofuniversity.core.Team;
import de.hofuniversity.queries.MatchQuery;

@Stateless
public class GroupListBean {
    
    private MatchQuery matchQuery;

    public GroupListBean() { this.matchQuery = new MatchQuery(); }
    
    public List<MatchGroupSummaryData> getMatchGroupSummaryDataList(int groupId)
    {
	List<MatchGroupSummaryData> matchGroupSummaryList = new ArrayList<MatchGroupSummaryData>();
	
	for (Match match : this.matchQuery.getAllMatchesForGroupId(groupId))
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
