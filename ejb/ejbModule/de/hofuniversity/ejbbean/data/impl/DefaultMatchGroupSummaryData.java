package de.hofuniversity.ejbbean.data.impl;

import de.hofuniversity.ejbbean.data.MatchGroupSummaryData;

public class DefaultMatchGroupSummaryData implements MatchGroupSummaryData {

    private String homeTeamName, homeTeamIconUrl, guestTeamName, guestTeamIconUrl;
    private String homeTeamFinalPoints, homeTeamHalfPoints, guestTeamFinalPoints, guestTeamHalfPoints;

    @Override
    public String getHomeTeamName() {
	return this.homeTeamName;
    }

    @Override
    public String getHomeTeamIconUrl() {
	return this.homeTeamIconUrl;
    }

    @Override
    public String getGuestTeamName() {
	return this.guestTeamName;
    }

    @Override
    public String getGuestTeamIconUrl() {
	return this.guestTeamIconUrl;
    }

    @Override
    public String getHomeTeamFinalPoints() {
	return this.homeTeamFinalPoints;
    }

    @Override
    public String getGuestTeamFinalPoints() {
	return this.guestTeamFinalPoints;
    }

    @Override
    public String getHomeTeamHalfPoints() {
	return this.homeTeamHalfPoints;
    }

    @Override
    public String getGuestTeamHalfPoints() {
	return this.guestTeamHalfPoints;
    }

    public void setHomeTeamName(String homeTeamName) {
	this.homeTeamName = homeTeamName;
    }

    public void setHomeTeamIconUrl(String homeTeamIconUrl) {
	this.homeTeamIconUrl = homeTeamIconUrl;
    }

    public void setGuestTeamName(String guestTeamName) {
	this.guestTeamName = guestTeamName;
    }

    public void setGuestTeamIconUrl(String guestTeamIconUrl) {
	this.guestTeamIconUrl = guestTeamIconUrl;
    }

    private String getStringOfScorePoints(int points) {
	if (points < 0) {
	    return "-";
	} else {
	    return String.valueOf(points);
	}
    }

    public void setHomeTeamFinalPoints(int homeTeamFinalPoints) {
	this.homeTeamFinalPoints = getStringOfScorePoints(homeTeamFinalPoints);
    }

    public void setHomeTeamHalfPoints(int homeTeamHalfPoints) {
	this.homeTeamHalfPoints = getStringOfScorePoints(homeTeamHalfPoints);
    }

    public void setGuestTeamFinalPoints(int guestTeamFinalPoints) {
	this.guestTeamFinalPoints = getStringOfScorePoints(guestTeamFinalPoints);
    }

    public void setGuestTeamHalfPoints(int guestTeamHalfPoints) {
	this.guestTeamHalfPoints = getStringOfScorePoints(guestTeamHalfPoints);
    }
}
