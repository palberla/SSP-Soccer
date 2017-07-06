package de.hofuniversity.ejbbean.data.impl;

import java.util.ArrayList;
import java.util.List;

import de.hofuniversity.core.Goal;
import de.hofuniversity.ejbbean.data.MatchDetailsGoalSummaryData;
import de.hofuniversity.ejbbean.data.MatchDetailsSummaryData;

public class DefaultMatchDetailsSummaryData implements MatchDetailsSummaryData {
    
    private int stadiumCapacity;
    private String homeTeamName, homeTeamIconURL, guestTeamName, guestTeamIconURL, 
    		stadiumName, stadiumAdress, stadiumInsideURL, stadiumOutsideURL;
    private double longitude, latitude;
    private List<MatchDetailsGoalSummaryData> goalList;
    private String finalHomeScore, finalGuestScore, halfHomeScore, halfGuestScore;
    
    public DefaultMatchDetailsSummaryData() {}
    
    private String getStringOfScorePoints(int points) {
	if (points < 0) {
	    return "-";
	} else {
	    return String.valueOf(points);
	}
    }
    
    public void setFinalHomeScore(int finalHomeScore) {
        this.finalHomeScore = this.getStringOfScorePoints(finalHomeScore);
    }
    public void setFinalGuestScore(int finalGuestScore) {
        this.finalGuestScore = this.getStringOfScorePoints(finalGuestScore);
    }
    public void setHalfHomeScore(int halfHomeScore) {
        this.halfHomeScore = this.getStringOfScorePoints(halfHomeScore);
    }
    public void setHalfGuestScore(int halfGuestScore) {
        this.halfGuestScore = this.getStringOfScorePoints(halfGuestScore);
    }
    public void setStadiumCapacity(int stadiumCapacity) {
        this.stadiumCapacity = stadiumCapacity;
    }
    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }
    public void setHomeTeamIconURL(String homeTeamIconURL) {
        this.homeTeamIconURL = homeTeamIconURL;
    }
    public void setGuestTeamName(String guestTeamName) {
        this.guestTeamName = guestTeamName;
    }
    public void setGuestTeamIconURL(String guestTeamIconURL) {
        this.guestTeamIconURL = guestTeamIconURL;
    }
    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }
    public void setStadiumAdress(String stadiumAdress) {
        this.stadiumAdress = stadiumAdress;
    }
    public void setStadiumInsideURL(String stadiumInsideURL) {
        this.stadiumInsideURL = stadiumInsideURL;
    }
    public void setStadiumOutsideURL(String stadiumOutsideURL) {
        this.stadiumOutsideURL = stadiumOutsideURL;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public void addGoal(Goal goal) {
	DefaultMatchDetailsGoalSummaryData mdgsd = new DefaultMatchDetailsGoalSummaryData();
	
	mdgsd.setComment(goal.getComment());
	mdgsd.setGoalGetterName(goal.getPlayer().getName());
	mdgsd.setGuestScore(goal.getPointsGuest());
	mdgsd.setHomeScore(goal.getPointsHome());
	
        this.getGoalList().add(mdgsd);
    }
    @Override
    public String getHomeTeamName() {
	return this.homeTeamName;
    }
    @Override
    public String getHomeTeamIconURL() {
	return this.homeTeamIconURL;
    }
    @Override
    public String getGuestTeamName() {
	return this.guestTeamName;
    }
    @Override
    public String getGuestTeamIconURL() {
	return this.guestTeamIconURL;
    }
    @Override
    public String getFinalHomeScore() {
	return this.finalHomeScore;
    }
    @Override
    public String getFinalGuestScore() {
	return this.finalGuestScore;
    }
    @Override
    public String getHalfHomeScore() {
	return this.halfHomeScore;
    }
    @Override
    public String getHalfGuestScore() {
	return this.halfGuestScore;
    }
    @Override
    public String getStadiumName() {
	return this.stadiumName;
    }
    @Override
    public String getStadiumAdress() {
	return this.stadiumAdress;
    }
    @Override
    public int getStadiumCapacity() {
	return this.stadiumCapacity;
    }
    @Override
    public double getLongitude() {
	return this.longitude;
    }
    @Override
    public double getLatitude() {
	return this.latitude;
    }
    @Override
    public String getStadiumInsideURL() {
	return this.stadiumInsideURL;
    }
    @Override
    public String getStadiumOutsideURL() {
	return this.stadiumOutsideURL;
    }
    @Override
    public List<MatchDetailsGoalSummaryData> getGoalList() {
	if (this.goalList == null)
	{
	    this.goalList = new ArrayList<MatchDetailsGoalSummaryData>();
	}
	return this.goalList;
    }
}
