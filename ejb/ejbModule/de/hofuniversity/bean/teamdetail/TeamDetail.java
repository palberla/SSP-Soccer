package de.hofuniversity.bean.teamdetail;

import java.util.Collection;

import de.hofuniversity.bean.grouplist.MatchGroupSummaryData;

public interface TeamDetail {
    
    public String getTeamName();
    
    public String getTeamIconURL();
    
    public String getStadiumName();
    
    public String getStadiumAdress();
    
    public int getStadiumCapacity();
    
    public double getLongitude();
    
    public double getLatitude();
    
    public String getStadiumInsideURL();
    
    public String getStadiumOutsideURL();
    
    public Collection<MatchGroupSummaryData> getTeamMatches();
    
    public Collection<String> getTeamPlayerList();

}
