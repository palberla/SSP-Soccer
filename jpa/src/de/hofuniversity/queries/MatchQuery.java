package de.hofuniversity.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.hofuniversity.core.Match;

public class MatchQuery {
    
    private static EntityManagerFactory	ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("SSP-Soccer-Java");

    private EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    
    public List<Match> getTeamMatches(int id) {
	if (id < 1) {
	    throw new IllegalArgumentException("Id must not lower than 1");
	}
	// Exception, wenn ID nicht gefunden.

	TypedQuery<Match> query = this.entityManager.createQuery(
		"SELECT m FROM Team t, Match m WHERE t.id = :id AND (m.homeTeam.id = :id OR m.guestTeam.id = :id)", Match.class);
	query.setParameter("id", id);

	return query.getResultList();
    }

    public List<Match> getTeamPlayedMatches(int id) {
	if (id < 1) {
	    throw new IllegalArgumentException("Id must not lower than 1");
	}

	TypedQuery<Match> query = this.entityManager.createQuery(
		"SELECT m FROM Team t, Match m WHERE t.id = :id AND (m.homeTeam.id = :id OR m.guestTeam.id = :id) AND (m.finalScore IS NOT NULL)",
		Match.class);
	query.setParameter("id", id);

	return query.getResultList();
    }
    
    public List<Match> getAllMatchesForGroupId(int groupId)
    {
	TypedQuery<Match> query = this.entityManager.createQuery("SELECT m FROM Match m WHERE m.groupId = :id", Match.class);
	query.setParameter("id", groupId);

	return query.getResultList();
    }
}
