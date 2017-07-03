package de.hofuniversity.ejbbean.bean.impl;

import javax.ejb.Stateless;

import de.hofuniversity.ejbbean.bean.TeamDetailsRemote;
import de.hofuniversity.ejbbean.data.TeamDetailSummaryData;

@Stateless(name = TeamDetailsRemote.MAPPED_NAME, mappedName = TeamDetailsRemote.MAPPED_NAME)
public class TeamDetailsBean implements TeamDetailsRemote {
    
    public TeamDetailSummaryData getTeamDetail(int id) {
	return null;
    }

}