package de.hofuniversity.ejbbean.bean;

import javax.ejb.Remote;

import de.hofuniversity.ejbbean.data.TeamDetailsSummaryData;

/**
 * 
 * @author Michael Jahn
 *
 */

@Remote
public interface TeamDetailsRemote {
    
    public final String MAPPED_NAME = "ejb/TeamDetails";
    
    public TeamDetailsSummaryData getTeamDetails(int id);
    
    public TeamDetailsSummaryData getTeamDetailsForIconURL(String iconURL);

}
