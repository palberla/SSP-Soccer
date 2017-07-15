package de.hofuniversity.ejbbean.data;

import java.util.List;

/**
 * 
 * @author Michael Jahn
 *
 */

public interface TeamDetailsSummaryData {
    
    public String getTeamName();
    
    public String getTeamIconURL();
    
    public String getStadiumName();
    
    public String getStadiumAdress();
    
    public int getStadiumCapacity();
    
    public double getLongitude();
    
    public double getLatitude();
    
    public String getStadiumInsideURL();
    
    public String getStadiumOutsideURL();
    
    public List<MatchGroupSummaryData> getTeamMatches();
    
    public List<String> getTeamPlayerList();

}
