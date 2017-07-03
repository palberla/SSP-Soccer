package de.hofuniversity.ejbbean.bean;

import javax.ejb.Remote;

import de.hofuniversity.ejbbean.data.TeamDetailSummaryData;

@Remote
public interface TeamDetailsRemote {
    
    public final String MAPPED_NAME = "ejb/TeamDetails";
    
    public TeamDetailSummaryData getTeamDetail(int id);

}
