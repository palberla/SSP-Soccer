package de.hofuniversity.io.xml.util;

import de.hofuniversity.core.Goal;
import de.hofuniversity.core.Match;
import de.hofuniversity.core.Team;
import de.hofuniversity.core.cache.PlayerCache;

public class GoalPlayerToTeamPlayerConnection {
    
    public GoalPlayerToTeamPlayerConnection() {}
    
    public void conntectGoalPlayerToTeamPlayer(Goal goal, Match match)
    {
	Goal previousGoal = this.getPreviousGoal(goal, match);
	Team team = null;
	boolean ownGoal = goal.isOwnGoal();
	if (previousGoal == null) {
	    if(goal.getPointsHome() > goal.getPointsGuest()) {
		team = (ownGoal) ? match.getGuestTeam() : match.getHomeTeam();
	    } else {
		team = (ownGoal) ? match.getHomeTeam() : match.getGuestTeam();
	    }
	} else {
	    if (goal.getPointsHome() > previousGoal.getPointsHome()) {
		team = (ownGoal) ? match.getGuestTeam() : match.getHomeTeam();
	    } else {
		team = (ownGoal) ? match.getHomeTeam() : match.getGuestTeam();
	    }
	}
	if (team.containsPlayer(goal.getPlayer().getName())) {
	    goal.conntectToPlayer(team.getPlayer(goal.getPlayer().getName()));
	} else {
	    team.conntectToPlayer(goal.getPlayer());
	    PlayerCache.getInstance().add(goal.getPlayer());
	}
    }
	
    private Goal getPreviousGoal(Goal goal, Match match)
    {
	for (Goal matchGoal : match.getUnmodifiableGoalCollection())
	{
	    int difference = 0;
	    difference += matchGoal.getPointsHome() - goal.getPointsHome();
	    difference += matchGoal.getPointsGuest() - goal.getPointsGuest();
	    if (difference == -1)
	    {
		return matchGoal;
	    }
	}
	return null;
    }
}

