package de.hofuniversity.queries;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import de.hofuniversity.core.Goal;
import de.hofuniversity.core.Player;

public class PlayerQuery {

    private EntityManagerFactory EntityManagerFactory = null;

    private EntityManager entityManager = null;
    
    protected PlayerQuery() {}
    
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
    
    
    public List<Object[]> getGoalGetterDataList()
    {
	return this.getEntityManager().createQuery("SELECT p, COUNT(g.player.id) AS goalCount FROM Goal g, Player p WHERE g.player.id = p.id GROUP BY g.player.id ORDER BY goalCount DESC").getResultList();
    }
}
