package de.hofuniversity.ejbbean.bean;

import java.util.List;

import javax.ejb.Remote;

import de.hofuniversity.ejbbean.data.GroupListSummaryData;

@Remote
public interface GroupOrderListRemote {
    public final String MAPPED_NAME = "ejb/GroupOrderList";
    
    public List<GroupListSummaryData> getGroupListSummaryDataList();
}
