package de.hofuniversity.bean;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.hofuniversity.bean.tablelist.DefaultTeamGroupSummaryData;
import de.hofuniversity.bean.tablelist.TableListComparator;
import de.hofuniversity.bean.tablelist.TeamGroupSummaryData;
import de.hofuniversity.core.Match;
import de.hofuniversity.core.Result;
import de.hofuniversity.core.Team;

//@Transactional
@ManagedBean
@ViewScoped
public class TableListBean {
    private static EntityManagerFactory	ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("SSP-Soccer-Java");

    private EntityManager		entityManager	       = ENTITY_MANAGER_FACTORY.createEntityManager();

    public TableListBean() {}
    
    public Set<TeamGroupSummaryData> getTableList()
    {
	SortedSet<TeamGroupSummaryData> tableList = new TreeSet<TeamGroupSummaryData>(new TableListComparator());
	
	for (Team team : this.getAllTeams())
	{
	    tableList.add(this.getTeamGroupSummaryData(team));
	}
	
	return tableList;
    }
    
    private TeamGroupSummaryData getTeamGroupSummaryData(Team team) {
	DefaultTeamGroupSummaryData teamGroupSummaryData = new DefaultTeamGroupSummaryData();
	
	teamGroupSummaryData.setTeamName(team.getName());
	teamGroupSummaryData.setTeamIconUrl(team.getIconURL());
	
	int id = team.getId();

	Collection<Match> matchCollection = this.getTeamPlayedMatches(id);

	int points = 0, goalPlus = 0, goalMinus = 0, gameAmount = 0;
	boolean isHome = false;

	for (Match match : matchCollection) {
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

//    private TeamGroupSummaryData getTeamGroupSummaryData(int id) {
//	return this.getTeamGroupSummaryData(this.getTeam(id));
//    }
    
//    private Collection<Team> getAllTeams()
//    {
//	TypedQuery<Team> query = this.entityManager.createQuery("SELECT t FROM Team t", Team.class);
//
//	return query.getResultList();
//    }
    
    public List<Team> getAllTeams()
    {
	TypedQuery<Team> query = this.entityManager.createQuery("SELECT t FROM Team t", Team.class);

	return query.getResultList();
    }

    private Team getTeam(int id) {
	if (id < 1) {
	    throw new IllegalArgumentException("Id must not lower than 1");
	}

	TypedQuery<Team> query = this.entityManager.createQuery("SELECT t FROM Team t WHERE t.id = :id)", Team.class);
	query.setParameter("id", id);

	return query.getSingleResult();
    }

    private Collection<Match> getTeamMatches(int id) {
	if (id < 1) {
	    throw new IllegalArgumentException("Id must not lower than 1");
	}
	// Exception, wenn ID nicht gefunden.

	TypedQuery<Match> query = this.entityManager.createQuery(
		"SELECT m FROM Team t, Match m WHERE t.id = :id AND (m.homeTeam.id = :id OR m.guestTeam.id = :id)", Match.class);
	query.setParameter("id", id);

	return query.getResultList();
    }

    private Collection<Match> getTeamPlayedMatches(int id) {
	if (id < 1) {
	    throw new IllegalArgumentException("Id must not lower than 1");
	}

	TypedQuery<Match> query = this.entityManager.createQuery(
		"SELECT m FROM Team t, Match m WHERE t.id = :id AND (m.homeTeam.id = :id OR m.guestTeam.id = :id) AND (m.finalScore IS NOT NULL)",
		Match.class);
	query.setParameter("id", id);

	return query.getResultList();
    }

}