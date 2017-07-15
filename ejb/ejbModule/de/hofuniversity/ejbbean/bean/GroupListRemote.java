package de.hofuniversity.ejbbean.bean;

import java.util.List;

import javax.ejb.Remote;

import de.hofuniversity.ejbbean.data.MatchGroupSummaryData;

/**
 * 
 * @author Markus Exner
 *
 */

@Remote
public interface GroupListRemote {
    public final String MAPPED_NAME = "ejb/GroupList";
    
    public List<MatchGroupSummaryData> getMatchGroupSummaryDataList(int groupId);
}
