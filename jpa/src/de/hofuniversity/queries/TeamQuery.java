package de.hofuniversity.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.hofuniversity.core.Team;

public class TeamQuery {
    
    private static EntityManagerFactory	ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("SSP-Soccer-Java");

    private EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    
    public List<Team> getAllTeams()
    {
	TypedQuery<Team> query = this.entityManager.createQuery("SELECT t FROM Team t", Team.class);

	return query.getResultList();
    }
    
    public Team getTeam(int id) {
	if (id < 1) {
	    throw new IllegalArgumentException("Id must not lower than 1");
	}

	TypedQuery<Team> query = this.entityManager.createQuery("SELECT t FROM Team t WHERE t.id = :id)", Team.class);
	query.setParameter("id", id);

	return query.getSingleResult();
    }
}
