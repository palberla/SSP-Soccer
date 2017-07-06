package de.hofuniversity.ejbbean.data.impl;

import de.hofuniversity.ejbbean.data.GroupListSummaryData;

public class DefaultGroupListSummaryData implements GroupListSummaryData {
    
    private long orderId;
    private String groupName;
    
    public void setOrderId(long orderId)
    {
	this.orderId = orderId;
    }
    
    public void setGroupName(long orderId)
    {
	this.groupName = "" + orderId + ". Spieltag";
    }
    
    @Override
    public long getOrderId() {
	return this.orderId;
    }

    @Override
    public String getGroupName() {
	return this.groupName;
    }
    
}
