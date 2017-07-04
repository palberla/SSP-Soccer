package de.hofuniversity.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.hofuniversity.core.Match;

public class MatchQuery {
    
    private EntityManagerFactory EntityManagerFactory = null;

    private EntityManager entityManager = null;
    
    protected MatchQuery() {}
    
    public EntityManager getEntityManager()
    {
	if (EntityManagerFactory == null)
	{
	    EntityManagerFactory = Persistence.createEntityManagerFactory("SSP-JPA");
	    entityManager = EntityManagerFactory.createEntityManager();
	}
	return entityManager;
    }
    
    public void close()
    {
	if ( entityManager != null)
	{
	    entityManager.close();
	}
    }
    
    
    public List<Match> getTeamMatches(int id) {
	if (id < 1) {
	    throw new IllegalArgumentException("Id must not lower than 1");
	}
	// Exception, wenn ID nicht gefunden.

	TypedQuery<Match> query = this.getEntityManager().createQuery(
		"SELECT m FROM Team t, Match m WHERE t.id = :id AND (m.homeTeam.id = :id OR m.guestTeam.id = :id)", Match.class);
	query.setParameter("id", id);

	return query.getResultList();
    }

    public List<Match> getTeamPlayedMatches(int id) {
	if (id < 1) {
	    throw new IllegalArgumentException("Id must not lower than 1");
	}

	TypedQuery<Match> query = this.getEntityManager().createQuery(
		"SELECT m FROM Team t, Match m WHERE t.id = :id AND (m.homeTeam.id = :id OR m.guestTeam.id = :id) AND (m.finalScore IS NOT NULL)",
		Match.class);
	query.setParameter("id", id);

	return query.getResultList();
    }
    
    public List<Match> getAllMatchesForGroupId(int groupId)
    {
	TypedQuery<Match> query = this.getEntityManager().createQuery("SELECT m FROM Match m WHERE m.groupId = :id", Match.class);
	query.setParameter("id", groupId);

	return query.getResultList();
    }
    
    public Match getMatch(int matchId)
    {
	TypedQuery<Match> query = this.getEntityManager().createQuery("SELECT m FROM Match m WHERE m.id = :id", Match.class);
	query.setParameter("id", matchId);

	return query.getSingleResult();
    }
}

