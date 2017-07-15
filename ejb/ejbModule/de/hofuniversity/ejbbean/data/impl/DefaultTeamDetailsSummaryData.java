package de.hofuniversity.ejbbean.data.impl;

import java.util.ArrayList;
import java.util.List;

import de.hofuniversity.core.Player;
import de.hofuniversity.ejbbean.data.MatchGroupSummaryData;
import de.hofuniversity.ejbbean.data.TeamDetailsSummaryData;

/**
 * 
 * @author Markus Exner
 *
 */

public class DefaultTeamDetailsSummaryData implements TeamDetailsSummaryData {
    
    private String teamName, teamIconURL, stadiumName, stadiumAdress, stadiumInsideURL, stadiumOutsideURL;
    private int stadiumCapacity;
    private double longitude,latitude;
    private List<MatchGroupSummaryData> teamMatchList;
    private List<String> teamPlayerList;

    public void setStadiumAdress(String stadiumAdress) {
        this.stadiumAdress = stadiumAdress;
    }

    public void addPlayer(Player player) {
        this.getTeamPlayerList().add(player.getName());
    }
    
    public void addTeamMatch(MatchGroupSummaryData mgsd)
    {
	this.getTeamMatches().add(mgsd);
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamIconURL(String teamIconURL) {
        this.teamIconURL = teamIconURL;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public void setStadiumInsideURL(String stadiumInsideURL) {
        this.stadiumInsideURL = stadiumInsideURL;
    }

    public void setStadiumOutsideURL(String stadiumOutsideURL) {
        this.stadiumOutsideURL = stadiumOutsideURL;
    }

    public void setStadiumCapacity(int stadiumCapacity) {
        this.stadiumCapacity = stadiumCapacity;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String getTeamName() {
	return this.teamName;
    }

    @Override
    public String getTeamIconURL() {
	return this.teamIconURL;
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
    public List<MatchGroupSummaryData> getTeamMatches() {
	if (this.teamMatchList == null) {
	    this.teamMatchList = new ArrayList<MatchGroupSummaryData>();
	}
	return this.teamMatchList;
    }

    @Override
    public List<String> getTeamPlayerList() {
	if (this.teamPlayerList == null)
	{
	    this.teamPlayerList = new ArrayList<String>();
	}
	return this.teamPlayerList;
    }
}
