package de.hofuniversity.ejbbean.bean.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import de.hofuniversity.ejbbean.bean.GroupOrderListRemote;
import de.hofuniversity.ejbbean.data.GroupListSummaryData;
import de.hofuniversity.ejbbean.data.impl.DefaultGroupListSummaryData;
import de.hofuniversity.queries.MatchQuery;
import de.hofuniversity.queries.QueryCache;

/**
 * 
 * @author Markus Exner
 *
 */
@Stateless(name = GroupOrderListRemote.MAPPED_NAME, mappedName = GroupOrderListRemote.MAPPED_NAME)
public class GroupOrderListBean implements GroupOrderListRemote {


    private QueryCache queryCache;
    
    public GroupOrderListBean() {}
    
    private QueryCache getQueryCache()
    {
	if (this.queryCache == null)
	{
	    this.queryCache = QueryCache.getInstance();
	}
	return this.queryCache;
    }

    @Override
    public List<GroupListSummaryData> getGroupListSummaryDataList() {
	MatchQuery matchQuery = this.getQueryCache().getMatchQuery();
	List<GroupListSummaryData> glsdList = new ArrayList<GroupListSummaryData>();
	
	for (Long orderId : matchQuery.getAllGroups())
	{
	    DefaultGroupListSummaryData glsd = new DefaultGroupListSummaryData();
	    glsd.setOrderId(orderId);
	    glsd.setGroupName(orderId);
	    glsdList.add(glsd);
	}
	
	return glsdList;
    } 
    
    
}
