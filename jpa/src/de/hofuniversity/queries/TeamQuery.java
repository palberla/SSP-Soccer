package de.hofuniversity.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.hofuniversity.core.Team;

public class TeamQuery {

    private EntityManagerFactory EntityManagerFactory = null;

    private EntityManager entityManager = null;
    
    protected TeamQuery() {}
    
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
    
    public List<Team> getAllTeams()
    {
	TypedQuery<Team> query = this.getEntityManager().createQuery("SELECT t FROM Team t", Team.class);

	return query.getResultList();
    }
    
    public Team getTeam(int id) {
	if (id < 1) {
	    throw new IllegalArgumentException("Id must not lower than 1");
	}

	TypedQuery<Team> query = this.getEntityManager().createQuery("SELECT t FROM Team t WHERE t.id = :id", Team.class);
	query.setParameter("id", id);

	return query.getSingleResult();
    }

    public Team getTeam(String iconURL) {
	TypedQuery<Team> query = this.getEntityManager().createQuery("SELECT t FROM Team t WHERE t.iconURL = :iconUrl", Team.class);
	query.setParameter("iconUrl", iconURL);

	return query.getSingleResult();
    }
}
