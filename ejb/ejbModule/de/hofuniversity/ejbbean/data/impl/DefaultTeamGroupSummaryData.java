package de.hofuniversity.ejbbean.data.impl;

import de.hofuniversity.ejbbean.data.TeamGroupSummaryData;

public class DefaultTeamGroupSummaryData implements TeamGroupSummaryData {
    
    private int points, goalPlus, goalMinus, gameAmount;
    private String teamName, teamIconUrl;
    
    public DefaultTeamGroupSummaryData() {}

    @Override
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public int getGoalPlus() {
        return goalPlus;
    }

    public void setGoalPlus(int goalPlus) {
        this.goalPlus = goalPlus;
    }

    @Override
    public int getGoalMinus() {
        return goalMinus;
    }

    public void setGoalMinus(int goalMinus) {
        this.goalMinus = goalMinus;
    }

    @Override
    public int getGameAmount() {
        return gameAmount;
    }

    public void setGameAmount(int gameAmount) {
        this.gameAmount = gameAmount;
    }

    @Override
    public String getTeamIconUrl() {
	return this.teamIconUrl;
    }

    public void setTeamIconUrl(String teamIconUrl) {
	this.teamIconUrl = teamIconUrl;
    }

    @Override
    public String getTeamName() {
	return this.teamName;
    }

    public void setTeamName(String teamName) {
	this.teamName = teamName;
    }
}
