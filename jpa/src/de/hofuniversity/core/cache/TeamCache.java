package de.hofuniversity.core.cache;

import de.hofuniversity.core.Team;


/**
 * 
 */

/**
 * @author Michael Jahn
 *
 */
public class TeamCache extends Cache<Team>
{
	private static TeamCache INSTANCE;
	
	private Team cachedTeam = null;
	
	private TeamCache() {}
	
	private Team getTeam(int id)
	{
		for (Team team : this.getCollection())
		{
			if (team.getId() == id)
			{
				return team;
			}
		}
		return null;
	}
	
	public boolean contains(int id)
	{
		Team team = this.getTeam(id);
		if (team != null)
		{
			this.cachedTeam = team;
			return true;
		}
		return false;
	}
	
	public Team get(int id)
	{
		if (this.cachedTeam != null && id == this.cachedTeam.getId()) { 
			return this.cachedTeam; 
			}
		Team team = this.getTeam(id);
		if (team == null)
		{
			throw new IllegalArgumentException("No team with id " + id + " found in cache.");
		}
		return team;
	}

	public static TeamCache getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TeamCache();
		}
		return INSTANCE;
	}
}
