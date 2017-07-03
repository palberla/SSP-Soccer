package de.hofuniversity.webbean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.hofuniversity.ejbbean.bean.TableListRemote;
import de.hofuniversity.ejbbean.data.TeamGroupSummaryData;

@ManagedBean
@ViewScoped
public class TableListWebBean {
    private TableListRemote tableList = null;
    
    public TableListWebBean() {}
    
    private TableListRemote getTableList()
    {
	if (tableList == null)
	{
	    try {
		tableList = (TableListRemote) new InitialContext().lookup(TableListRemote.MAPPED_NAME);
	    } catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	return tableList;
    }
    
    public List<TeamGroupSummaryData> getTGSDTableList()
    {
	return new ArrayList<TeamGroupSummaryData>(this.getTableList().getTableList());
    }
    
    public List<String> getTeamNameList()
    {
	List<String> nameList = new ArrayList<String>();
	Collection<TeamGroupSummaryData> tgsdCollection = this.getTableList().getTableList();
	for (TeamGroupSummaryData tgsd : tgsdCollection)
	{
	    nameList.add(tgsd.getTeamName());
	}
	return nameList;
    }
}