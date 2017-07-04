package de.hofuniversity.core;
/**
 * 
 */


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.hofuniversity.util.ExceptionText;

/**
 * @author Michael Jahn
 *
 */
@Entity
@Table(name="t_result")
public class Result extends Identifier
{
    	@Column(name="c_points_home", nullable=false)
	private int pointsHome;
    	
    	@Column(name="c_points_guest", nullable=false)
	private int pointsGuest;
	
	public Result() {}
	
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
	
	@Override
	public String toString() {
	return "[" + this.getId() + "]: " + this.getPointsHome() + ":" + this.getPointsGuest();
	}
}
