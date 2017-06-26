package de.hofuniversity.core;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import de.hofuniversity.core.connect.ConnectToGoal;
import de.hofuniversity.core.connect.ConnectToGuestTeam;
import de.hofuniversity.core.connect.ConnectToHomeTeam;
import de.hofuniversity.util.ExceptionText;
import de.hofuniversity.util.comparator.GoalComparator;

/**
 * 
 */

/**
 * @author Michael Jahn
 *
 */
@Entity
@Table(name="t_match")
public class Match extends Identifier implements ConnectToGoal, ConnectToGuestTeam, ConnectToHomeTeam
{
    	@Column(name="c_group_id", nullable=false)
	private int groupId; // Spieltag
    	
    	@ManyToOne(targetEntity=Team.class, fetch=FetchType.EAGER)
    	@JoinColumn(name="c_home_team_id", referencedColumnName="c_id")
	private Team homeTeam;
    	
    	@ManyToOne(targetEntity=Team.class, fetch=FetchType.EAGER)
    	@JoinColumn(name="c_guest_team_id", referencedColumnName="c_id")
	private Team guestTeam;
    	
    	@OneToMany(mappedBy="match", fetch=FetchType.LAZY)
	private Collection<Goal> goalCollection;
	
	@ManyToOne(targetEntity=Stadium.class, fetch=FetchType.LAZY)
    	@JoinColumn(name="c_stadium_id", referencedColumnName="c_id")
	private Stadium stadium;
	
	@ManyToOne(targetEntity=Result.class, fetch=FetchType.LAZY)
    	@JoinColumn(name="c_half_result_id", referencedColumnName="c_id")
	private Result halfScore;
	
	@ManyToOne(targetEntity=Result.class, fetch=FetchType.LAZY)
    	@JoinColumn(name="c_final_result_id", referencedColumnName="c_id")
	private Result finalScore;
	
	@Column(name="c_viewers", nullable=true)
	private int viewers;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="c_date", nullable=false)
	private Calendar calendar;
	
	public Match() {}
	
	public int getGroupId()
	{
		return groupId;
	}

	public void setGroupId(int groupId)
	{
		if ( groupId < 0) { throw new IllegalArgumentException(
				ExceptionText.getInstance().getSetNegativeNumberIllegalArgumentExceptionMessage("group id", this)); }
		this.groupId = groupId;
	}

	public Team getHomeTeam()
	{
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam)
	{
		if (homeTeam == null) { throw new IllegalArgumentException(
				ExceptionText.getInstance().getSetNullIllegalArgumentExceptionMessage("home team", this)); }
		this.homeTeam = homeTeam;
	}

	public Team getGuestTeam()
	{
		return guestTeam;
	}

	public void setGuestTeam(Team guestTeam)
	{
		if (guestTeam == null) { throw new IllegalArgumentException(
				ExceptionText.getInstance().getSetNullIllegalArgumentExceptionMessage("guest team", this)); }
		this.guestTeam = guestTeam;
	}
	
	public Collection<Goal> getUnmodifiableGoalCollection()
	{
		if (this.getGoalCollection().isEmpty())
		{
			return Collections.emptyList();
		}
		return Collections.unmodifiableCollection(this.getGoalCollection());
	}

	private Collection<Goal> getGoalCollection()
	{
		if (this.goalCollection == null) {
			this.goalCollection = new TreeSet<Goal>(new GoalComparator());
		}
		return goalCollection;
	}

	public void addGoal(Goal goal)
	{
		if (goal == null) { throw new IllegalArgumentException(
			ExceptionText.getInstance().getAddNullIllegalArgumentExceptionMessage("goal", this));
		}
		this.getGoalCollection().add(goal);
	}

	public Stadium getStadium()
	{
		return this.stadium;
	}

	public void setStadium(Stadium stadium)
	{
		if (stadium == null) { throw new IllegalArgumentException(
			ExceptionText.getInstance().getSetNullIllegalArgumentExceptionMessage("stadium", this));
		}
		this.stadium = stadium;
	}

	public Result getHalfScore()
	{
		return this.halfScore;
	}

	public void setHalfScore(Result halfScore)
	{
		if (halfScore == null) { throw new IllegalArgumentException(
			ExceptionText.getInstance().getSetNullIllegalArgumentExceptionMessage("half score", this));
		}
		this.halfScore = halfScore;
	}

	public Result getFinalScore()
	{
		return this.finalScore;
	}

	public void setFinalScore(Result finalScore)
	{
		if (finalScore == null) { throw new IllegalArgumentException(
				ExceptionText.getInstance().getSetNullIllegalArgumentExceptionMessage("final score", this));
		}
		this.finalScore = finalScore;
	}

	public int getViewers()
	{
		return viewers;
	}

	public void setViewers(int viewers)
	{
		if ( viewers < 0) { throw new IllegalArgumentException(
				ExceptionText.getInstance().getSetNegativeNumberIllegalArgumentExceptionMessage("viewers", this)); }
		this.viewers = viewers;
	}

	public Calendar getCalendar()
	{
		return calendar;
	}

	public void setCalendar(Calendar calendar)
	{
		if (calendar == null) { throw new IllegalArgumentException(
				ExceptionText.getInstance().getSetNullIllegalArgumentExceptionMessage("calendar date", this));
		}
		this.calendar = calendar;
	}

	/* (non-Javadoc)
	 * @see de.hofuniversity.core.ConnectToHomeTeam#conntectToHomeTeam(de.hofuniversity.core.Team)
	 */
	@Override
	public void conntectToHomeTeam(Team team)
	{
		if (team == null) { throw new IllegalArgumentException(
				ExceptionText.getInstance().getConnectNullIllegalArgumentExceptionMessage("home team", this)); }
		this.setHomeTeam(team);
		team.addMatch(this);
	}

	/* (non-Javadoc)
	 * @see de.hofuniversity.core.ConnectToGuestTeam#conntectToGuestTeam(de.hofuniversity.core.Team)
	 */
	@Override
	public void conntectToGuestTeam(Team team)
	{
		if (team == null) { throw new IllegalArgumentException(
				ExceptionText.getInstance().getConnectNullIllegalArgumentExceptionMessage("guest team", this)); }
		this.setGuestTeam(team);
		team.addMatch(this);
	}

	/* (non-Javadoc)
	 * @see de.hofuniversity.core.ConnectToGoal#conntectToGoal(de.hofuniversity.core.Goal)
	 */
	@Override
	public void conntectToGoal(Goal goal)
	{
		if (goal == null) { throw new IllegalArgumentException(
				ExceptionText.getInstance().getConnectNullIllegalArgumentExceptionMessage("goal", this)); }
		this.addGoal(goal);
		goal.setMatch(this);
	}
	
	@Override
	public String toString() {
	    return "Es spielten " + this.getHomeTeam() + " gegen " + this.getGuestTeam() + " " + 
		    this.getFinalScore() + " (" + this.getHalfScore() + ") " + Arrays.toString(this.getUnmodifiableGoalCollection().toArray())
		    + " mit " + this.getViewers() + " Besuchern am " + new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").format(this.getCalendar().getTime());
	}
	

}
