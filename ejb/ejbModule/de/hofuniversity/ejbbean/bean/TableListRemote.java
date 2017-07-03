package de.hofuniversity.ejbbean.bean;

import java.util.List;

import javax.ejb.Remote;

import de.hofuniversity.ejbbean.data.TeamGroupSummaryData;

@Remote
public interface TableListRemote {
    public final String MAPPED_NAME = "ejb/TableList";
    
    public List<TeamGroupSummaryData> getTableList();
}