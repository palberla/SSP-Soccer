package de.hofuniversity.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.hofuniversity.core.Goal;

public class GoalQuery {
    private EntityManagerFactory EntityManagerFactory = null;

    private EntityManager entityManager = null;
    
    protected GoalQuery() {}
    
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
    
    public List<Goal> getGoalListForMatchId(int matchId)
    {
	TypedQuery<Goal> query = this.getEntityManager().createQuery(
		"Select g FROM Goal g WHERE g.match.id = :id", Goal.class);
	query.setParameter("id", matchId);

	return query.getResultList();
    }
}
