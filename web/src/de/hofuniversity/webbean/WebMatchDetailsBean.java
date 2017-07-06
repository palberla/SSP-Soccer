package de.hofuniversity.webbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.directory.InitialDirContext;

import de.hofuniversity.ejbbean.bean.MatchDetailsRemote;
import de.hofuniversity.ejbbean.data.MatchDetailsSummaryData;

/**
 * 
 * @author Michael Jahn
 *
 */

@ManagedBean
@ViewScoped
public class WebMatchDetailsBean {
    
    private MatchDetailsRemote matchDetail = null;
    
    private MatchDetailsRemote thisGetMatchDetails () {
	if (matchDetail == null) {
	    try {
		matchDetail = (MatchDetailsRemote) new InitialDirContext().lookup(MatchDetailsRemote.MAPPED_NAME);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return matchDetail;
    }
   
    
    public MatchDetailsSummaryData getMatchDetails(int groupID) {
	
	return thisGetMatchDetails().getMatchDetails(groupID);
    }
    
	
}
