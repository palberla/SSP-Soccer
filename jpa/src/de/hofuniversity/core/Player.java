package de.hofuniversity.core;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import javax.persistence.*;

import de.hofuniversity.util.ExceptionText;

/**
 * 
 */

/**
 * @author Michael Jahn
 *
 */
@Entity
@Table(name="t_player")
public class Player extends Named implements Serializable
{
    	@ManyToOne(targetEntity=Team.class, fetch=FetchType.LAZY)
    	@JoinColumn(name="c_team_id", referencedColumnName="c_id")
	private Team team;
    	
    	@Transient
	private Collection<Goal> goalCollection;
	
	public Player() {}

	public Team getTeam()
	{
		return this.team;
	}

	public void setTeam(Team team)
	{
		if (team == null) { throw new IllegalArgumentException(
				ExceptionText.getInstance().getSetNullIllegalArgumentExceptionMessage("team", this)); }
			this.team = team;
	}


	private Collection<Goal> getGoalCollection()
	{
		if (this.goalCollection == null) {
			goalCollection = new LinkedList<Goal>();
		}
		return this.goalCollection;
	}
	
	public Collection<Goal> getUnmodifiableGoalCollection()
	{
		Collection<Goal> goalCollection = this.getGoalCollection();
		if (goalCollection.isEmpty())
		{
			return Collections.emptyList();
		}
		return Collections.unmodifiableCollection(goalCollection);
	}

	public void addGoal(Goal goal)
	{
		if (goal == null) { throw new IllegalArgumentException(
				ExceptionText.getInstance().getAddNullIllegalArgumentExceptionMessage("goal", this));
		}
		this.getGoalCollection().add(goal);
	}
	
	@Override
	public String toString() {
	    return this.getName() + "[" + this.getId() + "]" + "Team: " + this.getTeam().getName();
	}
}
