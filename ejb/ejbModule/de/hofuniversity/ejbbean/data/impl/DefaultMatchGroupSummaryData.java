package de.hofuniversity.ejbbean.data.impl;

import de.hofuniversity.ejbbean.data.MatchGroupSummaryData;

public class DefaultMatchGroupSummaryData implements MatchGroupSummaryData {
    
    private String homeTeamName, homeTeamIconUrl, guestTeamName, guestTeamIconUrl;
    private int homeTeamFinalPoints, homeTeamHalfPoints, guestTeamFinalPoints, guestTeamHalfPoints;
    
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
    public int getHomeTeamFinalPoints() {
	return this.homeTeamFinalPoints;
    }

    @Override
    public int getGuestTeamFinalPoints() {
	return this.guestTeamFinalPoints;
    }

    @Override
    public int getHomeTeamHalfPoints() {
	return this.homeTeamHalfPoints;
    }

    @Override
    public int getGuestTeamHalfPoints() {
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

    public void setHomeTeamFinalPoints(int homeTeamFinalPoints) {
        this.homeTeamFinalPoints = homeTeamFinalPoints;
    }

    public void setHomeTeamHalfPoints(int homeTeamHalfPoints) {
        this.homeTeamHalfPoints = homeTeamHalfPoints;
    }

    public void setGuestTeamFinalPoints(int guestTeamFinalPoints) {
        this.guestTeamFinalPoints = guestTeamFinalPoints;
    }

    public void setGuestTeamHalfPoints(int guestTeamHalfPoints) {
        this.guestTeamHalfPoints = guestTeamHalfPoints;
    }
}
