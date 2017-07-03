package de.hofuniversity.ejbbean.data;

import java.util.Collection;

import de.hofuniversity.ejbbean.data.impl.MatchDetailGoal;

public interface MatchDetailSummaryData {

    public String getHomeTeamName();
    
    public String getHomeTeamIconURL();
    
    public String getGuestTeamName();
    
    public String getGuestIconURL();
    
    public int getFinalHomeScore();
    
    public int getFinalGuestScore();
    
    public int getHalfHomeScore();
    
    public int getHalfGuestScore();
    
    public String getStadiumName();
    
    public String getStadiumAdress();
    
    public int getStadiumCapacity();
    
    public double getLongitude();
    
    public double getLatitude();
    
    public String getStadiumInsideURL();
    
    public String getStadiumOutsideURL();
    
    public Collection<MatchDetailGoal> getGoalList();
}
