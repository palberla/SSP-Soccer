package de.hofuniversity.core;
/**
 * 
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.hofuniversity.core.connect.ConnectToPlayer;
import de.hofuniversity.util.ExceptionText;

/**
 * @author Michael Jahn
 *
 */

@Entity
@Table(name="t_goal")
public class Goal extends Identifier implements ConnectToPlayer, Serializable
{	
    	@Column(name="c_points_home", nullable=false)
	private int pointsHome = -1;
    	
    	@Column(name="c_points_guest", nullable=false)
	private int pointsGuest = -1;
    	
    	@Column(name="c_minute", nullable=false)
	private int minute = -1;
    	
    	@ManyToOne(targetEntity=Match.class, fetch=FetchType.LAZY)
    	@JoinColumn(name="c_match_id", referencedColumnName="c_id")
	private Match match = null;
    	
    	@ManyToOne(targetEntity=Player.class, fetch=FetchType.LAZY)
    	@JoinColumn(name="c_player_id", referencedColumnName="c_id")
	private Player player = null;
    	
    	@Column(name="c_penalty", nullable=true)
    	private boolean penaltyGoal;
    	@Column(name="c_own", nullable=true)
    	private boolean ownGoal;
    	@Column(name="c_overtime", nullable=true)
    	private boolean overtimeGoal;

	@Column(name="c_comment", nullable=true)
	private String comment = null;
	
	public Goal() {}
	
	public Player getPlayer()
	{
		return this.player;
	}

	public void setPlayer(Player player)
	{
		if (player == null) { throw new IllegalArgumentException(
				ExceptionText.getInstance().getSetNullIllegalArgumentExceptionMessage("player", this)); }
		this.player = player;
	}
	
	public int getPointsHome()
	{
		return this.pointsHome;
	}

	public void setPointsHome(int pointsHome)
	{
		if (pointsHome < 0) { throw new IllegalArgumentException(
				ExceptionText.getInstance().getSetNegativeNumberIllegalArgumentExceptionMessage("home points", this)); }
		this.pointsHome = pointsHome;
	}

	public int getPointsGuest()
	{
		return this.pointsGuest;
	}

	public void setPointsGuest(int pointsGuest)
	{
		if (pointsGuest < 0) { throw new IllegalArgumentException(
				ExceptionText.getInstance().getSetNegativeNumberIllegalArgumentExceptionMessage("guest points", this)); }
		this.pointsGuest = pointsGuest;
	}
	
	public int getMinute()
	{
		return minute;
	}

	public void setMinute(int minute)
	{
		if (minute < 0) { throw new IllegalArgumentException(
				ExceptionText.getInstance().getSetNegativeNumberIllegalArgumentExceptionMessage("minute", this)); }
		this.minute = minute;
	}

	public Match getMatch()
	{
		return match;
	}

	public void setMatch(Match match)
	{
		if (match == null) { throw new IllegalArgumentException(
				ExceptionText.getInstance().getSetNullIllegalArgumentExceptionMessage("match", this)); }
		this.match = match;
	}
	
	public boolean isPenaltyGoal() {
	    return penaltyGoal;
	}

	public void setPenaltyGoal(boolean penaltyGoal) {
	    this.penaltyGoal = penaltyGoal;
	}

	public boolean isOwnGoal() {
	    return ownGoal;
	}

	public void setOwnGoal(boolean ownGoal) {
	    this.ownGoal = ownGoal;
	}

	public boolean isOvertimeGoal() {
	    return overtimeGoal;
	}

	public void setOvertimeGoal(boolean overtimeGoal) {
	    this.overtimeGoal = overtimeGoal;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
	    StringBuilder comment = new StringBuilder();
	    if (this.isPenaltyGoal())
	    {
		comment.append("Elfmeter");
	    }
	    
	    if (this.isOwnGoal())
	    {
		if (comment.length() > 0)
		{
		    comment.append(", ");
		}
		comment.append("Eigentor");
	    }
	    
	    if (this.isOvertimeGoal())
	    {
		if (comment.length() > 0)
		{
		    comment.append(", ");
		}
		comment.append("Nachspielzeit");
	    }
	    
	    if (this.getComment() != null)
	    {
		if (comment.length() > 0)
		{
		    comment.append(" : ");
		}
		comment.append(this.getComment());
	    }
	    
		return this.getPointsHome() + " : " + this.getPointsGuest() + " " + this.getPlayer().getName() + " in der " + this.getMinute() + ". Minute" + ((this.getComment() == null) ? "" : "( " + comment.toString() + ")");
	}

	/* (non-Javadoc)
	 * @see de.hofuniversity.core.ConnectToPlayer#conntectToPlayer(de.hofuniversity.core.Player)
	 */
	@Override
	public void conntectToPlayer(Player player)
	{
		if (player == null) { throw new IllegalArgumentException(
				ExceptionText.getInstance().getConnectNullIllegalArgumentExceptionMessage("player", this)); }
		this.setPlayer(player);
		player.addGoal(this);
		
	}
	
	
}
