package de.hofuniversity.queries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.hofuniversity.core.Stadium;

public class StadiumQuery {
    
    private EntityManagerFactory EntityManagerFactory = null;

    private EntityManager entityManager = null;
    
    protected StadiumQuery() {}
    
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
    
    public Stadium getStadium(int stadiumId)
    {
	TypedQuery<Stadium> query = this.getEntityManager().createQuery("SELECT s FROM Stadium s WHERE s.id = :id", Stadium.class);
	query.setParameter("id", stadiumId);

	return query.getSingleResult();
    }
}
