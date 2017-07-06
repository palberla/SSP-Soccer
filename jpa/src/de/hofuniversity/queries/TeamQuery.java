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
	CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
	CriteriaQuery<Team> criteriaQuery = criteriaBuilder.createQuery(Team.class);
	Root<Team> teamRoot = criteriaQuery.from(Team.class);
	ParameterExpression<Integer> paramExp = criteriaBuilder.parameter(Integer.class);
	criteriaQuery.select(teamRoot).where(criteriaBuilder.equal(teamRoot.get("id"), paramExp));
	TypedQuery<Team> query = this.getEntityManager().createQuery(criteriaQuery);
	query.setParameter(paramExp, id);
	return query.getSingleResult();
    }

    public Team getTeam(String iconURL) {
	TypedQuery<Team> query = this.getEntityManager().createQuery("SELECT t FROM Team t WHERE t.iconURL = :iconUrl", Team.class);
	query.setParameter("iconUrl", iconURL);

	return query.getSingleResult();
    }
}
