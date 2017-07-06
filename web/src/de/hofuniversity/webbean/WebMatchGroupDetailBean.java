package de.hofuniversity.webbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.directory.InitialDirContext;

import de.hofuniversity.ejbbean.bean.GroupListRemote;
import de.hofuniversity.ejbbean.data.MatchGroupSummaryData;

@ManagedBean
@ViewScoped
public class WebMatchGroupDetailBean {
    
    private GroupListRemote groupList = null;
    
    private GroupListRemote thisGetGroupList() {
	if (groupList == null) {
	    try {
		groupList = (GroupListRemote) new InitialDirContext().lookup(GroupListRemote.MAPPED_NAME);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return groupList;
    }
    
    public List<MatchGroupSummaryData> getMatchGroupList(int groupID) {
	return this.thisGetGroupList().getMatchGroupSummaryDataList(groupID);
	
    }

}
