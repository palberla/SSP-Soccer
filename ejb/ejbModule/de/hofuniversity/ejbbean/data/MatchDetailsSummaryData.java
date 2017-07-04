package de.hofuniversity.ejbbean.data;

import java.util.List;

public interface MatchDetailsSummaryData {

    public String getHomeTeamName();
    
    public String getHomeTeamIconURL();
    
    public String getGuestTeamName();
    
    public String getGuestTeamIconURL();
    
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
    
    public List<MatchDetailsGoalSummaryData> getGoalList();
}
