package de.hofuniversity.webbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.directory.InitialDirContext;

import de.hofuniversity.ejbbean.bean.TeamDetailsRemote;
import de.hofuniversity.ejbbean.data.TeamDetailsSummaryData;

@ManagedBean
@ViewScoped
public class WebTeamDetailBean {
    
    private TeamDetailsRemote teamRemote = null;
    
    private TeamDetailsRemote thisGetTeamDetail() {
	if (teamRemote == null) {
	    try {
		teamRemote = (TeamDetailsRemote) new InitialDirContext().lookup(TeamDetailsRemote.MAPPED_NAME);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return teamRemote;
    }
    
    public TeamDetailsSummaryData getTeamDetails(int id) {
	return thisGetTeamDetail().getTeamDetails(id);
    }
    
}
