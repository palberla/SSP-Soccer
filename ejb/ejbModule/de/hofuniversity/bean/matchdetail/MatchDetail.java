package de.hofuniversity.bean.matchdetail;

import java.util.Collection;

public interface MatchDetail {

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
