package de.hofuniversity.bean;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.hofuniversity.bean.grouplist.DefaultMatchGroupSummaryData;
import de.hofuniversity.bean.grouplist.MatchGroupSummaryData;
import de.hofuniversity.core.Match;
import de.hofuniversity.core.Result;
import de.hofuniversity.core.Team;

public class GroupListBean {

    private static EntityManagerFactory	ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("SSP-Soccer-Java");

    private EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();

    public GroupListBean() {}
    
    public Collection<MatchGroupSummaryData> getMatchGroupSummaryDataList(int groupId)
    {
	Collection<MatchGroupSummaryData> matchGroupSummaryList = new ArrayList<MatchGroupSummaryData>();
	
	for (Match match : this.getAllMatchesForId(groupId))
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

    
    
    private Collection<Match> getAllMatchesForId(int groupId)
    {
	TypedQuery<Match> query = this.entityManager.createQuery("SELECT m FROM Match m WHERE m.groupId = :id)", Match.class);
	query.setParameter("id", groupId);

	return query.getResultList();
    }
}
