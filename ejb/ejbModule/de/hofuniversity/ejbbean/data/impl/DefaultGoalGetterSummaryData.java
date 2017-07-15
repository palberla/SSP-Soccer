package de.hofuniversity.ejbbean.data.impl;

import de.hofuniversity.ejbbean.data.GoalGetterSummaryData;

/**
 * 
 * @author Michael Jahn
 *
 */

public class DefaultGoalGetterSummaryData implements GoalGetterSummaryData {
    
    private int goalAmount;
    private String playerName, teamName, teamIconURL;

    @Override
    public int getGoalAmount() {
	return this.goalAmount;
    }

    @Override
    public String getPlayerName() {
	return this.playerName;
    }

    @Override
    public String getTeamName() {
	return this.teamName;
    }

    @Override
    public String getTeamIconURL() {
	return this.teamIconURL;
    }

    public void setGoalAmount(int goalAmount) {
        this.goalAmount = goalAmount;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamIconURL(String teamIconURL) {
        this.teamIconURL = teamIconURL;
    }

}
